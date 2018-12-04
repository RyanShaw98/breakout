/**
 * This class will contain code for the ball
 */

class Circle extends Shape implements Settings {

    private int directionX;
    private int directionY;
    Circle() {
        pos_x = (int) CIR_START_POS_X;
        pos_y = CIR_START_POS_Y;

        directionX = 1;
        directionY = -1;
    }

    void move() {
        pos_x += directionX;
        pos_y += directionY;

        if (pos_x == 0) {
            directionX = 1;
        }

        if (pos_x + CIR_SIZE == FRAME_WIDTH - HOR_INSET) { // We minus HOR_INSET because setSize(x, y) includes the border and frame. Using getInsets we can see that left inset = 8 and right inset = 8 so we minus HOR_INSET (16) to counter this
            directionX = -1;
        }

        if (pos_y == 0) {
            directionY = 1;
        }
    }
    void setDirectionX(int x) {
        directionX = x;
    }

    void setDirectionY(int y) {
        directionY = y;
    }
}
