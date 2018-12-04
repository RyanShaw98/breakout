import javax.swing.*;
import java.awt.*;

/**
 * This class defines the frame settings
 */

class GameFrame extends JFrame implements Settings {

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
