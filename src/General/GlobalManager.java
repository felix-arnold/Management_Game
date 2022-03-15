package General;

import Quarter.ProductionQuarter.*;
import Quarter.*;

public class GlobalManager {

    /* Manage turn, which means :       calculate resources
                                        research tech               Done
                                        build ship                  Done
                                        heal ships and units
                                        produce units

       Manage the list of all ships :   define list                 Done
                                        build ship                  Done
                                        upgrade ship                Done
    */


    //Constructor | Use of a singleton
    private GlobalManager () {
        this.airshipList[0]= new Airship("Sloop");
        this.turn = 1;
    }
    private static final GlobalManager INSTANCE = new GlobalManager();
    public static GlobalManager getInstance() {
        return INSTANCE;
    }


    //Definition of general resources and their getter
    private final Resources scienceResource = new Resources(false);
    private final Resources bitResource = new Resources(false);
    private final Resources codeDataResource = new Resources(false);
    private final Resources cryptoMoneyResource = new Resources(true);

    public Resources getBitResource() {
        return bitResource;
    }
    public Resources getCodeDataResource() {
        return codeDataResource;
    }
    public Resources getCryptoMoneyResource() {
        return cryptoMoneyResource;
    }
    public Resources getScienceResource() {
        return scienceResource;
    }


    //General airships list and construction
    private final Airship[] airshipList = new Airship[12];
    private int numberOfShip=1;

    public Airship[] getAirshipList() {
        return airshipList;
    }
    public int getNumberOfShip() {
        return numberOfShip;
    }

    //build an Airship (the button is not supposed to be available if there is not enough resources or too much ship)
    public void constructAirship(String name, Airship airship) {
        Airship provisionalShip = new Airship(name);
        if ((bitResource.getAmount() >= provisionalShip.getBitCost()) && (codeDataResource.getAmount() >= provisionalShip.getCodeDataCost()) && (cryptoMoneyResource.getAmount() >= provisionalShip.getCryptomoneyCost()) && (airship.getLocalResources().getCrewResource().getAmount() > provisionalShip.getCrewCost()) && (airship.getLocalResources().getFoodResource().getAmount() >= provisionalShip.getFoodCost()) && (airship.getLocalResources().getElectricityResource().getAmount() >= provisionalShip.getElectricityCost())  && (numberOfShip < 13)) {
            bitResource.subtractAmount(provisionalShip.getBitCost());
            codeDataResource.subtractAmount(provisionalShip.getCodeDataCost());
            cryptoMoneyResource.subtractAmount(provisionalShip.getCryptomoneyCost());
            airship.getLocalResources().getCrewResource().subtractAmount(provisionalShip.getCrewCost());
            airship.getLocalResources().getFoodResource().subtractAmount(provisionalShip.getFoodCost());
            airship.getLocalResources().getElectricityResource().subtractAmount(provisionalShip.getElectricityCost());
            airshipList[numberOfShip] = provisionalShip;
            numberOfShip++;

            //construction of prebuild quarters
            if (provisionalShip.getPrebuildQuarter1() != null && provisionalShip.getPrebuildQuarter2() != null) {
                airshipList[numberOfShip].constructQuarter(provisionalShip.getPrebuildQuarter1()[0], Integer.parseInt(provisionalShip.getPrebuildQuarter1()[1]), Integer.parseInt(provisionalShip.getPrebuildQuarter1()[2]), true);
                airshipList[numberOfShip].constructQuarter(provisionalShip.getPrebuildQuarter2()[0], Integer.parseInt(provisionalShip.getPrebuildQuarter2()[1]), Integer.parseInt(provisionalShip.getPrebuildQuarter2()[2]), true);
            }
        }
    }

    public void upgradeAirship(String name, Airship airship, int indexAirship) {
        Airship provisionalShip = new Airship(name);
        if ((bitResource.getAmount() >= provisionalShip.getBitCost()) && (codeDataResource.getAmount() >= provisionalShip.getCodeDataCost()) && (cryptoMoneyResource.getAmount() >= provisionalShip.getCryptomoneyCost()) && (airship.getLocalResources().getCrewResource().getAmount() > provisionalShip.getCrewCost()) && (airship.getLocalResources().getFoodResource().getAmount() >= provisionalShip.getFoodCost()) && (airship.getLocalResources().getElectricityResource().getAmount() >= provisionalShip.getElectricityCost())) {
            bitResource.subtractAmount(provisionalShip.getBitCost());
            codeDataResource.subtractAmount(provisionalShip.getCodeDataCost());
            cryptoMoneyResource.subtractAmount(provisionalShip.getCryptomoneyCost());
            airship.getLocalResources().getCrewResource().subtractAmount(provisionalShip.getCrewCost());
            airship.getLocalResources().getFoodResource().subtractAmount(provisionalShip.getFoodCost());
            airship.getLocalResources().getElectricityResource().subtractAmount(provisionalShip.getElectricityCost());
            airshipList[indexAirship] = provisionalShip;

            //construction of prebuild quarters
            airshipList[indexAirship].constructQuarter(provisionalShip.getPrebuildQuarter1()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter1()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter1()[2]),true);
            airshipList[indexAirship].constructQuarter(provisionalShip.getPrebuildQuarter2()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter2()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter2()[2]),true);
        }
    }


    //Turn manager
    private int turn;
    public int getTurn() {
        return turn;
    }

    public void nextTurn() {
        scienceResource.setAmount(0);
        for (Airship iShip : airshipList) {
            iShip.updateProduction();
            for (Quarter[] iQuarter : iShip.getQuarterList()) {
                for (Quarter jQuarter : iQuarter) {
                    if ((jQuarter instanceof ProductionQuarter) || (jQuarter instanceof AdmiralCabin) ||(jQuarter instanceof CaptainCabin)) {
                        for (int i = 0; i < jQuarter.getProduction().length/2; i++) {

                            /* Production type:
                                science = 1
                                bit = 2
                                dataCode = 3
                                cryptomoney = 4
                                electricity = 5
                                food = 6
                                crew = 7
                                other = 0 */

                            //add science production
                            if (jQuarter.getProduction()[2*i] == 1) {
                                bitResource.addAmount(jQuarter.getProduction()[2*i + 1]);
                            }
                            //add bit production
                            if (jQuarter.getProduction()[2*i] == 2) {
                                bitResource.addAmount(jQuarter.getProduction()[2*i+1]);
                            }
                            //add dataCode production
                            if (jQuarter.getProduction()[2*i] == 3) {
                                codeDataResource.addAmount(jQuarter.getProduction()[2*i+1]);
                            }
                            //add cryptomoney production
                            if (jQuarter.getProduction()[2*i] == 4) {
                                cryptoMoneyResource.addAmount(jQuarter.getProduction()[2*i+1]);
                            }
                            //add electricity production
                            if (jQuarter.getProduction()[2*i] == 5) {
                                iShip.getLocalResources().getElectricityResource().addAmount(jQuarter.getProduction()[2*i+1]);
                            }
                            //add food production
                            if (jQuarter.getProduction()[2*i] == 6) {
                                iShip.getLocalResources().getFoodResource().addAmount(jQuarter.getProduction()[2*i+1]);
                            }
                            //add crew production
                            if (jQuarter.getProduction()[2*i] == 7) {
                                iShip.getLocalResources().getCrewResource().addAmount(jQuarter.getProduction()[2*i+1]);
                                iShip.getLocalResources().getAvailableCrewResource().addAmount(jQuarter.getProduction()[2*i+1]);
                            }
                        }
                    }
                    //subtract cryptomoney consumption
                    cryptoMoneyResource.subtractAmount(jQuarter.getCryptomoneyConsumption());
                    //subtract electricity consumption
                    iShip.getLocalResources().getElectricityResource().subtractAmount(jQuarter.getElectricityConsumption());
                    //subtract food consumption
                    iShip.getLocalResources().getFoodResource().subtractAmount(jQuarter.getFoodConsumption());
                }
            }
            iShip.calculateFoodBonus();
            iShip.manageElectricityOverconsumption();
        }
        TechManager.getInstance().updateTech();
        turn ++;
    }
}