package General;

public class LocalResourcesManager {

    Resources elecricityResource = new Resources(true);
    Resources foodResource = new Resources(true);
    Resources crewResource = new Resources(false);
    Resources availableCrewResource = new Resources(false);

    public Resources getElectricityResource() {
        return elecricityResource;
    }
    public Resources getFoodResource() {
        return foodResource;
    }
    public Resources getCrewResource() {
        return crewResource;
    }
    public Resources getAvailableCrewResource() {
        return availableCrewResource;
    }

    //Constructor
    public LocalResourcesManager() {    }
}
