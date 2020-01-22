import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class ConfigParser {
    private HashMap<String, String> configData;

    public ConfigParser(String pathName) {
        this.configData = new HashMap<>();
        storeData(pathName);
    }

    private void storeData(String pathName) {
        Path path = Paths.get(pathName);
        boolean appStatus = false, seen = false;
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            String appDetail = "[application]";
            for (String line : lines) {
                if(appStatus && line.isEmpty()) {
                    appStatus = false;
                    seen = true;
                } else if(line.equalsIgnoreCase(appDetail)) {
                    appStatus = true;
                }

                if(!(appStatus && seen) && !line.equalsIgnoreCase(appDetail)) {
                    String key = "";

                    if (appStatus) {
                        key += "application.";
                    }

                    StringTokenizer tokens = new StringTokenizer(line, "=");
                    if (tokens.hasMoreTokens()) {
                        key += tokens.nextToken();
                        this.configData.put(key, tokens.nextToken());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid path provided.");
            System.err.println("exception: " + e.getMessage());
        }
    }

    public String get(String key) {
        return configData.get(key);
    }

    public HashMap<String, String> all() {
        return configData;
    }
}
