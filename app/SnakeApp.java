package app;

import javax.swing.SwingUtilities;

import gui.MainBoard; 

public class SnakeApp{

	public static void main(String[]args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new MainBoard("Snake Game", 550, 550);
			}
		});
	}
}
