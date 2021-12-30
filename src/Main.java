import General.Gamescene;
import Quarter.Quarter;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){

        primaryStage.setTitle("Demo");
        Group root = new Group();
        Gamescene scene = new Gamescene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add("Style.css");
    }

    public static void main(String[] args) {
        launch(args);

        /*Sloop sloop = new Sloop("sloop1");

        System.out.println(sloop.getName());

        ParadoxalGenerator a=new ParadoxalGenerator();

        sloop.getQuarterList().add(a);

        System.out.println(a.getProduction());

        if (sloop.getQuarterList().get(0) instanceof ParadoxalGenerator) {
            System.out.println(1);
            System.out.println(((ParadoxalGenerator) sloop.getQuarterList().get(0)).getProduction());
        }*/



        /*CryptoMine gen = new CryptoMine();

        int j = 1;
        int i = 0;
        while (j<4) {
            gen.upgrade();
            j++;
        }
        while (i<8) {
            gen.crewUp();
            i++;
        }

        //gen.isAdjacent(0,6,2,0);

        System.out.println(gen.getLevel());
        //System.out.println(gen.getCrew());
        //System.out.println(gen.getProductionBonusConstant());
        //System.out.println(gen.getProductionBonusRate());
        System.out.println(gen.getBitCost());
        System.out.println(gen.getCodeDataCost());
        System.out.println(gen.getCryptomoneyCost());
        System.out.println(gen.getElectricityConsumption());
        System.out.println(gen.getMaxCrew());
        System.out.println(gen.getProductionPerCrew());
        System.out.println(gen.getProduction());*/
    }
}