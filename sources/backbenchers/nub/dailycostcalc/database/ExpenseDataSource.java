package backbencers.nub.dailycostcalc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import backbencers.nub.dailycostcalc.constant.Constant;
import backbencers.nub.dailycostcalc.model.Category;
import backbencers.nub.dailycostcalc.model.Credit;
import backbencers.nub.dailycostcalc.model.Debit;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public ExpenseDataSource(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public void open() {
        this.database = this.databaseHelper.getWritableDatabase();
    }

    public void close() {
        this.database.close();
    }

    public boolean insertDebit(Debit debit) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COL_DEBIT_DATE, debit.getDebitDate());
        contentValues.put(Constant.COL_MONTH, debit.getMonth());
        contentValues.put(Constant.COL_YEAR, debit.getYear());
        contentValues.put(Constant.COL_DEBIT_CATEGORY, debit.getDebitCategory());
        contentValues.put(Constant.COL_DEBIT_DESCRIPTION, debit.getDebitDescription());
        contentValues.put(Constant.COL_DEBIT_AMOUNT, debit.getDebitAmount());
        long inserted = this.database.insert(Constant.TABLE_DEBIT, null, contentValues);
        close();
        return inserted > 0;
    }

    public boolean insertCredit(Credit credit) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COL_CREDIT_DATE, credit.getCreditDate());
        contentValues.put(Constant.COL_MONTH, credit.getMonth());
        contentValues.put(Constant.COL_YEAR, credit.getYear());
        contentValues.put(Constant.COL_CREDIT_CATEGORY, credit.getCreditCategory());
        contentValues.put(Constant.COL_CREDIT_DESCRIPTION, credit.getCreditDescription());
        contentValues.put(Constant.COL_CREDIT_AMOUNT, credit.getCreditAmount());
        contentValues.put(Constant.COL_CREDIT_TIMESTAMP, Integer.valueOf(credit.getCreditTimestamp()));
        long inserted = this.database.insert(Constant.TABLE_CREDIT, null, contentValues);
        close();
        return inserted > 0;
    }

    public boolean insertDeletedCredit(Credit credit) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COL_CREDIT_DATE, credit.getCreditDate());
        contentValues.put(Constant.COL_CREDIT_CATEGORY, credit.getCreditCategory());
        contentValues.put(Constant.COL_CREDIT_DESCRIPTION, credit.getCreditDescription());
        contentValues.put(Constant.COL_CREDIT_AMOUNT, credit.getCreditAmount());
        contentValues.put(Constant.COL_CREDIT_TIMESTAMP, Integer.valueOf(credit.getCreditTimestamp()));
        long inserted = this.database.insert(Constant.TABLE_DELETED_CREDIT, null, contentValues);
        close();
        return inserted > 0;
    }

    public boolean insertCategory(Category category) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COL_CATEGORY_NAME, category.getCategoryName());
        long inserted = this.database.insert("Category", null, contentValues);
        close();
        return inserted > 0;
    }

    public Debit getDebit(int id) {
        open();
        Cursor cursor = this.database.query(Constant.TABLE_DEBIT, new String[]{Constant.COL_ID, Constant.COL_DEBIT_DATE, Constant.COL_DEBIT_CATEGORY, Constant.COL_DEBIT_DESCRIPTION, Constant.COL_DEBIT_AMOUNT}, "Id = " + id, null, null, null, null);
        cursor.moveToFirst();
        Debit debit = createDebit(cursor);
        cursor.close();
        close();
        return debit;
    }

    public Credit getCredit(int id) {
        open();
        Cursor cursor = this.database.query(Constant.TABLE_CREDIT, new String[]{Constant.COL_ID, Constant.COL_CREDIT_DATE, Constant.COL_CREDIT_CATEGORY, Constant.COL_CREDIT_DESCRIPTION, Constant.COL_CREDIT_AMOUNT, Constant.COL_CREDIT_TIMESTAMP}, "Id = " + id, null, null, null, null);
        cursor.moveToFirst();
        Credit credit = createCredit(cursor);
        cursor.close();
        close();
        return credit;
    }

    public ArrayList<Debit> getAllDebits() {
        ArrayList<Debit> debits = new ArrayList<>();
        open();
        Cursor cursor = this.database.rawQuery("SELECT * FROM Debit", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                debits.add(createDebit(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.database.close();
        return debits;
    }

    public List<Debit> getDebitsInThisDate(String date) {
        List<Debit> debits = new ArrayList<>();
        open();
        Cursor cursor = this.database.rawQuery("SELECT * FROM Debit WHERE DebitDate = ?", new String[]{date});
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                debits.add(createDebit(cursor));
                cursor.moveToNext();
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        if (this.database != null) {
            this.database.close();
        }
        return debits;
    }

    public double getDebitAmountInThisDate(String date) {
        double amount = 0.0d;
        open();
        Cursor cursor = this.database.rawQuery("SELECT SUM(DebitAmount) FROM Debit WHERE DebitDate = ?", new String[]{date});
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            amount = cursor.getDouble(0);
        }
        if (cursor != null) {
            cursor.close();
        }
        if (this.database != null) {
            this.database.close();
        }
        return amount;
    }

    public double getTotalDebitAmount(String month, String year) {
        double amount = 0.0d;
        open();
        Cursor cursor = this.database.rawQuery("SELECT SUM(DebitAmount) FROM Debit WHERE month = ? AND year = ?", new String[]{month, year});
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            amount = cursor.getDouble(0);
        }
        if (cursor != null) {
            cursor.close();
        }
        if (this.database != null) {
            this.database.close();
        }
        return amount;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> getMonthListForDebit(java.lang.String r7) {
        /*
            r6 = this;
            r5 = 0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r6.open()
            android.database.sqlite.SQLiteDatabase r2 = r6.database
            java.lang.String r3 = "SELECT DISTINCT month FROM Debit WHERE year = ?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]
            r4[r5] = r7
            android.database.Cursor r0 = r2.rawQuery(r3, r4)
            if (r0 == 0) goto L_0x002e
            int r2 = r0.getCount()
            if (r2 <= 0) goto L_0x002e
            r0.moveToFirst()
        L_0x0021:
            java.lang.String r2 = r0.getString(r5)
            r1.add(r2)
            boolean r2 = r0.moveToNext()
            if (r2 != 0) goto L_0x0021
        L_0x002e:
            if (r0 == 0) goto L_0x0033
            r0.close()
        L_0x0033:
            android.database.sqlite.SQLiteDatabase r2 = r6.database
            if (r2 == 0) goto L_0x003c
            android.database.sqlite.SQLiteDatabase r2 = r6.database
            r2.close()
        L_0x003c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: backbencers.nub.dailycostcalc.database.ExpenseDataSource.getMonthListForDebit(java.lang.String):java.util.List");
    }

    public ArrayList<Credit> getAllCredits() {
        ArrayList<Credit> credits = new ArrayList<>();
        open();
        Cursor cursor = this.database.rawQuery("SELECT * FROM Credit", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                credits.add(createCredit(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.database.close();
        return credits;
    }

    public ArrayList<Credit> getAllDeletedCredits() {
        ArrayList<Credit> credits = new ArrayList<>();
        open();
        Cursor cursor = this.database.rawQuery("SELECT * FROM DeletedCredit", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                credits.add(createCredit(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.database.close();
        return credits;
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        open();
        Cursor cursor = this.database.rawQuery("SELECT * FROM Category", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                categories.add(createCategory(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.database.close();
        return categories;
    }

    public double getCreditAmounts(String month, String year) {
        double amount = 0.0d;
        open();
        Cursor cursor = this.database.rawQuery("SELECT SUM(CreditAmount) FROM Credit WHERE month = ? AND year = ?", new String[]{month, year});
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            amount = cursor.getDouble(0);
        }
        if (cursor != null) {
            cursor.close();
        }
        if (this.database != null) {
            this.database.close();
        }
        return amount;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> getMonthListForCredit(java.lang.String r7) {
        /*
            r6 = this;
            r5 = 0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r6.open()
            android.database.sqlite.SQLiteDatabase r2 = r6.database
            java.lang.String r3 = "SELECT DISTINCT month FROM Credit WHERE year = ?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]
            r4[r5] = r7
            android.database.Cursor r0 = r2.rawQuery(r3, r4)
            if (r0 == 0) goto L_0x002e
            int r2 = r0.getCount()
            if (r2 <= 0) goto L_0x002e
            r0.moveToFirst()
        L_0x0021:
            java.lang.String r2 = r0.getString(r5)
            r1.add(r2)
            boolean r2 = r0.moveToNext()
            if (r2 != 0) goto L_0x0021
        L_0x002e:
            if (r0 == 0) goto L_0x0033
            r0.close()
        L_0x0033:
            android.database.sqlite.SQLiteDatabase r2 = r6.database
            if (r2 == 0) goto L_0x003c
            android.database.sqlite.SQLiteDatabase r2 = r6.database
            r2.close()
        L_0x003c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: backbencers.nub.dailycostcalc.database.ExpenseDataSource.getMonthListForCredit(java.lang.String):java.util.List");
    }

    public ArrayList<Double> getCreditAmountsInThisDate(String date) {
        ArrayList<Double> creditAmounts = new ArrayList<>();
        open();
        Cursor cursor = this.database.rawQuery("SELECT * FROM Credit WHERE CreditDate = " + date, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                creditAmounts.add(createCredit(cursor).getCreditAmount());
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.database.close();
        return creditAmounts;
    }

    public boolean updateDebit(int id, Debit debit) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COL_DEBIT_DATE, debit.getDebitDate());
        contentValues.put(Constant.COL_DEBIT_CATEGORY, debit.getDebitCategory());
        contentValues.put(Constant.COL_DEBIT_DESCRIPTION, debit.getDebitDescription());
        contentValues.put(Constant.COL_DEBIT_AMOUNT, debit.getDebitAmount());
        int updated = this.database.update(Constant.TABLE_DEBIT, contentValues, "Id = " + id, null);
        close();
        return updated > 0;
    }

    public boolean updateCredit(int id, Credit credit) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COL_CREDIT_DATE, credit.getCreditDate());
        contentValues.put(Constant.COL_CREDIT_CATEGORY, credit.getCreditCategory());
        contentValues.put(Constant.COL_CREDIT_DESCRIPTION, credit.getCreditDescription());
        contentValues.put(Constant.COL_CREDIT_AMOUNT, credit.getCreditAmount());
        contentValues.put(Constant.COL_CREDIT_TIMESTAMP, Integer.valueOf(credit.getCreditTimestamp()));
        int updated = this.database.update(Constant.TABLE_CREDIT, contentValues, "Id = " + id, null);
        close();
        return updated > 0;
    }

    public boolean deleteDebit(int id) {
        open();
        int deleted = this.database.delete(Constant.TABLE_DEBIT, "Id = " + id, null);
        close();
        return deleted > 0;
    }

    public boolean deleteCredit(int id) {
        open();
        int deleted = this.database.delete(Constant.TABLE_CREDIT, "Id = " + id, null);
        close();
        return deleted > 0;
    }

    public double getTotalDebitAmount() {
        open();
        Cursor c = this.database.rawQuery("SELECT SUM(DebitAmount) FROM Debit", null);
        c.moveToFirst();
        double amount = c.getDouble(0);
        c.close();
        return amount;
    }

    public double getTotalDebitAmountOnThisYear(int year) {
        open();
        Cursor c = this.database.rawQuery("SELECT SUM(DebitAmount) FROM Debit", null);
        c.moveToFirst();
        double amount = c.getDouble(0);
        c.close();
        return amount;
    }

    public double getTotalCreditAmount() {
        open();
        Cursor c = this.database.rawQuery("SELECT SUM(CreditAmount) FROM Credit", null);
        c.moveToFirst();
        double amount = c.getDouble(0);
        c.close();
        return amount;
    }

    public void deleteAllCredits() {
        open();
        this.database.delete(Constant.TABLE_CREDIT, null, null);
        close();
    }

    private Debit createDebit(Cursor cursor) {
        return new Debit(cursor.getInt(cursor.getColumnIndex(Constant.COL_ID)), cursor.getString(cursor.getColumnIndex(Constant.COL_DEBIT_DATE)), cursor.getString(cursor.getColumnIndex(Constant.COL_DEBIT_CATEGORY)), cursor.getString(cursor.getColumnIndex(Constant.COL_DEBIT_DESCRIPTION)), Double.valueOf(cursor.getDouble(cursor.getColumnIndex(Constant.COL_DEBIT_AMOUNT))));
    }

    private Credit createCredit(Cursor cursor) {
        return new Credit(cursor.getInt(cursor.getColumnIndex(Constant.COL_ID)), cursor.getString(cursor.getColumnIndex(Constant.COL_CREDIT_DATE)), cursor.getString(cursor.getColumnIndex(Constant.COL_CREDIT_CATEGORY)), cursor.getString(cursor.getColumnIndex(Constant.COL_CREDIT_DESCRIPTION)), Double.valueOf(cursor.getDouble(cursor.getColumnIndex(Constant.COL_CREDIT_AMOUNT))), cursor.getInt(cursor.getColumnIndex(Constant.COL_CREDIT_TIMESTAMP)));
    }

    private Category createCategory(Cursor cursor) {
        return new Category(cursor.getInt(cursor.getColumnIndex(Constant.COL_ID)), cursor.getString(cursor.getColumnIndex(Constant.COL_CATEGORY_NAME)));
    }
}
