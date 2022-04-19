package Quarter;

import General.StaticThing;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class DataFundry extends Quarter {

    public DataFundry(int level) {
        super(level);

        this.level=3;
        maxLevel=3;
        maxCrew=4;
        bitCost=1000;
        codeDataCost=0;
        cryptomoneyCost=1000;
        electricityConsumption=300;
        foodConsumption=1;
        cyptomoneyConsumptionPerCrew=20;

        name = "Data Fundry";
        trueName = "DataFundry";
        quarterIcon = new ImageView(new Image("dataFundryIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedDataFundryIcon.png"));
        indexConstructionPane = new int[]{4, 0, 0};




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

        productionPerCrewLabel = new Label("Production per crew:   " + "PLACEHOLDER");
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

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {

    }


}
