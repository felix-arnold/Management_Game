package Airship;

import General.AnimatedThing;
import Quarter.Quarter;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.util.ArrayList;


public abstract class Airship {

    protected String name;

    /*protected int armour;
    protected int health;
    protected int speed;
    protected int recoveryRate;
    protected int crewCapacity;
    protected int crew;
    protected int electricity;*/
    static int numberOfShip;


    protected int[][] positionQuarter;
    protected Quarter[][] quarterList;
    protected int numberQuarter;

    public Airship(String name) {
        this.name = name;
        numberOfShip++;
        }

    public String getName() {
        return name;
    }

    public Quarter[][] getQuarterList() {
        return quarterList;
    }

    public int getNumberQuarter() {
        return numberQuarter;
    }

}
