package Quarter;

import javafx.scene.layout.Pane;

public abstract class Quarter {

    //Construction cost, consumption, upgrade cost, variable
    protected int level;
    protected int maxLevel;
    protected int crew;

    protected int[] maxCrew;
    protected int[] bitCost;
    protected int[] codeDataCost;
    protected int[] cryptomoneyCost;
    protected int[] electricityConsumption;

    protected long[] production;

    protected Pane infoPane = new Pane();

    protected String style;
    protected String selectedStyle;

    protected String name;
    private int xPos;
    private int yPos;


    public Quarter(int xPos, int yPos) {
        infoPane.setPrefSize(200,500);
        infoPane.setLayoutX(1340);
        infoPane.setLayoutY(50);
        this.xPos = xPos;
        this.yPos = yPos;
    }


    public int getCrew() {
        return crew;
    }

    public int getLevel() {
        return level;
    }

    public void crewUp() {
        if (crew<maxCrew[level-1]) {
            crew++;
        }
    }

    public void crewDown() {
        crew--;
    }

    public void upgrade() {
        //RAJOUTER SI LOCK PAR TECH
        level++;
    }

    public void construct() {

    }

    //Test functions
    public int getMaxCrew() {
        return maxCrew[level-1];
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getCodeDataCost() {
        return codeDataCost[level-1];
    }

    public int getBitCost() {
        return bitCost[level-1];
    }

    public int getCryptomoneyCost() {
        return cryptomoneyCost[level-1];
    }

    public int getElectricityConsumption() {
        return electricityConsumption[level-1];
    }

    public Pane getInfoPane() {
        return infoPane;
    }

    public String getStyle() {
        return style;
    }

    public String getSelectedStyle() {
        return selectedStyle;
    }

    public long[] getProduction() {
        return production;
    }

    public abstract void adjacentBonuses(Quarter adjQuarter);

    public String getName() {
        return name;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
