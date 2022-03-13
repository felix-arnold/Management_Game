package General;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static General.CsvFileUser.readCSV;

public class TechManager {

    /*  Build and manage the tech tree :        Define the status of each tech


        Research tech :                         Store excessive science
                                                Check if previous tech are discovered
                                                Ensure only one tech can be research at the same time
    */

    private final ArrayList<Tech> techTree = new ArrayList<>();
    static final List<String[]> techData = new ArrayList<>();

    private int indexTechInProgress = -1;
    private long additionalScience = 0;


    //Constructor | Use of a singleton
    private TechManager () {
        readCSV("techData.csv", techData);
        for (String[] techInfo : techData) {
            ArrayList<Integer> previousTechIndex = new ArrayList<>();
            ArrayList<Integer> nextTechIndex = new ArrayList<>();
            for (String i : techInfo[1].split(",")) {
                previousTechIndex.add(Integer.parseInt(i));
            }
            for (String j : techInfo[2].split(",")) {
                previousTechIndex.add(Integer.parseInt(j));
            }
            techTree.add(new Tech(techInfo[0], previousTechIndex, nextTechIndex, Integer.parseInt(techInfo[3]), Boolean.parseBoolean(techInfo[4]), techInfo[5]));
        }
    }
    private static final TechManager INSTANCE = new TechManager();
    public static TechManager getInstance() {
        return INSTANCE;
    }


    //Design the selected tech as researched if possible
    public int selectResearchedTech(int index) {
        //check if the previous tech are already researched and if not return -1
        for (int indexPreviousTech : techTree.get(index).getPreviousTechIndex()) {
            if (!techTree.get(indexPreviousTech).isDiscovered()) {
                return -1;
            }
        }
        //design the selected tech as researched and put a possible previous researched tech in stand by
        if (indexTechInProgress != -1) {
            techTree.get(indexTechInProgress).setResearched(false);
        }
        indexTechInProgress = index;
        techTree.get(indexTechInProgress).setResearched(true);
        return 0;
    }


    public void updateTech(){
        //Decrease cost each turn depending on the science generated if a tech is selected
        if (indexTechInProgress != -1) {
            techTree.get(indexTechInProgress).consumeScience(GlobalManager.getInstance().getScienceResource().getAmount() + additionalScience);
            additionalScience = 0;
            //If the amount is enough to discover the tech
            if (techTree.get(indexTechInProgress).getScienceCost() <= 0) {
                //store excessive science for next turn
                additionalScience = -GlobalManager.getInstance().getScienceResource().getAmount();
                techTree.get(indexTechInProgress).setResearched(false);
                techTree.get(indexTechInProgress).setDiscovered();
                techTree.get(indexTechInProgress).applyTech();
                //set next tech as unlock if all of their previous tech are discovered
                for (int indexNextTech : techTree.get(indexTechInProgress).getNextTechIndex()) {
                    boolean canUnlock = true;
                    for (int indexPreviousNextTech : techTree.get(indexNextTech).getPreviousTechIndex()) {
                        if (!techTree.get(indexPreviousNextTech).isDiscovered()) {
                            canUnlock = false;
                            break;
                        }
                    }
                    if (canUnlock) {
                        techTree.get(indexNextTech).setUnlocked();
                    }
                }
                indexTechInProgress = -1;
            }
        }
        //Store science if none is selected
        else {
            additionalScience += GlobalManager.getInstance().getScienceResource().getAmount();
        }
        //Limit the amount of stored science to 2 times the amount generated each turn
        if (additionalScience > 2*GlobalManager.getInstance().getScienceResource().getAmount()) {
            additionalScience = 2*GlobalManager.getInstance().getScienceResource().getAmount();
        }
    }
}