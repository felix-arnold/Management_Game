package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class InternetFiberProvider extends ProductionQuarter{

    public InternetFiberProvider() {

        super();
        name = "InternetFiberProvider";
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        if ("Restroom".equals(adjQuarter.getName())) {
            productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
        }
    }
}
