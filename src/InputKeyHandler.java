import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class InputKeyHandler {
    private JComponent app;
    private InputMap im;

    InputKeyHandler(JComponent app) {
        this.app = app;
        im = app.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    void setKeyBinds() {
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "Move Right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "Move Left");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "Move Right Release");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "Move Left Release");

        ActionMap ap = app.getActionMap();

        ap.put("Move Right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle.moveRight();
            }
        });

        ap.put("Move Left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle.moveLeft();
            }
        });

        ap.put("Move Right Release", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle.resetMove();
            }
        });

        ap.put("Move Left Release", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle.resetMove();
            }
        });
    }
}
