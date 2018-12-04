import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuView extends JComponent implements Settings {

    JTextField nameTField;
    ButtonGroup bg;

    MenuView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("BREAKOUT", SwingConstants.CENTER);
        add(titleLabel, gbc);
        titleLabel.setForeground(Color.WHITE);
        gbc.gridy++;

        gbc.insets = new Insets(50, 0, 0, 0);

        JLabel nameLabel = new JLabel("Name:");
        add(nameLabel, gbc);
        nameLabel.setForeground(Color.WHITE);
        gbc.gridy++;

        gbc.insets = new Insets(0, 0, 0, 0);

        nameTField = new JTextField();
        nameTField.setForeground(Color.WHITE);
        nameTField.setOpaque(false);
        nameTField.setColumns(10);
        add(nameTField, gbc);
        gbc.gridy++;

        gbc.insets = new Insets(50, 0, 0, 0);

        JLabel difficultyLabel = new JLabel("Difficulty:");
        add(difficultyLabel, gbc);
        difficultyLabel.setForeground(Color.WHITE);
        gbc.gridy++;

        gbc.insets = new Insets(0, 0, 0, 0);

        JRadioButton easyBtn = new JRadioButton("Easy");
        easyBtn.setActionCommand(easyBtn.getText());
        easyBtn.setForeground(Color.WHITE);
        easyBtn.setOpaque(false);

        JRadioButton medBtn = new JRadioButton("Medium");
        medBtn.setActionCommand(medBtn.getText());
        medBtn.setForeground(Color.WHITE);
        medBtn.setOpaque(false);

        JRadioButton hardBtn = new JRadioButton("Hard");
        hardBtn.setActionCommand(hardBtn.getText());
        hardBtn.setForeground(Color.WHITE);
        hardBtn.setOpaque(false);

        easyBtn.setSelected(true);

        bg = new ButtonGroup();
        bg.add(easyBtn);
        bg.add(medBtn);
        bg.add(hardBtn);

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.add(easyBtn);
        buttons.add(medBtn);
        buttons.add(hardBtn);
        add(buttons, gbc);
        gbc.gridy++;

        gbc.insets = new Insets(50, 0, 0, 0);

        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(new ButtonHandler(this));
        startBtn.setBackground(Color.BLACK);
        startBtn.setForeground(Color.WHITE);
        add(startBtn, gbc);
    }
}

class ButtonHandler implements ActionListener {
    private MenuView app;

    ButtonHandler(MenuView app) {
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