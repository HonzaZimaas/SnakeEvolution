package model;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Apples {
    private List<GObject> apples;

    public Apples() {
        apples = new ArrayList<>();

        GObject apple = new GObject(50, 20, 10, Color.red);
        GObject peach = new GObject( 230, 400,10, Color.cyan );
        GObject grapes = new GObject(450, 50, 10,  Color.magenta );

        apples.add(apple);
        apples.add(peach);
        apples.add(grapes);

    }

    public List<GObject> getApples() {
        return apples;
    }

    public void setApples(List<GObject> apples) {
        this.apples = apples;
    }


    public void draw(Graphics2D g2) {
        for (GObject ob: apples) {
            ob.draw(g2);
        }
    }
}



