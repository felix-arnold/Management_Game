package General;

import Airship.ManOWar;
import General.StaticThing;
import Quarter.ProductionQuarter.Cryptomine;
import Quarter.ProductionQuarter.ParadoxalGenerator;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.SplitPaneSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import Quarter.Quarter;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Gamescene extends Scene {

    private AnimationTimer timer;

    private StaticThing backgroundLeft;
    private StaticThing backgroundRight;
    private StaticThing starsFix;
    private StaticThing cloudBottom1Left;
    private StaticThing cloudBottom1Right;
    private StaticThing cloudBottom2Left;
    private StaticThing cloudBottom2Right;
    private StaticThing cloudTop1Left;
    private StaticThing cloudTop1Right;
    private StaticThing cloudTop2Left;
    private StaticThing cloudTop2Right;


    private ManOWar ManOWar1;


    private long scienceValue=0;
    private IntegerProperty scienceNumber = new SimpleIntegerProperty();
    private Label scienceLabel = new Label();

    private long cryptomoneyValue=5000;
    private IntegerProperty cryptomoneyNumber = new SimpleIntegerProperty();
    private Label cryptomoneyLabel = new Label();

    private long bitValue=6000;
    private IntegerProperty bitNumber = new SimpleIntegerProperty();
    private Label bitLabel = new Label();

    private long codeDataValue=7000;
    private IntegerProperty codeDataNumber = new SimpleIntegerProperty();
    private Label codeDataLabel = new Label();

    private long electricityValue=8000;
    private IntegerProperty electricityNumber = new SimpleIntegerProperty();
    private Label electricityLabel = new Label();

    private long foodValue=9000;
    private IntegerProperty foodNumber = new SimpleIntegerProperty();
    private Label foodLabel = new Label();

    private long crewValue=20;
    private IntegerProperty crewNumber = new SimpleIntegerProperty();
    private Label crewLabel = new Label();

    private long[] ressourcesValue = {scienceValue, cryptomoneyValue, bitValue, codeDataValue, electricityValue, foodValue, crewValue};
    private IntegerProperty[] ressourcesNumber = {scienceNumber, cryptomoneyNumber, bitNumber, codeDataNumber, electricityNumber, foodNumber, crewNumber};
    private String[] ressourcesName = {"Science", "Cryptomoney", "Bits", "Code Datas", "Electricity", "Food", "Crew"};
    private Label[] ressourcesLabels = {scienceLabel, cryptomoneyLabel, bitLabel, codeDataLabel, electricityLabel, foodLabel, crewLabel};


    private Boolean constructionMenuIsDisplayed=FALSE;
    private Boolean commonRessourcesQuarterMenuIsDisplayed = FALSE;
    private Boolean constructionIsSelected = FALSE;
    private Boolean quarterIsSelected = FALSE;
    private Quarter constructionSelected;

    private long turn;


    public Gamescene(Group g) {
        super(g, 1540, 800);

        backgroundLeft = new StaticThing("BackgroundLayer.png", 0, 0, 1831, 800);
        backgroundRight = new StaticThing("BackgroundLayer.png", 1831, 0, 1831, 800);
        g.getChildren().add(backgroundLeft.getSprite());
        g.getChildren().add(backgroundRight.getSprite());

        starsFix = new StaticThing("StarsLayer.png", 0, 5, 1540, 193);
        g.getChildren().add(starsFix.getSprite());

        cloudBottom1Left = new StaticThing("CloudBottom1Layer.png", 0, 612, 1826, 188);
        cloudBottom1Right = new StaticThing("CloudBottom1Layer.png", 1826, 612, 1826, 188);
        g.getChildren().add(cloudBottom1Left.getSprite());
        g.getChildren().add(cloudBottom1Right.getSprite());

        cloudTop1Left = new StaticThing("CloudTop1Layer.png", 0, 120, 4538, 556);
        cloudTop1Right = new StaticThing("CloudTop1Layer.png", 4538, 120, 4538, 556);
        g.getChildren().add(cloudTop1Left.getSprite());
        g.getChildren().add(cloudTop1Right.getSprite());

        ManOWar1 = new ManOWar("ManOWar1");
        g.getChildren().add(ManOWar1.getImage().getSprite());

        cloudBottom2Left = new StaticThing("CloudBottom2Layer.png", 0, 238, 4790, 562);
        cloudBottom2Right = new StaticThing("CloudBottom2Layer.png", 4790, 238, 4790, 562);
        g.getChildren().add(cloudBottom2Left.getSprite());
        g.getChildren().add(cloudBottom2Right.getSprite());

        cloudTop2Left = new StaticThing("CloudTop2Layer.png", 0, 120, 5780, 433);
        cloudTop2Right = new StaticThing("CloudTop2Layer.png", 5780, 120, 5780, 433);
        g.getChildren().add(cloudTop2Left.getSprite());
        g.getChildren().add(cloudTop2Right.getSprite());


        //A METTRE DANS MANOWAR
        GridPane quarterDisplayPane = new GridPane();
        quarterDisplayPane.setLayoutX(363);
        quarterDisplayPane.setLayoutY(430);
        quarterDisplayPane.setHgap(3);
        quarterDisplayPane.setVgap(3);
        ToggleGroup toggleQuarter = new ToggleGroup();


        //Panneaux de menus

        GridPane menuePane = new GridPane();

        menuePane.setLayoutX(15);
        menuePane.setLayoutY(745);

        menuePane.setHgap(10);

        Button settingsButton = new Button();
        Button viewFleetButton = new Button();
        Button switchUnitShipViewButton = new Button();
        Button technologyTreeButton = new Button();
        Button constructionButton = new Button();

        settingsButton.setPrefSize(45, 45);
        viewFleetButton.setPrefSize(45, 45);
        switchUnitShipViewButton.setPrefSize(45, 45);
        technologyTreeButton.setPrefSize(45, 45);
        constructionButton.setPrefSize(45, 45);

        settingsButton.getStyleClass().add("settingsButton");
        viewFleetButton.getStyleClass().add("viewFleetButton");
        switchUnitShipViewButton.getStyleClass().add("switchUnitShipViewButton");
        technologyTreeButton.getStyleClass().add("technologyTreeButton");
        constructionButton.getStyleClass().add("constructionButton");

        menuePane.add(settingsButton, 0, 0);
        menuePane.add(viewFleetButton, 1, 0);
        menuePane.add(switchUnitShipViewButton, 2, 0);
        menuePane.add(technologyTreeButton, 3, 0);
        menuePane.add(constructionButton, 4, 0);

        g.getChildren().add(menuePane);


        //Panneaux menu construction

        GridPane menuConstructionPane = new GridPane();

        menuConstructionPane.getStyleClass().add("menuConstructionPane");

        menuConstructionPane.setPrefSize(420, 100);
        menuConstructionPane.setLayoutX(900);
        menuConstructionPane.setLayoutY(700);

        Button commonRessourcesQuarterButton = new Button("Commmon Ressources");
        Button shipRessourcesQuarterButton = new Button();
        Button scienceQuarterButton = new Button();
        Button unitQuarterButton = new Button();
        Button infrastructureQuarterButton = new Button();

        commonRessourcesQuarterButton.setPrefSize(60, 60);
        shipRessourcesQuarterButton.setPrefSize(60, 60);
        scienceQuarterButton.setPrefSize(60, 60);
        unitQuarterButton.setPrefSize(60, 60);
        infrastructureQuarterButton.setPrefSize(60, 60);

        menuConstructionPane.add(commonRessourcesQuarterButton, 0, 0);
        menuConstructionPane.add(shipRessourcesQuarterButton, 1, 0);
        menuConstructionPane.add(scienceQuarterButton, 2, 0);
        menuConstructionPane.add(unitQuarterButton, 3, 0);
        menuConstructionPane.add(infrastructureQuarterButton, 4, 0);


        //Panneaux menu construction ressources commmunes

        GridPane commonRessourceQuarterPane = new GridPane();

        commonRessourceQuarterPane.getStyleClass().add("commonRessourceQuarterPane");

        commonRessourceQuarterPane.setPrefSize(550, 140);
        commonRessourceQuarterPane.setLayoutX(835);
        commonRessourceQuarterPane.setLayoutY(560);

        RadioButton cryptomineButton = new RadioButton("Cryptomine");
        RadioButton iASyhnthesisTankButton = new RadioButton();
        RadioButton paradoxlGeneratorButton = new RadioButton("Paradoxal Generator");
        RadioButton temporalCambooseButton = new RadioButton();

        cryptomineButton.getStyleClass().remove("radio-button");
        cryptomineButton.getStyleClass().add("button");
        iASyhnthesisTankButton.getStyleClass().remove("radio-button");
        iASyhnthesisTankButton.getStyleClass().add("button");
        paradoxlGeneratorButton.getStyleClass().remove("radio-button");
        paradoxlGeneratorButton.getStyleClass().add("button");
        temporalCambooseButton.getStyleClass().remove("radio-button");
        temporalCambooseButton.getStyleClass().add("button");

        ToggleGroup toggleCommonRessources = new ToggleGroup();

        cryptomineButton.setToggleGroup(toggleCommonRessources);
        iASyhnthesisTankButton.setToggleGroup(toggleCommonRessources);
        paradoxlGeneratorButton.setToggleGroup(toggleCommonRessources);
        temporalCambooseButton.setToggleGroup(toggleCommonRessources);

        cryptomineButton.setPrefSize(100, 100);
        iASyhnthesisTankButton.setPrefSize(100, 100);
        paradoxlGeneratorButton.setPrefSize(100, 100);
        temporalCambooseButton.setPrefSize(100, 100);

        commonRessourceQuarterPane.add(cryptomineButton, 0, 0);
        commonRessourceQuarterPane.add(paradoxlGeneratorButton, 1, 0);
        commonRessourceQuarterPane.add(iASyhnthesisTankButton, 2, 0);
        commonRessourceQuarterPane.add(temporalCambooseButton, 3, 0);


        constructionButton.setOnAction((event) -> {
            if (constructionMenuIsDisplayed == FALSE) {
                g.getChildren().add(menuConstructionPane);
                constructionMenuIsDisplayed = TRUE;
            } else {
                g.getChildren().remove(menuConstructionPane);
                constructionMenuIsDisplayed = FALSE;
                if (commonRessourcesQuarterMenuIsDisplayed == TRUE) {
                    g.getChildren().remove(commonRessourceQuarterPane);
                    commonRessourcesQuarterMenuIsDisplayed = FALSE;
                }
            }
        });

        commonRessourcesQuarterButton.setOnAction((event) -> {
            if (commonRessourcesQuarterMenuIsDisplayed == FALSE) {
                g.getChildren().add(commonRessourceQuarterPane);
                commonRessourcesQuarterMenuIsDisplayed = TRUE;
            } else {
                g.getChildren().remove(commonRessourceQuarterPane);
                commonRessourcesQuarterMenuIsDisplayed = FALSE;
            }
        });

        cryptomineButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    constructionSelected = new Cryptomine();
                    constructionIsSelected = TRUE;
                } else {
                    constructionSelected = null;
                    constructionIsSelected = FALSE;
                }
            }
        });


        paradoxlGeneratorButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    constructionSelected = new ParadoxalGenerator();
                    constructionIsSelected = TRUE;
                } else {
                    constructionSelected = null;
                    constructionIsSelected = FALSE;
                }
            }
        });


        /*for (int i = 0; i < ManOWar1.getNumberQuarter(); i++) {
            RadioButton radioButton = new RadioButton();
            radioButton.setPrefSize(55, 28);
            radioButton.setToggleGroup(toggleQuarter);
            radioButton.getStyleClass().remove("radio-button");
            radioButton.getStyleClass().add("emptyQuarter");
            quarterDisplayPane.add(radioButton, ManOWar1.getPositionQuarter()[i][0], ManOWar1.getPositionQuarter()[i][1]);
            Pane infoPane = new Pane();
            infoPane.getStyleClass().add("infoPane");
            infoPane.setPrefSize(300,500);
            infoPane.setLayoutX(1240);
            infoPane.setLayoutY(100);
            int j = i;
            radioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> observable, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                    if (isNowSelected) {
                        addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                            public void handle(MouseEvent mouseEvent) {
                                toggleQuarter.selectToggle(null);
                            }
                        });
                        quarterIsSelected=TRUE;
                        g.getChildren().add(infoPane);
                        if (constructionIsSelected == TRUE && ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[j][1]][ManOWar1.getPositionQuarter()[j][0]] == null) {
                            radioButton.getStyleClass().clear();
                            radioButton.getStyleClass().add(constructionSelected.getSelectedStyle());
                            ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[j][1]][ManOWar1.getPositionQuarter()[j][0]] = constructionSelected;
                            construct(constructionSelected);
                            toggleCommonRessources.selectToggle(null);
                        } else if (constructionIsSelected == TRUE && ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[j][1]][ManOWar1.getPositionQuarter()[j][0]] != null) {
                            radioButton.getStyleClass().clear();
                            radioButton.getStyleClass().add("impossibleConstruction");
                            PauseTransition transition = new PauseTransition(Duration.seconds(0.5));
                            transition.setOnFinished(event -> radioButton.getStyleClass().clear());
                            transition.setOnFinished(event -> radioButton.getStyleClass().add(ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[j][1]][ManOWar1.getPositionQuarter()[j][0]].getStyle()));
                            transition.playFromStart();
                        } else if (ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[j][1]][ManOWar1.getPositionQuarter()[j][0]] != null) {
                            radioButton.getStyleClass().clear();
                            radioButton.getStyleClass().add(ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[j][1]][ManOWar1.getPositionQuarter()[j][0]].getSelectedStyle());
                        } else {
                            radioButton.getStyleClass().clear();
                            radioButton.getStyleClass().add("selectedQuarter");
                        }
                    } else if (ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[j][1]][ManOWar1.getPositionQuarter()[j][0]] != null) {
                        radioButton.getStyleClass().clear();
                        radioButton.getStyleClass().add(ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[j][1]][ManOWar1.getPositionQuarter()[j][0]].getStyle());
                        quarterIsSelected=FALSE;
                        g.getChildren().remove(infoPane);
                    } else {
                        radioButton.getStyleClass().clear();
                        radioButton.getStyleClass().add("emptyQuarter");
                        quarterIsSelected=FALSE;
                        g.getChildren().remove(infoPane);
                    }
                }
            });
        }*/
        g.getChildren().add(quarterDisplayPane);


        addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                    toggleCommonRessources.selectToggle(null);
                }
            }
        });




        for (int i = 0; i < 7; i++) {
            ressourcesNumber[i].setValue(ressourcesValue[i]);
            ressourcesLabels[i].textProperty().bind(Bindings.concat(ressourcesName[i], " : ", ressourcesNumber[i].asString()));
            g.getChildren().add(ressourcesLabels[i]);
            ressourcesLabels[i].setLayoutX(220 * i + 55);
            ressourcesLabels[i].setLayoutY(5);
            ressourcesLabels[i].setFont(new Font("Arial", 24));
            ressourcesLabels[i].setTextFill(Color.web("#FFFFFF"));
        }


        Button endTurnButton = new Button();
        endTurnButton.setLayoutX(1440);
        endTurnButton.setLayoutY(697);
        endTurnButton.getStyleClass().add("endTurnButton");
        StaticThing endTurnLayout = new StaticThing("EndTurnLayout.png", 1390, 600, 150, 200);
        g.getChildren().add(endTurnLayout.getSprite());
        g.getChildren().add(endTurnButton);


        timer = new AnimationTimer() {
            public void handle(long time) {
                ManOWar1.getImage().updateAnimated(time);
                valuesUpdate();
                render(time, g);
            }
        };

        timer.start();


        endTurnButton.setOnAction((event) -> {
            turn++;
            endturn();
            System.out.println("Turn : " + turn);
        });

    }

    public void endturn() {

        //A REVOIR

        /*for (int i = 0; i < ManOWar1.getNumberQuarter(); i++) {
            if (ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[i][1]][ManOWar1.getPositionQuarter()[i][0]]!=null) {
                if (ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[i][1]][ManOWar1.getPositionQuarter()[i][0]].getProductionType() == 2) {
                    cryptomoneyValue += ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[i][1]][ManOWar1.getPositionQuarter()[i][0]].getProduction();
                }
                if (ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[i][1]][ManOWar1.getPositionQuarter()[i][0]].getProductionType() == 1) {
                    electricityValue += ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[i][1]][ManOWar1.getPositionQuarter()[i][0]].getProduction();
                }
            }
        }*/




        /*for (int i = 0; i < ManOWar1.getNumberQuarter(); i++) {
            if (ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[i][1]][ManOWar1.getPositionQuarter()[i][0]]!=null) {
                electricityValue -= ManOWar1.getQuarterList()[ManOWar1.getPositionQuarter()[i][1]][ManOWar1.getPositionQuarter()[i][0]].getElectricityConsumption();
            }
        }*/

    }

    public void valuesUpdate() {

        ressourcesValue[0]=scienceValue;
        ressourcesValue[1]=cryptomoneyValue;
        ressourcesValue[2]=bitValue;
        ressourcesValue[3]=codeDataValue;
        ressourcesValue[4]=electricityValue;
        ressourcesValue[5]=foodValue;
        ressourcesValue[6]=crewValue;

        for (int i = 0; i < 7; i++) {
            ressourcesNumber[i].setValue(ressourcesValue[i]);
        }
    }

    public void render(long time, Group g) {

        backgroundLeft.updateBackground(1831, 2, 0);
        backgroundRight.updateBackground(1831, 2, 1);

        cloudBottom1Left.updateBackground(1826,6,0);
        cloudBottom1Right.updateBackground(1826,6,1);

        cloudTop1Left.updateBackground(4538,10,0);
        cloudTop1Right.updateBackground(4538,10,1);

        cloudBottom2Left.updateBackground(4790,18,0);
        cloudBottom2Right.updateBackground(4790,18,1);

        cloudTop2Left.updateBackground(5780,25,0);
        cloudTop2Right.updateBackground(5780,25,1);
    }

    //faut vraiment que je la mette pas la
    public void construct(Quarter constructionSelected){
        bitValue-=constructionSelected.getBitCost();
        codeDataValue-=constructionSelected.getCodeDataCost();
        cryptomoneyValue-=constructionSelected.getCryptomoneyCost();
    }
}

