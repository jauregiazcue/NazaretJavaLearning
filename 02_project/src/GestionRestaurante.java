/*********** By: Ainara de Miguel  @ teknofriker */


import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GestionRestaurante {

    public static final String NOMBRE_FICHERO_TICKETS = "tickets.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int NUM_MESAS = 3;

       
        String[] nombresMesas = new String[NUM_MESAS];
        nombresMesas[0] = "MESA 1";
        nombresMesas[1] = "MESA 2";
        nombresMesas[2] = "MESA 3";

        // Cuentas de cada mesa
        double[] cuentasMesas = new double[NUM_MESAS];

        double facturacionTotal = 0.0;
        int mesasCerradas = 0;

        int opcion;

        do {
            opcion = mostrarMenuPrincipal(sc);

            switch (opcion) {
                case 1: // Añadir consumición
                    int indiceMesa1 = seleccionarMesa(sc, nombresMesas);
                    double importe = leerImporte(sc);

                    cuentasMesas[indiceMesa1] = sumarAcuenta(cuentasMesas[indiceMesa1], importe);

                    System.out.println("Consumición añadida correctamente a " + nombresMesas[indiceMesa1] + ".\n");
                    break;

                case 2: // Ver cuenta
                    int indiceMesa2 = seleccionarMesa(sc, nombresMesas);
                    mostrarCuentaMesa(nombresMesas[indiceMesa2], cuentasMesas[indiceMesa2]);
                    break;

                case 3: // Cerrar cuenta
                    int indiceMesa3 = seleccionarMesa(sc, nombresMesas);
                    double cuentaCerrar = cuentasMesas[indiceMesa3];

                    if (!hayCuentaAbierta(cuentaCerrar)) {
                        System.out.println("La mesa " + nombresMesas[indiceMesa3] + " no tiene consumiciones.\n");
                        break;
                    }

                    double descuento = calcularDescuento(cuentaCerrar);
                    double totalFinal = calcularTotalConDescuento(cuentaCerrar, descuento);

                    mostrarCierreMesa(nombresMesas[indiceMesa3], cuentaCerrar, descuento, totalFinal);

                    String camarero = pedirNombreCamarero(sc);

                    escribirTicketEnArchivo(
                            NOMBRE_FICHERO_TICKETS,
                            nombresMesas[indiceMesa3],
                            camarero,
                            cuentaCerrar,
                            descuento,
                            totalFinal
                    );

                    facturacionTotal += totalFinal;
                    mesasCerradas++;

                    // vaciamos cuenta de la mesa
                    cuentasMesas[indiceMesa3] = 0.0;

                    break;

                case 4: // total descripción
                    mostrarResumenRestaurante(nombresMesas, cuentasMesas, facturacionTotal, mesasCerradas);
                    break;

                case 5:
                    System.out.println("Saliendo del gestor. ¡Hasta luego Mari Carmen!");
                    break;
            }

        } while (opcion != 5);

        sc.close();
    }

    // ************** LA GESTIÓN **************

    public static int mostrarMenuPrincipal(Scanner sc) {
        int opcion;
        do {
            System.out.println("------ GESTOR DE RESTAURANTE ------");
            System.out.println("1. Añadir consumición a una mesa");
            System.out.println("2. Ver cuenta de una mesa, la dolorosa");
            System.out.println("3. Cerrar cuenta de una mesa");
            System.out.println("4. Ver resumen del restaurante");
            System.out.println("5. Salir");
            System.out.println("-----------------------------------");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }

    public static int seleccionarMesa(Scanner sc, String[] nombresMesas) {
        int mesa;
        do {
            System.out.println("Mesas disponibles:");
            for (int i = 0; i < nombresMesas.length; i++) {
                System.out.println((i + 1) + ". " + nombresMesas[i]);
            }
            System.out.print("Elige una mesa (1-" + nombresMesas.length + "): ");
            mesa = sc.nextInt();
        } while (mesa < 1 || mesa > nombresMesas.length);

        
        return mesa - 1;
    }

    public static double leerImporte(Scanner sc) {
        double importe;
        do {
            System.out.print("Introduce importe de la consumición (€): ");
            importe = sc.nextDouble();
        } while (importe <= 0);
        return importe;
    }

    public static double sumarAcuenta(double cuentaActual, double importe) {
        return cuentaActual + importe;
    }

    public static boolean hayCuentaAbierta(double cuentaMesa) {
        return cuentaMesa > 0;
    }

    //Descuentico para los que han gastado bien

    public static double calcularDescuento(double cuenta) {
        if (cuenta > 50) {       
            return cuenta * 0.10; 
        }
        return 0;
    }

    public static double calcularTotalConDescuento(double cuenta, double descuento) {
        return cuenta - descuento;
    }

    public static void mostrarCuentaMesa(String nombreMesa, double cuenta) {
        System.out.println("\n--- " + nombreMesa + " ---");
        System.out.printf("Importe acumulado: %.2f €\n\n", cuenta);
    }

    public static void mostrarCierreMesa(String nombreMesa, double cuenta, double descuento, double totalFinal) {
        System.out.println("\n--- CIERRE DE " + nombreMesa + " ---");
        System.out.printf("Importe sin descuento: %.2f €\n", cuenta);
        System.out.printf("Descuento aplicado: %.2f €\n", descuento);
        System.out.printf("TOTAL A PAGAR: %.2f €\n\n", totalFinal);
    }

    public static void mostrarResumenRestaurante(String[] nombresMesas,
                                                 double[] cuentasMesas,
                                                 double facturacionTotal,
                                                 int mesasCerradas) {
        System.out.println("\n====== RESUMEN DEL RESTAURANTE ======");
        for (int i = 0; i < nombresMesas.length; i++) {
            System.out.printf("%s: %.2f €\n", nombresMesas[i], cuentasMesas[i]);
        }
        System.out.println("-------------------------------------");
        System.out.println("Mesas cerradas: " + mesasCerradas);
        System.out.printf("Facturación total: %.2f €\n", facturacionTotal);
        System.out.println("=====================================\n");
    }

    // ---------- Camarero y escritura de ticket ----------

    public static String pedirNombreCamarero(Scanner sc) {
        sc.nextLine(); // limpiar salto pendiente
        String nombre;
        do {
            System.out.print("Nombre del camarero/a: ");
            nombre = sc.nextLine().trim();
        } while (nombre.isEmpty());
        return nombre;
    }

    public static void escribirTicketEnArchivo(String fichero,
                                               String nombreMesa,
                                               String camarero,
                                               double cuenta,
                                               double descuento,
                                               double totalFinal) {
        try (FileWriter fw = new FileWriter(fichero, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println("-------- TICKET --------");
            out.println("Mesa: " + nombreMesa);
            out.println("Camarero/a: " + camarero);
            out.printf("Importe sin descuento: %.2f €%n", cuenta);
            out.printf("Descuento: %.2f €%n", descuento);
            out.printf("Total a pagar: %.2f €%n", totalFinal);
            out.println("------------------------");

        } catch (IOException e) {
            System.out.println("ERROR al escribir el ticket en el archivo: " + e.getMessage());
        }
    }

}
