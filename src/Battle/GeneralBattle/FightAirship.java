package Battle.GeneralBattle;

import Battle.Unit.Weapon;
import Management.GeneralManagement.Airship;
import Management.Quarter.Quarter;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * This class is used to create FightAirship which are copy of the airships from the management part.
 */
public class FightAirship {

    /**
     * Creates an instance of FightAirship by copying the properties useful for combat of the argument airship.
     * <br>
     * @param airship the original airship
     * @param ally the property of a FightAirship which the player controls
     */
    public FightAirship(Airship airship, boolean ally) {
        this.ally=ally;
        this.name=airship.getName();
        this.maxHullIntegrity = airship.getMaxHullIntegrity();
        this.maxShield = airship.getMaxShield();
        this.hullArmorRating = airship.getHullArmorRating();
        this.shieldArmorRating = airship.getShieldArmorRating();
        this.speed = airship.getSpeed();

        for (Quarter quarter : airship.getWeaponsList()) {
            weaponsList.add(new Weapon(quarter.getTrueName(), quarter.getLevel(), this));
        }

        this.hullIntegrity = airship.getHullIntegrity();
        this.shield = maxShield;

        Image preSprite= new Image(name+".png");
        sprite= new ImageView(preSprite);
        sprite.setViewport(new Rectangle2D(0,0,474,505));
        sprite.setFitWidth(122);
        sprite.setFitHeight(130);

        weaponPane.getStyleClass().clear();
        weaponPane.getStyleClass().add("weaponPane");
        weaponPane.setLayoutY(650);
        weaponPane.setLayoutX(100);

        moveButton.getStyleClass().clear();
        moveButton.getStyleClass().add("button");

        hullIntegrityLabel.getStyleClass().add("hullShield");
        hullIntegrityLabel.textProperty().bind(Bindings.concat("Hull Integrity:", hullIntegrityProperty.asString()));

        shieldIntegrityLabel.getStyleClass().add("hullShield");
        shieldIntegrityLabel.textProperty().bind(Bindings.concat("Shield:", shieldIntegrityProperty.asString()));

        shieldIntegrityLabel.setLayoutY(12);
    }


    /**
     * Name of the airship.
     */
    private final String name;
    /**
     * Maximum hull integrity, equivalent of the maximum health of the ship.
     */
    private final int maxHullIntegrity;
    /**
     * Maximum shield of the ship.
     */
    private final int maxShield;
    /**
     * Armor rating of the hull, decreasing the received damage.
     */
    private final int shieldArmorRating;
    /**
     * Armor rating of the shield, decreasing the received damage.
     */
    private final int hullArmorRating;
    /**
     * Speed of the airship.
     */
    private int speed;
    /**
     * Current hull integrity of the ship.
     */
    private int hullIntegrity;
    /**
     * Current shield of the ship
     */
    private int shield;
    /**
     * Field in which the airship can is located.
     */
    private int field = -1;
    /**
     * Position of the airship in the field.
     */
    private int position;
    /**
     * Health of the crew in percent.
     */
    private double crewHealth=100;

    /**
     * Property of an airship which can move.
     */
    private boolean canMove = true;
    /**
     * Turn of the last move from this airship.
     */
    private int turnPreviousMove;

    /**
     * List of weapons on this ship.
     */
    private final ArrayList<Weapon> weaponsList= new ArrayList<>();


    /**
     * Property of an airship which is destroyed.
     */
    private boolean destroyed=false;


    /**
     * Updates the property canMove of the airship.
     * <br> By comparing the current turn and the turn of the last move with the speed of this airship, the function decides each turn if the airship can realise a movement.
     * @param turn the current turn of the battle
     */
    public void udpateCanMove(int turn) {
        if (turn - turnPreviousMove >= speed) {
            canMove = true;
        }
    }

    /**
     * Node of this airship to display it.
     */
    ImageView sprite;

    /**
     * Returns the displayed node of this airship.
     */
    public ImageView getSprite() {
        return sprite;
    }

    /**
     * Returns the field in which this airship is located.
     */
    public int getField() {
        return field;
    }

    /**
     * Returns the position of this airship in the field.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Returns the maximum value of the hull integrity of the ship.
     */
    public int getMaxHullIntegrity() {
        return maxHullIntegrity;
    }

    /**
     * Returns the maximum value of the shield of the ship.
     */
    public int getMaxShield() {
        return maxShield;
    }

    /**
     * Returns the hull integrity of the ship.
     */
    public int getHullIntegrity() {
        return hullIntegrity;
    }

    /**
     * Returns the armor rating of the hull of the ship.
     */
    public int getHullArmorRating() {
        return hullArmorRating;
    }

    /**
     * Returns the armor rating of the shield of the ship.
     */
    public int getShieldArmorRating() {
        return shieldArmorRating;
    }

    /**
     * Returns the property canMove of this airship.
     */
    public boolean canMove() {
        return canMove;
    }

    /**
     * Sets the property canMove of this airship to false.
     */
    public void hasMove() {
        canMove = false;
    }

    /**
     * Increases (or decreases) the hull integrity of this airship by the argument amount.
     * @param hullIntegrity the additional amount
     */
    public void adjustHullIntegrity(double hullIntegrity) {
        this.hullIntegrity += hullIntegrity;
    }

    /**
     * Increases (or decreases) the shield of this airship by the argument amount.
     * @param shield  the additional amount
     */
    public void adjustShield(double shield) {
        this.shield += shield;
    }

    /**
     * Sets the field to which belongs the airship.
     * @param field the new field of this airship
     */
    public void setField(int field) {
        this.field = field;
    }

    /**
     * Sets the position in the field to which belongs the airship.
     * @param position the new position of this airship
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     Increases (or decreases) the health of the crew of this airship by the argument amount.
     * @param crewHealth the additional amount
     */
    public void adjustCrewHealth(double crewHealth) {
        this.crewHealth+=crewHealth;
    }

    /**
     * Returns the list of the weapons belonging to this airship.
     */
    public ArrayList<Weapon> getWeaponsList() {
        return weaponsList;
    }


    //If the airship is ignited
    private int numberOfFireStart = 0;
    public int getNumberOfFireStart() {
        return numberOfFireStart;
    }
    public void ignite(int newFireStarts) {
        numberOfFireStart+=newFireStarts;
    }

    /**
     * Returns the shield value of this airship.
     */
    public int getShield() {
        return shield;
    }

    private int vulnerabilities=0;
    public int getVulnerabilities() {
        return vulnerabilities;
    }
    public void changeVulnerability(int amount) {
        vulnerabilities+=amount;
    }


    public void slow(double slowAmount) {
        speed-=slowAmount;
    }

    public void loadWeaponDisplay() {
        weaponPane.add(moveButton,0,0);
        for (int i = 0; i<weaponsList.size();i++)  {
            weaponPane.add(weaponsList.get(i).getWeaponButton(),i+1,0);
            weaponButtonList.add(weaponsList.get(i).getWeaponButton());
        }
    }

    private final GridPane weaponPane = new GridPane();
    public GridPane getWeaponPane() {
        return weaponPane;
    }

    private final ArrayList<RadioButton> weaponButtonList = new ArrayList<>();
    public ArrayList<RadioButton> getWeaponButtonList() {
        return weaponButtonList;
    }

    /**
     * Property of an airship controlled by the player.
     */
    boolean ally;

    /**
     * Returns the property ally of this airship.
     */
    public boolean isAlly() {
        return ally;
    }

    /**
     * Button used to move this airship.
     */
    private final RadioButton moveButton = new RadioButton("Move");

    /**
     * Returns the button used to move this airship.
     */
    public RadioButton getMoveButton() {
        return moveButton;
    }

    /**
     * Integer property of the hull integrity of this airship, used to display its value in real time.
     */
    private final IntegerProperty hullIntegrityProperty = new SimpleIntegerProperty();
    /**
     * Label used to display the hull integrity of this airship.
     */
    private final Label hullIntegrityLabel = new Label();
    /**
     * Integer property of the shield integrity of this airship, used to display its value in real time.
     */
    private final IntegerProperty shieldIntegrityProperty = new SimpleIntegerProperty();
    /**
     * Label used to display the shield integrity of this airship.
     */
    private final Label shieldIntegrityLabel = new Label();

    /**
     * Returns the integer property of the hull integrity.
     */
    public IntegerProperty getHullIntegrityProperty() {
        return hullIntegrityProperty;
    }

    /**
     * Returns the label displaying the hull integrity of this airship.
     */
    public Label getHullIntegrityLabel() {
        return hullIntegrityLabel;
    }

    /**
     * Returns the label displaying the shield integrity of this airship.
     */
    public Label getShieldIntegrityLabel() {
        return shieldIntegrityLabel;
    }

    /**
     * Returns the integer property of the shield integrity.
     */
    public IntegerProperty getShieldIntegrityProperty() {
        return shieldIntegrityProperty;
    }

}
