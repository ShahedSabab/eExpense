package backbencers.nub.dailycostcalc.adapter;

import android.graphics.drawable.GradientDrawable;
import android.support.p003v7.widget.RecyclerView.Adapter;
import android.support.p003v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.model.MyColor;
import java.util.List;

public class ColorAdapter extends Adapter<MyViewHolder> {
    private List<MyColor> colorList;

    public class MyViewHolder extends ViewHolder {
        public TextView tvMonthlyText;
        public View viewMonthlyColor;

        public MyViewHolder(View view) {
            super(view);
            this.tvMonthlyText = (TextView) view.findViewById(C0374R.C0376id.tv_monthly_text);
            this.viewMonthlyColor = view.findViewById(C0374R.C0376id.view_monthly_color);
        }
    }

    public ColorAdapter(List<MyColor> colorList2) {
        this.colorList = colorList2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0374R.layout.row_monthly_colors, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyColor myColor = (MyColor) this.colorList.get(position);
        holder.tvMonthlyText.setText(myColor.getName());
        ((GradientDrawable) holder.viewMonthlyColor.getBackground()).setColor(myColor.getColor());
    }

    public int getItemCount() {
        return this.colorList.size();
    }
}
