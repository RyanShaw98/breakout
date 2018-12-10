/**
 * Abstract class that all the other shapes inherit from.
 */
abstract class Shape {
    double pos_x;
    double pos_y;

    /**
     * Gets the current x co-ordinate of the shape.
     *
     * @return double of the x co-ordinate.
     */
    double getX() {
        return pos_x;
    }

    /**
     * Gets the current y co-ordinate of the shape.
     *
     * @return double of the y co-ordinate.
     */
    double getY() {
        return pos_y;
    }

}
