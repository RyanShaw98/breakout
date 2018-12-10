import java.awt.*;

/**
 * 'Circle' creates the ball and defines its movement behaviour and how it should be drawn.
 */

class Circle extends Shape implements Settings {

    private int directionX;
    private int directionY;

    /**
     * Constructor that sets its position and the direction it should initially move in.
     */
    Circle() {
        pos_x = (int) CIR_START_POS_X;
        pos_y = CIR_START_POS_Y;

        directionX = 1;
        directionY = -1;
    }

    /**
     * Defines how the ball should behave if it comes into contact with the north, west or eastern frame border.
     */
    void move() {
        pos_x += directionX;
        pos_y += directionY;

        if (pos_x == 0) { // West
            directionX = 1;
        }

        if (pos_x + CIR_SIZE == FRAME_WIDTH - HOR_INSET) {  // East
            directionX = -1;
        }

        if (pos_y == 0) { // North
            directionY = 1;
        }
    }

    /**
     * Changes the x direction of the ball.
     * @param x current direction of the ball in regards to the x-axis.
     */
    void setDirectionX(int x) {
        directionX = x;
    }

    /**
     * Changes the y direction of the ball.
     * @param y current direction of the ball in regards to the y-axis.
     */
    void setDirectionY(int y) {
        directionY = y;
    }

    /**
     * Draws the ball in white at the specified position and at the specified size.
     *
     * @param g graphics object.
     */
    void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) this.pos_x, (int) this.pos_y, (int) CIR_SIZE, (int) CIR_SIZE);
    }
}
