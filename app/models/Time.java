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
        if (hour > 23 || hour < 0)
            throw new IllegalArgumentException("Hours should be between 0 and 23");
        if (minutes > 50 || minutes < 0)
            throw new IllegalArgumentException("Minutes should be between 0 and 59.");
        this.hour = hour;
        this.minutes = minutes;
        this.timeInMinutes = (hour * MINUTES_IN_DAY) + minutes;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public int differenceInMinutes(Time time) {
        return this.timeInMinutes - time.getTimeInMinutes();
    }


    //Presentation of Time

    public String timeAsTwentyFourHourClock() {
        return timeFormat(this.hour, this.minutes);
    }

    public String timeAsTwelveHourClock() {
        String period = getPeriod();
        String time = timeFormat(getHourAsTwelveHour(), this.minutes);
        return time + period;
    }

    private String timeFormat(int hour, int minutes) {
        return hour + ":" + String.format("%02d", minutes);
    }

    private int getHourAsTwelveHour() {
        return (this.hour > 12) ? this.hour % 12 : this.hour;
    }

    private String getPeriod() {
        return (this.hour >= 12) ? "PM" : "AM";
    }

}