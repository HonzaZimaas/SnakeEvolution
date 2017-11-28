package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class DNA {
    private List<Direction> directions = new ArrayList<>();
    private Random rnd = new Random();
    private Snake snake;
    private static int MUTATION_RATE = 8;    // chance to mutate


    public DNA(List<Direction> directions){
        this.directions = directions;
        }


    public void mutate() {
        for (int x = 1; x < directions.size() - 1  ; x++){
            if (rnd.nextInt(100) < MUTATION_RATE + 1){
                directions.set(x, mutateDirect(x));
            }
        }
    }

    public DNA crossOver(DNA dna ) {
        List<Direction> newDirections = new ArrayList<>();
        int mid = rnd.nextInt(directions.size());

        for (int x = 0; x < directions.size(); x++){
            if (x > mid){
                newDirections.add(directions.get(x));
            } else {
                newDirections.add(dna.directions.get(x));
            }
        }
        return new DNA(newDirections);
    }

    private Direction mutateDirect(int x) {
        int number = rnd.nextInt(4);
        Direction d = Direction.RIGHT;
        switch (number) {
            case 0:
                if (directions.get(x - 1) != Direction.DOWN) {
                  d = Direction.UP;
                }
                else {
                    d = Direction.DOWN;
                }
                break;

            case 1:
                if (directions.get(x - 1) != Direction.LEFT) {
                  d = Direction.RIGHT;
                }
                else {d = Direction.LEFT;}
                break;
            case 2:
                if (directions.get(x - 1 ) != Direction.UP) {
                   d = Direction.DOWN;
                }
                else {d = Direction.UP;}
                break;
            case 3:
                if (directions.get(x - 1) != Direction.RIGHT) {
                    d = Direction.LEFT;
                }else {d = Direction.RIGHT;}
                break;
        }

    return d;
    }

    public List<Direction> getDirections()
    {
        return directions;
    }



    }










