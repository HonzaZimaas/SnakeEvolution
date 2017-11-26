package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends GObject{
    private List<GObject> body;
    private List<Direction> directions;   // seznam smeru hada
    private List<Direction> directionsNovy;
    private Direction direct;
    private Color colorBody;
    private boolean isAlive = true;

    private Random rnd;
    private DNA dna;

    public Snake(int x,int y,int size, Color color, Color colorBody) {
        super(x, y, size, color );
        body = new ArrayList<>();
        directions = new ArrayList<>();
        directionsNovy= new ArrayList<>();
        rnd = new Random();
        dna = new DNA();

        setColorBody(colorBody);
        setDirect(Direction.RIGHT);
        fillSnake();
    }

    public Snake(int x,int y,int size, Color color, Color colorBody, List<Direction> directions) {
        super(x, y, size, color );
        body = new ArrayList<>();
        this.directions = directions;
        rnd = new Random();
        dna = new DNA();

        setColorBody(colorBody);
        setDirect(Direction.RIGHT);
        fillSnake();
    }


    private void fillSnake() {
        GObject ob1 = new GObject((getX() - getSize()), getY(), getSize(), getColorBody());
        GObject ob2 = new GObject((getX() - getSize()), getY(), getSize(), getColorBody());
        GObject ob3 = new GObject ((getX() - getSize()), getY(), getSize(), getColorBody());
        body.add(ob1);
        body.add(ob2);
        body.add(ob3);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getSize(), getSize());

        for (GObject ob: body) {
            ob.draw(g);
        }
    }

    public void expandBody() {
        body.add(0, new GObject(getX(), getY(), getSize(), getColorBody())) ;
        moveHead();
    }

    public void move() {
        if (isAlive) {
            Direction d = getDirect();
            directions.add(d);

            moveBody();
            changeDirect();
            moveHead();
        }
    }



    private void moveBody() {
        int move;
        int moveX = getX();
        int moveY = getY();

        for(GObject ob : body) {
            move = ob.getX();
            ob.setX(moveX);
            moveX = move;

            move = ob.getY();
            ob.setY(moveY);
            moveY = move;
        }
    }

    private void moveHead() {
        switch (getDirect()) {
            case LEFT:
                setX(getX() - getSize());
                break;
            case RIGHT:
                setX(getX() + getSize());
                break;
            case UP:
                setY(getY() - getSize());
                break;
            case DOWN:
                setY(getY() + getSize());
                break;
        }
    }

    private void changeDirect() {
        int number = rnd.nextInt(4) ;
        switch (number) {
            case 0:
                if (getDirect() != Direction.DOWN) {
                    setDirect(Direction.UP);
                }
                break;

            case 1:
                if (getDirect() != Direction.LEFT) {
                    setDirect(Direction.RIGHT);
                }

                break;
            case 2:
                if (getDirect() != Direction.UP) {
                    setDirect(Direction.DOWN);
                }

                break;
            case 3:
                if (getDirect() != Direction.RIGHT) {
                    setDirect(Direction.LEFT);
                break;
                }
        }
    }





    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public List<GObject> getBody() {
        return body;
    }

    public void setBody(List<GObject> body) {
        this.body = body;
    }

    public Direction getDirect() {
        return direct;
    }

    public void setDirect(Direction direct) {
        this.direct = direct;
    }

    public Color getColorBody() {
        return colorBody;
    }

    public void setColorBody(Color colorBody) {
        this.colorBody = colorBody;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }
}
