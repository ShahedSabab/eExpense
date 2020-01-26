package backbencers.nub.dailycostcalc.constant;

import java.util.ArrayList;

public class CalendarCollection {
    public static ArrayList<CalendarCollection> date_collection_arr;
    public String date = "";
    public String event_message = "";

    public CalendarCollection(String date2, String event_message2) {
        this.date = date2;
        this.event_message = event_message2;
    }
}
