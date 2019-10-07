package frame;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Roll {

    private int value;
    private boolean isFault;

    private static String FAULT_SYMBOL = "F";

    @Override
    public String toString() {
        if (isFault) {
            return FAULT_SYMBOL;
        }
        else {
           return String.valueOf(value);
        }
    }
}
