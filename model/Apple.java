package model;

import java.awt.Color;
import java.util.Random;

public class Apple extends GObject {
	private int dimension;  // kde v�ude se mi m��e vykreslit jabko - �tvrecov� pl�tno
	private Random random; 

	public Apple(int dimension, Color color, int size ) {
		super(0, 0, size, color);
		this.dimension = dimension;
		random = new Random(); 
		
	} 
	
	public void AppleLocation() {
		int pixel; 
		
		pixel =  random.nextInt((dimension - getSize())/getSize());  	//m�lo by to hl�dat okraje, aby se jablo nenasralo pod okrajovou kravinu 
		setX(pixel * getSize() + getSize());
		pixel =  random.nextInt((dimension - getSize())/ getSize()); 
		setY(pixel * getSize() + getSize());
	}
	
	
	
	

}
