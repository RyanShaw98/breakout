/**
 * The Square class will contain code for the squares that will break when hit by the ball
 */

public class Square extends Shape implements Settings {

    private boolean broken;
    private int life;

    Square(double pos_x, double pos_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        broken = false;
        life = 3;
    }

    boolean isBroken() {
        return broken;
    }

    void setBroken() {
        broken = true;
    }

    int getLife() {return life;}

    void removeLife() {
        life--;
    }

    int hasPoint(double x, double y) {
        double half = SQUARE_SIZE * 0.5;

        // Check top - left
        if (x >= pos_x && x <= pos_x + half && y == pos_y) {
            return 1;
        }

        // Check top - right
        if (x >= pos_x + half && x <= pos_x + SQUARE_SIZE && y == pos_y) {
            return 2;
        }

        // Check right - left
        if (y >= pos_y && y <= pos_y + half && x == pos_x + SQUARE_SIZE) {
            return 2;
        }

        // Check right - right
        if (y >= pos_y + half && y <= pos_y + SQUARE_SIZE && x == pos_x + SQUARE_SIZE) {
            return 4;
        }

        // Check bottom - left
        if (x >= pos_x && x <= pos_x + half && y == pos_y + SQUARE_SIZE) {
            return 3;
        }

        // Check bottom - right
        if (x >= pos_x + half && x <= pos_x + SQUARE_SIZE && y == pos_y + SQUARE_SIZE) {
            return 4;
        }

        // Check left - left
        if (y >= pos_y && y <= pos_y + half && x == pos_x) {
            return 1;
        }

        // Check left - right
        if (y >= pos_y + half && y <= pos_y + SQUARE_SIZE && x == pos_x) {
            return 3;
        }

        return 0;
    }
}
