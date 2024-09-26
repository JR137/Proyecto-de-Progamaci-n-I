import java.util.Scanner;

public class Main {


    public static double[][] IntroductMatriz(int filas, int columnas) {
        Scanner sc = new Scanner(System.in);
        double[][] mat = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("Fila " + (i + 1) + " | Columna " + (j + 1) + ": ");
                mat[i][j] = sc.nextDouble();
            }
        }
        return mat;
    }

    public static void main(String[] args) {

        // Array de 10 elementos donde se pueden guardar matrices
        double[][][] matrices = new double[10][][];

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            try {
                // comando para limpiar la pantalla
                System.out.print("\033[H\033[2J");

                System.out.println("Calculadora de matrices");
                System.out.println("Que desea hacer?");
                System.out.println("1. Introducir matriz");
                System.out.println("2. Sumar matrices");
                System.out.println("3. Restar matrices");
                System.out.println("4. Multiplicar matrices");
                System.out.println("5. Transponer matriz");
                System.out.println("6. Calcular determinante de matriz");
                System.out.println("7. Calcular adjunta de matriz");
                System.out.println("8. Calcular inversa de matriz");
                System.out.println("9. Guardar matriz en archivo");
                System.out.println("10. Cargar matriz desde archivo");
                System.out.println("11. Ver matrices guardadas");
                System.out.println("12. Salir");

                opcion = scanner.nextInt();
                scanner.nextLine();
                int i1;
                int i2;
                String guardar;
                int identificador;
                switch (opcion) {
                    case 1 -> {
                        // Que matriz se va a introducir
                        System.out.println("En que hueco desea introducir la matriz? (1-10)");
                        i1 = scanner.nextInt();
                        scanner.nextLine();
                        // Numero de filas y columnas
                        System.out.println("Introduzca el numero de filas");
                        int filas = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Introduzca el numero de columnas");
                        int columnas = scanner.nextInt();
                        scanner.nextLine();
                        // Introducir matriz
                        matrices[i1- 1] = IntroductMatriz(filas, columnas);
                    }
                    case 2 -> {
                        // Que matriz se va a sumar
                        System.out.println("Que matriz desea sumar? (1-10)");
                        i1 = scanner.nextInt();
                        scanner.nextLine();
                        // Que matriz se va a sumar
                        System.out.println("Que otra matriz desea sumar? (1-10)");
                        i2 = scanner.nextInt();
                        scanner.nextLine();
                        if (matrices[i1- 1] == null || matrices[i2- 1] == null || i1 < 1 || i1 > 10 || i2 < 1 || i2 > 10) {
                            System.out.println("No existe la matriz seleccionada");
                            break;
                        }
                        // Sumar matrices
                        double[][] sum = MatrizUtils.suma(matrices[i1- 1], matrices[i2- 1]);

                        // Preguntar si se quiere guardar la matriz
                        System.out.println("Desea guardar la matriz resultante? (y/n)");
                        guardar = scanner.next();
                        scanner.nextLine();
                        if (guardar.equals("y")) {
                            System.out.println("En que posicion desea guardar la matriz? (1-10)");
                            i1 = scanner.nextInt();
                            scanner.nextLine();
                            matrices[i1- 1] = sum;
                        }
                        // Imprimir matriz
                        MatrizUtils.printMatrix(sum);
                    }
                    case 3 -> {
                        // Que matriz se va a restar
                        System.out.println("Que matriz desea restar? (1-10)");
                        i1 = scanner.nextInt();
                        scanner.nextLine();
                        // Que matriz se va a restar
                        System.out.println("Que otra matriz desea restar? (1-10)");
                        i2 = scanner.nextInt();
                        scanner.nextLine();
                        if (matrices[i1- 1] == null || matrices[i2- 1] == null || i1 < 1 || i1 > 10 || i2 < 1 || i2 > 10) {
                            System.out.println("No existe la matriz seleccionada");
                            break;
                        }
                        // Restar matrices
                        double[][] resta = MatrizUtils.resta(matrices[i1- 1], matrices[i2- 1]);

                        // Preguntar si se quiere guardar la matriz
                        System.out.println("Desea guardar la matriz resultante? (y/n)");
                        guardar = scanner.next();
                        scanner.nextLine();
                        if (guardar.equals("y")) {
                            System.out.println("En que posicion desea guardar la matriz? (1-10)");
                            i1 = scanner.nextInt();
                            scanner.nextLine();
                            matrices[i1- 1] = resta;
                        }
                        // Imprimir matriz
                        MatrizUtils.printMatrix(resta);
                    }
                    case 4 -> {
                        // Que matriz se va a multiplicar
                        System.out.println("Que matriz desea multiplicar? (1-10)");
                        i1 = scanner.nextInt();
                        scanner.nextLine();
                        // Que matriz se va a multiplicar
                        System.out.println("Que otra matriz desea multiplicar? (1-10)");
                        i2 = scanner.nextInt();
                        scanner.nextLine();
                        if (matrices[i1- 1] == null || matrices[i2- 1] == null || i1 < 1 || i1 > 10 || i2 < 1 || i2 > 10) {
                            System.out.println("No existe la matriz seleccionada");
                            break;
                        }
                        // Multiplicar matrices
                        double[][] mult = MatrizUtils.multiplicacion(matrices[i1- 1], matrices[i2- 1]);
                        if (mult == null) {
                            System.out.println("No se puede multiplicar las matrices");
                            break;
                        }
                        // Preguntar si se quiere guardar la matriz
                        System.out.println("Desea guardar la matriz resultante? (y/n)");
                        guardar = scanner.next();
                        scanner.nextLine();
                        if (guardar.equals("y")) {
                            System.out.println("En que posicion desea guardar la matriz? (1-10)");
                            i1 = scanner.nextInt();
                            scanner.nextLine();
                            matrices[i1- 1] = mult;
                        }

                        // Imprimir matriz
                        MatrizUtils.printMatrix(mult);
                    }
                    case 5 -> {
                        // Que matriz se va a transponer
                        System.out.println("Que matriz desea multiplicar? (1-10)");
                        i1 = scanner.nextInt();
                        scanner.nextLine();
                        if (matrices[i1- 1] == null || i1 < 1 || i1 > 10) {
                            System.out.println("No existe la matriz seleccionada");
                            break;
                        }
                        // Transponer matriz
                        double[][] traspuesta = MatrizUtils.traspuesta(matrices[i1- 1]);

                        // Preguntar si se quiere guardar la matriz
                        System.out.println("Desea guardar la matriz resultante? (y/n)");
                        guardar = scanner.next();
                        scanner.nextLine();
                        if (guardar.equals("y")) {
                            System.out.println("En que posicion desea guardar la matriz? (1-10)");
                            i1 = scanner.nextInt();
                            scanner.nextLine();
                            matrices[i1- 1] = traspuesta;
                        }

                        // Imprimir matriz
                        MatrizUtils.printMatrix(traspuesta);
                    }
                    case 6 -> {
                        // Que matriz se va a calcular el determinante
                        System.out.println("Que matriz desea calcular el determinante? (1-10)");
                        i1 = scanner.nextInt();
                        scanner.nextLine();
                        if (matrices[i1- 1] == null || i1 < 1 || i1 > 10) {
                            System.out.println("No existe la matriz seleccionada");
                            break;
                        }
                        // Calcular determinante
                        double determinante = MatrizUtils.det(matrices[i1- 1]);

                        // Imprimir Determinante
                        System.out.println("El determinante es: " + determinante);
                    }
                    case 7 -> {
                        // Que matriz se va a calcular la adjunta
                        System.out.println("Que matriz desea calcular la adjunta? (1-10)");
                        i1 = scanner.nextInt();
                        scanner.nextLine();
                        if (matrices[i1- 1] == null || i1 < 1 || i1 > 10) {
                            System.out.println("No existe la matriz seleccionada");
                            break;
                        }
                        // Calcular adjunta
                        double[][] adjunta = MatrizUtils.adjunta(matrices[i1- 1]);

                        // Preguntar si se quiere guardar la matriz
                        System.out.println("Desea guardar la matriz resultante? (y/n)");
                        guardar = scanner.next();
                        scanner.nextLine();
                        if (guardar.equals("y")) {
                            System.out.println("En que posicion desea guardar la matriz? (1-10)");
                            i1 = scanner.nextInt();
                            scanner.nextLine();
                            matrices[i1- 1] = adjunta;
                        }
                        // Imprimir matriz
                        MatrizUtils.printMatrix(adjunta);
                    }
                    case 8 -> {
                        // Que matriz se va a calcular la inversa
                        System.out.println("Que matriz desea calcular la inversa? (1-10)");
                        i1 = scanner.nextInt();
                        scanner.nextLine();
                        if (matrices[i1- 1] == null || i1 < 1 || i1 > 10) {
                            System.out.println("No existe la matriz seleccionada");
                            break;
                        }
                        // Calcular inversa
                        double[][] inversa = MatrizUtils.inversa(matrices[i1- 1]);
                        if (inversa == null) {
                            System.out.println("La matriz no tiene inversa");
                            break;
                        }
                        // Preguntar si se quiere guardar la matriz
                        System.out.println("Desea guardar la matriz resultante? (y/n)");
                        guardar = scanner.next();
                        scanner.nextLine();
                        if (guardar.equals("y")) {
                            // Que matriz se va a introducir
                            System.out.println("En que matriz desea guardar la matriz resultante? (1-10)");
                            i1 = scanner.nextInt();
                            scanner.nextLine();
                            matrices[i1- 1] = inversa;
                        }
                        // Imprimir matriz
                        MatrizUtils.printMatrix(inversa);
                    }
                    // Guardar matriz en archivo
                    case 9 -> {
                        // Que matriz se va a guardar
                        System.out.println("Que matriz desea guardar? (1-10)");
                        i1 = scanner.nextInt();
                        scanner.nextLine();
                        if (matrices[i1- 1] == null || i1 < 1 || i1 > 10) {
                            System.out.println("No existe la matriz seleccionada");
                            break;
                        }
                        // Identificar el archivo (Int)
                        System.out.println("Introduzca un identificador para el archivo (número)");
                        identificador = scanner.nextInt();
                        scanner.nextLine();
                        // Guardar matriz
                        MatrizUtils.saveMatrix(matrices[i1- 1], identificador);
                    }
                    // Cargar matriz de archivo
                    case 10 -> {
                        // Identificar el archivo (Int)
                        System.out.println("Introduzca el identificador del archivo (número)");
                        identificador = scanner.nextInt();
                        scanner.nextLine();

                        // Que matriz se va a cargar
                        System.out.println("En que matriz desea guardar la matriz cargada? (1-10)");
                        i1 = scanner.nextInt();
                        scanner.nextLine();
                        if (i1 < 1 || i1 > 10) {
                            System.out.println("Error");
                            break;
                        }
                        // Cargar matriz
                        matrices[i1- 1] = MatrizUtils.loadMatrix(identificador);
                    }
                    // Ver todas las matrices guardadas
                    case 11 -> {
                        for (int i = 0; i < matrices.length; i++) {
                            System.out.println("Matriz " + (i+1) + ":");
                            if (matrices[i] != null) {
                                MatrizUtils.printMatrix(matrices[i]);
                            } else {
                                System.out.println("\tMatriz vacia");
                            }
                        }
                    }
                    // Salir
                    case 12 -> {
                        System.out.println("Hasta luego!");
                        return;
                    }

                    default ->{
                        System.out.println("Opcion invalida");
                        continue;
                    }

                }
            }
            catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("Pulse enter para continuar");
            scanner.nextLine();
        } while (opcion != 12);

    }
}