package Management.GeneralManagement;

import javafx.geometry.Rectangle2D;

/**
 * A special type of NonAnimatedThing which have an animated displayed element, such as airships.
 */
public class AnimatedThing extends NonAnimatedThing {

    /**
     * Number of different sprites in the sprite sheet.
     */
    private final int numberSprite;
    /**
     * Width of a sprite.
     */
    private final int width;
    /**
     * Height of a sprite
     */
    private final int height;

    /**
     * Creates a new instance of AnimatedThing.
     * <br>The node created will have the position (x,y) on the screen, and the size of the original image will be readjusted, as the class extends NonAnimatedThing.
     * @param fileName the name of the file with the sprite sheet of the displayed element
     * @param x the x coordinate of the displayed element
     * @param y the y coordinate of the displayed element
     * @param width the width of a sprite in the original image
     * @param height the height of a sprite in the original image
     * @param numberSprite the number of different sprites
     * @param fitWidth the resized width of a displayed sprite
     * @param fitHeight the resized height of a displayed sprite
     */
    public AnimatedThing(String fileName, double x, double y, int width, int height, int numberSprite, int fitWidth, int fitHeight) {
        super(fileName, x, y, width, height, fitWidth, fitHeight);

        this.numberSprite=numberSprite;
        this.width=width;
        this.height=height;
    }

    /**
     * Update the animation of the AnimatedThing.
     * <br> Every 0.1 second, the sprite of the element changes to display the next one.
     * Each sprite must be included inside a sprite sheet with every image on the same line, with the same size and height.
     * Once the last sprite has been reached, the method return to the first sprite.
     * @param time the timestamp of the current frame given in nanoseconds
     */
    public void updateAnimated(long time) {
        long frameFactor = 100000000;
        int index = (int) (Math.floor(((float) time / frameFactor)) % numberSprite);
        getSprite().setViewport(new Rectangle2D(index * width, 0, width, height));
    }
}

