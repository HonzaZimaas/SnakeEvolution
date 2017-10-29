package utils;

import model.Apple;
import model.Snake;
import model.Brick;
import model.GObject;


public class Collisions {

	private Collisions(){}

	 public static boolean checkCollision(Snake snake) {
		for (GObject obj : snake.getBody()) {
			 if ((snake.getX()) == obj.getX() && (snake.getY()) == 	obj.getY()) {
				 return true; 
			 }
		 }
      return false;
		 
	 }
	
	public static boolean eat(Snake snake, Apple apple){
		if(snake.getX()== apple.getX() && snake.getY()==apple.getY()){
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean defect(Snake snake, Brick brick) {
		for (GObject ob: brick.getWall()) {
			if ((snake.getX()) == ob.getX() && (snake.getY()) == ob.getY()) {
				return true; 
				
			}
		}
		return false;
	} 
	
}
