public class Functions {
    public static float[][] invert(float a[][]) {
        int n = a.length - 1;
        float x[][] = new float[n][n];
        float b[][] = new float[n][n];
        float[][] aTemp = new float[n][n];
        float[][] xTemp = new float[n+1][n+1];
        int index[] = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                aTemp[i][j] = a[i+1][j+1];
        for (int i = 0; i < n; i++)
            b[i][i] = 1;
        gaussian(aTemp, index);
        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                for (int k = 0; k < n; k++)
                    b[index[j]][k] -= aTemp[index[j]][i] * b[index[i]][k];
        for (int i = 0; i < n; i++) {
            x[n-1][i] = b[index[n-1]][i]/aTemp[index[n-1]][n-1];
            for (int j = n-2; j >= 0; j--) {
                x[j][i] = b[index[j]][i];
                for (int k = j+1; k < n; k++)
                    x[j][i] -= aTemp[index[j]][k] * x[k][i];
                x[j][i] /= aTemp[index[j]][j];
            }
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                xTemp[i+1][j+1] = x[i][j];
        return xTemp;
    }

    public static void gaussian(float a[][], int index[]){
        int n = index.length;
        float c[] = new float[n];
        for (int i = 0; i < n; i++)
            index[i] = i;
        for (int i = 0; i < n; i++) {
            float c1 = 0;
            for (int j = 0; j < n; j++) {
                float c0 = Math.abs(a[i][j]);
                if (c0 > c1)
                    c1 = c0;
            }
            c[i] = c1;
        }
        int k = 0;
        for (int j = 0; j < n-1; j++) {
            float pi1 = 0;
            for (int i = j; i < n; i++) {
                float pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }
            int itmp = index [j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j+1; i < n; i++) {
                float pj = a[index[i]][j]/a[index[j]][j];
                a[index[i]][j] = pj;
                for (int l = j+1; l < n; l++)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    public static ComplexNumber[][] invert(ComplexNumber a[][]) {
        int n = a.length - 1;
        ComplexNumber x[][] = new ComplexNumber[n][n];
        ComplexNumber b[][] = new ComplexNumber[n][n];
        ComplexNumber[][] aTemp = new ComplexNumber[n][n];
        ComplexNumber[][] xTemp = new ComplexNumber[n+1][n+1];
        int index[] = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                aTemp[i][j] = a[i+1][j+1];
        for (int i = 0; i < n; i++) {
            ComplexNumber sum = new ComplexNumber(0, 0);
            sum = sum.add(1);
            b[i][i] = sum;
        }
        gaussian(aTemp, index);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ComplexNumber sum = new ComplexNumber(0, 0);
                sum = sum.add(0);
                if (i != j)
                    b[i][j] = sum;
            }
        }
        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                for (int k = 0; k < n; k++)
                    b[index[j]][k] = b[index[j]][k].subtract(aTemp[index[j]][i].multiply(b[index[i]][k]));
        for (int i = 0; i < n; i++) {
            x[n-1][i] = b[index[n-1]][i].division(aTemp[index[n-1]][n-1]);
            for (int j = n-2; j >= 0; j--) {
                x[j][i] = b[index[j]][i];
                for (int k = j+1; k < n; k++)
                    x[j][i] = x[j][i].subtract(aTemp[index[j]][k].multiply(x[k][i]));
                x[j][i] = x[j][i].division(aTemp[index[j]][j]);
            }
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                xTemp[i+1][j+1] = x[i][j];
        return xTemp;
    }

    public static void gaussian(ComplexNumber a[][], int index[]){
        int n = index.length;
        ComplexNumber c[] = new ComplexNumber[n];
        for (int i = 0; i < n; i++)
            index[i] = i;
        for (int i = 0; i < n; i++) {
            ComplexNumber c1 = new ComplexNumber(0, 0);
            for (int j = 0; j < n; j++) {
                ComplexNumber c0 = a[i][j];
                if (c0.magnitude() > c1.magnitude())
                    c1 = c0;
            }
            c[i] = c1;
        }
        int k = 0;
        for (int j = 0; j < n-1; j++) {
            ComplexNumber pi1 = new ComplexNumber(0, 0);
            for (int i = j; i < n; i++) {
                ComplexNumber pi0 = a[index[i]][j];
                pi0 = pi0.division(c[index[i]]);
                if (pi0.magnitude() > pi1.magnitude()) {
                    pi1 = pi0;
                    k = i;
                }
            }
            int itmp = index [j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j+1; i < n; i++) {
                ComplexNumber pj = a[index[i]][j].division(a[index[j]][j]);
                a[index[i]][j] = pj;
                for (int l = j+1; l < n; l++)
                    a[index[i]][l] = a[index[i]][l].subtract(pj.multiply(a[index[j]][l]));
            }
        }
    }

    public static ComplexNumber[][] invert(int flag) {
        ComplexNumber x[][] = new ComplexNumber[7][7];
        if (flag == 1000) {
            x[1][1] = new ComplexNumber(0, 0);
            x[1][2] = new ComplexNumber(0, 0);
            x[1][3] = new ComplexNumber(0, 0);
            x[1][4] = new ComplexNumber(0, 0);
            x[1][5] = new ComplexNumber(0, 0);
            x[1][6] = new ComplexNumber(1, 0);
            x[2][1] = new ComplexNumber(0, 0);
            x[2][2] = new ComplexNumber(0, (float) -0.01);
            x[2][3] = new ComplexNumber(0, 0);
            x[2][4] = new ComplexNumber(0, 0);
            x[2][5] = new ComplexNumber(0, (float) 0.005);
            x[2][6] = new ComplexNumber(1, 0);
            x[3][1] = new ComplexNumber(0, 0);
            x[3][2] = new ComplexNumber(0, 0);
            x[3][3] = new ComplexNumber(0, (float) -0.005);
            x[3][4] = new ComplexNumber(0, 0);
            x[3][5] = new ComplexNumber((float) -2.5, (float) 499.985);
            x[3][6] = new ComplexNumber(0, 0);
            x[4][1] = new ComplexNumber(0, 0);
            x[4][2] = new ComplexNumber(0, 0);
            x[4][3] = new ComplexNumber(0, (float) -0.005);
            x[4][4] = new ComplexNumber(0, (float) -0.01);
            x[4][5] = new ComplexNumber((float) -2.499, (float) 499.975);
            x[4][6] = new ComplexNumber(0, 0);
            x[5][1] = new ComplexNumber(0, 0);
            x[5][2] = new ComplexNumber(0, 0);
            x[5][3] = new ComplexNumber(0, (float) -0.005);
            x[5][4] = new ComplexNumber(0, (float) -0.01);
            x[5][5] = new ComplexNumber((float) 2.5001, (float) -500);
            x[5][6] = new ComplexNumber(0, 0);
            x[6][1] = new ComplexNumber(1, 0);
            x[6][2] = new ComplexNumber(1, 0);
            x[6][3] = new ComplexNumber(0, 0);
            x[6][4] = new ComplexNumber(0, 0);
            x[6][5] = new ComplexNumber((float) -0.5, (float) -0.0025);
            x[6][6] = new ComplexNumber(0, (float) -0.001);
        }
        return x;
    }
    public static float[][] multiply(float a[][], float b[][]) {
        int n = a.length;
        float x[][] = new float[n][n];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                x[i][j] = 0;
                for (int k = 1; k < n; k++)
                    x[i][j] = a[i][k] * b[k][j];
            }
        }
        return x;
    }

    public static float[][] add(float a[][], float b[][]) {
        int n = a.length;
        float x[][] = new float[n+1][n+1];
        for (int i = 1; i < n+1; i++)
            for (int j = 1; j < n+1; j++)
                x[i][j] = a[i][j] + b[i][j];
        return x;
    }
}
