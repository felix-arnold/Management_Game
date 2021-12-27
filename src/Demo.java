import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class Demo  extends Scene {

    private long cryptomoneyValue=0;
    private IntegerProperty cryptomoneyNumber = new SimpleIntegerProperty();

    private long electricityValue=0;
    private IntegerProperty electricityNumber = new SimpleIntegerProperty();

    private long crewValue=0;
    private IntegerProperty crewNumber = new SimpleIntegerProperty();

    private long foodValue=0;
    private IntegerProperty foodNumber = new SimpleIntegerProperty();

    private long scienceValue=0;
    private IntegerProperty scienceNumber = new SimpleIntegerProperty();

    private long bitValue=0;
    private IntegerProperty bitNumber = new SimpleIntegerProperty();

    private long codedataValue=0;
    private IntegerProperty codedataNumber = new SimpleIntegerProperty();


    public Demo(Group g) {
        super(g, 1540, 800);

        cryptomoneyNumber.setValue(cryptomoneyValue);
        Label cryptomoneyDisplay = new Label();
        cryptomoneyDisplay.textProperty().bind(cryptomoneyNumber.asString());
        g.getChildren().add(cryptomoneyDisplay);
        cryptomoneyDisplay.setLayoutX(100);
        cryptomoneyDisplay.setLayoutY(5);

        electricityNumber.setValue(electricityValue);
        Label electricityDisplay = new Label();
        electricityDisplay.textProperty().bind(electricityNumber.asString());
        g.getChildren().add(electricityDisplay);
        electricityDisplay.setLayoutX(200);
        electricityDisplay.setLayoutY(5);

        foodNumber.setValue(foodValue);
        Label foodDisplay = new Label();
        foodDisplay.textProperty().bind(foodNumber.asString());
        g.getChildren().add(foodDisplay);
        foodDisplay.setLayoutX(300);
        foodDisplay.setLayoutY(5);

        crewNumber.setValue(crewValue);
        Label crewDisplay = new Label();
        crewDisplay.textProperty().bind(crewNumber.asString());
        g.getChildren().add(crewDisplay);
        crewDisplay.setLayoutX(400);
        crewDisplay.setLayoutY(5);

        scienceNumber.setValue(scienceValue);
        Label scienceDisplay = new Label();
        scienceDisplay.textProperty().bind(scienceNumber.asString());
        g.getChildren().add(scienceDisplay);
        scienceDisplay.setLayoutX(500);
        scienceDisplay.setLayoutY(5);

        codedataNumber.setValue(codedataValue);
        Label codedataDisplay = new Label();
        codedataDisplay.textProperty().bind(codedataNumber.asString());
        g.getChildren().add(codedataDisplay);
        codedataDisplay.setLayoutX(600);
        codedataDisplay.setLayoutY(5);

        bitNumber.setValue(bitValue);
        Label bitDisplay = new Label();
        bitDisplay.textProperty().bind(bitNumber.asString());
        g.getChildren().add(bitDisplay);
        bitDisplay.setLayoutX(700);
        bitDisplay.setLayoutY(5);



        this.setOnKeyPressed( (event)-> {
            KeyCode keyCode = event.getCode();
            if (keyCode.equals(KeyCode.SPACE)) {
                cryptomoneyValue+=1;
                cryptomoneyNumber.setValue(cryptomoneyValue);
            }
        });
    }
}
