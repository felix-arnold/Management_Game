package Quarter;

import General.Airship;
import General.GlobalManager;
import General.StaticThing;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

import static General.CsvFileUser.loadValue;


public abstract class Quarter {

    //Construction cost, consumption, upgrade cost, variable
    protected int level;
    protected int maxLevel;
    protected int crew;

    //Taille des tableaux à changer
    protected int maxCrew;
    protected int bitCost;
    protected int codeDataCost;
    protected int cryptomoneyCost;
    protected int electricityConsumption;
    protected int foodConsumption;
    protected int cyptomoneyConsumptionPerCrew;

    protected long[] production = new long[2];
    protected long[] productionPerCrew = new long[2];

    protected Pane infoPane = new Pane();

    protected String style;
    protected String selectedStyle;

    protected String name;
    protected String trueName;
    protected Label nameLabel;
    protected Label emptyLabel = new Label();
    protected Label bitCostLabel;
    protected Label cryptomoneyCostLabel;
    protected Label codeDataCostLabel;
    protected Label electricityConsumptionLabel;
    protected Label cryptomoneyConsumptionPerCrewLabel;
    protected Label maxCrewLabel;
    protected Label productionPerCrewLabel;
    protected Label levelLabel;

    protected int x;
    protected int y;

    protected boolean isUnlocked = true;

    protected boolean enabled = true;

    protected Airship airship;

    //Constructor
    public Quarter(int level) {
        this.level=level;
        constructionQuarterInfoPane.getStyleClass().add("quarterInfoPane");
        quarterInfoPane.getStyleClass().add("quarterInfoPane");
        row.setPrefHeight(32);
        row2.setPrefHeight(140);
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column1.setPercentWidth(8);
        column2.setPercentWidth(84);
        constructionQuarterInfoPane.getColumnConstraints().addAll(column1, column2,column1);
        quarterInfoPane.getColumnConstraints().addAll(column1, column2,column1);
    }

    Label crewLabel = new Label();
    IntegerProperty propertyCrew = new SimpleIntegerProperty();
    public IntegerProperty getPropertyCrew() {
        return propertyCrew;
    }


    protected final GridPane constructionQuarterInfoPane = new GridPane();
    public GridPane getConstructionQuarterInfoPane() {
        return constructionQuarterInfoPane;
    }
    protected final GridPane quarterInfoPane = new GridPane();
    public GridPane getQuarterInfoPane() {
        return quarterInfoPane;
    }

    protected RadioButton buildButton;


    protected final ToggleGroup buildToggleGroup = GlobalManager.getInstance().getConstructQuarterToggleGroup();

    protected final RowConstraints row = new RowConstraints();
    protected final RowConstraints row2 = new RowConstraints();

    protected ImageView quarterIcon;
    protected ImageView selectedQuarterIcon;

    public ImageView getFitterQuarterIcon(int width, int height) {
        quarterIcon.setFitWidth(width);
        quarterIcon.setFitHeight(height);
        return quarterIcon;
    }

    public ImageView getFitterSelectedQuarterIcon(int width, int height) {
        selectedQuarterIcon.setFitWidth(width);
        selectedQuarterIcon.setFitHeight(height);
        return selectedQuarterIcon;
    }


    protected int[] indexConstructionPane;
    public int[] getIndexConstructionPane() {
        return indexConstructionPane;
    }


    public void setAirship(Airship airship) {
        this.airship=airship;
    }

    public int getCrew() {
        return crew;
    }

    public int getLevel() {
        return level;
    }

    public void addCrew() {
        if (crew<maxCrew && airship.getLocalResourcesManager().getAvailableCrewResource().getAmount()>=1) {
            crew++;
            airship.getLocalResourcesManager().getAvailableCrewResource().subtractAmount(1);
        }
    }

    public void subCrew() {
        if (crew>0) {
            crew--;
            airship.getLocalResourcesManager().getAvailableCrewResource().addAmount(1);
        }
    }


    public void upgrade() {
        //RAJOUTER SI LOCK PAR TECH
        level++;
    }

    public long getCryptomoneyConsumption() {
        return 1;
    }


    public void setX(int xPos) {
        x=xPos;
    }

    public void setY(int yPos) {
        y=yPos;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Test functions
    public int getMaxCrew() {
        return maxCrew;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getCodeDataCost() {
        return codeDataCost;
    }

    public int getBitCost() {
        return bitCost;
    }

    public int getCryptomoneyCost() {
        return cryptomoneyCost;
    }

    public int getElectricityConsumption() {
        return electricityConsumption;
    }

    public String getStyle() {
        return style;
    }

    public String getSelectedStyle() {
        return selectedStyle;
    }

    public long[] getProduction() {
        return production;
    }

    public abstract void adjacentBonuses(Quarter adjQuarter);

    public String getName() {
        return name;
    }

    public int getFoodConsumption() {
        return foodConsumption;
    }

    public void unlock() {
        isUnlocked = true;
    }

    public void disable() {
        enabled = false;
    }

    public void enable() {
        enabled = true;
    }

    public boolean isEnable() {
        return enabled;
    }

    public boolean isUnlock(){
        return isUnlocked;
    }


    protected void loadConstructionInfoPaneValue() {

        nameLabel= new Label(name);
        nameLabel.getStyleClass().add("titleFont");
        nameLabel.setMinWidth(247);
        nameLabel.setAlignment(Pos.CENTER);
        constructionQuarterInfoPane.add(nameLabel,1,0);
        constructionQuarterInfoPane.getRowConstraints().add(row);

        constructionQuarterInfoPane.add(emptyLabel,1,1);
        constructionQuarterInfoPane.getRowConstraints().add(row);

        bitCostLabel = new Label("Bit cost:   " + bitCost);
        bitCostLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(bitCostLabel,1,2);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,2);

        codeDataCostLabel = new Label("Data code cost:   " + codeDataCost);
        codeDataCostLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(codeDataCostLabel,1,3);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,3);

        cryptomoneyCostLabel = new Label("Cryptomoney cost:   " + cryptomoneyCost);
        cryptomoneyCostLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(cryptomoneyCostLabel,1,4);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,4);

        electricityConsumptionLabel = new Label("Electricity consumption:   " + electricityConsumption);
        electricityConsumptionLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(electricityConsumptionLabel,1,5);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,5);

        cryptomoneyConsumptionPerCrewLabel = new Label("Salary per crew:   " + "PLACEHOLDER");
        cryptomoneyConsumptionPerCrewLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(cryptomoneyConsumptionPerCrewLabel,1,6);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,6);

        maxCrewLabel = new Label("Maximum crew:   " + maxCrew);
        maxCrewLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(maxCrewLabel,1,7);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,7);

        productionPerCrewLabel = new Label("Production per crew:   " + "PLACEHOLDER");
        productionPerCrewLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(productionPerCrewLabel,1,8);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,8);

        constructionQuarterInfoPane.getRowConstraints().add(row2);

        GridPane buildGridPane = new GridPane();
        buildButton = new RadioButton("Build");
        buildButton.getStyleClass().clear();
        buildButton.getStyleClass().add("buildButton");
        buildButton.setToggleGroup(buildToggleGroup);
        buildButton.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                buildButton.getStyleClass().clear();
                buildButton.getStyleClass().add("selectedBuildButton");
                GlobalManager.getInstance().setSelectedBuildQuarter(trueName);
            }
            else {
                buildButton.getStyleClass().clear();
                buildButton.getStyleClass().add("buildButton");
                GlobalManager.getInstance().setSelectedBuildQuarter(null);
            }
        });
        buildGridPane.add(buildButton,1,0);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPrefWidth(93.5);
        buildGridPane.getColumnConstraints().add(col3);
        buildButton.setPrefSize(60,40);
        constructionQuarterInfoPane.add(buildGridPane,1,10);
    }

    public void loadQuarterInfoPane() {

        quarterInfoPane.getChildren().clear();
        crewLabel.textProperty().bind(Bindings.concat("Crew:   ", propertyCrew.asString(), "/", maxCrew));

        nameLabel= new Label(name);
        nameLabel.getStyleClass().add("titleFont");
        nameLabel.setMinWidth(247);
        nameLabel.setAlignment(Pos.CENTER);
        quarterInfoPane.add(nameLabel,1,0);
        quarterInfoPane.getRowConstraints().add(row);

        quarterInfoPane.add(emptyLabel,1,1);
        quarterInfoPane.getRowConstraints().add(row);

        levelLabel = new Label("Level:   " + level+"/"+maxLevel);
        levelLabel.getStyleClass().add("normalFont");
        quarterInfoPane.add(levelLabel,1,2);
        quarterInfoPane.getRowConstraints().add(row);
        quarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,2);


        electricityConsumptionLabel = new Label("Electricity consumption:   " + electricityConsumption);
        electricityConsumptionLabel.getStyleClass().add("normalFont");
        quarterInfoPane.add(electricityConsumptionLabel,1,3);
        quarterInfoPane.getRowConstraints().add(row);
        quarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,3);

        cryptomoneyConsumptionPerCrewLabel = new Label("Salary per crew:   " + "PLACEHOLDER");
        cryptomoneyConsumptionPerCrewLabel.getStyleClass().add("normalFont");
        quarterInfoPane.add(cryptomoneyConsumptionPerCrewLabel,1,4);
        quarterInfoPane.getRowConstraints().add(row);
        quarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,4);

        crewLabel.getStyleClass().add("normalFont");
        quarterInfoPane.add(crewLabel,1,6);
        quarterInfoPane.getRowConstraints().add(row);
        quarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,6);

        productionPerCrewLabel = new Label("Production per crew:   " + production[1]);
        productionPerCrewLabel.getStyleClass().add("normalFont");
        quarterInfoPane.add(productionPerCrewLabel,1,5);
        quarterInfoPane.getRowConstraints().add(row);
        quarterInfoPane.add(new StaticThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,5);

        quarterInfoPane.getRowConstraints().add(row2);

        GridPane buildGridPane3 = new GridPane();
        RadioButton addCrew = new RadioButton("+");
        RadioButton subCrew = new RadioButton("-");
        addCrew.getStyleClass().clear();
        subCrew.getStyleClass().clear();
        addCrew.getStyleClass().add("button");
        subCrew.getStyleClass().add("button");
        addCrew.setPrefSize(25,25);
        subCrew.setPrefSize(25,25);
        addCrew.setOnAction((event) -> {
            addCrew();
            addCrew.setSelected(false);
        });
        subCrew.setOnAction((event) -> {
            subCrew();
            subCrew.setSelected(false);
        });
        buildGridPane3.add(addCrew,0,0);
        buildGridPane3.add(subCrew,2,0);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPrefWidth(20);
        buildGridPane3.getColumnConstraints().add(col3);
        buildGridPane3.getColumnConstraints().add(col3);
        buildGridPane3.getColumnConstraints().add(col3);
        quarterInfoPane.add(buildGridPane3,1,7);

        GridPane buildGridPane2 = new GridPane();
        RadioButton upgradeButton = new RadioButton("Upgrade");
        upgradeButton.getStyleClass().clear();
        upgradeButton.getStyleClass().add("buildButton");
        upgradeButton.setToggleGroup(buildToggleGroup);
        upgradeButton.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                upgradeButton.setDisable(true);
                upgradeButton.getStyleClass().clear();
                upgradeButton.getStyleClass().add("selectedBuildButton");
                PauseTransition upgradeTransition = new PauseTransition(Duration.seconds(0.5));
                upgradeTransition.setOnFinished(eventBis -> {
                    buildToggleGroup.selectToggle(null);
                    upgradeButton.setDisable(false);
                });
                upgradeTransition.play();
                airship.upgrade(this);
            }
            else {
                upgradeButton.setDisable(true);
                upgradeButton.getStyleClass().clear();
                upgradeButton.getStyleClass().add("wrongBuildButton");
                PauseTransition wrongUpgradeTransition = new PauseTransition(Duration.seconds(0.5));
                wrongUpgradeTransition.setOnFinished(eventBis -> {
                    buildToggleGroup.selectToggle(null);
                    upgradeButton.setDisable(false);
                });
                wrongUpgradeTransition.play();
                upgradeButton.getStyleClass().clear();
                upgradeButton.getStyleClass().add("buildButton");
            }
        });
        buildGridPane2.add(upgradeButton,1,0);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPrefWidth(93.5);
        buildGridPane2.getColumnConstraints().add(col4);
        upgradeButton.setPrefSize(60,40);
        quarterInfoPane.add(buildGridPane2,1,10);
    }


    public void setLevel(int level) {
        this.level = level;
    }

    public String getTrueName() {
        return trueName;
    }


    public void baseCalculationProduction() {}
}
