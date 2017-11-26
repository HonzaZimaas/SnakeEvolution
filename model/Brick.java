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
            for ( int i = 0; i < maxY; i += 10) {
                for (int j = 0; j < maxX; j += 10) {
                    if ( (j == maxX  - 20 ) || (i == maxY -20 ) || (j == 0 )|| (i == 0  )  ||
                            ((j == 200) && (( i > 200) && (i < 350 ))) ||
                            ((i == 250) && (( j < 250) && (j > 100 ))) ) {
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



