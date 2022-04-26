package Management.GeneralManagement;

/**
 * LocalResourcesManager creates and manages 3 resources which have different instance for each airship.
 */
public class LocalResourcesManager {

    /**
     * Resource of available electricity.
     */
    private final Resources elecricityResource = new Resources( "Electricity");

    /**
     * Resource of available food.
     */
    private final Resources foodResource = new Resources( "Food");
    /**
     * Resource of the total number of crew.
     */
    private final Resources crewResource = new Resources( "Crew");
    /**
     * Resource of available crew.
     */
    private final Resources availableCrewResource = new Resources( "Available Crew");

    /**
     * Return the electricity resource.
     */
    public Resources getElectricityResource() {
        return elecricityResource;
    }

    /**
     * Returns the food resource.
     */
    public Resources getFoodResource() {
        return foodResource;
    }

    /**
     * Returns the total crew resource.
     */
    public Resources getCrewResource() {
        return crewResource;
    }

    /**
     * Return the available crew resource.
     */
    public Resources getAvailableCrewResource() {
        return availableCrewResource;
    }

    //Constructor

    /**
     * Create a new instance of LocalResourcesManager.
     */
    public LocalResourcesManager() {}

    /**
     * List containing the 4 resources.
     */
    private final Resources[] resourcesList = {elecricityResource, foodResource, availableCrewResource, crewResource};

    /**
     * Return the list of every resource.
     */
    public Resources[] getResourcesList() {
        return resourcesList;
    }
}
