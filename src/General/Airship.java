package General;

import Quarter.ProductionQuarter.*;
import Quarter.*;

import javafx.animation.PauseTransition;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import Combat.Unit.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static General.CsvFileUser.*;
import static Quarter.QuarterFactory.getQuarter;

public class Airship {

    private final String name;
    private final int numberOfRaws;
    private final int numberOfColumns;
    private final ArrayList<int[]> positionQuarter = new ArrayList<>();
    private Quarter[][] quarterList;
    private int numberQuarter;

    private final int bitCost;
    private final int codeDataCost;
    private final int cryptomoneyCost;
    private final int electricityCost;
    private final int foodCost;
    private final int crewCost;
    private final int constructionTime;
    private final String[] prebuildQuarter1;
    private final String[] prebuildQuarter2;

    protected LocalResourcesManager localResourcesManager = new LocalResourcesManager();

    int foodDiversityProductionBonus = 0;
    int foodQuantityProductionBonus = 0;
    int foodQuantityRecruitmentCost = 0;
    int foodQuantityConstructionCost = 0;
    int foodQuantityHealthBonus = 0;
    int foodQualityProductionBonus = 0;
    int foodQualityDamageBonus = 0;

    public boolean isDisplay=false;

    ArrayList<int[]> disabledQuarterList = new ArrayList<>();

    static final List<String[]> airshipData = new ArrayList<>();

    static final String[] prorityList = {"Berth","VirtualQuantumComputer", "MadScientist", "HellishBoss", "Cryptoinvestor", "DataCenter", "ProgrammersOffice", "Birdcatcher", "TemporalCaboose", "IASynthesisTank", "Galley", "DimensionlessSpace", "ParadoxalGenerator", "Cryptomine"};




    //Combat variables
    private final int maxHullIntegrity;
    private final int maxShield;
    private final int shieldArmorRating;
    private final int hullArmorRating;
    private final int speed;
    private int hullIntegrity;

    private ArrayList<Unit> unitList = new ArrayList<>();

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final int fitWidth;
    private final int fitHeight;
    private final int numberOfFrame;


    //Constructor
    public Airship(String name) {
        CsvFileUser.readCSV("AirshipData.csv", airshipData);
        this.name = name;
        bitCost = Integer.parseInt(loadValue(name, airshipData, "bitCost"));
        codeDataCost = Integer.parseInt(loadValue(name, airshipData, "codeDataCost"));
        cryptomoneyCost = Integer.parseInt(loadValue(name, airshipData, "cryptomoneyCost"));
        electricityCost = Integer.parseInt(loadValue(name, airshipData, "electricityCost"));
        foodCost = Integer.parseInt(loadValue(name, airshipData, "foodCost"));
        crewCost = Integer.parseInt(loadValue(name, airshipData, "crewCost"));
        constructionTime = Integer.parseInt(loadValue(name, airshipData, "bitCost"));
        numberOfRaws = Integer.parseInt(loadValue(name, airshipData, "numberOfRaws"));
        numberOfColumns = Integer.parseInt(loadValue(name, airshipData, "numberOfColumns"));

        maxHullIntegrity = Integer.parseInt(loadValue(name, airshipData, "maxHullIntegrity"));
        maxShield = Integer.parseInt(loadValue(name, airshipData, "maxShield"));
        shieldArmorRating = Integer.parseInt(loadValue(name,airshipData, "shieldArmorRating"));
        hullArmorRating = Integer.parseInt(loadValue(name,airshipData, "hullArmorRating"));
        speed = Integer.parseInt(loadValue(name, airshipData, "speed"));

        x = Integer.parseInt(loadValue(name, airshipData, "x"));
        y = Integer.parseInt(loadValue(name, airshipData, "y"));
        width = Integer.parseInt(loadValue(name, airshipData, "width"));
        height = Integer.parseInt(loadValue(name, airshipData, "height"));
        fitWidth = Integer.parseInt(loadValue(name, airshipData, "fitWidth"));
        fitHeight = Integer.parseInt(loadValue(name, airshipData, "fitHeight"));
        numberOfFrame = Integer.parseInt(loadValue(name, airshipData, "numberOfFrame"));
        image = new AnimatedThing(name+".png",x, y,width, height,numberOfFrame, fitWidth, fitHeight);

        quarterList = new Quarter[numberOfRaws][numberOfColumns];

        hullIntegrity=maxHullIntegrity;

        prebuildQuarter1 = loadValue(name, airshipData, "prebuildQuarter1").split("/");
        prebuildQuarter2 = loadValue(name, airshipData, "prebuildQuarter2").split("/");

        //load positionQuarter as an array of values
        for (int i=0; i<loadValue(name, airshipData, "positionQuarter").split("/").length; i++) {
            String[] positionQuarterBis=loadValue(name, airshipData, "positionQuarter").split("/")[i].split(",");
            assert false;
            positionQuarter.add(new int[]{Integer.parseInt(positionQuarterBis[0]), Integer.parseInt(positionQuarterBis[1])});
        }

        int xPane = Integer.parseInt(loadValue(name, airshipData, "xPane"));
        int yPane = Integer.parseInt(loadValue(name, airshipData, "yPane"));
        int widthQuarter = Integer.parseInt(loadValue(name, airshipData, "widthQuarter"));
        int heightQuarter = Integer.parseInt(loadValue(name, airshipData, "heightQuarter"));

        quarterInfoPane.setLayoutX(1230);

        quarterPane.getChildren().add(quarterDisplayPane);
        quarterPane.setLayoutX(100);
        quarterPane.setLayoutY(100);
        quarterDisplayPane.setHgap(3);
        quarterDisplayPane.setVgap(3);
        quarterDisplayPane.setLayoutX(xPane-100);
        quarterDisplayPane.setLayoutY(yPane-100);
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
                            if (constructQuarter(GlobalManager.getInstance().getSelectedBuildQuarter(), position[0], position[1], false, false) == 0) {
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
            quarterDisplayPane.add(radioButton, position[1], position[0]);
            k[0]++;
        }
        RowConstraints constraintHeight = new RowConstraints();
        ColumnConstraints constraintWidth = new ColumnConstraints();
        constraintHeight.setPrefHeight(heightQuarter);
        constraintWidth.setPrefWidth(widthQuarter);
        for (int i = 0; i <= numberOfRaws; i++) {
            quarterDisplayPane.getRowConstraints().add(constraintHeight);
        }
        for (int j = 0; j <= numberOfColumns; j++) {
            quarterDisplayPane.getColumnConstraints().add(constraintWidth);
        }
    }

    private int[] selectedQuarter = {-1,-1};
    public int[] getSelectedQuarter() {
        return selectedQuarter;
    }

    private final AnimatedThing image;
    public AnimatedThing getImage() {
        return image;
    }

    private final Pane quarterPane = new Pane();
    private final Pane quarterInfoPane = new Pane();
    private final GridPane quarterDisplayPane = new GridPane();

    public GridPane getQuarterDisplayPane() {
        return quarterDisplayPane;
    }

    ToggleGroup toggleQuarter = GlobalManager.getInstance().getQuarterToggleGroup();

    public Pane getQuarterPane() {
        return quarterPane;
    }

    public Pane getQuarterInfoPane() {
        return quarterInfoPane;
    }
    /////////////// ADD FOR UPGRADING

    //ok
    //Construct the building at the selected place
    public int constructQuarter(String quarterName, int xPos, int yPos, boolean resourcesException, boolean upgrade) {
        Quarter quarter = getQuarter(quarterName, 1);
        assert quarter != null;
        if ((quarterList[xPos][yPos]==null && GlobalManager.getInstance().getBitResource().getAmount() >= quarter.getBitCost()) && (GlobalManager.getInstance().getCodeDataResource().getAmount() >= quarter.getCodeDataCost()) && (GlobalManager.getInstance().getCryptoMoneyResource().getAmount() >= quarter.getCryptomoneyCost()) && !resourcesException) {
            GlobalManager.getInstance().getBitResource().subtractAmount(quarter.getBitCost());
            GlobalManager.getInstance().getCodeDataResource().subtractAmount(quarter.getCodeDataCost());
            GlobalManager.getInstance().getCryptoMoneyResource().subtractAmount(quarter.getCryptomoneyCost());
            quarter.setLevel(1);
            quarterList[xPos][yPos] = quarter;
            quarter.setAirship(this);
            quarter.setX(xPos);
            quarter.setY(yPos);
            numberQuarter++;
            GlobalManager.getInstance().setSelectedBuildQuarter(null);
            GlobalManager.getInstance().getConstructQuarterToggleGroup().selectToggle(null);
            return 0;
        }
        else if (quarterList[xPos][yPos]==null && resourcesException) {
            quarterList[xPos][yPos] = quarter;
            numberQuarter++;
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

    public void upgrade(Quarter quarter) {
        if (quarter.getLevel()<quarter.getMaxLevel()) {
            Quarter quarterBis = getQuarter(quarter.getTrueName(), quarter.getLevel()+1);
            while(quarter.getCrew()>0) {
                quarter.subCrew();
            }
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
    }



    //ok
    //Get a list of the four adjacent quarters of this quarter
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
    public void calculateFoodBonus() {
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
        else {
            foodDiversityProductionBonus = 0;
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
    /*public void manageElectricityOverconsumption() {
        if (localResourcesManager.getElectricityResource().getAmount() < 0) {
            while (localResourcesManager.getElectricityResource().getAmount() < 0) {
                int r1 = (int) abs(Math.random()*numberOfRaws);
                int r2 = (int) abs(Math.random()*numberOfColumns);
                if (quarterList[r1][r2] != null && quarterList[r1][r2].getElectricityConsumption() == 0 && quarterList[r1][r2].isEnable()) {
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
    }*/



    //Calculate production for each quarter depending on the priority list
    public void updateProduction() {
        for (String prorityQuarterName : prorityList) {
            Quarter priorityQuarter = getQuarter(prorityQuarterName,1);
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
    public String getName() {
        return name;
    }
    public Quarter[][] getQuarterList() {
        return quarterList;
    }
    public int getNumberQuarter() {
        return numberQuarter;
    }

    public ArrayList<int[]> getPositionQuarter() {
        return positionQuarter;
    }

    public LocalResourcesManager getLocalResourcesManager() {
        return localResourcesManager;
    }

    public int getBitCost() {
        return bitCost;
    }
    public int getCodeDataCost() {
        return codeDataCost;
    }
    public int getCryptomoneyCost() {
        return cryptomoneyCost;
    }
    public int getElectricityCost() {
        return electricityCost;
    }
    public int getFoodCost() {
        return foodCost;
    }
    public int getCrewCost() {
        return crewCost;
    }
    public int getConstructionTime() {
        return constructionTime;
    }
    public String[] getPrebuildQuarter1() {
        return prebuildQuarter1;
    }
    public String[] getPrebuildQuarter2() {
        return prebuildQuarter2;
    }

    public boolean isDisplay() {
        return isDisplay;
    }
    public void setDisplay(boolean isDisplay) {
        this.isDisplay=isDisplay;
    }





    //Fight getter
    public int getMaxHullIntegrity() {
        return maxHullIntegrity;
    }
    public int getMaxShield() {
        return maxShield;
    }
    public int getHullIntegrity() {
        return hullIntegrity;
    }
    public int getHullArmorRating() {
        return hullArmorRating;
    }
    public int getShieldArmorRating() {
        return shieldArmorRating;
    }
    public int getSpeed(){
        return speed;
    }
    public ArrayList<Unit> getUnitList(){
        return unitList;
    }


    public int getX() {
        return x;
    }
}
