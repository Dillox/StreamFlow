package Vista;

import java.util.Scanner;

public class MenuPrincipal {

    private Scanner scanner;

    public MenuPrincipal() {
        scanner = new Scanner(System.in);
    }

    public void iniciar() {

        int opcion;

        do {

            System.out.println("\n========== STREAMFLOW ==========");
            System.out.println("1. Gestionar contenidos");
            System.out.println("2. Gestionar usuarios");
            System.out.println("3. Calcular suscripción");
            System.out.println("4. Recomendar contenidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("Gestión de contenidos");
                    break;

                case 2:
                    System.out.println("Gestión de usuarios");
                    break;

                case 3:
                    System.out.println("Cálculo de suscripción");
                    break;

                case 4:
                    System.out.println("Recomendación de contenidos");
                    break;

                case 0:
                    System.out.println("Gracias por usar StreamFlow.");
                    break;

                default:
                    System.out.println("Opción inválida.");

            }

        } while (opcion != 0);

    }

}