package Management.Quarter;

import Management.GeneralManagement.Airship;
import Management.GeneralManagement.NonAnimatedThing;
import Management.GeneralManagement.GlobalManager;
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

/**
 * This abstract class implements the basic properties and methods of every quarter.
 */
public abstract class Quarter {

    //Construction cost, consumption, upgrade cost, variable
    /**
     * Level of the quarter.
     */
    protected int level;
    /**
     * Maximum level of the quarter.
     */
    protected int maxLevel;
    /**
     * Number of crew member affected to the quarter.
     */
    protected int crew;

    /**
     * Maximum number of crew member affected to the quarter.
     */
    protected int maxCrew;
    /**
     * Construction cost in .
     */
    protected int bitCost;
    /**
     * Construction cost in data codes.
     */
    protected int codeDataCost;
    /**
     * Construction cost in cryptomoney.
     */
    protected int cryptomoneyCost;
    /**
     * Consumption per turn in electricity.
     */
    protected int electricityConsumption;
    /**
     * Consumption per turn in food.
     */
    protected int foodConsumption;
    /**
     * Consumption per crew member and per turn in food.
     */
    protected int foodConsumptionPerCrew;
    /**
     * Consumption per turn in cryptomoney.
     */
    protected int cyptomoneyConsumption;
    /**
     * Consumption per crew member and per turn in cryptomoney.
     */
    protected int cyptomoneyConsumptionPerCrew;


    /**
     * Production type and production of the quarter.
     */
    protected long[] production = new long[2];
    /**
     * Production type and production per crew member of the quarter.
     */
    protected long[] productionPerCrew = new long[2];

    /**
     * Pane warping the information of the quarter.
     */
    protected Pane infoPane = new Pane();


    /**
     * Displayed and nice name of the quarter.
     */
    protected String name;
    /**
     * Name of the quarter, used notably by the quarter factory.
     */
    protected String trueName;
    /**
     * Label used to display the name of the quarter.
     */
    protected Label nameLabel;
    /**
     * Label used to display the construction cost in bit of the quarter.
     */
    protected Label bitCostLabel;
    /**
     * Label used to display the construction cost in cryptomoney of the quarter.
     */
    protected Label cryptomoneyCostLabel;
    /**
     * Label used to display the construction cost in data code of the quarter.
     */
    protected Label codeDataCostLabel;
    /**
     * Label used to display the consumption in electricity of the quarter.
     */
    protected Label electricityConsumptionLabel;
    /**
     * Label used to display the cryptomoney consumption per crew member of the quarter.
     */
    protected Label cryptomoneyConsumptionPerCrewLabel;
    /**
     * Label used to display the maximum number of crew member of the quarter.
     */
    protected Label maxCrewLabel;
    /**
     * Label used to display the number and maximum number of crew member of the quarter.
     */
    protected Label crewLabel = new Label();
    /**
     * Label used to display the production per crew member of the quarter.
     */
    protected Label productionPerCrewLabel;
    /**
     * Label used to display the level and the maximum level of the quarter.
     */
    protected Label levelLabel;
    /**
     * Empty label used only to add an empty column to grid panes.
     */
    protected Label emptyLabel = new Label();

    /**
     * X coordinate of the quarter inside the quarterList of an airship.
     */
    protected int x;
    /**
     * Y coordinate of the quarter inside the quarterList of an airship.
     */
    protected int y;

    /**
     * Property of an enabled quarter.
     */
    protected boolean enabled = true;

    /**
     * Airship to which belongs the quarter.
     */
    protected Airship airship;

    /**
     * Sets the parameters of different properties of the new instance of a quarter child.
     * @param level the level of the quarter
     */
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

    /**
     * IntegerProperty of the number of crew member affected to the quarter, used to automatically update its displayed number when changed.
     */
    protected final IntegerProperty propertyCrew = new SimpleIntegerProperty();

    /**
     * Returns the IntegerProperty of the number of crew member affected to the quarter.
     */
    public IntegerProperty getPropertyCrew() {
        return propertyCrew;
    }

    /**
     * GridPane wrapping the construction information.
     */
    protected final GridPane constructionQuarterInfoPane = new GridPane();
    /**
     * Returns the GridPane wrapping the construction information.
     */
    public GridPane getConstructionQuarterInfoPane() {
        return constructionQuarterInfoPane;
    }

    /**
     * GridPane wrapping the information of the built quarter.
     */
    protected final GridPane quarterInfoPane = new GridPane();

    /**
     * Returns the GridPane wrapping the information of the built quarter.
     */
    public GridPane getQuarterInfoPane() {
        return quarterInfoPane;
    }

    /**
     * ToggleGroup of the "Upgrade" and "Build" buttons.
     */
    protected static final ToggleGroup buildToggleGroup = GlobalManager.getInstance().getConstructQuarterToggleGroup();

    /**
     * Row constraints used for the information panes.
     */
    protected final RowConstraints row = new RowConstraints();
    /**
     * Row constraints used for the information panes.
     */
    protected final RowConstraints row2 = new RowConstraints();

    /**
     * Icon of the quarter when unselected.
     */
    protected ImageView quarterIcon;
    /**
     * Icon the quarter when selected.
     */
    protected ImageView selectedQuarterIcon;

    /**
     * Returns the icon of the quarter when unselected respecting the size in arguments.
     * @param width the width of the icon
     * @param height the height of the icon
     */
    public ImageView getFitterQuarterIcon(int width, int height) {
        quarterIcon.setFitWidth(width);
        quarterIcon.setFitHeight(height);
        return quarterIcon;
    }
    /**
     * Returns the icon of the quarter when selected respecting the size in arguments.
     * @param width the width of the icon
     * @param height the height of the icon
     */
    public ImageView getFitterSelectedQuarterIcon(int width, int height) {
        selectedQuarterIcon.setFitWidth(width);
        selectedQuarterIcon.setFitHeight(height);
        return selectedQuarterIcon;
    }

    /**
     * Spot of the quarter's construction button, indicating, in that order, the construction menu, the X position inside the GridPane between 0 and 4, and the Y position inside the GridPane between 0 and 1.
     */
    protected int[] indexConstructionPane;

    /**
     * Returns the spot of the quarter's construction button
     */
    public int[] getIndexConstructionPane() {
        return indexConstructionPane;
    }

    /**
     * Sets the airship to which belongs the quarter.
     * @param airship
     */
    public void setAirship(Airship airship) {
        this.airship=airship;
    }

    /**
     * Returns the number of crew member affected to the quarter.
     */
    public int getCrew() {
        return crew;
    }

    /**
     * Returns the level of the quarter.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Adds one to the number of crew affected to the quarter, if it has not yet reached its maximum.
     */
    public void addCrew() {
        if (crew<maxCrew && airship.getLocalResourcesManager().getAvailableCrewResource().getAmount()>=1) {
            crew++;
            airship.getLocalResourcesManager().getAvailableCrewResource().subtractAmount(1);
        }
    }
    /**
     * Subtracts one to the number of crew affected to the quarter, if it has not yet reached 0.
     */
    public void subCrew() {
        if (crew>0) {
            crew--;
            airship.getLocalResourcesManager().getAvailableCrewResource().addAmount(1);
        }
    }

    /**
     * Returns the consumption in cryptomoney of the quarter.
     */
    public long getCryptomoneyConsumption() {
        return 1;
    }

    /**
     * Sets the X coordinate of the quarter in the quarterList of its airship.
     * @param xPos the X position of the quarter
     */
    public void setX(int xPos) {
        x=xPos;
    }
    /**
     * Sets the Y coordinate of the quarter in the quarterList of its airship.
     * @param yPos the Y position of the quarter
     */
    public void setY(int yPos) {
        y=yPos;
    }

    /**
     * Returns the X coordinate of the quarter in the quarterList of its airship.
     */
    public int getX() {
        return x;
    }
    /**
     * Returns the Y coordinate of the quarter in the quarterList of its airship.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the maximum level of the quarter.
     */
    public int getMaxLevel() {
        return maxLevel;
    }
    /**
     * Returns the of construction cost in data code the quarter.
     */
    public int getCodeDataCost() {
        return codeDataCost;
    }

    /**
     * Returns the construction cost in bits of the quarter.
     */
    public int getBitCost() {
        return bitCost;
    }

    /**
     * Returns the construction cost in cryptomoney of the quarter.
     */
    public int getCryptomoneyCost() {
        return cryptomoneyCost;
    }

    /**
     * Returns the consumption in electricity of the quarter.
     */
    public int getElectricityConsumption() {
        return electricityConsumption;
    }

    /**
     * Returns the production of the quarter.
     */
    public long[] getProduction() {
        return production;
    }


    /**
     * Computes the bonus inherited from some specific adjacent quarter.
     * @param adjQuarter an adjacent quarter
     */
    public abstract void adjacentBonuses(Quarter adjQuarter);

    /**
     * Returns the name of the quarter.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the consumption in food of the quarter.
     */
    public int getFoodConsumption() {
        return foodConsumption;
    }

    /**
     * Sets the property enabled of the quarter to false.
     */
    public void disable() {
        enabled = false;
    }
    /**
     * Sets the property enabled of the quarter to true.
     */
    public void enable() {
        enabled = true;
    }
    /**
     * Returns the property enabled of the quarter.
     */
    public boolean isEnable() {
        return enabled;
    }

    /**
     * Load the information pane for the construction of the quarter.
     * It indicated notably the name, the different construction costs and the maximum level, the maximum number of crew member, the consumptions and the production values once built.
     * The pane also presents the button to build the quarter, which once selected change the value of selectedQuarter in the GlobalManager to the TrueName of the quarter.
     */
    protected void loadConstructionInfoPaneValue() {

        RadioButton buildButton;
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
        constructionQuarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,2);

        codeDataCostLabel = new Label("Data code cost:   " + codeDataCost);
        codeDataCostLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(codeDataCostLabel,1,3);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,3);

        cryptomoneyCostLabel = new Label("Cryptomoney cost:   " + cryptomoneyCost);
        cryptomoneyCostLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(cryptomoneyCostLabel,1,4);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,4);

        electricityConsumptionLabel = new Label("Electricity consumption:   " + electricityConsumption);
        electricityConsumptionLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(electricityConsumptionLabel,1,5);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,5);

        cryptomoneyConsumptionPerCrewLabel = new Label("Salary per crew:   " + "PLACEHOLDER");
        cryptomoneyConsumptionPerCrewLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(cryptomoneyConsumptionPerCrewLabel,1,6);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,6);

        maxCrewLabel = new Label("Maximum crew:   " + maxCrew);
        maxCrewLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(maxCrewLabel,1,7);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,7);

        productionPerCrewLabel = new Label("Production per crew:   " + "PLACEHOLDER");
        productionPerCrewLabel.getStyleClass().add("normalFont");
        constructionQuarterInfoPane.add(productionPerCrewLabel,1,8);
        constructionQuarterInfoPane.getRowConstraints().add(row);
        constructionQuarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,8);

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
    /**
     * Load the information pane for the construction of the quarter.
     * It indicated notably the name, the level and maximum level, the different consumptions, the number of crew member and its maximum, the production values.
     * The pane also presents the button to upgrade the quarter.
     * */
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
        quarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,2);


        electricityConsumptionLabel = new Label("Electricity consumption:   " + electricityConsumption);
        electricityConsumptionLabel.getStyleClass().add("normalFont");
        quarterInfoPane.add(electricityConsumptionLabel,1,3);
        quarterInfoPane.getRowConstraints().add(row);
        quarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,3);

        cryptomoneyConsumptionPerCrewLabel = new Label("Salary per crew:   " + "PLACEHOLDER");
        cryptomoneyConsumptionPerCrewLabel.getStyleClass().add("normalFont");
        quarterInfoPane.add(cryptomoneyConsumptionPerCrewLabel,1,4);
        quarterInfoPane.getRowConstraints().add(row);
        quarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,4);

        crewLabel.getStyleClass().add("normalFont");
        quarterInfoPane.add(crewLabel,1,6);
        quarterInfoPane.getRowConstraints().add(row);
        quarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,6);

        productionPerCrewLabel = new Label("Production per crew:   " + production[1]);
        productionPerCrewLabel.getStyleClass().add("normalFont");
        quarterInfoPane.add(productionPerCrewLabel,1,5);
        quarterInfoPane.getRowConstraints().add(row);
        quarterInfoPane.add(new NonAnimatedThing("iconArrowSelectionT.png", 0, 0,1022, 777,20,15).getSprite(),0,5);

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

    /**
     * Returns the true name of the quarter.
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * Computes the production of the quarter.
     */
    public void baseCalculationProduction() {}
}
