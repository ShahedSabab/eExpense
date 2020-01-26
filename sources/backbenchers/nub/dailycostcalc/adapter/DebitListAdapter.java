package backbencers.nub.dailycostcalc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.model.Debit;
import java.util.List;

public class DebitListAdapter extends ArrayAdapter<Debit> {
    public DebitListAdapter(@NonNull Context context, @NonNull List<Debit> debits) {
        super(context, 0, debits);
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(C0374R.layout.debit_list_item, parent, false);
        }
        Debit currentDebit = (Debit) getItem(position);
        ((TextView) listItemView.findViewById(C0374R.C0376id.tv_debit_category)).setText(currentDebit.getDebitCategory());
        ((TextView) listItemView.findViewById(C0374R.C0376id.tv_debit_date)).setText(currentDebit.getDebitDate());
        ((TextView) listItemView.findViewById(C0374R.C0376id.tv_debit_amount)).setText("" + currentDebit.getDebitAmount());
        return listItemView;
    }
}
