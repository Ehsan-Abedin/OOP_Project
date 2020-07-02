import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.time.chrono.IsoChronology;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Data {
    public static void main(String args[]) throws FileNotFoundException {
        String resistorRegex = "(R.+? ) *(\\d*) *(\\d) *(\\d*)(.)";
        Pattern p1 = Pattern.compile(resistorRegex);
        String capacitorRegex = "(C.+? ) *(\\d*) *(\\d) *(\\d*)(.)";
        Pattern p2 = Pattern.compile(capacitorRegex);
        String inductanceRegex = "(L.+? ) *(\\d*) *(\\d) *(\\d*)(.)";
        Pattern p3 = Pattern.compile(inductanceRegex);
        String VACSourceRegex = "(I.+? ) *(\\d*) *(\\d) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
        Pattern p4 = Pattern.compile(VACSourceRegex);
        String IACSourceRegex = "(I.+? ) *(\\d*) *(\\d) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
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
                new Resistance(0,0,0,N1,N2,RName);
            }
            if(m2.find()){

            }
            if(m3.find()){

            }
            if(m4.find()){

            }
            if(m5.find()){

            }
            if(m6.find()){

            }
            if(m7.find()){

            }
            if(m8.find()){

            }
            if(m9.find()){

            }
        }
    }
}
