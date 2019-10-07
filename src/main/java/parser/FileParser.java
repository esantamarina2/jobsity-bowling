package parser;

import frame.Frame;
import frame.Roll;
import frame.Spare;
import frame.Strike;
import game.Game;
import game.Line;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileParser implements Parser {

    private File file;
    protected Logger log = Logger.getLogger(getClass());
    private Map<String, Game> games = new HashMap<>();

    private static final String MAX_FRAMES = "10";
    private static final String SPACE_DELIMITER = "\\s+";

    /* Read file line by line and created the associated frame
        Exception if file cannot be opened
     */
    public void process() {
        try {
            List<String> lines = Files.lines(file.toPath()).collect(Collectors.toList());
           for (int i= 0; i<lines.size(); i++) {
               String player = getLineField(lines.get(i), 0);
               Game currentGame;
               Frame frame;
               if (games.get(player) != null) {
                   currentGame = games.get(player);
               } else {
                   currentGame = new Game(player);
                   games.put(player, currentGame);
               }

               if (MAX_FRAMES.equals(getLineField(lines.get(i), 1))) {
                   frame = new Strike(new Roll(10, false), new Roll(0, false));
                   if ((currentGame.getLine().getThrowsList().size()+1 == 10) && i < lines.size()) {
                       frame.setSecondThrow(getFrameRoll(lines.get(i+1)));
                       ((Strike) frame).setExtraThrow(getFrameRoll(lines.get(i+2)));
                       i += 2;
                   }
               } else {
                   frame = getFrame(lines.get(i), lines.get(i + 1));
               }

               frame.setPosition(currentGame.getLine().getThrowsList().size());
               currentGame.getLine().getThrowsList().add(frame);

               if (!frame.isStrike()) {
                   i++;
               }
           }
            populateScores();
            printValues();
        }
         catch (IOException e) {
            log.error("Exception reading file " + file.getName(), e);
        }
    }

    public Game getGame(String player) {
       return games.get(player);
    }


    //Once all the file was read, populate the score for each player
    public void populateScores(){
        for (Map.Entry<String, Game> entry : games.entrySet()) {
            Game currentGame = entry.getValue();
            List<Integer> scores = currentGame.getLine().getScores();
            int partial = 0;
            for (int i = 0; i < currentGame.getLine().getThrowsList().size(); i++) {
                Frame currentFrame = getFrameByIndex(currentGame, i);
                partial += currentFrame.numberOfKnockedPins() + getBonusScore(currentGame, currentFrame);
                if (currentFrame.isStrike() && ((Strike)currentFrame).getExtraThrow() != null) {
                    partial += ((Strike)currentFrame).getExtraThrow().getValue();
                }
                scores.add(partial);
            }
            log.info("Score populated for " + currentGame.getPlayer());
        }
    }

    public Frame getFrameByIndex(Game game, int i)  {
        if (i <= game.getLine().getThrowsList().size()-1) {
           return game.getLine().getThrowsList().get(i);
        }
        return null;
    }

    private int getBonusScore(Game game, Frame currentFrame) {
        if (currentFrame.isSpare()) {
            return spareBonus(getFrameByIndex(game, currentFrame.getPosition()+1));
        }

        if (currentFrame.isStrike()) {
            return strikeBonus(game, getFrameByIndex(game, currentFrame.getPosition()+1));
        }
        return 0;
    }

    private int spareBonus(Frame frame) {
        if (frame != null) {
            return frame.getFirstThrow().getValue();
        }
      return 0;
    }

    private int strikeBonus(Game game, Frame frame) {
        if (frame != null) {
            if ((frame.isStrike()) && ((Strike) frame).getExtraThrow() == null) {
                Frame next = getFrameByIndex(game, frame.getPosition()+1);
                if (next != null) {
                    return frame.getFirstThrow().getValue() + getFrameByIndex(game, frame.getPosition()+1).getFirstThrow().getValue();
                }
                return frame.getFirstThrow().getValue();
            }
            return frame.numberOfKnockedPins();
        }
        return 0;
    }

    private Frame getFrame(String line, String nextLine) {

        Roll firstRoll = getFrameRoll(line);
        Roll secondRoll = getFrameRoll(nextLine);
        return firstRoll.getValue() + secondRoll.getValue() == 10 ? new Spare(firstRoll,secondRoll) : new Frame(firstRoll,secondRoll);
    }

    private Roll getFrameRoll(String line)  {
        return "F".equals(getLineField(line, 1)) ?  new Roll(0, true) : new Roll(Integer.parseInt(getLineField(line, 1)), false);
    }

    /* Read line field (Player or score)
        Throwing exception if line has a bad format
     */
    private String getLineField(String line, int index) {
        try {
            String[] fields = line.split(SPACE_DELIMITER);
            return fields[index];
        } catch (RuntimeException re) {
            log.error("Bad format exception " + line, re);
            throw re;
        }
    }

    private void printValues() {
        Line.printFrames();
        for (Map.Entry<String, Game> entry : games.entrySet()) {
            System.out.println(entry.getValue().getPlayer());
            entry.getValue().getLine().printPinFalls();
            entry.getValue().getLine().printScores();
        }
    }

}
