package Battle.GeneralBattle;

import Battle.Unit.Weapon;
import Management.GeneralManagement.Airship;
import Management.GeneralManagement.GlobalManager;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

import static java.lang.Math.*;
/**
 * This class manages most of the elements of the battles.
 */
public class BombingCombatManager {
    /**
     * Creates the unique instance of BombingCombatManager and copy the list of Airship from the management part to a list of FightAirship.
     * <br> Only one instance of BombingCombatManager needs to be created as it controls the whole battle part.
     * Thus, its access modifier is set to private as we want it to be a singleton.
     * <br> This should be changed when integrating with the other parts of the game as we need to create a new instance for each new battle.
     */
    private BombingCombatManager() {
        for (Airship iShip : GlobalManager.getInstance().getAirshipList()) {
            if(iShip != null) {
                availableShipList.add(new FightAirship(iShip,true));
            }
        }
    }


    /**
     * Instantiates the unique instance of GlobalManager
     */
    private static final BombingCombatManager INSTANCE = new BombingCombatManager();

    /**
     * Returns the instance of BombingCombatManager.
     */
    public static BombingCombatManager getInstance() {
        return INSTANCE;
    }


    /**
     * Matrix of the airships in the battlefield.
     */
    FightAirship[][] airshipBattlefield = new FightAirship[6][5];
    ArrayList<FightAirship> availableShipList = new ArrayList<>();

    /**
     * Returns the matrix of the airships in the battlefield.
     */
    public FightAirship[][] getAirshipBattlefield() {
        return airshipBattlefield;
    }


    //Move the selected ship from the available list to the selected place in the airshipBattlefield, with

    /**
     * Moves the selected airship from the reserves to the battlefield.
     * <br> The airship can only move to an empty place in the first field of the battlefield.
     * @param indexShip the index of the airship in the list of available airships.
     * @param position the position in the first field to which the airship will move
     */
    public void moveAvailableShipListToField(int indexShip, int position) {
        if (availableShipList.get(indexShip).canMove() && airshipBattlefield[0][position] == null) {
            airshipBattlefield[0][position] = availableShipList.get(indexShip);
            availableShipList.remove(indexShip);
            airshipBattlefield[0][position].setField(0);
            airshipBattlefield[0][position].setPosition(position);
            airshipBattlefield[0][position].hasMove();
        }
    }

    //Retreat a ship if it is in the last raw

    /**
     * Retreats the selected airship from the battlefield to the reserves.
     * <br> This action can only be performed if the airship is located in the first field of the battlefield.
     * @param airship the selected airship to move
     */
    public void retreatShip(FightAirship airship) {
        if (airship.canMove() && airship.getField() == 0) {
            availableShipList.add(airship);
            airshipBattlefield[0][airship.getPosition()] = null;
            airship.setField(-1);
            airship.hasMove();
        }
    }

    //Move a ship from airshipBattlefield to another empty place in the airshipBattlefield

    /**
     * Moves an airship in the battlefield to an empty place.
     * <br> This action can only be performed if the selected place is less than 1 field away from the airship initial place.
     * @param airship the airship to move
     * @param field the field to which the airship will move
     * @param position the position in the field to which the airship if moving
     */
    public void moveShip(FightAirship airship, int field, int position) {
        if (abs(airship.getField() - field) <= 1 && airshipBattlefield[field][position] == null && airship.canMove()) {
            airshipBattlefield[field][position] = airship;
            airshipBattlefield[airship.getField()][airship.getPosition()] = null;
            airship.setField(field);
            airship.setPosition(position);
            airship.hasMove();
        }
    }

    //Invert two ships on the airshipBattlefield

    /**
     * Inverts the position and field of two airships.
     * <br> This action can only happen if the two airships are separated at most by one field.
     * @param airship1 the first airship to move
     * @param airship2 the second airship to move
     */
    public void invertShip(FightAirship airship1, FightAirship airship2) {
        if (abs(airship1.getField() - airship2.getField()) <= 1 && airship1.canMove() && airship2.canMove()) {
            int field2 = airship2.getField();
            int position2 = airship2.getPosition();
            airshipBattlefield[airship1.getField()][airship1.getPosition()] = airship2;
            airship2.setField(airship1.getField());
            airship2.setPosition(airship1.getPosition());
            airshipBattlefield[field2][position2] = airship1;
            airship1.setField(field2);
            airship1.setPosition(position2);
            airship1.hasMove();
            airship2.hasMove();
        }
    }


    /**
     * Current turn of the battle.
     */
    int turn = 0;

    /**
     * Returns the current turn of the battle.
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Computes the next turn.
     * <br> The methods notably checks if the airships can move and if their weapon can realise an action.
     */
    public void nextTurn() {
        for (FightAirship availableShip : availableShipList) {
            availableShip.udpateCanMove(turn);
        }
        for (FightAirship[] field : airshipBattlefield) {
            for (FightAirship inBattleAirship : field) {
                if (inBattleAirship!=null) {
                    inBattleAirship.udpateCanMove(turn);
                    for (Weapon weapon : inBattleAirship.getWeaponsList()) {
                        weapon.udpateCanAction(turn);
                        if (weapon.canAction()) {
                            weapon.getDeck().newTurn();
                        }
                    }
                }
            }
        }
        turn++;
    }

    /**
     * Toggle group of the buttons representing the weapons.
     */
    private final ToggleGroup weaponToggleGroup = new ToggleGroup();

    /**
     * Returns the toggle group of the weapons' button.
     */
    public ToggleGroup getWeaponToggleGroup() {
        return weaponToggleGroup;
    }

    /**
     * Enemy FightAirship selected by the player.
     */
    private FightAirship selectedEnemyShip;
    /**
     * Allied FightAirship selected by the player.
     */
    private FightAirship selectedAllyShip;

    /**
     * Sets the selectedAllyShip property.
     * @param selectedAllyShip the new selectedAllyShip
     */
    public void setSelectedAllyShip(FightAirship selectedAllyShip) {
        this.selectedAllyShip = selectedAllyShip;
    }
    /**
     * Sets the selectedEnemyShip property.
     * @param selectedEnemyShip  the new selectedEnemyShip
     */
    public void setSelectedEnemyShip(FightAirship selectedEnemyShip) {
        this.selectedEnemyShip = selectedEnemyShip;
    }

    /**
     * Returns the selected enemy airship.
     */
    public FightAirship getSelectedEnemyShip() {
        return selectedEnemyShip;
    }

    /**
     * List of the button of the airships' places.
     */
    private final RadioButton[][] airshipBattleButton = new RadioButton[6][5];

    /**
     * Returns the lst of the button of the airships' places.
     */
    public RadioButton[][] getAirshipBattleButton() {
        return airshipBattleButton;
    }

    /**
     * Load the file of the music and launch it.
     * Once ended, the music restarts.
     */
    public void music(){
        MediaPlayer music;
        music = new MediaPlayer(new Media(new File("MusicBattle.mp3").toURI().toString()));
        music.setOnEndOfMedia(new Runnable() {
            public void run() {
                music.seek(Duration.ZERO);
            }
        });
        music.play();
    }
}
