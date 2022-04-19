package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cryptomine extends ProductionQuarter {

    public Cryptomine(int level) {

        super(level);
        name = "Cryptomine";
        trueName = "Cryptomine";
        quarterIcon = new ImageView(new Image("cryptomineIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedCryptomineIcon.png"));
        indexConstructionPane = new int[]{0, 1, 1};
        loadAllValues();
        loadConstructionInfoPaneValue();
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "Cryptomine" -> productionPerCrewBonusRate -= 0.01 * adjQuarter.getCrew();
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "Cryptoinvestor" -> {
                double rnd = Math.random();
                if (rnd < 0.50) {
                    productionBonusConstant += adjQuarter.getProduction()[1];
                } else if (rnd >= 0.7) {
                    productionBonusConstant -= adjQuarter.getProduction()[1];
                }
            }
            case "ParadoxalGenerator" -> productionBonusRate += 0.05 * adjQuarter.getLevel();
            case "InternetFiberProvider" -> productionBonusRate += 0.01 * adjQuarter.getCrew();
        }
    }
}
