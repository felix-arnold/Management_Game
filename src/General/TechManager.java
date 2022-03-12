package General;

import Airship.Airship;
import com.sun.javafx.image.impl.General;

import java.util.ArrayList;
import java.util.List;

import static General.CsvFileUser.loadValue;
import static General.CsvFileUser.readCSV;

public class TechManager {

    private final ArrayList<Tech> techTree = new ArrayList<>();
    static final List<String[]> techData = new ArrayList<>();

    private int indexTechInProgress = -1;
    private long additionalScience = 0;

    //Constructor | Use of a singleton
    private TechManager () {
        readCSV("techData.csv", techData);
        for (String[] techInfo : techData) {
            techTree.add(new Tech(techInfo[0], techInfo[1].split(","), Integer.parseInt(techInfo[2]), Integer.parseInt(techInfo[3])));
        }
    }
    private static TechManager INSTANCE = new TechManager();
    public static TechManager getInstance() {
        return INSTANCE;
    }

    public void selectResearchedTech(String name) {
        int i = 0;
        while (!techTree.get(i).getName().equals(name)) {
            i++;
        }
        if (indexTechInProgress!=-1) {
            techTree.get(indexTechInProgress).setResearched(false);
        }
        indexTechInProgress=i;
        techTree.get(indexTechInProgress).setResearched(true);
    }

    public void updateTech(long scienceCost){
        techTree.get(indexTechInProgress).consumeScience(GlobalManager.getInstance().getScienceResource().getAmount());
        if (techTree.get(indexTechInProgress).getScienceCost() < 0) {
            additionalScience = -GlobalManager.getInstance().getScienceResource().getAmount();
            techTree.get(indexTechInProgress).setResearched(false);
            techTree.get(indexTechInProgress).setDiscovered();
            indexTechInProgress=-1;
        }
    }
}