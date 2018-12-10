import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles mouse events.
 */
class InputMouseHandler implements ActionListener {
    private MenuView app;

    /**
     * Constructor which defines the 'app' variable.
     *
     * @param app JComponent which refers to the game menu.
     */
    InputMouseHandler(MenuView app) {
        this.app = app;
    }

    /**
     * Handles mouse input for the play button in the menu. If the name field is empty prompt
     * the user to enter a name using a pop up message. If the name field is not empty it will
     * remove any leading and trailing white space. Once the program is satisfied with the name
     * it will remove the menu component from the frame and add a new 'GameView' component which
     * will display the game.
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (app.nameTField.getText().length() != 0) {
            String playerName = app.nameTField.getText().trim(); // .strip()
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(app);
            topFrame.remove(app);
            topFrame.add(new GameView(playerName, app.bg.getSelection().getActionCommand()));
            topFrame.revalidate();
        } else {
            JOptionPane.showMessageDialog(app, "Please enter a name");
        }
    }
}