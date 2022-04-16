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
        
        //GlobalManager Test
        System.out.println("crypto resource : "+GlobalManager.getInstance().getCryptoMoneyResource().getAmount());
        System.out.println(Arrays.toString(GlobalManager.getInstance().getAirshipList()));
        System.out.println("Number of ship : "+GlobalManager.getInstance().getNumberOfShip());

        //Construct new ship
        GlobalManager.getInstance().constructAirship("Sloop", GlobalManager.getInstance().getAirshipList()[0]);
        System.out.println(Arrays.toString(GlobalManager.getInstance().getAirshipList()));
        System.out.println("Number of ship : "+GlobalManager.getInstance().getNumberOfShip());

        //Upgrade
        GlobalManager.getInstance().upgradeAirship("Sloop",GlobalManager.getInstance().getAirshipList()[0],0);
        System.out.println(Arrays.toString(GlobalManager.getInstance().getAirshipList()));
        System.out.println(GlobalManager.getInstance().getAirshipList()[0].getName());

        //Construct new quarter
        GlobalManager.getInstance().getAirshipList()[0].constructQuarter("Cryptomine", 0, 1, true);
        System.out.println(Arrays.deepToString(GlobalManager.getInstance().getAirshipList()[0].getQuarterList()));
        System.out.println("crypto resource : "+GlobalManager.getInstance().getCryptoMoneyResource().getAmount());

        //Turn
        System.out.println("Turn number : "+GlobalManager.getInstance().getTurn());
        GlobalManager.getInstance().nextTurn();
        System.out.println("Turn number : "+GlobalManager.getInstance().getTurn());

        System.out.println("crypto resource : "+GlobalManager.getInstance().getCryptoMoneyResource().getAmount());
        System.out.println(GlobalManager.getInstance().getAirshipList()[0].getPositionQuarter().get(0));

        System.out.println(BombingCombatManager.getInstance().getAvailableShipList().get(0).getWeaponList().get(0).getName());
        System.out.println(BombingCombatManager.getInstance().getAvailableShipList().get(0).getWeaponList().get(0).getActionCardsList());
        //get adjacent
        //GlobalManager.getInstance().getAirshipList()[0].getAdjacent(GlobalManager.getInstance().getAirshipList()[0].getQuarterList()[0][1]);

        Deck deck = new Deck(BombingCombatManager.getInstance().getAvailableShipList().get(0).getWeaponList().get(0).getActionCardsList());
        System.out.println("Deck :");
        for(WeaponActionCard wac : deck.getDeck()) {
            System.out.println(wac.getNameActionCard());
        }
        System.out.println("Hand :");
        for(WeaponActionCard wac : deck.getHands_card()) {
            System.out.println(wac.getNameActionCard());
        }
        deck.discard(1);
        System.out.println("Hand :");
        for(WeaponActionCard wac : deck.getHands_card()) {
            if(wac != null){
                System.out.println(wac.getNameActionCard());
            }
        }
        deck.newTurn();
        System.out.println("Hand :");
        for(WeaponActionCard wac : deck.getHands_card()) {
            System.out.println(wac.getNameActionCard());
        }

        FightAirship enemy = new FightAirship(GlobalManager.getInstance().getAirshipList()[0]);
        System.out.println("enemy : "+enemy);
        BombingCombatManager.getInstance().addAirshipBattlefield(enemy,0,0);

        System.out.println("Played card : "+deck.getHands_card()[0].getNameActionCard());
        BombingCombatManager.getInstance().getAvailableShipList().get(0).getWeaponList().get(0).weaponAttack(enemy,deck.getHands_card()[0]);
        deck.discard(0);
        System.out.println("hull :"+enemy.getHullIntegrity());
        System.out.println("shield :"+enemy.getShield());

    }
}
