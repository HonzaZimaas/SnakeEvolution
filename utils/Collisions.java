package utils;

import model.Apples;
import model.Snake;
import model.Brick;
import model.GObject;


public class Collisions {

    private Collisions(){}


    public static boolean eat(Snake snake, Apples apple){
        for (GObject ap : apple.getApples()) {
            if (snake.getX() == ap.getX()  && snake.getY() == ap.getY() ) {
                return true;
            }
        }
        return false;
        }


    public static boolean defect(Snake snake, Brick brick) {
        for (GObject ob: brick.getWall()) {
            if ((snake.getX() )  == ob.getX()  && (snake.getY()) == ob.getY() ) {
                return true;

            }
        }
        return false;
    }

}
