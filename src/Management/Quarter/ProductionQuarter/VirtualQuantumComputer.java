package Management.Quarter.ProductionQuarter;

import Management.Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class wraps the property of a VirtualQuantumComputer.
 */
public class VirtualQuantumComputer extends ProductionQuarter{

    //production[0MadScientist, 1MadScientist, 2HellishBoss, 3HellishBoss, 4dataCentre, 5dataCentre]
    /**
     * Creates a new instance of VirtualQuantumComputer.
     * @param level the level of the quarter
     */
    public VirtualQuantumComputer(int level) {

        super(level);
        name = "Quantum Computer";
        trueName = "VirtualQuantumComputer";
        quarterIcon = new ImageView(new Image("virtualQuantumComputerIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedVirtualQuantumComputerIcon.png"));
        indexConstructionPane = new int[]{1, 1, 2};
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
            case "VirtualQuantumComputer" -> productionBonusRate += 0.1 + 0.1 * adjQuarter.getLevel() * adjQuarter.getCrew() / (adjQuarter.getLevel() + 1);
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "MadScientist" -> {
                double rnd = Math.random();
                if (rnd < 0.3) {
                    productionBonusConstant += adjQuarter.getProduction()[1];
                } else if (rnd >= 0.9) {
                    productionBonusConstant -= adjQuarter.getProduction()[1];
                }
            }
        }
    }
}
