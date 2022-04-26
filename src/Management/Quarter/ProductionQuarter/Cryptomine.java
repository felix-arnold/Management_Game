package Management.Quarter.ProductionQuarter;

import Management.Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class wraps the property of a Cryptomine.
 */
public class Cryptomine extends ProductionQuarter {

    /**
     * Creates a new instance of cryptomine.
     * @param level the level of the quarter
     */
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

    /**
     * Computes the bonus inherited from some specific adjacent quarter.
     * @param adjQuarter an adjacent quarter
     */
    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getTrueName()) {
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
