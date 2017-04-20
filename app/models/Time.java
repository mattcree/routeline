package models;
/**
 * Created by Cree on 20/04/2017.
 */
public class Time {

    public static final int MINUTES_IN_DAY = 1440;
    public static final int MINUTES_IN_HOUR = 60;

    private int timeInMinutes;
    private int hour;
    private int minutes;

    public Time(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
        this.timeInMinutes = (hour * MINUTES_IN_DAY) + minutes;
    }

}