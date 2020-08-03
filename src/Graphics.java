import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.*;
class functions{
    ImageIcon ResistorV = new ImageIcon("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\resistor-V.png");
    ImageIcon ResistorH = new ImageIcon("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\resistor-H.png");
    ImageIcon CapacitorV = new ImageIcon("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\capacitor-V.png");
    ImageIcon CapacitorH = new ImageIcon("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\capacitor-H.png");
    ImageIcon InductanceV = new ImageIcon("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\inductor-V.png");
    ImageIcon InductanceH = new ImageIcon("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\inductor-H.png");
    ImageIcon Ground = new ImageIcon("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\ground.png");
    ImageIcon DCV = new ImageIcon("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\dc-V.png");
    ImageIcon DCH = new ImageIcon("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\dc-H.png");
    ImageIcon WireV = new ImageIcon("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\wire-V.png");
    ImageIcon WireH = new ImageIcon("E:\\Terms\\Term 2\\OOP\\OOP_Project\\Icons\\wire-H.png");
    //ImageIcon ACV = new ImageIcon("");
    //ImageIcon ACH = new ImageIcon("");
    public void CreateNodes(){
        int counter=1;
        for(int j=0 ; j<=5 ; j++){
            for(int i=0 ; i<=6 ; i++){
             new Node(counter,(78*i)+7,(75*j)+10,Node.getAllNodes().get(counter).getNodeVoltage());
             counter++;
            }
        }
    }
    public ImageIcon setIconElements(Node StartNode , Node EndNode , int firstState , int place){




    }
    public ImageIcon DrawWires(Node StartNode , Node EndNode , int Place){




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