package backbencers.nub.dailycostcalc.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.app.Fragment;
import android.support.p003v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.activities.CreditEditorActivity;
import backbencers.nub.dailycostcalc.adapter.CreditListAdapter;
import backbencers.nub.dailycostcalc.constant.Constant;
import backbencers.nub.dailycostcalc.database.ExpenseDataSource;
import backbencers.nub.dailycostcalc.model.Category;
import backbencers.nub.dailycostcalc.model.Credit;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CreditFragment extends Fragment {
    private static final int OPEN_CREDIT_EDITOR_ACTIVITY = 103;
    private static final int PERMISSION_CALLBACK_CONSTANT = 101;
    private static final int REQUEST_PERMISSION_SETTING = 102;
    /* access modifiers changed from: private */
    public static final String TAG = CreditFragment.class.getSimpleName();
    private CreditListAdapter adapter;
    private List<String> categoriesString = new ArrayList();
    private TextView creditEmptyView;
    /* access modifiers changed from: private */
    public List<Credit> creditList;
    private ListView creditListView;
    private ExpenseDataSource expenseDataSource;
    private ProgressBar loadingCreditProgressBar;
    private SharedPreferences permissionStatus;
    /* access modifiers changed from: private */
    public boolean sentToCreditEditor = false;
    /* access modifiers changed from: private */
    public boolean sentToSettings = false;
    private TextView tvFooterCreditAmount;
    private View view;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        getActivity().setTitle(Constant.TABLE_CREDIT);
        View inflate = inflater.inflate(C0374R.layout.fragment_credit, container, false);
        this.view = inflate;
        return inflate;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity();
        this.permissionStatus = getActivity().getSharedPreferences("permissionStatus", 0);
        if (this.view != null) {
            this.creditListView = (ListView) this.view.findViewById(C0374R.C0376id.lv_credits);
            this.creditEmptyView = (TextView) this.view.findViewById(C0374R.C0376id.empty_view_credit);
            this.loadingCreditProgressBar = (ProgressBar) this.view.findViewById(C0374R.C0376id.pb_loading_credits);
            this.tvFooterCreditAmount = (TextView) this.view.findViewById(C0374R.C0376id.text_view_amount_credit);
            this.expenseDataSource = new ExpenseDataSource(getContext());
            ((FloatingActionButton) this.view.findViewById(C0374R.C0376id.fab_credit)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    CreditFragment.this.sentToCreditEditor = true;
                    Intent intent = new Intent(CreditFragment.this.getContext(), CreditEditorActivity.class);
                    intent.putExtra(Constant.ACTIVITY_TYPE, Constant.ACTIVITY_TYPE_ADD);
                    CreditFragment.this.startActivityForResult(intent, 103);
                }
            });
            this.creditListView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Log.e("CrditFragment", "----------------------position: " + position + " id: " + id);
                    CreditFragment.this.sentToCreditEditor = true;
                    Intent intent = new Intent(CreditFragment.this.getContext(), CreditEditorActivity.class);
                    intent.putExtra(Constant.ACTIVITY_TYPE, Constant.ACTIVITY_TYPE_EDIT);
                    intent.putExtra(Constant.CREDIT_ITEM_ID, ((Credit) CreditFragment.this.creditList.get(position)).getCreditId());
                    Log.e(CreditFragment.TAG, "Clicked item id: " + id);
                    CreditFragment.this.startActivityForResult(intent, 103);
                }
            });
            if (ActivityCompat.checkSelfPermission(getActivity(), "android.permission.READ_SMS") != 0) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), "android.permission.READ_SMS")) {
                    Builder builder = new Builder(getActivity());
                    builder.setTitle((CharSequence) "Need Permission");
                    builder.setMessage((CharSequence) "This app needs SMS permission.");
                    builder.setPositiveButton((CharSequence) "Grant", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            CreditFragment.this.requestPermissions(new String[]{"android.permission.READ_SMS"}, 101);
                        }
                    });
                    builder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else if (this.permissionStatus.getBoolean("android.permission.READ_SMS", false)) {
                    Builder builder2 = new Builder(getActivity());
                    builder2.setTitle((CharSequence) "Need Permission");
                    builder2.setMessage((CharSequence) "This app needs SMS permission.");
                    builder2.setPositiveButton((CharSequence) "Grant", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            CreditFragment.this.sentToSettings = true;
                            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent.setData(Uri.fromParts("package", CreditFragment.this.getActivity().getPackageName(), null));
                            CreditFragment.this.startActivityForResult(intent, 102);
                            Toast.makeText(CreditFragment.this.getActivity(), "Go to Permissions to Grant SMS", 0).show();
                        }
                    });
                    builder2.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder2.show();
                } else {
                    requestPermissions(new String[]{"android.permission.READ_SMS"}, 101);
                }
                Editor editor = this.permissionStatus.edit();
                editor.putBoolean("android.permission.READ_PHONE_STATE", true);
                editor.putBoolean("android.permission.READ_SMS", true);
                editor.commit();
                return;
            }
            loadCredits();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            boolean allgranted = false;
            int i = 0;
            while (true) {
                if (i < grantResults.length) {
                    if (grantResults[i] != 0) {
                        allgranted = false;
                        break;
                    } else {
                        allgranted = true;
                        i++;
                    }
                } else {
                    break;
                }
            }
            if (allgranted) {
                loadCredits();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), "android.permission.READ_SMS")) {
                Builder builder = new Builder(getActivity());
                builder.setTitle((CharSequence) "Need SMS Permission");
                builder.setMessage((CharSequence) "This app needs SMS permission.");
                builder.setPositiveButton((CharSequence) "Grant", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        CreditFragment.this.requestPermissions(new String[]{"android.permission.READ_SMS"}, 101);
                    }
                });
                builder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                Toast.makeText(getActivity(), "Unable to get Permission", 0).show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102) {
            if (ActivityCompat.checkSelfPermission(getActivity(), "android.permission.READ_SMS") == 0) {
                loadCredits();
            }
        } else if (requestCode == 103) {
            loadCredits();
        }
    }

    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        if (this.sentToSettings && ActivityCompat.checkSelfPermission(getActivity(), "android.permission.READ_SMS") == 0) {
            loadCredits();
        }
        if (this.sentToCreditEditor) {
            this.sentToCreditEditor = false;
            loadCredits();
        }
    }

    private void getCategoriesFromDatabase() {
        ArrayList<Category> categories = this.expenseDataSource.getAllCategories();
        for (int i = 0; i < categories.size(); i++) {
            this.categoriesString.add(((Category) categories.get(i)).getCategoryName());
        }
    }

    private boolean isCategoryExisted(String category) {
        for (String s : this.categoriesString) {
            if (s.equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    private void showCreditDetailInDialog(int position) {
        Builder builder;
        if (VERSION.SDK_INT >= 21) {
            builder = new Builder(getContext(), 16974374);
        } else {
            builder = new Builder(getContext());
        }
        Credit credit = (Credit) this.creditListView.getItemAtPosition(position);
        builder.setTitle((CharSequence) credit.getCreditDate()).setMessage((CharSequence) credit.getCreditDescription()).setPositiveButton(17039379, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }

    private void loadCredits() {
        this.loadingCreditProgressBar.setVisibility(0);
        this.creditListView.setAdapter(new CreditListAdapter(getContext(), new ArrayList()));
        Credit credit = new Credit();
        credit.setCreditCategory("Bank");
        Cursor creditCursor = getActivity().getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (creditCursor.moveToFirst()) {
            do {
                String str = "";
                for (int idx = 0; idx < creditCursor.getColumnCount(); idx++) {
                    if (creditCursor.getColumnName(idx).equals("date")) {
                        Timestamp timestamp = new Timestamp(creditCursor.getLong(idx));
                        int intTimestamp = (int) (timestamp.getTime() % 100000000);
                        Log.e(TAG, "message longTimestamp: " + intTimestamp);
                        new Date(timestamp.getTime());
                        credit.setCreditDate(new SimpleDateFormat("dd-MM-yyyy").format(timestamp));
                        credit.setCreditTimestamp(intTimestamp);
                    }
                    if (creditCursor.getColumnName(idx).equals("body")) {
                        String messageBodyNormal = creditCursor.getString(idx);
                        String messageBodyLowerCase = messageBodyNormal.toLowerCase();
                        if (messageBodyLowerCase.contains("credited") || messageBodyLowerCase.contains("cash in") || messageBodyLowerCase.contains("received")) {
                            Log.i(TAG, creditCursor.getString(idx));
                            credit.setCreditDescription(messageBodyNormal);
                            credit.setCreditAmount(findCreditAmountFromMessageBody(messageBodyLowerCase));
                            if (!isCreditExisted(credit.getCreditTimestamp())) {
                                String month = new SimpleDateFormat("MMMM").format(new Date());
                                String year = new SimpleDateFormat("yyyy").format(new Date());
                                credit.setMonth(month);
                                credit.setYear(year);
                                this.expenseDataSource.insertCredit(credit);
                            }
                        }
                    }
                }
            } while (creditCursor.moveToNext());
        }
        if (creditCursor != null) {
            try {
                creditCursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            this.creditList = this.expenseDataSource.getAllCredits();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.loadingCreditProgressBar.setVisibility(4);
        if (this.creditList.size() == 0) {
            this.creditEmptyView.setVisibility(0);
            return;
        }
        Log.e(TAG, "creditList size: " + this.creditList.size());
        this.adapter = new CreditListAdapter(getContext(), this.creditList);
        this.creditListView.setAdapter(this.adapter);
        this.tvFooterCreditAmount.setText("" + this.expenseDataSource.getTotalCreditAmount());
    }

    private boolean isCreditExisted(int creditTimestamp) {
        ArrayList<Credit> credits = this.expenseDataSource.getAllCredits();
        ArrayList<Credit> deletedCredits = this.expenseDataSource.getAllDeletedCredits();
        Iterator it = credits.iterator();
        while (it.hasNext()) {
            if (((Credit) it.next()).getCreditTimestamp() == creditTimestamp) {
                return true;
            }
        }
        Iterator it2 = deletedCredits.iterator();
        while (it2.hasNext()) {
            if (((Credit) it2.next()).getCreditTimestamp() == creditTimestamp) {
                return true;
            }
        }
        return false;
    }

    private Double findCreditAmountFromMessageBody(String messageBody) {
        int indexOfTaka = -1;
        if (messageBody.contains("bdt")) {
            indexOfTaka = messageBody.indexOf("bdt");
        } else if (messageBody.contains("tk")) {
            indexOfTaka = messageBody.indexOf("tk");
        }
        double creditAmount = 0.0d;
        if (indexOfTaka != -1) {
            for (int i = indexOfTaka; i < messageBody.length(); i++) {
                char c = messageBody.charAt(i);
                if (Character.isDigit(c)) {
                    creditAmount = (10.0d * creditAmount) + ((double) (c - '0'));
                } else if (c == '.') {
                    break;
                }
            }
        }
        return Double.valueOf(creditAmount);
    }
}
