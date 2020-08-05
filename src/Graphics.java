import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
class GraphicFunctions{
    GraphicFunctions() throws FileNotFoundException {}
    FileInputStream ResistorHI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\resistor-H.png");
    InputStream ResistorVI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\resistor-V.png");
    InputStream CapacitorVI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\capacitor-V.png");
    InputStream CapacitorHI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\capacitor-H.png");
    InputStream InductanceVI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\inductor-V.png");
    InputStream InductanceHI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\inductor-H.png");
    InputStream GroundI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\ground.png");
    InputStream DCVI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\dc-V.png");
    InputStream DCHI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\dc-H.png");
    InputStream WireVI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\wire-V.png");
    InputStream WireHI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\wire-H.png");
    Image ResistorH = new Image(ResistorHI);
    Image ResistorV = new Image(ResistorVI);
    Image CapacitorH = new Image(CapacitorHI);
    Image CapacitorV = new Image(CapacitorVI);
    Image InductanceH = new Image(InductanceHI);
    Image InductanceV = new Image(InductanceVI);
    Image Ground = new Image(GroundI);
    Image DCH = new Image(DCHI);
    Image DCV = new Image(DCVI);
    Image WireH = new Image(WireHI);
    Image WireV = new Image(WireVI);
    public void SetNodes(){
        int counter=0;
        for(counter=1;counter<=6;counter++){
            if(counter<Node.getAllNodes().size()){
                Node.getAllNodes().get(counter).setX((78*counter)+7);
                Node.getAllNodes().get(counter).setY(10);
            }
            else{
                new Node(counter,(78*counter)+7,10,new ComplexNumber(0,0));
            }
        }
        for(counter=7;counter<=12;counter++){
            if(counter<Node.getAllNodes().size()){
                Node.getAllNodes().get(counter).setX((78*(counter-6))+7);
                Node.getAllNodes().get(counter).setY(85);
            }
            else{
                new Node(counter,(78*(counter-6)+7),85,new ComplexNumber(0,0));
            }
        }
        for(counter=13;counter<=18;counter++){
            if(counter<Node.getAllNodes().size()){
                Node.getAllNodes().get(counter).setX((78*(counter-6))+7);
                Node.getAllNodes().get(counter).setY(160);
            }
            else{
                new Node(counter,(78*(counter-6))+7,160,new ComplexNumber(0,0));
            }
        }
        for(counter=19;counter<=24;counter++){
            if(counter<Node.getAllNodes().size()){
                Node.getAllNodes().get(counter).setX((78*(counter-6))+7);
                Node.getAllNodes().get(counter).setY(235);
            }
            else{
                new Node(counter,(78*(counter-6))+7,235,new ComplexNumber(0,0));
            }
        }
        for(counter=25;counter<=30;counter++){
            if(counter<Node.getAllNodes().size()){
                Node.getAllNodes().get(counter).setX((78*(counter-6))+7);
                Node.getAllNodes().get(counter).setY(310);
            }
            else{
                new Node(counter,(78*(counter-6))+7,310,new ComplexNumber(0,0));
            }
        }
    }
    public ImageView setIconElements() throws FileNotFoundException {
        int firstNode=0 , secondNode=0 ;
        for(int i=1 ; i<=Element.getAllElements().size() ; i++){
            firstNode = Element.getAllElements().get(i).getNode1();
            if(Element.getAllElements().get(i).getName().charAt(0)=='R'){
                if(Math.abs(Element.getAllElements().get(i).getNode1()-Element.getAllElements().get(i).getNode2())==1){
                    ImageView R = new ImageView(ResistorH);
                    R.setX(Node.getAllNodes().get(firstNode).getX());
                    R.setY(Node.getAllNodes().get(firstNode).getY()-33);
                    return R;
                }
                else{
                    ImageView R = new ImageView(ResistorV);
                    R.setX(Node.getAllNodes().get(firstNode).getX()-33);
                    R.setY(Node.getAllNodes().get(firstNode).getY());
                    return R;
                }
            }
            else if(Element.getAllElements().get(i).getName().charAt(0)=='L'){
                if(Math.abs(Element.getAllElements().get(i).getNode1()-Element.getAllElements().get(i).getNode2())==1){
                    ImageView L = new ImageView(InductanceH);
                    L.setX(Node.getAllNodes().get(firstNode).getX());
                    L.setY(Node.getAllNodes().get(firstNode).getY()-33);
                    return L;
                }
                else{
                    ImageView L = new ImageView(InductanceV);
                    L.setX(Node.getAllNodes().get(firstNode).getX()-33);
                    L.setY(Node.getAllNodes().get(firstNode).getY());
                    return L;
                }
            }
            else if(Element.getAllElements().get(i).getName().charAt(0)=='C'){
                if(Math.abs(Element.getAllElements().get(i).getNode1()-Element.getAllElements().get(i).getNode2())==1){
                    ImageView C = new ImageView(CapacitorH);
                    C.setX(Node.getAllNodes().get(firstNode).getX());
                    C.setY(Node.getAllNodes().get(firstNode).getY()-33);
                    return C;
                }
                else{
                    ImageView C = new ImageView(CapacitorV);
                    C.setX(Node.getAllNodes().get(firstNode).getX()-33);
                    C.setY(Node.getAllNodes().get(firstNode).getY());
                    return C;
                }
            }
            else if(Element.getAllElements().get(i).getName().charAt(0)=='V'){
                if(Math.abs(Element.getAllElements().get(i).getNode1()-Element.getAllElements().get(i).getNode2())==1){
                    ImageView V = new ImageView(DCH);
                    V.setX(Node.getAllNodes().get(firstNode).getX());
                    V.setY(Node.getAllNodes().get(firstNode).getY()-33);
                    return V;
                }
                else{
                    ImageView V = new ImageView(DCV);
                    V.setX(Node.getAllNodes().get(firstNode).getX()-33);
                    V.setY(Node.getAllNodes().get(firstNode).getY());
                    return V;
                }
            }
        }
        return null;
    }
    public String inputANDOutputTexts() throws IOException {
        String inputTabText = "";
        String outputTabText = "";
        File file = new File("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Test.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((inputTabText = br.readLine()) != null)
            outputTabText+= inputTabText + "\n";
        return outputTabText;
    }
    public String JustOutputTexts() throws IOException {
        String inputTabText = "";
        String outputTabText = "";
        File file = new File("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Output.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((inputTabText = br.readLine()) != null)
            outputTabText+= inputTabText + "\n";
        return outputTabText;
    }

}
public class Graphics extends Application {
    public static void Graphic(String args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        GraphicFunctions a = new GraphicFunctions();
        AnchorPane anchorPane0 = new AnchorPane();
        anchorPane0.setPrefHeight(715.0);
        // anchorPane0.setXmlns(http://javafx.com/javafx/11.0.1);
        // anchorPane0.setFx(http://javafx.com/fxml/1);
        anchorPane0.setPrefWidth(883.0);
        MenuBar menuBar1 = new MenuBar();
        menuBar1.setPrefHeight(26.0);
        menuBar1.setPrefWidth(600.0);
        menuBar1.setLayoutY(1.0);
        //menuBar1.();
// Adding child to parent
        anchorPane0.getChildren().add(menuBar1);
        TabPane tabPane2 = new TabPane();
        Tab tab1 = new Tab("Your Input Circuit");
        Tab tab2 = new Tab("Your Output Circuit");
        //tab1.setText();
        tabPane2.setPrefHeight(651.0);
        tabPane2.setPrefWidth(472.0);
        tabPane2.setLayoutY(66.0);
        tabPane2.getTabs().add(tab1);
        tabPane2.getTabs().add(tab2);
        tabPane2.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        TextArea inputCircuit = new TextArea(a.inputANDOutputTexts());
        inputCircuit.setEditable(false);
        tab1.setContent(inputCircuit);
// Adding child to parent
        anchorPane0.getChildren().add(tabPane2);
        TitledPane titledPane3 = new TitledPane();
        titledPane3.setPrefHeight(330.0);
        titledPane3.setPrefWidth(404.0);
        titledPane3.setAnimated(false);
        titledPane3.setLayoutX(479.0);
        titledPane3.setLayoutY(66.0);
        titledPane3.setText("Graphic Circuit");
// Adding child to parent
        anchorPane0.getChildren().add(titledPane3);
        Separator separator4 = new Separator();
        separator4.setPrefHeight(651.0);
        separator4.setOrientation(Orientation.VERTICAL);
        separator4.setPrefWidth(11.0);
        separator4.setLayoutX(468.0);
        separator4.setLayoutY(66.0);
// Adding child to parent
        anchorPane0.getChildren().add(separator4);
        TextArea textArea5 = new TextArea();
        textArea5.setPrefHeight(321.0);
        textArea5.setEditable(false);
        textArea5.setPrefWidth(404.0);
        textArea5.setLayoutX(479.0);
        textArea5.setLayoutY(396.0);
// Adding child to parent
        anchorPane0.getChildren().add(textArea5);
        ButtonBar buttonBar6 = new ButtonBar();
        Button Draw = new Button("Draw");
        Button Output = new Button("Output");
        Button Run = new Button("Run");
        buttonBar6.setPrefHeight(40.0);
        buttonBar6.setPrefWidth(883.0);
        buttonBar6.setLayoutY(26.0);
        buttonBar6.getButtons().add(Draw);
        buttonBar6.getButtons().add(Output);
        buttonBar6.getButtons().add(Run);
        Draw.setOnAction(E->{
            a.SetNodes();
            Group root = new Group();
            for(int i=0 ; i<Element.getAllElements().size();i++){
                if(Element.getAllElements().get(i).getName().charAt(0)=='R'){
                    if(Math.abs(Element.getAllElements().get(i).getNode1()-Element.getAllElements().get(i).getNode2())==1){
                        ImageView R = new ImageView(a.ResistorH);
                        R.setX(Node.getAllNodes().get(Element.getAllElements().get(i).getNode1()).getX());
                        R.setY(Node.getAllNodes().get(Element.getAllElements().get(i).getNode1()).getY()-33);
                        root.getChildren().add(R);
                    }
                    else{
                        ImageView R = new ImageView(a.ResistorV);
                        R.setX(Node.getAllNodes().get(Element.getAllElements().get(i).getNode1()).getX()-33);
                        R.setY(Node.getAllNodes().get(Element.getAllElements().get(i).getNode1()).getY());
                        root.getChildren().add(R);
                    }
                }
            }
            for(int j=0;j<Node.getAllNodes().size();j++){
                System.out.println(Node.getAllNodes().get(j).getX());
                System.out.println(Node.getAllNodes().get(j).getY());
            }
            Scene s = new Scene(root,1000,1000);
            stage.setResizable(false);
            stage.setScene(s);
            stage.show();
        });
        Output.setOnAction(E->{
            TextArea outputCircuit = new TextArea();
            try {
                outputCircuit.setText(a.JustOutputTexts());
            } catch (IOException e) {
                e.printStackTrace();
            }
            outputCircuit.setEditable(false);
            tab2.setContent(outputCircuit);
        });
        Run.setOnAction(E->{
            AnchorPane anchorPane1 = new AnchorPane();
            anchorPane1.setPrefHeight(729.0);
            anchorPane1.setPrefWidth(855.0);
            NumberAxis xAxis = new NumberAxis(1960, 2020, 10);
            xAxis.setLabel("Time");
            NumberAxis yAxis = new NumberAxis(0, 350, 10);
            yAxis.setLabel("No.of schools");
            LineChart lineChart1 = new LineChart(xAxis,yAxis);
            lineChart1.setPrefHeight(402.0);
            lineChart1.setPrefWidth(822.0);
            lineChart1.setLayoutX(16.0);
            lineChart1.setLayoutY(204.0);
// Adding child to parent
            anchorPane1.getChildren().add(lineChart1);
            TextArea textArea2 = new TextArea();
            textArea2.setPrefHeight(80.0);
            textArea2.setPrefWidth(523.0);
            textArea2.setLayoutX(151.0);
            textArea2.setLayoutY(58.0);
            textArea2.setPromptText("Circuit Output");
            textArea2.setEditable(false);
// Adding child to parent
            anchorPane1.getChildren().add(textArea2);
            stage.close();
            Scene RunButtonScene = new Scene(anchorPane1,anchorPane1.getWidth(),anchorPane1.getHeight());
            stage.setResizable(true);
            stage.setScene(RunButtonScene);
            stage.show();
        });
        anchorPane0.getChildren().add(buttonBar6);
        a.setIconElements();
        Scene scene = new Scene(anchorPane0,anchorPane0.getWidth(),anchorPane0.getHeight());
        stage.setTitle("Circuit Design");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}