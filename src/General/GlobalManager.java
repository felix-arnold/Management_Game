package General;

import Quarter.ProductionQuarter.*;
import Quarter.*;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

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
        this.turn = 1;
        infoBisPane.setLayoutX(1230);
    }
    private static final GlobalManager INSTANCE = new GlobalManager();
    public static GlobalManager getInstance() {
        return INSTANCE;
    }

    private static final TechManager techManager = new TechManager();
    public static TechManager getTechManager() {
        return techManager;
    }

    //Definition of general resources and their getter
    private final Resources scienceResource = new Resources( "Science");
    private final Resources bitResource = new Resources( "Bit");
    private final Resources codeDataResource = new Resources( "Data Code");
    private final Resources cryptoMoneyResource = new Resources("Cryptomoney");

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
    public void setNumberOfShip(int numberOfShip) {
        this.numberOfShip = numberOfShip;
    }

    //ok
    //build an Airship (the button is not supposed to be available if there is not enough resources or too much ship)
    public void constructAirship(String name, Airship airship) {
        Airship provisionalShip = new Airship(name);
        if ((bitResource.getAmount() >= provisionalShip.getBitCost()) && (codeDataResource.getAmount() >= provisionalShip.getCodeDataCost()) && (cryptoMoneyResource.getAmount() >= provisionalShip.getCryptomoneyCost()) && (airship.getLocalResourcesManager().getCrewResource().getAmount() > 5) && (airship.getLocalResourcesManager().getFoodResource().getAmount() >= 50) && (airship.getLocalResourcesManager().getElectricityResource().getAmount() >= 500)  && (numberOfShip < 12)) {
            bitResource.subtractAmount(provisionalShip.getBitCost());
            codeDataResource.subtractAmount(provisionalShip.getCodeDataCost());
            cryptoMoneyResource.subtractAmount(provisionalShip.getCryptomoneyCost());
            airship.getLocalResourcesManager().getCrewResource().subtractAmount(5);
            airship.getLocalResourcesManager().getFoodResource().subtractAmount(50);
            airship.getLocalResourcesManager().getElectricityResource().subtractAmount(500);
            provisionalShip.getLocalResourcesManager().getCrewResource().setAmount(5);
            provisionalShip.getLocalResourcesManager().getFoodResource().setAmount(50);
            provisionalShip.getLocalResourcesManager().getElectricityResource().setAmount(500);
            airshipList[numberOfShip] = provisionalShip;

            //Ajout de la condition
            //construction of prebuild quarters
            /*if((provisionalShip.getPrebuildQuarter1() != null) && (provisionalShip.getPrebuildQuarter2() != null)) {
                airshipList[numberOfShip].constructQuarter(provisionalShip.getPrebuildQuarter2()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter2()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter2()[2]),true, false);
                airshipList[numberOfShip].constructQuarter(provisionalShip.getPrebuildQuarter1()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter1()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter1()[2]),true, false);
            }*/
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
            airshipList[indexAirship].constructQuarter(provisionalShip.getPrebuildQuarter1()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter1()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter1()[2]),true, false);
            airshipList[indexAirship].constructQuarter(provisionalShip.getPrebuildQuarter2()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter2()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter2()[2]),true, false);
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
        scienceResource.setAmount(1);
        for (Airship iShip : airshipList) {
            if(iShip != null) {
                iShip.updateProduction();
                for (Quarter[] iQuarter : iShip.getQuarterList()) {
                    if(iQuarter != null) {
                        for (Quarter jQuarter : iQuarter) {
                            if(jQuarter != null) {
                                if ((jQuarter instanceof ProductionQuarter)) {
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
                                        if (jQuarter.getProduction()[0] == 1) {
                                            scienceResource.addAmount(jQuarter.getProduction()[1]);
                                        }
                                        //add bit production
                                        if (jQuarter.getProduction()[0] == 2) {
                                            bitResource.addAmount(jQuarter.getProduction()[1]);
                                        }
                                        //add dataCode production
                                        if (jQuarter.getProduction()[0] == 3) {
                                            codeDataResource.addAmount(jQuarter.getProduction()[1]);
                                        }
                                        //add cryptomoney production
                                        if (jQuarter.getProduction()[0] == 4) {
                                            cryptoMoneyResource.addAmount(jQuarter.getProduction()[1]);
                                        }
                                        //add electricity production
                                        if (jQuarter.getProduction()[0] == 5) {
                                            iShip.getLocalResourcesManager().getElectricityResource().addAmount(jQuarter.getProduction()[1]);
                                        }
                                        //add food production
                                        if (jQuarter.getProduction()[0] == 6) {
                                            iShip.getLocalResourcesManager().getFoodResource().addAmount(jQuarter.getProduction()[1]);
                                        }
                                        //add crew production
                                        if (jQuarter.getProduction()[0] == 7) {
                                            iShip.getLocalResourcesManager().getCrewResource().addAmount(jQuarter.getProduction()[1]);
                                            iShip.getLocalResourcesManager().getAvailableCrewResource().addAmount(jQuarter.getProduction()[1]);
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
                //iShip.manageElectricityOverconsumption();
            }
        }
        techManager.updateTech();
        turn ++;
    }


    ToggleGroup constructionSubmenuToggleGroup = new ToggleGroup();
    public ToggleGroup getConstructionSubmenuToggleGroup() {
        return constructionSubmenuToggleGroup;
    }

    ToggleGroup constructQuarterToggleGroup = new ToggleGroup();
    public ToggleGroup getConstructQuarterToggleGroup() {
        return constructQuarterToggleGroup;
    }

    ToggleGroup quarterToggleGroup = new ToggleGroup();
    public ToggleGroup getQuarterToggleGroup() {
        return quarterToggleGroup;
    }


    private final Resources[] globalResources = {scienceResource, bitResource, codeDataResource, cryptoMoneyResource};
    public Resources[] getGlobalResources() {
        return globalResources;
    }

    private final String[] allQuartersList = {"Berth", "Birdcatcher", "Cryptomine", "ParadoxalGenerator", "TemporalCaboose", "DataCenter", "ProgrammersOffice", "IASynthesisTank", "Cryptoinvestor", "Galley", "DimensionlessSpace", "HellishBoss", "VirtualQuantumComputer", "MadScientist", "DataFundry"};
    public String[] getAllQuartersList() {
        return allQuartersList;
    }

    private final ArrayList<String> unlockedAirship = new ArrayList<>();
    public ArrayList<String> getUnlockedAirships() {
        return unlockedAirship;
    }

    private final ArrayList<Quarter> unlockedQuarter = new ArrayList<>();
    public ArrayList<Quarter> getUnlockedQuarters() {
        return unlockedQuarter;
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



    private String selectedBuildQuarter;
    public String getSelectedBuildQuarter() {
        return selectedBuildQuarter;
    }
    public void setSelectedBuildQuarter(String selectedBuildQuarter) {
        this.selectedBuildQuarter = selectedBuildQuarter;
    }

    private int selectedQuarterButton=-1;
    public int getSelectedQuarterButton() {
        return selectedQuarterButton;
    }
    public void setSelectedQuarterButton(int selectedQuarterButton) {
        this.selectedQuarterButton = selectedQuarterButton;
    }

    private Pane infoBisPane = new Pane();
    public Pane getInfoBisPane() {
        return infoBisPane;
    }
}