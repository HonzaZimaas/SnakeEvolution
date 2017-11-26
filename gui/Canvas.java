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

    public static int TICKS = 100;
    private long cycleTime;
    private final int FRAME_DELAY = 100;
    private int round = 0;

    private boolean isRunning;


    public Canvas(int width , int height ) {
        this.width = width - 26 ;
        this.height = height - 9 ;

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
        brick = new Brick(Color.gray, 10, width , height );

        updateGraphics();
        start();
    }

    public void updateGraphics() {
        Graphics2D g2 = (Graphics2D) panel.getGraphics();
        g2.setBackground(Color.black);
        g2.fillRect(10,10,width, height );

        brick.draw(g2);
        apples.draw(g2);
        population.draw(g2, brick, apples);
    }


    public void start(){
        Thread thread = new Thread(this, "Snake move");
        thread.start();
    }

    @Override
    public void run() {
        while(isRunning) {
            while(TICKS > 0 ) {
                updateGraphics();
                synchFrameRate();
                TICKS --;
            }
            population.createNewPopulation();
            TICKS = 100 ;
            round ++ ;
        }
    }


    private void synchFrameRate() {
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
