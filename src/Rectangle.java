import java.awt.event.KeyEvent;

/**
 * The Rectangle class contains code for the paddle
 */

public class Rectangle extends Shape implements Settings {

    private int direction;

    Rectangle() {
        pos_x = START_POS_X;
        pos_y = START_POS_Y;
        System.out.println("(Rec) StartX: " + START_POS_X + "\n(Rec) StartY: " + START_POS_Y);
    }

    void move() {
        pos_x += direction;

        if (pos_x <= 0) {
            pos_x = 0;
        }

        if (pos_x >= (FRAME_WIDTH - (REC_WIDTH))) { // TODO: Fix the rectangle overflowing before getting stopped
            pos_x = FRAME_WIDTH - (REC_WIDTH );
        }
    }

    void keyPressed(KeyEvent e) {
        int moveLeft = 1;
        int moveRight = -1;

        int keyPressed = e.getKeyCode();

        if (keyPressed == KeyEvent.VK_RIGHT) {
            direction = moveLeft;
        }

        if (keyPressed == KeyEvent.VK_LEFT) {
            direction = moveRight;
        }
    }

    void keyReleased(KeyEvent e) {
        int keyPressed = e.getKeyCode();

        if (keyPressed == KeyEvent.VK_LEFT || keyPressed == KeyEvent.VK_RIGHT) {
            direction = 0; // When key is released stop moving
        }
    }
}
