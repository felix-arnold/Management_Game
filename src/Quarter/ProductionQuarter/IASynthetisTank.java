package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static java.lang.Math.min;

public class IASynthetisTank extends ProductionQuarter {

    public IASynthetisTank() {
        super();
        name = "IA Synthesis Tank";
        quarterIcon = new ImageView(new Image("iaSynthesisTankIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedIASynthesisTankIcon.png"));
        indexConstructionPane = new int[]{0, 0, 0};
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[13]) {
                    productionBonusConstant += adjQuarter.getProduction()[7];
                } else if (rnd >= adjQuarter.getProduction()[15]) {
                    productionBonusConstant -= adjQuarter.getProduction()[7];
                }
            }
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "TemporalCaboose" -> productionBonusRate += 0.05 * adjQuarter.getLevel() * adjQuarter.getCrew() /(adjQuarter.getLevel()+1);
        }
    }
}