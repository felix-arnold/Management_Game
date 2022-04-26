package Management.GeneralManagement;

import Management.Quarter.Quarter;

import static Management.GeneralManagement.Airship.getToggleQuarter;
import static Management.Quarter.QuarterFactory.getQuarter;

import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * This class is responsible for the display and some implementations of the interface of the management part.
 */
public class ManagementGamescene extends Scene {


    /**
     * General background.
     */
    private final NonAnimatedThing backgroundLeft;
    /**
     * Twin of the general background.
     */
    private final NonAnimatedThing backgroundRight;
    /**
     * First bottom layer of clouds of the background.
     */
    private final NonAnimatedThing cloudBottom1Left;
    /**
     * Twin of the first bottom layer of clouds of the background.
     */
    private final NonAnimatedThing cloudBottom1Right;
    /**
     * First top layer of clouds of the background.
     */
    private final NonAnimatedThing cloudTop1Left;
    /**
     * Twin of the first top cloud layer of the background.
     */
    private final NonAnimatedThing cloudTop1Right;
    /**
     * Second bottom layer of clouds of the background.
     */
    private final NonAnimatedThing cloudBottom2Left;
    /**
     * Twin of the second bottom cloud layer of the background.
     */
    private final NonAnimatedThing cloudBottom2Right;
    /**
     * Second top layer of clouds of the background.
     */
    private final NonAnimatedThing cloudTop2Left;
    /**
     * Twin of the second top cloud layer of the background.
     */
    private final NonAnimatedThing cloudTop2Right;

    /**
     * Index of the ship currently display in the airshipList in GlobalManager.
     */
    private int indexShipDisplay;


    /**
     * Array of GridPane which will contains the 5 panes displaying the buttons of buildable quarter.
     */
    private static final GridPane[] constructionsPane = new GridPane[5];


    /**
     * Displays and implements the different elements of the interface.
     * <br><br> This constructor manages the following elements:
     * <br> - The numerous parts of the background
     * <br> - The end turn button
     * <br> - The display in real time of the different resources
     * <br> - The buttons to change the displayed airship and its animation
     * <br> - The interface of the quarters' construction menu and its animation
     * <br> - The interface to build new airships
     * <br> - The animation timer to keep updated the resources amount, the number of crew per airship and the animation of the background and airships.
     * @param g the node contains the list of every rendered children
     */
    public ManagementGamescene(Group g) {
        super(g, 1540, 800);

        backgroundLeft = new NonAnimatedThing("BackgroundLayer.png", 0, 0, 1831, 800,1831, 800);
        backgroundRight = new NonAnimatedThing("BackgroundLayer.png", 1831, 0, 1831, 800,1831, 800);
        g.getChildren().add(backgroundLeft.getSprite());
        g.getChildren().add(backgroundRight.getSprite());

        NonAnimatedThing starsFix = new NonAnimatedThing("StarsLayer.png", 0, 5, 1540, 193,1540, 193);
        g.getChildren().add(starsFix.getSprite());

        cloudBottom1Left = new NonAnimatedThing("CloudBottom1Layer.png", 0, 612, 1826, 188,1826, 188);
        cloudBottom1Right = new NonAnimatedThing("CloudBottom1Layer.png", 1826, 612, 1826, 188,1826, 188);
        g.getChildren().add(cloudBottom1Left.getSprite());
        g.getChildren().add(cloudBottom1Right.getSprite());

        cloudTop1Left = new NonAnimatedThing("CloudTop1Layer.png", 0, 120, 4538, 556,4538, 556);
        cloudTop1Right = new NonAnimatedThing("CloudTop1Layer.png", 4538, 120, 4538, 556,4538, 556);
        g.getChildren().add(cloudTop1Left.getSprite());
        g.getChildren().add(cloudTop1Right.getSprite());

        cloudBottom2Left = new NonAnimatedThing("CloudBottom2Layer.png", 0, 238, 4790, 562,4790, 562);
        cloudBottom2Right = new NonAnimatedThing("CloudBottom2Layer.png", 4790, 238, 4790, 562,4790, 562);
        g.getChildren().add(cloudBottom2Left.getSprite());
        g.getChildren().add(cloudBottom2Right.getSprite());

        cloudTop2Left = new NonAnimatedThing("CloudTop2Layer.png", 0, 120, 5780, 433,5780, 433);
        cloudTop2Right = new NonAnimatedThing("CloudTop2Layer.png", 5780, 120, 5780, 433,5780, 433);
        g.getChildren().add(cloudTop2Left.getSprite());
        g.getChildren().add(cloudTop2Right.getSprite());


        GlobalManager.getInstance().getAirshipList()[1] = new Airship("Man'o'war");
        GlobalManager.getInstance().getAirshipList()[0] = new Airship("Frigate");
        GlobalManager.getInstance().setNumberOfShip(2);

        g.getChildren().add(GlobalManager.getInstance().getAirshipList()[0].getImage().getSprite());
        GlobalManager.getInstance().getAirshipList()[0].setDisplayed(true);


        cloudBottom2Left.getSprite().toFront();
        cloudBottom2Right.getSprite().toFront();
        cloudTop2Left.getSprite().toFront();
        cloudTop2Right.getSprite().toFront();

        NonAnimatedThing endTurnBackground = new NonAnimatedThing("endTurnBackground.png", 1400, 660, 947, 946,140,140);
        Button endTurnButton = new Button();


        Pane changeShipPane = new Pane();
        Pane resourcesPane = new Pane();

        GridPane menuConstructionPane = new GridPane();


        ToggleGroup menuConstructionPaneToggleGroup = new ToggleGroup();

        RadioButton buildAirshipButton = new RadioButton("Build Airship");

        g.getChildren().add(changeShipPane);


        g.getChildren().add(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getQuarterPane());
        g.getChildren().add(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getQuarterInfoPane());

        g.getChildren().add(resourcesPane);

        NonAnimatedThing resourcesLayout = new NonAnimatedThing("resourcesLayout.png", 0, 0, 4400, 205, 1138, 53);
        resourcesPane.getChildren().add(resourcesLayout.getSprite());

        IntegerProperty scienceNumber = new SimpleIntegerProperty();
        Label scienceLabel = new Label();
        IntegerProperty cryptomoneyNumber = new SimpleIntegerProperty();
        Label cryptomoneyLabel = new Label();
        IntegerProperty bitNumber = new SimpleIntegerProperty();
        Label bitLabel = new Label();
        IntegerProperty codeDataNumber = new SimpleIntegerProperty();
        Label codeDataLabel = new Label();
        IntegerProperty electricityNumber = new SimpleIntegerProperty();
        Label electricityLabel = new Label();
        IntegerProperty foodNumber = new SimpleIntegerProperty();
        Label foodLabel = new Label();
        IntegerProperty crewNumber = new SimpleIntegerProperty();
        IntegerProperty availableCrewNumber = new SimpleIntegerProperty();
        Label crewLabel = new Label();

        IntegerProperty[] globalResouresNumbers = {scienceNumber, bitNumber, codeDataNumber, cryptomoneyNumber};
        IntegerProperty[] localResourcesNumbers = {electricityNumber, foodNumber, availableCrewNumber, crewNumber};
        Label[] globalResouresLabel = {scienceLabel, bitLabel, codeDataLabel, cryptomoneyLabel};
        Label[] localResourcesLabels = {electricityLabel, foodLabel, crewLabel};

        for (int i=0; i<4; i++) {
            globalResouresLabel[i].textProperty().bind(Bindings.concat(globalResouresNumbers[i].asString()));
            resourcesPane.getChildren().add(globalResouresLabel[i]);
            globalResouresLabel[i].setLayoutX(147 * i + 96);
            globalResouresLabel[i].getStyleClass().add("resourcesNumber");
            globalResouresLabel[i].setAlignment(Pos.CENTER_RIGHT);
        }
        for (int i=0; i<3; i++) {
            localResourcesLabels[i].textProperty().bind(Bindings.concat(localResourcesNumbers[i].asString()));
            resourcesPane.getChildren().add(localResourcesLabels[i]);
            localResourcesLabels[i].setLayoutX(147 * i + 705);
            localResourcesLabels[i].getStyleClass().add("resourcesNumber");
            localResourcesLabels[i].setAlignment(Pos.CENTER_RIGHT);
        }
        localResourcesLabels[2].textProperty().bind(Bindings.concat(localResourcesNumbers[2].asString(), "/", localResourcesNumbers[3].asString()));

        Button[] resourcesName = new Button [7];
        Label[] resourcesInfo = new Label [7];
        for (int i=0; i<7; i++) {
            resourcesName[i]=new Button();
            if (i<4) {
                resourcesName[i].setLayoutX(147 * i + 72);
                GlobalManager.getInstance().getGlobalResources()[i].getResourceInfo().setLayoutX(146.7*i+71);
                GlobalManager.getInstance().getGlobalResources()[i].getResourceInfo().setLayoutY(39);
                resourcesInfo[i]=GlobalManager.getInstance().getGlobalResources()[i].getResourceInfo();
            }
            else {
                resourcesName[i].setLayoutX(147 * i + 93);
                GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getLocalResourcesManager().getResourcesList()[i-4].getResourceInfo().setLayoutX(146.7 * i + 92.5);
                GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getLocalResourcesManager().getResourcesList()[i-4].getResourceInfo().setLayoutY(39);
                resourcesInfo[i]=GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getLocalResourcesManager().getResourcesList()[i-4].getResourceInfo();
            }
            resourcesName[i].setLayoutY(12);
            resourcesName[i].setPrefSize(124,26);
            resourcesName[i].getStyleClass().remove("button");
            resourcesPane.getChildren().add(resourcesName[i]);


            int finalI = i;

            PauseTransition displayResourceInfoTransition = new PauseTransition(Duration.seconds(0.2));
            resourcesName[i].setOnMouseEntered((event) -> {
                displayResourceInfoTransition.setOnFinished((eventBis) -> resourcesPane.getChildren().add(resourcesInfo[finalI]));
                displayResourceInfoTransition.playFromStart();
            });
            resourcesName[i].setOnMouseExited((event) -> {
                displayResourceInfoTransition.stop();
                if (resourcesInfo[finalI].isVisible()) {
                    resourcesPane.getChildren().remove(resourcesInfo[finalI]);
                }
            });
        }








        Button nextShip = new Button();
        Button previousShip = new Button();
        nextShip.setLayoutX(1460);
        previousShip.setLayoutX(30);
        nextShip.setLayoutY(470);
        previousShip.setLayoutY(470);
        nextShip.getStyleClass().add("arrowNextShip");
        previousShip.getStyleClass().add("arrowPreviousShip");
        changeShipPane.getChildren().add(nextShip);
        changeShipPane.getChildren().add(previousShip);


        final BooleanProperty onAnimation = new SimpleBooleanProperty(false);
        TranslateTransition rightTransition = new TranslateTransition();
        TranslateTransition rightTransition2 = new TranslateTransition();

        nextShip.setOnAction((event) -> {

            if (!onAnimation.get()) {

                GlobalManager.getInstance().getConstructQuarterToggleGroup().selectToggle(null);
                menuConstructionPaneToggleGroup.selectToggle(null);
                GlobalManager.getInstance().getConstructionSubmenuToggleGroup().selectToggle(null);
                getToggleQuarter().selectToggle(null);

                g.getChildren().remove(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getQuarterPane());
                g.getChildren().remove(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getQuarterInfoPane());
                rightTransition.setNode(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());
                rightTransition.setDuration(Duration.seconds(0.5));
                rightTransition.setByX(-1500);
                rightTransition.setAutoReverse(true);

                rightTransition.setOnFinished(eventRightTransition -> {
                    g.getChildren().remove(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());
                    GlobalManager.getInstance().getAirshipList()[indexShipDisplay].setDisplayed(false);
                    if (indexShipDisplay==11 || GlobalManager.getInstance().getAirshipList()[++indexShipDisplay] == null ) {
                        indexShipDisplay = 0;
                    }
                    GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite().setTranslateX(1500);
                    g.getChildren().add(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());

                    cloudBottom2Left.getSprite().toFront();
                    cloudBottom2Right.getSprite().toFront();
                    cloudTop2Left.getSprite().toFront();
                    cloudTop2Right.getSprite().toFront();
                    changeShipPane.toFront();
                    resourcesPane.toFront();
                    menuConstructionPane.toFront();
                    endTurnBackground.getSprite().toFront();
                    endTurnButton.toFront();
                    buildAirshipButton.toFront();
                    GlobalManager.getInstance().getInfoBisPane().toFront();
                    GlobalManager.getTechManager().getTechPane().toFront();

                    rightTransition2.setDuration(Duration.seconds(0.5));
                    rightTransition2.setAutoReverse(true);
                    rightTransition2.setNode(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());
                    rightTransition2.setByX(-1500);
                    rightTransition2.setOnFinished(eventRightTransition2 -> {
                        GlobalManager.getInstance().getAirshipList()[indexShipDisplay].setDisplayed(true);
                        g.getChildren().add(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getQuarterPane());
                        g.getChildren().add(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getQuarterInfoPane());
                        onAnimation.setValue(false);
                    });
                    rightTransition2.play();
                });
                onAnimation.setValue(true);
                rightTransition.play();
            }
        });


        TranslateTransition leftTransition = new TranslateTransition();
        TranslateTransition leftTransition2 = new TranslateTransition();

        previousShip.setOnAction((event) -> {

            GlobalManager.getInstance().getConstructQuarterToggleGroup().selectToggle(null);
            menuConstructionPaneToggleGroup.selectToggle(null);
            GlobalManager.getInstance().getConstructionSubmenuToggleGroup().selectToggle(null);
            getToggleQuarter().selectToggle(null);

            if (!onAnimation.get()) {

                g.getChildren().remove(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getQuarterPane());
                g.getChildren().remove(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getQuarterInfoPane());

                leftTransition.setNode(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());
                leftTransition.setDuration(Duration.seconds(0.5));
                leftTransition.setByX(1500);
                leftTransition.setAutoReverse(true);

                leftTransition.setOnFinished(eventRightTransition -> {
                    g.getChildren().remove(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());
                    GlobalManager.getInstance().getAirshipList()[indexShipDisplay].setDisplayed(false);
                    if (--indexShipDisplay==-1) {
                        indexShipDisplay=11;
                    }
                    while (GlobalManager.getInstance().getAirshipList()[indexShipDisplay]==null) {
                        indexShipDisplay--;
                    }
                    GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite().setTranslateX(-1500);
                    g.getChildren().add(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());

                    cloudBottom2Left.getSprite().toFront();
                    cloudBottom2Right.getSprite().toFront();
                    cloudTop2Left.getSprite().toFront();
                    cloudTop2Right.getSprite().toFront();
                    changeShipPane.toFront();
                    resourcesPane.toFront();
                    menuConstructionPane.toFront();
                    endTurnBackground.getSprite().toFront();
                    endTurnButton.toFront();
                    buildAirshipButton.toFront();
                    GlobalManager.getInstance().getInfoBisPane().toFront();
                    GlobalManager.getTechManager().getTechPane().toFront();

                    leftTransition2.setDuration(Duration.seconds(0.5));
                    leftTransition2.setAutoReverse(true);
                    leftTransition2.setNode(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());
                    leftTransition2.setByX(1500);
                    leftTransition2.setOnFinished(eventRightTransition2 -> {
                        GlobalManager.getInstance().getAirshipList()[indexShipDisplay].setDisplayed(true);
                        g.getChildren().add(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getQuarterPane());
                        g.getChildren().add(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getQuarterInfoPane());
                        onAnimation.setValue(false);
                    });
                    leftTransition2.play();
                });
                onAnimation.setValue(true);
                leftTransition.play();
            }
        });







        g.getChildren().add(endTurnBackground.getSprite());


        endTurnButton.setLayoutX(1385);
        endTurnButton.setLayoutY(675);
        endTurnButton.getStyleClass().clear();
        endTurnButton.getStyleClass().add("endTurnButton");
        g.getChildren().add(endTurnButton);

        endTurnButton.setOnAction((event) -> {
            endTurnButton.setDisable(true);
            endTurnButton.getStyleClass().clear();
            endTurnButton.getStyleClass().add("selectedEndTurnButton");
            PauseTransition endTurnTransition = new PauseTransition(Duration.seconds(1.5));
            endTurnTransition.setOnFinished(eventBis -> {
                endTurnButton.getStyleClass().clear();
                endTurnButton.getStyleClass().add("endTurnButton");
                endTurnButton.setDisable(false);
            });
            endTurnTransition.playFromStart();
            GlobalManager.getInstance().nextTurn();
        });



        g.getChildren().add(menuConstructionPane);
        endTurnBackground.getSprite().toFront();
        endTurnButton.toFront();
        menuConstructionPane.setLayoutX(1030);
        menuConstructionPane.setLayoutY(720);
        menuConstructionPane.getStyleClass().add("menuConstructionPane");

        RadioButton[] constructionButtons = new RadioButton[5];
        for (int i=0; i<5; i++) {
            constructionButtons[i] = new RadioButton();
            menuConstructionPane.add(constructionButtons[i],i,0);
            constructionButtons[i].setToggleGroup(menuConstructionPaneToggleGroup);
            constructionButtons[i].getStyleClass().clear();
        }

        constructionButtons[0].getStyleClass().add("productionQuartersButton");
        constructionButtons[1].getStyleClass().add("advancedQuartersButton");
        constructionButtons[2].getStyleClass().add("facilityQuartersButton");
        constructionButtons[3].getStyleClass().add("masterQuartersButton");
        constructionButtons[4].getStyleClass().add("recruitmentQuartersButton");

        g.getChildren().add(GlobalManager.getInstance().getInfoBisPane());



        for (int i=0; i<5; i++) {
            constructionsPane[i] = new GridPane();
            constructionsPane[i].getStyleClass().add("constructionPane");
            constructionsPane[i].setLayoutX(1016.5+70*i);
            constructionsPane[i].setLayoutY(716);
            constructionsPane[i].setHgap(7);
            constructionsPane[i].setVgap(7);
            constructionsPane[i].setMinSize(109, 203);
        }

        constructionMenuDisplay();



        //CREER METHODE POUR SIMPLIFIER TOUT CA
        TranslateTransition constructionPaneTransition = new TranslateTransition();
        constructionPaneTransition.setByY(-195);
        constructionPaneTransition.setDuration(Duration.seconds(0.15));

        NonAnimatedThing arrowSelection = new NonAnimatedThing("iconArrowSelected.png", 1063, 716,738, 983,15,19);

        constructionButtons[0].selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            constructionButtons[0].getStyleClass().clear();
            if (isNowSelected) {
                constructionButtons[0].getStyleClass().add("selectedProductionQuartersButton");
                g.getChildren().add(constructionsPane[0]);
                menuConstructionPane.toFront();
                endTurnBackground.getSprite().toFront();
                endTurnButton.toFront();
                constructionPaneTransition.setNode(constructionsPane[0]);
                constructionPaneTransition.playFromStart();
                arrowSelection.getSprite().setTranslateX(0);
                g.getChildren().add(arrowSelection.getSprite());
                GlobalManager.getInstance().getConstructionSubmenuToggleGroup().selectToggle(null);
            } else {
                constructionButtons[0].getStyleClass().add("productionQuartersButton");
                constructionPaneTransition.stop();
                g.getChildren().remove(constructionsPane[0]);
                g.getChildren().remove(arrowSelection.getSprite());
                constructionsPane[0].setTranslateY(0);
            }
        });

        constructionButtons[1].selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            constructionButtons[1].getStyleClass().clear();
            if (isNowSelected) {
                constructionButtons[1].getStyleClass().add("selectedAdvancedQuartersButton");
                g.getChildren().add(constructionsPane[1]);
                menuConstructionPane.toFront();
                endTurnBackground.getSprite().toFront();
                endTurnButton.toFront();
                constructionPaneTransition.setNode(constructionsPane[1]);
                constructionPaneTransition.playFromStart();
                arrowSelection.getSprite().setTranslateX(70);
                g.getChildren().add(arrowSelection.getSprite());
                GlobalManager.getInstance().getConstructionSubmenuToggleGroup().selectToggle(null);
            } else {
                constructionButtons[1].getStyleClass().add("advancedQuartersButton");
                constructionPaneTransition.stop();
                g.getChildren().remove(constructionsPane[1]);
                g.getChildren().remove(arrowSelection.getSprite());
                constructionsPane[1].setTranslateY(0);
            }
        });

        constructionButtons[2].selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            constructionButtons[2].getStyleClass().clear();
            if (isNowSelected) {
                constructionButtons[2].getStyleClass().add("selectedFacilityQuartersButton");
                g.getChildren().add(constructionsPane[2]);
                menuConstructionPane.toFront();
                endTurnBackground.getSprite().toFront();
                endTurnButton.toFront();
                constructionPaneTransition.setNode(constructionsPane[2]);
                constructionPaneTransition.playFromStart();
                arrowSelection.getSprite().setTranslateX(140);
                g.getChildren().add(arrowSelection.getSprite());
                GlobalManager.getInstance().getConstructionSubmenuToggleGroup().selectToggle(null);
            } else {
                constructionButtons[2].getStyleClass().add("facilityQuartersButton");
                constructionPaneTransition.stop();
                g.getChildren().remove(constructionsPane[2]);
                g.getChildren().remove(arrowSelection.getSprite());
                constructionsPane[2].setTranslateY(0);
            }
        });

        constructionButtons[3].selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            constructionButtons[3].getStyleClass().clear();
            if (isNowSelected) {
                constructionButtons[3].getStyleClass().add("selectedMasterQuartersButton");
                g.getChildren().add(constructionsPane[3]);
                menuConstructionPane.toFront();
                endTurnBackground.getSprite().toFront();
                endTurnButton.toFront();
                constructionPaneTransition.setNode(constructionsPane[3]);
                constructionPaneTransition.playFromStart();
                arrowSelection.getSprite().setTranslateX(210);
                g.getChildren().add(arrowSelection.getSprite());
                GlobalManager.getInstance().getConstructionSubmenuToggleGroup().selectToggle(null);
            } else {
                constructionButtons[3].getStyleClass().add("masterQuartersButton");
                constructionPaneTransition.stop();
                g.getChildren().remove(constructionsPane[3]);
                g.getChildren().remove(arrowSelection.getSprite());
                constructionsPane[3].setTranslateY(0);
            }
        });

        constructionButtons[4].selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            constructionButtons[4].getStyleClass().clear();
            if (isNowSelected) {
                constructionButtons[4].getStyleClass().add("selectedRecruitmentQuartersButton");
                g.getChildren().add(constructionsPane[4]);
                menuConstructionPane.toFront();
                endTurnBackground.getSprite().toFront();
                endTurnButton.toFront();
                constructionPaneTransition.setNode(constructionsPane[4]);
                constructionPaneTransition.playFromStart();
                arrowSelection.getSprite().setTranslateX(280);
                g.getChildren().add(arrowSelection.getSprite());
                GlobalManager.getInstance().getConstructionSubmenuToggleGroup().selectToggle(null);
            } else {
                constructionButtons[4].getStyleClass().add("recruitmentQuartersButton");
                constructionPaneTransition.stop();
                g.getChildren().remove(constructionsPane[4]);
                g.getChildren().remove(arrowSelection.getSprite());
                constructionsPane[4].setTranslateY(0);
            }
        });


        g.getChildren().add(GlobalManager.getTechManager().getTechPane());


        buildAirshipButton.setLayoutX(20);
        buildAirshipButton.setLayoutY(740);
        buildAirshipButton.getStyleClass().clear();
        buildAirshipButton.getStyleClass().add("button");
        buildAirshipButton.setToggleGroup(menuConstructionPaneToggleGroup);
        buildAirshipButton.setPrefHeight(50);

        GridPane buildAirshipPane = new GridPane();
        buildAirshipPane.setLayoutY(660);

        buildAirshipButton.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                g.getChildren().add(buildAirshipPane);
            }
            else {
                g.getChildren().remove(buildAirshipPane);
            }
        });
        ToggleGroup buildAirshipToggleGroup = new ToggleGroup();
        String[] airshipAll = {"Sloop", "Corvette","Man'o'war","Schooner","Fluyt","Brig","Frigate","Longskip","Junk" };
        int m = airshipAll.length;
        for (int i =3; i>0;i--) {
            for (int j=3; j>0; j--) {
                m--;
                if (GlobalManager.getInstance().getUnlockedAirships().contains(airshipAll[m])) {
                    RadioButton radioButton = new RadioButton(airshipAll[m]);
                    radioButton.setMinWidth(72);
                    radioButton.setToggleGroup(buildAirshipToggleGroup);
                    radioButton.getStyleClass().clear();
                    radioButton.getStyleClass().add("button");
                    buildAirshipPane.add(radioButton, j, i);
                    int n = m;
                    radioButton.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
                        if (isNowSelected) {
                            GlobalManager.getInstance().constructAirship(airshipAll[n], GlobalManager.getInstance().getAirshipList()[indexShipDisplay]);
                            buildAirshipToggleGroup.selectToggle(null);
                        }
                    });
                }
            }
        }

        g.getChildren().add(buildAirshipButton);


        AnimationTimer timer = new AnimationTimer() {
            public void handle(long time) {
                airshipAnimation(time);
                render();
                for (int i = 0; i < 4; i++) {
                    globalResouresNumbers[i].setValue(GlobalManager.getInstance().getGlobalResources()[i].getAmount());
                }
                for (int i = 0; i < 4; i++) {
                    localResourcesNumbers[i].setValue(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getLocalResourcesManager().getResourcesList()[i].getAmount());
                }

                for (Airship iShip : GlobalManager.getInstance().getAirshipList()) {
                    if (iShip != null) {
                        for (Quarter[] iQuarter : iShip.getQuarterList()) {
                            if (iQuarter != null) {
                                for (Quarter jQuarter : iQuarter) {
                                    if (jQuarter != null) {
                                        jQuarter.getPropertyCrew().setValue(jQuarter.getCrew());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        };
        timer.start();
    }


    /**
     * Calls the updateAnimated method for the airship being currently display.
     * @param time the timestamp of the current frame given in nanoseconds
     */
    public void airshipAnimation(long time) {
        for (Airship airship : GlobalManager.getInstance().getAirshipList()) {
            if (airship!=null && airship.isDisplayed()) {
                airship.getImage().updateAnimated(time);
            }
        }
    }


    /**
     * Calls the update method of each non-static part of the background.
     */
    public void render() {

        backgroundLeft.updateBackground(1831, 2, false);
        backgroundRight.updateBackground(1831, 2, true);

        cloudBottom1Left.updateBackground(1826,6,false);
        cloudBottom1Right.updateBackground(1826,6,true);

        cloudTop1Left.updateBackground(4538,10,false);
        cloudTop1Right.updateBackground(4538,10,true);

        cloudBottom2Left.updateBackground(4790,18,false);
        cloudBottom2Right.updateBackground(4790,18,true);

        cloudTop2Left.updateBackground(5780,25,false);
        cloudTop2Right.updateBackground(5780,25,true);
    }


    /**
     * Displays the buttons to build new quarters.
     * <br> This method is called when loading the game but also each time a new technology has been researched. Thus, the pane must be cleared before executing the rest of the method.
     * <br> For each button in the unlockedQuarters list in the GlobalManager, it adds a button to the constructionsPane at the position in the class of the quarter.
     * When this button is selected, it displays the pane of information for its construction.
     * */
    public static void constructionMenuDisplay() {
        for (Pane pane : constructionsPane) {
            pane.getChildren().clear();
        }
        for (String quarterName : GlobalManager.getInstance().getAllQuartersList()) {
            Quarter quarter = getQuarter(quarterName,1);
            for (Quarter unlockedQuarter : GlobalManager.getInstance().getUnlockedQuarters()) {
                if (quarter.getClass() == unlockedQuarter.getClass()) {
                    RadioButton constructionQuarterButton = new RadioButton();
                    constructionQuarterButton.getStyleClass().clear();
                    constructionQuarterButton.setPrefSize(40, 40);
                    constructionQuarterButton.setToggleGroup(GlobalManager.getInstance().getConstructionSubmenuToggleGroup());
                    constructionQuarterButton.setGraphic(quarter.getFitterQuarterIcon(40, 40));
                    constructionsPane[quarter.getIndexConstructionPane()[0]].add(constructionQuarterButton, quarter.getIndexConstructionPane()[1], quarter.getIndexConstructionPane()[2]);

                    constructionQuarterButton.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
                        if (isNowSelected) {
                            getToggleQuarter().selectToggle(null);
                            GlobalManager.getInstance().getConstructQuarterToggleGroup().selectToggle(null);
                            constructionQuarterButton.setGraphic(quarter.getFitterSelectedQuarterIcon(40, 40));
                            GlobalManager.getInstance().getConstructQuarterToggleGroup().selectToggle(null);
                            GlobalManager.getInstance().getInfoBisPane().getChildren().add(quarter.getConstructionQuarterInfoPane());
                        } else {
                            constructionQuarterButton.setGraphic(quarter.getFitterQuarterIcon(40, 40));
                            GlobalManager.getInstance().getInfoBisPane().getChildren().remove(quarter.getConstructionQuarterInfoPane());
                        }
                    });
                }
            }
        }
    }
}
