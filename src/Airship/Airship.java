package Airship;

import General.GlobalManager;
import General.LocalResourcesManager;
import Quarter.Quarter;
import Quarter.QuarterFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

    static final List<String[]> AirshipsData = new ArrayList<>();

    //Constructor
    public Airship(String name) {
        this.name = name;
        bitCost = Integer.parseInt(loadValue(name, "bitCost"));
        codeDataCost = Integer.parseInt(loadValue(name, "codeDataCost"));
        cryptomoneyCost = Integer.parseInt(loadValue(name, "cryptomoneyCost"));
        electricityCost = Integer.parseInt(loadValue(name,"electricityCost"));
        foodCost = Integer.parseInt(loadValue(name,"foodCost"));
        crewCost = Integer.parseInt(loadValue(name,"crewCost"));
        constructionTime = Integer.parseInt(loadValue(name, "bitCost"));
        maxHealth = Integer.parseInt(loadValue(name, "maxHealth"));
        maxHull = Integer.parseInt(loadValue(name, "maxHull"));

        health=maxHealth;
        hull=maxHull;

        prebuildQuarter1 = loadValue(name, "prebuildQuarter1").split(",");
        prebuildQuarter2 = loadValue(name, "prebuildQuarter2").split(",");

        //load positionQuarter as an array of values
        String[] positionQuarterBis = loadValue(name, "positionQuarter").split("/");
        for (int i=0; i<positionQuarterBis.length; i++) {
            String[] positionQuarterBisBis=positionQuarterBis[i].split(",");
            positionQuarter[i][0]=Integer.parseInt(positionQuarterBisBis[0]);
            positionQuarter[i][1]=Integer.parseInt(positionQuarterBisBis[1]);
        }

        //Read .csv file
        try (BufferedReader br = new BufferedReader(new FileReader("AirshipsData.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                AirshipsData.add(line.split(";"));
            }
        } catch (Exception e) {
        }
    }

    //Load the value in AirshipsData
    protected String loadValue(String name, String valueResearched){
        int index=0;
        for (int j = 0; j < AirshipsData.get(0).length; j++) {
            if (AirshipsData.get(0)[j].equals(valueResearched)) {
                index = j;
            }
        }
        for (String[] element : AirshipsData) {
            if (element[0].equals(name)) {
                return element[index];
            }
        }
        return "Error reading Airship file";
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
    public LocalResourcesManager getLocalResources() {
        return localResources;
    }
}
