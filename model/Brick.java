package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Brick extends GObject {
	private List<GObject> wall; 
	private int dimension; 
	
	public Brick(Color color, int size, int dimension) { 
		super(0, 0, size, color);
		wall = new ArrayList<>(); 
		this.dimension = dimension; 
		madeBrick();
	}
	
	public void madeBrick() {
		for ( int i = 0; i < dimension; i += 10) {
			for (int j = 0; j < dimension; j += 10) {
				if( (j == dimension - getSize() ) || (i == dimension - getSize()) || (j == 20 )|| (i == 0 )) {
					setX(i); 
					setY(j); 
					wall.add(new GObject(getX(), getY(), getSize(), getColor()));   
				}
			} 
		}
	}

	
	public List<GObject> getWall() {
		return wall;
	}

	public void setWall(List<GObject> wall) {
		this.wall = wall;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(getColor());
		g.fillRect(getX(), getY(), getSize(), getSize());
		
		for (GObject ob: wall) {
			ob.draw(g);
		}
	} 
}
