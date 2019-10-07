import frame.Roll;
import frame.Spare;
import frame.Strike;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class FrameTest {

    private static final int TEST_THROW = 5;
    private static final int STRIKE_VALUE = 10;


    /*
    Scenario: test frame sub-type creation (Spare)
   */
    @Test
    public void testSpareCreation() {
        Roll firstRoll = new Roll(TEST_THROW, false);
        Spare spare = new Spare(firstRoll, firstRoll);
        assertThat(spare.isSpare(), is(equalTo(true)));
        assertThat(spare.isStrike(), is(equalTo(false)));
        assertThat(spare.numberOfKnockedPins(), is(equalTo(TEST_THROW + TEST_THROW)));
    }

    /*
    Scenario: test frame sub-type creation (Strike)
    */
    @Test
    public void testStrikeCreation() {
        Roll firstRoll = new Roll(STRIKE_VALUE, false);
        Strike strike = new Strike(firstRoll, null);
        assertThat(strike.isSpare(), is(equalTo(false)));
        assertThat(strike.isStrike(), is(equalTo(true)));
        assertThat(strike.numberOfKnockedPins(), is(equalTo(STRIKE_VALUE)));
    }

    /*
    Scenario: test strike translation - Used when the last frame is a strike
    */
    @Test
    public void testStrikeTranslation() {
        Strike strike = createStrikeWithExtraRoll();
        String expected = "X X 5";
        assertThat(strike.toString(), is(equalTo(expected)));
    }

    /*
     Scenario: test last strike value - Used when the last frame is a strike
    */
    @Test
    public void testLastStrikeValue() {
        Strike strike = createStrikeWithExtraRoll();
        assertThat(strike.numberOfKnockedPins(), is(equalTo(STRIKE_VALUE + STRIKE_VALUE)));
        assertThat(strike.getExtraThrow().getValue(), is(equalTo(TEST_THROW)));
    }

    private Strike createStrikeWithExtraRoll() {
        Roll firstRoll = new Roll(STRIKE_VALUE, false);
        Roll secondRoll = new Roll(STRIKE_VALUE, false);
        Roll extraRoll = new Roll(TEST_THROW, false);
        Strike strike = new Strike(firstRoll, secondRoll);
        strike.setExtraThrow(extraRoll);
        return strike;
    }
}
