import java.awt.*;

/**
 * The Rectangle class contains code for the paddle
 */

class Rectangle extends Shape implements Settings {

    private static int directionX;

    Rectangle() {
        pos_x = REC_START_POS_X;
        pos_y = REC_POS_Y;
    }

    static void moveRight() {
        directionX = 1;
    }

    static void moveLeft() {
        directionX = -1;
    }

    static void resetMove() {
        directionX = 0;
    }

    void move() {
        pos_x += directionX;

        if (pos_x <= 0) {
            pos_x = 0;
        }

        if (pos_x >= (FRAME_WIDTH - REC_WIDTH - HOR_INSET)) {
            pos_x = FRAME_WIDTH - REC_WIDTH - HOR_INSET;
        }
    }

    void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fill3DRect((int) this.pos_x, (int) this.pos_y, (int) REC_WIDTH, (int) REC_HEIGHT, true);
    }
}
