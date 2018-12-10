import java.util.Random;

/**
 * Defines constant variables used by numerous other classes and objects within this project.
 */
public interface Settings {

    // Frame settings
    String FRAME_TITLE = "Ryan Shaw (1605008)";
    int FRAME_WIDTH = 300;
    int FRAME_HEIGHT = 500;
    int HOR_INSET = 16; // HOR_INSET is used because setSize(x, y) includes the border and frame. Using getInsets we can see that left inset = 8 and right inset = 8 so we minus HOR_INSET (16) to counter this
    int VER_INSET = 31; // VER_INSET (-31) is used to counter insets

    // Rectangle settings
    double REC_WIDTH = 60.0;
    double REC_HEIGHT = 5.0;
    Random rand = new Random();
    double buff = rand.nextInt((int) (FRAME_WIDTH - REC_WIDTH - HOR_INSET));
    double REC_START_POS_X = buff;
    double REC_POS_Y = (FRAME_HEIGHT * 0.95) - VER_INSET;

    // Circle settings
    double CIR_START_POS_X = REC_START_POS_X + (REC_WIDTH / 2.0);
    double CIR_SIZE = 10.0;
    double CIR_START_POS_Y = REC_POS_Y - CIR_SIZE;

    // Square settings
    int AMOUNT_OF_SQUARES = 30;
    int SQUARE_ROWS = 6;
    int SQUARE_COLUMNS = 5;
    double SQUARE_SIZE = ((double) FRAME_WIDTH * 0.9) / (double) SQUARE_ROWS;

    // File settings
    String FILE_PATH = "src/scores.txt";

    // LeaderBoard settings
    double LAST_GAME_STATS_POS_Y = 0.1;
    double LEADER_BOARD_POS_Y = 0.25;
}