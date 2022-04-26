package Management.Quarter.ProductionQuarter;

import Management.Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class wraps the property of a TemporalCaboose.
 */
public class TemporalCaboose extends ProductionQuarter{

    //production[food,food, bonusfood, bonusfood]

    /**
     * Creates a new instance of TemporalCaboose.
     * @param level the level of the quarter
     */
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
    /**
     * Computes the bonus inherited from some specific adjacent quarter.
     * @param adjQuarter an adjacent quarter
     */
    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getTrueName()) {
            case "TemporalCaboose" -> productionBonusConstant += adjQuarter.getProduction()[1]*0.3;
            case "Birdcatcher" -> {
                productionPerCrewBonusRate += 0.02*adjQuarter.getCrew();
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
