package gui;

import model.Apples;
import model.Brick;
import model.Population;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JFrame implements Runnable{
    private JPanel panel;

    private Brick brick;
    private Population population;
    private Apples apples;

    private int height;
    private int width;

    private static int TICKS = 400;
    private static final int TOTAL = 400;
    private long cycleTime;
    private int round = 0;

    private boolean isRunning;


    public Canvas(int width , int height ) {
        this.width = width -1 ;
        this.height = height -4 ;

        setSize(width , height );
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Snake Game");
        setResizable(false);
        setFocusable(true);
        setIgnoreRepaint(false);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height ));
        add(panel, BorderLayout.CENTER);

        setVisible(true);

        SwingUtilities.invokeLater(() -> {
            SwingUtilities.invokeLater(() -> {
                SwingUtilities.invokeLater(() -> {
                    SwingUtilities.invokeLater(() -> {
                        gameInit();
                    });
                });
            });
        });
    }


    private void gameInit() {
        isRunning = true;

        population = new Population();
        apples = new Apples();
        brick = new Brick(Color.gray, 5, width , height );

        updateGraphics();
        start();
    }

    private void updateGraphics() {
        Graphics2D g2 = (Graphics2D) panel.getGraphics();
        g2.setBackground(Color.black);
        g2.fillRect(5,5,width - 25 , height - 45  );

        brick.draw(g2);
        apples.draw(g2);
        population.draw(g2, brick, apples,(TOTAL + 1 - TICKS) , round);
    }


    private void start(){
        Thread thread = new Thread(this, "Snake move");
        thread.start();
    }

    @Override
    public void run() {
        while(isRunning) {
            while(TICKS > 0 ) {
                updateGraphics();
                frameRate();
                TICKS --;
            }
            population.createNewPopulation(apples);
            TICKS = TOTAL ;
            round ++ ;
            System.out.println("-------Generace:"+ round +"-------");

        }
    }


    private void frameRate() {
        int FRAME_DELAY = 30;
        cycleTime += FRAME_DELAY;
        long difference = cycleTime - System.currentTimeMillis();

        try {
            Thread.sleep(Math.max(0, difference));
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        cycleTime = System.currentTimeMillis();
    }
}
