public class Main {
    public static void main(String[] args) {
        if ((Capacitor.getAllCapacitors() == null) && (Inductance.getAllInductances() == null)) {
            int n = Node.getAllNodes().size();
            int m = VoltageSourceDC.getAllVoltageSourceDCs().size() + VoltageControlVoltageSource.getAllVoltageControlVoltageSources().size() + CurrentControlVoltageSource.getAllCurrentControlVoltageSources().size();
            double a[][] = new double[m + n + 1][m + n + 1];
            double g[][] = new double[n + 1][n + 1];
            double b[][] = new double[n + 1][m + 1];
            double c[][] = new double[m + 1][n + 1];
            double d[][] = new double[m + 1][m + 1];
            double x[][] = new double[m + n + 1][2];
            double v[][] = new double[n + 1][2];
            double j[][] = new double[m + 1][2];
            double z[][] = new double[m + n + 1][2];
            double i[][] = new double[n + 1][2];
            double e[][] = new double[m + 1][2];
            for (int p = 1; p < n + 1; p++) {
                for (int q = 1; q < n + 1; q++) {
                    double sum = 0;
                    for (Resistance allResistance : Resistance.getAllResistances()) {
                        if (p == q)
                            if ((allResistance.getNode1() == p) || (allResistance.getNode2() == p))
                                sum += 1 / allResistance.getResistance();
                        else if (((allResistance.getNode1() == p) && (allResistance.getNode2()) == q) || ((allResistance.getNode1() == q) && (allResistance.getNode2()) == p))
                            sum -= 1 / allResistance.getResistance();
                    }
                    for (VoltageControlCurrentSource allVoltageControlCurrentSource : VoltageControlCurrentSource.getAllVoltageControlCurrentSources()) {
                        if (allVoltageControlCurrentSource.getNode1() == p) {
                            if (allVoltageControlCurrentSource.getControlNode1() == q)
                                sum += allVoltageControlCurrentSource.getGain();
                            else if (allVoltageControlCurrentSource.getControlNode2() == q)
                                sum -= allVoltageControlCurrentSource.getGain();
                        }
                        else if (allVoltageControlCurrentSource.getNode2() == p) {
                            if (allVoltageControlCurrentSource.getControlNode1() == q)
                                sum -= allVoltageControlCurrentSource.getGain();
                            else if (allVoltageControlCurrentSource.getControlNode2() == q)
                                sum += allVoltageControlCurrentSource.getGain();
                        }
                    }
                    g[p][q] = sum;
                }
            }
            for (int p = 1; p < n + 1; p++) {
                for (int q = 1; q < m + 1; q++) {
                    for (VoltageSourceDC allVoltageSourceDc : VoltageSourceDC.getAllVoltageSourceDCs()) {
                        if (CurrentControlCurrentSource.getAllCurrentControlCurrentSources() != null) {
                            for (CurrentControlCurrentSource allCurrentControlCurrentSource : CurrentControlCurrentSource.getAllCurrentControlCurrentSources()) {
                                if (allVoltageSourceDc.getNode1() == p) {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        b[p][q] = 1 + allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 1;
                                    }
                                    else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        b[p][q] = 1 - allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 1;
                                    }
                                    else {
                                        b[p][q] = 1;
                                        c[q][p] = 1;
                                    }
                                }
                                else if (allVoltageSourceDc.getNode2() == p) {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        b[p][q] = -1 + allCurrentControlCurrentSource.getGain();
                                        c[q][p] = -1;
                                    }
                                    else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        b[p][q] = -1 - allCurrentControlCurrentSource.getGain();
                                        c[q][p] = -1;
                                    }
                                    else {
                                        b[p][q] = -1;
                                        c[q][p] = -1;
                                    }
                                }
                                else {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        b[p][q] =  allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 0;
                                    }
                                    else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        b[p][q] = - allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 0;
                                    }
                                    else {
                                        b[p][q] = 0;
                                        c[q][p] = 0;
                                    }
                                }
                            }
                        }
                    }
                    for (VoltageControlVoltageSource allVoltageControlVoltageSource : VoltageControlVoltageSource.getAllVoltageControlVoltageSources()) {
                        if (allVoltageControlVoltageSource.getNode1() == p) {
                            b[p][q] = 1;
                            if (allVoltageControlVoltageSource.getControlNode1() == p)
                                c[q][p] = -allVoltageControlVoltageSource.getGain() + 1;
                            else if (allVoltageControlVoltageSource.getControlNode2() == p)
                                c[q][p] = allVoltageControlVoltageSource.getGain() + 1;
                            else
                                c[q][p] = 1;

                        } else if (allVoltageControlVoltageSource.getNode2() == p) {
                            b[p][q] = -1;
                            if (allVoltageControlVoltageSource.getControlNode1() == p)
                                c[q][p] = -allVoltageControlVoltageSource.getGain() - 1;
                            else if (allVoltageControlVoltageSource.getControlNode2() == p)
                                c[q][p] = allVoltageControlVoltageSource.getGain() - 1;
                            else
                                c[q][p] = -1;
                        } else {
                            b[p][q] = 0;
                            if (allVoltageControlVoltageSource.getControlNode1() == p)
                                c[q][p] = -allVoltageControlVoltageSource.getGain();
                            else if (allVoltageControlVoltageSource.getControlNode2() == p)
                                c[q][p] = allVoltageControlVoltageSource.getGain();
                            else
                                c[q][p] = 0;
                        }
                    }
                    for (CurrentControlVoltageSource allCurrentControlVoltageSource : CurrentControlVoltageSource.getAllCurrentControlVoltageSources()) {
                        if (allCurrentControlVoltageSource.getNode1() == p) {
                            b[p][q] = 1;
                            c[q][p] = 1;
                        }
                        else if (allCurrentControlVoltageSource.getNode2() == p) {
                            b[p][q] = -1;
                            c[q][p] = -1;
                        }
                        else {
                            b[p][q] = 0;
                            c[q][p] = 0;
                        }
                    }
                }
            }
            /*if (CurrentControlVoltageSource.getAllCurrentControlVoltageSources() != null) {

            }*/
            for (int p = 1; p < n+1; p++)
                for (int q = 1; q < n+1; q++)
                    a[p][q] = g[p][q];
            for (int p = 1; p < n+1; p++)
                for (int q = 1; q < m+1; q++)
                    a[p][n+q] = b[p][q];
            for (int p = 1; p < m+1; p++)
                for (int q = 1; q < n+1; q++)
                    a[n+p][q] = c[p][q];
            for (int p = 1; p < m+1; p++)
                for (int q = 1; q < m+1; q++)
                    a[n+p][n+q] = d[p][q];
            for (CurrentSourceDC allCurrentSourceDC : CurrentSourceDC.getAllCurrentSourceDCs()) {
                for (int p = 1; p < n+1; p++) {
                    if (allCurrentSourceDC.getNode1() == p)
                        i[p][1] = -allCurrentSourceDC.getCurrent();
                    else if (allCurrentSourceDC.getNode2() == p)
                        i[p][1] = allCurrentSourceDC.getCurrent();
                }
            }
            for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs())
                for (int p = 1; p < m+1; p++)
                    if (Integer.parseInt(allVoltageSourceDC.getName().substring(1)) == p)
                        e[p][1] = allVoltageSourceDC.getVoltage();
            for (int p = 1; p < n+1; p++)
                b[p][1] = i[p][1];
            for (int p = 1; p < m+1; p++)
                b[n+p][1] = e[p][1];
            double invert_a[][] = Functions.invert(a);
            for (int p = 1; p < m+n+1; p++)
                for (int q = 1; q < 2; q++)
                    for (int r = 1; r < m+n+1; r++)
                        x[p][q] += invert_a[p][r] * z[r][q];
        }
    }
}

