package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class Galley extends ProductionQuarter {

    public Galley() {
        super();
        name = "Galley";
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "HellishBoss" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[7]) {
                    productionBonusConstant += adjQuarter.getProduction()[3];
                } else if (rnd >= adjQuarter.getProduction()[9]) {
                    productionBonusConstant -= adjQuarter.getProduction()[3];
                }
            }
            case "Restroom" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "Galley" -> productionPerCrewBonusRate += 0.025 * adjQuarter.getCrew();
            //case "TemporalCamboose", "Birdcatcher" -> productionBonusRate += 0.01 * adjQuarter.getLevel() * adjQuarter.getCrew();
            //case Berth
        }
    }
}
