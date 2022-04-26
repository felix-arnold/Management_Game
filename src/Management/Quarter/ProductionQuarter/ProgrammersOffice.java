package Management.Quarter.ProductionQuarter;

import Management.Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class wraps the property of a ProgrammersOffice.
 */
public class ProgrammersOffice extends ProductionQuarter {

    //production[5dataCode,dataCode]
    /**
     * Creates a new instance of ProgrammersOffice.
     * @param level the level of the quarter
     */
    public ProgrammersOffice(int level) {

        super(level);
        name = "Programmers' Office";
        trueName = "ProgrammersOffice";
        quarterIcon = new ImageView(new Image("programmersOfficeIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedProgrammersOfficeIcon.png"));
        indexConstructionPane = new int[]{0, 1, 3};
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
            case "InternetFiberProvider" -> productionPerCrewBonusRate += 0.01 * adjQuarter.getCrew();
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "HellishBoss" -> {
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
