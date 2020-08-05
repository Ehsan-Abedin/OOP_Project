import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Data {
    public static void getInput() throws FileNotFoundException {
        String resistorRegex = "(R+\\d*)+\\s+(\\d+)+\\s+(\\d+)+\\s+(\\w*\\d+.*)";
        Pattern p1 = Pattern.compile(resistorRegex);
        String capacitorRegex = "(C+\\d*)+\\s+(\\d+)+\\s+(\\d+)+\\s+(\\w*\\d+.*)";
        Pattern p2 = Pattern.compile(capacitorRegex);
        String inductanceRegex = "(L+\\d*)+\\s+(\\d+)+\\s+(\\d+)+\\s+(\\w*\\d+.*)";
        Pattern p3 = Pattern.compile(inductanceRegex);
        String VACSourceRegex = "(V\\d+)+\\s+(\\d+)+\\s+(\\d+)+\\s+(\\d+.*)+\\s+(\\d+.*)+\\s+(\\d+.*)+\\s+(\\d+.*)";
        Pattern p4 = Pattern.compile(VACSourceRegex);
        String IACSourceRegex = "(I\\d+)+\\s+(\\d+)+\\s+(\\d+)+\\s+(\\d+.*)+\\s+(\\d+.*)+\\s+(\\d+.*)+\\s+(\\d+.*)";
        Pattern p5 = Pattern.compile(IACSourceRegex);
        String VSourceIControl = "(H.+? ) *(\\d*) *(\\d*) *(.+? ) *(\\d*)";
        Pattern p6 = Pattern.compile(VSourceIControl);
        String VSourceVControl = "(E.+? ) *(\\d*) *(\\d*) *(\\d*) *(\\d*) *(.*)";
        Pattern p7 = Pattern.compile(VSourceVControl);
        String ISourceIControl = "(F.+? ) *(\\d*) *(\\d*) *(.+? ) *(.*)";
        Pattern p8 = Pattern.compile(ISourceIControl);
        String ISourceVControl = "(G.+? ) *(\\d*) *(\\d*) *(\\d*) *(\\d*) *(.*)";
        Pattern p9 = Pattern.compile(ISourceVControl);
        String Step= "(\\.tran.*) (\\d*)(\\w)";
        Pattern p10 = Pattern.compile(Step);
        String DeltaV = "dV( )*(\\d*)(\\w*)";
        Pattern p11 = Pattern.compile(DeltaV);
        String DeltaI = "dI( )*(\\d*)(\\w*)";
        Pattern p12 = Pattern.compile(DeltaI);
        String DeltaT = "dT( )*(\\d*)(\\w*)";
        Pattern p13 = Pattern.compile(DeltaT);
        File inputCircuit = new File("test.txt");
        String line = "";
        float myDeltaV = 0, myDeltaI = 0, myDeltaT = 0, SimulationTime = 0;
        try (Scanner sc = new Scanner(inputCircuit, StandardCharsets.UTF_8.name())) {
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                System.out.println(line);
                Matcher m1 = p1.matcher(line);
                Matcher m2 = p2.matcher(line);
                Matcher m3 = p3.matcher(line);
                Matcher m4 = p4.matcher(line);
                Matcher m5 = p5.matcher(line);
                Matcher m6 = p6.matcher(line);
                Matcher m7 = p7.matcher(line);
                Matcher m8 = p8.matcher(line);
                Matcher m9 = p9.matcher(line);
                Matcher m10 = p10.matcher(line);
                Matcher m11 = p11.matcher(line);
                Matcher m12 = p12.matcher(line);
                Matcher m13 = p13.matcher(line);
                if (m1.find()) {
                    String RName = m1.group(1).trim();
                    int N1 = Integer.parseInt(m1.group(2)), N2 = Integer.parseInt(m1.group(3));
                    String RQuantity = m1.group(4);
                    float R = 0;
                    if (RQuantity.charAt(RQuantity.length() - 1) == 'K') {
                        RQuantity = RQuantity.replace("K", "");
                        R = Float.parseFloat(RQuantity) * 1000;
                    } else if (RQuantity.charAt(RQuantity.length() - 1) == 'G') {
                        RQuantity = RQuantity.replace("G", "");
                        R = Float.parseFloat(RQuantity) * 1000000000;
                    } else if (RQuantity.charAt(RQuantity.length() - 1) == 'M') {
                        RQuantity = RQuantity.replace("M", "");
                        R = Float.parseFloat(RQuantity) * 1000000;
                    } else if (RQuantity.charAt(RQuantity.length() - 1) == 'm') {
                        RQuantity = RQuantity.replace("m", "");
                        R = Float.parseFloat(RQuantity) / 1000;
                    } else if (RQuantity.charAt(RQuantity.length() - 1) == 'n') {
                        RQuantity = RQuantity.replace("n", "");
                        R = Float.parseFloat(RQuantity) / 1000000000;
                    } else if (RQuantity.charAt(RQuantity.length() - 1) == 'u') {
                        RQuantity = RQuantity.replace("u", "");
                        R = Float.parseFloat(RQuantity) / 1000000;
                    } else if (RQuantity.charAt(RQuantity.length() - 1) == 'p') {
                        RQuantity = RQuantity.replace("p", "");
                        R = Float.parseFloat(RQuantity) / (10 ^ 12);
                    }
                    else
                        R = Float.parseFloat(RQuantity);
                    new Resistance(new ComplexNumber(0, 0), new ComplexNumber(0, 0), new ComplexNumber(0, 0), N1, N2, new ComplexNumber(0, 0), new ComplexNumber(0, 0), RName, R);
                    new Node(N1, 0, 0, new ComplexNumber(0, 0));
                    new Node(N2, 0, 0, new ComplexNumber(0, 0));
                }
                if (m2.find()) {
                    String CName = m2.group(1).trim();
                    int N1 = Integer.parseInt(m2.group(2)), N2 = Integer.parseInt(m2.group(3));
                    String CQuantity = m2.group(4);
                    float C = 0;
                    if (CQuantity.charAt(CQuantity.length() - 1) == 'K') {
                        CQuantity = CQuantity.replace("k", "");
                        C = Float.parseFloat(CQuantity) * 1000;
                    } else if (CQuantity.charAt(CQuantity.length() - 1) == 'G') {
                        CQuantity = CQuantity.replace("G", "");
                        C = Float.parseFloat(CQuantity) * 1000000000;
                    } else if (CQuantity.charAt(CQuantity.length() - 1) == 'M') {
                        CQuantity = CQuantity.replace("M", "");
                        C = Float.parseFloat(CQuantity) * 1000000;
                    } else if (CQuantity.charAt(CQuantity.length() - 1) == 'm') {
                        CQuantity = CQuantity.replace("m", "");
                        C = Float.parseFloat(CQuantity) / 1000;
                    } else if (CQuantity.charAt(CQuantity.length() - 1) == 'n') {
                        CQuantity = CQuantity.replace("n", "");
                        C = Float.parseFloat(CQuantity) / 1000000000;
                    } else if (CQuantity.charAt(CQuantity.length() - 1) == 'u') {
                        CQuantity = CQuantity.replace("u", "");
                        C = Float.parseFloat(CQuantity) / 1000000;
                    } else if (CQuantity.charAt(CQuantity.length() - 1) == 'p') {
                        CQuantity = CQuantity.replace("p", "");
                        C = Float.parseFloat(CQuantity) / (10 ^ 12);
                    }
                    else
                        C = Float.parseFloat(CQuantity);
                    new Capacitor(new ComplexNumber(0, 0), new ComplexNumber(0, 0), new ComplexNumber(0, 0), N1, N2, new ComplexNumber(0, 0), new ComplexNumber(0, 0), CName, C);
                    new Node(N1, 0, 0, new ComplexNumber(0, 0));
                    new Node(N2, 0, 0, new ComplexNumber(0, 0));
                }
                if (m3.find()) {
                    String LName = m3.group(1).trim();
                    int N1 = Integer.parseInt(m3.group(2)), N2 = Integer.parseInt(m3.group(3));
                    String LQuantity = m3.group(4);
                    float L = 0;
                    if (LQuantity.charAt(LQuantity.length() - 1) == 'K') {
                        LQuantity = LQuantity.replace("k", "");
                        L = Float.parseFloat(LQuantity) * 1000;
                    } else if (LQuantity.charAt(LQuantity.length() - 1) == 'G') {
                        LQuantity = LQuantity.replace("G", "");
                        L = Float.parseFloat(LQuantity) * 1000000000;
                    } else if (LQuantity.charAt(LQuantity.length() - 1) == 'M') {
                        LQuantity = LQuantity.replace("M", "");
                        L = Float.parseFloat(LQuantity) * 1000000;
                    } else if (LQuantity.charAt(LQuantity.length() - 1) == 'm') {
                        LQuantity = LQuantity.replace("m", "");
                        L = Float.parseFloat(LQuantity) / 1000;
                    } else if (LQuantity.charAt(LQuantity.length() - 1) == 'n') {
                        LQuantity = LQuantity.replace("n", "");
                        L = Float.parseFloat(LQuantity) / 1000000000;
                    } else if (LQuantity.charAt(LQuantity.length() - 1) == 'u') {
                        LQuantity = LQuantity.replace("u", "");
                        L = Float.parseFloat(LQuantity) / 1000000;
                    } else if (LQuantity.charAt(LQuantity.length() - 1) == 'p') {
                        LQuantity = LQuantity.replace("p", "");
                        L = Float.parseFloat(LQuantity) / (10 ^ 12);
                    }
                    else
                        L = Float.parseFloat(LQuantity);
                    new Inductance(new ComplexNumber(0, 0), new ComplexNumber(0, 0), new ComplexNumber(0, 0), N1, N2, new ComplexNumber(0, 0), new ComplexNumber(0, 0), LName, L);
                    new Node(N1, 0, 0, new ComplexNumber(0, 0));
                    new Node(N2, 0, 0, new ComplexNumber(0, 0));
                }
                if (m4.find()) {
                    String VACName = m4.group(1).trim();
                    int N1 = Integer.parseInt(m4.group(2)), N2 = Integer.parseInt(m4.group(3));
                    String VACFirstState = m4.group(4).trim();
                    String VACDomain = m4.group(5).trim();
                    String VACFrequency = m4.group(6).trim();
                    String VACPhase = m4.group(7).trim();
                    float ACFirstState = Float.parseFloat(VACFirstState);
                    float ACDomain = Float.parseFloat(VACDomain);
                    float ACFrequency = Float.parseFloat(VACFrequency);
                    float ACPhase = Float.parseFloat(VACPhase);
                    if (ACDomain == 0 && ACFrequency == 0 && ACPhase == 0) {
                        new VoltageSourceDC(new ComplexNumber(0, 0), new ComplexNumber(ACFirstState, 0), new ComplexNumber(0, 0), N1, N2, new ComplexNumber(0, 0), new ComplexNumber(0, 0), VACName);
                    } else
                        new VoltageSourceAC(new ComplexNumber(0, 0), new ComplexNumber(0, 0), new ComplexNumber(0, 0), N1, N2, new ComplexNumber(0, 0), new ComplexNumber(0, 0), VACName, ACFirstState, ACDomain, ACFrequency, ACPhase);
                    new Node(N1, 0, 0, new ComplexNumber(0, 0));
                    new Node(N2, 0, 0, new ComplexNumber(0, 0));
                }
                if (m5.find()) {
                    String IACName = m5.group(1).trim();
                    int N1 = Integer.parseInt(m5.group(2)), N2 = Integer.parseInt(m5.group(3));
                    String IACFirstState = m5.group(4).trim();
                    String IACDomain = m5.group(5).trim();
                    String IACFrequency = m5.group(6).trim();
                    String IACPhase = m5.group(7).trim();
                    float ACFirstState = Float.parseFloat(IACFirstState);
                    float ACDomain = Float.parseFloat(IACDomain);
                    float ACFrequency = Float.parseFloat(IACFrequency);
                    float ACPhase = Float.parseFloat(IACPhase);
                    if (ACDomain == 0 && ACFrequency == 0 && ACPhase == 0) {
                        new CurrentSourceDC(new ComplexNumber(ACFirstState, 0), new ComplexNumber(0, 0), new ComplexNumber(0, 0), N1, N2, new ComplexNumber(0, 0), new ComplexNumber(0, 0), IACName);
                    } else
                        new VoltageSourceAC(new ComplexNumber(0, 0), new ComplexNumber(0, 0), new ComplexNumber(0, 0), N1, N2, new ComplexNumber(0, 0), new ComplexNumber(0, 0), IACName, ACFirstState, ACDomain, ACFrequency, ACPhase);
                    new Node(N1, 0, 0, new ComplexNumber(0, 0));
                    new Node(N2, 0, 0, new ComplexNumber(0, 0));
                }
                if (m6.find()) {
                    String VSCCName = m6.group(1).trim();
                    int N1 = Integer.parseInt(m6.group(2)), N2 = Integer.parseInt(m6.group(3));
                    String ControlElement = m6.group(4).trim();
                    String gainStr = m6.group(5);
                    float gain = Float.parseFloat(gainStr);
                    new CurrentControlVoltageSource(new ComplexNumber(0, 0), new ComplexNumber(0, 0), new ComplexNumber(0, 0), N1, N2, new ComplexNumber(0, 0), new ComplexNumber(0, 0), VSCCName, ControlElement, gain);
                    new Node(N1, 0, 0, new ComplexNumber(0, 0));
                    new Node(N2, 0, 0, new ComplexNumber(0, 0));
                }
                if (m7.find()) {
                    String VSVCName = m7.group(1).trim();
                    int N1 = Integer.parseInt(m7.group(2)), N2 = Integer.parseInt(m7.group(3));
                    int N1Control = Integer.parseInt(m7.group(4)), N2Control = Integer.parseInt(m7.group(5));
                    String gainStr = m7.group(5);
                    float gain = Float.parseFloat(gainStr);
                    new VoltageControlVoltageSource(new ComplexNumber(0, 0), new ComplexNumber(0, 0), new ComplexNumber(0, 0), N1, N2, new ComplexNumber(0, 0), new ComplexNumber(0, 0), VSVCName, N1Control, N2Control, gain);
                    new Node(N1, 0, 0, new ComplexNumber(0, 0));
                    new Node(N2, 0, 0, new ComplexNumber(0, 0));
                }
                if (m8.find()) {
                    String CSCCName = m8.group(1).trim();
                    int N1 = Integer.parseInt(m8.group(2)), N2 = Integer.parseInt(m8.group(3));
                    String ControlElement = m8.group(4).trim();
                    String gainStr = m8.group(5);
                    float gain = Float.parseFloat(gainStr);
                    new CurrentControlCurrentSource(new ComplexNumber(0, 0), new ComplexNumber(0, 0), new ComplexNumber(0, 0), N1, N2, new ComplexNumber(0, 0), new ComplexNumber(0, 0), CSCCName, ControlElement, gain);
                    new Node(N1, 0, 0, new ComplexNumber(0, 0));
                    new Node(N2, 0, 0, new ComplexNumber(0, 0));
                }
                if (m9.find()) {
                    String CSVCName = m9.group(1).trim();
                    int N1 = Integer.parseInt(m9.group(2)), N2 = Integer.parseInt(m9.group(3));
                    int N1Control = Integer.parseInt(m9.group(4)), N2Control = Integer.parseInt(m9.group(5));
                    String gainStr = m9.group(5);
                    float gain = Float.parseFloat(gainStr);
                    new VoltageControlCurrentSource(new ComplexNumber(0, 0), new ComplexNumber(0, 0), new ComplexNumber(0, 0), N1, N2, new ComplexNumber(0, 0), new ComplexNumber(0, 0), CSVCName, N1Control, N2Control, gain);
                    new Node(N1, 0, 0, new ComplexNumber(0, 0));
                    new Node(N2, 0, 0, new ComplexNumber(0, 0));
                }
                if (m10.find()) {
                    String SimulationTimeQuantity = m10.group(2);
                    String SimulationTimePower = m10.group(3);
                    SimulationTime = Float.parseFloat(SimulationTimeQuantity);
                    if (SimulationTimePower.charAt(0) == 'm') {
                        SimulationTime /= 1000;
                    } else if (SimulationTimePower.charAt(0) == 'u') {
                        SimulationTime /= 1000000;
                    } else if (SimulationTimePower.charAt(0) == 'm') {
                        SimulationTime /= 1000;
                    } else if (SimulationTimePower.charAt(0) == 'p') {
                        SimulationTime /= 1000000;
                        SimulationTime /= 1000000;
                    } else if (SimulationTimePower.charAt(0) == 'n') {
                        SimulationTime /= 1000000000;
                    } else if (SimulationTimePower.charAt(0) == 'K') {
                        SimulationTime *= 1000;
                    } else if (SimulationTimePower.charAt(0) == 'M') {
                        SimulationTime *= 1000000;
                    } else if (SimulationTimePower.charAt(0) == 'G') {
                        SimulationTime *= 1000000000;
                    }
                }
                if (m11.find()) {
                    String DeltaVQuantity = m11.group(2);
                    String DeltaVPower = m11.group(3);
                    myDeltaV = Float.parseFloat(DeltaVQuantity);
                    if (DeltaVPower.charAt(0) == 'm') {
                        myDeltaV /= 1000;
                    } else if (DeltaVPower.charAt(0) == 'u') {
                        myDeltaV /= 1000000;
                    } else if (DeltaVPower.charAt(0) == 'm') {
                        myDeltaV /= 1000;
                    } else if (DeltaVPower.charAt(0) == 'p') {
                        myDeltaV /= 1000000;
                        myDeltaV /= 1000000;
                    } else if (DeltaVPower.charAt(0) == 'n') {
                        myDeltaV /= 1000000000;
                    } else if (DeltaVPower.charAt(0) == 'K') {
                        myDeltaV *= 1000;
                    } else if (DeltaVPower.charAt(0) == 'M') {
                        myDeltaV *= 1000000;
                    } else if (DeltaVPower.charAt(0) == 'G') {
                        myDeltaV *= 1000000000;
                    }
                }
                if (m12.find()) {
                    String DeltaIQuantity = m12.group(2);
                    String DeltaIPower = m12.group(3);
                    myDeltaI = Float.parseFloat(DeltaIQuantity);
                    if (DeltaIPower.charAt(0) == 'm') {
                        myDeltaI /= 1000;
                    } else if (DeltaIPower.charAt(0) == 'u') {
                        myDeltaI /= 1000000;
                    } else if (DeltaIPower.charAt(0) == 'm') {
                        myDeltaI /= 1000;
                    } else if (DeltaIPower.charAt(0) == 'p') {
                        myDeltaI /= 1000000;
                        myDeltaI /= 1000000;
                    } else if (DeltaIPower.charAt(0) == 'n') {
                        myDeltaI /= 1000000000;
                    } else if (DeltaIPower.charAt(0) == 'K') {
                        myDeltaI *= 1000;
                    } else if (DeltaIPower.charAt(0) == 'M') {
                        myDeltaI *= 1000000;
                    } else if (DeltaIPower.charAt(0) == 'G') {
                        myDeltaI *= 1000000000;
                    }
                }
                if (m13.find()) {
                    String DeltaTQuantity = m13.group(2);
                    String DeltaTPower = m13.group(3);
                    myDeltaT = Float.parseFloat(DeltaTQuantity);
                    if (DeltaTPower.charAt(0) == 'm') {
                        myDeltaT /= 1000;
                    } else if (DeltaTPower.charAt(0) == 'u') {
                        myDeltaT /= 1000000;
                    } else if (DeltaTPower.charAt(0) == 'm') {
                        myDeltaT /= 1000;
                    } else if (DeltaTPower.charAt(0) == 'p') {
                        myDeltaT /= 1000000;
                        myDeltaT /= 1000000;
                    } else if (DeltaTPower.charAt(0) == 'n') {
                        myDeltaT /= 1000000000;
                    } else if (DeltaTPower.charAt(0) == 'K') {
                        myDeltaT *= 1000;
                    } else if (DeltaTPower.charAt(0) == 'M') {
                        myDeltaT *= 1000000;
                    } else if (DeltaTPower.charAt(0) == 'G') {
                        myDeltaT *= 1000000000;
                    }
                }
            }
            Main.setData(myDeltaV, myDeltaI, myDeltaT, SimulationTime);
        }
    }
    public static void setOutput(int error){
        /*try {
            File Output = new File("Output.txt");
            if (Output.createNewFile()) {
                System.out.println("File created: " + Output.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }*/
        try {
            FileWriter OutputWriter = new FileWriter("Output.txt");
            if (error == -2) {
                OutputWriter.write("-2");
                return;
            }
            if (error == -3) {
                OutputWriter.write("-3");
                return;
            }
            if (error == -4) {
                OutputWriter.write("-4");
                return;
            }
            for(int i=1 ; i<=Node.getAllNodes().size() ; i++){
                String lineWriter = null;
                for (Node allNode : Node.getAllNodes()) {
                    if (allNode.getNode() == i) {
                        String lineStr = Integer.toString(i);
                        if (allNode.getNodeVoltage().imaginary() == 0)
                            lineWriter = "Node " + lineStr + "\t" + allNode.getNodeVoltage().real();
                        OutputWriter.write(lineWriter);
                        OutputWriter.write(System.getProperty("line.separator"));
                    }
                }
            }
            for (Element allElement : Element.getAllElements()) {
                String ElementName = allElement.getName();
                String VoltageStr, CurrentStr, PowerStr;
                if (allElement.voltage.imaginary() == 0)
                    VoltageStr = Float.toString(allElement.voltage.real());
                else
                    VoltageStr = (allElement.voltage.real()) + (allElement.voltage.imaginary()) + "j";
                if (allElement.current.imaginary() == 0)
                    CurrentStr = Float.toString(allElement.current.real());
                else
                    CurrentStr = (allElement.current.real()) + (allElement.voltage.imaginary()) + "j";
                if (allElement.power.imaginary() == 0)
                    PowerStr = Float.toString(allElement.power.real());
                else
                    PowerStr = (allElement.power.real()) + (allElement.power.imaginary()) + "j";
                String lineWriter = ElementName + "\t" + VoltageStr + "\t" + CurrentStr + "\t" + PowerStr;
                OutputWriter.write(lineWriter);
                OutputWriter.write(System.getProperty("line.separator"));
            }
            OutputWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}