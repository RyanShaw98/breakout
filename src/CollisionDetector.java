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
        double botBallX = ball.getX() + (CIR_SIZE * 0.5);
        double botBallY = ball.getY() + CIR_SIZE;

        double paddleLeftX = REC_WIDTH * (1.0 / 3.0);
        double paddleCenterX = REC_WIDTH * (2.0 / 3.0);

        // Left side of paddle
        if ((botBallX >= paddle.getX()) && (botBallX <= (paddle.getX() + paddleLeftX)) && (botBallY == paddle.getY())) {
            ball.setDirectionY(-1);
            ball.setDirectionX(-1);
        }

        // Center side of paddle
        if ((botBallX >= (paddle.getX() + paddleLeftX)) && (botBallX <= (paddle.getX() + paddleCenterX)) && (botBallY == paddle.getY())) {
            ball.setDirectionY(-1);
            ball.setDirectionX(0);
        }

        // Right side of paddle
        if ((botBallX >= (paddle.getX() + paddleCenterX)) && (botBallX <= (paddle.getX() + REC_WIDTH)) && (botBallY == paddle.getY())) {
            ball.setDirectionY(-1);
            ball.setDirectionX(1);
        }
    }

    boolean checkCollisionBallAndBrick() {
        if (brick.hasPoint(ball.getX(), ball.getY()) > 0) {
            switch (brick.hasPoint(ball.getX(), ball.getY())) {
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
            return true;
        }
        return false;
    }
}
