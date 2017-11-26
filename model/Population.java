package model;

import utils.Collisions;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Snake> populationSnake;
    private static final int poolSize = 10;


    public Population() {
        populationSnake = new ArrayList<>();

        for (int i = 0; i < poolSize; i++) {
            populationSnake.add(new Snake(200, 400, 10, Color.green, Color.ORANGE));
        }
    }

    public void draw(Graphics2D g2, Brick brick, Apples apple ) {
        for (Snake snake : populationSnake) {

            snake.draw(g2);
            snake.move();




            if (Collisions.defect(snake, brick)) {
                snake.setAlive(false);
            }

            if (Collisions.eat(snake, apple)) {
                snake.expandBody();
            }


    }}

    public void createNewPopulation() {
        List<Snake> childrenSnake = new ArrayList<>();

        for (Snake s: populationSnake) {



        }
    }
}









