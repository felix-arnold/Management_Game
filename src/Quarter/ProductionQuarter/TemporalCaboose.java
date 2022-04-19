package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TemporalCaboose extends ProductionQuarter{

    //production[food,food, bonusfood, bonusfood]

    double food;

    int bonusStack=0;

    public TemporalCaboose(int level) {

        super(level);
        name = "Temporal Caboose";
        trueName = "TemporalCaboose";
        quarterIcon = new ImageView(new Image("temporalCabooseIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedTemporalCabooseIcon.png"));
        indexConstructionPane = new int[]{0, 1, 0};
        loadAllValues();
        loadConstructionInfoPaneValue();
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getTrueName()) {
            case "TemporalCaboose" -> productionBonusConstant += adjQuarter.getProduction()[1]*0.3;
            case "Birdcatcher" -> {
                if (bonusStack<=10*level & food>0) {
                    bonusStack++;
                    productionBonusConstant--;
                }
                productionPerCrewBonusRate += 0.02*bonusStack;
            }
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < 0.3) {
                    productionBonusRate += 0.1*adjQuarter.getLevel();
                } else if (rnd >= 0.9) {
                    productionBonusRate -= 0.1*adjQuarter.getLevel();
                }
            }
            case "DimensionlessSpace" -> productionBonusRate -= 0.1*adjQuarter.getLevel();
        }
    }
}
