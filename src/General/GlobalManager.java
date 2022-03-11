package General;

import Airship.Airship;
import Quarter.ProductionQuarter.ProductionQuarter;
import Quarter.Quarter;

public class GlobalManager {

    //Constructor | Use of a singleton
    private GlobalManager () {
        this.airshipList[0]= new Airship("Sloop");
        this.turn = 1;
    }
    private static GlobalManager INSTANCE = new GlobalManager();
    public static GlobalManager getInstance() {
        return INSTANCE;
    }


    //Definition of general resources and their getter
    private Resources scienceResource = new Resources(false);
    private Resources bitResource = new Resources(false);
    private Resources codeDataResource = new Resources(false);
    private Resources cryptoMoneyResource = new Resources(true);

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
    private Airship[] airshipList = new Airship[12];
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
        if ((bitResource.getAmount() >= provisionalShip.getBitCost()) && (codeDataResource.getAmount() >= provisionalShip.getCodeDataCost()) && (cryptoMoneyResource.getAmount() >= provisionalShip.getCryptomoneyCost()) && (airship.getLocalResources().getCrewRessource().getAmount() > provisionalShip.getCrewCost()) && (airship.getLocalResources().getFoodRessource().getAmount() >= provisionalShip.getFoodCost()) && (airship.getLocalResources().getElecricityRessource().getAmount() >= provisionalShip.getElectricityCost())  && (numberOfShip < 13)) {
            bitResource.subtractAmount(provisionalShip.getBitCost());
            codeDataResource.subtractAmount(provisionalShip.getCodeDataCost());
            cryptoMoneyResource.subtractAmount(provisionalShip.getCryptomoneyCost());
            numberOfShip++;
            airshipList[numberOfShip] = provisionalShip;

            //construction of prebuild quarters
            airshipList[numberOfShip].constructQuarter(provisionalShip.getPrebuildQuarter1()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter1()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter1()[2]),true);
            airshipList[numberOfShip].constructQuarter(provisionalShip.getPrebuildQuarter2()[0],Integer.parseInt(provisionalShip.getPrebuildQuarter2()[1]),Integer.parseInt(provisionalShip.getPrebuildQuarter2()[2]),true);
        }
    }


    //Turn manager
    private int turn;
    public int getTurn() {
        return turn;
    }

    //calculate gained and lost resources
    public void nextTurn() {
        for (Airship iShip : airshipList) {
            for (Quarter[] iQuarter : iShip.getQuarterList()) {
                for (Quarter jQuarter : iQuarter) {
                    if (jQuarter instanceof ProductionQuarter) {
                        for (int i = 0; i < jQuarter.getProduction().length/2; i++) {

                            /* Production type:
                                science = 1
                                bit = 2
                                dataCode = 3
                                cryptomoney = 4
                                electricity = 5
                                food = 6
                                crew = 7
                                other = 8 */

                            //add science production
                            if (jQuarter.getProduction()[2*i] == 1) {
                                scienceResource.addAmount(jQuarter.getProduction()[2*i+1]);
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
                                iShip.getLocalResources().getElecricityRessource().addAmount(jQuarter.getProduction()[2*i+1]);
                            }
                            //add food production
                            if (jQuarter.getProduction()[2*i] == 6) {
                                iShip.getLocalResources().getFoodRessource().addAmount(jQuarter.getProduction()[2*i+1]);
                            }
                            //add crew production
                            if (jQuarter.getProduction()[2*i] == 7) {
                                iShip.getLocalResources().getCrewRessource().addAmount(jQuarter.getProduction()[2*i+1]);
                            }
                        }
                    }
                    //subtract cryptomoney consumption
                    cryptoMoneyResource.subtractAmount(jQuarter.getCryptomoneyConsumption());
                }
            }
        }
        turn ++;
    }
}