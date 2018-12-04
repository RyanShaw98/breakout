import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The GameView class displays the game
 */

public class GameView extends JComponent implements Settings {

    String difficulty;
    private String playerName;
    private Rectangle paddle = new Rectangle();
    private Circle ball = new Circle();
    private int gameOver = 0;
    private Timer timer = new Timer();
    private Square squares[];
    private int score = 0;

    GameView(String name, String difficulty) {
        InputKeyEvents bindings = new InputKeyEvents(this);

        bindings.setKeyBinds();

        playerName = name;
        this.difficulty = difficulty;
        squares = new Square[AMOUNT_OF_SQUARES];
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, setDifficulty(difficulty));

        int k = 0;
        int spacerY = 2;
        for (int i = 0; i < SQUARE_COLUMNS; i++) {
            int spacerX = 2;
            for (int j = 0; j < SQUARE_ROWS; j++) {
                squares[k] = new Square((j * SQUARE_SIZE) + spacerX, (i * SQUARE_SIZE) + spacerY);
                k++;
                spacerX += 2;
            }
            spacerY += 2;
        }
    }

    private int setDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                return 10;
            case "Medium":
                return 7;
            case "Hard":
                return 4;
        }
        return 10;
    }

    private void ballOutOfBounds() {
        if (ball.getY() > paddle.getY()) {
            setGameOver(-1);
        }
    }

    private void allBricksBroken() {
        for (int i = 0, j = 0; i < AMOUNT_OF_SQUARES; i++) {
            if (squares[i].isBroken()) {
                j++;
            }

            if (j == AMOUNT_OF_SQUARES) {
                setGameOver(1);
            }
        }
    }

    private void setGameOver(int x) {
        gameOver = x;
        timer.cancel();
    }

    private void collisionDetector() {
        // Collision detector for paddle and ball
        CollisionDetector detector = new CollisionDetector(paddle, ball);
        detector.checkCollisionBallAndPaddle();

        // Collision detector for ball and brick
        for (int i = 0; i < AMOUNT_OF_SQUARES; i++) {
            if (!squares[i].isBroken()) {
                detector = new CollisionDetector(squares[i], ball);
                if (detector.checkCollisionBallAndBrick()) {
                    squares[i].removeLife();
                    score++;
                    if (squares[i].getLife() == 0) {
                        squares[i].setBroken();
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        if (gameOver == 0) {
            drawInGame(g);
        } else {
            drawGameOver(g);
        }
    }

    private void drawInGame(Graphics g) {
        g.setColor(Color.WHITE);
        g.fill3DRect((int) paddle.getX(), (int) paddle.getY(), (int) REC_WIDTH, (int) REC_HEIGHT, true);
        g.fillOval((int) ball.getX(), (int) ball.getY(), (int) CIR_SIZE, (int) CIR_SIZE);
        g.drawString(String.valueOf(score), (int) ((FRAME_WIDTH * 0.95) - HOR_INSET), (int) ((FRAME_HEIGHT * 0.98) - VER_INSET));

        for (int i = 0; i < AMOUNT_OF_SQUARES; i++) {
            if (squares[i].getLife() == 3) {
                g.setColor(Color.GREEN);
            } else if (squares[i].getLife() == 2) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.RED);
            }
            if (!squares[i].isBroken()) {
                g.fill3DRect((int) squares[i].getX(), (int) squares[i].getY(), (int) SQUARE_SIZE, (int) SQUARE_SIZE, true);
            }
        }
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        String winMsg = "You win!";
        String loseMsg = "You lose!";
        String scoreMsg = playerName + ", you scored: " + String.valueOf(score);
        final int POS_INSET = 3;
        if (gameOver == -1) {
            g.drawString(loseMsg, (FRAME_WIDTH - g.getFontMetrics().stringWidth(loseMsg) - HOR_INSET) / 2, (int) (FRAME_HEIGHT * LAST_GAME_STATS_POS_Y));
        } else {
            g.drawString(winMsg, (FRAME_WIDTH - g.getFontMetrics().stringWidth(winMsg) - HOR_INSET) / 2, (int) (FRAME_HEIGHT * LAST_GAME_STATS_POS_Y));
        }
        g.drawString(scoreMsg, (FRAME_WIDTH - g.getFontMetrics().stringWidth(scoreMsg) - HOR_INSET) / 2, (int) (FRAME_HEIGHT * (LAST_GAME_STATS_POS_Y + 0.05)));

        LeaderBoard leaderboard = new LeaderBoard(playerName, score);
        leaderboard.saveScore();

        int colourCount = 0;
        ArrayList<ArrayList<String>> leaderBoardScores = leaderboard.getTopTen();
        leaderBoardScores.sort(new ListComparator());
        double leaderBoardMsgPosY = LEADER_BOARD_POS_Y;
        int position = 1;
        for (ArrayList<String> combo : leaderBoardScores) {
            if (colourCount == 0) {
                g.setColor(Color.YELLOW);
            } else if (colourCount == 1) {
                g.setColor(new Color(192, 192, 192)); // Silver
            } else if (colourCount == 2) {
                g.setColor(new Color(205, 127, 50)); // Bronze
            } else {
                g.setColor(Color.WHITE);
            }

            StringBuilder sb = new StringBuilder();
            String leaderBoardMsg = position + ". ";
            for (int i = 0; i < combo.size(); i++) {
                leaderBoardMsg = sb.append(combo.get(i)).toString();
                if (i < combo.size() - 2) {
                    leaderBoardMsg = sb.append(" ").toString();
                } else if (i == combo.size() - 2){
                    leaderBoardMsg = sb.append(" - ").toString();
                }
            }

            g.drawString(leaderBoardMsg, (FRAME_WIDTH - g.getFontMetrics().stringWidth(leaderBoardMsg) - HOR_INSET - POS_INSET) / 2, (int) (FRAME_HEIGHT * leaderBoardMsgPosY));
            colourCount++;
            leaderBoardMsgPosY += 0.05;
            position++;
            if (position > 10) {
                break;
            }
        } // TODO add play again button
//        setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        JButton playAgain = new JButton();
//        add(playAgain, gbc);
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            ball.move();
            paddle.move();
            ballOutOfBounds();
            allBricksBroken();
            collisionDetector();
            repaint();
        }
    }
}