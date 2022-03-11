package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class MadScientist extends ProductionQuarter{

    //production[0VirtualQuantumComputer, 1VirtualQuantumComputer, 2ParadoxalGenerator, 3ParadoxalGenerator, 4TemporalCamboose, 5TemporalCamboose,
    //6IaSynthesisTank, 7IaSynthesisTank, 8DataCentre, 9DataCentre, 10DimensionlessSpace, 11dimensionlessSpace, 12Probawin, 13Probawin, 14Probalose, 15Probalose]

    public MadScientist() {

        super();
        name = "MadScientist";
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < 0.2) {
                    productionPerCrewBonusRate += (0.6 - 0.2 * (level-adjQuarter.getLevel())) * adjQuarter.getCrew();
                } else if (rnd >= 0.4) {
                    productionPerCrewBonusRate -= (0.9 - 0.3 * (level-adjQuarter.getLevel())) * adjQuarter.getCrew();
                }
            }
            case "Restroom" -> productionPerCrewBonusRate -= 0.1*adjQuarter.getLevel();
            case "VirtualQuantumComputer" -> productionPerCrewBonusRate += adjQuarter.getProduction()[1];
            case "HellishBoss" -> productionPerCrewBonusRate -= 0.5*adjQuarter.getCrew();
            case "CryptoInvestor" -> productionPerCrewBonusRate -= 0.05*adjQuarter.getCrew();
        }
    }
}
