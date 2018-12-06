import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class InputMouseHandler implements ActionListener {
    private MenuView app;

    InputMouseHandler(MenuView app) {
        this.app = app;
    }

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