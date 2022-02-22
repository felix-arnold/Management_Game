package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class Cryptomine extends ProductionQuarter {

    public Cryptomine() {

        super();
        name = "Cryptomine";
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "Cryptomine" -> productionPerCrewBonusRate -= 0.01 * adjQuarter.getCrew();
            case "Restroom" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "Cryptoinvestor" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[3]) {
                    productionBonusConstant += adjQuarter.getProduction()[1];
                } else if (rnd >= adjQuarter.getProduction()[5]) {
                    productionBonusConstant -= adjQuarter.getProduction()[1];
                }
            }
            case "ParadoxalGenerator" -> productionBonusRate += 0.05 * adjQuarter.getLevel();
            case "InternetFiberProvider" -> productionBonusRate += 0.01 * adjQuarter.getCrew();
        }
    }
}
