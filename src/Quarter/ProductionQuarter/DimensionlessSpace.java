package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class DimensionlessSpace extends ProductionQuarter {

    //prodution[1Paradoxalgenerator,2paradoxalGenerator]

    public DimensionlessSpace() {
        super();
        name="DimensionlessSpace";
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "Restroom" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[13]) {
                    productionBonusConstant += adjQuarter.getProduction()[11];
                } else if (rnd >= adjQuarter.getProduction()[15]) {
                    productionBonusConstant -= adjQuarter.getProduction()[11];
                }
            }
            case "TemporalCaboose" -> productionBonusRate -= 0.1*adjQuarter.getLevel();
        }
    }
}
