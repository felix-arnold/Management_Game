package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Berth extends ProductionQuarter {

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

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {

    }
}
