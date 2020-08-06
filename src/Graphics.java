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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.Line;
import java.io.*;
import java.util.Date;
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
    InputStream ACHI= new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\AC-H.png");
    InputStream ACVI= new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\AC-V.png");
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
    Image ACH = new Image(ACHI);
    Image ACV = new Image(ACVI);
    public void SetNodes(){
        int counter=0;
        Node.getAllNodes().get(counter).setX(0);
        Node.getAllNodes().get(counter).setY(10);
        for(counter=1;counter<=6;counter++){
            if(counter<Node.getAllNodes().size()){
                Node.getAllNodes().get(counter).setX((75*(counter-1))+10);
                Node.getAllNodes().get(counter).setY(10);
            }
            else{
                new Node(counter,(75*(counter-1))+10,10,new ComplexNumber(0,0));
            }
        }
        for(counter=7;counter<=12;counter++){
            if(counter<Node.getAllNodes().size()){
                Node.getAllNodes().get(counter).setX((75*(counter-7))+10);
                Node.getAllNodes().get(counter).setY(85);
            }
            else{
                new Node(counter,(75*(counter-7)+10),85,new ComplexNumber(0,0));
            }
        }
        for(counter=13;counter<=18;counter++){
            if(counter<Node.getAllNodes().size()){
                Node.getAllNodes().get(counter).setX((75*(counter-13))+10);
                Node.getAllNodes().get(counter).setY(160);
            }
            else{
                new Node(counter,(75*(counter-13))+10,160,new ComplexNumber(0,0));
            }
        }
        for(counter=19;counter<=24;counter++){
            if(counter<Node.getAllNodes().size()){
                Node.getAllNodes().get(counter).setX((75*(counter-19))+10);
                Node.getAllNodes().get(counter).setY(235);
            }
            else{
                new Node(counter,(75*(counter-19))+10,235,new ComplexNumber(0,0));
            }
        }
        for(counter=25;counter<=30;counter++){
            if(counter<Node.getAllNodes().size()){
                Node.getAllNodes().get(counter).setX((75*(counter-25))+10);
                Node.getAllNodes().get(counter).setY(310);
            }
            else{
                new Node(counter,(75*(counter-25))+10,310,new ComplexNumber(0,0));
            }
        }
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
        a.SetNodes();
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
        VBox ali = new VBox();
        titledPane3.setContent(ali);
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
            ImageView g0 = new ImageView(a.Ground);
            g0.setX(Node.getAllNodes().get(0).getX());
            g0.setY(Node.getAllNodes().get(0).getY());
            System.out.println(Node.getAllNodes().get(0).getY());
            ali.getChildren().add(g0);
            for(int j=0 ; j<Element.getAllElements().size();j++){
                if(Element.getAllElements().get(j).getName().charAt(0)=='R'){
                    ImageView b = new ImageView(a.ResistorH);
                    b.setTranslateX(Node.getAllNodes().get(Element.getAllElements().get(j).getNode2()).getX());
                    b.setTranslateY(Node.getAllNodes().get(Element.getAllElements().get(j).getNode2()).getY());
                    b.setVisible(true);
                    ali.getChildren().add(b);
                }
                else if(Element.getAllElements().get(j).getName().charAt(0)=='C'){
                    ImageView b = new ImageView(a.CapacitorH);
                    b.setTranslateX(Node.getAllNodes().get(Element.getAllElements().get(j).getNode1()).getX());
                    b.setTranslateY(Node.getAllNodes().get(Element.getAllElements().get(j).getNode1()).getY());
                    b.setVisible(true);
                    ali.getChildren().add(b);
                }
                else if(Element.getAllElements().get(j).getName().charAt(0)=='L'){
                    ImageView b = new ImageView(a.InductanceH);
                    b.setTranslateX(Node.getAllNodes().get(Element.getAllElements().get(j).getNode1()).getX());
                    b.setTranslateY(Node.getAllNodes().get(Element.getAllElements().get(j).getNode1()).getY());
                    b.setVisible(true);
                    ali.getChildren().add(b);
                }
                else if(Element.getAllElements().get(j).getName().charAt(0)=='V'){
                    ImageView b = new ImageView(a.DCH);
                    b.setTranslateX(Node.getAllNodes().get(Element.getAllElements().get(j).getNode1()).getX());
                    b.setTranslateY(Node.getAllNodes().get(Element.getAllElements().get(j).getNode1()).getY());
                    b.setVisible(true);
                    ali.getChildren().add(b);
                }
                else if(Element.getAllElements().get(j).getName().charAt(0)=='I'){
                    if(Math.abs(Element.getAllElements().get(j).getNode1()-Element.getAllElements().get(j).getNode2())==1){
                        ImageView b = new ImageView(a.ACH);
                        b.setTranslateX(Node.getAllNodes().get(Element.getAllElements().get(j).getNode1()).getX());
                        b.setTranslateY(Node.getAllNodes().get(Element.getAllElements().get(j).getNode1()).getY());
                        b.setVisible(true);
                        ali.getChildren().add(b);
                    }
                    else{
                        ImageView b = new ImageView(a.ACV);
                        b.setTranslateX(Node.getAllNodes().get(Element.getAllElements().get(j).getNode1()).getX());
                        b.setTranslateY(Node.getAllNodes().get(Element.getAllElements().get(j).getNode1()).getY());
                        b.setVisible(true);
                        ali.getChildren().add(b);
                    }
                }
            }
            for(int t=0 ; t<Node.getAllNodes().size();t++){

            }
            for(int k=0;k<Node.getAllNodes().size();k++){
                System.out.printf("Node %d: (%d,%d)\n",Node.getAllNodes().get(k).getNode(),Node.getAllNodes().get(k).getX(),Node.getAllNodes().get(k).getY());
            }
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
        Scene scene = new Scene(anchorPane0,anchorPane0.getWidth(),anchorPane0.getHeight());
        stage.setTitle("Circuit Design");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }
}