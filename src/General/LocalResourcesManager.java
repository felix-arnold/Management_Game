package General;

public class LocalResourcesManager {

    Resources elecricityRessource = new Resources(true);
    Resources foodRessource = new Resources(true);
    Resources crewRessource = new Resources(false);

    public Resources getElecricityRessource() {
        return elecricityRessource;
    }
    public Resources getFoodRessource() {
        return foodRessource;
    }
    public Resources getCrewRessource() {
        return crewRessource;
    }


    //Constructor
    public LocalResourcesManager() {    }
}
