package Quarter.ProductionQuarter;

import Quarter.Quarter;

public class TemporalCaboose extends ProductionQuarter{

    //production[food,food, bonusfood, bonusfood]

    double food;

    int bonusStack=0;

    public TemporalCaboose() {

        super();
        name = "TemporalCaboose";
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "TemporalCaboose" -> productionBonusConstant += adjQuarter.getProduction()[3];
            case "Birdcatcher" -> {
                if (bonusStack<=10*level & food>0) {
                    bonusStack++;
                    productionBonusConstant--;
                }
                productionPerCrewBonusRate += 0.02*bonusStack;
            }
            case "Restroom" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[13] * adjQuarter.getLevel()) {
                    productionBonusRate += adjQuarter.getProduction()[5];
                } else if (rnd >= adjQuarter.getProduction()[15]) {
                    productionBonusRate -= adjQuarter.getProduction()[5];
                }
            }
            case "DimensionlessSpace" -> productionBonusRate -= 0.1*adjQuarter.getLevel();
        }
    }
}
