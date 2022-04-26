package Management.Quarter.ProductionQuarter;

import Management.Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class wraps the property of a Berth.
 */
public class Berth extends ProductionQuarter {

    /**
     * Creates a new instance of berth.
     * @param level the level of the quarter
     */
    public Berth(int level) {
        super(level);
        name = "Berth";
        trueName = "Berth";
        quarterIcon = new ImageView(new Image("berthIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedBerthIcon.png"));
        indexConstructionPane = new int[]{0, 1, 2};
        loadAllValues();
        loadConstructionInfoPaneValue();
    }

    /**
     * Computes the bonus inherited from some specific adjacent quarter.
     * @param adjQuarter an adjacent quarter
     */
    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
    }
}
