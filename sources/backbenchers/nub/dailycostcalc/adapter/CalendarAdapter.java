package backbencers.nub.dailycostcalc.adapter;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.constant.CalendarCollection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends BaseAdapter {
    public static List<String> day_string;
    int calMaxP;
    private Context context;
    String curentDateString = this.f42df.format(this.selectedDate.getTime());
    public ArrayList<CalendarCollection> date_collection_arr;

    /* renamed from: df */
    DateFormat f42df = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    int firstDay;
    private ArrayList<String> items = new ArrayList<>();
    String itemvalue;
    int lastWeekDay;
    int leftDays;
    int maxP;
    int maxWeeknumber;
    int mnthlength;
    private Calendar month;
    public GregorianCalendar pmonth;
    public GregorianCalendar pmonthmaxset;
    private View previousView;
    private GregorianCalendar selectedDate;

    public CalendarAdapter(Context context2, GregorianCalendar monthCalendar, ArrayList<CalendarCollection> date_collection_arr2) {
        this.date_collection_arr = date_collection_arr2;
        day_string = new ArrayList();
        Locale.setDefault(Locale.US);
        this.month = monthCalendar;
        this.selectedDate = (GregorianCalendar) monthCalendar.clone();
        this.context = context2;
        this.month.set(5, 1);
        refreshDays();
    }

    public void setItems(ArrayList<String> items2) {
        for (int i = 0; i != items2.size(); i++) {
            if (((String) items2.get(i)).length() == 1) {
                items2.set(i, "0" + ((String) items2.get(i)));
            }
        }
        this.items = items2;
    }

    public int getCount() {
        return day_string.size();
    }

    public Object getItem(int position) {
        return day_string.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            v = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(C0374R.layout.cal_item, null);
        }
        TextView dayView = (TextView) v.findViewById(C0374R.C0376id.date);
        String gridvalue = ((String) day_string.get(position)).split("-")[0].replaceFirst("^0*", "");
        if (Integer.parseInt(gridvalue) > 1 && position < this.firstDay) {
            dayView.setTextColor(-7829368);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else if (Integer.parseInt(gridvalue) >= 7 || position <= 28) {
            dayView.setTextColor(-1);
        } else {
            dayView.setTextColor(-7829368);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        }
        if (((String) day_string.get(position)).equals(this.curentDateString)) {
            v.setBackgroundColor(-7829368);
        } else {
            v.setBackgroundColor(Color.parseColor("#343434"));
        }
        dayView.setText(gridvalue);
        String date = (String) day_string.get(position);
        if (date.length() == 1) {
            String date2 = "0" + date;
        }
        String monthStr = "" + (this.month.get(2) + 1);
        if (monthStr.length() == 1) {
            String monthStr2 = "0" + monthStr;
        }
        setEventView(v, position, dayView);
        return v;
    }

    public View setSelected(View view, int pos) {
        if (this.previousView != null) {
            this.previousView.setBackgroundColor(Color.parseColor("#343434"));
        }
        view.setBackgroundColor(-16711681);
        if (day_string.size() > pos) {
            if (((String) day_string.get(pos)).equals(this.curentDateString)) {
                view.setBackgroundColor(-7829368);
            } else {
                this.previousView = view;
            }
        }
        return view;
    }

    public void refreshDays() {
        this.items.clear();
        day_string.clear();
        Locale.setDefault(Locale.US);
        this.pmonth = (GregorianCalendar) this.month.clone();
        this.firstDay = this.month.get(7);
        this.maxWeeknumber = this.month.getActualMaximum(4);
        this.mnthlength = this.maxWeeknumber * 7;
        this.maxP = getMaxP();
        this.calMaxP = this.maxP - (this.firstDay - 1);
        this.pmonthmaxset = (GregorianCalendar) this.pmonth.clone();
        this.pmonthmaxset.set(5, this.calMaxP + 1);
        for (int n = 0; n < this.mnthlength; n++) {
            this.itemvalue = this.f42df.format(this.pmonthmaxset.getTime());
            this.pmonthmaxset.add(5, 1);
            day_string.add(this.itemvalue);
        }
    }

    private int getMaxP() {
        if (this.month.get(2) == this.month.getActualMinimum(2)) {
            this.pmonth.set(this.month.get(1) - 1, this.month.getActualMaximum(2), 1);
        } else {
            this.pmonth.set(2, this.month.get(2) - 1);
        }
        return this.pmonth.getActualMaximum(5);
    }

    public void setEventView(View v, int pos, TextView txt) {
        int len = CalendarCollection.date_collection_arr.size();
        for (int i = 0; i < len; i++) {
            String date = ((CalendarCollection) CalendarCollection.date_collection_arr.get(i)).date;
            if (day_string.size() > pos && ((String) day_string.get(pos)).equals(date)) {
                v.setBackgroundColor(Color.parseColor("#343434"));
                v.setBackgroundResource(C0374R.C0375drawable.rounded_calender_item);
                txt.setTextColor(-1);
            }
        }
    }

    public void getPositionList(String date, final Activity act) {
        int len = CalendarCollection.date_collection_arr.size();
        for (int i = 0; i < len; i++) {
            CalendarCollection cal_collection = (CalendarCollection) CalendarCollection.date_collection_arr.get(i);
            String event_date = cal_collection.date;
            String event_message = cal_collection.event_message;
            if (date.equals(event_date)) {
                Toast.makeText(this.context, "You have event on this date: " + event_date, 1).show();
                new Builder(this.context).setIcon(17301543).setTitle("Date: " + event_date).setMessage("Event: " + event_message).setPositiveButton("OK", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        act.finish();
                    }
                }).show();
                return;
            }
        }
    }
}
