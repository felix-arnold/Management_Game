package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cryptoinvestor extends ProductionQuarter{

    //production[0Cryptomine,1Cryptomine,2Probawin,3Probawin,4Probaloss,5Probaloss]

    protected double probaBonus;

    public Cryptoinvestor(int level) {

        super(level);
        name = "Cryptoinvestors";
        trueName = "Cryptoinvestor";
        quarterIcon = new ImageView(new Image("cryptoinvestorsIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedcryptoinvestorsIcon.png"));
        indexConstructionPane = new int[]{1, 1, 0};
        loadAllValues();
        loadConstructionInfoPaneValue();
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getTrueName()) {
            case "Cryptoinvestor" -> {
                probaBonus += 10*getLevel();
                double rnd = Math.random();
                if (rnd < 0.3) {
                    productionPerCrewBonusRate += 0.1 * adjQuarter.getCrew();
                } else if (rnd >= 0.7) {
                    productionPerCrewBonusRate -= 0.1 * adjQuarter.getCrew();
                }
            }
            case "Berth" -> productionPerCrewBonusRate -= 0.4;
            case "InternetFiberProvider" -> productionPerCrewBonusRate += 0.1 * adjQuarter.getCrew();
            case "VirtualQuantumComputer" -> productionPerCrewBonusRate += 0.1 * adjQuarter.getLevel();
            case "HellishBoss" -> productionPerCrewBonusRate -= 0.3*adjQuarter.getCrew();
            case "MadScientist" -> productionPerCrewBonusRate -= 0.5*adjQuarter.getCrew();
        }
    }
}
