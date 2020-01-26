package backbencers.nub.dailycostcalc.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.view.InputDeviceCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import backbencers.nub.dailycostcalc.C0374R;
import com.github.mikephil.charting.charts.PieChart;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BalanceFragment extends Fragment {
    private List<Integer> colorList = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(-7829368), Integer.valueOf(-16776961), Integer.valueOf(SupportMenu.CATEGORY_MASK), Integer.valueOf(-16711936), Integer.valueOf(-16711681), Integer.valueOf(InputDeviceCompat.SOURCE_ANY), Integer.valueOf(-65281), Integer.valueOf(-12303292), Integer.valueOf(-3355444), Integer.valueOf(-16711681), Integer.valueOf(SupportMenu.CATEGORY_MASK), Integer.valueOf(-16711936)}));
    private Context context;
    private String[] descriptoins = {"Yearly Cost(In ৳)", "Monthly Cost(In ৳)"};
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private PieChart pie_chart_balance;
    private RadioGroup rg_balance_chart;
    private RadioGroup rg_balance_chart_monthly;
    private String[] xData;
    private List<Double> xDataList;
    private float[] yData;
    private List<Double> yDataList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0374R.layout.fragment_balance, container, false);
        this.context = getActivity().getApplicationContext();
        getActivity().setTitle("Balance");
        inits(view);
        return view;
    }

    private void inits(View view) {
        this.rg_balance_chart = (RadioGroup) view.findViewById(C0374R.C0376id.rg_balance_chart);
        this.rg_balance_chart.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == C0374R.C0376id.rb_yearly) {
                    BalanceFragment.this.setYearlyFragment();
                } else if (checkedId == C0374R.C0376id.rb_monthly) {
                    BalanceFragment.this.setMonthlyFragment();
                }
            }
        });
        setYearlyFragment();
    }

    /* access modifiers changed from: private */
    public void setYearlyFragment() {
        Fragment fragment = new YearlyBalanceFragment();
        this.fragmentManager = getFragmentManager();
        this.fragmentTransaction = this.fragmentManager.beginTransaction();
        this.fragmentTransaction.replace(C0374R.C0376id.fragment_place, fragment);
        this.fragmentTransaction.commit();
    }

    /* access modifiers changed from: private */
    public void setMonthlyFragment() {
        Fragment fragment = new MonthlyBalanceFragment();
        this.fragmentManager = getFragmentManager();
        this.fragmentTransaction = this.fragmentManager.beginTransaction();
        this.fragmentTransaction.replace(C0374R.C0376id.fragment_place, fragment);
        this.fragmentTransaction.commit();
    }
}
