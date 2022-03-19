package Combat.Unit;

import org.w3c.dom.Element;

import java.util.Objects;

import static General.XMLReader.readXML;

public class Weapon extends Unit {

    //Cosntructor
    public Weapon(String name, int level) {
        super(name, level);
        final Element dataXML = readXML("WeaponsData.xml", name);

        if (Objects.equals(Objects.requireNonNull(dataXML).getElementsByTagName("useSituation").item(0).getTextContent(), "bombing")) {
            canBombing = true;
            canBoarding = false;
        } else if (Objects.equals(dataXML.getElementsByTagName("useSituation").item(0).getTextContent(), "bombing and boarding")) {
            canBombing = true;
            canBoarding = true;
        }

        preparationTime=Integer.parseInt(dataXML.getElementsByTagName("preparationTime").item(0).getTextContent());
        blockadedForcePerWeapon=Integer.parseInt(dataXML.getElementsByTagName("blockadedForcePerWeapon").item(0).getTextContent());
        numberOfWeapon=Integer.parseInt(dataXML.getElementsByTagName("statLevel"+level).item(0).getAttributes().getNamedItem("numberOfWeapon").getTextContent());
    }

    public final int preparationTime;
    public final int blockadedForcePerWeapon;
    public final int numberOfWeapon;

    public int getPreparationTime() {
        return preparationTime;
    }
    public int getBlockadedForcePerWeapon() {
        return blockadedForcePerWeapon;
    }
    public int getNumberOfWeapon() {
        return numberOfWeapon;
    }
}
