package Airship;

import General.GlobalRessourcesManager;
import General.LocalRessourcesManager;
import Quarter.Quarter;

public abstract class Airship {

    protected String name;
    static int numberOfShip;

    protected int[][] positionQuarter;
    protected Quarter[][] quarterList;
    protected int numberQuarter;

    protected LocalRessourcesManager localRessources;

    //Constructor
    public Airship(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Quarter[][] getQuarterList() {
        return quarterList;
    }

    public int getNumberQuarter() {
        return numberQuarter;
    }

    //Construct the building at the selected place
    public void constructQuarter(Quarter quarter, int xPos, int yPos) {
        if ((GlobalRessourcesManager.getInstance().getBitsRessource().getAmount() >= quarter.getBitCost()) && (GlobalRessourcesManager.getInstance().getCodeDataRessource().getAmount() >= quarter.getCodeDataCost()) && (GlobalRessourcesManager.getInstance().getCryptoMoneyRessource().getAmount() >= quarter.getCryptomoneyCost())) {
            GlobalRessourcesManager.getInstance().getBitsRessource().substractAmount(quarter.getBitCost());
            GlobalRessourcesManager.getInstance().getCodeDataRessource().substractAmount(quarter.getCodeDataCost());
            GlobalRessourcesManager.getInstance().getCryptoMoneyRessource().substractAmount(quarter.getCryptomoneyCost());
            quarterList[xPos][yPos] = quarter;
        }
    }

    //Get a list of the four adjacent quarters of this quarter
    public Quarter[] getAdjacent(Quarter quarter) {
        Quarter[] adjacentQuarter = new Quarter[4];
        int i = 0;
        int xPos = quarter.getX();
        int yPos = quarter.getY();
        if(quarterList[xPos+1][yPos] != null) {
            adjacentQuarter[i] = quarterList[xPos+1][yPos];
            i++;
        }
        if(quarterList[xPos][yPos+1] != null) {
            adjacentQuarter[i] = quarterList[xPos][yPos+1];
            i++;
        }
        if(quarterList[xPos-1][yPos] != null) {
            adjacentQuarter[i] = quarterList[xPos-1][yPos];
            i++;
        }
        if(quarterList[xPos][yPos-1] != null) {
            adjacentQuarter[i] = quarterList[xPos][yPos-1];
        }
        return adjacentQuarter;
    }
}
