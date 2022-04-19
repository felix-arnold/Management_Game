package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class InternetFiberProvider extends Quarter{

    //PAS UN BATIMENT DE PROD

    public InternetFiberProvider(int level) {

        super(level);
        name = "Internet Fiber Provider";
        loadConstructionInfoPaneValue();
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        if ("Berth".equals(adjQuarter.getName())) {
            //0.05 * adjQuarter.getLevel();
        }
    }
}