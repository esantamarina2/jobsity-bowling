package frame;

import lombok.Data;

@Data
public class Strike extends Frame {

    private static final String STRIKE_SYMBOL= "X";

    protected Roll extraThrow;

    public Strike(Roll firstThrow, Roll secondThrow) {
        super(firstThrow, secondThrow);
    }

    @Override
    public boolean isStrike() {
        return true;
    }

    @Override
    public String toString() {
        return STRIKE_SYMBOL + getStrikeTranslation(secondThrow) + getStrikeTranslation(extraThrow);
    }

    public String getStrikeTranslation(Roll roll) {
        if ((roll != null) && roll.getValue() != 0) {
           return roll.getValue() == 10 ? " " + STRIKE_SYMBOL : " " + String.valueOf(roll.getValue());
        }
        return "";
    }
}
