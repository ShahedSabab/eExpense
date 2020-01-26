package backbencers.nub.dailycostcalc.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.adapter.CalendarAdapter;
import backbencers.nub.dailycostcalc.adapter.DebitListAdapter;
import backbencers.nub.dailycostcalc.constant.CalendarCollection;
import backbencers.nub.dailycostcalc.database.ExpenseDataSource;
import backbencers.nub.dailycostcalc.model.Debit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class HistoryFragment extends Fragment {
    private CalendarAdapter cal_adapter;
    public GregorianCalendar cal_month;
    public GregorianCalendar cal_month_copy;
    private Context context;
    private TextView empty_view_his_debit;
    private ExpenseDataSource expenseDataSource;
    private ListView lv_daily_debit;
    private TextView tv_month;
    /* access modifiers changed from: private */
    public TextView tv_selected_date;
    /* access modifiers changed from: private */
    public TextView tv_selected_date_amount;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Balance");
        View view = inflater.inflate(C0374R.layout.fragment_detail, container, false);
        this.context = getActivity().getApplicationContext();
        this.expenseDataSource = new ExpenseDataSource(getContext());
        this.cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        this.cal_month_copy = (GregorianCalendar) this.cal_month.clone();
        this.cal_adapter = new CalendarAdapter(this.context, this.cal_month, CalendarCollection.date_collection_arr);
        inits(view);
        return view;
    }

    private void inits(View view) {
        this.tv_month = (TextView) view.findViewById(C0374R.C0376id.tv_month);
        this.tv_month.setText(DateFormat.format("MMMM yyyy", this.cal_month));
        this.tv_selected_date = (TextView) view.findViewById(C0374R.C0376id.tv_selected_date);
        this.tv_selected_date_amount = (TextView) view.findViewById(C0374R.C0376id.tv_selected_date_amount);
        this.empty_view_his_debit = (TextView) view.findViewById(C0374R.C0376id.empty_view_his_debit);
        this.lv_daily_debit = (ListView) view.findViewById(C0374R.C0376id.lv_daily_debit);
        String curentDateString = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.tv_selected_date.setText(curentDateString);
        this.tv_selected_date_amount.setText(getAmount(curentDateString));
        this.lv_daily_debit.setEmptyView(this.empty_view_his_debit);
        setDailyDebitList(this.tv_selected_date.getText().toString());
        ((ImageButton) view.findViewById(C0374R.C0376id.ib_prev)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                HistoryFragment.this.setPreviousMonth();
                HistoryFragment.this.refreshCalendar();
            }
        });
        ((ImageButton) view.findViewById(C0374R.C0376id.Ib_next)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                HistoryFragment.this.setNextMonth();
                HistoryFragment.this.refreshCalendar();
            }
        });
        GridView gridview = (GridView) view.findViewById(C0374R.C0376id.gv_calendar);
        gridview.setAdapter(this.cal_adapter);
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ((CalendarAdapter) parent.getAdapter()).setSelected(v, position);
                String selectedGridDate = (String) CalendarAdapter.day_string.get(position);
                HistoryFragment.this.tv_selected_date.setText(selectedGridDate);
                HistoryFragment.this.tv_selected_date_amount.setText(HistoryFragment.this.getAmount(selectedGridDate));
                HistoryFragment.this.setDailyDebitList(HistoryFragment.this.tv_selected_date.getText().toString());
                int gridvalue = Integer.parseInt(selectedGridDate.split("-")[2].replaceFirst("^0*", ""));
                if (gridvalue > 10 && position < 8) {
                    HistoryFragment.this.setPreviousMonth();
                    HistoryFragment.this.refreshCalendar();
                } else if (gridvalue < 7 && position > 28) {
                    HistoryFragment.this.setNextMonth();
                    HistoryFragment.this.refreshCalendar();
                }
                ((CalendarAdapter) parent.getAdapter()).setSelected(v, position);
                ((CalendarAdapter) parent.getAdapter()).getPositionList(selectedGridDate, HistoryFragment.this.getActivity());
            }
        });
    }

    /* access modifiers changed from: private */
    public void setDailyDebitList(String date) {
        List<Debit> debits = this.expenseDataSource.getDebitsInThisDate(date);
        if (debits == null || debits.size() <= 0) {
            this.lv_daily_debit.setAdapter(null);
            return;
        }
        DebitListAdapter adapter = new DebitListAdapter(this.context, debits);
        this.lv_daily_debit.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public String getAmount(String date) {
        return Double.toString(this.expenseDataSource.getDebitAmountInThisDate(date));
    }

    /* access modifiers changed from: protected */
    public void setNextMonth() {
        if (this.cal_month.get(2) == this.cal_month.getActualMaximum(2)) {
            this.cal_month.set(this.cal_month.get(1) + 1, this.cal_month.getActualMinimum(2), 1);
        } else {
            this.cal_month.set(2, this.cal_month.get(2) + 1);
        }
    }

    /* access modifiers changed from: protected */
    public void setPreviousMonth() {
        if (this.cal_month.get(2) == this.cal_month.getActualMinimum(2)) {
            this.cal_month.set(this.cal_month.get(1) - 1, this.cal_month.getActualMaximum(2), 1);
        } else {
            this.cal_month.set(2, this.cal_month.get(2) - 1);
        }
    }

    public void refreshCalendar() {
        this.cal_adapter.refreshDays();
        this.cal_adapter.notifyDataSetChanged();
        this.tv_month.setText(DateFormat.format("MMMM yyyy", this.cal_month));
    }
}
