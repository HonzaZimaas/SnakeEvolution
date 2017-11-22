package gui;

import model.Apple;
import model.Brick;
import model.Snake;

import utils.Collisions;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JFrame implements Runnable{

    private JPanel panel;
    private Apple apple;
    private Apple peach;
    private Apple grapes;
    private Brick brick;
    private Snake snake;


    private int height;
    private int width;

    protected static int TICKS = 500;
    private long cycleTime;
    private final int FRAME_DELAY = 100;

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
        apple = new Apple(50, 20, Color.red,10);
        peach = new Apple ( 530, 500, Color.green, 10);
        grapes = new Apple (450, 50, Color.magenta, 10);
        brick = new Brick(Color.gray, 10, width , height );
        snake = new Snake(200, 400, 10, Color.green, Color.ORANGE);

        updateGraphics();
        start();
    }

    public void updateGraphics() {
        Graphics2D g2 = (Graphics2D) panel.getGraphics();
        g2.setBackground(Color.black);
        g2.fillRect(10,10,width, height );

        peach.draw(g2);
        apple.draw(g2);
        grapes.draw(g2);
        brick.draw(g2);
        snake.draw(g2);

    }


    public void updateLogic() {
     if(Collisions.eat(snake, apple)){
            snake.expandBody();
        }

        else if(Collisions.defect(snake, brick)) {
            isRunning = false;
        }

     /* else if (Collisions.checkCollision(snake)){
            isRunning = false; }  */

        else {
            snake.changeDirect();
            snake.move(); }
    }

    public void start(){
        Thread thread = new Thread(this, "Snake move");
        thread.start();
    }

    @Override
    public void run() {
        while(isRunning) {
            while(TICKS > 0 ) {
                updateLogic();
                updateGraphics();
                synchFrameRate();
                TICKS --;
            }
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
