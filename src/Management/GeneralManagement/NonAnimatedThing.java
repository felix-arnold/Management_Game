package Management.GeneralManagement;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * NonAnimatedThing is used to display non-interactive elements such as backgrounds.
 */
public class NonAnimatedThing {

    /**
     * Node of the displayed element
     */
    private final ImageView sprite;
    /**
     * X coordinate of the displayed element
     */
    private double x;

    /**
     * Creates a new instance of NonAnimatedThing.
     * <br>The node created will have the position (x,y) on the screen, and the size of the original image will be readjusted.
     * @param fileName the name of the file with the image of the displayed element
     * @param x the x coordinate of the displayed element
     * @param y the y coordinate of the displayed element
     * @param width the width of the original image
     * @param height the height of the original image
     * @param fitWidth the resized width of the displayed element
     * @param fitHeight the resized height of the displayed element
     */
    public NonAnimatedThing(String fileName, double x, double y, int width, int height, int fitWidth, int fitHeight) {

        this.x=x;
        Image preSprite = new Image(fileName);
        sprite = new ImageView(preSprite);
        sprite.setViewport(new Rectangle2D(0,0,width,height));
        sprite.setX(x);
        sprite.setY(y);

        sprite.setFitWidth(fitWidth);
        sprite.setFitHeight(fitHeight);
    }

    /**
     * Returns the node of the displayed element
     */
    public ImageView getSprite(){
        return sprite;
    }

    /**
     * Updates the position of the NonAnimatedThing by moving it to the left.
     * <br>This method is only used to move the backgrounds of the game.
     * Thus, we expect that each background is doubled. The second background moves the same way as the first one, but with an offset equal to their width.
     * That way, when the first background moves, the second one moves accordingly and fills the released space.
     * Once the first background has fully disappeared to the left, the position of both backgrounds is reset.
     * @param width the width of the displayed element
     * @param velocity the speed of the displayed element
     * @param twin indicates if the element is the shifted twin one or not
     */
    public void updateBackground(int width, int velocity, boolean twin) {
        x += velocity * 0.16;
        double offset = (x-velocity*0.16)%width;
        if (!twin){
            sprite.setX(-offset);
        }
        else {
            sprite.setX(width-offset);
        }
    }
}
