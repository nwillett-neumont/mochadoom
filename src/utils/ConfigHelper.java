package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigHelper {
    private final File configFile = new File("./mochadoom.cfg");
    private boolean autorunEnabled = false;
    private HashMap<String, String> gameFiles = new HashMap<>();

    public ConfigHelper() {
        autorunEnabled = getSettingToBoolean("alwaysrun");
    }

    public String getSetting(String setting) {
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

    public boolean getSettingToBoolean(String setting) {
        String found = getSetting(setting);
        return Boolean.parseBoolean(found);
    }

    public <T> void setSetting(String setting, T value) {
        Path configFilePath = configFile.toPath();
        boolean foundSetting = false;
        ArrayList<String[]> settings = new ArrayList<>();

        if (Files.exists(configFilePath)) {
            int lines = 0;

            try (BufferedReader reader = Files.newBufferedReader(configFilePath, StandardCharsets.US_ASCII)) {
                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.split("\t\t")[0].equalsIgnoreCase(setting)) foundSetting = true;
                    if (!foundSetting) lines++;
                    settings.add(currentLine.split("\t\t"));
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(configFilePath, StandardCharsets.US_ASCII)) {
                for (int i = 0; i < settings.size(); i++) {
                    if (i != lines) writer.write(MessageFormat.format("{0}\t\t{1}", settings.get(i)[0], settings.get(i)[1]));
                    else writer.write(MessageFormat.format("{0}\t\t{1}", setting, value + ""));
                    writer.newLine();
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }

    public HashMap<String, String> readGameDir() throws IOException {
        String directory = getSetting("gamesdir");
        Set<String> files;
        HashMap<String, String> games = new HashMap<>();

        try (Stream<Path> stream = Files.list(Paths.get(directory))) {
            files = stream.filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }

        for (String file : files) {
            String[] pathArray = file.split("/");
            games.put(pathArray[pathArray.length - 1], file);
        }

        return games;
    }

    public File getConfigFile() {
        return configFile;
    }

    public boolean isAutorunEnabled() {
        return autorunEnabled;
    }

    public void setAutorunEnabled(boolean autorunEnabled) {
        this.autorunEnabled = autorunEnabled;
    }

    public HashMap<String, String> getGameFiles() {
        return gameFiles;
    }

    public void setGameFiles(HashMap<String, String> gameFiles) {
        this.gameFiles = gameFiles;
    }
}
