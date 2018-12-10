import java.awt.*;

/**
 * 'Square' creates a brick and defines its state, life points, whether it contains a give co-ordinate and how it should be drawn.
 */
class Square extends Shape implements Settings {

    private boolean broken;
    private int life;

    /**
     * Constructor that sets the brick's default values.
     *
     * @param pos_x x position.
     * @param pos_y y position.
     */
    Square(double pos_x, double pos_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        broken = false;
        life = 3;
    }

    /**
     * @return a boolean that states if the brick has been broken.
     */
    boolean isBroken() {
        return broken;
    }

    /**
     * Sets the brick to broken (Usually when the brick has ran out of lives).
     */
    void setBroken() {
        broken = true;
    }

    /**
     * @return how many lives the brick has remaining.
     */
    int getLife() {
        return life;
    }

    /**
     * Deduct one life from the brick.
     */
    void removeLife() {
        life--;
    }

    /**
     * Checks to see if the brick has a co-ordinate and if so, where abouts
     * does that co-ordinate lie within the brick; it will split each face into
     * two parts and then searches every part (for a total of eight) for the given
     * co-ordinate.
     *
     * @param x co-ordinate.
     * @param y co-ordinate.
     * @return an int referring to where the co-ordinate was found, or '0' if it was not found.
     */
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

    /**
     * If the brick is not broken it draws the brick in either green, yellow or red depending on the color at the specified position and at the specified size.
     *
     * @param g
     */
    void draw(Graphics g) {
        if (!this.isBroken()) {
            if (this.getLife() == 3) {
                g.setColor(Color.GREEN);
            } else if (this.getLife() == 2) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.RED);
            }
            g.fill3DRect((int) pos_x, (int) pos_y, (int) SQUARE_SIZE, (int) SQUARE_SIZE, true);
        }
    }
}
