package Quarter;

public abstract class Quarter {

    protected int crew;
    protected int maxCrew;
    protected int level;
    protected int maxLevel;

    //Construction costs
    protected int codeDataCost;
    protected int bitCost;
    protected int cryptomoneyCost;
    protected int electricityConsumption;

    //Upgrade to level 2 costs and consumption
    protected int codeDataCost1Upgrade;
    protected int bitCost1Upgrade;
    protected int cryptomoneyCost1Upgrade;
    protected int electricityConsumption1Upgrade;

    // Upgrade to level 3 costs and consumption
    protected int codeDataCost2Upgrade;
    protected int bitCost2Upgrade;
    protected int cryptomoneyCost2Upgrade;
    protected int electricityConsumption2Upgrade;

    //Upgrade to level 4 costs and consumption
    protected int codeDataCost3Upgrade;
    protected int bitCost3Upgrade;
    protected int cryptomoneyCost3Upgrade;
    protected int electricityConsumption3Upgrade;


    public int getCrew() {
        return crew;
    }

    public int getLevel() {
        return level;
    }

    public void crewUp() {
        if (crew<maxCrew) {
            crew++;
        }
    }

    public void crewDown() {
        crew--;
    }

    public abstract void isAdjacent(int quarterAdjacentType, int crewAdjacent, int levelAdjacent, int parameterAdjacent);

    public void upgrade() {
        //RAJOUTER SI LOCK PAR TECH
        level++;
        if (level == 2) {
            electricityConsumption=electricityConsumption1Upgrade;
        }
        else if (level == 3) {
            electricityConsumption=electricityConsumption2Upgrade;
        }
        else if (level == 4) {
            electricityConsumption=electricityConsumption3Upgrade;
        }
    }

    public void construct() {

    }

    //Test functions
    public int getMaxCrew() {
        return maxCrew;
    }

    public int getMaxLevel() {
        return maxLevel;
    }
}
