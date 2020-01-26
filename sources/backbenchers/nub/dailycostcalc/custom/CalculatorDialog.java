package backbencers.nub.dailycostcalc.custom;

import android.app.Activity;
import android.content.Context;
import android.support.p003v7.app.AlertDialog;
import android.support.p003v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import backbencers.nub.dailycostcalc.C0374R;

public class CalculatorDialog implements OnClickListener {
    private Button btnDiv;
    private Button btnEight;
    private Button btnEqual;
    private Button btnFive;
    private Button btnFour;
    private Button btnMul;
    private Button btnNine;
    private Button btnOne;
    private Button btnPlus;
    private Button btnPoint;
    private Button btnSeven;
    private Button btnSix;
    private Button btnSub;
    private Button btnThree;
    private Button btnTwo;
    private Button btnZero;
    private Context context;
    private double fNum;
    private String number = "";
    private char operator = ' ';
    private double result;
    private double sNum;
    private TextView tvCalDisplay;

    public void showCalculator(Activity activity) {
        this.context = activity.getApplicationContext();
        Builder alertDialog = new Builder(activity);
        TextView title = new TextView(this.context);
        title.setText("Calculator");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(17);
        title.setTextSize(25.0f);
        alertDialog.setCustomTitle(title);
        View convertView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(C0374R.layout.layout_calculator, null);
        initViews(convertView);
        alertDialog.setView(convertView);
        alertDialog.setCancelable(true);
        AlertDialog dialog = alertDialog.create();
        setActions();
        dialog.show();
    }

    private void initViews(View view) {
        this.tvCalDisplay = (TextView) view.findViewById(C0374R.C0376id.tvCalDisplay);
        this.tvCalDisplay.setText("0");
        this.btnSeven = (Button) view.findViewById(C0374R.C0376id.btnSeven);
        this.btnEight = (Button) view.findViewById(C0374R.C0376id.btnEight);
        this.btnNine = (Button) view.findViewById(C0374R.C0376id.btnNine);
        this.btnDiv = (Button) view.findViewById(C0374R.C0376id.btnDiv);
        this.btnFour = (Button) view.findViewById(C0374R.C0376id.btnFour);
        this.btnFive = (Button) view.findViewById(C0374R.C0376id.btnFive);
        this.btnSix = (Button) view.findViewById(C0374R.C0376id.btnSix);
        this.btnMul = (Button) view.findViewById(C0374R.C0376id.btnMul);
        this.btnOne = (Button) view.findViewById(C0374R.C0376id.btnOne);
        this.btnTwo = (Button) view.findViewById(C0374R.C0376id.btnTwo);
        this.btnThree = (Button) view.findViewById(C0374R.C0376id.btnThree);
        this.btnSub = (Button) view.findViewById(C0374R.C0376id.btnSub);
        this.btnPoint = (Button) view.findViewById(C0374R.C0376id.btnPoint);
        this.btnZero = (Button) view.findViewById(C0374R.C0376id.btnZero);
        this.btnPlus = (Button) view.findViewById(C0374R.C0376id.btnPlus);
        this.btnEqual = (Button) view.findViewById(C0374R.C0376id.btnEqual);
    }

    private void setActions() {
        this.btnSeven.setOnClickListener(this);
        this.btnEight.setOnClickListener(this);
        this.btnNine.setOnClickListener(this);
        this.btnDiv.setOnClickListener(this);
        this.btnFour.setOnClickListener(this);
        this.btnFive.setOnClickListener(this);
        this.btnSix.setOnClickListener(this);
        this.btnMul.setOnClickListener(this);
        this.btnOne.setOnClickListener(this);
        this.btnTwo.setOnClickListener(this);
        this.btnThree.setOnClickListener(this);
        this.btnSub.setOnClickListener(this);
        this.btnPoint.setOnClickListener(this);
        this.btnZero.setOnClickListener(this);
        this.btnPlus.setOnClickListener(this);
        this.btnEqual.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case C0374R.C0376id.btnDiv /*2131624187*/:
            case C0374R.C0376id.btnMul /*2131624191*/:
            case C0374R.C0376id.btnSub /*2131624195*/:
            case C0374R.C0376id.btnPlus /*2131624198*/:
                if (this.tvCalDisplay.getText().toString().trim().length() > 0) {
                    this.operator = ((Button) v.findViewById(id)).getText().toString().toLowerCase().charAt(0);
                    this.fNum = Double.parseDouble(this.tvCalDisplay.getText().toString().trim());
                    this.number = "";
                    return;
                }
                return;
            case C0374R.C0376id.btnEqual /*2131624199*/:
                this.sNum = Double.parseDouble(this.tvCalDisplay.getText().toString().trim());
                calculate();
                return;
            default:
                this.number += ((Button) v.findViewById(id)).getText().toString();
                this.tvCalDisplay.setText(this.number);
                return;
        }
    }

    private void calculate() {
        switch (this.operator) {
            case '+':
                this.result = this.fNum + this.sNum;
                break;
            case '-':
                this.result = this.fNum - this.sNum;
                break;
            case 'x':
                this.result = this.fNum * this.sNum;
                break;
            case 247:
                this.result = this.fNum / this.sNum;
                break;
        }
        this.tvCalDisplay.setText("" + this.result);
    }
}
