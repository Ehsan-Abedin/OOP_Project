import javafx.stage.Stage;

import java.io.FileNotFoundException;
public class Main {
    public static float deltaV = 0;
    public static float deltaI = 0;
    public static float deltaT = 0;
    public static float simulationTime = 0;
    public static void setData(float V, float I, float T, float Time) {
        deltaV = V;
        deltaI = I;
        deltaT = T;
        simulationTime = Time;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Data.getInput();
        for (CurrentSourceDC allCurrentSourceDC : CurrentSourceDC.getAllCurrentSourceDCs()) {
            for (CurrentSourceDC currentSourceDC : CurrentSourceDC.getAllCurrentSourceDCs()) {
                if ((allCurrentSourceDC.getNode1() == currentSourceDC.getNode2()) || (allCurrentSourceDC.getNode2() == currentSourceDC.getNode1())) {
                    if (allCurrentSourceDC.getCurrent() != currentSourceDC.getCurrent()) {
                        Data.setOutput(-2);
                        return;
                    }
                }
            }
        }
        for (CurrentSourceAC allCurrentSourceAC : CurrentSourceAC.getAllCurrentSourceACs()) {
            for (CurrentSourceAC currentSourceAC : CurrentSourceAC.getAllCurrentSourceACs()) {
                if ((allCurrentSourceAC.getNode1() == currentSourceAC.getNode2()) || (allCurrentSourceAC.getNode2() == currentSourceAC.getNode1())) {
                    if (allCurrentSourceAC.getCurrent() != currentSourceAC.getCurrent()) {
                        Data.setOutput(-2);
                        return;
                    }
                }
            }
        }

        for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs()) {
            for (VoltageSourceDC voltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs()) {
                if (((allVoltageSourceDC.getNode1() == voltageSourceDC.getNode1()) && (allVoltageSourceDC.getNode2() == voltageSourceDC.getNode2())) || ((allVoltageSourceDC.getNode2() == voltageSourceDC.getNode1()) && (allVoltageSourceDC.getNode1() == voltageSourceDC.getNode2()))) {
                    if (allVoltageSourceDC.getVoltage() != voltageSourceDC.getVoltage()) {
                        Data.setOutput(-3);
                        return;
                    }
                }
            }
        }
        for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs()) {
            for (VoltageSourceAC voltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs()) {
                if (((allVoltageSourceAC.getNode1() == voltageSourceAC.getNode1()) && (allVoltageSourceAC.getNode2() == voltageSourceAC.getNode2())) || ((allVoltageSourceAC.getNode2() == voltageSourceAC.getNode1()) && (allVoltageSourceAC.getNode1() == voltageSourceAC.getNode2()))) {
                    if (allVoltageSourceAC.getVoltage() != voltageSourceAC.getVoltage()) {
                        Data.setOutput(-3);
                        return;
                    }
                }
            }
        }

        int flag = 0;
        for (Node allNode : Node.getAllNodes()) {
            if (allNode.getNode() == 0)
                flag = 1;
            if ((allNode.getNode() == 0) && ((allNode.getNodeVoltage().real() != 0) || (allNode.getNodeVoltage().imaginary() != 0))) {
                Data.setOutput(-4);
                return;
            }
        }
        if (flag != 1) {
            Data.setOutput(-4);
            return;
        }

        for (Element allElement : Element.getAllElements()) {
            System.out.println(allElement);
        }
        for (Node allNode : Node.getAllNodes()) {
            System.out.println(allNode);
        }
        if ((Capacitor.getAllCapacitors().size() == 0) && (Inductance.getAllInductances().size() == 0)) {
            for (float t = 0; t <= simulationTime; t += deltaT) {
                int n = Node.getAllNodes().size() - 1;
                int m = VoltageSourceDC.getAllVoltageSourceDCs().size() + VoltageSourceAC.getAllVoltageSourceACs().size() + VoltageControlVoltageSource.getAllVoltageControlVoltageSources().size() + CurrentControlVoltageSource.getAllCurrentControlVoltageSources().size();
                float a[][] = new float[m + n + 1][m + n + 1];
                float g[][] = new float[n + 1][n + 1];
                float b[][] = new float[n + 1][m + 1];
                float c[][] = new float[m + 1][n + 1];
                float d[][] = new float[m + 1][m + 1];
                float x[][] = new float[m + n + 1][2];
                float z[][] = new float[m + n + 1][2];
                float i[][] = new float[n + 1][2];
                float e[][] = new float[m + 1][2];
                for (int p = 1; p < n + 1; p++) {
                    for (int q = 1; q < n + 1; q++) {
                        float sum = 0;
                        for (Resistance allResistance : Resistance.getAllResistances()) {
                            if (p == q) {
                                if ((allResistance.getNode1() == p) || (allResistance.getNode2() == p))
                                    sum += 1 / allResistance.getResistance();
                            } else {
                                if (((allResistance.getNode1() == p) && (allResistance.getNode2()) == q) || ((allResistance.getNode1() == q) && (allResistance.getNode2()) == p))
                                    sum -= 1 / allResistance.getResistance();
                            }
                        }
                        for (VoltageControlCurrentSource allVoltageControlCurrentSource : VoltageControlCurrentSource.getAllVoltageControlCurrentSources()) {
                            if (allVoltageControlCurrentSource.getNode1() == p) {
                                if (allVoltageControlCurrentSource.getControlNode1() == q)
                                    sum += allVoltageControlCurrentSource.getGain();
                                else if (allVoltageControlCurrentSource.getControlNode2() == q)
                                    sum -= allVoltageControlCurrentSource.getGain();
                            } else if (allVoltageControlCurrentSource.getNode2() == p) {
                                if (allVoltageControlCurrentSource.getControlNode1() == q)
                                    sum -= allVoltageControlCurrentSource.getGain();
                                else if (allVoltageControlCurrentSource.getControlNode2() == q)
                                    sum += allVoltageControlCurrentSource.getGain();
                            }
                        }
                        g[p][q] = sum;
                    }
                }
                for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs()) {
                    int q = 1;
                    for (int p = 1; p < n + 1; p++) {
                        if (CurrentControlCurrentSource.getAllCurrentControlCurrentSources().size() != 0) {
                            for (CurrentControlCurrentSource allCurrentControlCurrentSource : CurrentControlCurrentSource.getAllCurrentControlCurrentSources()) {
                                if (allVoltageSourceDC.getNode1() == p) {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        b[p][q] = 1 + allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 1;
                                    } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        b[p][q] = 1 - allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 1;
                                    } else {
                                        b[p][q] = 1;
                                        c[q][p] = 1;
                                    }
                                } else if (allVoltageSourceDC.getNode2() == p) {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        b[p][q] = -1 + allCurrentControlCurrentSource.getGain();
                                        c[q][p] = -1;
                                    } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        b[p][q] = -1 - allCurrentControlCurrentSource.getGain();
                                        c[q][p] = -1;
                                    } else {
                                        b[p][q] = -1;
                                        c[q][p] = -1;
                                    }
                                } else {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        b[p][q] = allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 0;
                                    } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        b[p][q] = -allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 0;
                                    }
                                }
                            }
                        }
                        else {
                            if (allVoltageSourceDC.getNode1() == p) {
                                b[p][q] = 1;
                                c[q][p] = 1;
                            }
                            else if (allVoltageSourceDC.getNode2() == p) {
                                b[p][q] = -1;
                                c[q][p] = -1;
                            }
                        }
                    }
                    q++;
                }
                for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs()) {
                    int q = 1 + VoltageSourceDC.getAllVoltageSourceDCs().size();
                    for (int p = 1; p < n+1; p++) {
                        if (CurrentControlCurrentSource.getAllCurrentControlCurrentSources().size() != 0) {
                            for (CurrentControlCurrentSource allCurrentControlCurrentSource : CurrentControlCurrentSource.getAllCurrentControlCurrentSources()) {
                                if (allVoltageSourceAC.getNode1() == p) {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        b[p][q] = 1 + allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 1;
                                    } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        b[p][q] = 1 - allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 1;
                                    } else {
                                        b[p][q] = 1;
                                        c[q][p] = 1;
                                    }
                                } else if (allVoltageSourceAC.getNode2() == p) {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        b[p][q] = -1 + allCurrentControlCurrentSource.getGain();
                                        c[q][p] = -1;
                                    } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        b[p][q] = -1 - allCurrentControlCurrentSource.getGain();
                                        c[q][p] = -1;
                                    } else {
                                        b[p][q] = -1;
                                        c[q][p] = -1;
                                    }
                                } else {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        b[p][q] = allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 0;
                                    } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        b[p][q] = -allCurrentControlCurrentSource.getGain();
                                        c[q][p] = 0;
                                    }
                                }
                            }
                        }
                        else {
                            if (allVoltageSourceAC.getNode1() == p) {
                                b[p][q] = 1;
                                c[q][p] = 1;
                            } else if (allVoltageSourceAC.getNode2() == p) {
                                b[p][q] = -1;
                                c[q][p] = -1;
                            }
                        }
                    }
                    q++;
                }
                for (VoltageControlVoltageSource allVoltageControlVoltageSource : VoltageControlVoltageSource.getAllVoltageControlVoltageSources()) {
                    int q = 1 + VoltageSourceDC.getAllVoltageSourceDCs().size() + VoltageSourceAC.getAllVoltageSourceACs().size();
                    for (int p = 1; p < n+1; p++) {
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
                        }
                    }
                    q++;
                }
                for (CurrentControlVoltageSource allCurrentControlVoltageSource : CurrentControlVoltageSource.getAllCurrentControlVoltageSources()) {
                    int q = 1 + VoltageSourceDC.getAllVoltageSourceDCs().size() + VoltageSourceAC.getAllVoltageSourceACs().size() + VoltageControlVoltageSource.getAllVoltageControlVoltageSources().size();
                    for (int p = 1; p < n+1; p++) {
                        if (allCurrentControlVoltageSource.getNode1() == p) {
                            b[p][q] = 1;
                            c[q][p] = 1;
                        } else if (allCurrentControlVoltageSource.getNode2() == p) {
                            b[p][q] = -1;
                            c[q][p] = -1;
                        }
                    }
                    q++;
                }
                if (CurrentControlVoltageSource.getAllCurrentControlVoltageSources().size() != 0) {
                    for (CurrentControlVoltageSource allCurrentControlVoltageSource : CurrentControlVoltageSource.getAllCurrentControlVoltageSources())
                        d[m][m] = -allCurrentControlVoltageSource.getGain();
                }
                for (int p = 1; p < n + 1; p++)
                    for (int q = 1; q < n + 1; q++)
                        a[p][q] = g[p][q];
                for (int p = 1; p < n + 1; p++)
                    for (int q = 1; q < m + 1; q++)
                        a[p][n + q] = b[p][q];
                for (int p = 1; p < m + 1; p++)
                    for (int q = 1; q < n + 1; q++)
                        a[n + p][q] = c[p][q];
                for (int p = 1; p < m + 1; p++)
                    for (int q = 1; q < m + 1; q++)
                        a[n + p][n + q] = d[p][q];
                for (int p = 1; p < n + 1; p++) {
                    for (CurrentSourceDC allCurrentSourceDC : CurrentSourceDC.getAllCurrentSourceDCs()) {
                        if (allCurrentSourceDC.getNode1() == p)
                            i[p][1] = -allCurrentSourceDC.getCurrent().real();
                        else if (allCurrentSourceDC.getNode2() == p)
                            i[p][1] = allCurrentSourceDC.getCurrent().real();
                    }
                    for (CurrentSourceAC allCurrentSourceAC : CurrentSourceAC.getAllCurrentSourceACs()) {
                        if (allCurrentSourceAC.getNode1() == p)
                            i[p][1] = -allCurrentSourceAC.getAmplitude() * (float) Math.sin(2 * Math.PI * allCurrentSourceAC.getFrequency() * t + allCurrentSourceAC.getPhase());
                        else if (allCurrentSourceAC.getNode2() == p)
                            i[p][1] = allCurrentSourceAC.getAmplitude() * (float) Math.sin(2 * Math.PI * allCurrentSourceAC.getFrequency() * t + allCurrentSourceAC.getPhase());
                    }
                }
                for (int p = 1; p < m + 1; p++) {
                    for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs())
                        if (Integer.parseInt(allVoltageSourceDC.getName().substring(1)) == p)
                            e[p][1] = allVoltageSourceDC.getVoltage().real();
                    for (VoltageSourceAC allvoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                        if (Integer.parseInt(allvoltageSourceAC.getName().substring(1)) == p)
                            e[p][1] = allvoltageSourceAC.getAmplitude() * (float) Math.sin(2 * Math.PI * allvoltageSourceAC.getFrequency() * t + allvoltageSourceAC.getPhase());
                }
                for (int p = 1; p < n + 1; p++)
                    z[p][1] = i[p][1];
                for (int p = 1; p < m + 1; p++)
                    z[n + p][1] = e[p][1];
                float invert_a[][] = Functions.invert(a);
                for (int p = 1; p < m + n + 1; p++)
                    for (int q = 1; q < 2; q++)
                        for (int r = 1; r < m + n + 1; r++)
                            x[p][q] += invert_a[p][r] * z[r][q];
                for (Node allNode : Node.getAllNodes())
                    for (int p = 1; p < n + 1; p++)
                        if (allNode.getNode() == p)
                            allNode.setNodeVoltage(new ComplexNumber(x[p][1], 0));
                if ((VoltageControlVoltageSource.getAllVoltageControlVoltageSources().size() == 0) && (CurrentControlVoltageSource.getAllCurrentControlVoltageSources().size() == 0)) {
                    for (int p = n + 1; p < m + n + 1; p++) {
                        for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs())
                            allVoltageSourceDC.setCurrent(new ComplexNumber(x[p][1], 0));
                        for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                            if (Integer.parseInt((allVoltageSourceAC.getName().substring(1))) == p)
                                allVoltageSourceAC.setCurrent(new ComplexNumber(x[p][1], 0));
                    }
                }
                else {
                    for (int p = n + 1; p < m + n; p++) {
                        for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs())
                            allVoltageSourceDC.setCurrent(new ComplexNumber(x[p][1], 0));
                        for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                            if (Integer.parseInt((allVoltageSourceAC.getName().substring(1))) == p)
                                allVoltageSourceAC.setCurrent(new ComplexNumber(x[p][1], 0));
                    }
                    for (VoltageControlVoltageSource allVoltageControlVoltageSource : VoltageControlVoltageSource.getAllVoltageControlVoltageSources())
                        allVoltageControlVoltageSource.setCurrent(new ComplexNumber(x[m + n][1], 0));
                    for (CurrentControlVoltageSource allCurrentControlVoltageSource : CurrentControlVoltageSource.getAllCurrentControlVoltageSources())
                        allCurrentControlVoltageSource.setCurrent(new ComplexNumber(x[m + n][1], 0));
                }
                for (Resistance allResistance : Resistance.getAllResistances()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allResistance.getNode1() == allNode.getNode())
                            allResistance.setNode1Voltage(allNode.getNodeVoltage());
                        if (allResistance.getNode2() == allNode.getNode())
                            allResistance.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allResistance.setVoltage(allResistance.voltage(allResistance.getNode1Voltage(), allResistance.getNode2Voltage()));
                    allResistance.setCurrent(allResistance.current(allResistance.voltage));
                    allResistance.setPower(allResistance.power(allResistance.voltage, allResistance.current));
                }
                for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs()) {
                    allVoltageSourceDC.setVoltage(allVoltageSourceDC.voltage);
                    allVoltageSourceDC.setPower(allVoltageSourceDC.power(allVoltageSourceDC.current, allVoltageSourceDC.voltage));
                }
                for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs()) {
                    allVoltageSourceAC.setVoltage(allVoltageSourceAC.voltage);
                    allVoltageSourceAC.setPower(allVoltageSourceAC.power(allVoltageSourceAC.current, allVoltageSourceAC.voltage));
                }
                for (CurrentSourceDC allCurrentSourceDC : CurrentSourceDC.getAllCurrentSourceDCs()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allCurrentSourceDC.getNode1() == allNode.getNode())
                            allCurrentSourceDC.setNode1Voltage(allNode.getNodeVoltage());
                        if (allCurrentSourceDC.getNode2() == allNode.getNode())
                            allCurrentSourceDC.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allCurrentSourceDC.setVoltage(allCurrentSourceDC.voltage(allCurrentSourceDC.getNode1Voltage(), allCurrentSourceDC.getNode2Voltage()));
                    allCurrentSourceDC.setPower(allCurrentSourceDC.power(allCurrentSourceDC.current, allCurrentSourceDC.voltage));
                }
                for (CurrentSourceAC allCurrentSourceAC : CurrentSourceAC.getAllCurrentSourceACs()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allCurrentSourceAC.getNode1() == allNode.getNode())
                            allCurrentSourceAC.setNode1Voltage(allNode.getNodeVoltage());
                        if (allCurrentSourceAC.getNode2() == allNode.getNode())
                            allCurrentSourceAC.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allCurrentSourceAC.setVoltage(allCurrentSourceAC.voltage(allCurrentSourceAC.getNode1Voltage(), allCurrentSourceAC.getNode2Voltage()));
                    allCurrentSourceAC.setPower(allCurrentSourceAC.power(allCurrentSourceAC.current, allCurrentSourceAC.voltage));
                }
                for (VoltageControlVoltageSource allVoltageControlVoltageSource : VoltageControlVoltageSource.getAllVoltageControlVoltageSources()) {
                    allVoltageControlVoltageSource.setVoltage(allVoltageControlVoltageSource.voltage);
                    allVoltageControlVoltageSource.setPower(allVoltageControlVoltageSource.power(allVoltageControlVoltageSource.current, allVoltageControlVoltageSource.voltage));
                }
                for (VoltageControlCurrentSource allVoltageControlCurrentSource : VoltageControlCurrentSource.getAllVoltageControlCurrentSources()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allVoltageControlCurrentSource.getNode1() == allNode.getNode())
                            allVoltageControlCurrentSource.setNode1Voltage(allNode.getNodeVoltage());
                        if (allVoltageControlCurrentSource.getNode2() == allNode.getNode())
                            allVoltageControlCurrentSource.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allVoltageControlCurrentSource.setVoltage(allVoltageControlCurrentSource.voltage(allVoltageControlCurrentSource.getNode1Voltage(), allVoltageControlCurrentSource.getNode2Voltage()));
                    allVoltageControlCurrentSource.setPower(allVoltageControlCurrentSource.power(allVoltageControlCurrentSource.current, allVoltageControlCurrentSource.voltage));
                }
                for (CurrentControlCurrentSource allCurrentControlCurrentSource : CurrentControlCurrentSource.getAllCurrentControlCurrentSources()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allCurrentControlCurrentSource.getNode1() == allNode.getNode())
                            allCurrentControlCurrentSource.setNode1Voltage(allNode.getNodeVoltage());
                        if (allCurrentControlCurrentSource.getNode2() == allNode.getNode())
                            allCurrentControlCurrentSource.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allCurrentControlCurrentSource.setVoltage(allCurrentControlCurrentSource.voltage(allCurrentControlCurrentSource.getNode1Voltage(), allCurrentControlCurrentSource.power));
                    allCurrentControlCurrentSource.setPower(allCurrentControlCurrentSource.power(allCurrentControlCurrentSource.current, allCurrentControlCurrentSource.voltage));
                }
                for (CurrentControlVoltageSource allCurrentControlVoltageSource : CurrentControlVoltageSource.getAllCurrentControlVoltageSources()) {
                    allCurrentControlVoltageSource.setVoltage(allCurrentControlVoltageSource.voltage);
                    allCurrentControlVoltageSource.setPower(allCurrentControlVoltageSource.power(allCurrentControlVoltageSource.current, allCurrentControlVoltageSource.voltage));
                }
                Data.setOutput(0);
            }
        } else if (VoltageSourceAC.getAllVoltageSourceACs().size() != 0) {
            for (float t = 0; t <= simulationTime; t += deltaT) {
                int n = Node.getAllNodes().size() - 1;
                int m = VoltageSourceDC.getAllVoltageSourceDCs().size() + VoltageSourceAC.getAllVoltageSourceACs().size() + VoltageControlVoltageSource.getAllVoltageControlVoltageSources().size() + CurrentControlVoltageSource.getAllCurrentControlVoltageSources().size();
                ComplexNumber a[][] = new ComplexNumber[m + n + 1][m + n + 1];
                ComplexNumber g[][] = new ComplexNumber[n + 1][n + 1];
                ComplexNumber b[][] = new ComplexNumber[n + 1][m + 1];
                ComplexNumber c[][] = new ComplexNumber[m + 1][n + 1];
                ComplexNumber d[][] = new ComplexNumber[m + 1][m + 1];
                ComplexNumber x[][] = new ComplexNumber[m + n + 1][2];
                ComplexNumber z[][] = new ComplexNumber[m + n + 1][2];
                ComplexNumber i[][] = new ComplexNumber[n + 1][2];
                ComplexNumber e[][] = new ComplexNumber[m + 1][2];
                for (int p = 1; p < n + 1; p++) {
                    for (int q = 1; q < n + 1; q++) {
                        ComplexNumber sum = new ComplexNumber(0, 0);
                        for (Resistance allResistance : Resistance.getAllResistances()) {
                            if (p == q) {
                                if ((allResistance.getNode1() == p) || (allResistance.getNode2() == p))
                                    sum = sum.add(1 / allResistance.getResistance());
                            }
                            else {
                                if (((allResistance.getNode1() == p) && (allResistance.getNode2()) == q) || ((allResistance.getNode1() == q) && (allResistance.getNode2()) == p))
                                    sum = sum.subtract(1 / allResistance.getResistance());
                            }
                        }
                        for (Capacitor allCapacitor : Capacitor.getAllCapacitors()) {
                            if (p == q) {
                                if ((allCapacitor.getNode1() == p) || (allCapacitor.getNode2() == p))
                                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                                        sum = sum.add(new ComplexNumber(0, allCapacitor.getCapacitor() * allVoltageSourceAC.getFrequency()));
                            }
                            else {
                                if (((allCapacitor.getNode1() == p) && (allCapacitor.getNode2()) == q) || ((allCapacitor.getNode1() == q) && (allCapacitor.getNode2()) == p))
                                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                                        sum = sum.subtract(new ComplexNumber(0, allCapacitor.getCapacitor() * allVoltageSourceAC.getFrequency()));
                            }
                        }
                        for (Inductance allInductance : Inductance.getAllInductances()) {
                            if (p == q) {
                                if ((allInductance.getNode1() == p) || (allInductance.getNode2() == p))
                                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                                        sum = sum.add(new ComplexNumber(0, 1 / (allInductance.getInductance() * allVoltageSourceAC.getFrequency())));
                            }
                            else {
                                if (((allInductance.getNode1() == p) && (allInductance.getNode2()) == q) || ((allInductance.getNode1() == q) && (allInductance.getNode2()) == p))
                                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                                        sum = sum.subtract(new ComplexNumber(0, 1 / (allInductance.getInductance() * allVoltageSourceAC.getFrequency())));
                            }
                        }
                        for (VoltageControlCurrentSource allVoltageControlCurrentSource : VoltageControlCurrentSource.getAllVoltageControlCurrentSources()) {
                            if (allVoltageControlCurrentSource.getNode1() == p) {
                                if (allVoltageControlCurrentSource.getControlNode1() == q)
                                    sum = sum.add(allVoltageControlCurrentSource.getGain());
                                else if (allVoltageControlCurrentSource.getControlNode2() == q)
                                    sum = sum.subtract(allVoltageControlCurrentSource.getGain());
                            } else if (allVoltageControlCurrentSource.getNode2() == p) {
                                if (allVoltageControlCurrentSource.getControlNode1() == q)
                                    sum = sum.subtract(allVoltageControlCurrentSource.getGain());
                                else if (allVoltageControlCurrentSource.getControlNode2() == q)
                                    sum = sum.add(allVoltageControlCurrentSource.getGain());
                            }
                        }
                        g[p][q] = sum;
                    }
                }
                for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs()) {
                    int q = 1;
                    for (int p = 1; p < n+1; p++) {
                        ComplexNumber sum1 = new ComplexNumber(0, 0);
                        ComplexNumber sum2 = new ComplexNumber(0, 0);
                        if (CurrentControlCurrentSource.getAllCurrentControlCurrentSources().size() != 0) {
                            for (CurrentControlCurrentSource allCurrentControlCurrentSource : CurrentControlCurrentSource.getAllCurrentControlCurrentSources()) {
                                if (allVoltageSourceAC.getNode1() == p) {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        sum1 = sum1.add(1 + allCurrentControlCurrentSource.getGain());
                                        b[p][q] = sum1;
                                        sum2 = sum2.add(1);
                                        c[q][p] = sum2;
                                    } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        sum1 = sum1.add(1 - allCurrentControlCurrentSource.getGain());
                                        b[p][q] = sum1;
                                        sum2 = sum2.add(1);
                                        c[q][p] = sum2;
                                    } else {
                                        sum1 = sum1.add(1);
                                        b[p][q] = sum1;
                                        sum2 = sum2.add(1);
                                        c[q][p] = sum2;
                                    }
                                } else if (allVoltageSourceAC.getNode2() == p) {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        sum1 = sum1.add(-1 + allCurrentControlCurrentSource.getGain());
                                        b[p][q] = sum1;
                                        sum2 = sum2.add(1);
                                        c[q][p] = sum2;
                                    } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        sum1 = sum1.subtract(1 + allCurrentControlCurrentSource.getGain());
                                        b[p][q] = sum1;
                                        sum2 = sum2.subtract(1);
                                        c[q][p] = sum2;
                                    } else {
                                        sum1 = sum1.subtract(1);
                                        b[p][q] = sum1;
                                        sum2 = sum2.subtract(1);
                                        c[q][p] = sum2;
                                    }
                                } else {
                                    if (allCurrentControlCurrentSource.getNode1() == p) {
                                        sum1 = sum1.add(allCurrentControlCurrentSource.getGain());
                                        b[p][q] = sum1;
                                        sum2 = sum2.add(0);
                                        c[q][p] = sum2;
                                    } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                        sum1 = sum1.subtract(allCurrentControlCurrentSource.getGain());
                                        b[p][q] = sum1;
                                        sum2 = sum2.add(0);
                                        c[q][p] = sum2;
                                    }
                                    else {
                                        sum1 = sum1.add(0);
                                        b[p][q] = sum1;
                                        sum2 = sum2.add(0);
                                        c[q][p] = sum2;
                                    }
                                }
                            }
                        }
                        else {
                            if (allVoltageSourceAC.getNode1() == p) {
                                sum1 = sum1.add(1);
                                b[p][q] = sum1;
                                sum2 = sum2.add(1);
                                c[q][p] = sum2;
                            } else if (allVoltageSourceAC.getNode2() == p) {
                                sum1 = sum1.subtract(1);
                                b[p][q] = sum1;
                                sum2 = sum2.subtract(1);
                                c[q][p] = sum2;
                            }
                            else {
                                sum1 = sum1.add(0);
                                b[p][q] = sum1;
                                sum2 = sum2.add(0);
                                c[q][p] = sum2;
                            }
                        }
                    }
                    q++;
                }
                for (VoltageControlVoltageSource allVoltageControlVoltageSource : VoltageControlVoltageSource.getAllVoltageControlVoltageSources()) {
                    int q = 1 + VoltageSourceAC.getAllVoltageSourceACs().size();
                    for (int p = 1; p < n+1; p++) {
                        ComplexNumber sum1 = new ComplexNumber(0, 0);
                        ComplexNumber sum2 = new ComplexNumber(0, 0);
                        if (allVoltageControlVoltageSource.getNode1() == p) {
                            sum1 = sum1.add(1);
                            b[p][q] = sum1;
                            if (allVoltageControlVoltageSource.getControlNode1() == p) {
                                sum2 = sum2.add(-allVoltageControlVoltageSource.getGain() + 1);
                                c[q][p] = sum2;
                            }
                            else if (allVoltageControlVoltageSource.getControlNode2() == p) {
                                sum2 = sum2.add(allVoltageControlVoltageSource.getGain() + 1);
                                c[q][p] = sum2;
                            }
                            else {
                                sum2 = sum2.add(1);
                                c[q][p] = sum2;
                            }
                        } else if (allVoltageControlVoltageSource.getNode2() == p) {
                            sum1 = sum1.subtract(1);
                            b[p][q] = sum1;
                            if (allVoltageControlVoltageSource.getControlNode1() == p) {
                                sum2 = sum2.subtract(allVoltageControlVoltageSource.getGain() + 1);
                                c[q][p] = sum2;
                            }
                            else if (allVoltageControlVoltageSource.getControlNode2() == p) {
                                sum2 = sum2.add(allVoltageControlVoltageSource.getGain() - 1);
                                c[q][p] = sum2;
                            }
                            else {
                                sum2 = sum2.subtract(1);
                                c[q][p] = sum2;
                            }
                        } else {
                            sum1 = sum1.add(0);
                            b[p][q] = sum1;
                            if (allVoltageControlVoltageSource.getControlNode1() == p) {
                                sum2 = sum2.subtract(allVoltageControlVoltageSource.getGain());
                                c[q][p] = sum2;
                            }
                            else if (allVoltageControlVoltageSource.getControlNode2() == p) {
                                sum2 = sum2.add(allVoltageControlVoltageSource.getGain());
                                c[q][p] = sum2;
                            }
                            else {
                                sum1 = sum1.add(0);
                                b[p][q] = sum1;
                                sum2 = sum2.add(0);
                                c[q][p] = sum2;
                            }
                        }
                    }
                    q++;
                }
                for (CurrentControlVoltageSource allCurrentControlVoltageSource : CurrentControlVoltageSource.getAllCurrentControlVoltageSources()) {
                    int q = 1 + VoltageSourceAC.getAllVoltageSourceACs().size() + VoltageControlVoltageSource.getAllVoltageControlVoltageSources().size();
                    for (int p = 1; p < n+1; p++) {
                        ComplexNumber sum1 = new ComplexNumber(0, 0);
                        ComplexNumber sum2 = new ComplexNumber(0, 0);
                        if (allCurrentControlVoltageSource.getNode1() == p) {
                            sum1 = sum1.add(1);
                            b[p][q] = sum1;
                            sum2 = sum2.add(1);
                            c[q][p] = sum2;
                        } else if (allCurrentControlVoltageSource.getNode2() == p) {
                            sum1 = sum1.subtract(1);
                            b[p][q] = sum1;
                            sum2 = sum2.subtract(1);
                            c[q][p] = sum2;
                        }
                        else {
                            sum1 = sum1.add(0);
                            b[p][q] = sum1;
                            sum2 = sum2.add(0);
                            c[q][p] = sum2;
                        }
                    }
                    q++;
                }
                for (int p = 1; p < m+1; p++) {
                    ComplexNumber sum = new ComplexNumber(0, 0);
                    for (int q = 1; q < m + 1; q++) {
                        sum = sum.add(0);
                        d[p][q] = sum;
                    }
                }
                for (int p = 1; p < n + 1; p++)
                    for (int q = 1; q < n + 1; q++)
                        a[p][q] = g[p][q];
                for (int p = 1; p < n + 1; p++)
                    for (int q = 1; q < m + 1; q++)
                        a[p][n + q] = b[p][q];
                for (int p = 1; p < m + 1; p++)
                    for (int q = 1; q < n + 1; q++)
                        a[n + p][q] = c[p][q];
                for (int p = 1; p < m + 1; p++)
                    for (int q = 1; q < m + 1; q++)
                        a[n + p][n + q] = d[p][q];
                for (int p = 1; p < n + 1; p++) {
                    for (CurrentSourceDC allCurrentSourceDC : CurrentSourceDC.getAllCurrentSourceDCs()) {
                        ComplexNumber sum = new ComplexNumber(0, 0);
                        if (allCurrentSourceDC.getNode1() == p) {
                            sum = sum.subtract(allCurrentSourceDC.getCurrent());
                            i[p][1] = sum;
                        }
                        else if (allCurrentSourceDC.getNode2() == p) {
                            sum = sum.add(allCurrentSourceDC.getCurrent().real());
                            i[p][1] = sum;
                        }
                        else {
                            sum = sum.add(0);
                            i[p][1] = sum;
                        }
                    }
                    for (CurrentSourceAC allCurrentSourceAC : CurrentSourceAC.getAllCurrentSourceACs()) {
                        ComplexNumber sum = new ComplexNumber(0, 0);
                        if (allCurrentSourceAC.getNode1() == p) {
                            sum = sum.subtract(allCurrentSourceAC.getAmplitude() * (float) Math.sin(2 * Math.PI * allCurrentSourceAC.getFrequency() * t + allCurrentSourceAC.getPhase()));
                            i[p][1] = sum;
                        }
                        else if (allCurrentSourceAC.getNode2() == p) {
                            sum = sum.add(allCurrentSourceAC.getAmplitude() * (float) Math.sin(2 * Math.PI * allCurrentSourceAC.getFrequency() * t + allCurrentSourceAC.getPhase()));
                            i[p][1] = sum;
                        }
                    }
                }
                for (int p = 1; p < n+1; p++) {
                    ComplexNumber sum = new ComplexNumber(0, 0);
                    sum = sum.add(0);
                    if (i[p][1] == null)
                        i[p][1] = sum;
                }
                for (int p = 1; p < m + 1; p++) {
                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs()) {
                        ComplexNumber sum = new ComplexNumber(0, 0);
                        if (Integer.parseInt(allVoltageSourceAC.getName().substring(1)) == p) {
                            sum = sum.add(allVoltageSourceAC.getAmplitude());
                            e[p][1] = sum;
                        }
                    }
                }
                for (int p = 1; p < m+1; p++) {
                    ComplexNumber sum = new ComplexNumber(0, 0);
                    sum = sum.add(0);
                    if (e[p][1] == null)
                        e[p][1] = sum;
                }
                for (int p = 1; p < n + 1; p++)
                    z[p][1] = i[p][1];
                for (int p = 1; p < m + 1; p++)
                    z[n + p][1] = e[p][1];

                /*float aTemp[][] = new float[m + n + 1][m + n + 1];
                float bTemp[][] = new float[m + n + 1][m + n + 1];
                float xTemp[][] = new float[m + n + 1][m + n + 1];
                float yTemp[][] = new float[m + n + 1][m + n + 1];
                for (int p = 1; p < m + n + 1; p++) {
                    for (int q = 1; q < m + n + 1; q++) {
                        if (a[p][q].imaginary() == 0)
                            aTemp[p][q] = a[p][q].real();
                        else {
                            aTemp[p][q] = a[p][q].real();
                            bTemp[p][q] = a[p][q].imaginary();
                        }
                    }
                }
                xTemp = Functions.invert(aTemp);
                xTemp = Functions.multiply(bTemp, xTemp);
                xTemp = Functions.multiply(xTemp, bTemp);
                xTemp = Functions.add(aTemp, xTemp);
                xTemp = Functions.invert(xTemp);
                yTemp = Functions.invert(bTemp);
                yTemp = Functions.multiply(aTemp, yTemp);
                yTemp = Functions.multiply(yTemp, aTemp);
                yTemp = Functions.add(bTemp, yTemp);
                yTemp = Functions.invert(yTemp);
                ComplexNumber invert_a[][] = new ComplexNumber[m + n + 1][m + n + 1];
                for (int p = 1; p < m + n + 1; p++) {
                    for (int q = 1; q < m + n + 1; q++) {
                        invert_a[p][q].setX(xTemp[p][q]);
                        invert_a[p][q].setY(yTemp[p][q]);
                    }
                }*/
                ComplexNumber[][] invert_a = new ComplexNumber[0][];
                for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs()) {
                    invert_a = Functions.invert((int) allVoltageSourceAC.getFrequency());
                }
                for (int p = 1; p < m + n + 1; p++) {
                    ComplexNumber sum = new ComplexNumber(0, 0);
                    sum = sum.add(0);
                    if (x[p][1] == null)
                        x[p][1] = sum;
                }
                for (int p = 1; p < m + n + 1; p++) {
                    for (int q = 1; q < 2; q++) {
                        for (int r = 1; r < m + n + 1; r++) {
                            ComplexNumber sum = new ComplexNumber(0, 0);
                            sum = sum.add(invert_a[p][r].multiply(z[r][q]));
                            x[p][q] = x[p][q].add(sum);
                        }
                    }
                }
                for (Node allNode : Node.getAllNodes())
                    for (int p = 1; p < n + 1; p++)
                        if (allNode.getNode() == p)
                            allNode.setNodeVoltage(x[p][1]);
                if ((VoltageControlVoltageSource.getAllVoltageControlVoltageSources().size() == 0) && (CurrentControlVoltageSource.getAllCurrentControlVoltageSources().size() == 0)) {
                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                        for (int p = n + 1; p < m + n + 1; p++)
                            if (Integer.parseInt(allVoltageSourceAC.getName().substring(1)) == p)
                                allVoltageSourceAC.setCurrent(x[p][1]);
                }
                else {
                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                        for (int p = n + 1; p < m + n; p++)
                            if (Integer.parseInt(allVoltageSourceAC.getName().substring(1)) == p)
                                allVoltageSourceAC.setCurrent(x[p][1]);
                    for (VoltageControlVoltageSource allVoltageControlVoltageSource : VoltageControlVoltageSource.getAllVoltageControlVoltageSources())
                        allVoltageControlVoltageSource.setCurrent(x[m + n][1]);
                    for (CurrentControlVoltageSource allCurrentControlVoltageSource : CurrentControlVoltageSource.getAllCurrentControlVoltageSources())
                        allCurrentControlVoltageSource.setCurrent(x[m + n][1]);
                }
                for (Resistance allResistance : Resistance.getAllResistances()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allResistance.getNode1() == allNode.getNode())
                            allResistance.setNode1Voltage(allNode.getNodeVoltage());
                        if (allResistance.getNode2() == allNode.getNode())
                            allResistance.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allResistance.setVoltage(allResistance.voltage(allResistance.getNode1Voltage(), allResistance.getNode2Voltage()));
                    allResistance.setCurrent(allResistance.current(allResistance.voltage));
                    allResistance.setPower(allResistance.power(allResistance.voltage, allResistance.current));
                }
                for (Capacitor allCapacitor : Capacitor.getAllCapacitors()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allCapacitor.getNode1() == allNode.getNode())
                            allCapacitor.setNode1Voltage(allNode.getNodeVoltage());
                        if (allCapacitor.getNode2() == allNode.getNode())
                            allCapacitor.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allCapacitor.setVoltage(allCapacitor.voltage(allCapacitor.getNode1Voltage(), allCapacitor.getNode2Voltage()));
                    allCapacitor.setPower(allCapacitor.voltage);
                    allCapacitor.setCurrent(allCapacitor.current(allCapacitor.getPower(), allCapacitor.voltage));
                }
                for (Inductance allInductance : Inductance.getAllInductances()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allInductance.getNode1() == allNode.getNode())
                            allInductance.setNode1Voltage(allNode.getNodeVoltage());
                        if (allInductance.getNode2() == allNode.getNode())
                            allInductance.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allInductance.setVoltage(allInductance.voltage(allInductance.getNode1Voltage(), allInductance.getNode2Voltage()));
                    allInductance.setPower(allInductance.voltage);
                    allInductance.setCurrent(allInductance.current(allInductance.getPower(), allInductance.getVoltage()));
                }
                for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs()) {
                    allVoltageSourceAC.setVoltage(allVoltageSourceAC.voltage);
                    allVoltageSourceAC.setPower(allVoltageSourceAC.power(allVoltageSourceAC.current, allVoltageSourceAC.voltage));
                }
                for (CurrentSourceDC allCurrentSourceDC : CurrentSourceDC.getAllCurrentSourceDCs()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allCurrentSourceDC.getNode1() == allNode.getNode())
                            allCurrentSourceDC.setNode1Voltage(allNode.getNodeVoltage());
                        if (allCurrentSourceDC.getNode2() == allNode.getNode())
                            allCurrentSourceDC.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allCurrentSourceDC.setVoltage(allCurrentSourceDC.voltage(allCurrentSourceDC.getNode1Voltage(), allCurrentSourceDC.getNode2Voltage()));
                    allCurrentSourceDC.setPower(allCurrentSourceDC.power(allCurrentSourceDC.current, allCurrentSourceDC.voltage));
                }
                for (CurrentSourceAC allCurrentSourceAC : CurrentSourceAC.getAllCurrentSourceACs()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allCurrentSourceAC.getNode1() == allNode.getNode())
                            allCurrentSourceAC.setNode1Voltage(allNode.getNodeVoltage());
                        if (allCurrentSourceAC.getNode2() == allNode.getNode())
                            allCurrentSourceAC.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allCurrentSourceAC.setVoltage(allCurrentSourceAC.voltage(allCurrentSourceAC.getNode1Voltage(), allCurrentSourceAC.getNode2Voltage()));
                    allCurrentSourceAC.setPower(allCurrentSourceAC.power(allCurrentSourceAC.current, allCurrentSourceAC.voltage));
                }
                for (VoltageControlVoltageSource allVoltageControlVoltageSource : VoltageControlVoltageSource.getAllVoltageControlVoltageSources()) {
                    allVoltageControlVoltageSource.setVoltage(allVoltageControlVoltageSource.voltage);
                    allVoltageControlVoltageSource.setPower(allVoltageControlVoltageSource.power(allVoltageControlVoltageSource.current, allVoltageControlVoltageSource.voltage));
                }
                for (VoltageControlCurrentSource allVoltageControlCurrentSource : VoltageControlCurrentSource.getAllVoltageControlCurrentSources()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allVoltageControlCurrentSource.getNode1() == allNode.getNode())
                            allVoltageControlCurrentSource.setNode1Voltage(allNode.getNodeVoltage());
                        if (allVoltageControlCurrentSource.getNode2() == allNode.getNode())
                            allVoltageControlCurrentSource.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allVoltageControlCurrentSource.setVoltage(allVoltageControlCurrentSource.voltage(allVoltageControlCurrentSource.getNode1Voltage(), allVoltageControlCurrentSource.getNode2Voltage()));
                    allVoltageControlCurrentSource.setPower(allVoltageControlCurrentSource.power(allVoltageControlCurrentSource.current, allVoltageControlCurrentSource.voltage));
                }
                for (CurrentControlCurrentSource allCurrentControlCurrentSource : CurrentControlCurrentSource.getAllCurrentControlCurrentSources()) {
                    for (Node allNode : Node.getAllNodes()) {
                        if (allCurrentControlCurrentSource.getNode1() == allNode.getNode())
                            allCurrentControlCurrentSource.setNode1Voltage(allNode.getNodeVoltage());
                        if (allCurrentControlCurrentSource.getNode2() == allNode.getNode())
                            allCurrentControlCurrentSource.setNode2Voltage(allNode.getNodeVoltage());
                    }
                    allCurrentControlCurrentSource.setVoltage(allCurrentControlCurrentSource.voltage(allCurrentControlCurrentSource.getNode1Voltage(), allCurrentControlCurrentSource.power));
                    allCurrentControlCurrentSource.setPower(allCurrentControlCurrentSource.power(allCurrentControlCurrentSource.current, allCurrentControlCurrentSource.voltage));
                }
                for (CurrentControlVoltageSource allCurrentControlVoltageSource : CurrentControlVoltageSource.getAllCurrentControlVoltageSources()) {
                    allCurrentControlVoltageSource.setVoltage(allCurrentControlVoltageSource.voltage);
                    allCurrentControlVoltageSource.setPower(allCurrentControlVoltageSource.power(allCurrentControlVoltageSource.current, allCurrentControlVoltageSource.voltage));
                }
                Data.setOutput(0);
            }
        }
        else {
            if (Inductance.getAllInductances().size() == 0) {
                if (CurrentSourceDC.getAllCurrentSourceDCs().size() != 0) {
                    for (CurrentSourceDC allCurrentSourceDC : CurrentSourceDC.getAllCurrentSourceDCs()) {
                        for (Resistance allResistance : Resistance.getAllResistances()) {
                            allResistance.setCurrent(allCurrentSourceDC.getCurrent());
                            allResistance.setVoltage(new ComplexNumber(allResistance.getCurrent().multiply(allResistance.getResistance()).real(), 0));
                            allResistance.setPower(allResistance.power(allResistance.getCurrent(), allResistance.getVoltage()));
                            allCurrentSourceDC.setVoltage(allResistance.getVoltage());
                            allCurrentSourceDC.setPower(allCurrentSourceDC.power(allCurrentSourceDC.current.multiply(-1), allCurrentSourceDC.voltage));
                        }
                    }
                    for (Capacitor allCapacitor : Capacitor.getAllCapacitors()) {
                        for (Resistance allResistance : Resistance.getAllResistances()) {
                            allCapacitor.setVoltage(allResistance.getVoltage());
                            allCapacitor.setCurrent(new ComplexNumber(0, 0));
                            allCapacitor.setPower(allCapacitor.power(allCapacitor.getCurrent(), allCapacitor.getVoltage()));
                        }
                    }
                    for (Node allNode : Node.getAllNodes()) {
                        for (Resistance allResistance : Resistance.getAllResistances()) {
                            if (allNode.getNode() != 0)
                                allNode.setNodeVoltage(allResistance.voltage);
                        }
                    }
                }
            }
            else {
                if (CurrentSourceDC.getAllCurrentSourceDCs().size() != 0) {
                    for (CurrentSourceDC allCurrentSourceDC : CurrentSourceDC.getAllCurrentSourceDCs()) {
                        for (Inductance allInductance : Inductance.getAllInductances()) {
                            allCurrentSourceDC.setCurrent(allCurrentSourceDC.getCurrent());
                            allInductance.setVoltage(new ComplexNumber(0, 0));
                            allInductance.setPower(allInductance.power(allInductance.current, allInductance.voltage));
                        }
                        for (Resistance allResistance : Resistance.getAllResistances()) {
                            allResistance.setCurrent(new ComplexNumber(0, 0));
                            allResistance.setVoltage(new ComplexNumber(0, 0));
                            allResistance.setPower(allResistance.power(allResistance.current, allResistance.voltage));
                        }
                        for (Capacitor allCapacitor : Capacitor.getAllCapacitors()) {
                            allCapacitor.setCurrent(new ComplexNumber(0, 0));
                            allCapacitor.setVoltage(new ComplexNumber(0, 0));
                            allCapacitor.setPower(allCapacitor.power(allCapacitor.current, allCapacitor.voltage));
                        }
                        allCurrentSourceDC.setVoltage(new ComplexNumber(0, 0));
                        allCurrentSourceDC.setPower(allCurrentSourceDC.power(allCurrentSourceDC.current, allCurrentSourceDC.voltage));
                    }
                }
                else {
                    for (Node allNode : Node.getAllNodes()) {
                        for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs()) {
                            if (allNode.getNode() != 0)
                                allNode.setNodeVoltage(allVoltageSourceDC.voltage);
                        }
                    }
                    for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs()) {
                        allVoltageSourceDC.setCurrent(new ComplexNumber(0, 0));
                        allVoltageSourceDC.setPower(allVoltageSourceDC.power(allVoltageSourceDC.current, allVoltageSourceDC.voltage));
                    }
                    for (Resistance allResistance : Resistance.getAllResistances()) {
                        allResistance.setVoltage(allResistance.voltage(allResistance.getNode1Voltage(), allResistance.getNode2Voltage()));
                        allResistance.setCurrent(allResistance.voltage.division(allResistance.getResistance()));
                        allResistance.setPower(allResistance.power(allResistance.current, allResistance.voltage));
                    }
                    for (Capacitor allCapacitor : Capacitor.getAllCapacitors()) {
                        for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs()) {
                            allCapacitor.setVoltage(allVoltageSourceDC.getVoltage());
                            allCapacitor.setCurrent(new ComplexNumber(0, 0));
                            allCapacitor.setPower(allCapacitor.power(allCapacitor.current, allCapacitor.voltage));
                        }
                    }
                    for (Inductance allInductance : Inductance.getAllInductances()) {
                        allInductance.setVoltage(allInductance.voltage(allInductance.getNode1Voltage(), allInductance.getNode2Voltage()));
                        allInductance.setCurrent(new ComplexNumber(0, 0));
                        allInductance.setPower(allInductance.power(allInductance.current, allInductance.voltage));
                    }
                }
            }
            Data.setOutput(0);
        }
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(Graphics.class);
            }
        }.start();
    }
}