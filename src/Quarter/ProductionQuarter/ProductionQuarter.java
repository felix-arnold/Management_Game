package Quarter.ProductionQuarter;

import General.CsvFileUser;
import Quarter.Quarter;

import java.util.ArrayList;
import java.util.List;

import static General.CsvFileUser.*;


public abstract class ProductionQuarter extends Quarter {

    protected double productionPerCrewBonusRate;
    protected double productionBonusRate;
    protected double productionBonusConstant;
    protected double productionPerCrew;
    protected long productionType;


    protected final List<String[]> productionBuildingsData = new ArrayList<>();


    public ProductionQuarter() {
        super();

        productionBonusConstant = 0;
        productionBonusRate = 1;
        productionPerCrewBonusRate = 1;
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

    protected void loadAllValues() {
        CsvFileUser.readCSV("ProductionBuildingsData.csv", productionBuildingsData);
        bitCost=Integer.parseInt(loadValue(name+String.valueOf(1),productionBuildingsData, "bitCost"));
        codeDataCost=Integer.parseInt(loadValue(name + String.valueOf(level),productionBuildingsData, "codeDataCost"));
        cryptomoneyCost=Integer.parseInt(loadValue(name + String.valueOf(level),productionBuildingsData, "cryptomoneyCost"));
        electricityConsumption=Integer.parseInt(loadValue(name + String.valueOf(level),productionBuildingsData, "baseElectricityConsumption"));
        cyptomoneyConsumptionPerCrew=Integer.parseInt(loadValue(name + String.valueOf(level),productionBuildingsData, "baseCyptomoneyConsumptionPerCrew"));
    }

}
