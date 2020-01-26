package backbencers.nub.dailycostcalc.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.view.InputDeviceCompat;
import android.support.p003v7.widget.DefaultItemAnimator;
import android.support.p003v7.widget.LinearLayoutManager;
import android.support.p003v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.adapter.ColorAdapter;
import backbencers.nub.dailycostcalc.constant.Constant;
import backbencers.nub.dailycostcalc.database.ExpenseDataSource;
import backbencers.nub.dailycostcalc.model.MyColor;
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
import java.util.HashMap;
import java.util.List;

public class MonthlyBalanceFragment extends Fragment {
    private List<Integer> colorList = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(-7829368), Integer.valueOf(-16776961), Integer.valueOf(SupportMenu.CATEGORY_MASK), Integer.valueOf(-16711936), Integer.valueOf(-16711681), Integer.valueOf(InputDeviceCompat.SOURCE_ANY), Integer.valueOf(-65281), Integer.valueOf(-12303292), Integer.valueOf(-3355444), Integer.valueOf(-16711681), Integer.valueOf(SupportMenu.CATEGORY_MASK), Integer.valueOf(-16711936)}));
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public ExpenseDataSource expenseDataSource;
    private HashMap<String, Integer> monthColors;
    /* access modifiers changed from: private */
    public List<String> monthListCredit;
    /* access modifiers changed from: private */
    public List<String> monthListDebit;
    private List<String> months = new ArrayList(Arrays.asList(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));

    /* renamed from: mx */
    private int f43mx;
    private PieChart pieChartBalanceMCredit;
    private PieChart pieChartBalanceMDebit;
    private RecyclerView rvMonthlyColors;
    /* access modifiers changed from: private */
    public String[] xData;
    private List<Double> xDataList;
    /* access modifiers changed from: private */
    public float[] yData;
    private List<Double> yDataList;

    private class CreditData {
        private CreditData() {
        }

        public void getXData() {
            MonthlyBalanceFragment.this.xData = new String[MonthlyBalanceFragment.this.monthListCredit.size()];
            for (int i = 0; i < MonthlyBalanceFragment.this.monthListCredit.size(); i++) {
                MonthlyBalanceFragment.this.xData[i] = (String) MonthlyBalanceFragment.this.monthListCredit.get(i);
            }
        }

        public void getYData() {
            MonthlyBalanceFragment.this.yData = new float[MonthlyBalanceFragment.this.monthListCredit.size()];
            String year = new SimpleDateFormat("yyyy").format(new Date());
            for (int i = 0; i < MonthlyBalanceFragment.this.monthListCredit.size(); i++) {
                MonthlyBalanceFragment.this.yData[i] = getMonthlyCredit((String) MonthlyBalanceFragment.this.monthListCredit.get(i), year);
            }
        }

        private float getMonthlyCredit(String month, String year) {
            return (float) MonthlyBalanceFragment.this.expenseDataSource.getCreditAmounts(month, year);
        }
    }

    private class DebitData {
        private DebitData() {
        }

        public void getXData() {
            MonthlyBalanceFragment.this.xData = new String[MonthlyBalanceFragment.this.monthListDebit.size()];
            for (int i = 0; i < MonthlyBalanceFragment.this.monthListDebit.size(); i++) {
                MonthlyBalanceFragment.this.xData[i] = (String) MonthlyBalanceFragment.this.monthListDebit.get(i);
            }
        }

        public void getYData() {
            MonthlyBalanceFragment.this.yData = new float[MonthlyBalanceFragment.this.monthListDebit.size()];
            String year = new SimpleDateFormat("yyyy").format(new Date());
            for (int i = 0; i < MonthlyBalanceFragment.this.monthListDebit.size(); i++) {
                MonthlyBalanceFragment.this.yData[i] = getMonthlyDebit((String) MonthlyBalanceFragment.this.monthListDebit.get(i), year);
            }
        }

        private float getMonthlyDebit(String month, String year) {
            return (float) MonthlyBalanceFragment.this.expenseDataSource.getTotalDebitAmount(month, year);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0374R.layout.fragment_monthly_balance, container, false);
        this.context = getActivity().getApplicationContext();
        this.expenseDataSource = new ExpenseDataSource(this.context);
        inits(view);
        return view;
    }

    private void inits(View view) {
        this.pieChartBalanceMCredit = (PieChart) view.findViewById(C0374R.C0376id.pie_chart_balance_m_credit);
        this.pieChartBalanceMDebit = (PieChart) view.findViewById(C0374R.C0376id.pie_chart_balance_m_debit);
        this.rvMonthlyColors = (RecyclerView) view.findViewById(C0374R.C0376id.rv_monthly_colors);
        this.rvMonthlyColors.setLayoutManager(new LinearLayoutManager(this.context, 0, false));
        this.rvMonthlyColors.setItemAnimator(new DefaultItemAnimator());
        this.xDataList = new ArrayList();
        this.yDataList = new ArrayList();
        getMonthList();
        setPieChart();
        this.pieChartBalanceMCredit.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            public void onValueSelected(Entry e, Highlight h) {
                int index = (int) Float.parseFloat(h.toString().split(",")[1].trim().split(" ")[1].trim());
                Toast.makeText(MonthlyBalanceFragment.this.context, MonthlyBalanceFragment.this.xData[index] + "\nCost: ৳" + MonthlyBalanceFragment.this.yData[index], 1).show();
            }

            public void onNothingSelected() {
            }
        });
        this.pieChartBalanceMDebit.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            public void onValueSelected(Entry e, Highlight h) {
                int index = (int) Float.parseFloat(h.toString().split(",")[1].trim().split(" ")[1].trim());
                Toast.makeText(MonthlyBalanceFragment.this.context, MonthlyBalanceFragment.this.xData[index] + "\nCost: ৳" + MonthlyBalanceFragment.this.yData[index], 1).show();
            }

            public void onNothingSelected() {
            }
        });
    }

    private void getMonthList() {
        String year = new SimpleDateFormat("yyyy").format(new Date());
        this.monthListDebit = this.expenseDataSource.getMonthListForDebit(year);
        this.monthListCredit = this.expenseDataSource.getMonthListForCredit(year);
        if (this.monthListDebit.size() > this.monthListCredit.size()) {
            setColorAdapter(this.monthListDebit);
        } else {
            setColorAdapter(this.monthListCredit);
        }
    }

    private void setPieChart() {
        this.pieChartBalanceMCredit.setDescription("");
        this.pieChartBalanceMCredit.setRotationEnabled(true);
        this.pieChartBalanceMCredit.setHoleRadius(18.0f);
        this.pieChartBalanceMCredit.setTransparentCircleAlpha(0);
        this.pieChartBalanceMCredit.setCenterText(Constant.TABLE_CREDIT);
        this.pieChartBalanceMCredit.setCenterTextSize(10.0f);
        CreditData creditData = new CreditData();
        creditData.getXData();
        creditData.getYData();
        addDataSetForCredit();
        getColors();
        this.pieChartBalanceMDebit.setDescription("");
        this.pieChartBalanceMDebit.setRotationEnabled(true);
        this.pieChartBalanceMDebit.setHoleRadius(18.0f);
        this.pieChartBalanceMDebit.setTransparentCircleAlpha(0);
        this.pieChartBalanceMDebit.setCenterText(Constant.TABLE_DEBIT);
        this.pieChartBalanceMDebit.setCenterTextSize(10.0f);
        DebitData debitData = new DebitData();
        debitData.getXData();
        debitData.getYData();
        addDataSetForDebit();
        getColors();
    }

    private void setColorAdapter(List<String> monthList) {
        List<MyColor> myColors = new ArrayList<>();
        for (int i = 0; i < monthList.size(); i++) {
            myColors.add(new MyColor(((Integer) this.colorList.get(i)).intValue(), (String) monthList.get(i)));
        }
        this.rvMonthlyColors.setAdapter(new ColorAdapter(myColors));
    }

    private List<Integer> getColors() {
        new ArrayList<>().clear();
        return this.colorList.subList(0, this.yData.length);
    }

    private void addDataSetForCredit() {
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
        this.pieChartBalanceMCredit.setData(new PieData(pieDataSet));
        this.pieChartBalanceMCredit.invalidate();
    }

    private void addDataSetForDebit() {
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
        this.pieChartBalanceMDebit.setData(new PieData(pieDataSet));
        this.pieChartBalanceMDebit.invalidate();
    }
}
