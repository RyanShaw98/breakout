class CollisionDetector implements Settings {
    private Square brick;
    private Rectangle paddle;
    private Circle ball;

    CollisionDetector(Rectangle paddle, Circle ball) {
        this.paddle = paddle;
        this.ball = ball;
    }

    CollisionDetector(Square brick, Circle ball) {
        this.brick = brick;
        this.ball = ball;
    }

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
