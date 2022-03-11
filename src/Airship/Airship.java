package Airship;

import General.CsvFileUser;
import General.GlobalManager;
import General.LocalResourcesManager;
import Quarter.Quarter;
import Quarter.QuarterFactory;
import java.util.ArrayList;
import java.util.List;

import static General.CsvFileUser.*;

public class Airship {

    private final String name;
    private int[][] positionQuarter;
    private Quarter[][] quarterList;
    private int numberQuarter;

    private final int bitCost;
    private final int codeDataCost;
    private final int cryptomoneyCost;
    private final int electricityCost;
    private final int foodCost;
    private final int crewCost;
    private final int constructionTime;
    private final int maxHealth;
    private final int maxHull;
    private int health;
    private int hull;
    private final String[] prebuildQuarter1;
    private final String[] prebuildQuarter2;

    protected LocalResourcesManager localResources;

    static final List<String[]> airshipData = new ArrayList<>();

    //Constructor
    public Airship(String name) {
        this.name = name;
        bitCost = Integer.parseInt(loadValue(name, airshipData, "bitCost"));
        codeDataCost = Integer.parseInt(loadValue(name, airshipData, "codeDataCost"));
        cryptomoneyCost = Integer.parseInt(loadValue(name, airshipData, "cryptomoneyCost"));
        electricityCost = Integer.parseInt(loadValue(name, airshipData, "electricityCost"));
        foodCost = Integer.parseInt(loadValue(name, airshipData, "foodCost"));
        crewCost = Integer.parseInt(loadValue(name, airshipData, "crewCost"));
        constructionTime = Integer.parseInt(loadValue(name, airshipData, "bitCost"));
        maxHealth = Integer.parseInt(loadValue(name, airshipData, "maxHealth"));
        maxHull = Integer.parseInt(loadValue(name, airshipData, "maxHull"));

        health=maxHealth;
        hull=maxHull;

        prebuildQuarter1 = loadValue(name, airshipData, "prebuildQuarter1").split(",");
        prebuildQuarter2 = loadValue(name, airshipData, "prebuildQuarter2").split(",");

        //load positionQuarter as an array of values
        for (int i=0; i<loadValue(name, airshipData, "positionQuarter").split("/").length; i++) {
            String[] positionQuarterBis=loadValue(name, airshipData, "positionQuarter").split("/")[i].split(",");
            assert false;
            positionQuarter[i][0]=Integer.parseInt(positionQuarterBis[0]);
            positionQuarter[i][1]=Integer.parseInt(positionQuarterBis[1]);
        }

        CsvFileUser.readCSV("airshipData", airshipData);
    }


    //Construct the building at the selected place
    public void constructQuarter(String quarterName, int xPos, int yPos, boolean exception) {
        Quarter quarter = QuarterFactory.getQuarter(quarterName);
        assert quarter != null;
        if ((GlobalManager.getInstance().getBitResource().getAmount() >= quarter.getBitCost()) && (GlobalManager.getInstance().getCodeDataResource().getAmount() >= quarter.getCodeDataCost()) && (GlobalManager.getInstance().getCryptoMoneyResource().getAmount() >= quarter.getCryptomoneyCost()) && !exception) {
            GlobalManager.getInstance().getBitResource().subtractAmount(quarter.getBitCost());
            GlobalManager.getInstance().getCodeDataResource().subtractAmount(quarter.getCodeDataCost());
            GlobalManager.getInstance().getCryptoMoneyResource().subtractAmount(quarter.getCryptomoneyCost());
            quarterList[xPos][yPos] = quarter;
            numberQuarter++;
        }
        else if (exception) {
            quarterList[xPos][yPos] = quarter;
            numberQuarter++;
        }
    }

    //Get a list of the four adjacent quarters of this quarter
    public Quarter[] getAdjacent(Quarter quarter) {
        Quarter[] adjacentQuarter = new Quarter[4];
        int xPos = quarter.getX();
        int yPos = quarter.getY();
        adjacentQuarter[0] = quarterList[xPos+1][yPos];
        adjacentQuarter[1] = quarterList[xPos][yPos-1];
        adjacentQuarter[2] = quarterList[xPos-1][yPos];
        adjacentQuarter[3] = quarterList[xPos][yPos+1];
        return adjacentQuarter;
    }

    //Getter
    public String getName() {
        return name;
    }
    public Quarter[][] getQuarterList() {
        return quarterList;
    }
    public int getNumberQuarter() {
        return numberQuarter;
    }
    public int[][] getPositionQuarter() {
        return positionQuarter;
    }
    public LocalResourcesManager getLocalResources() {
        return localResources;
    }

    public int getBitCost() {
        return bitCost;
    }
    public int getCodeDataCost() {
        return codeDataCost;
    }
    public int getCryptomoneyCost() {
        return cryptomoneyCost;
    }
    public int getElectricityCost() {
        return electricityCost;
    }
    public int getFoodCost() {
        return foodCost;
    }
    public int getCrewCost() {
        return crewCost;
    }
    public int getConstructionTime() {
        return constructionTime;
    }
    public String[] getPrebuildQuarter1() {
        return prebuildQuarter1;
    }
    public String[] getPrebuildQuarter2() {
        return prebuildQuarter2;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
    public int getHealth() {
        return health;
    }
    public int getHull() {
        return hull;
    }
    public int getMaxHull() {
        return maxHull;
    }
}
