package backbencers.nub.dailycostcalc.activities;

import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.NavUtils;
import android.support.p003v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.constant.Constant;
import backbencers.nub.dailycostcalc.database.ExpenseDataSource;
import backbencers.nub.dailycostcalc.model.Category;
import backbencers.nub.dailycostcalc.model.Debit;
import backbencers.nub.dailycostcalc.objects.ObjectParser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class DebitEditorActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public static EditText etDebitDate;
    private final int PERMS_REQUEST_CODE = 102;
    private String TAG = DebitEditorActivity.class.getSimpleName();
    private String activityType;
    private AutoCompleteTextView actvDebitCategory;
    private Button btnScanDebit;
    private ArrayList<String> categoriesString = new ArrayList<>();
    private Debit debit;
    /* access modifiers changed from: private */
    public boolean debitHasChanged = false;
    private int debitId;
    private Intent debitIntent;
    private EditText etDebitAmount;
    private EditText etDebitDescription;
    private ExpenseDataSource expenseDataSource;
    private ImageButton ibDebitCalendar;
    private String[] permissions = {"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    private OnTouchListener touchListener = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            DebitEditorActivity.this.debitHasChanged = true;
            return false;
        }
    };

    public static class DatePickerFragment extends DialogFragment implements OnDateSetListener {
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            return new DatePickerDialog(getActivity(), this, c.get(1), c.get(2), c.get(5));
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            int month2 = month + 1;
            String finalDay = day > 9 ? "" + day : "0" + day;
            String finalMonth = month2 > 9 ? "" + month2 : "0" + month2;
            String finalYear = "" + year;
            DebitEditorActivity.etDebitDate.setText("");
            DebitEditorActivity.etDebitDate.setText(finalDay + "-" + finalMonth + "-" + finalYear);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0374R.layout.activity_debit_editor);
        String scanData = getIntent().getStringExtra(Constant.INTENT_SCAN_DATA);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.expenseDataSource = new ExpenseDataSource(this);
        initializeViews();
        if (scanData != null && scanData.trim().length() > 0) {
            String category = getIntent().getStringExtra("Category");
            if (category != null) {
                this.actvDebitCategory.setText(category);
            }
            this.debit = new ObjectParser(this.actvDebitCategory.getText().toString(), scanData).parse();
            this.actvDebitCategory.setText(this.debit.getDebitCategory());
            etDebitDate.setText(this.debit.getDebitDate());
            this.etDebitAmount.setText(this.debit.getDebitAmount() + "");
            this.etDebitDescription.setText(this.debit.getDebitDescription());
        }
        this.ibDebitCalendar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                new DatePickerFragment().show(DebitEditorActivity.this.getSupportFragmentManager(), "datePicker");
            }
        });
        this.btnScanDebit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (DebitEditorActivity.this.hasPermissions()) {
                    DebitEditorActivity.this.moveToScan();
                } else {
                    DebitEditorActivity.this.requestPerms();
                }
            }
        });
        getCategoriesFromDatabase();
        this.actvDebitCategory.setAdapter(new ArrayAdapter<>(this, 17367057, this.categoriesString));
        this.actvDebitCategory.setThreshold(1);
        this.debitIntent = getIntent();
        this.activityType = this.debitIntent.getStringExtra(Constant.ACTIVITY_TYPE);
        Log.e(this.TAG, "Activity type: " + this.activityType);
        if (this.activityType.equals(Constant.ACTIVITY_TYPE_ADD)) {
            setTitle("Add Debit");
            invalidateOptionsMenu();
            setInitialDate();
        } else if (this.activityType.equals(Constant.ACTIVITY_TYPE_EDIT)) {
            setTitle("Edit Debit");
            this.debitId = this.debitIntent.getIntExtra(Constant.DEBIT_ITEM_ID, -1);
            Log.e(this.TAG, "debit list item position: " + this.debitId);
            if (this.debitId > -1) {
                this.debit = this.expenseDataSource.getDebit(this.debitId);
                etDebitDate.setText(this.debit.getDebitDate());
                this.actvDebitCategory.setText(this.debit.getDebitCategory());
                this.etDebitDescription.setText(this.debit.getDebitDescription());
                this.etDebitAmount.setText("" + this.debit.getDebitAmount());
                return;
            }
            Toast.makeText(this, "Error loading debit!", 0).show();
        }
    }

    private void initializeViews() {
        etDebitDate = (EditText) findViewById(C0374R.C0376id.edit_text_debit_date);
        this.ibDebitCalendar = (ImageButton) findViewById(C0374R.C0376id.image_button_debit_calendar);
        this.actvDebitCategory = (AutoCompleteTextView) findViewById(C0374R.C0376id.auto_complete_debit_category);
        this.etDebitDescription = (EditText) findViewById(C0374R.C0376id.edit_text_debit_description);
        this.etDebitAmount = (EditText) findViewById(C0374R.C0376id.edit_text_debit_amount);
        this.btnScanDebit = (Button) findViewById(C0374R.C0376id.btn_scan_debit);
        etDebitDate.setOnTouchListener(this.touchListener);
        this.ibDebitCalendar.setOnTouchListener(this.touchListener);
        this.actvDebitCategory.setOnTouchListener(this.touchListener);
        this.etDebitDescription.setOnTouchListener(this.touchListener);
        this.etDebitAmount.setOnTouchListener(this.touchListener);
    }

    private void getCategoriesFromDatabase() {
        ArrayList<Category> categories = this.expenseDataSource.getAllCategories();
        for (int i = 0; i < categories.size(); i++) {
            this.categoriesString.add(((Category) categories.get(i)).getCategoryName());
        }
    }

    private void setInitialDate() {
        etDebitDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0374R.C0377menu.menu_editor, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (this.activityType.equals(Constant.ACTIVITY_TYPE_ADD)) {
            menu.findItem(C0374R.C0376id.action_delete).setVisible(false);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                if (!this.debitHasChanged) {
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                }
                showUnsavedChangesDialog(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NavUtils.navigateUpFromSameTask(DebitEditorActivity.this);
                    }
                });
                return true;
            case C0374R.C0376id.action_save /*2131624240*/:
                saveDebit();
                return true;
            case C0374R.C0376id.action_delete /*2131624241*/:
                showDeleteConfirmationDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDeleteConfirmationDialog() {
        Builder builder = new Builder(this);
        builder.setMessage(C0374R.string.delete_credit_dialog_msg);
        builder.setPositiveButton(C0374R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DebitEditorActivity.this.deleteDebit();
            }
        });
        builder.setNegativeButton(C0374R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void deleteDebit() {
        if (this.activityType.equals(Constant.ACTIVITY_TYPE_EDIT)) {
            if (this.expenseDataSource.deleteDebit(this.debitId)) {
                Toast.makeText(this, getString(C0374R.string.editor_delete_debit_successful), 0).show();
            } else {
                Toast.makeText(this, getString(C0374R.string.editor_delete_debit_failed), 0).show();
            }
        }
        finish();
    }

    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListener) {
        Builder builder = new Builder(this);
        builder.setMessage(C0374R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(C0374R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(C0374R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        builder.create().show();
    }

    private void saveDebit() {
        String date = etDebitDate.getText().toString().trim();
        String category = this.actvDebitCategory.getText().toString().trim();
        String description = this.etDebitDescription.getText().toString().trim();
        String amount = this.etDebitAmount.getText().toString().trim();
        if (TextUtils.isEmpty(date)) {
            Toast.makeText(this, "Please enter or select a date!", 0).show();
        } else if (TextUtils.isEmpty(category)) {
            Toast.makeText(this, "Please enter a category!", 0).show();
        } else if (TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Please enter description!", 0).show();
        } else if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "Please enter amount!", 0).show();
        } else {
            if (!isCategoryExisted(category)) {
                this.expenseDataSource.insertCategory(new Category(category));
            }
            Debit debit2 = new Debit(date, new SimpleDateFormat("MMMM").format(new Date()), new SimpleDateFormat("yyyy").format(new Date()), category, description, new Double(amount));
            if (this.activityType.equals(Constant.ACTIVITY_TYPE_ADD)) {
                if (this.expenseDataSource.insertDebit(debit2)) {
                    Toast.makeText(this, "Debit saved!", 0).show();
                    finish();
                    return;
                }
                Toast.makeText(this, "Failed to save debit!", 0).show();
            } else if (!this.activityType.equals(Constant.ACTIVITY_TYPE_EDIT)) {
            } else {
                if (this.expenseDataSource.updateDebit(this.debitId, debit2)) {
                    Toast.makeText(this, "Debit updated!", 0).show();
                    finish();
                    return;
                }
                Toast.makeText(this, "Failed to update debit!", 0).show();
            }
        }
    }

    private boolean isCategoryExisted(String category) {
        Iterator it = this.categoriesString.iterator();
        while (it.hasNext()) {
            if (((String) it.next()).equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void moveToScan() {
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra("Category", this.actvDebitCategory.getText().toString());
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    /* access modifiers changed from: private */
    public boolean hasPermissions() {
        for (String permission : this.permissions) {
            if (checkCallingOrSelfPermission(permission) != 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void requestPerms() {
        if (VERSION.SDK_INT >= 23) {
            requestPermissions(this.permissions, 102);
        }
    }

    public void onBackPressed() {
        if (!this.debitHasChanged) {
            super.onBackPressed();
        } else {
            showUnsavedChangesDialog(new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DebitEditorActivity.this.finish();
                }
            });
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions2, @NonNull int[] grantResults) {
        boolean allowed = true;
        switch (requestCode) {
            case 102:
                for (int res : grantResults) {
                    if (!allowed || res != 0) {
                        allowed = false;
                    } else {
                        allowed = true;
                    }
                }
                break;
            default:
                allowed = false;
                break;
        }
        if (allowed) {
            moveToScan();
        } else if (VERSION.SDK_INT >= 23) {
            if (shouldShowRequestPermissionRationale(permissions2[0])) {
                Toast.makeText(this, "Camera permission denied!", 0).show();
            }
            if (shouldShowRequestPermissionRationale(permissions2[1])) {
                Toast.makeText(this, "Storage permission denied!", 0).show();
            }
            if (shouldShowRequestPermissionRationale(permissions2[2])) {
                Toast.makeText(this, "Reader permission denied!", 0).show();
            }
        }
    }
}
