package model;

import java.awt.*;

public class GObject {
    private int x;
    private int y;
    private int size;
    private Color color;

    public GObject(int x, int y, int size, Color color) {
       this.x = x;
       this.y = y;
       this.size = size;
       this.color = color;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect( x, y, size, size);
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
