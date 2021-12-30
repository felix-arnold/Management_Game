package General;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class StaticThing {

    protected ImageView sprite;
    private double x;

    public StaticThing (String fileName, double x, double y, int width, int heigth) {

        this.x=x;
        Image preSprite = new Image(fileName);
        sprite = new ImageView(preSprite);
        sprite.setViewport(new Rectangle2D(0,0,width,heigth));
        sprite.setX(x);
        sprite.setY(y);
    }

    public ImageView getSprite(){
        return sprite;
    }

    public void updateBackground(int width, int velocity, int position) {
        x += velocity * 0.16;
        double offset = (x-velocity*0.16)%width;
        if (position==0){
            sprite.setX(-offset);
        }
        else if (position==1) {
            sprite.setX(width-offset);
        }
    }
}
