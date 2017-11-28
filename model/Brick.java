package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

    public class Brick extends GObject {
        private List<GObject> wall;
        private int maxY;
        private int maxX;

        public Brick(Color color, int size, int width, int height) {
            super(0, 0, size, color);
            wall = new ArrayList<>();
            this.maxX = width ;
            this.maxY = height ;

            madeBrick();
        }

        private void madeBrick() {
            for ( int i = 0; i < maxX; i += 5) {
                for (int j = 0; j < maxY; j += 5) {
                    if ( (j == maxY - 40 ) || (i == maxX - 20 ) || (j == 0 )|| (i == 0  )  ||    // edge

                            ((j == 250) && (( i > 100) && (i < 350 )))   ||        // cross double
                            ((j == 255) && (( i > 100) && (i < 350 )))   ||

                            ((i == 225) && (( j < 300) && (j > 100 )))   ||
                            ((i == 220) && (( j < 300) && (j > 100 )))   ||

                            (i == 375 ) && ( j > 550) || (i == 380 ) && ( j > 550)      // edge on x double
                     )
                    {
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


        @Override
        public void draw(Graphics2D g) {
            g.setColor(getColor());
            g.fillRect(getX(), getY(), getSize(), getSize());

            for (GObject ob: wall) {
                ob.draw(g);
            }
        }
    }



