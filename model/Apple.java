package model;

import java.awt.*;
import java.util.Random;

public class Apple extends GObject {
    private int dimension;
    private Random random;

    public Apple(int x,int y, Color color, int size ) {
        super(x, y, size, color);
        random = new Random();
    }

    public void appleLocation() {
       setX(200);
       setY(400);
    }

}
