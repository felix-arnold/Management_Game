import General.BattleGamescene;
import General.GlobalManager;
import General.ManagementGamescene;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;



public class Main extends Application{

    public void start(Stage primaryStage){

        primaryStage.setTitle("Management");
        Group root = new Group();
        //BattleGamescene battleScene = new BattleGamescene(root);
        ManagementGamescene managementScene = new ManagementGamescene(root);
        primaryStage.setScene(managementScene);
        primaryStage.show();
        managementScene.getStylesheets().add("Style.css");

        GlobalManager.getInstance().music();
    }

    public static void main(String[] args) {
        launch(args);


    }
}