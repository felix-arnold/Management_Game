package Combat;

import org.w3c.dom.Element;

public class WeaponActionCard extends ActionCard {

    //Constructor
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

    private int damagePerAmmo;
    private int reloadTime;
    private int range;
    private int accuracy;
    private int critic;
    private final String specialActionType;

    public int getDamagePerAmmo() {
        return damagePerAmmo;
    }
    public int getReloadTime() {
        return reloadTime;
    }
    public int getRange() {
        return range;
    }
    public int getAccuracy() {
        return accuracy;
    }
    public int getCritic() {
        return critic;
    }
    public String getSpecialActionType() {
        return specialActionType;
    }

}
