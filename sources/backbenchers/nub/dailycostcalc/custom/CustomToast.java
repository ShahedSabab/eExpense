package backbencers.nub.dailycostcalc.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import backbencers.nub.dailycostcalc.C0374R;

public class CustomToast {
    public void Show_Toast(Context context, View view, String error) {
        View layout = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0374R.layout.custom_toast, (ViewGroup) view.findViewById(C0374R.C0376id.toast_root));
        ((TextView) layout.findViewById(C0374R.C0376id.toast_error)).setText(error);
        Toast toast = new Toast(context);
        toast.setGravity(55, 0, 0);
        toast.setDuration(0);
        toast.setView(layout);
        toast.show();
    }
}
