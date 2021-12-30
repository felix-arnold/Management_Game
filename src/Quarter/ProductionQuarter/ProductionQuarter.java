package Quarter.ProductionQuarter;

import Quarter.Quarter;


public abstract class ProductionQuarter extends Quarter {

    public ProductionQuarter() {
        super();

        level=1;
        crew=0;
        productionBonusConstant=0;
        productionBonusRate=1;
    }

    protected double[] productionPerCrew;
    protected double productionBonusRate;
    protected double productionBonusConstant;

    public void upgrade(){
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
        return productionPerCrew[level-1];
    }

    public long getProduction() {
        production = Math.round(productionPerCrew[level-1]*crew*productionBonusRate+productionBonusConstant);
        return production;
    }

}
