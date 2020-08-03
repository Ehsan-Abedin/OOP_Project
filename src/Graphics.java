import javafx.application.Application;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class functions{
    functions() throws FileNotFoundException {}
    InputStream ResistorHI = new FileInputStream("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\resistor-H.png");
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
    public ImageView setIconElements(Node StartNode , Node EndNode , int firstState , int place){
        for(int i=1 ; i<=Element.getAllElements().size() ; i++){
            if(Element.getAllElements().get(i).getName().charAt(0)=='R'){
                if(Math.abs(Element.getAllElements().get(i).getNode1()-Element.getAllElements().get(i).getNode2())==1){
                    ImageView R = new ImageView
                    R.





                }
                else{



                }
            }
            else if(Element.getAllElements().get(i).getName().charAt(0)=='L'){
                if(Math.abs(Element.getAllElements().get(i).getNode1()-Element.getAllElements().get(i).getNode2())==1){




                }
                else{



                }
            }
            else if(Element.getAllElements().get(i).getName().charAt(0)=='C'){
                if(Math.abs(Element.getAllElements().get(i).getNode1()-Element.getAllElements().get(i).getNode2())==1){




                }
                else{



                }
            }
            else if(Element.getAllElements().get(i).getName().charAt(0)=='V'){
                if(Math.abs(Element.getAllElements().get(i).getNode1()-Element.getAllElements().get(i).getNode2())==1){




                }
                else{



                }
            }
        }
    }
    public ImageView DrawWires(Node StartNode , Node EndNode , int Place){




    }

}
public class Graphics extends Application {
    public static void Graphic(String args){
        launch(args);
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