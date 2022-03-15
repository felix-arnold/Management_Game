package General;

import Quarter.ProductionQuarter.*;
import Quarter.*;
import java.util.ArrayList;
import java.util.List;

import static General.CsvFileUser.*;
import static java.lang.Math.abs;

public class Airship {

    private final String name;
    private final int numberOfRaws;
    private final int numberOfColumns;
    private ArrayList<int[]> positionQuarter = new ArrayList<>();
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

    int foodDiversityProductionBonus = 0;
    int foodQuantityProductionBonus = 0;
    int foodQuantityRecruitmentCost = 0;
    int foodQuantityConstructionCost = 0;
    int foodQuantityHealthBonus = 0;
    int foodQualityProductionBonus = 0;
    int foodQualityDamageBonus = 0;

    ArrayList<int[]> disabledQuarterList = new ArrayList<int[]>();

    static final List<String[]> airshipData = new ArrayList<>();


    //Constructor
    public Airship(String name) {

        CsvFileUser.readCSV("AirshipTest.csv", airshipData);

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
        numberOfColumns = Integer.parseInt(loadValue(name, airshipData, "numberOfLines"));
        numberOfRaws = Integer.parseInt(loadValue(name, airshipData, "numberOfRaws"));
        quarterList = new Quarter[numberOfRaws][numberOfColumns];

        health=maxHealth;
        hull=maxHull;

        prebuildQuarter1 = loadValue(name, airshipData, "prebuildQuarter1").split(",");
        prebuildQuarter2 = loadValue(name, airshipData, "prebuildQuarter2").split(",");

        //load positionQuarter as an array of values
        for (int i=0; i<loadValue(name, airshipData, "positionQuarter").split("/").length; i++) {
            String[] positionQuarterBis=loadValue(name, airshipData, "positionQuarter").split("/")[i].split(",");
            assert false;
            positionQuarter.add(new int[]{Integer.parseInt(positionQuarterBis[0]), Integer.parseInt(positionQuarterBis[1])});
        }
    }


    //Construct the building at the selected place
    public void constructQuarter(String quarterName, int xPos, int yPos, boolean resourcesException) {
        Quarter quarter = QuarterFactory.getQuarter(quarterName);
        assert quarter != null;
        if ((GlobalManager.getInstance().getBitResource().getAmount() >= quarter.getBitCost()) && (GlobalManager.getInstance().getCodeDataResource().getAmount() >= quarter.getCodeDataCost()) && (GlobalManager.getInstance().getCryptoMoneyResource().getAmount() >= quarter.getCryptomoneyCost()) && !resourcesException) {
            GlobalManager.getInstance().getBitResource().subtractAmount(quarter.getBitCost());
            GlobalManager.getInstance().getCodeDataResource().subtractAmount(quarter.getCodeDataCost());
            GlobalManager.getInstance().getCryptoMoneyResource().subtractAmount(quarter.getCryptomoneyCost());
            quarterList[xPos][yPos] = quarter;
            numberQuarter++;
        }
        else if (resourcesException) {
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


    //Calculate the food bonuses
    public void calculateFoodBonus() {
        //Galley + diversity
        long temporalCabooseProduction=0;
        long birdCatcherProduction=0;
        for (Quarter[] iQuarter : quarterList) {
            for (Quarter jQuarter : iQuarter) {
                if (jQuarter instanceof Galley) {
                    foodQualityProductionBonus += (int) jQuarter.getProduction()[1];
                    foodQualityDamageBonus += (int) jQuarter.getProduction()[3];
                }
                else if (jQuarter instanceof TemporalCaboose) {
                    temporalCabooseProduction = jQuarter.getProduction()[1];
                }
                else if (jQuarter instanceof Birdcatcher) {
                    birdCatcherProduction = jQuarter.getProduction()[1];
                }
            }
        }
        if (foodQualityProductionBonus > 20) {
            foodQualityProductionBonus = 20;
        }
        if (foodQualityDamageBonus > 1) {
            foodQualityDamageBonus = 1;
        }
        if ((temporalCabooseProduction > 0) && (birdCatcherProduction > 0) && (temporalCabooseProduction > birdCatcherProduction/2) && (birdCatcherProduction > temporalCabooseProduction/2)) {
            foodDiversityProductionBonus = 10;
        }
        else {
            foodDiversityProductionBonus = 0;
        }
        //Quantity
        if (localResources.getFoodResource().getAmount() <= -50) {
            foodQuantityProductionBonus = -60;
            foodQuantityRecruitmentCost = -50;
            foodQuantityConstructionCost = -40;
            foodQuantityHealthBonus = -2;
        } else if (localResources.getFoodResource().getAmount() > -50 && localResources.getFoodResource().getAmount() <= -30) {
            foodQuantityProductionBonus = -40;
            foodQuantityRecruitmentCost = -30;
            foodQuantityConstructionCost = -25;
            foodQuantityHealthBonus = -1;
        } else if (localResources.getFoodResource().getAmount() > -30 && localResources.getFoodResource().getAmount() <= -15) {
            foodQuantityProductionBonus = -20;
            foodQuantityRecruitmentCost = -10;
            foodQuantityConstructionCost = -10;
        } else if (localResources.getFoodResource().getAmount() > -15 && localResources.getFoodResource().getAmount() < 0) {
            foodQuantityProductionBonus = -10;
            foodQuantityConstructionCost = -5;
        } else if (localResources.getFoodResource().getAmount() >= 0 && localResources.getFoodResource().getAmount() <= 15) {
            foodQuantityProductionBonus = 5;
        } else if (localResources.getFoodResource().getAmount() > 15 && localResources.getFoodResource().getAmount() <= 30) {
            foodQuantityProductionBonus = 10;
            foodQuantityConstructionCost = 5;
        } else if (localResources.getFoodResource().getAmount() > 30 && localResources.getFoodResource().getAmount() <= 50) {
            foodQuantityProductionBonus = 15;
            foodQuantityConstructionCost = 10;
            foodQuantityRecruitmentCost = 10;
        } else if (localResources.getFoodResource().getAmount() > 50) {
            foodQuantityProductionBonus = 20;
            foodQuantityConstructionCost = 15;
            foodQuantityRecruitmentCost = 20;
            foodQuantityHealthBonus = 1;
        }
    }


    //Manage overconsumption of electricity by disabling or enabling quarter
    public void manageElectricityOverconsumption() {
        if (localResources.getElectricityResource().getAmount() < 0) {
            while (localResources.getElectricityResource().getAmount() < 0) {
                int r1 = (int) abs(Math.random()*numberOfRaws);
                int r2 = (int) abs(Math.random()*numberOfColumns);
                if (quarterList[r1][r2] != null && quarterList[r1][r2].getElectricityConsumption() == 0 && quarterList[r1][r2].isEnable()) {
                    quarterList[r1][r2].disable();
                    int[] index = {r1, r2};
                    disabledQuarterList.add(index);
                    localResources.getElectricityResource().addAmount(quarterList[r1][r2].getElectricityConsumption());
                }
            }
        }
        else if (localResources.getElectricityResource().getAmount() > 0 && !disabledQuarterList.isEmpty()) {
            while (!disabledQuarterList.isEmpty()) {
                ArrayList<Integer> canEnableQuarterIndexList = new ArrayList<>();
                for (int i = 0; i < disabledQuarterList.size(); i++) {
                    if (quarterList[disabledQuarterList.get(i)[0]][disabledQuarterList.get(i)[1]].getElectricityConsumption() < localResources.getElectricityResource().getAmount()) {
                        canEnableQuarterIndexList.add(i);
                    }
                }
                int r = (int) abs(Math.random() * canEnableQuarterIndexList.size());
                quarterList[disabledQuarterList.get(canEnableQuarterIndexList.get(r))[0]][disabledQuarterList.get(canEnableQuarterIndexList.get(r))[1]].enable();
                disabledQuarterList.remove((int) canEnableQuarterIndexList.get(r));
            }
        }
    }


    //Add or remove crew from a building
    public void addCrew(Quarter quarter) {
        if (localResources.availableCrewResource.getAmount()>=100) {
            if (quarter.getCrew() < quarter.getMaxCrew()) {
                quarter.addCrew();
                localResources.availableCrewResource.subtractAmount(100);
            }
        }
    }

    public void removeCrew(Quarter quarter) {
        if (quarter.getCrew()>0) {
            quarter.removeCrew();
            localResources.availableCrewResource.addAmount(100);
        }
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

    public ArrayList<int[]> getPositionQuarter() {
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
