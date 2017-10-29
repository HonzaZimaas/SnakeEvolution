package gui;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;


import utils.Collisions;
import model.Apple;
import model.Brick;
import model.Snake;
import model.Direction;


public class GameBoard extends JPanel implements Runnable {

	private static final long serialVersionUID = -418365881549856414L;
	
	private final int h; 
	private final int w; 
	private boolean inGame; 
	
	private BufferStrategy bs; 
	private long cycleTime; 
	private final int FRAME_DELAY = 100;
	
	private Snake snake; 
	private Apple apple;
	private Brick brick; 
	
	public GameBoard(int width, int height, BufferStrategy bs) {
		w = width ; 
		h = height ;
		this.bs = bs; 
	
		addKeyListener(new TAdapter());
        setFocusable(true);
        setIgnoreRepaint(false);
		gameInit(); 
	}
	
	private void gameInit() {
		inGame = true; 
		snake = new Snake(250, 250, 10, Color.GREEN, Color.GRAY); 
		apple = new Apple(w, Color.RED, 10); 
		apple.AppleLocation();
		brick = new Brick(Color.GRAY, 10, w); 
		

		Thread animace = new Thread(this, "Game");
		animace.start();
		
	}
	
	private void updateGui() {
		Graphics2D g = (Graphics2D)bs.getDrawGraphics(); 
		g.setColor(Color.black);
		g.fillRect(0, 0, w, h);
				
		apple.draw(g);
		snake.draw(g);
		brick.draw(g);
		
		bs.show();
				
		Toolkit.getDefaultToolkit().sync();			
	}


	@Override
	public void run() {
		cycleTime = System.currentTimeMillis();
		while(inGame) {
			updateLogic();
			updateGui();
			synchFrameRate();
		}
		gameOver();
	}
	
	private void updateLogic() {
		if(Collisions.checkCollision(snake)){
			inGame = false; 
		}
			
		else if(Collisions.eat(snake, apple)){
			apple.AppleLocation(); 
			snake.expandBody();
			
			}
		
		else if(Collisions.defect(snake, brick)) {
			inGame = false;
			
		} 
		
		else {
		snake.move(); } 
	}
		
	
	private void gameOver() {
		Graphics2D g = (Graphics2D)bs.getDrawGraphics(); 
		g.setColor(Color.black);
		g.fillRect(0, 0, w, h);
	}

	private void synchFrameRate() {
		cycleTime += FRAME_DELAY;
		long difference = cycleTime-System.currentTimeMillis();
		
		try {
			Thread.sleep(Math.max(0, difference));
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		cycleTime = System.currentTimeMillis();
	}
	
	private class TAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			
			int key=e.getKeyCode();
            if ((key == KeyEvent.VK_UP || key==KeyEvent.VK_W) && (snake.getDirect()!=Direction.DOWN)) {
            	snake.setDirect(Direction.UP);
            }
            
            if ((key == KeyEvent.VK_RIGHT || key==KeyEvent.VK_D) && (snake.getDirect()!=Direction.LEFT)) {
            	snake.setDirect(Direction.RIGHT);
            }		
            
            if ((key == KeyEvent.VK_DOWN || key==KeyEvent.VK_S) && (snake.getDirect()!=Direction.UP)) {
            	snake.setDirect(Direction.DOWN);
            
            }
            if ((key == KeyEvent.VK_LEFT || key==KeyEvent.VK_A) && (snake.getDirect()!=Direction.RIGHT)) {
            	snake.setDirect(Direction.LEFT);
            }
		}
	}
}
