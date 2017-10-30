package gui;

import java.awt.Dimension;


import javax.swing.JFrame;



public class MainBoard extends JFrame{
	private static final long serialVersionUID = -4458522672757342981L;

	public MainBoard(String title, int width, int height){
			setTitle(title);
			setSize(new Dimension(width , height ));
			setLocationRelativeTo(null);    //okno se otevøe uprostøed plochy
			setResizable(false); 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			createBufferStrategy(1);
			
			add(new GameBoard(height , width , getBufferStrategy())); 
			
		
			
		
			
			
		}
		
	}

