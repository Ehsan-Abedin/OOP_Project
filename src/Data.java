import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.time.chrono.IsoChronology;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Data {
    public static void main(String args[]) throws FileNotFoundException {
        String resistorRegex = "(R.+? ) *(\\d*) *(\\d) *(\\d.+?\\D)";
        Pattern p1 = Pattern.compile(resistorRegex);
        String capacitorRegex = "(C.+? ) *(\\d*) *(\\d) *(\\d*)(.)";
        Pattern p2 = Pattern.compile(capacitorRegex);
        String inductanceRegex = "(L.+? ) *(\\d*) *(\\d) *(\\d*)(.)";
        Pattern p3 = Pattern.compile(inductanceRegex);
        String VACSourceRegex = "(V.+? ) *(\\d*) *(\\d) *(.+? ) *(.+? ) *(.+? ) *(.+? ) *(.*)";
        Pattern p4 = Pattern.compile(VACSourceRegex);
        String IACSourceRegex = "(I.+? ) *(\\d*) *(\\d) *(.+? ) *(.+? ) *(.+? ) *(.+? ) *(.*)";
        Pattern p5 = Pattern.compile(IACSourceRegex);
        String VSourceIControl = "(H.+? ) *(\\d*) *(\\d*) *(.+? ) *(\\d*)";
        Pattern p6 = Pattern.compile(VSourceIControl);
        String VSourceVControl = "(E.+? ) *(\\d*) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
        Pattern p7 = Pattern.compile(VSourceVControl);
        String ISourceIControl = "(F.+? ) *(\\d*) *(\\d*) *(.+? ) *(\\d*)";
        Pattern p8 = Pattern.compile(ISourceIControl);
        String ISourceVControl = "(G.+? ) *(\\d*) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
        Pattern p9 = Pattern.compile(ISourceVControl);
        String VDCSourceRegex = "";
        String IDCSourceRegex = "";
        int i=0;
        File inputCircuit = new File("test.txt");
        String line = "";
        try (Scanner sc = new Scanner(inputCircuit, StandardCharsets.UTF_8.name())){
            for(i=0;line!="END";i++)
            line=sc.nextLine();
            Matcher m1 = p1.matcher(line);
            Matcher m2 = p2.matcher(line);
            Matcher m3 = p3.matcher(line);
            Matcher m4 = p4.matcher(line);
            Matcher m5 = p5.matcher(line);
            Matcher m6 = p6.matcher(line);
            Matcher m7 = p7.matcher(line);
            Matcher m8 = p8.matcher(line);
            Matcher m9 = p9.matcher(line);
            if(m1.find()){
                String RName=m1.group(1).trim();
                int N1=Integer.parseInt(m1.group(2)) , N2 = Integer.parseInt(m1.group(3));
                String RQuantity = m1.group(4);
                float R=0;
                if(RQuantity.indexOf(RQuantity.length()-1)=='k')
                {
                    RQuantity=RQuantity.replace("k","");
                    R=Float.parseFloat(RQuantity)*1000;
                }
                else if(RQuantity.indexOf(RQuantity.length()-1)=='G')
                {
                    RQuantity=RQuantity.replace("G","");
                    R=Float.parseFloat(RQuantity)*1000000000;
                }
                else if(RQuantity.indexOf(RQuantity.length()-1)=='M')
                {
                    RQuantity=RQuantity.replace("M","");
                    R=Float.parseFloat(RQuantity)*1000000;
                }
                else if(RQuantity.indexOf(RQuantity.length()-1)=='m')
                {
                    RQuantity=RQuantity.replace("m","");
                    R=Float.parseFloat(RQuantity)/1000;
                }
                else if(RQuantity.indexOf(RQuantity.length()-1)=='n')
                {
                    RQuantity=RQuantity.replace("n","");
                    R=Float.parseFloat(RQuantity)/1000000000;
                }
                else if(RQuantity.indexOf(RQuantity.length()-1)=='u')
                {
                    RQuantity=RQuantity.replace("u","");
                    R=Float.parseFloat(RQuantity)/1000000;
                }
                else if(RQuantity.indexOf(RQuantity.length()-1)=='p')
                {
                    RQuantity=RQuantity.replace("p","");
                    R=Float.parseFloat(RQuantity)/(10^12);
                }
                new Resistance(0,0,0,N1,N2,RName,R);
            }
            if(m2.find()){
                String CName=m2.group(1).trim();
                int N1=Integer.parseInt(m2.group(2)) , N2 = Integer.parseInt(m2.group(3));
                String CQuantity = m2.group(4);
                float C=0;
                if(CQuantity.indexOf(CQuantity.length()-1)=='k')
                {
                    CQuantity=CQuantity.replace("k","");
                    C=Float.parseFloat(CQuantity)*1000;
                }
                else if(CQuantity.indexOf(CQuantity.length()-1)=='G')
                {
                    CQuantity=CQuantity.replace("G","");
                    C=Float.parseFloat(CQuantity)*1000000000;
                }
                else if(CQuantity.indexOf(CQuantity.length()-1)=='M')
                {
                    CQuantity=CQuantity.replace("M","");
                    C=Float.parseFloat(CQuantity)*1000000;
                }
                else if(CQuantity.indexOf(CQuantity.length()-1)=='m')
                {
                    CQuantity=CQuantity.replace("m","");
                    C=Float.parseFloat(CQuantity)/1000;
                }
                else if(CQuantity.indexOf(CQuantity.length()-1)=='n')
                {
                    CQuantity=CQuantity.replace("n","");
                    C=Float.parseFloat(CQuantity)/1000000000;
                }
                else if(CQuantity.indexOf(CQuantity.length()-1)=='u')
                {
                    CQuantity=CQuantity.replace("u","");
                    C=Float.parseFloat(CQuantity)/1000000;
                }
                else if(CQuantity.indexOf(CQuantity.length()-1)=='p')
                {
                    CQuantity=CQuantity.replace("p","");
                    C=Float.parseFloat(CQuantity)/(10^12);
                }
                new Capacitor(0,0,0,N1,N2,CName,C);
            }
            if(m3.find()){
                String LName=m3.group(1).trim();
                int N1=Integer.parseInt(m3.group(2)) , N2 = Integer.parseInt(m3.group(3));
                String LQuantity = m3.group(4);
                float L=0;
                if(LQuantity.indexOf(LQuantity.length()-1)=='k')
                {
                    LQuantity=LQuantity.replace("k","");
                    L=Float.parseFloat(LQuantity)*1000;
                }
                else if(LQuantity.indexOf(LQuantity.length()-1)=='G')
                {
                    LQuantity=LQuantity.replace("G","");
                    L=Float.parseFloat(LQuantity)*1000000000;
                }
                else if(LQuantity.indexOf(LQuantity.length()-1)=='M')
                {
                    LQuantity=LQuantity.replace("M","");
                    L=Float.parseFloat(LQuantity)*1000000;
                }
                else if(LQuantity.indexOf(LQuantity.length()-1)=='m')
                {
                    LQuantity=LQuantity.replace("m","");
                    L=Float.parseFloat(LQuantity)/1000;
                }
                else if(LQuantity.indexOf(LQuantity.length()-1)=='n')
                {
                    LQuantity=LQuantity.replace("n","");
                    L=Float.parseFloat(LQuantity)/1000000000;
                }
                else if(LQuantity.indexOf(LQuantity.length()-1)=='u')
                {
                    LQuantity=LQuantity.replace("u","");
                    L=Float.parseFloat(LQuantity)/1000000;
                }
                else if(LQuantity.indexOf(LQuantity.length()-1)=='p')
                {
                    LQuantity=LQuantity.replace("p","");
                    L=Float.parseFloat(LQuantity)/(10^12);
                }
                new Inductance(0,0,0,N1,N2,LName,L);
            }
            if(m4.find()){
                String VACName=m4.group(1).trim();
                int N1=Integer.parseInt(m4.group(2)) , N2 = Integer.parseInt(m4.group(3));
                String VACFirstState = m4.group(5).trim();
                String VACDomain = m4.group(6).trim();
                String VACFrequency = m4.group(7).trim();
                String VACPhase = m4.group(8).trim();
                float ACFirstState=Float.parseFloat(VACFirstState);
                float ACDomain=Float.parseFloat(VACDomain);
                float ACFrequency=Float.parseFloat(VACFrequency);
                float ACPhase=Float.parseFloat(VACPhase);
                new VoltageSourceAC(0, 0, 0, N1 , N2 , VACName , ACFirstState,ACDomain,ACFrequency,ACPhase);
            }
            if(m5.find()){
                String IACName=m5.group(1).trim();
                int N1=Integer.parseInt(m5.group(2)) , N2 = Integer.parseInt(m5.group(3));
                String IACFirstState = m5.group(5).trim();
                String IACDomain = m5.group(6).trim();
                String IACFrequency = m5.group(7).trim();
                String IACPhase = m5.group(8).trim();
                float ACFirstState=Float.parseFloat(IACFirstState);
                float ACDomain=Float.parseFloat(IACDomain);
                float ACFrequency=Float.parseFloat(IACFrequency);
                float ACPhase=Float.parseFloat(IACPhase);
                new VoltageSourceAC(0, 0, 0, N1 , N2 , IACName , ACFirstState,ACDomain,ACFrequency,ACPhase);
            }
            if(m6.find()){
                String VSCCName=m6.group(1).trim();
                int N1=Integer.parseInt(m6.group(2)) , N2 = Integer.parseInt(m6.group(3));
                String ControlElement=m6.group(4).trim();
                String gainStr = m6.group(5);
                float gain = Float.parseFloat(gainStr);
                new CurrentControlVoltageSource(0, 0, 0, N1 , N2 ,VSCCName , ControlElement);
            }
            if(m7.find()){
                String VSCCName=m6.group(1).trim();
                int N1=Integer.parseInt(m6.group(2)) , N2 = Integer.parseInt(m6.group(3));
                String ControlElement=m6.group(4).trim();
                String gainStr = m6.group(5);
                float gain = Float.parseFloat(gainStr);
                new CurrentControlVoltageSource(0, 0, 0, N1 , N2 ,VSCCName , ControlElement);
            }
            if(m8.find()){

            }
            if(m9.find()){

            }
        }
    }
}