package frame;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Frame {

    protected Roll firstThrow;
    protected Roll secondThrow;
    protected int position;

    public Frame(Roll firstThrow, Roll secondThrow) {
        this.firstThrow = firstThrow;
        this.secondThrow = secondThrow;
    }

    public boolean isSpare() {
        return false;
    }

    public boolean isStrike() {
        return false;
    }

    public int numberOfKnockedPins() {
        return firstThrow.getValue() + secondThrow.getValue();
    }

    @Override
    public String toString() {
        return firstThrow.toString() + " " + secondThrow.toString();
    }

}
