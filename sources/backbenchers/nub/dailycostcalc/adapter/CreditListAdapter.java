package backbencers.nub.dailycostcalc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.model.Credit;
import java.util.List;

public class CreditListAdapter extends ArrayAdapter<Credit> {
    public CreditListAdapter(@NonNull Context context, @NonNull List<Credit> credits) {
        super(context, 0, credits);
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(C0374R.layout.credit_list_item, parent, false);
        }
        Credit currentCredit = (Credit) getItem(position);
        ((TextView) listItemView.findViewById(C0374R.C0376id.tv_credit_category)).setText(currentCredit.getCreditCategory());
        ((TextView) listItemView.findViewById(C0374R.C0376id.tv_credit_date)).setText(currentCredit.getCreditDate());
        Log.e(CreditListAdapter.class.getSimpleName(), "Date: " + currentCredit.getCreditDate());
        ((TextView) listItemView.findViewById(C0374R.C0376id.tv_credit_amount)).setText("" + currentCredit.getCreditAmount());
        return listItemView;
    }
}
