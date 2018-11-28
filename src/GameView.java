/**
 * The GameView class displays the game
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends JComponent implements Settings {

    private Rectangle paddle = new Rectangle();

    GameView() {
        addKeyListener(new TAdapter());
        setFocusable(true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fill3DRect(paddle.getX(), paddle.getY(), REC_WIDTH, REC_HEIGHT, true);


    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {paddle.keyPressed(e);}

        @Override
        public void keyReleased(KeyEvent e) {paddle.keyReleased(e);}
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {

            //ball.move();
            paddle.move();
            //checkCollision();
            repaint();
        }
    }
}
