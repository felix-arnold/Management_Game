package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Galley extends ProductionQuarter {

    //prod dépend du nombre total déquipage


    public Galley(int level) {
        super(level);
        name = "Galley";
        trueName = "Galley";
        quarterIcon = new ImageView(new Image("galleyIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedGalleyIcon.png"));
        indexConstructionPane = new int[]{1, 0, 2};
        loadAllValues();
        loadConstructionInfoPaneValue();
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getTrueName()) {
            case "HellishBoss" -> {
                double rnd = Math.random();
                if (rnd < 0.3) {
                    productionBonusRate += 0.1*adjQuarter.getLevel();
                } else if (rnd >= 0.9) {
                    productionBonusRate -= 0.1*adjQuarter.getLevel();
                }
            }
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "Galley" -> productionPerCrewBonusRate += 0.025 * adjQuarter.getCrew();
            case "TemporalCaboose", "Birdcatcher" -> productionBonusRate += 0.01 * adjQuarter.getLevel() * adjQuarter.getCrew();
        }
    }
}
