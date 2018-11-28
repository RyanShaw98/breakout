import javax.swing.*;
import java.awt.*;

/**
 * This class defines the frame settings TODO Maybe move to the main class as a method
 */

class GameFrame extends JFrame implements Settings {

    GameFrame(Component c) {
        Component comp;
        setTitle(FRAME_TITLE);

        comp = c;
        getContentPane().setBackground(Color.BLACK);
        getContentPane().add(comp);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT)); // TODO Figure out why frame size is not displaying correctly
        //pack();

        repaint();
    }
}
