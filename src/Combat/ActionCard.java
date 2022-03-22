package Combat;

import org.w3c.dom.Element;

public abstract class ActionCard {

    //Constructor
    public ActionCard(Element dataXML, int index, int level, int unlockLevel) {

        this.index=index;
        this.unlockLevel=unlockLevel;
        nameActionCard=dataXML.getElementsByTagName("actionCard").item(index).getAttributes().getNamedItem("nameActionCard").getTextContent();
        target=dataXML.getElementsByTagName("actionCard").item(index).getAttributes().getNamedItem("target").getTextContent();
        type=dataXML.getElementsByTagName("actionCard").item(index).getAttributes().getNamedItem("type").getTextContent();
        situation=dataXML.getElementsByTagName("actionCard").item(index).getAttributes().getNamedItem("situation").getTextContent();
        descriptionActionCard=dataXML.getElementsByTagName("actionCard").item(index).getChildNodes().item(1).getTextContent();
    }

    protected final String nameActionCard;
    protected final String target;
    protected final String type;
    protected final String situation;
    protected final String descriptionActionCard;
    protected final int index;
    protected final int unlockLevel;

    public String getNameActionCard() {
        return nameActionCard;
    }
    public String getTarget() {
        return target;
    }
    public String getType() {
        return type;
    }
    public String getSituation() {
        return situation;
    }
    public String getDescriptionActionCard() {
        return descriptionActionCard;
    }
    public int getIndex() {
        return index;
    }
    public int getUnlockLevel() {
        return unlockLevel;
    }
}
