package game;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {

    private String player;
    private Line line;

    public Game (String player) {
        this.player =  player;
        this.line = new Line();
    }
}
