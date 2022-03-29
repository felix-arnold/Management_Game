package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cryptomine extends ProductionQuarter {

    public Cryptomine() {

        super();
        name = "Cryptomine";
        quarterIcon = new ImageView(new Image("cryptomineIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedCryptomineIcon.png"));
        indexConstructionPane = new int[]{0, 1, 1};
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "Cryptomine" -> productionPerCrewBonusRate -= 0.01 * adjQuarter.getCrew();
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "Cryptoinvestor" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[3]) {
                    productionBonusConstant += adjQuarter.getProduction()[1];
                } else if (rnd >= adjQuarter.getProduction()[5]) {
                    productionBonusConstant -= adjQuarter.getProduction()[1];
                }
            }
            case "ParadoxalGenerator" -> productionBonusRate += 0.05 * adjQuarter.getLevel();
            case "InternetFiberProvider" -> productionBonusRate += 0.01 * adjQuarter.getCrew();
        }
    }
}
