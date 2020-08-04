import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
class functions{
    functions() throws FileNotFoundException {}
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

    public void CreateNodes(){
        int counter=1;
        for(int j=0 ; j<=5 ; j++){
            for(int i=0 ; i<=6 ; i++){
                new Node(counter,(78*i)+7,(75*j)+10,Node.getAllNodes().get(counter).getNodeVoltage());
                counter++;
            }
        }
    }
    public ImageView setIconElements(Node StartNode , Node EndNode , int firstState , int place) throws FileNotFoundException {
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
    public ImageView DrawWires(Node StartNode , Node EndNode , int Place){




    }

}
public class Graphics extends Application {
    public static void Graphic(String args){
        launch(args);
        AnchorPane anchorPane0 = new AnchorPane();
        anchorPane0.setMinHeight(-Infinity);
        anchorPane0.setPrefHeight(715.0);
        anchorPane0.setXmlns(http://javafx.com/javafx/11.0.1);
        anchorPane0.setFx(http://javafx.com/fxml/1);
        anchorPane0.setMaxHeight(-Infinity);
        anchorPane0.setPrefWidth(883.0);
        anchorPane0.setMinWidth(-Infinity);
        anchorPane0.setMaxWidth(-Infinity);
        MenuBar menuBar1 = new MenuBar();
        menuBar1.setPrefHeight(26.0);
        menuBar1.setAnchorPane.topAnchor(1.0);
        menuBar1.setPrefWidth(600.0);
        menuBar1.setAnchorPane.rightAnchor(0.0);
        menuBar1.setAnchorPane.leftAnchor(0.0);
        menuBar1.setLayoutY(1.0);

// Adding child to parent
        anchorPane0.getChildren().add(menuBar1);
        TabPane tabPane2 = new TabPane();
        tabPane2.setPrefHeight(651.0);
        tabPane2.setPrefWidth(472.0);
        tabPane2.setLayoutY(66.0);
        tabPane2.setTabClosingPolicy(UNAVAILABLE);

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
        buttonBar6.setPrefHeight(40.0);
        buttonBar6.setPrefWidth(883.0);
        buttonBar6.setAnchorPane.rightAnchor(10.0);
        buttonBar6.setAnchorPane.leftAnchor(0.0);
        buttonBar6.setLayoutY(26.0);

// Adding child to parent
        anchorPane0.getChildren().add(buttonBar6);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Group Layout = new Group();
        Scene scene = new Scene(Layout,700,610);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Circuit Design");
    }
}