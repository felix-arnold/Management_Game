package Airship;

import General.AnimatedThing;
import Quarter.Quarter;

public class ManOWar extends Airship {

    int[][] positionQuarter = {{0,0},{1,0},{0,1},{1,1},{5,1},{6,1},{0,2},{1,2},{3,2},{4,2},{5,2},{6,2},{0,3},{1,3},{2,3},{3,3},{4,3},{5,3},{6,3},{0,4},{1,4},{2,4},{3,4},{4,4},{5,4},{6,4},{1,5},{2,5},{3,5},{4,5},{5,5}};
    int maxRow=6;
    int maxColumn=7;

    public ManOWar(String name) {
        super(name);
    }

    AnimatedThing image = new AnimatedThing("ManOWar.png",200, 20, 2574, 2480, 4, 727, 700);

    public AnimatedThing getImage() {
        return image;
    }

    public int[][] getPositionQuarter() {
        return positionQuarter;
    }
}
