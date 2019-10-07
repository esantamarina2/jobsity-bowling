import game.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import parser.FileParser;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(JUnit4.class)
public class FileParserTest {

    private static final String GAME_FILE = "test-data.txt";
    private static final String PERFECT_PLAYER = "Carl";
    private static final String ZERO_PLAYER = "Esteban";
    private static final String GAME_PLAYER = "Jeff";
    private static final int PERFECT_SCORE = 300;
    private static final int ZERO_SCORE = 0;

    private File file;
    private FileParser fileParser;

    /*
   Scenario: test file processing with extreme scores
         return 200 OK
    */
    @Before
    public void init(){
        file = new File(getClass().getClassLoader().getResource(GAME_FILE).getFile());
        fileParser = new FileParser();
        fileParser.setFile(file);
    }


    @Test
    public void validateExtremeScores() {
        fileParser.process();
        Game perfectGame = fileParser.getGame(PERFECT_PLAYER);
        Game zeroGame = fileParser.getGame(ZERO_PLAYER);
        int perfectResult = perfectGame.getLine().getScores().get(perfectGame.getLine().getScores().size()-1);
        int zeroResult = zeroGame.getLine().getScores().get(zeroGame.getLine().getScores().size()-1);
        assertThat(perfectResult, is(equalTo(PERFECT_SCORE)));
        assertThat(zeroResult, is(equalTo(ZERO_SCORE)));
    }

        /*
    Scenario: test file processing validating all the scores
          return 200 OK
     */
        @Test
        public void validateAllScores() {
            fileParser.process();
            String expression = "[20, 39, 48, 66, 74, 84, 90, 120, 148, 167]";
            Game currentGame = fileParser.getGame(GAME_PLAYER);
            assertThat(expression, is(equalTo(currentGame.getLine().getScores().toString())));
        }

    /*
  Scenario: test file processing validating all the pin falls
        return 200 OK
   */
    @Test
    public void validatePinFallsExpression() {
        fileParser.process();
        String expression = "[X, 7 /, 9 0, X, 0 8, 8 /, F 6, X, X, X 8 1]";
        Game currentGame = fileParser.getGame(GAME_PLAYER);
        assertThat(expression, is(equalTo(currentGame.getLine().getThrowsList().toString())));
    }



}
