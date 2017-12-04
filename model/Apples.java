package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Apples {
    private List<GObject> apples;

    public Apples() {
        apples = new ArrayList<>();

        apples.add (new GObject(500, 425, 15, Color.RED));
        apples.add (new GObject(550, 250, 15,  Color.magenta ));
        apples.add (new GObject( 350, 125,15, Color.GREEN ));


    }

    public List<GObject> getApples() {
        return apples;
    }

    public void draw(Graphics2D g2) {
        for (GObject ob: apples) {
            ob.draw(g2);
        }
    }

    public int getAppleX(int i) {
        return apples.get(i).getX();
    }

    public int getAppleY(int i) {
        return apples.get(i).getY();
    }
}



