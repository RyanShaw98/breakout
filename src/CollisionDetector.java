/**
 * Handles the collision detection between the paddle, ball and bricks.
 */
class CollisionDetector implements Settings {
    private Square brick;
    private Rectangle paddle;
    private Circle ball;

    /**
     * Constructor for the paddle and ball.
     *
     * @param paddle game paddle.
     * @param ball   game ball.
     */
    CollisionDetector(Rectangle paddle, Circle ball) {
        this.paddle = paddle;
        this.ball = ball;
    }

    /**
     * Constructor for the bricks and ball.
     *
     * @param brick game brick.
     * @param ball  game ball.
     */
    CollisionDetector(Square brick, Circle ball) {
        this.brick = brick;
        this.ball = ball;
    }

    /**
     * Checks for a collision between the ball and paddle. If the two have collided, it will change the x and y directional values of the ball
     * depending on where it hit the paddle (left side, center or right side). It will check to see if the bottom left co-ordinate of the ball has collided
     * with the paddle as well as the bottom right co-ordinate of the ball so that the ball does not behave unexpectedly and clip through the paddle.
     */
    void checkCollisionBallAndPaddle() {
        double botBallXLeft = ball.getX() + (CIR_SIZE * 0.5); // Left side
        double botBallXRight = ball.getX() + (CIR_SIZE * 0.5); // Right side
        double botBallY = ball.getY() + CIR_SIZE;

        double paddleLeftX = REC_WIDTH * (1.0 / 3.0);
        double paddleCenterX = REC_WIDTH * (2.0 / 3.0);

        // Left side of paddle
        if (((botBallXLeft >= paddle.getX()) && (botBallXLeft <= (paddle.getX() + paddleLeftX)) && (botBallY == paddle.getY())) ||
                ((botBallXRight >= paddle.getX()) && (botBallXRight <= (paddle.getX() + paddleLeftX)) && (botBallY == paddle.getY()))) {
            ball.setDirectionY(-1);
            ball.setDirectionX(-1);
        }

        // Center side of paddle
        if (((botBallXLeft >= (paddle.getX() + paddleLeftX)) && (botBallXLeft <= (paddle.getX() + paddleCenterX)) && (botBallY == paddle.getY())) ||
                ((botBallXRight >= (paddle.getX() + paddleLeftX)) && (botBallXRight <= (paddle.getX() + paddleCenterX)) && (botBallY == paddle.getY()))) {
            ball.setDirectionY(-1);
            ball.setDirectionX(0);
        }

        // Right side of paddle
        if (((botBallXLeft >= (paddle.getX() + paddleCenterX)) && (botBallXLeft <= (paddle.getX() + REC_WIDTH)) && (botBallY == paddle.getY())) ||
                ((botBallXRight >= (paddle.getX() + paddleCenterX)) && (botBallXRight <= (paddle.getX() + REC_WIDTH)) && (botBallY == paddle.getY()))) {
            ball.setDirectionY(-1);
            ball.setDirectionX(1);
        }
    }

    /**
     * Sets the x and y directional values of the ball depending on which brick face (top, left, bottom, right)
     * and which part of the face (top side or bottom side, left side or right side) the ball collided with.
     * @param x co-ordinate of the ball.
     * @param y co-ordinate of the ball.
     * @param brick game brick.
     */
    private void setBallDirection(double x, double y, Square brick) {
        switch (brick.hasPoint(x, y)) {
            case 1:
                ball.setDirectionX(-1);
                ball.setDirectionY(-1);
                break;
            case 2:
                ball.setDirectionX(1);
                ball.setDirectionY(-1);
                break;
            case 3:
                ball.setDirectionX(-1);
                ball.setDirectionY(1);
                break;
            case 4:
                ball.setDirectionX(1);
                ball.setDirectionY(1);
                break;
        }
    }

    /**
     * Checks to see if the ball has collided with a brick; if it has, then it sets the directional values of the ball using the 'setBallDirection' method.
     * @return a boolean value where true is the ball has collided with a brick and false where the ball has not collided with a brick.
     */
    boolean checkCollisionBallAndBrick() {
        if (brick.hasPoint(ball.getX(), ball.getY()) > 0) { // Top left of ball
            setBallDirection(ball.getX(), ball.getY(), brick);
            return true;
        }

        if (brick.hasPoint(ball.getX() + CIR_SIZE, ball.getY()) > 0) { // Top right of ball
            setBallDirection(ball.getX() + CIR_SIZE, ball.getY(), brick);
            return true;
        }

        if (brick.hasPoint(ball.getX(), ball.getY() + CIR_SIZE) > 0) { // Bot left of ball
            setBallDirection(ball.getX(), ball.getY() + CIR_SIZE, brick);
            return true;
        }

        if (brick.hasPoint(ball.getX() + CIR_SIZE, ball.getY() + CIR_SIZE) > 0) { // Bot right of ball
            setBallDirection(ball.getX() + CIR_SIZE, ball.getY() + CIR_SIZE, brick);
            return true;
        }
        return false;
    }
}
