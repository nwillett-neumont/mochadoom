package awt;

import utils.RunGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurationWindow extends JFrame {
    private JPanel mainWindow;
    private JLabel title;
    private JCheckBox autorunCheckbox;
    private JButton submit;

    public ConfigurationWindow(Runnable runGame) {
        initComponents();
        addComponents();

        this.setSize(600, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launch();
                new Thread(runGame).start();
            }
        });
    }

    void initComponents() {
        mainWindow = new JPanel();
        title = new JLabel("Mochaconfig");
        autorunCheckbox = new JCheckBox("Enable autorun");
        submit = new JButton("Launch");
    }

    void addComponents() {
        mainWindow.add(title);
        mainWindow.add(autorunCheckbox);
        mainWindow.add(submit);
        add(mainWindow);
    }

    void launch() {
        setVisible(false);
        dispose();
    }
}
