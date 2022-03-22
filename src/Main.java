import Airship.ManOWar;
import General.Gamescene;
import General.GlobalManager;
import Quarter.ProductionQuarter.ParadoxalGenerator;
import Quarter.ProductionQuarter.ProductionQuarter;
import Quarter.Quarter;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

import General.*;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static General.CsvFileUser.loadValue;
import static General.CsvFileUser.readCSV;

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

        /*ArrayList<int[]> positionQuarter = new ArrayList<>();
        List<String[]> data = new ArrayList<>();
        int[] positionQuarterBisBis;
        readCSV("AirshipTest.csv", data);
        for (int i=0; i<loadValue("Sloop", data, "positionQuarter").split("/").length; i++) {
            String[] positionQuarterBis=loadValue("Sloop", data, "positionQuarter").split("/")[1].split(",");
            assert false;
            //positionQuarterBisBis = new int[]{Integer.parseInt(positionQuarterBis[0]), Integer.parseInt(positionQuarterBis[1])};
            positionQuarter.add(new int[]{Integer.parseInt(positionQuarterBis[0]), Integer.parseInt(positionQuarterBis[1])});
        }
        System.out.println(Arrays.toString(positionQuarter.get(0)) + Arrays.toString(positionQuarter.get(1)));*/

        //GlobalManager Test
        System.out.println("crypto resource : "+GlobalManager.getInstance().getCryptoMoneyResource().getAmount());
        System.out.println(Arrays.toString(GlobalManager.getInstance().getAirshipList()));
        System.out.println("Number of ship : "+GlobalManager.getInstance().getNumberOfShip());

        //Construct new ship
        GlobalManager.getInstance().constructAirship("Sloop", GlobalManager.getInstance().getAirshipList()[0]);
        System.out.println(Arrays.toString(GlobalManager.getInstance().getAirshipList()));
        System.out.println("Number of ship : "+GlobalManager.getInstance().getNumberOfShip());

        //Upgrade
        GlobalManager.getInstance().upgradeAirship("Caca",GlobalManager.getInstance().getAirshipList()[0],0);
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
        int[] test = {0,0};
        if(GlobalManager.getInstance().getAirshipList()[0].getPositionQuarter().get(0).equals(test)) {
            System.out.println("True blblblblblbblbllblblblblblblblblbblblblbl");
        }
        System.out.println(GlobalManager.getInstance().getAirshipList()[0].getPositionQuarter().get(0));
        System.out.println(test);
        //get adjacent
        //GlobalManager.getInstance().getAirshipList()[0].getAdjacent(GlobalManager.getInstance().getAirshipList()[0].getQuarterList()[0][1]);
    }
}