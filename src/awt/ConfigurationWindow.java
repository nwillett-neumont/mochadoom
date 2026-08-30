package awt;

import utils.ConfigHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationWindow extends JFrame {
    private final ConfigHelper helper = new ConfigHelper();

    private JPanel top;
    private JPanel mainWindow;
    private JPanel bottom;
    private JLabel title;
    private JComboBox<String> gameSelect;
    private JCheckBox autorunCheckbox;
    private JButton submit;


    public ConfigurationWindow(Runnable runGame) {
        initComponents();
        addComponents();

        setValues();

        gameSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helper.setSelectedGame(((JComboBox<String>)e.getSource()).getSelectedItem().toString());
            }
        });

        autorunCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                helper.setAutorunEnabled(!helper.isAutorunEnabled());
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launch();
                new Thread(runGame).start();
            }
        });
    }

    void initComponents() {
        top = new JPanel();
        mainWindow = new JPanel();
        bottom = new JPanel();

        title = new JLabel("Mochaconfig");
        gameSelect = new JComboBox(helper.getGameFiles().keySet().toArray());
        autorunCheckbox = new JCheckBox("Enable autorun");
        submit = new JButton("Launch");
    }
    void addComponents() {
        top.add(title);

        mainWindow.add(gameSelect);
        mainWindow.add(autorunCheckbox);

        bottom.add(submit);

        this.add(top);
        this.add(mainWindow);
        this.add(bottom);
    }

    void setValues() {
        this.setSize(600, 800);
        this.setMinimumSize(new Dimension(200, 300));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        top.setPreferredSize(new Dimension(600, 60));
        mainWindow.setPreferredSize(new Dimension(600, 640));
        bottom.setPreferredSize(new Dimension(600, 100));

        this.getContentPane().add(BorderLayout.NORTH, top);
        this.getContentPane().add(BorderLayout.CENTER, mainWindow);
        this.getContentPane().add(BorderLayout.SOUTH, bottom);

        title.setFont(title.getFont().deriveFont(Font.PLAIN, 32));

        gameSelect.setSelectedIndex(new ArrayList<>(List.of(helper.getGameFiles().keySet().toArray())).indexOf(helper.getSelectedGame()));

        autorunCheckbox.setSelected(helper.isAutorunEnabled());
    }

    void launch() {
        setVisible(false);
        dispose();

        helper.setSetting("alwaysrun", helper.isAutorunEnabled());
        helper.setSetting("lastgame", helper.getSelectedGame());
        ConfigHelper.setGameCommandVariable("-iwad " + helper.getGameFiles().get(helper.getSelectedGame()));
    }
}
