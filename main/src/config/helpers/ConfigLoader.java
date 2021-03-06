package src.config.helpers;

import src.config.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Configuration loader.
 * Reads from the config.txt file located at ~/LoveFuets/src/config/ directory.
 */
public class ConfigLoader {

    private LanguageHelper mLanguageHelper;

    /**
     * Constructor.
     */
    public ConfigLoader() {

        mLanguageHelper = new LanguageHelper();
    }

    /**
     * Reads the configuration file.
     *
     * @throws FileNotFoundException Exception.
     */
    public void readConfig() throws FileNotFoundException {

        //TODO: Beautify this. Really. It sucks.
        try {

            Scanner file = new Scanner(new File(CONFIG_FILE_NAME));

            String readLine;

            //Language
            boolean stop = false;
            while (file.hasNextLine() && !stop) {

                readLine = file.nextLine();
                if (isLineValid(readLine)) {

                    stop = true;
                    System.out.println(readLine);
                    mLanguageHelper.setLanguage(readLine);
                }
            }

            stop = false;
            //Hostname
            while (file.hasNextLine() && !stop) {

                readLine = file.nextLine();
                if (isLineValid(readLine)) {

                    stop = true;
                    Settings.setHostname(readLine);
                    System.out.println(readLine);
                }
            }

            stop = false;
            //Channel
            while (file.hasNextLine() && !stop) {

                readLine = file.nextLine();
                if (isLineValid(readLine)) {

                    stop = true;
                    Settings.setAutoJoinChannel("#" + readLine);
                    System.out.println(readLine);
                }
            }

            stop = false;
            //Bot name
            while (file.hasNextLine() && !stop) {

                readLine = file.nextLine();
                if (isLineValid(readLine)) {

                    stop = true;
                    Settings.setBotUsername(readLine);
                    System.out.println(readLine);
                }

            }

            if (Settings.getUsername() == null || Settings.getUsername().isEmpty()) {

                Settings.setBotUsername("LoveLetterBot");
            }

        } catch (FileNotFoundException e) {

            throw new FileNotFoundException("Unable to read from file");

        }

    }

    /**
     * Determines if line is valid.
     *
     * @param line Line.
     * @return Returns true if line is valid, false otherwise.
     */
    private boolean isLineValid(String line) {

        return !line.startsWith("#") && !isLineEmpty(line);
    }

    /**
     * Determines if line is empty.
     *
     * @param line Line.
     * @return True if line is empty, false otherwise.
     */
    private boolean isLineEmpty(String line) {

        return line.isEmpty() || line.trim().equals("") || line.trim().equals("\n");
    }

    public static final String CONFIG_FILE_NAME = "config.txt";
}
