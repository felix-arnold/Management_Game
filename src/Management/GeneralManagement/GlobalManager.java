package Management.GeneralManagement;

import Management.Quarter.ProductionQuarter.*;
import Management.Quarter.*;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

/**
 * This class manages most of the elements of the management part.
 * <br> Its roles includes to handle the passage of the turns by managing the resources values and to take care of every airship.
 */




public class GlobalManager {


    /**
     * Creates the unique instance of GlobalManager.
     * <br> Only one instance of GlobalManager needs to be created as it controls the whole management game. Moreover, its instance need to be created from the start of the game.
     * Thus, its access modifier is set to private as we want it to be a singleton.
     */
    private GlobalManager () {
        this.turn = 1;
        infoBisPane.setLayoutX(1230);
    }

    /**
     * Instantiates the unique instance of GlobalManager
     */
    private static final GlobalManager INSTANCE = new GlobalManager();

    /**
     * Returns the instance of GlobalManager.
     */
    public static GlobalManager getInstance() {
        return INSTANCE;
    }

    /**
     * Instantiates the instance of TechManager.
     */
    private static final TechManager techManager = new TechManager();

    /**
     * Returns the instance of techManager.
     */
    public static TechManager getTechManager() {
        return techManager;
    }

    //Definition of general resources and their getter
    /**
     * Resource of produced science.
     */
    private final Resources scienceResource = new Resources( "Science");
    /**
     * Resource of available bits.
     */
    private final Resources bitResource = new Resources( "Bit");
    /**
     * Resource of available data codes.
     */
    private final Resources codeDataResource = new Resources( "Data Code");
    /**
     * Resource of available cryptomoney.
     */
    private final Resources cryptoMoneyResource = new Resources("Cryptomoney");

    /**
     * Returns the bit resource.
     */
    public Resources getBitResource() {
        return bitResource;
    }

    /**
     * Returns the data code resource.
     */
    public Resources getCodeDataResource() {
        return codeDataResource;
    }

    /**
     * Returns the cryptomoney resource.
     */
    public Resources getCryptoMoneyResource() {
        return cryptoMoneyResource;
    }

    /**
     * Returns the science resource.
     */
    public Resources getScienceResource() {
        return scienceResource;
    }


    //General airships list and construction
    /**
     * List of all built airships
     * Its size is fixed as the player can not have more than 12 airships.
     */
    private final Airship[] airshipList = new Airship[12];
    /**
     * Number of built airships.
     */
    private int numberOfShip=0;

    //ok

    /**
     * Returns the list containing every airship.
     */
    public Airship[] getAirshipList() {
        return airshipList;
    }

    /**
     * Returns the number of airship owned.
     */
    public int getNumberOfShip() {
        return numberOfShip;
    }

    /**
     * Sets the number of built airships.
     * @param numberOfShip the new number of airships
     */
    public void setNumberOfShip(int numberOfShip) {
        this.numberOfShip = numberOfShip;
    }

    //ok
    //build an Airship (the button is not supposed to be available if there is not enough resources or too much ship)

    /**
     * Builds the selected kind of airship and add it to airshipList.
     * <br> Before validating the construction, the method checks if the player and the airship in argument corresponding to the airship currently displayed, disposes of enough resources. Then it removes the costs of the construction and build the airship.
     * @param name the name of the airship the player wants to build
     * @param airship the airship currently displayed which will lose its local resources to build the airship.
     */
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

    /**
     * Upgrades the selected airship. (implemented but not integrated)
     * <br> Beforehand, the methods checks if there is enough resources in the airship to upgrade it.
     * @param name the name of the airship once upgraded
     * @param airship the airship to upgrade
     * @param indexAirship index of the airship to upgrade
     */
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
            //airshipList[indexAirship].constructQuarter(provisionalShip.getPrebuildQuarter1()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter1()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter1()[2]),true);
            //airshipList[indexAirship].constructQuarter(provisionalShip.getPrebuildQuarter2()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter2()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter2()[2]),true);
        }
    }

    //ok
    //Turn manager
    /**
     * Current turn of the game
     */
    private int turn;

    /**
     * Returns the current turn of the game
     */
    public int getTurn() {
        return turn;
    }

    //ok

    /**
     * Pass to the next turn.
     * <br> For each quarter of each ship, the amount of resources produced are computed and added to the local resources of the airships and to the general resources.
     * To find out what kind of resource type the production of a quarter is, its production is of the form (type, amount) with the type being an integer between 0 and 7, as describes below. As the science resource does not indicate the available science amount but the resources produced last turn, its value is reset to 1.
     * The consumption of cryptomoney, food and electricity of the quarters is also deducts from the resources amount. Finally, we all the updateTech method from the techManager in order to research the selected technology.
     * <br> <br> We use the following representation to indicate the type of production of a quarter:
     * <br>- 1 means the production is science
     * <br>- 2 means the production is bits, but this case should not happen as bits are inherited from the platform part of the game
     * <br>- 3 means the production is data code
     * <br>- 4 means the production is cryptomoney
     * <br>- 5 means the production is electricity
     * <br>- 6 means the production is food
     * <br>- 7 means the production is crew
     * <br>- 0 means the production should not be considered as it is in most cases used to boost adjacent quarters
     */
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


    /**
     * ToggleGroup of the buttons of the categories of the construction menu.
     */
    ToggleGroup constructionSubmenuToggleGroup = new ToggleGroup();

    /**
     * Returns the ToggleGroup of the buttons of the categories of the construction menu.
     */
    public ToggleGroup getConstructionSubmenuToggleGroup() {
        return constructionSubmenuToggleGroup;
    }

    /**
     * ToggleGroup of the buttons of the construction menu which allow building quarters.
     */
    ToggleGroup constructQuarterToggleGroup = new ToggleGroup();

    /**
     * Returns the ToggleGroup of the buttons of the construction menu which allow building quarters.
     */
    public ToggleGroup getConstructQuarterToggleGroup() {
        return constructQuarterToggleGroup;
    }

    /**
     * Lists of every resource common to all airships.
     */
    private final Resources[] globalResources = {scienceResource, bitResource, codeDataResource, cryptoMoneyResource};

    /**
     * Returns the list of every resource common to all airships.
     */
    public Resources[] getGlobalResources() {
        return globalResources;
    }

    /**
     * List of the name of every different quarter class.
     */
    private final String[] allQuartersList = {"Berth", "Birdcatcher", "Cryptomine", "ParadoxalGenerator", "TemporalCaboose", "DataCenter", "ProgrammersOffice", "IASynthesisTank", "Cryptoinvestor", "Galley", "DimensionlessSpace", "HellishBoss", "VirtualQuantumComputer", "MadScientist", "DataFundry"};

    /**
     * Returns the list of the name of every different quarter class.
     */
    public String[] getAllQuartersList() {
        return allQuartersList;
    }

    /**
     * List of the name of every airship unlocked.
     */
    private final ArrayList<String> unlockedAirship = new ArrayList<>();

    /**
     * Returns the list of the name every quarter unlocked.
     */
    public ArrayList<String> getUnlockedAirships() {
        return unlockedAirship;
    }

    /**
     * List of every unlock quarter.
     */
    private final ArrayList<Quarter> unlockedQuarter = new ArrayList<>();
    /**
     * Returns the list of every unlock quarter.
     */
    public ArrayList<Quarter> getUnlockedQuarters() {
        return unlockedQuarter;
    }

    /**
     * Load the file of the music and launch it.
     * Once ended, the music restarts.
     */
    public void music(){
        MediaPlayer music = new MediaPlayer(new Media(new File("MusicMana.mp3").toURI().toString()));
        music.setOnEndOfMedia(new Runnable() {
            public void run() {
                music.seek(Duration.ZERO);
            }
        });
        music.play();
    }


    /**
     * Name of the quarter selected to be built.
     */
    private String selectedBuildQuarter;

    /**
     * Returns the name of the quarter selected to be built.
     */
    public String getSelectedBuildQuarter() {
        return selectedBuildQuarter;
    }

    /**
     * Sets the name of the quarter selected to be built.
     * @param selectedBuildQuarter the name of the selected quarter type
     */
    public void setSelectedBuildQuarter(String selectedBuildQuarter) {
        this.selectedBuildQuarter = selectedBuildQuarter;
    }

    /**
     * Index of the button of the quarter currently selected
     * It is set to -1 in case there is no selected quarter.
     */
    private int selectedQuarterButton=-1;

    /**
     * Returns the index of the button of the quarter currently selected.
     */
    public int getSelectedQuarterButton() {
        return selectedQuarterButton;
    }

    /**
     * Sets the index of the button of the quarter currently selected
     * @param selectedQuarterButton the index of the selected button
     */
    public void setSelectedQuarterButton(int selectedQuarterButton) {
        this.selectedQuarterButton = selectedQuarterButton;
    }

    /**
     * Empty pane to facilitate the display of the information of a quarter the player wants to build.
     */
    private final Pane infoBisPane = new Pane();

    /**
     * Return the pane infoBisPane.
     */
    public Pane getInfoBisPane() {
        return infoBisPane;
    }
}