package Quarter.ProductionQuarter;

import Quarter.Quarter;

import static java.lang.Math.min;

public class IASynthetisTank extends ProductionQuarter {

    public IASynthetisTank() {
        super();
        name = "IASynthesisTank";
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[13]) {
                    productionBonusConstant += adjQuarter.getProduction()[7];
                } else if (rnd >= adjQuarter.getProduction()[15]) {
                    productionBonusConstant -= adjQuarter.getProduction()[7];
                }
            }
            case "Restroom" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "TemporalCamboose" -> productionBonusRate += 0.05 * adjQuarter.getLevel() * adjQuarter.getCrew() /(adjQuarter.getLevel()+1);
        }
    }
}