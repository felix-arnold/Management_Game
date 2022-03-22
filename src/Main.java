import General.Gamescene;
import General.ManagementGamescene;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){

        primaryStage.setTitle("A simple runner");
        Group root = new Group();
        ManagementGamescene scene = new ManagementGamescene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add("Style.css");
    }

    public static void main(String[] args) {
        launch(args);
    }
}