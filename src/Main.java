import General.GlobalManager;
import General.ManagementGamescene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application{

    public void start(Stage primaryStage){

        primaryStage.setTitle("Management");
        Group root = new Group();
        ManagementGamescene scene = new ManagementGamescene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add("Style.css");

        GlobalManager.getInstance().music();
    }

    public static void main(String[] args) {
        launch(args);
    }
}