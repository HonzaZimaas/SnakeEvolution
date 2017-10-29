package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Snake extends GObject{
	private List<GObject> body; 
	private Direction direct; 
	private Color colorBody;
	
	public Snake(int x,int y,int size, Color color, Color colorBody) {
		super(x, y, size, color ); 
		setColorBody(colorBody);
		setDirect(Direction.RIGHT); 
		body = new ArrayList<>();
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
		moveBody();
		moveHead();
		
	}
	
	public void moveBody() {
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
		
	public void moveHead() {
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
}
