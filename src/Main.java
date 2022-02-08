import Airship.ManOWar;
import General.Gamescene;
import Quarter.ProductionQuarter.ParadoxalGenerator;
import Quarter.ProductionQuarter.ProductionQuarter;
import Quarter.Quarter;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

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

        //launch(args);

        ManOWar maw = new ManOWar("maw");

        //System.out.println(maw.getName());

        ParadoxalGenerator a=new ParadoxalGenerator();

        maw.getQuarterList()[3][3]=a;

        //System.out.println(a.getProduction());

        /*if (maw.getQuarterList()[3][3] instanceof ParadoxalGenerator) {
            System.out.println((((ParadoxalGenerator) maw.getQuarterList()[3][3]).getCodeDataCost2()));
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