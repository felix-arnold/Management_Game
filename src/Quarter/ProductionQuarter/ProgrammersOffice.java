package Quarter.ProductionQuarter;

import Quarter.Quarter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProgrammersOffice extends ProductionQuarter {

    //production[5dataCode,dataCode]

    public ProgrammersOffice() {

        super();
        name = "ProgrammersOffice";
        quarterIcon = new ImageView(new Image("programmersOfficeIcon.png"));
        selectedQuarterIcon = new ImageView(new Image("selectedProgrammersOfficeIcon.png"));
        indexConstructionPane = new int[]{0, 1, 3};
        loadAllValues();
    }

    @Override
    public void adjacentBonuses(Quarter adjQuarter) {
        switch (adjQuarter.getName()) {
            case "InternetFiberProvider" -> productionPerCrewBonusRate += 0.01 * adjQuarter.getCrew();
            case "Berth" -> productionPerCrewBonusRate += 0.05 * adjQuarter.getLevel();
            case "HellishBoss" -> {
                double rnd = Math.random();
                if (rnd < adjQuarter.getProduction()[7]) {
                    productionBonusConstant += adjQuarter.getProduction()[1];
                } else if (rnd >= adjQuarter.getProduction()[9]) {
                    productionBonusConstant -= adjQuarter.getProduction()[1];
                }
            }
        }
    }

}
