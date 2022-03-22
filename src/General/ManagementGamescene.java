package General;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import Airship.ManOWar;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;

import java.security.cert.PolicyNode;

public class ManagementGamescene extends Scene {

    private final StaticThing backgroundLeft;
    private final StaticThing backgroundRight;
    private final StaticThing cloudBottom1Left;
    private final StaticThing cloudBottom1Right;
    private final StaticThing cloudTop1Left;
    private final StaticThing cloudTop1Right;
    private final StaticThing cloudBottom2Left;
    private final StaticThing cloudBottom2Right;
    private final StaticThing cloudTop2Left;
    private final StaticThing cloudTop2Right;


    private int indexShipDisplay;

    public ManagementGamescene(Group g) {
        super(g, 1540, 800);

        backgroundLeft = new StaticThing("BackgroundLayer.png", 0, 0, 1831, 800);
        backgroundRight = new StaticThing("BackgroundLayer.png", 1831, 0, 1831, 800);
        g.getChildren().add(backgroundLeft.getSprite());
        g.getChildren().add(backgroundRight.getSprite());

        StaticThing starsFix = new StaticThing("StarsLayer.png", 0, 5, 1540, 193);
        g.getChildren().add(starsFix.getSprite());

        cloudBottom1Left = new StaticThing("CloudBottom1Layer.png", 0, 612, 1826, 188);
        cloudBottom1Right = new StaticThing("CloudBottom1Layer.png", 1826, 612, 1826, 188);
        g.getChildren().add(cloudBottom1Left.getSprite());
        g.getChildren().add(cloudBottom1Right.getSprite());

        cloudTop1Left = new StaticThing("CloudTop1Layer.png", 0, 120, 4538, 556);
        cloudTop1Right = new StaticThing("CloudTop1Layer.png", 4538, 120, 4538, 556);
        g.getChildren().add(cloudTop1Left.getSprite());
        g.getChildren().add(cloudTop1Right.getSprite());

        cloudBottom2Left = new StaticThing("CloudBottom2Layer.png", 0, 238, 4790, 562);
        cloudBottom2Right = new StaticThing("CloudBottom2Layer.png", 4790, 238, 4790, 562);
        g.getChildren().add(cloudBottom2Left.getSprite());
        g.getChildren().add(cloudBottom2Right.getSprite());

        cloudTop2Left = new StaticThing("CloudTop2Layer.png", 0, 120, 5780, 433);
        cloudTop2Right = new StaticThing("CloudTop2Layer.png", 5780, 120, 5780, 433);
        g.getChildren().add(cloudTop2Left.getSprite());
        g.getChildren().add(cloudTop2Right.getSprite());


        GlobalManager.getInstance().getAirshipList()[1] = new Airship("Corvette");
        GlobalManager.getInstance().getAirshipList()[2] = new Airship("Man'o'war");
        GlobalManager.getInstance().getAirshipList()[3] = new Airship("Schooner");
        GlobalManager.getInstance().getAirshipList()[4] = new Airship("Fluyt");
        GlobalManager.getInstance().getAirshipList()[5] = new Airship("Brig");
        GlobalManager.getInstance().getAirshipList()[6] = new Airship("Frigate");
        GlobalManager.getInstance().getAirshipList()[7] = new Airship("Longskip");
        GlobalManager.getInstance().getAirshipList()[8] = new Airship("Junk");
        g.getChildren().add(GlobalManager.getInstance().getAirshipList()[0].getImage().getSprite());
        GlobalManager.getInstance().getAirshipList()[0].setDisplay(true);

        cloudBottom2Left.getSprite().toFront();
        cloudBottom2Right.getSprite().toFront();
        cloudTop2Left.getSprite().toFront();
        cloudTop2Right.getSprite().toFront();


        Button nextShip = new Button();
        nextShip.setPrefSize(40, 40);
        nextShip.setLayoutX(1490);
        nextShip.setLayoutY(400);
        g.getChildren().add(nextShip);
        nextShip.setOnAction((event) -> {
            g.getChildren().remove(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());
            GlobalManager.getInstance().getAirshipList()[indexShipDisplay].setDisplay(false);
            if (GlobalManager.getInstance().getAirshipList()[++indexShipDisplay]==null) {
                indexShipDisplay=0;
            }
            g.getChildren().add(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());
            GlobalManager.getInstance().getAirshipList()[indexShipDisplay].setDisplay(true);
            cloudBottom2Left.getSprite().toFront();
            cloudBottom2Right.getSprite().toFront();
            cloudTop2Left.getSprite().toFront();
            cloudTop2Right.getSprite().toFront();
        });

        Button previousShip = new Button();
        previousShip.setPrefSize(40, 40);
        previousShip.setLayoutX(0);
        previousShip.setLayoutY(400);
        g.getChildren().add(previousShip);
        previousShip.setOnAction((event) -> {
            g.getChildren().remove(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());
            GlobalManager.getInstance().getAirshipList()[indexShipDisplay].setDisplay(false);
            if (--indexShipDisplay==-1) {
                indexShipDisplay=11;
            }
            while (GlobalManager.getInstance().getAirshipList()[indexShipDisplay]==null) {
                indexShipDisplay--;
            }
            g.getChildren().add(GlobalManager.getInstance().getAirshipList()[indexShipDisplay].getImage().getSprite());
            GlobalManager.getInstance().getAirshipList()[indexShipDisplay].setDisplay(true);
            cloudBottom2Left.getSprite().toFront();
            cloudBottom2Right.getSprite().toFront();
            cloudTop2Left.getSprite().toFront();
            cloudTop2Right.getSprite().toFront();
        });


        AnimationTimer timer = new AnimationTimer() {
            public void handle(long time) {
                airshipAnimation(time, g);
                render(time, g);
            }
        };
        timer.start();
    }


    public void airshipAnimation(long time, Group g) {
        for (Airship airship : GlobalManager.getInstance().getAirshipList()) {
            if (airship!=null && airship.isDisplay()) {
                airship.getImage().updateAnimated(time);
            }
        }
    }


    public void render(long time, Group g) {

        backgroundLeft.updateBackground(1831, 2, 0);
        backgroundRight.updateBackground(1831, 2, 1);

        cloudBottom1Left.updateBackground(1826,6,0);
        cloudBottom1Right.updateBackground(1826,6,1);

        cloudTop1Left.updateBackground(4538,10,0);
        cloudTop1Right.updateBackground(4538,10,1);

        cloudBottom2Left.updateBackground(4790,18,0);
        cloudBottom2Right.updateBackground(4790,18,1);

        cloudTop2Left.updateBackground(5780,25,0);
        cloudTop2Right.updateBackground(5780,25,1);
    }
}
