package awt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;

public class ConfigurationWindow extends JFrame {
    private JPanel mainWindow;
    private JLabel title;
    private JCheckBox autorunCheckbox;
    private JButton submit;
    private final File configFile = new File("./mochadoom.cfg");
    private boolean autorunEnabled = false;

    public ConfigurationWindow(Runnable runGame) {
        initComponents();
        addComponents();
        fetchValues();

        setValues();

        autorunCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                autorunEnabled = !autorunEnabled;
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
        mainWindow = new JPanel();
        title = new JLabel("Mochaconfig");
        autorunCheckbox = new JCheckBox("Enable autorun");
        submit = new JButton("Launch");
    }

    void fetchValues() {
        autorunEnabled = getSettingToBoolean("alwaysrun", configFile);
    }

    void addComponents() {
        mainWindow.add(title);
        mainWindow.add(autorunCheckbox);
        mainWindow.add(submit);
        add(mainWindow);
    }

    void setValues() {
        this.setSize(600, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        autorunCheckbox.setSelected(autorunEnabled);
    }

    void launch() {
        setVisible(false);
        dispose();

        setSetting("alwaysrun", autorunEnabled, configFile);
        System.out.println(getSetting("alwaysrun", configFile));
    }

    public String getSetting(String setting, File configFile) {
        Path configFilePath = configFile.toPath();

        if (Files.exists(configFilePath)) {
            try (BufferedReader reader = Files.newBufferedReader(configFilePath, StandardCharsets.US_ASCII)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] currentSetting = line.split("\t\t");

                    if (currentSetting[0].equalsIgnoreCase(setting)) {
                        return currentSetting[1];
                    }
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
        return "";
    }

    public boolean getSettingToBoolean(String setting, File configFile) {
        String found = getSetting(setting, configFile);
        return Boolean.parseBoolean(found);
    }

    public <T> void setSetting(String setting, T value, File configFile) {
        Path configFilePath = configFile.toPath();

        if (Files.exists(configFilePath)) {
            int lines = 0;

            try (BufferedReader reader = Files.newBufferedReader(configFilePath, StandardCharsets.US_ASCII)) {
                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.split("\t\t")[0].equalsIgnoreCase(setting)) break;
                    lines++;
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(configFilePath, StandardCharsets.US_ASCII)) {
                for (int i = 0; i < lines; i++) {
                    writer.newLine();
                }
                writer.write(MessageFormat.format("{0}\t\t{1}", setting, value + ""));
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }
}
