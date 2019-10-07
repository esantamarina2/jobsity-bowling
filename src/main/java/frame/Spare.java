package frame;

import lombok.Data;

@Data
public class Spare extends Frame {

    private static final String SPARE_SYMBOL = "/";

    public Spare(Roll firstThrow, Roll secondThrow) {
        super(firstThrow, secondThrow);
    }

    @Override
    public boolean isSpare() {
        return true;
    }

    @Override
    public String toString() {
        return firstThrow + " " + SPARE_SYMBOL;
    }
}
