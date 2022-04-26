package Battle.GeneralBattle;

import org.w3c.dom.Element;

/**
 * This class regroups the properties and methods of action cards used by weapons.
 */
public class WeaponActionCard extends ActionCard {

    /**
     * Creates a new instance of action card and load its properties.
     * @param dataXML the file containing the attributes and elements of the action cards of a weapon
     * @param index the index of the action card in dataXML
     * @param level the level of the weapon
     * @param unlockLevel the level to unlock the action card
     */
    public WeaponActionCard(Element dataXML, int index, int level, int unlockLevel) {
        super(dataXML, index, level, unlockLevel);

        if (type.equals("attack")) {
            damagePerAmmo = Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(index).getChildNodes().item(5 + (level - unlockLevel) * 2).getAttributes().getNamedItem("damagePerAmmo").getTextContent());
            reloadTime = Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(index).getChildNodes().item(5 + (level - unlockLevel) * 2).getAttributes().getNamedItem("reloadTime").getTextContent());
            range = Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(index).getChildNodes().item(5 + (level - unlockLevel) * 2).getAttributes().getNamedItem("range").getTextContent());
            accuracy = Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(index).getChildNodes().item(5 + (level - unlockLevel) * 2).getAttributes().getNamedItem("accuracy").getTextContent());
            critic = Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(index).getChildNodes().item(5 + (level - unlockLevel) * 2).getAttributes().getNamedItem("critic").getTextContent());
        }
        specialActionType = dataXML.getElementsByTagName("actionCard").item(index).getChildNodes().item(3).getAttributes().getNamedItem("type").getTextContent();
    }

    /**
     * Damage for each attack using this action card if its action is an attack.
     */
    private int damagePerAmmo;
    /**
     * Reload time of the action card if its action is an attack.
     */
    private int reloadTime;
    /**
     * Range of the attack if its action is an attack, can be up to 5.
     */
    private int range;
    /**
     * Accuracy of the attack in percent if its action is an attack.
     */
    private int accuracy;
    /**
     * Critical hit chance in percent if its action is an attack.
     */
    private int critic;
    /**
     * Special type of the action, ie what special effect the attack of buff has.
     */
    private final String specialActionType;

    /**
     * Returns the damage for each attack using this action card.
     */
    public int getDamagePerAmmo() {
        return damagePerAmmo;
    }

    /**
     * Returns the reload time of an action card.
     */
    public int getReloadTime() {
        return reloadTime;
    }

    /**
     * Returns the range of the attack.
     * */
    public int getRange() {
        return range;
    }

    /**
     * Returns the accuracy of the attack.
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * Returns the chance of critical hit of the attack.
     */
    public int getCritic() {
        return critic;
    }

    /**
     * Returns the special type of the action of the card.
     */
    public String getSpecialActionType() {
        return specialActionType;
    }

}
