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

    protected long production;
    protected String productionType;

    protected Pane infoPane = new Pane();

    protected String style;
    protected String selectedStyle;

    protected String name;


    public Quarter() {
        infoPane.setPrefSize(200,500);
        infoPane.setLayoutX(1340);
        infoPane.setLayoutY(50);

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

    //ISADJACENT DOIT SE RECALCULER CHAQUE FOIS QUE L'ON UP/CONSTRUIT/DETRUIT UN QUARTER ET PAS A CHAQUE TOUR -> CREER FONCTION RESET ET FONCTION POUR TOUT CALCULER
    public abstract void isAdjacent(int quarterAdjacentType, int crewAdjacent, int levelAdjacent, int parameterAdjacent);

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

    public long getProduction() {
        return production;
    }

    public String getProductionType (){
        return productionType;
    }
}
