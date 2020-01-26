package backbencers.nub.dailycostcalc.fragments;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.view.InputDeviceCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.constant.Constant;
import backbencers.nub.dailycostcalc.database.ExpenseDataSource;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class YearlyBalanceFragment extends Fragment {
    private List<Integer> colorList = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(-7829368), Integer.valueOf(-16776961), Integer.valueOf(SupportMenu.CATEGORY_MASK), Integer.valueOf(-16711936), Integer.valueOf(-16711681), Integer.valueOf(InputDeviceCompat.SOURCE_ANY), Integer.valueOf(-65281), Integer.valueOf(-12303292), Integer.valueOf(-3355444), Integer.valueOf(-16711681), Integer.valueOf(SupportMenu.CATEGORY_MASK), Integer.valueOf(-16711936)}));
    /* access modifiers changed from: private */
    public Context context;
    private String[] descriptoins = {"Yearly Cost(In ৳)", "Monthly Cost(In ৳)"};
    private ExpenseDataSource expenseDataSource;
    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private PieChart pie_chart_balance;
    private RadioGroup rg_balance_chart;
    private TextView tvYearlyCrebitColor;
    private TextView tvYearlyDebitColor;
    private View viewYearlyCredit;
    private View viewYearlyDebit;
    /* access modifiers changed from: private */
    public String[] xData;
    private List<Double> xDataList;
    /* access modifiers changed from: private */
    public float[] yData;
    private List<Double> yDataList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0374R.layout.fragment_yearly_balance, container, false);
        this.context = getActivity().getApplicationContext();
        this.expenseDataSource = new ExpenseDataSource(this.context);
        inits(view);
        return view;
    }

    private void inits(View view) {
        this.pie_chart_balance = (PieChart) view.findViewById(C0374R.C0376id.pie_chart_balance);
        this.tvYearlyCrebitColor = (TextView) view.findViewById(C0374R.C0376id.tvYearlyCrebitColor);
        this.tvYearlyDebitColor = (TextView) view.findViewById(C0374R.C0376id.tvYearlyDebitColor);
        this.viewYearlyCredit = view.findViewById(C0374R.C0376id.view_yearly_credit);
        this.viewYearlyDebit = view.findViewById(C0374R.C0376id.view_yearly_debit);
        GradientDrawable drawable2 = (GradientDrawable) this.viewYearlyCredit.getBackground();
        ((GradientDrawable) this.viewYearlyDebit.getBackground()).setColor(((Integer) this.colorList.get(0)).intValue());
        drawable2.setColor(((Integer) this.colorList.get(1)).intValue());
        this.xDataList = new ArrayList();
        this.yDataList = new ArrayList();
        setPieChart();
        this.pie_chart_balance.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            public void onValueSelected(Entry e, Highlight h) {
                int index = (int) Float.parseFloat(h.toString().split(",")[1].trim().split(" ")[1].trim());
                Toast.makeText(YearlyBalanceFragment.this.context, YearlyBalanceFragment.this.xData[index] + "\nCost: ৳" + YearlyBalanceFragment.this.yData[index], 1).show();
            }

            public void onNothingSelected() {
            }
        });
    }

    private void setPieChart() {
        this.pie_chart_balance.setDescription("");
        this.pie_chart_balance.setRotationEnabled(true);
        this.pie_chart_balance.setHoleRadius(25.0f);
        this.pie_chart_balance.setTransparentCircleAlpha(0);
        this.pie_chart_balance.setCenterText("Balance");
        this.pie_chart_balance.setCenterTextSize(10.0f);
        setyData();
        setxData();
        addDataSet();
    }

    private void setxData() {
        this.xData = new String[2];
        this.xData[0] = Constant.TABLE_DEBIT;
        this.xData[1] = Constant.TABLE_CREDIT;
    }

    private void setyData() {
        this.yData = new float[2];
        this.yData[0] = getDebitBalance();
        this.yData[1] = getCreditBalance();
    }

    private float getDebitBalance() {
        return (float) this.expenseDataSource.getTotalDebitAmount(new SimpleDateFormat("MMMM").format(new Date()), new SimpleDateFormat("yyyy").format(new Date()));
    }

    private float getCreditBalance() {
        return (float) this.expenseDataSource.getCreditAmounts(new SimpleDateFormat("MMMM").format(new Date()), new SimpleDateFormat("yyyy").format(new Date()));
    }

    private List<Integer> getColors() {
        new ArrayList<>().clear();
        return this.colorList.subList(0, this.yData.length);
    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        int i = 0;
        while (i < this.yData.length && i < this.xData.length) {
            if (((double) this.yData[i]) > 0.0d) {
                yEntrys.add(new PieEntry(this.yData[i], (Object) Integer.valueOf(i)));
                xEntrys.add(this.xData[i]);
            }
            i++;
        }
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Balance");
        pieDataSet.setSliceSpace(2.0f);
        pieDataSet.setValueTextSize(12.0f);
        pieDataSet.setValueTextColor(-1);
        pieDataSet.setColors(getColors());
        this.pie_chart_balance.setData(new PieData(pieDataSet));
        this.pie_chart_balance.invalidate();
    }
}
