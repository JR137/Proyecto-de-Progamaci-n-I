import java.io.*;
import java.nio.charset.StandardCharsets;

public class MatrizUtils {
    // Imprimir una matriz por consola
    public static void printMatrix(double[][]m) {
        for (double[] doubles : m) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.printf("%10s", doubles[j]);
            }
            System.out.println();
        }
    }

    // Determinande de matriz 2x2
    public static double det2(double[][] m){
        return m[0][0]*m[1][1] - m[0][1]*m[1][0];
    }

    // Determinande de Matriz mxn
    public static double det(double[][] m) {
        // por adjuntos(fila 0)

        int grado = m.length;
        // si la matriz es 2x2 se calcula directamente
        if(grado==2) {
            return det2(m);
        }
        int det = 0;

        //
        for(int i = 0; i<m.length;i++) {
            // fila % 2 -> para alternar el signo
            int n =  (int)m[i][0] * (int)Math.pow(-1,(i%2));

            // matriz de adjunta de grado n-1
            double[][] _matrizTemp = new double[grado-1][grado-1]; // matriz adjunta de grado inferior
            int c = 0;
            for(int i2 = 0; i2<m.length;i2++) {
                if(i2!=i) { // no se copia la fila i
                    // coje las columnas despues de la columna 0
                     for(int j = 1; j<m.length;j++) {
                      _matrizTemp[c][j-1] = m[i2][j];
                     }
                    c++;
                }
            }
            // n * det de matriz de adjunta de grado n-1
            det += n*((_matrizTemp.length==2)?det2(_matrizTemp):det(_matrizTemp));	// calcula el determinante de grado inferior, o de grado 2
        }
        return det;

    }

    // Traspuesta de una matriz
    public static double[][] traspuesta(double[][]m1){
        double t[][] = new double[m1[0].length][m1.length];
        for(int i = 0;i<m1[0].length;i++) {
            for(int j = 0; j<m1.length;j++) {
                t[i][j] =m1[j][i];
            }
        }
        return t;
    }

    // Multiplicacion de matriz por un escalar
    public static double[][] MultiplicacionEscalar(double[][]m1, double num){
        double mul[][] = new double[m1.length][m1[0].length];
        // Multiplicacion de matriz por un escalar, multiplica al numero por cada elemento de la matriz.
        for(int i = 0;i<m1.length;i++) {
            for(int j = 0; j<m1[0].length;j++) {
                mul[i][j] =m1[i][j]*num;
            }
        }
        return mul;
    }

    // Suma de 2 matrices
    public static double[][] suma(double[][]m1, double[][] m2){
        double suma[][] = new double[m1.length][m1[0].length];
        // por cada fila
        for(int i = 0;i<m1.length;i++) {
            // por cada columna
            for(int j = 0; j<m1[0].length;j++) {
                suma[i][j] =m1[i][j]+m2[i][j];
            }
        }
        return suma;
    }

    // Resta de 2 matrices
    public static double[][] resta(double[][]m1, double[][] m2){
        return suma(m1, MultiplicacionEscalar(m2,-1));
    }

    // Multiplicacion de 2 matrices
    public static double[][] multiplicacion(double[][]m1, double[][] m2){
        //// m1: a × b      m1.length x m1[0].length
        //// m2: c x d      m2.length x m2[0].length
        //// b == c -> compatible
        //// res: a x d
        int a = m1.length;
        int b = m1[0].length;

        int c = m2.length;
        int d = m2[0].length;

        if (b != c) {
            return null;
        }

        double[][] fm = new double[a][d];

        for(int f = 0; f<a;f++) { // fila 0 - a (matriz 1)
            for(int col = 0; col<d;col++) { // columna 0 - d (matriz 2)
                int sum = 0;
                // multiplicar los elementos de la fila de la matriz 1 por los elementos de la columna de la matriz 2
                for(int i = 0; i<b;i++) {
                    sum+=m1[f][i]*m2[i][col];
                }
                // matriz resultante
                fm[f][col] = sum;
            }
        }
        return fm;
    }

    // Matriz adjunta
    public static double[][] adjunta(double[][]m1){
        int a = m1.length; // nfilas
        int b = m1[0].length; // ncolumnas
        double[][] adjunta = new double[a][b];
        for(int fil = 0; fil<a;fil++) { // fila 0-a
            for(int col = 0; col<b;col++) { //columna 0-d

                int ff = 0;
                // Matriz adjunta de grado inferior (menor complementario)
                double[][] mt = new double[a-1][b-1];
                for(int i = 0; i<a;i++) {
                    if(i!=fil) { // si no es la fila
                        int cc = 0;
                        for(int j = 0; j<b;j++) {
                            if(j!=col) { // si no es la columna
                                mt[ff][cc] = m1[i][j];
                                cc++;
                            }
                        }
                        ff++;
                    }
                }
                // determinante(mt) * (-1)^(fil+col) para ir alternando el signo
                adjunta[fil][col] = det(mt)*(int)Math.pow(-1,(fil+col));
            }
        }
        return adjunta;
    }

    // Inversa de una matriz
    public static double[][] inversa(double[][]m1){
        double[][]adj = adjunta(m1);
        double[][]tras = traspuesta(adj);
        double _det = det(m1);
        if(_det==0) {
            // System.out.println("No tiene inversa");
            return null;
        }
        return(MultiplicacionEscalar(tras, 1./_det));
    }

    // Guardar matriz en un archivo
    public static void saveMatrix(double[][]m1, int id){
        String fileName = "matriz"+id+".txt";
        try {
            PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
            writer.println(m1.length); // numero de filas
            writer.println(m1[0].length); // numero de columnas
            for (double[] row : m1) {
                for (int j = 0; j < m1[0].length; j++) {
                    writer.print(row[j] + "|");
                }
                writer.println();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar matriz de un archivo
    public static double[][] loadMatrix(int id){
        String fileName = "matriz"+id+".txt";
        double[][] m = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            int nfilas = Integer.parseInt(br.readLine()); // numero de filas
            int ncolumnas = Integer.parseInt(br.readLine()); // numero de columnas
            m = new double[nfilas][ncolumnas];
            for (int i = 0; i < nfilas; i++) {
                String[] line = br.readLine().split("\\|"); // split by |
                for (int j = 0; j < ncolumnas; j++) {
                    m[i][j] = Double.parseDouble(line[j]);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return m;
    }



}
