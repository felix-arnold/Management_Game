package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Galley extends ProductionQuarter {

    //prod dépend du nombre total déquipage


    public Galley() {
        super();
        name = "Galley";
        quarterIcon = new ImageView(new Image("galleyIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedGalleyIcon.png"));
        indexConstructionPane = new int[]{1, 0, 2};
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "HellishBoss" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[7]) {
                    productionBonusConstant += adjQuarter.getProduction()[3];
                } else if (rnd >= adjQuarter.getProduction()[9]) {
                    productionBonusConstant -= adjQuarter.getProduction()[3];
                }
            }
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "Galley" -> productionPerCrewBonusRate += 0.025 * adjQuarter.getCrew();
            case "TemporalCaboose", "Birdcatcher" -> productionBonusRate += 0.01 * adjQuarter.getLevel() * adjQuarter.getCrew();
        }
    }
}
