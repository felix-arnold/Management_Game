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


    protected final List<String[]> productionBuildingsData = new ArrayList<>();


    public ProductionQuarter(int level) {
        super(level);

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

    public long[] getProduction() {
        return production;
    }

    protected void loadAllValues() {
        CsvFileUser.readCSV("ProductionBuildingsData.csv", productionBuildingsData);
        maxCrew=Integer.parseInt(loadValue(trueName+String.valueOf(level),productionBuildingsData, "baseMaxCrew"));
        maxLevel=Integer.parseInt(loadValue(trueName+String.valueOf(level),productionBuildingsData, "maxLevel"));
        bitCost=Integer.parseInt(loadValue(trueName+String.valueOf(level),productionBuildingsData, "bitCost"));
        codeDataCost=Integer.parseInt(loadValue(trueName + String.valueOf(level),productionBuildingsData, "codeDataCost"));
        cryptomoneyCost=Integer.parseInt(loadValue(trueName + String.valueOf(level),productionBuildingsData, "cryptomoneyCost"));
        electricityConsumption=Integer.parseInt(loadValue(trueName + String.valueOf(level),productionBuildingsData, "baseElectricityConsumption"));
        cyptomoneyConsumptionPerCrew=Integer.parseInt(loadValue(trueName + String.valueOf(level),productionBuildingsData, "baseCyptomoneyConsumptionPerCrew"));
        productionPerCrew[0]=Long.parseLong(loadValue(trueName + String.valueOf(level), productionBuildingsData, "production").split("!")[0]);
        productionPerCrew[1]=Long.parseLong(loadValue(trueName + String.valueOf(level), productionBuildingsData, "production").split("!")[1]);

    }

    @Override
    public void baseCalculationProduction() {
        production[0] = productionPerCrew[0];
        production[1] = (long) ((productionPerCrew[1] * crew * productionPerCrewBonusRate + productionBonusConstant)*productionBonusRate);
    }
}
