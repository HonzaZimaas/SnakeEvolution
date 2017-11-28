package model;

import utils.Collisions;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
    private List<Snake> populationSnake, matingPool;
    private static final int poolSize = 20;
    private static final int size = 5;
    private DNA predekA, predekB, origingen;
    private Random rnd = new Random();


    public Population() {
        populationSnake = new ArrayList<>();

        for (int i = 0; i < poolSize; i++) {
            populationSnake.add(new Snake(175, 500, size, Color.green, Color.ORANGE));

        }
    }

    public void draw(Graphics2D g2, Brick brick, Apples apple, int tick, int round ) {
        for (Snake snake : populationSnake) {

            snake.draw(g2);

            if (round == 0) {
                snake.move();
            } else {
                snake.move(tick);
            }


            if (Collisions.defect(snake, brick)) {
                snake.setAlive(false);
            }

            if (Collisions.eat(snake, apple)) {
                snake.expandBody();
                snake.setBigger(true);
            }

    }
    }

    public void createNewPopulation(Apples apples) {
        List<Snake> childrenSnake = new ArrayList<>();

        matingPool = new ArrayList<>();

        for (Snake snake: populationSnake) {
            int fit = (int) (snake.calculateFitness(apples));
            System.out.println(fit);
            for (int i = 0; i < fit; i++) {
                matingPool.add(snake);
            }
        }

        for (int i = 0; i < poolSize; i++){
            predekA = matingPool.get(rnd.nextInt(matingPool.size())).getDNA();
            predekB = matingPool.get(rnd.nextInt(matingPool.size())).getDNA();

            origingen = predekA.crossOver(predekB);
            origingen.mutate();

            childrenSnake.add(new Snake(175, 500, size, Color.green, Color.ORANGE, origingen)); }

        populationSnake = childrenSnake;
    }




    }










