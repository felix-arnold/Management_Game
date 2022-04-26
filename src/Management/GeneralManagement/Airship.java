package Management.GeneralManagement;

import FileReaders.CsvFileUser;
import Management.Quarter.ProductionQuarter.*;
import Management.Quarter.*;

import javafx.animation.PauseTransition;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static FileReaders.CsvFileUser.*;
import static Management.Quarter.QuarterFactory.getQuarter;
import static java.lang.Math.abs;


/**
 * This class is used to create airships and to manage their properties, notably their quarters.
 */
public class Airship {

    /**
     * Name of the airship.
     */
    private final String name;
    /**
     * Matrix of the quarters build in the airship.
     * Compared to the buildable quarters displayed, it has 2 more rows on the top and bottom, and 2 more columns on the left and right, so that the adjacentQuarter function works.
     */
    private final Quarter[][] quarterList;
    /**
     *Number of rows in quarterList.
     */
    private final int numberOfRaws;
    /**
     *Number of columns in quarterList.
     */
    private final int numberOfColumns;

    /**
     * Construction cost in bits of the airship.
     */
    private final int bitCost;
    /**
     * Construction cost in data code of the airship.
     */
    private final int codeDataCost;
    /**
     * Construction cost in cryptomoney of the airship.
     */
    private final int cryptomoneyCost;
    /**
     * Construction cost in electricity of the airship.
     */
    private final int electricityCost;
    /**
     * Construction cost in food of the airship.
     */
    private final int foodCost;
    /**
     * Construction cost in crew of the airship.
     */
    private final int crewCost;
    /**
     * Name and position of the first prebuild quarter.
     */
    private final String[] prebuildQuarter1;
    /**
     * Name and position of the second prebuild quarter.
     */
    private final String[] prebuildQuarter2;

    /**
     * Manager of local resources of the airship.
     */
    protected LocalResourcesManager localResourcesManager = new LocalResourcesManager();


    /**
     * Property of the airship which is currently displayed.
     */
    private boolean isDisplayed=false;
    /**
     * List of disabled quarters.
     */
    private final ArrayList<int[]> disabledQuarterList = new ArrayList<>();
    /**
     * Content of the CSV file containing the data of all airships.
     */
    private static final List<String[]> airshipData = new ArrayList<>();
    /**
     * List of the name of the quarters in their order of priority for the computing of their production.
     */
    private static final String[] priorityList = {"Berth","VirtualQuantumComputer", "MadScientist", "HellishBoss", "Cryptoinvestor", "DataCenter", "ProgrammersOffice", "Birdcatcher", "TemporalCaboose", "IASynthesisTank", "Galley", "DimensionlessSpace", "ParadoxalGenerator", "Cryptomine"};




    //Combat variables
    /**
     * Maximum hull integrity, equivalent of the maximum health of the ship.
     */
    private final int maxHullIntegrity;
    /**
     * Maximum shield of the ship.
     */
    private final int maxShield;
    /**
     * Armor rating of the hull, decreasing the received damage.
     */
    private final int shieldArmorRating;
    /**
     * Armor rating of the shield, decreasing the received damage.
     */
    private final int hullArmorRating;
    /**
     * Speed of the airship.
     */
    private final int speed;
    /**
     * Current hull integrity of the ship.
     */
    private int hullIntegrity;
    /**
     * List of the weapons build on the ship.
     */
    private final ArrayList<Quarter> weaponsList = new ArrayList<>();


    //Constructor

    /**
     * Creates a new instance of airship.
     * <br> The method loads the value of the different properties from the AirshipData.csv and initializes the pane containing the button for each quarter place.
     * These buttons are placed depending on the list of available positions inside the CSV file.
     * <br>   - If the button of a non-null quarter is selected, its information are displayed.
     * <br>   - If the button of a null quarter is selected and the property selectedBuildQuarter of GlobalManager is not empty, the quarter described in selectedBuildQuarter is build at the spot of the selected quarter.
     * <br>   - If the property selectedBuildQuarter of GlobalManager is not empty but the construction can not happen, the selected quarter button blink in red.
     * @param name the name of the airship
     */
    public Airship(String name) {
        CsvFileUser.readCSV("AirshipData.csv", airshipData);
        this.name = name;
        bitCost = Integer.parseInt(loadValue(name, airshipData, "bitCost"));
        codeDataCost = Integer.parseInt(loadValue(name, airshipData, "codeDataCost"));
        cryptomoneyCost = Integer.parseInt(loadValue(name, airshipData, "cryptomoneyCost"));
        electricityCost = Integer.parseInt(loadValue(name, airshipData, "electricityCost"));
        foodCost = Integer.parseInt(loadValue(name, airshipData, "foodCost"));
        crewCost = Integer.parseInt(loadValue(name, airshipData, "crewCost"));
        numberOfRaws = Integer.parseInt(loadValue(name, airshipData, "numberOfRaws"));
        numberOfColumns = Integer.parseInt(loadValue(name, airshipData, "numberOfColumns"));

        maxHullIntegrity = Integer.parseInt(loadValue(name, airshipData, "maxHullIntegrity"));
        maxShield = Integer.parseInt(loadValue(name, airshipData, "maxShield"));
        shieldArmorRating = Integer.parseInt(loadValue(name,airshipData, "shieldArmorRating"));
        hullArmorRating = Integer.parseInt(loadValue(name,airshipData, "hullArmorRating"));
        speed = Integer.parseInt(loadValue(name, airshipData, "speed"));

        int x = Integer.parseInt(loadValue(name, airshipData, "x"));
        int y = Integer.parseInt(loadValue(name, airshipData, "y"));
        int width = Integer.parseInt(loadValue(name, airshipData, "width"));
        int height = Integer.parseInt(loadValue(name, airshipData, "height"));
        int fitWidth = Integer.parseInt(loadValue(name, airshipData, "fitWidth"));
        int fitHeight = Integer.parseInt(loadValue(name, airshipData, "fitHeight"));
        int numberOfFrame = Integer.parseInt(loadValue(name, airshipData, "numberOfFrame"));
        image = new AnimatedThing(name+".png", x, y, width, height, numberOfFrame, fitWidth, fitHeight);

        quarterList = new Quarter[numberOfRaws][numberOfColumns];

        hullIntegrity=maxHullIntegrity;

        prebuildQuarter1 = loadValue(name, airshipData, "prebuildQuarter1").split("/");
        prebuildQuarter2 = loadValue(name, airshipData, "prebuildQuarter2").split("/");

        //load positionQuarter as an array of values
        ArrayList<int[]> positionQuarter = new ArrayList<>();
        for (int i = 0; i<loadValue(name, airshipData, "positionQuarter").split("/").length; i++) {
            String[] positionQuarterBis=loadValue(name, airshipData, "positionQuarter").split("/")[i].split(",");
            assert false;
            positionQuarter.add(new int[]{Integer.parseInt(positionQuarterBis[0]), Integer.parseInt(positionQuarterBis[1])});
        }

        int xPane = Integer.parseInt(loadValue(name, airshipData, "xPane"));
        int yPane = Integer.parseInt(loadValue(name, airshipData, "yPane"));
        int widthQuarter = Integer.parseInt(loadValue(name, airshipData, "widthQuarter"));
        int heightQuarter = Integer.parseInt(loadValue(name, airshipData, "heightQuarter"));

        quarterInfoPane.setLayoutX(1230);

        quarterPane.setHgap(3);
        quarterPane.setVgap(3);
        quarterPane.setLayoutX(xPane);
        quarterPane.setLayoutY(yPane);
        final int[]  k= {0};
        for (int[] position : positionQuarter) {
            RadioButton radioButton = new RadioButton();
            radioButton.setPrefSize(widthQuarter, heightQuarter);
            radioButton.setToggleGroup(toggleQuarter);
            radioButton.getStyleClass().remove("radio-button");
            radioButton.getStyleClass().add("emptyQuarter");
            radioButton.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
                if (isNowSelected) {
                    GlobalManager.getInstance().setSelectedQuarterButton(k[0]);
                    radioButton.getStyleClass().clear();
                    GlobalManager.getInstance().getConstructionSubmenuToggleGroup().selectToggle(null);
                    if (quarterList[position[0]][position[1]] == null) {
                        radioButton.getStyleClass().add("selectedEmptyQuarter");
                        selectedQuarter[1] = position[1];
                        selectedQuarter[0] = position[0];

                        if (GlobalManager.getInstance().getSelectedBuildQuarter() != null) {
                            if (constructQuarter(GlobalManager.getInstance().getSelectedBuildQuarter(), position[0], position[1], false) == 0) {
                                radioButton.setGraphic(quarterList[position[0]][position[1]].getFitterSelectedQuarterIcon(widthQuarter, heightQuarter));
                                quarterInfoPane.getChildren().add(quarterList[position[0]][position[1]].getQuarterInfoPane());
                                quarterList[position[0]][position[1]].loadQuarterInfoPane();
                            }
                            else {
                                radioButton.getStyleClass().clear();
                                radioButton.getStyleClass().add("errorQuarter");
                                PauseTransition pause = new PauseTransition(
                                        Duration.seconds(0.7));
                                pause.setOnFinished(event -> {
                                    radioButton.getStyleClass().clear();
                                    radioButton.getStyleClass().add("selectedEmptyQuarter");
                                });
                                pause.play();
                            }
                        }
                    }
                    else {
                        if (GlobalManager.getInstance().getSelectedBuildQuarter() != null) {

                            radioButton.getStyleClass().clear();
                            radioButton.getStyleClass().add("errorQuarter");
                            radioButton.setGraphic(null);
                            PauseTransition pause = new PauseTransition(
                                    Duration.seconds(0.7));
                            pause.setOnFinished(event -> {
                                radioButton.getStyleClass().clear();
                                radioButton.getStyleClass().add("selectedEmptyQuarter");
                            });
                            pause.play();
                        }
                        radioButton.setGraphic(quarterList[position[0]][position[1]].getFitterSelectedQuarterIcon(widthQuarter, heightQuarter));
                        GlobalManager.getInstance().getConstructionSubmenuToggleGroup().selectToggle(null);
                        quarterInfoPane.getChildren().add(quarterList[position[0]][position[1]].getQuarterInfoPane());
                    }
                }
                else {
                    GlobalManager.getInstance().setSelectedQuarterButton(-1);
                    radioButton.getStyleClass().clear();
                    if (quarterList[position[0]][position[1]] == null) {
                        radioButton.getStyleClass().add("emptyQuarter");
                    }
                    else {
                        radioButton.setGraphic(quarterList[position[0]][position[1]].getFitterQuarterIcon(widthQuarter, heightQuarter));
                        quarterInfoPane.getChildren().remove(quarterList[position[0]][position[1]].getQuarterInfoPane());
                    }
                }
            });
            quarterPane.add(radioButton, position[1], position[0]);
            k[0]++;
        }
        RowConstraints constraintHeight = new RowConstraints();
        ColumnConstraints constraintWidth = new ColumnConstraints();
        constraintHeight.setPrefHeight(heightQuarter);
        constraintWidth.setPrefWidth(widthQuarter);
        for (int i = 0; i <= numberOfRaws; i++) {
            quarterPane.getRowConstraints().add(constraintHeight);
        }
        for (int j = 0; j <= numberOfColumns; j++) {
            quarterPane.getColumnConstraints().add(constraintWidth);
        }
    }

    /**
     * Position (x,y) of the selected quarter.
     */
    private final int[] selectedQuarter = {-1,-1};

    /**
     * Returns the position of the selected quarter.
     */
    public int[] getSelectedQuarter() {
        return selectedQuarter;
    }

    /**
     * AnimatedThing of the airship
     */
    private final AnimatedThing image;

    /**
     * Returns the image property.
     */
    public AnimatedThing getImage() {
        return image;
    }

    /**
     * Pane displaying the information of a quarter.
     */
    private final Pane quarterInfoPane = new Pane();
    /**
     * GridPane containing the button of each quarter.
     */
    private final GridPane quarterPane = new GridPane();

    /**
     * ToggleGroup of the button of each quarter.
     */
    private static final ToggleGroup toggleQuarter =  new ToggleGroup();
    /**
     * Returns the TogglfeGroup of the button of each quarter.
     */
    public static ToggleGroup getToggleQuarter() {
        return toggleQuarter;
    }

    /**
     * Returns the pane containing the button of each quarter.
     */
    public GridPane getQuarterPane() {
        return quarterPane;
    }

    /**
     * Returns the pane containing the quarter information.
     */
    public Pane getQuarterInfoPane() {
        return quarterInfoPane;
    }
    /////////////// ADD FOR UPGRADING

    //ok
    //Construct the building at the selected place

    /**
     * Builds the selected quarter at the specified position.
     * <br> Beforehand, the method check if the player disposes of enough resources to build the quarter and if the selected position is empty.
     * The boolean resourcesException allow to build the quarter without using resource.
     * <br> No matter the outcome, the build button is unselected in the end.
     * @param quarterName the name of the quarter the player wants to build
     * @param xPos the x desired position of the quarter
     * @param yPos the y desired position of the quarter
     * @param resourcesException allows building without using resources
     * @return 0 in case of successful building, -1 otherwise
     */
    public int constructQuarter(String quarterName, int xPos, int yPos, boolean resourcesException) {
        Quarter quarter = getQuarter(quarterName, 1);
        assert quarter != null;
        if ((quarterList[xPos][yPos]==null && GlobalManager.getInstance().getBitResource().getAmount() >= quarter.getBitCost()) && (GlobalManager.getInstance().getCodeDataResource().getAmount() >= quarter.getCodeDataCost()) && (GlobalManager.getInstance().getCryptoMoneyResource().getAmount() >= quarter.getCryptomoneyCost()) && !resourcesException) {
            GlobalManager.getInstance().getBitResource().subtractAmount(quarter.getBitCost());
            GlobalManager.getInstance().getCodeDataResource().subtractAmount(quarter.getCodeDataCost());
            GlobalManager.getInstance().getCryptoMoneyResource().subtractAmount(quarter.getCryptomoneyCost());
            quarterList[xPos][yPos] = quarter;
            quarter.setAirship(this);
            quarter.setX(xPos);
            quarter.setY(yPos);
            GlobalManager.getInstance().setSelectedBuildQuarter(null);
            GlobalManager.getInstance().getConstructQuarterToggleGroup().selectToggle(null);
            return 0;
        }
        else if (quarterList[xPos][yPos]==null && resourcesException) {
            quarterList[xPos][yPos] = quarter;
            GlobalManager.getInstance().setSelectedBuildQuarter(null);
            GlobalManager.getInstance().getConstructQuarterToggleGroup().selectToggle(null);
            quarter.setAirship(this);
            return 0;
        }
        else {
            GlobalManager.getInstance().setSelectedBuildQuarter(null);
            GlobalManager.getInstance().getConstructQuarterToggleGroup().selectToggle(null);
            return -1;
        }
    }

    /**
     * Upgrades the selected quarter.
     * <br>The method first check if the quarter can be upgraded, then if the player disposes of enough resources to upgrade it.
     * <br> No matter the outcome, the upgrade button is unselected in the end.
     * @param quarter the quarter to upgrade
     */
    public void upgrade(Quarter quarter) {
        if (quarter.getLevel()<quarter.getMaxLevel()) {
            Quarter quarterBis = getQuarter(quarter.getTrueName(), quarter.getLevel()+1);
            while(quarter.getCrew()>0) {
                quarter.subCrew();
            }
            assert quarterBis != null;
            if ((GlobalManager.getInstance().getBitResource().getAmount() >= quarterBis.getBitCost()) && (GlobalManager.getInstance().getCodeDataResource().getAmount() >= quarterBis.getCodeDataCost()) && (GlobalManager.getInstance().getCryptoMoneyResource().getAmount() >= quarterBis.getCryptomoneyCost())) {
                GlobalManager.getInstance().getBitResource().subtractAmount(quarterBis.getBitCost());
                GlobalManager.getInstance().getCodeDataResource().subtractAmount(quarterBis.getCodeDataCost());
                GlobalManager.getInstance().getCryptoMoneyResource().subtractAmount(quarterBis.getCryptomoneyCost());
                toggleQuarter.selectToggle(null);
                quarterBis.setAirship(this);
                quarter.setAirship(this);
                quarterBis.setX(quarter.getX());
                quarterBis.setY(quarter.getY());
                GlobalManager.getInstance().setSelectedBuildQuarter(null);
                GlobalManager.getInstance().getConstructQuarterToggleGroup().selectToggle(null);
                quarterList[quarter.getX()][quarter.getY()] = quarterBis;
                quarterList[quarter.getX()][quarter.getY()].loadQuarterInfoPane();
            }
            else {
                GlobalManager.getInstance().setSelectedBuildQuarter(null);
                GlobalManager.getInstance().getConstructQuarterToggleGroup().selectToggle(null);
            }
        }
        else {
            GlobalManager.getInstance().setSelectedBuildQuarter(null);
            GlobalManager.getInstance().getConstructQuarterToggleGroup().selectToggle(null);
        }
    }



    //ok
    //Get a list of the four adjacent quarters of this quarter

    /**
     * Returns the list of every quarter adjacent to the considered quarter.
     * <br> If there is no quarter at one of the adjacent position, the corresponding cell in the list is null.
     * @param quarter the considered quarter
     * @return the list of the 4 adjacent quarters
     */
    public Quarter[] getAdjacent(Quarter quarter) {
        Quarter[] adjacentQuarter = new Quarter[4];
        int xPos = quarter.getX();
        int yPos = quarter.getY();

        if(quarterList[xPos+1][yPos] != null) {
            adjacentQuarter[0] = quarterList[xPos+1][yPos];
        }
        if(quarterList[xPos][yPos-1] != null) {
            adjacentQuarter[1] = quarterList[xPos][yPos-1];
        }
        if(quarterList[xPos-1][yPos] != null) {
            adjacentQuarter[2] = quarterList[xPos-1][yPos];
        }
        if (quarterList[xPos][yPos+1] != null) {
            adjacentQuarter[3] = quarterList[xPos][yPos+1];
        }
        return adjacentQuarter;
    }



    //Calculate the food bonuses


    /**
     * Computes the bonuses from the amount of available food in the ship. (implemented but not integrated yet)
     * <br> If the amount of food is low, the player face hard penalties on ths ship, while if it disposes of a great amount, he got some bonuses.
     * <br> The presence of a quarter Galley also increase the bonuses, as well as if the production is equilibrated between TemporalCaboose quarters and Birdcatcher quarters.
     */
    public void calculateFoodBonus() {
        int foodDiversityProductionBonus = 0;
        int foodQuantityProductionBonus = 0;
        int foodQuantityRecruitmentCost = 0;
        int foodQuantityConstructionCost = 0;
        int foodQuantityHealthBonus = 0;
        int foodQualityProductionBonus = 0;
        int foodQualityDamageBonus = 0;
        //Galley + diversity
        long temporalCabooseProduction=0;
        long birdCatcherProduction=0;
        for (Quarter[] iQuarter : quarterList) {
            for (Quarter jQuarter : iQuarter) {
                if (jQuarter instanceof Galley) {
                    foodQualityProductionBonus += (int) jQuarter.getProduction()[1];
                    foodQualityDamageBonus += (int) jQuarter.getProduction()[3];
                }
                else if (jQuarter instanceof TemporalCaboose) {
                    temporalCabooseProduction = jQuarter.getProduction()[1];
                }
                else if (jQuarter instanceof Birdcatcher) {
                    birdCatcherProduction = jQuarter.getProduction()[1];
                }
            }
        }
        if (foodQualityProductionBonus > 20) {
            foodQualityProductionBonus = 20;
        }
        if (foodQualityDamageBonus > 1) {
            foodQualityDamageBonus = 1;
        }
        if ((temporalCabooseProduction > 0) && (birdCatcherProduction > 0) && (temporalCabooseProduction > birdCatcherProduction/2) && (birdCatcherProduction > temporalCabooseProduction/2)) {
            foodDiversityProductionBonus = 10;
        }
        //Quantity
        if (localResourcesManager.getFoodResource().getAmount() <= -50) {
            foodQuantityProductionBonus = -60;
            foodQuantityRecruitmentCost = -50;
            foodQuantityConstructionCost = -40;
            foodQuantityHealthBonus = -2;
        } else if (localResourcesManager.getFoodResource().getAmount() > -50 && localResourcesManager.getFoodResource().getAmount() <= -30) {
            foodQuantityProductionBonus = -40;
            foodQuantityRecruitmentCost = -30;
            foodQuantityConstructionCost = -25;
            foodQuantityHealthBonus = -1;
        } else if (localResourcesManager.getFoodResource().getAmount() > -30 && localResourcesManager.getFoodResource().getAmount() <= -15) {
            foodQuantityProductionBonus = -20;
            foodQuantityRecruitmentCost = -10;
            foodQuantityConstructionCost = -10;
        } else if (localResourcesManager.getFoodResource().getAmount() > -15 && localResourcesManager.getFoodResource().getAmount() < 0) {
            foodQuantityProductionBonus = -10;
            foodQuantityConstructionCost = -5;
        } else if (localResourcesManager.getFoodResource().getAmount() >= 0 && localResourcesManager.getFoodResource().getAmount() <= 15) {
            foodQuantityProductionBonus = 5;
        } else if (localResourcesManager.getFoodResource().getAmount() > 15 && localResourcesManager.getFoodResource().getAmount() <= 30) {
            foodQuantityProductionBonus = 10;
            foodQuantityConstructionCost = 5;
        } else if (localResourcesManager.getFoodResource().getAmount() > 30 && localResourcesManager.getFoodResource().getAmount() <= 50) {
            foodQuantityProductionBonus = 15;
            foodQuantityConstructionCost = 10;
            foodQuantityRecruitmentCost = 10;
        } else if (localResourcesManager.getFoodResource().getAmount() > 50) {
            foodQuantityProductionBonus = 20;
            foodQuantityConstructionCost = 15;
            foodQuantityRecruitmentCost = 20;
            foodQuantityHealthBonus = 1;
        }
    }


    //Manage overconsumption of electricity by disabling or enabling quarter

    /**
     * Manages the over consumption of electricity by disabling random quarters. (implemented but not integrated yet)
     * <br> If at the end of a turn after having subtract the consumption of electricity of each quarter, the amount of remaining electricity is below 0, quarters are randomly disabled.
     * Each time a quarter is disabled, its electricity consumption is added to the amount of electricity of the ship. While the amount of electricity is below, the method continues to disabled more quarters.
     * Quarters that produces electricity can not be disabled.
     */
    public void manageElectricityOverconsumption() {
        if (localResourcesManager.getElectricityResource().getAmount() < 0) {
            while (localResourcesManager.getElectricityResource().getAmount() < 0) {
                int r1 = (int) abs(Math.random()*numberOfRaws);
                int r2 = (int) abs(Math.random()*numberOfColumns);
                if (quarterList[r1][r2] != null && quarterList[r1][r2].getElectricityConsumption() != 0 && quarterList[r1][r2].isEnable()) {
                    quarterList[r1][r2].disable();
                    int[] index = {r1, r2};
                    disabledQuarterList.add(index);
                    localResourcesManager.getElectricityResource().addAmount(quarterList[r1][r2].getElectricityConsumption());
                }
            }
        }
        else if (localResourcesManager.getElectricityResource().getAmount() > 0 && !disabledQuarterList.isEmpty()) {
            while (!disabledQuarterList.isEmpty()) {
                ArrayList<Integer> canEnableQuarterIndexList = new ArrayList<>();
                for (int i = 0; i < disabledQuarterList.size(); i++) {
                    if (quarterList[disabledQuarterList.get(i)[0]][disabledQuarterList.get(i)[1]].getElectricityConsumption() < localResourcesManager.getElectricityResource().getAmount()) {
                        canEnableQuarterIndexList.add(i);
                    }
                }
                int r = (int) abs(Math.random() * canEnableQuarterIndexList.size());
                quarterList[disabledQuarterList.get(canEnableQuarterIndexList.get(r))[0]][disabledQuarterList.get(canEnableQuarterIndexList.get(r))[1]].enable();
                disabledQuarterList.remove((int) canEnableQuarterIndexList.get(r));
            }
        }
    }



    //Calculate production for each quarter depending on the priority list

    /**
     * Computes the production for each quarter on the ship.
     * <br> In order to handle well the computation, the production is calculated in the order of the quarters inside the priority list.
     */
    public void updateProduction() {
        for (String priorityQuarterName : priorityList) {
            Quarter priorityQuarter = getQuarter(priorityQuarterName,1);
            for (Quarter[] iQuarter : quarterList) {
                for (Quarter jQuarter : iQuarter) {
                    if (jQuarter != null && priorityQuarter != null) {
                        if (jQuarter.getClass().equals(priorityQuarter.getClass())) {
                            for (Quarter adjacentQuarter : getAdjacent(jQuarter)) {
                                if (adjacentQuarter != null) {
                                    jQuarter.adjacentBonuses(adjacentQuarter);
                                }
                            }
                            jQuarter.baseCalculationProduction();
                        }
                    }
                }
            }
        }
    }


    //Getter

    /**
     *Returns the name of ship.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of the quarters of the ship.
     */
    public Quarter[][] getQuarterList() {
        return quarterList;
    }


    /**
     * Returns the local resources' manager.
     */
    public LocalResourcesManager getLocalResourcesManager() {
        return localResourcesManager;
    }

    /**
     * Returns the construction cost in bits of the ship.
     */
    public int getBitCost() {
        return bitCost;
    }

    /**
     * Returns the construction cost in data code of the ship.
     */
    public int getCodeDataCost() {
        return codeDataCost;
    }

    /**
     * Returns the construction cost in cryptomoney of the ship.
     */
    public int getCryptomoneyCost() {
        return cryptomoneyCost;
    }

    /**
     * Returns the construction cost in electricity of the ship.
     */
    public int getElectricityCost() {
        return electricityCost;
    }

    /**
     * Returns the construction cost in food of the ship.
     */
    public int getFoodCost() {
        return foodCost;
    }

    /**
     * Returns the construction cost in crew of the ship.
     */
    public int getCrewCost() {
        return crewCost;
    }

    /**
     * Returns the name and position of the first prebuild quarter.
     */
    public String[] getPrebuildQuarter1() {
        return prebuildQuarter1;
    }

    /**
     * Returns the name and position of the second prebuild quarter.
     */
    public String[] getPrebuildQuarter2() {
        return prebuildQuarter2;
    }

    /**
     * Returns the value of the property isDisplayed.
     */
    public boolean isDisplayed() {
        return isDisplayed;
    }

    /**
     * Sets the value of the property isDisplayed.
     */
    public void setDisplayed(boolean isDisplayed) {
        this.isDisplayed=isDisplayed;
    }





    //Fight getter

    /**
     * Returns the maximum value of the hull integrity of the ship.
     */
    public int getMaxHullIntegrity() {
        return maxHullIntegrity;
    }

    /**
     * Returns the maximum value of the shield of the ship.
     */
    public int getMaxShield() {
        return maxShield;
    }

    /**
     * Returns the hull integrity of the ship.
     */
    public int getHullIntegrity() {
        return hullIntegrity;
    }

    /**
     * Returns the armor rating of the hull of the ship.
     */
    public int getHullArmorRating() {
        return hullArmorRating;
    }

    /**
     * Returns the armor rating of the shield of the ship.
     */
    public int getShieldArmorRating() {
        return shieldArmorRating;
    }

    /**
     * Returns the speed of the ship.
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * Sets the value of the hull integrity.
     * @param hullIntegrity the new value of the hull integrity
     */
    public void setHullIntegrity(int hullIntegrity) {
        this.hullIntegrity = hullIntegrity;
    }

    /**
     * Returns the list of the weapons on this ship.
     */
    public ArrayList<Quarter> getWeaponsList() {
        return weaponsList;
    }
}
