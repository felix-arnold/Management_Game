package General;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;



public class BattleGamescene extends Scene {

    private AnimationTimer timer;

    public BattleGamescene(Group g) {
        super(g);

        final StaticThing background = new StaticThing("battleBackground.png", 0, 0, 720, 405,1540, 800);
        g.getChildren().add(background.getSprite());

        final StaticThing linesOverlay = new StaticThing("linesOverlay.png", 210, 0, 3240, 2468,1120, 800);
        g.getChildren().add(linesOverlay.getSprite());

    }
}
