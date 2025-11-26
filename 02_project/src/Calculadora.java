import java.util.Scanner;

public class Calculadora {

    public void main() {
        int opc;
        do {
            int result = 0;
            opc = mostrarMenu();
            switch (opc) {
                case 1:
                    result = sumar(leerNumero(), leerNumero());
                    break;
                case 2:
                    result = restar(leerNumero(), leerNumero());
                    break;
                case 3:
                    int firstNumber = leerNumero();
                    int secondNumber = 0;
                    do {
                        secondNumber = leerNumero();
                    } while (secondNumber == 0);
                    result = dividir(firstNumber, secondNumber);
                    break;
                case 4:
                    result = multiplicar(leerNumero(), leerNumero());
                    break;

                default:
                    System.out.println("Ciao Mari Carmen!!");
                    break;
            }
            if (opc != 0) {
                System.out.println("El resultado es: " + result);
            }
        } while (opc != 0);
    }

    public static int sumar(int numero1, int numero2) {
        return numero1 + numero2;
    }

    public static int restar(int numero1, int numero2) {
        return numero1 - numero2;
    }

    public static int dividir(int numero1, int numero2) {
        return numero1 / numero2;
    }

    public static int multiplicar(int numero1, int numero2) {
        return numero1 * numero2;
    }

    public static int mostrarMenu() {
        Scanner teclado = new Scanner(System.in);
        int opc;
        System.out.println("Elige una opción: \n0)Salir \n 1) sumar \n 2) restar \n 3) dividir \n 4) multiplicar");
        opc = teclado.nextInt();
        while (opc != 0 && opc != 1 && opc != 2 && opc != 3 && opc != 4) {
            System.out.println("Pero qué haces!! Una de las oprciones!!");
            System.out.println("Elige una opción: \n0)Salir \n 1) sumar \n 2) restar \n 3) dividir \n 4) multiplicar");
            opc = teclado.nextInt();
        }
        return opc;
    }

    public static int leerNumero() {
        Scanner teclado = new Scanner(System.in);
        int numero;
        System.out.println("Inserta un número, por favor");
        numero = teclado.nextInt();
        return numero;

    }

}