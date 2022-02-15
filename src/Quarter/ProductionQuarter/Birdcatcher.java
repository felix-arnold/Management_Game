package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class Birdcatcher extends ProductionQuarter {

    public Birdcatcher() {

        super();
        name = "Birdcatcher";
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "Birdcatcher" -> productionBonusConstant += adjQuarter.getLevel()-1 + adjQuarter.getCrew();
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[7] * adjQuarter.getLevel()) {
                    productionBonusConstant += adjQuarter.getProduction()[3];
                } else if (rnd >= 100 - (adjQuarter.getProduction()[9])) {
                    productionBonusConstant -= adjQuarter.getProduction()[3];
                }
            }
            case "Restroom" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
        }
    }

}
