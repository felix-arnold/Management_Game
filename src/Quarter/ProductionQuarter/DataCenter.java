package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class DataCenter extends ProductionQuarter {

    //production[7science,science]

    public DataCenter() {

        super();
        name = "DataCenter";
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "Datacenter" -> productionBonusConstant += 2 * level * crew;
            case "Restroom" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[13]) {
                    productionBonusConstant += adjQuarter.getProduction()[9];
                } else if (rnd >= adjQuarter.getProduction()[15]) {
                    productionBonusConstant -= adjQuarter.getProduction()[9];
                }
            }
            case "InternetFiberProvider" -> {
                double rnd = Math.random();
                if (rnd > 80 + 5 * adjQuarter.getLevel()) {
                    productionBonusRate -= 0.5;
                } else {
                    productionBonusRate += 0.05 * adjQuarter.getCrew();
                }
            }
            case "VirtualQuantumComputer" -> productionBonusRate += adjQuarter.getProduction()[5];
        }
    }
}
