import Combat.Unit.Weapon;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){

        /*primaryStage.setTitle("Demo");
        Group root = new Group();
        Gamescene scene = new Gamescene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add("Style.css");*/
    }

    public static void main(String[] args) {

        Weapon a = new Weapon("Canon broadside", 2);
        System.out.println(a.getBlockadedForcePerWeapon());
        System.out.println(a.getNumberOfWeapon());
        System.out.println(a.getPreparationTime());

    }
}