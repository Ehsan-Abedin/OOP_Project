public class Functions {
    public static double[][] invert(double a[][]) {
        int n = a.length;
        double x[][] = new double[n+1][n+1];
        double b[][] = new double[n+1][n+1];
        int index[] = new int[n+1];
        for (int i = 1; i < n+1; i++)
            b[i][i] = 1;
        gaussian(a, index);
        for (int i = 1; i < n; i++)
            for (int j = i+1; j < n+1; j++)
                for (int k = 1; k < n+1; k++)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];
        for (int i = 1; i < n+1; i++) {
            x[n][i] = b[index[n]][i]/a[index[n]][n];
            for (int j = n-1; j >= 1; j--) {
                x[j][i] -= a[index[j]][i];
                for (int k = j+1; k < n+1; k++)
                    x[j][i] -= a[index[j]][k] * x[k][i];
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    public static void gaussian(double a[][], int index[]){
        int n = index.length;
        double c[] = new double[n];
        for (int i = 1; i < n+1; i++)
            index[i] = i;
        for (int i = 1; i < n+1; i++) {
            double c1 = 0;
            for (int j = 1; j < n+1; j++) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1)
                    c1 = c0;
            }
            c[i] = c1;
        }
        int k = 0;
        for (int j = 1; j < n; j++) {
            double pi1 = 0;
            for (int i = j; i < n+1; i++) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }
            int itmp = index [j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j+1; i < n+1; i++) {
                double pj = a[index[i]][j]/a[index[j]][j];
                a[index[i]][j] = pj;
                for (int l = j+1; l < n+1; l++)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }
}