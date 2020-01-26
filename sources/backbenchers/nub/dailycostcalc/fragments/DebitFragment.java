package backbencers.nub.dailycostcalc.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.activities.DebitEditorActivity;
import backbencers.nub.dailycostcalc.adapter.CreditListAdapter;
import backbencers.nub.dailycostcalc.adapter.DebitListAdapter;
import backbencers.nub.dailycostcalc.constant.Constant;
import backbencers.nub.dailycostcalc.database.ExpenseDataSource;
import backbencers.nub.dailycostcalc.model.Debit;
import java.util.ArrayList;
import java.util.List;

public class DebitFragment extends Fragment {
    private static final int OPEN_DEBIT_EDITOR_ACTIVITY = 203;
    /* access modifiers changed from: private */
    public static final String TAG = DebitFragment.class.getSimpleName();
    private List<String> categoriesString = new ArrayList();
    private TextView debitEmptyView;
    /* access modifiers changed from: private */
    public List<Debit> debitList;
    private DebitListAdapter debitListAdapter;
    private ListView debitListView;
    private ExpenseDataSource expenseDataSource;
    /* access modifiers changed from: private */
    public boolean sentToDebitEditor = false;
    private TextView tvFooterDebitAmount;
    private View view;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        getActivity().setTitle(Constant.TABLE_DEBIT);
        View inflate = inflater.inflate(C0374R.layout.fragment_debit, container, false);
        this.view = inflate;
        return inflate;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.view != null) {
            this.debitListView = (ListView) this.view.findViewById(C0374R.C0376id.lv_debits);
            this.debitEmptyView = (TextView) this.view.findViewById(C0374R.C0376id.empty_view_debit);
            this.tvFooterDebitAmount = (TextView) this.view.findViewById(C0374R.C0376id.text_view_amount_debit);
            this.debitListView.setEmptyView(this.debitEmptyView);
            this.expenseDataSource = new ExpenseDataSource(getContext());
            ((FloatingActionButton) this.view.findViewById(C0374R.C0376id.fab_debit)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    DebitFragment.this.sentToDebitEditor = true;
                    Intent intent = new Intent(DebitFragment.this.getContext(), DebitEditorActivity.class);
                    intent.putExtra(Constant.ACTIVITY_TYPE, Constant.ACTIVITY_TYPE_ADD);
                    DebitFragment.this.startActivityForResult(intent, 203);
                }
            });
            this.debitListView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Log.e("CrditFragment", "----------------------position: " + position + " id: " + id);
                    DebitFragment.this.sentToDebitEditor = true;
                    Intent intent = new Intent(DebitFragment.this.getContext(), DebitEditorActivity.class);
                    intent.putExtra(Constant.ACTIVITY_TYPE, Constant.ACTIVITY_TYPE_EDIT);
                    intent.putExtra(Constant.DEBIT_ITEM_ID, ((Debit) DebitFragment.this.debitList.get(position)).getDebitId());
                    Log.e(DebitFragment.TAG, "Clicked item id: " + id);
                    DebitFragment.this.startActivityForResult(intent, 203);
                }
            });
            loadDebits();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 203) {
            loadDebits();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.sentToDebitEditor) {
            this.sentToDebitEditor = false;
            loadDebits();
        }
    }

    private void loadDebits() {
        this.debitListView.setAdapter(new CreditListAdapter(getContext(), new ArrayList()));
        try {
            this.debitList = this.expenseDataSource.getAllDebits();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.debitList.size() == 0) {
            this.debitEmptyView.setVisibility(0);
            return;
        }
        Log.e(TAG, "debitList size: " + this.debitList.size());
        this.debitListAdapter = new DebitListAdapter(getContext(), this.debitList);
        this.debitListView.setAdapter(this.debitListAdapter);
        this.tvFooterDebitAmount.setText("" + this.expenseDataSource.getTotalDebitAmount());
    }
}
