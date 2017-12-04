package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends GObject{
    private List<GObject> body;
    private List<Direction> directions;   // seznam smeru hada v prvni generaci via puvodni gen
    private Direction direct;
    private Color colorBody;

    private boolean isAlive = true;
    private boolean isBigger = false;

    private Random rnd;
    private DNA dna;


    public Snake(int x,int y,int size, Color color, Color colorBody) {
        super(x, y, size, color );
        body = new ArrayList<>();
        directions = new ArrayList<>();

        rnd = new Random();
        this.dna = new DNA(directions);

        setColorBody(colorBody);
        setDirect(Direction.RIGHT);
        fillSnake();
    }

    public Snake(int x,int y,int size, Color color, Color colorBody, DNA dna) {
        super(x, y, size, color );
        body = new ArrayList<>();

        rnd = new Random();
        this.dna = dna;

        setColorBody(colorBody);
        setDirect(Direction.RIGHT);
        fillSnake();
    }

    private void fillSnake() {
        body.add(new GObject((getX() - getSize()), getY(), getSize(), getColorBody()));
        body.add(new GObject((getX() - getSize()), getY(), getSize(), getColorBody()));
        body.add(new GObject ((getX() - getSize()), getY(), getSize(), getColorBody()));
    }

    public int calculateFitness(Apples apple) {
        int fitness = 0;
        int distance, nextDistance, lastDistance;


        if (isAlive) {
            distance = (int) (Math.sqrt(Math.pow( apple.getAppleX(0) - getX(),2) + Math.pow(apple.getAppleY(0) - getY(),2)));

            fitness =  apple.getAppleX(0) - distance ;
            if (distance < 200) fitness = fitness * 2;
            if (distance < 100) fitness = fitness * 3;
            if (distance < 10) { fitness = fitness * 5; isBigger = true;}

            if (isBigger)  { fitness = fitness * 10;

                nextDistance = (int) (Math.sqrt(Math.pow(apple.getAppleX(1) - getX(), 2) + Math.pow(apple.getAppleY(1) - getY(), 2)));
                fitness =  fitness + apple.getAppleY(1) - nextDistance ;
                if (nextDistance < 200) fitness = fitness * 2;
                if (nextDistance < 100) fitness = fitness * 3;
                if (nextDistance < 50) fitness = fitness * 5;

                lastDistance = (int) (Math.sqrt(Math.pow(apple.getAppleX(2) - getX(), 2) + Math.pow(apple.getAppleY(2) - getY(), 2)));
                fitness =  fitness + apple.getAppleY(2) - lastDistance ;
                if (lastDistance < 200) fitness = fitness * 2;
                if (lastDistance < 100) fitness = fitness * 3;
                if (lastDistance < 50) fitness = fitness * 5;
            }
        }


        return fitness;
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
        setBigger(true);
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

    public void move(int tick) {
    if (isAlive) {
        setDirect(dna.getDirections().get(tick) );
        moveBody();
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

    public void changeDirect() {
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


    public DNA getDNA() {
        return dna;
    }

    public void setBigger(boolean bigger) {
        isBigger = bigger;
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
