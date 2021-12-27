import Quarter.ProductionQuarter.ParadoxalGenerator;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){

        /*primaryStage.setTitle("Demo");
        Group root = new Group();
        Demo scene = new Demo(root);
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }
    public static void main(String[] args) {
        //launch(args);

        /*Sloop sloop = new Sloop("sloop1");

        System.out.println(sloop.getName());

        ParadoxalGenerator a=new ParadoxalGenerator();

        sloop.getQuarterList().add(a);

        System.out.println(a.getProduction());

        if (sloop.getQuarterList().get(0) instanceof ParadoxalGenerator) {
            System.out.println(1);
            System.out.println(((ParadoxalGenerator) sloop.getQuarterList().get(0)).getProduction());
        }*/




        ParadoxalGenerator gen = new ParadoxalGenerator();
        gen.upgrade();
        gen.upgrade();
        gen.upgrade();
        gen.crewUp();
        gen.crewUp();
        gen.crewUp();
        gen.crewUp();
        gen.crewUp();
        gen.crewUp();
        gen.crewUp();
        gen.crewUp();
        gen.isAdjacent(1,4,3,0);
        System.out.println(gen.getLevel());
        System.out.println(gen.getCrew());
        System.out.println(gen.getProductionBonusConstant());
        System.out.println(gen.getProductionBonusRate());
        System.out.println(gen.getProduction());
    }

    private static int bitTotal;
    private static int foodTotal;
    private static int codeDataTotal;
    private static int electricityTotal;
    private static int cryptomoneyTotal;
    private static int crewTotal;

    private static int bitBalance;
    private static int foodBalance;
    private static int codeDataBalance;
    private static int electricityBalance;
    private static int cryptomoneyBalance;
}