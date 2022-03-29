package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ParadoxalGenerator extends ProductionQuarter {

    public ParadoxalGenerator() {

        super();
        name = "ParadoxalGenerator";
        quarterIcon = new ImageView(new Image("paradoxalGeneratorIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedParadoxalGeneratorIcon.png"));
        indexConstructionPane = new int[]{0, 0, 1};
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "ParadoxalGenerator" -> productionBonusConstant += 8 * 3 * adjQuarter.getCrew() * adjQuarter.getLevel();
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[13]) {
                    productionBonusConstant += adjQuarter.getProduction()[3];
                } else if (rnd >= adjQuarter.getProduction()[15]) {
                    productionBonusConstant -= adjQuarter.getProduction()[3];
                }
            }
            case "DimensionlessSpace" -> productionBonusRate += adjQuarter.getProduction()[1];
        }
    }
}
