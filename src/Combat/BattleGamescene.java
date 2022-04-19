package Combat;

import Combat.Unit.Weapon;
import General.Airship;
import General.StaticThing;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;


public class BattleGamescene extends Scene {

    private AnimationTimer timer;

    public BattleGamescene(Group g) {
        super(g);

        final StaticThing background = new StaticThing("battleBackground.png", 0, 0, 720, 405,1540, 800);
        g.getChildren().add(background.getSprite());

        final StaticThing linesOverlay = new StaticThing("linesOverlay.png", 210, 0, 3240, 2468,1120, 800);
        g.getChildren().add(linesOverlay.getSprite());

        BombingCombatManager.getInstance().getAirshipBattlefield()[0][1]=new FightAirship(new Airship("Junk"),true);
        BombingCombatManager.getInstance().getAirshipBattlefield()[0][1].getWeaponsList().add(new Weapon("Canon broadside", 3, BombingCombatManager.getInstance().getAirshipBattlefield()[0][1]));
        BombingCombatManager.getInstance().getAirshipBattlefield()[0][1].getWeaponsList().add(new Weapon("Carronade broadside", 3, BombingCombatManager.getInstance().getAirshipBattlefield()[0][1]));
        BombingCombatManager.getInstance().getAirshipBattlefield()[0][1].getWeaponsList().add(new Weapon("Swivel guns", 3, BombingCombatManager.getInstance().getAirshipBattlefield()[0][1]));
        BombingCombatManager.getInstance().getAirshipBattlefield()[0][1].loadWeaponDisplay();

        BombingCombatManager.getInstance().getAirshipBattlefield()[0][3]=new FightAirship(new Airship("Junk"),true);
        BombingCombatManager.getInstance().getAirshipBattlefield()[0][3].getWeaponsList().add(new Weapon("Mortar", 3, BombingCombatManager.getInstance().getAirshipBattlefield()[0][3]));
        BombingCombatManager.getInstance().getAirshipBattlefield()[0][3].getWeaponsList().add(new Weapon("Gun turret", 3, BombingCombatManager.getInstance().getAirshipBattlefield()[0][3]));
        BombingCombatManager.getInstance().getAirshipBattlefield()[0][3].loadWeaponDisplay();

        BombingCombatManager.getInstance().getAirshipBattlefield()[5][2]=new FightAirship(new Airship("Junk"),false);



        ToggleGroup airshipAllyButtonToggleGroup = new ToggleGroup();
        ToggleGroup airshipEnnemyButtonToggleGroup = new ToggleGroup();
        RadioButton[][] airshipBattleButton = new RadioButton[6][5];
        for (int i = 0; i<BombingCombatManager.getInstance().getAirshipBattlefield().length; i++) {
            for (int j = 0; j<BombingCombatManager.getInstance().getAirshipBattlefield()[i].length;j++) {
                if (BombingCombatManager.getInstance().getAirshipBattlefield()[i][j] != null) {
                    RadioButton radioButton = new RadioButton();
                    if (BombingCombatManager.getInstance().getAirshipBattlefield()[i][j].isAlly()) {
                        radioButton.setToggleGroup(airshipAllyButtonToggleGroup);
                    }
                    else {
                        radioButton.setToggleGroup(airshipEnnemyButtonToggleGroup);
                    }
                    radioButton.getStyleClass().clear();
                    radioButton.setGraphic(BombingCombatManager.getInstance().getAirshipBattlefield()[i][j].getSprite());
                    int k = i;
                    int l = j;
                    for (int m = 0; m<BombingCombatManager.getInstance().getAirshipBattlefield()[k][l].getWeaponButtonList().size();m++) {
                        int n=m;
                        BombingCombatManager.getInstance().getAirshipBattlefield()[k][l].getWeaponButtonList().get(m).selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
                            if (isNowSelected) {
                                g.getChildren().add(BombingCombatManager.getInstance().getAirshipBattlefield()[k][l].getWeaponsList().get(n).getDeck().getDeckPane());
                            } else {
                                g.getChildren().remove(BombingCombatManager.getInstance().getAirshipBattlefield()[k][l].getWeaponsList().get(n).getDeck().getDeckPane());
                            }
                        });
                        BombingCombatManager.getInstance().getAirshipBattlefield()[k][l].getWeaponsList().get(n).getDeck().getCloseButton().setOnAction((event) -> {
                            g.getChildren().remove(BombingCombatManager.getInstance().getAirshipBattlefield()[k][l].getWeaponsList().get(n).getDeck().getDeckPane());
                        });
                    }
                    radioButton.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
                        if (BombingCombatManager.getInstance().getAirshipBattlefield()[k][l].isAlly()) {
                            if (isNowSelected) {
                                g.getChildren().add(BombingCombatManager.getInstance().getAirshipBattlefield()[k][l].getWeaponPane());
                                BombingCombatManager.getInstance().setSelectedAllyShip(BombingCombatManager.getInstance().getAirshipBattlefield()[k][l]);
                            } else {
                                g.getChildren().remove(BombingCombatManager.getInstance().getAirshipBattlefield()[k][l].getWeaponPane());
                                BombingCombatManager.getInstance().setSelectedAllyShip(null);
                            }
                        }
                        else {
                            if (isNowSelected) {
                                BombingCombatManager.getInstance().setSelectedEnnemyShip(BombingCombatManager.getInstance().getAirshipBattlefield()[k][l]);
                            } else {
                                BombingCombatManager.getInstance().setSelectedEnnemyShip(null);
                            }
                        }
                    });
                    airshipBattleButton[i][j]=radioButton;
                }
            }
        }


        ColumnConstraints col = new ColumnConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row = new RowConstraints();
        col.setPrefWidth(122);
        row.setPrefHeight(140);
        row2.setPrefHeight(5);

        GridPane emptyAirshipButtonPane = new GridPane();
        emptyAirshipButtonPane.setLayoutY(120);
        emptyAirshipButtonPane.setLayoutX(54);
        emptyAirshipButtonPane.setHgap(100);
        emptyAirshipButtonPane.setVgap(66);
        for (int i = 0; i<6; i++) {
            for (int j = 0; j<5; j++) {
                RadioButton radioButton = new RadioButton();
                emptyAirshipButtonPane.add(radioButton, i, j);
                radioButton.getStyleClass().clear();
                radioButton.getStyleClass().add("emptyAirshipButton");
            }
        }
        g.getChildren().add(emptyAirshipButtonPane);

        GridPane airshipButtonPane = new GridPane();
        for (int i = 0; i<6; i++) {
            airshipButtonPane.getColumnConstraints().add(col);
        }
        for (int j = 0; j<5; j++) {
            airshipButtonPane.getRowConstraints().add(row2);
            airshipButtonPane.getRowConstraints().add(row2);
            airshipButtonPane.getRowConstraints().add(row);
        }
        airshipButtonPane.setLayoutY(25);
        airshipButtonPane.setLayoutX(62);
        airshipButtonPane.setHgap(128);
        for (int i = 0; i<6; i++) {
            for (int j = 0; j<5; j++) {
                if (airshipBattleButton[i][j] != null) {
                    airshipButtonPane.add(airshipBattleButton[i][j], i, 3*j+2);
                    airshipButtonPane.add(BombingCombatManager.getInstance().getAirshipBattlefield()[i][j].getHullBar(), i, 3*j+1);
                    airshipButtonPane.add(BombingCombatManager.getInstance().getAirshipBattlefield()[i][j].getShieldBar(), i, 3*j);
                }
            }
        }
        g.getChildren().add(airshipButtonPane);
        airshipButtonPane.setGridLinesVisible(true);



    }
}
