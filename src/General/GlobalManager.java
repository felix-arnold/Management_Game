package General;

import Quarter.ProductionQuarter.*;
import Quarter.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class GlobalManager {

    /* Manage turn, which means :       calculate resources
                                        research tech               Done
                                        build ship                  Done
                                        heal ships and units
                                        produce units

       Manage the list of all ships :   define list                 Done
                                        build ship                  Done
                                        upgrade ship                Done
    */


    //Constructor | Use of a singleton
    private GlobalManager () {
        this.airshipList[0]= new Airship("Sloop");
        this.turn = 1;
    }
    private static final GlobalManager INSTANCE = new GlobalManager();
    public static GlobalManager getInstance() {
        return INSTANCE;
    }


    //Definition of general resources and their getter
    private final Resources scienceResource = new Resources(false, "Science");
    private final Resources bitResource = new Resources(false, "Bit");
    private final Resources codeDataResource = new Resources(false, "Data Code");
    private final Resources cryptoMoneyResource = new Resources(true, "Cryptomoney");

    public Resources getBitResource() {
        return bitResource;
    }
    public Resources getCodeDataResource() {
        return codeDataResource;
    }
    public Resources getCryptoMoneyResource() {
        return cryptoMoneyResource;
    }
    public Resources getScienceResource() {
        return scienceResource;
    }


    //General airships list and construction
    private final Airship[] airshipList = new Airship[12];
    private int numberOfShip=1;

    //ok
    public Airship[] getAirshipList() {
        return airshipList;
    }
    public int getNumberOfShip() {
        return numberOfShip;
    }

    //ok
    //build an Airship (the button is not supposed to be available if there is not enough resources or too much ship)
    public void constructAirship(String name, Airship airship) {
        Airship provisionalShip = new Airship(name);
        if ((bitResource.getAmount() >= provisionalShip.getBitCost()) && (codeDataResource.getAmount() >= provisionalShip.getCodeDataCost()) && (cryptoMoneyResource.getAmount() >= provisionalShip.getCryptomoneyCost()) && (airship.getLocalResourcesManager().getCrewResource().getAmount() > provisionalShip.getCrewCost()) && (airship.getLocalResourcesManager().getFoodResource().getAmount() >= provisionalShip.getFoodCost()) && (airship.getLocalResourcesManager().getElectricityResource().getAmount() >= provisionalShip.getElectricityCost())  && (numberOfShip < 13)) {
            bitResource.subtractAmount(provisionalShip.getBitCost());
            codeDataResource.subtractAmount(provisionalShip.getCodeDataCost());
            cryptoMoneyResource.subtractAmount(provisionalShip.getCryptomoneyCost());
            airship.getLocalResourcesManager().getCrewResource().subtractAmount(provisionalShip.getCrewCost());
            airship.getLocalResourcesManager().getFoodResource().subtractAmount(provisionalShip.getFoodCost());
            airship.getLocalResourcesManager().getElectricityResource().subtractAmount(provisionalShip.getElectricityCost());
            airshipList[numberOfShip] = provisionalShip;

            //Ajout de la condition
            //construction of prebuild quarters
            if((provisionalShip.getPrebuildQuarter1() != null) && (provisionalShip.getPrebuildQuarter2() != null)) {
                airshipList[numberOfShip].constructQuarter(provisionalShip.getPrebuildQuarter2()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter2()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter2()[2]),true);
                airshipList[numberOfShip].constructQuarter(provisionalShip.getPrebuildQuarter1()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter1()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter1()[2]),true);
            }
            numberOfShip++;
        }
    }

    //ok
    public void upgradeAirship(String name, Airship airship, int indexAirship) {
        Airship provisionalShip = new Airship(name);
        if ((bitResource.getAmount() >= provisionalShip.getBitCost()) && (codeDataResource.getAmount() >= provisionalShip.getCodeDataCost()) && (cryptoMoneyResource.getAmount() >= provisionalShip.getCryptomoneyCost()) && (airship.getLocalResourcesManager().getCrewResource().getAmount() > provisionalShip.getCrewCost()) && (airship.getLocalResourcesManager().getFoodResource().getAmount() >= provisionalShip.getFoodCost()) && (airship.getLocalResourcesManager().getElectricityResource().getAmount() >= provisionalShip.getElectricityCost())) {
            bitResource.subtractAmount(provisionalShip.getBitCost());
            codeDataResource.subtractAmount(provisionalShip.getCodeDataCost());
            cryptoMoneyResource.subtractAmount(provisionalShip.getCryptomoneyCost());
            airship.getLocalResourcesManager().getCrewResource().subtractAmount(provisionalShip.getCrewCost());
            airship.getLocalResourcesManager().getFoodResource().subtractAmount(provisionalShip.getFoodCost());
            airship.getLocalResourcesManager().getElectricityResource().subtractAmount(provisionalShip.getElectricityCost());
            airshipList[indexAirship] = provisionalShip;

            //construction of prebuild quarters
            airshipList[indexAirship].constructQuarter(provisionalShip.getPrebuildQuarter1()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter1()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter1()[2]),true);
            airshipList[indexAirship].constructQuarter(provisionalShip.getPrebuildQuarter2()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter2()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter2()[2]),true);
        }
    }

    //ok
    //Turn manager
    private int turn;
    public int getTurn() {
        return turn;
    }

    //ok
    public void nextTurn() {
        scienceResource.setAmount(0);
        for (Airship iShip : airshipList) {
            if(iShip != null) {
                for (Quarter[] iQuarter : iShip.getQuarterList()) {
                    if(iQuarter != null) {
                        for (Quarter jQuarter : iQuarter) {
                            if(jQuarter != null) {
                                if ((jQuarter instanceof ProductionQuarter) || (jQuarter instanceof AdmiralCabin) ||(jQuarter instanceof CaptainCabin)) {
                                    for (int i = 0; i < jQuarter.getProduction().length/2; i++) {

                            /* Production type:
                                science = 1
                                bit = 2
                                dataCode = 3
                                cryptomoney = 4
                                electricity = 5
                                food = 6
                                crew = 7
                                other = 0 */

                                        //add bit production
                                        if (jQuarter.getProduction()[2*i] == 2) {
                                            bitResource.addAmount(jQuarter.getProduction()[2*i+1]);
                                        }
                                        //add dataCode production
                                        if (jQuarter.getProduction()[2*i] == 3) {
                                            codeDataResource.addAmount(jQuarter.getProduction()[2*i+1]);
                                        }
                                        //add cryptomoney production
                                        if (jQuarter.getProduction()[2*i] == 4) {
                                            cryptoMoneyResource.addAmount(jQuarter.getProduction()[2*i+1]);
                                        }
                                        //add electricity production
                                        if (jQuarter.getProduction()[2*i] == 5) {
                                            iShip.getLocalResourcesManager().getElectricityResource().addAmount(jQuarter.getProduction()[2*i+1]);
                                        }
                                        //add food production
                                        if (jQuarter.getProduction()[2*i] == 6) {
                                            iShip.getLocalResourcesManager().getFoodResource().addAmount(jQuarter.getProduction()[2*i+1]);
                                        }
                                        //add crew production
                                        if (jQuarter.getProduction()[2*i] == 7) {
                                            iShip.getLocalResourcesManager().getCrewResource().addAmount(jQuarter.getProduction()[2*i+1]);
                                            iShip.getLocalResourcesManager().getAvailableCrewResource().addAmount(jQuarter.getProduction()[2*i+1]);
                                        }
                                    }
                                }

                                //subtract cryptomoney consumption
                                cryptoMoneyResource.subtractAmount(jQuarter.getCryptomoneyConsumption());
                                //subtract electricity consumption
                                iShip.getLocalResourcesManager().getElectricityResource().subtractAmount(jQuarter.getElectricityConsumption());
                                //subtract food consumption
                                iShip.getLocalResourcesManager().getFoodResource().subtractAmount(jQuarter.getFoodConsumption());
                            }
                        }
                    }
                }
                iShip.calculateFoodBonus();
                iShip.manageElectricityOverconsumption();
            }
        }
        TechManager.getInstance().updateTech();
        turn ++;
    }


    private final Resources[] globalResources = {scienceResource, bitResource, codeDataResource, cryptoMoneyResource};
    public Resources[] getGlobalResources() {
        return globalResources;
    }

    private final Quarter[] allQuartersList = {new Berth(), new Birdcatcher(), new Cryptomine(), new ParadoxalGenerator(), new TemporalCaboose(), new DataCenter(), new ProgrammersOffice(), new IASynthetisTank(), new Cryptoinvestor(), new Galley(), new DimensionlessSpace(), new HellishBoss(), new VirtualQuantumComputer(), new MadScientist()};
    public Quarter[] getAllQuartersList() {
        return allQuartersList;
    }


    private MediaPlayer music;
    public void music(){
        music = new MediaPlayer(new Media(new File("Music.mp3").toURI().toString()));
        music.setOnEndOfMedia(new Runnable() {
            public void run() {
                music.seek(Duration.ZERO);
            }
        });
        music.play();
    }
}