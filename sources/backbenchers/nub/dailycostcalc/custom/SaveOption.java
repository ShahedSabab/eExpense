package backbencers.nub.dailycostcalc.custom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.support.p003v7.app.AlertDialog;
import android.support.p003v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.activities.MainActivity;
import backbencers.nub.dailycostcalc.constant.Constant;
import backbencers.nub.dailycostcalc.model.Debit;
import backbencers.nub.dailycostcalc.objects.ObjectParser;
import com.google.gson.Gson;

public class SaveOption {
    private Activity activity;
    /* access modifiers changed from: private */
    public String category;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public Debit debit = null;
    private String text;

    public SaveOption(Activity activity2, String category2, String text2) {
        this.activity = activity2;
        this.category = category2;
        this.context = activity2.getApplicationContext();
        this.text = text2;
    }

    public void showDetailData() {
        Builder alertDialog = new Builder(this.activity);
        TextView title = new TextView(this.context);
        title.setText("Save data");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(17);
        title.setBackgroundColor(this.context.getResources().getColor(C0374R.color.colorPrimary));
        title.setTextSize(25.0f);
        alertDialog.setCustomTitle(title);
        View convertView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(C0374R.layout.custom_save_option, null);
        final EditText etNotiText = (EditText) convertView.findViewById(C0374R.C0376id.etCusNotiText);
        Button btnSave = (Button) convertView.findViewById(C0374R.C0376id.btnCusNotiSave);
        Button btnCancel = (Button) convertView.findViewById(C0374R.C0376id.btnCusNotiCancel);
        etNotiText.setText(this.text);
        alertDialog.setView(convertView);
        alertDialog.setCancelable(false);
        final AlertDialog dialog = alertDialog.create();
        final Editor editor = this.context.getApplicationContext().getSharedPreferences(Constant.PREF_DEBIT_NAME, 0).edit();
        btnSave.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (etNotiText.getText().toString().trim().length() > 0) {
                    SaveOption.this.debit = new ObjectParser(SaveOption.this.category, etNotiText.getText().toString().trim()).parse();
                    editor.putBoolean(Constant.PREF_IS_DATA_AVAILABLE, true);
                    editor.putString(Constant.PREF_DEBIT_OBJECT, new Gson().toJson((Object) SaveOption.this.debit));
                    editor.commit();
                    dialog.dismiss();
                    SaveOption.this.moveToMain();
                    return;
                }
                Toast.makeText(SaveOption.this.context, "Write some text to save", 0).show();
            }
        });
        btnCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* access modifiers changed from: private */
    public void moveToMain() {
        this.activity.startActivity(new Intent(this.activity, MainActivity.class));
        this.activity.finish();
    }
}
