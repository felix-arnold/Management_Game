package Management.GeneralManagement;

import javafx.scene.control.Label;

/**
 * The Resource class wraps the every element needed to describe a resource owned by the player.
 */
public class Resources {

    //Classe pour simplifier le code

    /**
     * Amount owned of the resource.
     */
    private int amount=9999;
    /**
     * Name of the resource.
     */
    private final String name;

    //Constructor

    /**
     * Creates a new instance of resource.
     * @param name the name of the resource
     */
    public Resources(String name) {
        this.name=name;

        resourceInfo.setText(name);
        resourceInfo.getStyleClass().add("resourcesInfo");
    }

    /**
     * Adds the production argument to the resource amount.
     * @param production the amount produced of the resource
     */
    public void addAmount(long production) {
        amount += production;
    }

    /**
     * Subtracts the consumption argument to the resource amount.
     * @param consumption the amount consumed of the resource
     */
    public void subtractAmount(long consumption) {
        amount -= consumption;
    }

    /**
     * Sets the resource amount.
     * @param newAmount the new resource amount
     */
    public void setAmount(int newAmount) {
        amount = newAmount;
    }

    /**
     * Returns the amount of the resource.
     */
    public long getAmount() {
        return amount;
    }

    /**
     * Returns the name of the resource.
     */
    public String getName() {
        return name;
    }

    /**
     * Displayed label of the name of the resource.
     */
    private final Label resourceInfo = new Label();

    /**
     * Returns the label of the resource.
     */
    public Label getResourceInfo() {
        return resourceInfo;
    }
}