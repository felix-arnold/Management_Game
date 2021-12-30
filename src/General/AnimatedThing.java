package General;

import General.StaticThing;
import javafx.geometry.Rectangle2D;


public class AnimatedThing extends StaticThing {

    private long lastTime = 0;
    private int index;
    private int nombreFrame;
    private long frameFactor = 100000000;
    private int width;
    private int height;

    public AnimatedThing(String fileName, double x, double y, int width, int height, int nombreFrame, int fitWidth, int fitHeight) {
        super(fileName, x, y, width, height);

        this.nombreFrame=nombreFrame;
        this.width=width;
        this.height=height;

        getSprite().setFitWidth(fitWidth);
        getSprite().setFitHeight(fitHeight);
        getSprite().setPreserveRatio(true);
    }

    public void updateAnimated(long time) {
        index = (int) (Math.floor((time/frameFactor)) % nombreFrame);
        getSprite().setViewport(new Rectangle2D(index * width, 0, width, height));
    }
}

