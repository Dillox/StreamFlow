package Vista;

import Controlador.ContenidoController;
import Controlador.UsuarioController;
import Modelo.*;
import Servicio.RecomendacionService;
import Servicio.SuscripcionService;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner scanner = new Scanner(System.in);

    private final ContenidoController contenidoCtrl = new ContenidoController();
    private final UsuarioController   usuarioCtrl   = new UsuarioController();
    private final SuscripcionService  suscripcion   = new SuscripcionService();
    private final RecomendacionService recomendacion = new RecomendacionService();

   
    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║       STREAMFLOW  v1.0       ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. Gestionar contenidos     ║");
            System.out.println("║  2. Gestionar usuarios       ║");
            System.out.println("║  3. Calcular suscripción     ║");
            System.out.println("║  4. Recomendar contenidos    ║");
            System.out.println("║  0. Salir                    ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Opción: ");
            opcion = leerInt();

            switch (opcion) {
                case 1 -> menuContenidos();
                case 2 -> menuUsuarios();
                case 3 -> menuSuscripcion();
                case 4 -> menuRecomendaciones();
                case 0 -> System.out.println("\n¡Hasta luego!");
                default -> System.out.println(" Opción inválida.");
            }
        } while (opcion != 0);
    }

 
    private void menuContenidos() {
        int op;
        do {
            System.out.println("\n── Contenidos ──────────────────");
            System.out.println("  1. Listar todos");
            System.out.println("  2. Agregar contenido");
            System.out.println("  3. Buscar por ID");
            System.out.println("  4. Eliminar por ID");
            System.out.println("  0. Volver");
            System.out.print("Opción: ");
            op = leerInt();

            switch (op) {
                case 1 -> listarContenidos();
                case 2 -> agregarContenido();
                case 3 -> buscarContenido();
                case 4 -> eliminarContenido();
                case 0 -> {}
                default -> System.out.println(" Opción inválida.");
            }
        } while (op != 0);
    }

    private void listarContenidos() {
        List<Contenido> lista = contenidoCtrl.listarContenido();
        if (lista.isEmpty()) {
            System.out.println("  (Sin contenidos registrados)");
            return;
        }
        System.out.println("\n── Catálogo ─────────────────────");
        for (Contenido c : lista) {
            System.out.printf("  [%d] %s (%s) - %s%n",
                    c.getID(), c.getTitulo(), c.getTipo(), c.getCalidad());
        }
    }

    private void agregarContenido() {
        System.out.println("\nTipo: 1=Película  2=Serie  3=Documental");
        System.out.print("Tipo: ");
        int tipo = leerInt();

        System.out.print("ID     : "); int id    = leerInt();
        System.out.print("Título : "); String titulo  = leerLinea();
        System.out.print("Género : "); String genero  = leerLinea();
        Calidad calidad = pedirCalidad();

        Contenido c = switch (tipo) {
            case 1 -> {
                System.out.print("Duración (min): "); int dur = leerInt();
                System.out.print("Director      : "); String dir = leerLinea();
                System.out.print("Año estreno   : "); int anio = leerInt();
                yield new Pelicula(id, titulo, genero, calidad, dur, dir, anio);
            }
            case 2 -> {
                System.out.print("Temporadas  : "); int temp = leerInt();
                System.out.print("Capítulos   : "); int caps = leerInt();
                System.out.print("Min/capítulo: "); int minCap = leerInt();
                yield new Serie(id, titulo, genero, calidad, temp, caps, minCap);
            }
            case 3 -> {
                System.out.print("Tema      : "); String tema = leerLinea();
                System.out.print("Duración  : "); int dur = leerInt();
                yield new Documental(id, titulo, genero, calidad, tema, dur);
            }
            default -> null;
        };

        if (c != null) {
            contenidoCtrl.agregarContenido(c);
        } else {
            System.out.println(" Tipo no reconocido.");
        }
    }

    private void buscarContenido() {
        System.out.print("ID a buscar: ");
        int id = leerInt();
        Contenido c = contenidoCtrl.buscarContenido(id);
        if (c != null) {
            System.out.println("\n" + c.obtenerDetalles());
            System.out.println(c.reproducir());
        } else {
            System.out.println(" No encontrado.");
        }
    }

    private void eliminarContenido() {
        System.out.print("ID a eliminar: ");
        int id = leerInt();
        contenidoCtrl.eliminarContenido(id);
    }


    private void menuUsuarios() {
        int op;
        do {
            System.out.println("\n── Usuarios ────────────────────");
            System.out.println("  1. Listar todos");
            System.out.println("  2. Agregar usuario");
            System.out.println("  3. Buscar por ID");
            System.out.println("  4. Eliminar por ID");
            System.out.println("  0. Volver");
            System.out.print("Opción: ");
            op = leerInt();

            switch (op) {
                case 1 -> listarUsuarios();
                case 2 -> agregarUsuario();
                case 3 -> buscarUsuario();
                case 4 -> eliminarUsuario();
                case 0 -> {}
                default -> System.out.println(" Opción inválida.");
            }
        } while (op != 0);
    }

    private void listarUsuarios() {
        var lista = usuarioCtrl.listarUsuarios();
        if (lista.isEmpty()) {
            System.out.println("  (Sin usuarios registrados)");
            return;
        }
        System.out.println("\n── Usuarios ─────────────────────");
        for (var u : lista) {
            System.out.printf("  [%d] %s  — Plan: %s%n",
                    u.getId(), u.getNombre(), u.getPlan());
        }
    }

    private void agregarUsuario() {
        System.out.print("ID    : "); int id = leerInt();
        System.out.print("Nombre: "); String nombre = leerLinea();
        System.out.print("Plan (SD/HD/4K): "); String plan = leerLinea().toUpperCase();
        usuarioCtrl.agregarUsuario(new Usuario(id, nombre, plan));
    }

    private void buscarUsuario() {
        System.out.print("ID a buscar: ");
        int id = leerInt();
        var u = usuarioCtrl.buscarUsuario(id);
        if (u != null) {
            System.out.printf("  [%d] %s  — Plan: %s%n", u.getId(), u.getNombre(), u.getPlan());
        } else {
            System.out.println(" No encontrado.");
        }
    }

    private void eliminarUsuario() {
        System.out.print("ID a eliminar: ");
        int id = leerInt();
        usuarioCtrl.eliminarUsuario(id);
    }

    private void menuSuscripcion() {
        Calidad calidad = pedirCalidad();
        double costo = suscripcion.calcularCosto(calidad);
        String plan  = suscripcion.obtenerPlan(calidad);
        System.out.printf("%nPlan: %s (%s) - $%.2f / mes%n", plan, calidad, costo);
    }

   
    private void menuRecomendaciones() {
        System.out.print("Género a buscar: ");
        String genero = leerLinea();
        List<Contenido> todos = contenidoCtrl.listarContenido();
        List<Contenido> recs  = recomendacion.recomendarPorGenero(todos, genero);

        if (recs.isEmpty()) {
            System.out.println("  (Sin resultados para \"" + genero + "\")");
        } else {
            System.out.println("\n── Recomendaciones para: " + genero + " ──");
            for (Contenido c : recs) {
                System.out.printf("   [%d] %s (%s)%n", c.getID(), c.getTitulo(), c.getTipo());
            }
        }
    }


    private Calidad pedirCalidad() {
        System.out.println("Calidad: 1=SD  2=HD  3=4K");
        System.out.print("Calidad: ");
        return switch (leerInt()) {
            case 2  -> Calidad.HD;
            case 3  -> Calidad.CUATRO_K;
            default -> Calidad.SD;
        };
    }

    private int leerInt() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Ingrese un número: ");
        }
        int v = scanner.nextInt();
        scanner.nextLine();
        return v;
    }

    private String leerLinea() {
        String linea = scanner.nextLine().trim();
        if (linea.isEmpty()) linea = scanner.nextLine().trim();
        return linea;
    }
}
