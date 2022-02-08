package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class ProgrammersOffice extends ProductionQuarter {

    public ProgrammersOffice() {

        super();
        name = "ProgrammerOffice";
        productionType=5; //5=dataCode
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "InternetFiberProvider" -> productionPerCrewBonusRate += 0.01 * adjQuarter.getCrew();
            case "Restroom" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "HellishBoss" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[3] * adjQuarter.getLevel()) {
                    productionBonusConstant += adjQuarter.getProduction()[1];
                } else if (rnd >= 100-(adjQuarter.getProduction()[5])) {
                    productionBonusConstant -= adjQuarter.getProduction()[1];
                }
            }
        }
    }

}
