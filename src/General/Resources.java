package General;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

public class Resources {

    //Classe pour simplifier le code

    private long amount=1000;
    private final boolean canBeNegativ;
    private final String name;

    //Constructor
    public Resources(boolean canBeNegativ, String name) {
        this.canBeNegativ = canBeNegativ;
        this.name=name;

        resourceInfo.setText(name);
        resourceInfo.getStyleClass().add("resourcesInfo");
    }

    public void addAmount(long production) {
        amount += production;
    }

    public void subtractAmount(long consumption) {
        amount -= consumption;
        if(!canBeNegativ && amount < 0) {
            amount = 0;
        }
    }

    public void setAmount(long newAmount) {
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