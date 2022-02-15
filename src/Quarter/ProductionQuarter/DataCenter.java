package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class DataCenter extends ProductionQuarter {

    public DataCenter() {

        super();
        name = "DataCenter";
        productionType = 7; //7=science
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "Datacenter" -> productionBonusConstant += 2 * level * crew;
            case "Restroom" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[3] * adjQuarter.getLevel()) {
                    productionBonusConstant += adjQuarter.getProduction()[1];
                } else if (rnd >= 100 - (adjQuarter.getProduction()[5])) {
                    productionBonusConstant -= adjQuarter.getProduction()[1];
                }
            }
            case "InternetFiberProvider" -> productionPerCrewBonusRate += 0.01 * adjQuarter.getCrew();
            case "VirtualQuantumComputer" -> productionBonusRate += adjQuarter.getProduction()[1];
        }
    }
}
