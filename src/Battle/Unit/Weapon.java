package Battle.Unit;

import Battle.GeneralBattle.BombingCombatManager;
import Battle.GeneralBattle.Deck;
import Battle.GeneralBattle.FightAirship;
import Battle.GeneralBattle.WeaponActionCard;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import org.w3c.dom.Element;

import java.util.Objects;

import static FileReaders.XMLReader.readXML;
import static java.lang.Math.floor;
import static java.lang.Math.random;


 /**
 * This class is used to create weapons and to manage their properties.
 */
public class Weapon extends Unit {

    /**
     * Deck of action cards of the weapon.
     */
    private final Deck deck;

    /**
     * Returns the deck of the weapon.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Creates a new instance of a weapon depending on its name and level.
     * @param name the name of the weapon
     * @param level the level of the weapon
     * @param airship the airship to which belongs the weapon
     */
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
        numberOfWeapon = Integer.parseInt(dataXML.getElementsByTagName("statLevel" + level).item(0).getAttributes().getNamedItem("numberOfWeapon").getTextContent());
        description = dataXML.getElementsByTagName("description").item(0).getTextContent();


        for (int i = 0; i < dataXML.getElementsByTagName("actionCard").getLength(); i++) {
            int unlockLevel = Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(i).getAttributes().getNamedItem("unlockLevel").getTextContent());
            if (level >= unlockLevel) {
                actionCardsList.add(new WeaponActionCard(dataXML, i, level, unlockLevel));
            }
        }

        deck = new Deck(actionCardsList,this);


        Button but = new Button("AAAAA");
        but.setLayoutY(100);

        weaponButton = new RadioButton(name);
        weaponButton.setToggleGroup(BombingCombatManager.getInstance().getWeaponToggleGroup());
        weaponButton.getStyleClass().clear();
        weaponButton.getStyleClass().add("button");

    }

    /**
     * FightAirship to which belongs the weapon.
     */
    private final FightAirship ourAirship;
    /**
     * Data from the XML file, containing the attributes and elements of the weapon.
     */
    private final Element dataXML = readXML("WeaponsData.xml", name);
    /**
     * Preparation time before the weapon can perform an action for the first time.
     */
    private final int preparationTime;
    /**
     * The number of weapons, which can be interpreted as the number of time the weapon attacks
     */
    private final int numberOfWeapon;
    /**
     * Description of the weapon.
     */
    private final String description;

    /**
     * Returns the preparation time before the weapon can perform an action for the first time.
     */
    public int getPreparationTime() {
        return preparationTime;
    }

    /**
     * Returns the description of the action card.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Attacks the target airship.
     * The nature of the attack depends on the action card used and its damages at well as its secondary effects may greatly vary. These properties are describes in the XML file in the action card attributes.
     * The method baseAirshipAttack corresponds to the base attack of the weapons, but some attacks may need to reimplement it.
     * @param airship the target airship receiving the upcoming damages
     * @param card the action card casting the buff
     */
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
            airship.addVulnerability(-1);
        }
        int i = 0;

        switch(card.getSpecialActionType()) {
            case "vulnerability":
                if(airship.getField() - (ourAirship.getField()+buffRange) >= 0) {
                    assert dataXML != null;
                    int vulnerabilityProbability = Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(3).getAttributes().getNamedItem("probability").getTextContent());
                    while (i < numberOfAttacks) {
                        int damagePerAttackBis = damagePerAttack+buffDamage;
                        if (random() * 100 < card.getAccuracy()+buffAccuracy) {
                            if(random() * 100 <vulnerabilityProbability) {
                                airship.addVulnerability(1);
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

            case "slow":
                if(airship.getField() - (ourAirship.getField()+buffRange) >= 0) {
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

            case "shieldDamageBonus":
                assert dataXML != null;
                bonusShieldDamage+=(double)Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(3).getAttributes().getNamedItem("bonusShieldDamage").getTextContent())/100;

            case "armourPiercing":
                shieldArmor = 0;
                hullArmor = 0;

            case "ignite":
                if(airship.getField() - (ourAirship.getField()+buffRange) >= 0) {
                    assert dataXML != null;
                    int igniteProbability=Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("critic").getTextContent());
                    while (i < numberOfAttacks) {
                        int damagePerAttackBis = damagePerAttack+buffDamage;
                        if (random() * 100 < card.getAccuracy()+buffAccuracy) {
                            if(random() * 100 <igniteProbability) {
                                airship.ignite(2);
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
                if(airship.getField() - (ourAirship.getField()+buffRange) >= 0) {
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
                if(airship.getField() - (ourAirship.getField()+buffRange) >= 0) {
                    while (i < numberOfAttacks) {
                        int damagePerAttackBis = damagePerAttack+buffDamage;
                        FightAirship randomAirship = BombingCombatManager.getInstance().getAirshipBattlefield()[airship.getField()][(int) floor(Math.random()*5)];
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
                if(airship.getField() - (ourAirship.getField()+buffRange) >= 0) {
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

            default:
                if(airship.getField() - (ourAirship.getField()+buffRange) >= 0) {
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

    /**
     * Adds buffs to the weapon for the next attack.
     * The nature of the buff depends on the action card and is described in the XML file.
     * @param card the action card casting the buff
     */
    public void weaponBuff(WeaponActionCard card) {
        /*for(int i = 3; i<6; i++) {
            if(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(i).getAttributes().getNamedItem("buffType").getTextContent() != null) {
            }
        }*/
        switch (Objects.requireNonNull(dataXML).getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(3).getAttributes().getNamedItem("buffType").getTextContent()) {
            case "range" -> buffRange = buffRange + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("buffRange").getTextContent());
            case "damage" -> buffDamage = buffDamage + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("buffDamage").getTextContent());
            case "critic" -> buffCritic = buffCritic + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("buffCritic").getTextContent());
            case "accuracy" -> buffAccuracy = buffAccuracy + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("buffAccuracy").getTextContent());
            case "criticDamage" -> buffCriticDamage = buffCriticDamage + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("buffDamageCritic").getTextContent());
            case "reload" -> buffReload = buffReload + Integer.parseInt(dataXML.getElementsByTagName("actionCard").item(card.getIndex()).getChildNodes().item(5 + (level - card.getUnlockLevel()) * 2).getAttributes().getNamedItem("reloadTime").getTextContent());
            default -> {
            }
        }
    }


    /**
     * Deals damages to the selected enemy.
     * <br> If the target airship owns a shield, the damage are dealt to the ship. Once the shield broken,the damages are dealt to the hull of the ship.
     * The armor rating of both shield and hull of the target airship reduce incoming damages by the same amount. This method is the base attack pattern.
     * @param airship the attacked airship
     * @param damagePerAttack the damages of one weapon
     * @param shieldArmor the armor rating of the target airship's shield
     * @param hullArmor the armor rating of the target airship's hull
     * @param shield the shield of the target airship
     * @param bonusHullDamage the additional bonus damage dealt to the hull
     * @param bonusShieldDamage the additional bonus damage dealt to the shield
     * @param bonusCrewDamage the additional bonus damage dealt to the crew
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

    /**
     * Property of a weapon which can realise an action, as launching an attack or casting a buff.
     */
    private boolean canAction = false;
    /**
     * Turn of the previous action.
     */
    private int turnPreviousAction = 0;

    /**
     * Number of turn after an attack to reload the weapon.
     */
    private int reloadTime;

    /**
     * ababababab
     */
    private int buffRange,buffDamage,buffCritic,buffAccuracy,buffCriticDamage,buffReload;

    /**
     * Returns the property of a weapon which can realise an action.
     */
    public boolean canAction() {
        return canAction;
    }

    /**
     * Sets the canAction property to false.
     */
    public void hasAttacked() {
        canAction = false;
    }

    /**
     * Updates the property canAction when the preparation time or the reload time of the weapon have been elapsed.
     * To reload the weapon, the method compare the current turn with the turn of the last attack and check if the reload time of the previous action has passed.
     * @param turn the current turn of the battle
     */
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

    /**
     * Button of the weapon.
     */
    private final RadioButton weaponButton;

    /**
     * Returns the button of the weapon.
     */
    public RadioButton getWeaponButton() {
        return weaponButton;
    }
}
