package Management.GeneralManagement;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import static FileReaders.CsvFileUser.readCSV;
import static Management.GeneralManagement.ManagementGamescene.constructionMenuDisplay;

/**
 * TechManager is the class that creates the technology tree and manages it.
 * Its roles also include the research of technology and the storage of the excessive science.
 * <br>Only one instance of TechManager is intended to be created, inside the GlobalManager singleton.
 */
public class TechManager {

    /*  Build and manage the tech tree :        Define the status of each tech


        Research tech :                         Store excessive science
                                                Check if previous tech are discovered
                                                Ensure only one tech can be research at the same time
    */

    /**
     * List of every technology.
     */
    private final ArrayList<Tech> techTree = new ArrayList<>();
    /**
     * Content of the CSV file containing the data of all technology.
     */
    private static final List<String[]> techData = new ArrayList<>();

    /**
     * Index of the technology currently researched. equals to -1 when there is no researched technology.
     */
    private int indexTechInProgress = -1;

    /**
     * Amount of excessive science being saved for the next turn.
     */
    private long additionalScience = 0;



    /**
     * Creates the TechManager instance by loading the content of the "techData.csv" file inside techData and then using it to instantiate each technology and adding them to techTree.
     * <br> The properties of techPane are initialized and for each technology inside the techTree, we add to subTechPane a button with the name of the technology as its label. When pressed, the button sets the researched technology as its technology.
     * SubTechPane is then added to techPane.
     */
    public TechManager () {
        readCSV("techData.csv", techData);
        for (String[] techInfo : techData) {
            ArrayList<Integer> previousTechIndex = new ArrayList<>();
            ArrayList<Integer> nextTechIndex = new ArrayList<>();
            for (String i : techInfo[1].split(",")) {
                previousTechIndex.add(Integer.parseInt(i));
            }
            for (String j : techInfo[2].split(",")) {
                nextTechIndex.add(Integer.parseInt(j));
            }
            techTree.add(new Tech(techInfo[0], previousTechIndex, nextTechIndex, Integer.parseInt(techInfo[3]),techInfo[4]));
        }

        techPane.setLayoutY(100);
        techPane.setPrefWidth(350);
        subTechPane.getStyleClass().add("researchPane");
        techPane.setContent(subTechPane);
        final int[] j = {0};
        for (int i = 0; i<techTree.size(); i++) {
            if (!techTree.get(i).isDiscovered()) {
                j[0]++;
                int k=i;
                RadioButton radioButton = new RadioButton(techTree.get(k).getName());
                radioButton.getStyleClass().clear();
                radioButton.getStyleClass().add("researchButton");
                radioButton.setToggleGroup(researchButtonToggleGroup);
                subTechPane.add(radioButton, j[0], 0);
                radioButton.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
                    if (isNowSelected) {
                        radioButton.getStyleClass().clear();
                        radioButton.getStyleClass().add("selectedResearchButton");
                        if (selectResearchedTech(k) == -1) {
                            researchButtonToggleGroup.selectToggle(null);
                        }
                    } else {
                        if (!techTree.get(k).isDiscovered()) {
                            radioButton.getStyleClass().clear();
                            radioButton.getStyleClass().add("researchButton");
                        } else {
                            subTechPane.getChildren().remove(radioButton);
                        }
                    }
                });
            }
        }
    }


    //Design the selected tech as researched if possible

    /**
     * Sets the technology to research.
     * <br>Beforehand, it checks is the previous technology required to unlock the selected one have been discovered. Then the value of indexTechInProgress is changed to the index argument and the researched property of the selected technology is set to true.
     * If another technology was already being researched, its researched property is set to false.
     * @param index the index of the selected technology
     * @return -1 if the check was not successful, 0 otherwise
     * */
    public int selectResearchedTech(int index) {
        //check if the previous tech are already researched and if not return -1
        for (int indexPreviousTech : techTree.get(index).getPreviousTechIndex()) {
            if (!techTree.get(indexPreviousTech-1).isDiscovered()) {
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

    /**
     * Manages the research of technology each turn.
     * <br>The method subtracts the value of science produced this turn from the cost of the technology researched. If no technology has been selected or if the amount of science produced is greater than the remaining amount of science needed to discover the technology, the science is stored to be use the next turns. Up to 2 times the amount of produce this turn can be stored.
     * When the cost in science needed to research a technology has been fully completed, the discovered property of the technology is set to true and its effect is applied. The indexTechInProgress is also reset to -1.
     * <br>For the demo, when a technology has been discovered, it also removes its button from the subTechPane grid.
     * <br> When a technology has been researched, it also reloads the constructionPane in ManagementGamescene to display the button of the unlocked quarter.
     */
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
                constructionMenuDisplay();
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
                researchButtonToggleGroup.selectToggle(null);
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

    /**
     * Scrolling pane used to allow the player to easily display all technology.
     */
    private final ScrollPane techPane = new ScrollPane();
    /**
     * Pane constituted of a grid which will contain in each cell a button allowing to select a technology to research.
     */
    private final GridPane subTechPane = new GridPane();
    /**
     * Toggle group associated with the radiobuttons allowing the player to choose a technology to research.
     */
    private final ToggleGroup researchButtonToggleGroup = new ToggleGroup();

    /**
     * Returns the techPane property.
     */
    public ScrollPane getTechPane() {
        return techPane;
    }
}