import models.Time;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Cree on 21/04/2017.
 */
public class TimeTest {


    @Test
    public void createNewTimeIsNotNull() {
        Time time = createValidTime();
        Assert.assertNotNull(time);
    }

    @Test
    public void timeAsTwelveHourClockCorrectlyDisplaysTwelveHourClockValueOfTime() {
        Time time = createValidTime();
        Assert.assertTrue(time.timeAsTwelveHourClock().equals("1:30PM"));
    }

    @Test
    public void timeAsTwentyFourHourClockCorrectlyDisplaysTwentyFourHourClockValueOfTime() {
        Time time = createValidTime();
        Assert.assertTrue(time.timeAsTwentyFourHourClock().equals("13:30"));
    }


    //Private test helpers
    private int hour1 = 13;
    private int hour2 = 9;
    private int minutes = 30;

    private Time createValidTime() {
        return new Time(hour1, minutes);
    }

}
