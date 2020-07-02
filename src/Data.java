public class Data {
    public static void main(String args[]){
        String resistorRegex = "(R.+? ) *(\\d*) *(\\d) *(\\d*)(.)";
        String capacitorRegex = "(C.+? ) *(\\d*) *(\\d) *(\\d*)(.)";
        String inductanceRegex = "(L.+? ) *(\\d*) *(\\d) *(\\d*)(.)";
        String VDCSourceRegex = "";
        String IDCSourceRegex = "";
        String VACSourceRegex = "(I.+? ) *(\\d*) *(\\d) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
        String IACSourceRegex = "(I.+? ) *(\\d*) *(\\d) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
        String VSourceIControl = "(H.+? ) *(\\d*) *(\\d*) *(.+? ) *(\\d*)";
        String VSourceVControl = "(E.+? ) *(\\d*) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
        String ISourceIControl = "(F.+? ) *(\\d*) *(\\d*) *(.+? ) *(\\d*)";
        String ISourceVControl = "(G.+? ) *(\\d*) *(\\d*) *(\\d*) *(\\d*) *(\\d*)";
    }
}
