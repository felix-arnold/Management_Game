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
    private int fitWidth;
    private int fitHeight;
    private double x;
    private double y;

    public AnimatedThing(String fileName, double x, double y, int width, int height, int nombreFrame, int fitWidth, int fitHeight) {
        super(fileName, x, y, width, height, fitWidth, fitHeight);

        this.nombreFrame=nombreFrame;
        this.width=width;
        this.height=height;
        this.fitHeight=fitHeight;
        this.fitWidth=fitWidth;
        this.x=x;
        this.y=y;
    }

    public void updateAnimated(long time) {
        index = (int) (Math.floor((time/frameFactor)) % nombreFrame);
        getSprite().setViewport(new Rectangle2D(index * width, 0, width, height));
    }
}

