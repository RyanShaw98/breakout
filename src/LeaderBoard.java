import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class LeaderBoard implements Settings {
    private int score;
    private String name;

    LeaderBoard(String name, int score) {
        this.score = score;
        this.name = name;
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    ArrayList<ArrayList<String>> getTopTen() {
        ArrayList<ArrayList<String>> playerScores = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null && count != 10) {
                ArrayList<String> individualScore = new ArrayList<>();
                String[] leaderBoardCombo = line.split("\\s+");
                for (String word : leaderBoardCombo) {
                    Collections.addAll(individualScore, word);
                }
                playerScores.add(individualScore);
                count++;
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

    void saveScore() {
        try {
            String[] nameSplit = name.split("\\s+");
            StringBuilder sb = new StringBuilder();
            String formatName = "";
            for (int i= 0; i < nameSplit.length; i++) {
                formatName = sb.append(nameSplit[i]).append(" ").toString();
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