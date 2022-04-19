package Combat;

import Combat.Unit.FlyingUnit;
import Combat.Unit.Unit;
import Combat.Unit.Weapon;
import General.Airship;
import General.GlobalManager;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import jdk.management.jfr.RecordingInfo;

import javax.swing.*;
import java.io.FileReader;
import java.util.ArrayList;

import static java.lang.Math.*;

public class BombingCombatManager {

    private BombingCombatManager() {
        for (Airship iShip : GlobalManager.getInstance().getAirshipList()) {
            if(iShip != null) {
                availableShipList.add(new FightAirship(iShip,true));
            }
        }
    }

    private static final BombingCombatManager INSTANCE = new BombingCombatManager();

    public static BombingCombatManager getInstance() {
        return INSTANCE;
    }


    FightAirship[][] airshipBattlefield = new FightAirship[6][5];
    ArrayList<FightAirship> availableShipList = new ArrayList<>();

    public ArrayList<FightAirship> getAvailableShipList() {
        return availableShipList;
    }
    
    public FightAirship[][] getAirshipBattlefield() {
        return airshipBattlefield;
    }
    
    public void addAirshipBattlefield(FightAirship fa, int x, int y) {
        airshipBattlefield[x][y]=fa;
    }


    //Move the selected ship from the available list to the selected place in the airshipBattlefield, with
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
    public void retreatShip(FightAirship airship) {
        if (airship.canMove() && airship.getField() == 0) {
            availableShipList.add(airship);
            airshipBattlefield[0][airship.getPosition()] = null;
            airship.setField(-1);
            airship.hasMove();
        }
    }

    //Move a ship from airshipBattlefield to another empty place in the airshipBattlefield
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


    //Turn manager
    int turn = 0;

    public int getTurn() {
        return turn;
    }

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
                            weapon.drawCards(2);
                        }
                    }
                }
            }
        }
        turn++;
    }

    private final ToggleGroup weaponToggleGroup = new ToggleGroup();
    public ToggleGroup getWeaponToggleGroup() {
        return weaponToggleGroup;
    }

    private FightAirship selectedEnnemyShip;
    private FightAirship selectedAllyShip;
    public void setSelectedAllyShip(FightAirship selectedAllyShip) {
        this.selectedAllyShip = selectedAllyShip;
    }
    public void setSelectedEnnemyShip(FightAirship selectedEnnemyShip) {
        this.selectedEnnemyShip = selectedEnnemyShip;
    }
    public FightAirship getSelectedEnnemyShip() {
        return selectedEnnemyShip;
    }
    public FightAirship getSelectedAllyShip() {
        return selectedAllyShip;
    }


    RadioButton[][] airshipBattleButton = new RadioButton[6][5];
    RadioButton[][] getAirshipBattleButton() {
        return airshipBattleButton;
    }
}
