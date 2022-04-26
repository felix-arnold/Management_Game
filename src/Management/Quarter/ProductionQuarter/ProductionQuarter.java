package Management.Quarter.ProductionQuarter;

import FileReaders.CsvFileUser;
import Management.Quarter.Quarter;

import java.util.ArrayList;
import java.util.List;

import static FileReaders.CsvFileUser.*;

/**
 * The parent abstract class of every quarter which produce or boost the production of resources.
 */
public abstract class ProductionQuarter extends Quarter {

    /**
     * Rate applied to the production of each crew member of the quarter.
     */
    protected double productionPerCrewBonusRate=0;
    /**
     * Rate applied to the total production of the quarter.
     */
    protected double productionBonusRate=1;
    /**
     * Flat production added to the crew production.
     */
    protected double productionBonusConstant=0;


    /**
     * Content of the CSV file containing the data of all productionQuarter.
     */
    protected final List<String[]> productionBuildingsData = new ArrayList<>();


    /**
     * Inherits from the Quarter class.
     * @param level the level of the quarter
     */
    public ProductionQuarter(int level) {
        super(level);
    }

    /**
     * Return the production of the quarter.
     */
    public long[] getProduction() {
        return production;
    }

    /**
     * Load the properties of the quarter by reading them in the productionBuildingsData.
     */
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

    /**
     * Computes the production of the quarter.
     * <br> The production depends on the production per crew member and the number of crew member attributed to the quarter.
     * It also takes into account the productionPerCrewBonusRate, productionBonusConstant and productionBonusRate properties.
     */
    @Override
    public void baseCalculationProduction() {
        production[0] = productionPerCrew[0];
        production[1] = (long) ((productionPerCrew[1] * crew * productionPerCrewBonusRate + productionBonusConstant)*productionBonusRate);
    }
}
