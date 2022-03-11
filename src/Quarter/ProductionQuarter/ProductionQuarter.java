package Quarter.ProductionQuarter;

import General.CsvFileUser;
import Quarter.Quarter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static General.CsvFileUser.*;


public abstract class ProductionQuarter extends Quarter {

    protected double productionPerCrewBonusRate;
    protected double productionBonusRate;
    protected double productionBonusConstant;
    protected double productionPerCrew;
    protected long productionType;

    static final List<String[]> productionBuildingsData = new ArrayList<>();

    public ProductionQuarter() {
        super();

        productionBonusConstant = 0;
        productionBonusRate = 1;
        productionPerCrewBonusRate = 1;

        readCSV("productionBuildingsData.csv", productionBuildingsData);
    }


    public void upgrade() {
        if (level < maxLevel) {
            super.upgrade();

        }
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
        return production;
    }

}
