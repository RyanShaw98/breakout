import java.util.ArrayList;

/**
 * An interface declaring the appropriate methods for the leader board.
 */
interface DisplayLeaderBoard {
    ArrayList<ArrayList<String>> getAllScores();
    ArrayList<ArrayList<String>> getTopTen();
}
