package model;

import java.awt.Color;
import java.util.Random;

public class Apple extends GObject {
	private int dimension;  // kde všude se mi mùže vykreslit jabko - ètvrecové plátno
	private Random random; 

	public Apple(int dimension, Color color, int size ) {
		super(0, 0, size, color);
		this.dimension = dimension;
		random = new Random(); 
		
	} 
	
	public void AppleLocation() {
		int pixel; 
		
		pixel =  random.nextInt((dimension - getSize())/getSize());  	//mìlo by to hlídat okraje, aby se jablo nenasralo pod okrajovou kravinu 
		setX(pixel * getSize() + getSize());
		pixel =  random.nextInt((dimension - getSize())/ getSize()); 
		setY(pixel * getSize() + getSize());
	}
	
	
	
	

}
