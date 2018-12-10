import javax.swing.*;
import java.awt.*;

/**
 * Defines the frame settings
 */

class GameFrame extends JFrame implements Settings {

    /**
     * Constructor that sets the desired values of the frame.
     *
     * @param frame either the 'menuView' or 'gameView' JComponent.
     */
    GameFrame(JComponent frame) {
        setTitle(FRAME_TITLE);
        getContentPane().setBackground(Color.BLACK);
        add(frame);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        repaint();
    }
}
