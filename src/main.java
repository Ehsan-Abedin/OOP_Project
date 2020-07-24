public class main {
    public static void main(String[] args) {
        if ((Capacitor.getAllCapacitors() == null) && (Inductance.getAllInductances() == null)) {
            int n = Node.getAllNodes().size();
            int m = VoltageSourceDC.getAllVoltageSourceDCs().size() + VoltageControlVoltageSource.getAllVoltageControlVoltageSources().size() + CurrentControlVoltageSource.getAllCurrentControlVoltageSources().size();
            double a[][] = new double[m+n+1][m+n+1];
            double g[][] = new double[n+1][n+1];
            double b[][] = new double[n+1][m+1];
            double c[][] = new double[m+1][n+1];
            double d[][] = new double[m+1][m+1];
            for (int i = 1; i < n+1; i++) {
                for (int j = 1; j < n+1; j++){
                    double sum = 0;
                    for (Resistance allResistance : Resistance.getAllResistances()) {
                        if (i == j)
                            if ((allResistance.getNode1() == i) || (allResistance.getNode2() == i))
                                sum += 1 / allResistance.getResistance();
                        else
                            if (((allResistance.getNode1() == i) && (allResistance.getNode2()) == j) || ((allResistance.getNode1() == j) && (allResistance.getNode2()) == i))
                                sum -= 1 / allResistance.getResistance();
                    }
                    g[i][j] = sum;
                }
            }
            for (int i = 1; i < n+1; i++){
                for (int j = 1; j < m+1; j++){
                    for (VoltageSourceDC allVoltageSourceDc : VoltageSourceDC.getAllVoltageSourceDCs()) {
                        if (allVoltageSourceDc.getNode1() == i) {
                            b[i][j] = 1;
                            c[j][i] = 1;
                        }
                        else if (allVoltageSourceDc.getNode2() == i) {
                            b[i][j] = -1;
                            c[j][i] = -1;
                        }
                        else {
                            b[i][j] = 0;
                            c[j][i] = 0;
                        }
                    }
                    for (VoltageControlVoltageSource allVoltageControlVoltageSource : VoltageControlVoltageSource.getAllVoltageControlVoltageSources()) {
                        if (allVoltageControlVoltageSource.getNode1() == i) {
                            b[i][j] = 1;
                            if (allVoltageControlVoltageSource.getControlNode1() == i)
                                c[j][i] = -allVoltageControlVoltageSource.getGain() + 1;
                            else if (allVoltageControlVoltageSource.getControlNode2() == i)
                                c[j][i] = allVoltageControlVoltageSource.getGain() + 1;
                            else
                                c[j][i] = 1;

                        }
                        else if (allVoltageControlVoltageSource.getNode2() == i) {
                            b[i][j] = -1;
                            if (allVoltageControlVoltageSource.getControlNode1() == i)
                                c[j][i] = -allVoltageControlVoltageSource.getGain() - 1;
                            else if (allVoltageControlVoltageSource.getControlNode2() == i)
                                c[j][i] = allVoltageControlVoltageSource.getGain() - 1;
                            else
                                c[j][i] = -1;
                        }
                        else {
                            b[i][j] = 0;
                            if (allVoltageControlVoltageSource.getControlNode1() == i)
                                c[j][i] = -allVoltageControlVoltageSource.getGain();
                            else if (allVoltageControlVoltageSource.getControlNode2() == i)
                                c[j][i] = allVoltageControlVoltageSource.getGain();
                            else
                                c[j][i] = 0;
                        }
                    }
                }
            }

        }
    }
}
