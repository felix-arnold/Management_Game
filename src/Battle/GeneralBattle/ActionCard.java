package Battle.GeneralBattle;

import org.w3c.dom.Element;

/**
 * This abstract class regroups the properties and methods of action cards common for each type of action card.
 */
public abstract class ActionCard {

    /**
     * Creates a new action card and loads the value of the different properties of the action card.
     * @param dataXML the data of the weapon from the XML file
     * @param index the index of the action card in dataXML
     * @param level the level of the weapon
     * @param unlockLevel the level at which the action card is unlocked.
     */
    public ActionCard(Element dataXML, int index, int level, int unlockLevel) {

        String descriptionActionCard;
        String situation;
        this.index=index;
        this.unlockLevel=unlockLevel;
        nameActionCard=dataXML.getElementsByTagName("actionCard").item(index).getAttributes().getNamedItem("nameActionCard").getTextContent();
        target=dataXML.getElementsByTagName("actionCard").item(index).getAttributes().getNamedItem("target").getTextContent();
        type=dataXML.getElementsByTagName("actionCard").item(index).getAttributes().getNamedItem("type").getTextContent();
        situation=dataXML.getElementsByTagName("actionCard").item(index).getAttributes().getNamedItem("situation").getTextContent();
        descriptionActionCard=dataXML.getElementsByTagName("actionCard").item(index).getChildNodes().item(1).getTextContent();
    }

    /**
     * Name of the action card.
     */
    protected final String nameActionCard;
    /**
     * Type of target of the action.
     */
    protected final String target;
    /**
     * Type of action.
     */
    protected final String type;

    /**
     * Index of the action card inside the XML file for the selected weapon attribute.
     */
    protected final int index;
    /**
     * Level at which the action card is unlocked.
     */
    protected final int unlockLevel;

    /**
     * Returns the name of the action card.
     */
    public String getNameActionCard() {
        return nameActionCard;
    }
    /**
     * Returns the type of target of the action.
     */
    public String getTarget() {
        return target;
    }
    /**
     * Returns the type of the action.
     */
    public String getType() {
        return type;
    }
    /**
     * Returns the index of the action card.
     */
    public int getIndex() {
        return index;
    }
    /**
     * Returns the level at which the action card is unlocked.
     */
    public int getUnlockLevel() {
        return unlockLevel;
    }
}
