package Combat.Unit;

import Combat.ActionCard;
import Combat.BombingCombatManager;
import Combat.FightAirship;
import Combat.WeaponActionCard;
import General.Airship;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Objects;

import static General.XMLReader.readXML;
import static java.lang.Math.floor;
import static java.lang.Math.random;

public class Weapon extends Unit {

    //Constructor
    public Weapon(String name, int level, FightAirship airship) {
        super(name, level);

        this.ourAirship=airship;

        if (Objects.equals(Objects.requireNonNull(dataXML).getElementsByTagName("useSituation").item(0).getTextContent(), "bombing")) {
            canBombing = true;
            canBoarding = false;
        } else if (Objects.equals(dataXML.getElementsByTagName("useSituation").item(0).getTextContent(), "bombing and boarding")) {
            canBombing = true;
            canBoarding = true;
        }

        preparationTime = Integer.parseInt(dataXML.getElementsByTagName("preparationTime").item(0).getTextContent());
        blockadedForcePerWeapon = Integer.parseInt(dataXML.getElementsByTagName("blockadedForcePerWeapon").item(0).getTextContent());
        numberOfWeapon = Integer.parseInt(dataXML.getElementsByTagName("statLevel" + level).item(0).getAttributes().getNamedItem("numberOfWeapon").getTextContent());
        description = dataXML.getElementsByTagName("description").item(0).getTextContent();


        for (int i = 0; i < dataXML.getElementsByTagName("actionCard").getLength(); i++) {
            int unlockLevel = Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(i).getAttributes().getNamedItem("unlockLevel").getTextContent());
            if (level >= unlockLevel) {
                actionCardsList.add(new WeaponActionCard(dataXML, i, level, unlockLevel));
            }
        }
    }

    private final FightAirship ourAirship;

    final Element dataXML = readXML("WeaponsData.xml", name);

    private final int preparationTime;
    private final int blockadedForcePerWeapon;
    private final int numberOfWeapon;
    private final String description;

    public int getPreparationTime() {
        return preparationTime;
    }
    public int getBlockadedForcePerWeapon() {
        return blockadedForcePerWeapon;
    }
    public int getNumberOfWeapon() {
        return numberOfWeapon;
    }
    public String getDescription() {
        return description;
    }


    public void weaponAttack(FightAirship airship, WeaponActionCard card, boolean lateAttack) {

        int numberOfAttacks=numberOfWeapon;
        int damagePerAttack=card.getDamagePerAmmo();
        int shieldArmor=airship.getShieldArmorRating();
        int hullArmor=airship.getHullArmorRating();
        int shield=airship.getShield();
        double bonusShieldDamage=1;
        double bonusCrewDamage=1;
        double bonusHullDamage=1;

        if (airship.getVulnerabilities()>0) {
            damagePerAttack*=1.2;
            airship.changeVulnerability(-1);
        }


        int i = 0;

        switch(card.getSpecialActionType()) {



            case "armourPiercing":
                shieldArmor = 0;
                hullArmor = 0;

            case "shieldDamageBonus":
                assert dataXML != null;
                bonusShieldDamage+=(double)Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(3).getAttributes().getNamedItem("bonusShieldDamage").getTextContent())/100;

            case "lateAttack":
                //???????????????????????????????

            case "slow":
                assert dataXML != null;
                double slowAmount=(double)Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(3).getAttributes().getNamedItem("slowAmount").getTextContent())/10;
                while (i < numberOfAttacks) {
                    int damagePerAttackBis = damagePerAttack;
                    if (random() * 100 < card.getAccuracy()) {
                        if (random() * 100 < card.getCritic()) {
                            damagePerAttackBis *= 1.5;
                        }
                        baseAirshipAttack(airship, damagePerAttackBis, shieldArmor, hullArmor, shield, bonusHullDamage, bonusShieldDamage, bonusCrewDamage);
                        airship.slow(slowAmount);
                    }
                    i++;
                }
                turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                reloadTime=card.getReloadTime();
                break;

            case "vulnerability":
                assert dataXML != null;
                int vulnerabilityProbability=Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("vulnerabilityProbability").getTextContent());
                while (i < numberOfAttacks) {
                    int damagePerAttackBis = damagePerAttack;
                    if (random() * 100 < card.getAccuracy()) {
                        if(random() * 100 <vulnerabilityProbability) {
                            airship.changeVulnerability(1);
                        }
                        if (random() * 100 < card.getCritic()) {
                            damagePerAttackBis *= 1.5;
                        }
                        baseAirshipAttack(airship, damagePerAttackBis, shieldArmor, hullArmor, shield, bonusHullDamage, bonusShieldDamage, bonusCrewDamage);
                    }
                    i++;
                }
                turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                reloadTime=card.getReloadTime();
                break;

            case "ignite":
                assert dataXML != null;
                int igniteProbability=Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("critic").getTextContent());
                int fireDepart=Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("fireDepart").getTextContent());
                while (i < numberOfAttacks) {
                    int damagePerAttackBis = damagePerAttack;
                    if (random() * 100 < card.getAccuracy()) {
                        if(random() * 100 <igniteProbability) {
                            airship.ignite(fireDepart);
                        }
                        if (random() * 100 < card.getCritic()) {
                            damagePerAttackBis *= 1.5;
                        }
                        baseAirshipAttack(airship, damagePerAttackBis, shieldArmor, hullArmor, shield, bonusHullDamage, bonusShieldDamage, bonusCrewDamage);
                    }
                    i++;
                }
                turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                reloadTime=card.getReloadTime();
                break;

            case "shieldPiercing":
                while (i < numberOfAttacks) {
                    int damagePerAttackBis = damagePerAttack;
                    if (random() * 100 < card.getAccuracy()) {
                        if (random() * 100 < card.getCritic()) {
                            damagePerAttackBis *= 1.5;
                        }
                        if (shield>0) {
                            if (damagePerAttackBis > shieldArmor) {
                                airship.adjustShield(-(double)damagePerAttackBis/4);
                                airship.adjustHullIntegrity(-(double)damagePerAttackBis*3/4);
                            }
                        }
                        else if (airship.getShield() <= 0) {
                            if (damagePerAttackBis > hullArmor) {
                                airship.adjustHullIntegrity(-damagePerAttackBis + hullArmor);
                                airship.adjustCrewHealth(-(damagePerAttackBis + hullArmor)*(double)airship.getMaxHullIntegrity()/100);
                            }
                        }
                    }
                    i++;
                }
                turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                reloadTime=card.getReloadTime();
                break;

            case "random":
                while (i < numberOfAttacks) {
                    int damagePerAttackBis = damagePerAttack;
                    FightAirship randomAirship = BombingCombatManager.getInstance().getAirshipBattlefield()[airship.getField()][(int) floor(random()*5)];
                    if (randomAirship!=null) {
                        if (random() * 100 < card.getAccuracy()) {
                            if (random() * 100 < card.getCritic()) {
                                damagePerAttackBis *= 1.5;
                            }
                            baseAirshipAttack(randomAirship, damagePerAttackBis, shieldArmor, hullArmor, shield, bonusHullDamage, bonusShieldDamage, bonusCrewDamage);
                        }
                        i++;
                    }
                }
                turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                reloadTime=card.getReloadTime();
                break;

            case "selfDamage":
                assert dataXML != null;
                int probability = Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(3).getAttributes().getNamedItem("probability").getTextContent());
                while (i < numberOfAttacks) {
                    int damagePerAttackBis = damagePerAttack;
                    if (random() * 100 < card.getAccuracy()) {
                        if (random() * 100 < card.getCritic()) {
                            damagePerAttackBis *= 1.5;
                        }
                        baseAirshipAttack(airship, damagePerAttackBis, shieldArmor, hullArmor, shield, bonusHullDamage, bonusShieldDamage, bonusCrewDamage);
                    }
                    if (random()*100 < probability) {
                        airship.adjustHullIntegrity(-(double)damagePerAttackBis/2+1);
                        airship.adjustCrewHealth((-(double)damagePerAttackBis/2+1)*(double)airship.getMaxHullIntegrity()/100);
                    }
                    i++;
                }
                turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                reloadTime=card.getReloadTime();
                break;

            default:
                while (i < numberOfAttacks) {
                    int damagePerAttackBis = damagePerAttack;
                    if (random() * 100 < card.getAccuracy()) {
                        if (random() * 100 < card.getCritic()) {
                            damagePerAttackBis *= 1.5;
                        }
                        baseAirshipAttack(airship, damagePerAttackBis, shieldArmor, hullArmor, shield, bonusHullDamage, bonusShieldDamage, bonusCrewDamage);
                    }
                    i++;
                    turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                    reloadTime=card.getReloadTime();
                }
        }
    }

    public void weaponAttack() {

    }

    /*specialAction airship
            preshot
            mortar

    specialAction flying
            damageBonus -> large
            multipleShot -> flying unit
            harpon

    specialAction boarding
            multipleShot -> landUnit
            damageBonus -> large
            damageBonus -> small unit
    */


    private void baseAirshipAttack(FightAirship airship, int damagePerAttack, int shieldArmor, int hullArmor, int shield, double bonusHullDamage, double bonusShieldDamage, double bonusCrewDamage) {
        if (shield>0) {
            if (damagePerAttack > shieldArmor) {
                airship.adjustShield(-damagePerAttack*bonusShieldDamage + shieldArmor);
            }
        }
        else if (airship.getShield() <= 0) {
            if (damagePerAttack > hullArmor) {
                airship.adjustHullIntegrity(-damagePerAttack*bonusHullDamage + hullArmor);
                airship.adjustCrewHealth(-(damagePerAttack + hullArmor)*(double)airship.getMaxHullIntegrity()/100*bonusCrewDamage);
            }
        }
    }

    private boolean canAction = false;
    private int turnPreviousAction = 0;
    private int reloadTime;
    public boolean canAction() {
        return canAction;
    }
    public void hasAttacked() {
        canAction = false;
    }

    public void udpateCanAction(int turn) {
        if (turn == preparationTime) {
            canAction=true;
        }
        if (turn > preparationTime) {
            if (turn - turnPreviousAction >= reloadTime) {
                canAction=true;
            }
        }
    }
}
