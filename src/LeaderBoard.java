import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages the leader board which is stored in an external text file.
 */
class LeaderBoard implements Settings, DisplayLeaderBoard {
    private double score;
    private String name;

    /**
     * Constructor that gets the player name and score.
     *
     * @param name  player name
     * @param score player score
     */
    LeaderBoard(String name, double score) {
        this.score = score;
        this.name = name;
    }

    /**
     * Stores all scores from text file in a list of list, where each text file line is separated by white space.
     *
     * @return a list of list where each of the second lists contains a single players score.
     */
    @SuppressWarnings("TryWithIdenticalCatches")
    public ArrayList<ArrayList<String>> getAllScores() {
        ArrayList<ArrayList<String>> playerScores = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> individualScore = new ArrayList<>();
                String[] leaderBoardCombo = line.split("\\s+");
                for (String word : leaderBoardCombo) {
                    Collections.addAll(individualScore, word);
                }
                playerScores.add(individualScore);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return playerScores;
    }

    /**
     * Gets the top ten scores of all time using the 'getAllScores' method and sorts the result using the 'ListComparator' class.
     *
     * @return a sorted list of lists containing the top ten scores of all time.
     */
    public ArrayList<ArrayList<String>> getTopTen() {
        ArrayList<ArrayList<String>> topTen = getAllScores();
        topTen.sort(new ListComparator());

        while (topTen.size() > 10) {
            topTen.remove(10);
        }
        return topTen;
    }

    /**
     * Formats the players name by removing un-necessary whitespace and then writes the formatted name and  the players score to an external text file.
     */
    void saveScore() {
        try {
            String[] nameSplit = name.split("\\s+");
            StringBuilder sb = new StringBuilder();
            String formatName = "";
            for (String word : nameSplit) {
                formatName = sb.append(word).append(" ").toString();
            }
            FileWriter writer = new FileWriter(FILE_PATH, true);
            writer.write((formatName + " " + score + "\n"));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}