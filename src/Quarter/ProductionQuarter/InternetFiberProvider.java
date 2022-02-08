package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class InternetFiberProvider extends ProductionQuarter{

    public InternetFiberProvider() {

        super();
        name = "ParadoxalGenerator";
        productionType=1; //1=electricity
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "ParadoxalGenerator" -> productionBonusConstant += 25 * level * crew;
            case "Restroom" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();      //15% de prod
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[3] * adjQuarter.getLevel()) {
                    productionBonusConstant += adjQuarter.getProduction()[1];
                } else if (rnd >= 100-(adjQuarter.getProduction()[5])) {
                    productionBonusConstant -= adjQuarter.getProduction()[1];
                }
            }
            case "DimensionlessSpace" -> productionBonusRate += adjQuarter.getProduction()[1];
        }
    }
}
