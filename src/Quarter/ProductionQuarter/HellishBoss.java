package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HellishBoss extends ProductionQuarter {

    //production[0ProgrammerOffice, 1ProgrammerOffice, 2Galley, 3Galley, 4 Birdcatcher, 5Birdcatcher, 6Probawin, 7Probawin, 8Probaloss, 9Probaloss]

    public HellishBoss(int level) {

        super(level);
        name = "Hellish Boss";
        trueName = "HellishBoss";
        quarterIcon = new ImageView(new Image("hellishBossIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedHellishBossIcon.png"));
        indexConstructionPane = new int[]{1, 1, 1};
        loadAllValues();
        loadConstructionInfoPaneValue();
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "Hellish Boss" -> {
                double rnd = Math.random();
                if (rnd < 0.2) {
                    productionPerCrewBonusRate += (0.6 - 0.2 * (level-adjQuarter.getLevel())) * adjQuarter.getCrew();
                } else if (rnd >= 0.4) {
                    productionPerCrewBonusRate -= (0.9 - 0.3 * (level-adjQuarter.getLevel())) * adjQuarter.getCrew();
                }
            }
            case "Berth" -> productionPerCrewBonusRate -= 0.1*adjQuarter.getLevel();
            case "VirtualQuantumComputer" -> productionBonusRate += adjQuarter.getProduction()[3];
            case "MadScientist" -> productionPerCrewBonusRate -= 0.3*adjQuarter.getCrew();
            case "CryptoInvestor" -> productionPerCrewBonusRate -= 0.1*adjQuarter.getCrew();
        }
    }
}