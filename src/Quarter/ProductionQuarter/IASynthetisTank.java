package Quarter.ProductionQuarter;

import Quarter.Quarter;

import static java.lang.Math.min;

public class IASynthetisTank extends ProductionQuarter {

    public IASynthetisTank() {
        super();
        name = "IASynthesisTank";
        style = "IASynthiesisTank";
        selectedStyle = "selectedIASynthesisTank";

    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {

    }

}