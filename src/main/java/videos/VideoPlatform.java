package videos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VideoPlatform {

    private List<Channel> channels = new ArrayList<>();

    public void readDataFromFile(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            boolean header = true;
            while ((line = reader.readLine()) != null) {
                if (header) {
                    line = reader.readLine();
                    header = false;
                }
                channels.add(createChannelByLine(line));
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot open file for read!", ioe);
        }
    }

    private Channel createChannelByLine(String line) {
        try {
            String[] row = line.split(";");
            return new Channel(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2]));
        } catch (NumberFormatException | NullPointerException exc) {
            throw new IllegalArgumentException("Invalid data in line: " + line, exc);
        }
    }

    public int calculateSumOfVideos() {
        return channels.stream()
                .mapToInt(Channel::getNumberOfVideos)
                .sum();
    }

    public List<Channel> getChannels() {
        return List.copyOf(channels);
    }
}
