package Combat;

import Combat.Unit.FlyingUnit;
import Combat.Unit.Unit;
import General.Airship;
import General.GlobalManager;

import java.util.ArrayList;

import static java.lang.Math.*;

public class BombingCombatManager {

    private BombingCombatManager() {
        for (Airship iShip : GlobalManager.getInstance().getAirshipList()) {
            availableShip.add(new FightAirship(iShip));
        }
    }
    private static final BombingCombatManager INSTANCE = new BombingCombatManager();
    public static BombingCombatManager getInstance() {
        return INSTANCE;
    }


    FightAirship[][] airshipBattlefield = new FightAirship[6][5];
    FlyingUnit[][] flyingUnitBattlefield = new FlyingUnit[6][10];
    ArrayList<FightAirship> availableShip = new ArrayList<>();


    //Move the selected ship from the available list to the selected place in the airshipBattlefield, with
    public void moveAvailableShipToField(int indexShip, int position) {
        if (availableShip.get(indexShip).canMove() && airshipBattlefield[0][position] == null) {
            airshipBattlefield[0][position] = availableShip.get(indexShip);
            availableShip.remove(indexShip);
            airshipBattlefield[0][position].setField(0);
            airshipBattlefield[0][position].setPosition(position);
            airshipBattlefield[0][position].hasMove();
        }
    }

    //Retreat a ship if it is in the last raw
    public void retreatShip(FightAirship airship) {
        if (airship.canMove() && airship.getField()==0) {
            availableShip.add(airship);
            airshipBattlefield[0][airship.getPosition()] = null;
            airship.setField(-1);
            airship.hasMove();
        }
    }

    //Move a ship from airshipBattlefield to another empty place in the airshipBattlefield
    public void moveShip(FightAirship airship, int field, int position) {
        if (abs(airship.getField()-field) <= 1 && airshipBattlefield[field][position] == null && airship.canMove()) {
            airshipBattlefield[field][position] = airship;
            airshipBattlefield[airship.getField()][airship.getPosition()] = null;
            airship.setField(field);
            airship.setPosition(position);
            airship.hasMove();
        }
    }

    //Invert two ships on the airshipBattlefield
    public void invertShip(FightAirship airship1, FightAirship airship2){
        if (abs(airship1.getField()-airship2.getField()) <= 1 && airship1.canMove() && airship2.canMove()) {
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

    //Draw a card among the one available for the selected unit
    public ArrayList<ActionCard> drawCards(Unit unit, int numberOfCard) {
        ArrayList<ActionCard> actionCardList = new ArrayList<>();
            for (int i=0; i<numberOfCard; i++) {
                int r = toIntExact(round(floor(random()*unit.getActionCardsList().size())));
                actionCardList.add(unit.getActionCardsList().get(r));
            }
        return actionCardList;
    }
}