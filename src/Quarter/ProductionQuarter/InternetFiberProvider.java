package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class InternetFiberProvider extends Quarter{

    //PAS UN BATIMENT DE PROD

    public InternetFiberProvider() {

        super();
        name = "InternetFiberProvider";
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        if ("Berth".equals(adjQuarter.getName())) {
            //0.05 * adjQuarter.getLevel();
        }
    }
}