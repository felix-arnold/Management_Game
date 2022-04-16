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


    public void weaponAttack(FightAirship airship, WeaponActionCard card) {
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

            case "slow":
                if(airship.getField() - (ourAirship.getField()+buffRange) <= 0) {
                    assert dataXML != null;
                    double slowAmount=Double.parseDouble(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(3).getAttributes().getNamedItem("turnDuration").getTextContent())/10;
                    while (i < numberOfAttacks) {
                        int damagePerAttackBis = damagePerAttack+buffDamage;
                        if (random() * 100 < card.getAccuracy()+buffAccuracy) {
                            if (random() * 100 < card.getCritic()+buffCritic) {
                                damagePerAttackBis *= 1.5*(1+buffCriticDamage/100);
                            }
                            baseAirshipAttack(airship, damagePerAttackBis, shieldArmor, hullArmor, shield, bonusHullDamage, bonusShieldDamage, bonusCrewDamage);
                            airship.slow(slowAmount);
                        }
                        i++;
                    }
                    turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                    reloadTime=card.getReloadTime()-buffReload;
                    buffReload = 0;
                    buffCritic = 0;
                    buffAccuracy = 0;
                    buffDamage = 0;
                    buffCriticDamage = 0;
                    buffRange = 0;
                }
                break;

            case "vulnerability":
                if(airship.getField() - (ourAirship.getField()+buffRange) <= 0) {
                    assert dataXML != null;
                    int vulnerabilityProbability=Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("probability").getTextContent());
                    while (i < numberOfAttacks) {
                        int damagePerAttackBis = damagePerAttack+buffDamage;
                        if (random() * 100 < card.getAccuracy()+buffAccuracy) {
                            if(random() * 100 <vulnerabilityProbability) {
                                airship.changeVulnerability(1);
                            }
                            if (random() * 100 < card.getCritic()+buffCritic) {
                                damagePerAttackBis *= 1.5*(1+buffCriticDamage/100);
                            }
                            baseAirshipAttack(airship, damagePerAttackBis, shieldArmor, hullArmor, shield, bonusHullDamage, bonusShieldDamage, bonusCrewDamage);
                        }
                        i++;
                    }
                    turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                    reloadTime=card.getReloadTime()-buffReload;
                    buffReload = 0;
                    buffCritic = 0;
                    buffAccuracy = 0;
                    buffDamage = 0;
                    buffCriticDamage = 0;
                    buffRange = 0;
                }
                break;

            case "ignite":
                if(airship.getField() - (ourAirship.getField()+buffRange) <= 0) {
                    assert dataXML != null;
                    int igniteProbability=Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("critic").getTextContent());
                    int fireDepart=Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("fireDepart").getTextContent());
                    while (i < numberOfAttacks) {
                        int damagePerAttackBis = damagePerAttack+buffDamage;
                        if (random() * 100 < card.getAccuracy()+buffAccuracy) {
                            if(random() * 100 <igniteProbability) {
                                airship.ignite(fireDepart);
                            }
                            if (random() * 100 < card.getCritic()+buffCritic) {
                                damagePerAttackBis *= 1.5*(1+buffCriticDamage/100);
                            }
                            baseAirshipAttack(airship, damagePerAttackBis, shieldArmor, hullArmor, shield, bonusHullDamage, bonusShieldDamage, bonusCrewDamage);
                        }
                        i++;
                    }
                    turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                    reloadTime=card.getReloadTime()-buffReload;
                    buffReload = 0;
                    buffCritic = 0;
                    buffAccuracy = 0;
                    buffDamage = 0;
                    buffCriticDamage = 0;
                    buffRange = 0;
                }
                break;

            case "shieldPiercing":
                if(airship.getField() - (ourAirship.getField()+buffRange) <= 0) {
                    while (i < numberOfAttacks) {
                        int damagePerAttackBis = damagePerAttack + buffDamage;
                        if (random() * 100 < card.getAccuracy()+buffAccuracy) {
                            if (random() * 100 < card.getCritic()+buffCritic) {
                                damagePerAttackBis *= 1.5*(1+buffCriticDamage/100);
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
                    reloadTime=card.getReloadTime()-buffReload;
                    buffReload = 0;
                    buffCritic = 0;
                    buffAccuracy = 0;
                    buffDamage = 0;
                    buffCriticDamage = 0;
                    buffRange = 0;
                }
                break;

            case "randomTarget":
                if(airship.getField() - (ourAirship.getField()+buffRange) <= 0) {
                    while (i < numberOfAttacks) {
                        int damagePerAttackBis = damagePerAttack+buffDamage;
                        FightAirship randomAirship = BombingCombatManager.getInstance().getAirshipBattlefield()[airship.getField()][(int) floor(random()*5)];
                        if (randomAirship!=null) {
                            if (random() * 100 < card.getAccuracy()+buffAccuracy) {
                                if (random() * 100 < card.getCritic()+buffCritic) {
                                    damagePerAttackBis *= 1.5*(1+buffCriticDamage/100);
                                }
                                baseAirshipAttack(randomAirship, damagePerAttackBis, shieldArmor, hullArmor, shield, bonusHullDamage, bonusShieldDamage, bonusCrewDamage);
                            }
                            i++;
                        }
                    }
                    turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                    reloadTime=card.getReloadTime()-buffReload;
                    buffReload = 0;
                    buffCritic = 0;
                    buffAccuracy = 0;
                    buffDamage = 0;
                    buffCriticDamage = 0;
                    buffRange = 0;
                }
                break;

            case "selfDamage":
                if(airship.getField() - (ourAirship.getField()+buffRange) <= 0) {
                    assert dataXML != null;
                    int probability = Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(3).getAttributes().getNamedItem("probability").getTextContent());
                    while (i < numberOfAttacks) {
                        int damagePerAttackBis = damagePerAttack+buffDamage;
                        if (random() * 100 < card.getAccuracy()+buffAccuracy) {
                            if (random() * 100 < card.getCritic()+buffCritic) {
                                damagePerAttackBis *= 1.5*(1+buffCriticDamage/100);
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
                    reloadTime=card.getReloadTime()-buffReload;
                    buffReload = 0;
                    buffCritic = 0;
                    buffAccuracy = 0;
                    buffDamage = 0;
                    buffCriticDamage = 0;
                    buffRange = 0;
                }
                break;

            case "buff":
                weaponBuff(card);
                turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                reloadTime = card.getReloadTime()-buffReload;
                buffReload = 0;
                break;

            default:
                if(airship.getField() - (ourAirship.getField()+buffRange) <= 0) {
                    while (i < numberOfAttacks) {
                        int damagePerAttackBis = damagePerAttack + buffDamage;
                        if (random() * 100 < card.getAccuracy()+buffAccuracy) {
                            if (random() * 100 < card.getCritic()+buffCritic) {
                                damagePerAttackBis *= 1.5*(1+buffCriticDamage/100);
                            }
                            baseAirshipAttack(airship, damagePerAttackBis, shieldArmor, hullArmor, shield, bonusHullDamage, bonusShieldDamage, bonusCrewDamage);
                        }
                        i++;
                    }
                    turnPreviousAction=BombingCombatManager.getInstance().getTurn();
                    reloadTime=card.getReloadTime()-buffReload;
                    buffReload = 0;
                    buffCritic = 0;
                    buffAccuracy = 0;
                    buffDamage = 0;
                    buffCriticDamage = 0;
                    buffRange = 0;
                }
        }
    }
    
    public void weaponBuff(WeaponActionCard card) {
        for(int i = 3; i<6; i++) {
            if(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(i).getAttributes().getNamedItem("buffType").getTextContent() != null) {
                switch (dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(i).getAttributes().getNamedItem("buffType").getTextContent()) {
                    case "range":
                        buffRange = buffRange + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("buffRange").getTextContent());
                        break;

                    case "damage":
                        buffDamage = buffDamage + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("buffDamage").getTextContent());
                        break;

                    case "critic":
                        buffCritic = buffCritic + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("buffCritic").getTextContent());
                        break;

                    case "accuracy":
                        buffAccuracy = buffAccuracy + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("buffAccuracy").getTextContent());
                        break;

                    case "criticDamage":
                        buffCriticDamage = buffCriticDamage + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("buffDamageCritic").getTextContent());
                        break;

                    case "reload":
                        buffReload = buffReload + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("reloadTime").getTextContent());
                        break;

                    default:
                        break;
                }
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
    private int buffRange,buffDamage,buffCritic,buffAccuracy,buffCriticDamage,buffReload;
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
