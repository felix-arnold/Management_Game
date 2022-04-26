package Management.Quarter.ProductionQuarter;

import Management.Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class wraps the property of a ParadoxalGenerator.
 */
public class ParadoxalGenerator extends ProductionQuarter {
    /**
     * Creates a new instance of ParadoxalGenerator.
     * @param level the level of the quarter
     */
    public ParadoxalGenerator(int level) {

        super(level);
        name = "Paradoxal Generator";
        trueName = "ParadoxalGenerator";
        quarterIcon = new ImageView(new Image("paradoxalGeneratorIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedParadoxalGeneratorIcon.png"));
        indexConstructionPane = new int[]{0, 0, 1};
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
            case "ParadoxalGenerator" -> productionBonusConstant += 8 * 3 * adjQuarter.getCrew() * adjQuarter.getLevel();
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < 0.3) {
                    productionBonusRate += 0.1*adjQuarter.getLevel();
                } else if (rnd >= 0.9) {
                    productionBonusRate -= 0.1*adjQuarter.getLevel();
                }
            }
            case "DimensionlessSpace" -> productionBonusConstant += adjQuarter.getProduction()[1];
        }
    }
}
