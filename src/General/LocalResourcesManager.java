package General;

public class LocalResourcesManager {

    Resources elecricityResource = new Resources( "Electricity");
    Resources foodResource = new Resources( "Food");
    Resources crewResource = new Resources( "Crew");
    Resources availableCrewResource = new Resources( "Available Crew");

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

    private final Resources[] resourcesList = {elecricityResource, foodResource, availableCrewResource};
    public Resources[] getResourcesList() {
        return resourcesList;
    }
}
