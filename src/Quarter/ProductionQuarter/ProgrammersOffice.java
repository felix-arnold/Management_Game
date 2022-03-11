package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class ProgrammersOffice extends ProductionQuarter {

    //production[5dataCode,dataCode]

    public ProgrammersOffice() {

        super();
        name = "ProgrammerOffice";
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "InternetFiberProvider" -> productionPerCrewBonusRate += 0.01 * adjQuarter.getCrew();
            case "Restroom" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "HellishBoss" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[7]) {
                    productionBonusConstant += adjQuarter.getProduction()[1];
                } else if (rnd >= adjQuarter.getProduction()[9]) {
                    productionBonusConstant -= adjQuarter.getProduction()[1];
                }
            }
        }
    }

}
