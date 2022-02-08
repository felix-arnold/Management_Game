package Quarter.ProductionQuarter;

import Quarter.Quarter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public abstract class ProductionQuarter extends Quarter {

    protected double productionPerCrewBonusRate;
    protected double productionBonusRate;
    protected double productionBonusConstant;
    protected double productionPerCrew;
    protected long productionType;

    static final List<String[]> ProductionBuildingsData = new ArrayList<>();

    public ProductionQuarter() {
        super();

        productionBonusConstant = 0;
        productionBonusRate = 1;
        productionPerCrewBonusRate = 1;


        //Reading a csv file and putting it into a List of String[]
        try (BufferedReader br = new BufferedReader(new FileReader("ProductionBuildingsData.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                ProductionBuildingsData.add(line.split(";"));
            }
        } catch (Exception e) {
        }
    }


    public void upgrade() {
        if (level < maxLevel) {
            super.upgrade();

        }
    }


    protected String loadValue(String name, int level, String valueResearched){
        int index=0;
        for (int j = 0; j < ProductionBuildingsData.get(0).length; j++) {
            if (ProductionBuildingsData.get(0)[j].equals(valueResearched)) {
                index = j;
            }
        }
        for (String[] element : ProductionBuildingsData) {
            if (element[0].equals(name + level)) {
                    return element[index];
                }
        }
        return "Error";
    }

    //Test functions
    public double getProductionBonusRate() {
        return productionBonusRate;
    }

    public double getProductionBonusConstant() {
        return productionBonusConstant;
    }

    public double getProductionPerCrew() {
        return productionPerCrew;
    }

    public long[] getProduction() {
        production[0]= productionType;
        production[1]=Math.round((long) productionPerCrew*crew*productionBonusRate+productionBonusConstant);
        return production;
    }

}
