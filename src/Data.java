import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Data {
    public static void main(String args[]) throws FileNotFoundException {
        File inputCircuit = new File("test.txt");
        String line = "";
        try (Scanner sc = new Scanner(inputCircuit, StandardCharsets.UTF_8.name())){



        }
        String resistorRegex = "(R.+? ) *(\\d*) *(\\d) *(\\d*)(.)";
        Pattern p1 = Pattern.compile(resistorRegex);
        //Matcher m1 = new Matcher(/*noName*/);
        String capacitorRegex = "(C.+? ) *(\\d*) *(\\d) *(\\d*)(.)";
        Pattern p2 = Pattern.compile(resistorRegex);
        //Matcher m2 = new Matcher(/*noName*/);
        String inductanceRegex = "(L.+? ) *(\\d*) *(\\d) *(\\d*)(.)";
        Pattern p3 = Pattern.compile(resistorRegex);
        //Matcher m3 = new Matcher(/*noName*/);
        String VACSourceRegex = "(I.+? ) *(\\d*) *(\\d) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
        Pattern p4 = Pattern.compile(resistorRegex);
        //Matcher m4 = new Matcher(/*noName*/);
        String IACSourceRegex = "(I.+? ) *(\\d*) *(\\d) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
        Pattern p5 = Pattern.compile(resistorRegex);
        //Matcher m5 = new Matcher(/*noName*/);
        String VSourceIControl = "(H.+? ) *(\\d*) *(\\d*) *(.+? ) *(\\d*)";
        Pattern p6 = Pattern.compile(resistorRegex);
        //Matcher m6 = new Matcher(/*noName*/);
        String VSourceVControl = "(E.+? ) *(\\d*) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
        Pattern p7 = Pattern.compile(resistorRegex);
        //Matcher m7 = new Matcher(/*noName*/);
        String ISourceIControl = "(F.+? ) *(\\d*) *(\\d*) *(.+? ) *(\\d*)";
        Pattern p8 = Pattern.compile(resistorRegex);
        //Matcher m8 = new Matcher(/*noName*/);
        String ISourceVControl = "(G.+? ) *(\\d*) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
        Pattern p9 = Pattern.compile(resistorRegex);
        //Matcher m9 = new Matcher(/*noName*/);
        String VDCSourceRegex = "";
        String IDCSourceRegex = "";


    }
}
