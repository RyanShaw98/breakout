import java.awt.*;

/**
 * 'Rectangle' creates the paddle and defines its movement behaviour and how it should be drawn.
 */

class Rectangle extends Shape implements Settings {

    private static int directionX;

    /**
     * Constructor which sets the start x and y co-ordinates.
     */
    Rectangle() {
        pos_x = REC_START_POS_X;
        pos_y = REC_POS_Y;
    }

    /**
     * Moves the paddle to the right.
     */
    static void moveRight() {
        directionX = 1;
    }

    /**
     * Moves the paddle to the left.
     */
    static void moveLeft() {
        directionX = -1;
    }

    /**
     * Stops the paddle from moving when either the left arrow or right arrow key is released.
     */
    static void resetMove() {
        directionX = 0;
    }

    /**
     * Moves the paddle along the x-axis and prevents it from moving beyond the frame borders.
     */
    void move() {
        pos_x += directionX;

        if (pos_x <= 0) {
            pos_x = 0;
        }

        if (pos_x >= (FRAME_WIDTH - REC_WIDTH - HOR_INSET)) {
            pos_x = FRAME_WIDTH - REC_WIDTH - HOR_INSET;
        }
    }

    /**
     * Draws the paddle in white at the specified position and at the specified size.
     *
     * @param g
     */
    void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fill3DRect((int) this.pos_x, (int) this.pos_y, (int) REC_WIDTH, (int) REC_HEIGHT, true);
    }
}
