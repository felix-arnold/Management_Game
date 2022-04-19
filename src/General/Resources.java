package General;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

public class Resources {

    //Classe pour simplifier le code

    private int amount=10000;
    private final String name;

    //Constructor
    public Resources(String name) {
        this.name=name;

        resourceInfo.setText(name);
        resourceInfo.getStyleClass().add("resourcesInfo");
    }

    public void addAmount(long production) {
        amount += production;
    }

    public void subtractAmount(long consumption) {
        amount -= consumption;
    }

    public void setAmount(int newAmount) {
        amount = newAmount;
    }

    public long getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }


    private final Label resourceInfo = new Label();

    public Label getResourceInfo() {
        return resourceInfo;
    }
}