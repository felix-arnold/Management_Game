import Battle.GeneralBattle.BattleGamescene;
import Battle.GeneralBattle.BombingCombatManager;
import Management.GeneralManagement.GlobalManager;
import Management.GeneralManagement.ManagementGamescene;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;


public class Main extends Application{

    public void start(Stage primaryStage){

        primaryStage.setTitle("Management");
        Group root = new Group();

        /*ManagementGamescene managementScene = new ManagementGamescene(root);
        primaryStage.setScene(managementScene);
        primaryStage.show();
        managementScene.getStylesheets().add("StyleManagement.css");
        managementScene.getStylesheets().add("StyleBattle.css");
        GlobalManager.getInstance().music();*/

        BattleGamescene battleScene = new BattleGamescene(root);
        primaryStage.setScene(battleScene);
        primaryStage.show();
        battleScene.getStylesheets().add("StyleManagement.css");
        battleScene.getStylesheets().add("StyleBattle.css");
        BombingCombatManager.getInstance().music();

    }

    public static void main(String[] args) {

        launch(args);
    }
}
