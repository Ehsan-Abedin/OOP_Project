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
        */
/*for (CurrentSourceDC allCurrentSourceDC : CurrentSourceDC.getAllCurrentSourceDCs()) {
            for (CurrentSourceDC currentSourceDC : CurrentSourceDC.getAllCurrentSourceDCs()) {
                if ((allCurrentSourceDC.getNode1() == currentSourceDC.getNode2()) || (allCurrentSourceDC.getNode2() == currentSourceDC.getNode1()))
                    if (allCurrentSourceDC.getCurrent() != currentSourceDC.getCurrent())
                        return;
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
                if (((allVoltageSourceAC.getNode1() == voltageSourceAC.getNode1()) && (allVoltageSourceAC.getNode2() == voltageSourceAC.getNode2())) || ((allVoltageSourceAC.getNode2() == voltageSourceAC.getNode1()) && (allVoltageSourceAC.getNode1() == voltageSourceAC.getNode2())))
                    if (allVoltageSourceAC.getVoltage() != voltageSourceAC.getVoltage())
                        return;
            }
        }

        int flag = 0;
        for (Node allNode : Node.getAllNodes()) {
            if (allNode.getNode() == 0)
                flag = 1;
            if ((allNode.getNode() == 0) && ((allNode.getNodeVoltage().real() != 0) || (allNode.getNodeVoltage().imaginary() != 0)))
                return;
        }
        if (flag != 1)
            return;

*//*

        for (Element allElement : Element.getAllElements()) {
            System.out.println(allElement);
        }
        for (Node allNode : Node.getAllNodes()) {
            System.out.println(allNode);
        }
        for (Resistance allResistance : Resistance.getAllResistances()) {
            System.out.println(allResistance.getResistance());
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
                for (int p = 1; p < n + 1; p++) {
                    for (int q = 1; q < m + 1; q++) {
                        for (VoltageSourceDC allVoltageSourceDc : VoltageSourceDC.getAllVoltageSourceDCs()) {
                            if (CurrentControlCurrentSource.getAllCurrentControlCurrentSources().size() != 0) {
                                for (CurrentControlCurrentSource allCurrentControlCurrentSource : CurrentControlCurrentSource.getAllCurrentControlCurrentSources()) {
                                    if (allVoltageSourceDc.getNode1() == p) {
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
                                    } else if (allVoltageSourceDc.getNode2() == p) {
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
                                        } else {
                                            b[p][q] = 0;
                                            c[q][p] = 0;
                                        }
                                    }
                                }
                            }
                            else {
                                if (allVoltageSourceDc.getNode1() == p) {
                                    b[p][q] = 1;
                                    c[q][p] = 1;
                                }
                                else if (allVoltageSourceDc.getNode2() == p) {
                                    b[p][q] = -1;
                                    c[q][p] = -1;
                                }
                                else {
                                    b[p][q] = 0;
                                    c[q][p] = 0;
                                }
                            }
                        }
                        for (VoltageSourceAC allVoltageSourceAc : VoltageSourceAC.getAllVoltageSourceACs()) {
                            if (CurrentControlCurrentSource.getAllCurrentControlCurrentSources().size() != 0) {
                                for (CurrentControlCurrentSource allCurrentControlCurrentSource : CurrentControlCurrentSource.getAllCurrentControlCurrentSources()) {
                                    if (allVoltageSourceAc.getNode1() == p) {
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
                                    } else if (allVoltageSourceAc.getNode2() == p) {
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
                                        } else {
                                            b[p][q] = 0;
                                            c[q][p] = 0;
                                        }
                                    }
                                }
                            }
                            else {
                                if (allVoltageSourceAc.getNode1() == p) {
                                    b[p][q] = 1;
                                    c[q][p] = 1;
                                }
                                else if (allVoltageSourceAc.getNode2() == p) {
                                    b[p][q] = -1;
                                    c[q][p] = -1;
                                }
                                else {
                                    b[p][q] = 0;
                                    c[q][p] = 0;
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
                            } else if (allCurrentControlVoltageSource.getNode2() == p) {
                                b[p][q] = -1;
                                c[q][p] = -1;
                            } else {
                                b[p][q] = 0;
                                c[q][p] = 0;
                            }
                        }
                    }
                }
            */
/*if (CurrentControlVoltageSource.getAllCurrentControlVoltageSources() != null) {

            }*//*

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
                            i[p][1] = -allCurrentSourceAC.getCurrent().real();
                        else if (allCurrentSourceAC.getNode2() == p)
                            i[p][1] = allCurrentSourceAC.getCurrentDC() * (float) Math.sin(2 * Math.PI * allCurrentSourceAC.getFrequency() * t + allCurrentSourceAC.getPhase());
                    }
                }
                for (int p = 1; p < m + 1; p++) {
                    for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs())
                        if (Integer.parseInt(allVoltageSourceDC.getName().substring(1)) == p)
                            e[p][1] = allVoltageSourceDC.getVoltage().real();
                    for (VoltageSourceAC allvoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                        if (Integer.parseInt(allvoltageSourceAC.getName().substring(1)) == p)
                            e[p][1] = allvoltageSourceAC.getVoltageDC() * (float) Math.sin(2 * Math.PI * allvoltageSourceAC.getFrequency() * t + allvoltageSourceAC.getPhase());
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
                for (int p = n + 1; p < m + n + 1; p++) {
                    for (VoltageSourceDC allVoltageSourceDC : VoltageSourceDC.getAllVoltageSourceDCs())
                        allVoltageSourceDC.setCurrent(new ComplexNumber(x[p][1], 0));
                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                        if (Integer.parseInt((allVoltageSourceAC.getName().substring(1))) == p)
                            allVoltageSourceAC.setCurrent(new ComplexNumber(x[p][1], 0));
                }
                for (VoltageControlVoltageSource allVoltageControlVoltageSource : VoltageControlVoltageSource.getAllVoltageControlVoltageSources())
                    allVoltageControlVoltageSource.setCurrent(new ComplexNumber(x[m + n + 1][1], 0));
                for (CurrentControlVoltageSource allCurrentControlVoltageSource : CurrentControlVoltageSource.getAllCurrentControlVoltageSources())
                    allCurrentControlVoltageSource.setCurrent(new ComplexNumber(x[m + n + 1][1], 0));
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
                int m = VoltageSourceDC.getAllVoltageSourceDCs().size() + VoltageControlVoltageSource.getAllVoltageControlVoltageSources().size() + CurrentControlVoltageSource.getAllCurrentControlVoltageSources().size();
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
                                    sum.add(1 / allResistance.getResistance());
                            }
                            else {
                                if (((allResistance.getNode1() == p) && (allResistance.getNode2()) == q) || ((allResistance.getNode1() == q) && (allResistance.getNode2()) == p))
                                    sum.subtract(1 / allResistance.getResistance());
                            }
                        }
                        for (Capacitor allCapacitor : Capacitor.getAllCapacitors()) {
                            if (p == q)
                                if ((allCapacitor.getNode1() == p) || (allCapacitor.getNode2() == p))
                                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                                        sum.add(new ComplexNumber(0, allCapacitor.getCapacitor() * allVoltageSourceAC.getFrequency()));
                                else if (((allCapacitor.getNode1() == p) && (allCapacitor.getNode2()) == q) || ((allCapacitor.getNode1() == q) && (allCapacitor.getNode2()) == p))
                                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                                        sum.subtract(new ComplexNumber(0, allCapacitor.getCapacitor() * allVoltageSourceAC.getFrequency()));
                        }
                        for (Inductance allInductance : Inductance.getAllInductances()) {
                            if (p == q)
                                if ((allInductance.getNode1() == p) || (allInductance.getNode2() == p))
                                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                                        sum.add(new ComplexNumber(0, allInductance.getInductance() * allVoltageSourceAC.getFrequency()));
                                else if (((allInductance.getNode1() == p) && (allInductance.getNode2()) == q) || ((allInductance.getNode1() == q) && (allInductance.getNode2()) == p))
                                    for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                                        sum.subtract(new ComplexNumber(0, allInductance.getInductance() * allVoltageSourceAC.getFrequency()));
                        }
                        for (VoltageControlCurrentSource allVoltageControlCurrentSource : VoltageControlCurrentSource.getAllVoltageControlCurrentSources()) {
                            if (allVoltageControlCurrentSource.getNode1() == p) {
                                if (allVoltageControlCurrentSource.getControlNode1() == q)
                                    sum.add(allVoltageControlCurrentSource.getGain());
                                else if (allVoltageControlCurrentSource.getControlNode2() == q)
                                    sum.subtract(allVoltageControlCurrentSource.getGain());
                            } else if (allVoltageControlCurrentSource.getNode2() == p) {
                                if (allVoltageControlCurrentSource.getControlNode1() == q)
                                    sum.subtract(allVoltageControlCurrentSource.getGain());
                                else if (allVoltageControlCurrentSource.getControlNode2() == q)
                                    sum.add(allVoltageControlCurrentSource.getGain());
                            }
                        }
                        g[p][q] = sum;
                    }
                }
                for (int p = 1; p < n + 1; p++) {
                    for (int q = 1; q < m + 1; q++) {
                        for (VoltageSourceAC allVoltageSourceAc : VoltageSourceAC.getAllVoltageSourceACs()) {
                            if (CurrentControlCurrentSource.getAllCurrentControlCurrentSources().size() != 0) {
                                for (CurrentControlCurrentSource allCurrentControlCurrentSource : CurrentControlCurrentSource.getAllCurrentControlCurrentSources()) {
                                    if (allVoltageSourceAc.getNode1() == p) {
                                        if (allCurrentControlCurrentSource.getNode1() == p) {
                                            b[p][q].add(1 + allCurrentControlCurrentSource.getGain());
                                            c[q][p].add(1);
                                        } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                            b[p][q].add(1 - allCurrentControlCurrentSource.getGain());
                                            c[q][p].add(1);
                                        } else {
                                            b[p][q].add(1);
                                            c[q][p].add(1);
                                        }
                                    } else if (allVoltageSourceAc.getNode2() == p) {
                                        if (allCurrentControlCurrentSource.getNode1() == p) {
                                            b[p][q].add(-1 + allCurrentControlCurrentSource.getGain());
                                            c[q][p].subtract(1);
                                        } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                            b[p][q].subtract(1 + allCurrentControlCurrentSource.getGain());
                                            c[q][p].subtract(1);
                                        } else {
                                            b[p][q].subtract(1);
                                            c[q][p].subtract(1);
                                        }
                                    } else {
                                        if (allCurrentControlCurrentSource.getNode1() == p) {
                                            b[p][q].add(allCurrentControlCurrentSource.getGain());
                                            c[q][p].add(0);
                                        } else if (allCurrentControlCurrentSource.getNode2() == p) {
                                            b[p][q].subtract(allCurrentControlCurrentSource.getGain());
                                            c[q][p].add(0);
                                        } else {
                                            b[p][q].add(0);
                                            c[q][p].add(0);
                                        }
                                    }
                                }
                            }
                            else {
                                if (allVoltageSourceAc.getNode1() == p) {
                                    b[p][q].add(1);
                                    c[q][p].add(1);
                                }
                                else if (allVoltageSourceAc.getNode2() == p) {
                                    b[p][q].subtract(1);
                                    c[q][p].subtract(1);
                                }
                                else {
                                    b[p][q].add(0);
                                    c[q][p].add(0);
                                }
                            }
                        }
                        for (VoltageControlVoltageSource allVoltageControlVoltageSource : VoltageControlVoltageSource.getAllVoltageControlVoltageSources()) {
                            if (allVoltageControlVoltageSource.getNode1() == p) {
                                b[p][q].add(1);
                                if (allVoltageControlVoltageSource.getControlNode1() == p)
                                    c[q][p].add(-allVoltageControlVoltageSource.getGain() + 1);
                                else if (allVoltageControlVoltageSource.getControlNode2() == p)
                                    c[q][p].add(allVoltageControlVoltageSource.getGain() + 1);
                                else
                                    c[q][p].add(1);

                            } else if (allVoltageControlVoltageSource.getNode2() == p) {
                                b[p][q].subtract(1);
                                if (allVoltageControlVoltageSource.getControlNode1() == p)
                                    c[q][p].subtract(allVoltageControlVoltageSource.getGain() + 1);
                                else if (allVoltageControlVoltageSource.getControlNode2() == p)
                                    c[q][p].add(allVoltageControlVoltageSource.getGain() - 1);
                                else
                                    c[q][p].subtract(1);
                            } else {
                                b[p][q].add(0);
                                if (allVoltageControlVoltageSource.getControlNode1() == p)
                                    c[q][p].subtract(allVoltageControlVoltageSource.getGain());
                                else if (allVoltageControlVoltageSource.getControlNode2() == p)
                                    c[q][p].add(allVoltageControlVoltageSource.getGain());
                                else
                                    c[q][p].add(0);
                            }
                        }
                        for (CurrentControlVoltageSource allCurrentControlVoltageSource : CurrentControlVoltageSource.getAllCurrentControlVoltageSources()) {
                            if (allCurrentControlVoltageSource.getNode1() == p) {
                                b[p][q].add(1);
                                c[q][p].add(1);
                            } else if (allCurrentControlVoltageSource.getNode2() == p) {
                                b[p][q].subtract(1);
                                c[q][p].subtract(1);
                            } else {
                                b[p][q].add(0);
                                c[q][p].add(0);
                            }
                        }
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
                        if (allCurrentSourceDC.getNode1() == p)
                            i[p][1].subtract(allCurrentSourceDC.getCurrent());
                        else if (allCurrentSourceDC.getNode2() == p)
                            i[p][1].add(allCurrentSourceDC.getCurrent().real());
                    }
                    for (CurrentSourceAC allCurrentSourceAC : CurrentSourceAC.getAllCurrentSourceACs()) {
                        if (allCurrentSourceAC.getNode1() == p)
                            i[p][1].subtract(allCurrentSourceAC.getCurrent().real());
                        else if (allCurrentSourceAC.getNode2() == p)
                            i[p][1].add(allCurrentSourceAC.getCurrentDC() * (float) Math.sin(2 * Math.PI * allCurrentSourceAC.getFrequency() * t + allCurrentSourceAC.getPhase()));
                    }
                }
                for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                    for (int p = 1; p < m + 1; p++)
                        if (Integer.parseInt(allVoltageSourceAC.getName().substring(1)) == p)
                            e[p][1].add(allVoltageSourceAC.getVoltageDC() * (float) Math.sin(2 * Math.PI * allVoltageSourceAC.getFrequency() * t + allVoltageSourceAC.getPhase()));
                for (int p = 1; p < n + 1; p++)
                    b[p][1] = i[p][1];
                for (int p = 1; p < m + 1; p++)
                    b[n + p][1] = e[p][1];

                float aTemp[][] = new float[m + n + 1][m + n + 1];
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
                }
                for (int p = 1; p < m + n + 1; p++)
                    for (int q = 1; q < 2; q++)
                        for (int r = 1; r < m + n + 1; r++)
                            x[p][q].add(invert_a[p][r].multiply(z[r][q]));
                for (Node allNode : Node.getAllNodes())
                    for (int p = 1; p < n + 1; p++)
                        if (allNode.getNode() == p)
                            allNode.setNodeVoltage(x[p][1]);
                for (VoltageSourceAC allVoltageSourceAC : VoltageSourceAC.getAllVoltageSourceACs())
                    for (int p = n + 1; p < m + n + 1; p++)
                        if (Integer.parseInt(allVoltageSourceAC.getName().substring(1)) == p)
                            allVoltageSourceAC.setCurrent(x[p][1]);
                for (VoltageControlVoltageSource allVoltageControlVoltageSource : VoltageControlVoltageSource.getAllVoltageControlVoltageSources())
                    allVoltageControlVoltageSource.setCurrent(x[m + n + 1][1]);
                for (CurrentControlVoltageSource allCurrentControlVoltageSource : CurrentControlVoltageSource.getAllCurrentControlVoltageSources())
                    allCurrentControlVoltageSource.setCurrent(x[m + n + 1][1]);
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
    }
}

