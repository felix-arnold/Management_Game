package Quarter.ProductionQuarter;

import Quarter.Quarter;


public abstract class ProductionQuarter extends Quarter {

    public ProductionQuarter() {
        super();

        level=1;
        crew=0;
        productionBonusConstant=0;
        productionBonusRate=100;
    }

    protected int production;
    protected int productionPerCrew;
    protected int productionBonusRate;
    protected int productionBonusConstant;


    public int getProduction() {
        return production;
    }


    //Test functions
    public int getProductionBonusRate() {
        return productionBonusRate;
    }

    public int getProductionBonusConstant() {
        return productionBonusConstant;
    }
}
