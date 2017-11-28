package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Apples {
    private List<GObject> apples;

    public Apples() {
        apples = new ArrayList<>();

        GObject apple = new GObject(550, 450, 25, Color.RED);
        GObject peach = new GObject( 350, 125,25, Color.GREEN );
        //GObject grapes = new GObject(450, 50, 10,  Color.magenta );

        apples.add(apple);
        apples.add(peach);
        //apples.add(grapes);
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



