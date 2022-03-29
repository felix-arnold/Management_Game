package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DimensionlessSpace extends ProductionQuarter {

    //prodution[1Paradoxalgenerator,2paradoxalGenerator]

    public DimensionlessSpace() {
        super();
        name="DimensionlessSpace";
        quarterIcon = new ImageView(new Image("dimensionlessSpaceIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedDimensionlessSpaceIcon.png"));
        indexConstructionPane = new int[]{1, 0, 0};
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[13]) {
                    productionBonusConstant += adjQuarter.getProduction()[11];
                } else if (rnd >= adjQuarter.getProduction()[15]) {
                    productionBonusConstant -= adjQuarter.getProduction()[11];
                }
            }
            case "TemporalCaboose" -> productionBonusRate -= 0.1*adjQuarter.getLevel();
        }
    }
}
