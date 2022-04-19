package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static java.lang.Math.min;

public class IASynthetisTank extends ProductionQuarter {

    public IASynthetisTank(int level) {
        super(level);
        name = "IA Synthesis Tank";
        trueName = "IASynthesisTank";
        quarterIcon = new ImageView(new Image("iaSynthesisTankIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedIASynthesisTankIcon.png"));
        indexConstructionPane = new int[]{0, 0, 0};
        loadAllValues();
        loadConstructionInfoPaneValue();
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getTrueName()) {
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < 0.3) {
                    productionBonusConstant += production[1]*0.5;
                } else if (rnd >= 0.9) {
                    productionBonusConstant -= production[1]*0.5;
                }
            }
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "TemporalCaboose" -> productionBonusRate += 0.05 * adjQuarter.getLevel() * adjQuarter.getCrew() /(adjQuarter.getLevel()+1);
        }
    }
}