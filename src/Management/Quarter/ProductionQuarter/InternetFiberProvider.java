package Management.Quarter.ProductionQuarter;

import Management.Quarter.Quarter;

/**
 * This class wraps the property of a InternetFiberProvider.
 */
public class InternetFiberProvider extends Quarter{

    //PAS UN BATIMENT DE PROD
    /**
     * Creates a new instance of InternetFiberProvider.
     * @param level the level of the quarter
     */
    public InternetFiberProvider(int level) {

        super(level);
        name = "Internet Fiber Provider";
        loadConstructionInfoPaneValue();
    }
    /**
     * Computes the bonus inherited from some specific adjacent quarter.
     * @param adjQuarter an adjacent quarter
     */
    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        if ("Berth".equals(adjQuarter.getTrueName())) {
            //0.05 * adjQuarter.getLevel();
        }
    }
}