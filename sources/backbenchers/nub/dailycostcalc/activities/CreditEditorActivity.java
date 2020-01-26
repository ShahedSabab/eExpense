package backbencers.nub.dailycostcalc.activities;

import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.constant.Constant;
import backbencers.nub.dailycostcalc.database.ExpenseDataSource;
import backbencers.nub.dailycostcalc.model.Category;
import backbencers.nub.dailycostcalc.model.Credit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class CreditEditorActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public static EditText etCreditDate;
    private String TAG = CreditEditorActivity.class.getSimpleName();
    private String activityType;
    private AutoCompleteTextView actvCreditCategory;
    private ArrayList<String> categoriesString = new ArrayList<>();
    private Credit credit;
    /* access modifiers changed from: private */
    public boolean creditHasChanged = false;
    private int creditId;
    private Intent creditIntent;
    private EditText etCreditAmount;
    private EditText etCreditDescription;
    private ExpenseDataSource expenseDataSource;
    private ImageButton ibCreditCalendar;
    private OnTouchListener touchListener = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            CreditEditorActivity.this.creditHasChanged = true;
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
            CreditEditorActivity.etCreditDate.setText("");
            CreditEditorActivity.etCreditDate.setText(finalDay + "-" + finalMonth + "-" + finalYear);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0374R.layout.activity_credit_editor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.expenseDataSource = new ExpenseDataSource(this);
        initializeViews();
        this.ibCreditCalendar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                new DatePickerFragment().show(CreditEditorActivity.this.getSupportFragmentManager(), "datePicker");
            }
        });
        getCategoriesFromDatabase();
        this.actvCreditCategory.setAdapter(new ArrayAdapter<>(this, 17367057, this.categoriesString));
        this.actvCreditCategory.setThreshold(1);
        this.creditIntent = getIntent();
        this.activityType = this.creditIntent.getStringExtra(Constant.ACTIVITY_TYPE);
        Log.e(this.TAG, "Activity type: " + this.activityType);
        if (this.activityType.equals(Constant.ACTIVITY_TYPE_ADD)) {
            setTitle("Add Credit");
            invalidateOptionsMenu();
            setInitialDate();
        } else if (this.activityType.equals(Constant.ACTIVITY_TYPE_EDIT)) {
            setTitle("Edit Credit");
            this.creditId = this.creditIntent.getIntExtra(Constant.CREDIT_ITEM_ID, -1);
            Log.e(this.TAG, "credit list item position: " + this.creditId);
            if (this.creditId > -1) {
                this.credit = this.expenseDataSource.getCredit(this.creditId);
                etCreditDate.setText(this.credit.getCreditDate());
                this.actvCreditCategory.setText(this.credit.getCreditCategory());
                this.etCreditDescription.setText(this.credit.getCreditDescription());
                this.etCreditAmount.setText("" + this.credit.getCreditAmount());
                return;
            }
            Toast.makeText(this, "Error loading credit!", 0).show();
        }
    }

    private void getCategoriesFromDatabase() {
        ArrayList<Category> categories = this.expenseDataSource.getAllCategories();
        for (int i = 0; i < categories.size(); i++) {
            this.categoriesString.add(((Category) categories.get(i)).getCategoryName());
        }
    }

    private void initializeViews() {
        etCreditDate = (EditText) findViewById(C0374R.C0376id.edit_text_credit_date);
        this.ibCreditCalendar = (ImageButton) findViewById(C0374R.C0376id.image_button_credit_calendar);
        this.actvCreditCategory = (AutoCompleteTextView) findViewById(C0374R.C0376id.auto_complete_credit_category);
        this.etCreditDescription = (EditText) findViewById(C0374R.C0376id.edit_text_credit_description);
        this.etCreditAmount = (EditText) findViewById(C0374R.C0376id.edit_text_credit_amount);
        etCreditDate.setOnTouchListener(this.touchListener);
        this.ibCreditCalendar.setOnTouchListener(this.touchListener);
        this.actvCreditCategory.setOnTouchListener(this.touchListener);
        this.etCreditDescription.setOnTouchListener(this.touchListener);
        this.etCreditAmount.setOnTouchListener(this.touchListener);
    }

    private void setInitialDate() {
        etCreditDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
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
                if (!this.creditHasChanged) {
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                }
                showUnsavedChangesDialog(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NavUtils.navigateUpFromSameTask(CreditEditorActivity.this);
                    }
                });
                return true;
            case C0374R.C0376id.action_save /*2131624240*/:
                saveCredit();
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
                CreditEditorActivity.this.deleteCredit();
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
    public void deleteCredit() {
        if (this.activityType.equals(Constant.ACTIVITY_TYPE_EDIT)) {
            boolean deleted = this.expenseDataSource.deleteCredit(this.creditId);
            this.expenseDataSource.insertDeletedCredit(this.credit);
            if (deleted) {
                Toast.makeText(this, getString(C0374R.string.editor_delete_credit_successful), 0).show();
            } else {
                Toast.makeText(this, getString(C0374R.string.editor_delete_credit_failed), 0).show();
            }
        }
        finish();
    }

    public void onBackPressed() {
        if (!this.creditHasChanged) {
            super.onBackPressed();
        } else {
            showUnsavedChangesDialog(new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CreditEditorActivity.this.finish();
                }
            });
        }
    }

    private void saveCredit() {
        String date = etCreditDate.getText().toString().trim();
        String category = this.actvCreditCategory.getText().toString().trim();
        String description = this.etCreditDescription.getText().toString().trim();
        String amount = this.etCreditAmount.getText().toString().trim();
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
            Credit credit2 = new Credit(date, new SimpleDateFormat("MMMM").format(new Date()), new SimpleDateFormat("yyyy").format(new Date()), category, description, new Double(amount), (int) (System.currentTimeMillis() % 100000000));
            Log.e(this.TAG, "system currentTimeMillis: " + (System.currentTimeMillis() % 100000000));
            if (this.activityType.equals(Constant.ACTIVITY_TYPE_ADD)) {
                if (this.expenseDataSource.insertCredit(credit2)) {
                    Toast.makeText(this, "Credit saved!", 0).show();
                    finish();
                    return;
                }
                Toast.makeText(this, "Failed to save credit!", 0).show();
            } else if (!this.activityType.equals(Constant.ACTIVITY_TYPE_EDIT)) {
            } else {
                if (this.expenseDataSource.updateCredit(this.creditId, credit2)) {
                    Toast.makeText(this, "Credit updated!", 0).show();
                    finish();
                    return;
                }
                Toast.makeText(this, "Failed to update credit!", 0).show();
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
}
