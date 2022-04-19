package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ParadoxalGenerator extends ProductionQuarter {

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

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
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
