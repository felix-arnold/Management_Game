package Quarter;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import static General.CsvFileUser.readCSV;

public abstract class Quarter {

    //Construction cost, consumption, upgrade cost, variable
    protected int level = 1;
    protected int maxLevel;
    protected int crew;

    //Taille des tableaux à changer
    protected int maxCrew;
    protected int bitCost;
    protected int codeDataCost;
    protected int cryptomoneyCost;
    protected int electricityConsumption;
    protected int foodConsumption;
    protected int cyptomoneyConsumptionPerCrew;

    protected long[] production = new long[2];

    protected Pane infoPane = new Pane();

    protected String style;
    protected String selectedStyle;

    protected String name;
    protected final Label nameLabel = new Label(name);

    protected int x;
    protected int y;

    protected boolean isUnlocked = true;

    protected boolean enabled = true;

    //Constructor
    public Quarter() {
        quarterInfoPane.getStyleClass().add("quarterInfoPane");
        quarterInfoPane.setLayoutY(200);
        quarterInfoPane.setLayoutX(800);
        quarterInfoPane.getChildren().add(nameLabel);
    }


    //For display

    private final GridPane quarterInfoPane = new GridPane();
    public GridPane getQuarterInfoPane() {
        return quarterInfoPane;
    }

    protected ImageView quarterIcon;
    protected ImageView selectedQuarterIcon;
    public ImageView getQuarterIcon() {
        return quarterIcon;
    }
    public ImageView getSelectedQuarterIcon() {
        return selectedQuarterIcon;
    }

    protected int[] indexConstructionPane;
    public int[] getIndexConstructionPane() {
        return indexConstructionPane;
    }



    public int getCrew() {
        return crew;
    }

    public int getLevel() {
        return level;
    }

    public void addCrew() {
        crew++;
    }

    public void removeCrew() {
        crew--;
    }


    public void upgrade() {
        //RAJOUTER SI LOCK PAR TECH
        level++;
    }

    public long getCryptomoneyConsumption() {
        return 1;
    }


    public void setX(int xPos) {
        x=xPos;
    }

    public void setY(int yPos) {
        y=yPos;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Test functions
    public int getMaxCrew() {
        return maxCrew;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getCodeDataCost() {
        return codeDataCost;
    }

    public int getBitCost() {
        return bitCost;
    }

    public int getCryptomoneyCost() {
        return cryptomoneyCost;
    }

    public int getElectricityConsumption() {
        return electricityConsumption;
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

    public int getFoodConsumption() {
        return foodConsumption;
    }

    public void unlock() {
        isUnlocked = true;
    }

    public void disable() {
        enabled = false;
    }

    public void enable() {
        enabled = true;
    }

    public boolean isEnable() {
        return enabled;
    }

    public boolean isUnlock(){
        return isUnlocked;
    }
}
