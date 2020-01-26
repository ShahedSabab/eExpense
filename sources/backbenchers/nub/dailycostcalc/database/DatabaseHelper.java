package backbencers.nub.dailycostcalc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import backbencers.nub.dailycostcalc.constant.Constant;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String CREATE_CATEGORY_TABLE = "CREATE TABLE Category (Id INTEGER PRIMARY KEY, CategoryName TEXT);";
    private static String CREATE_CREDIT_TABLE = "CREATE TABLE Credit (Id INTEGER PRIMARY KEY, CreditDate TEXT, month TEXT, year TEXT, CreditCategory TEXT, CreditDescription TEXT, CreditAmount DOUBLE, CreditTimestamp INTEGER);";
    private static String CREATE_DEBIT_TABLE = "CREATE TABLE Debit (Id INTEGER PRIMARY KEY, DebitDate TEXT, month TEXT, year TEXT, DebitCategory TEXT, DebitDescription TEXT, DebitAmount DOUBLE);";
    private static String CREATE_DELETED_CREDIT_TABLE = "CREATE TABLE DeletedCredit (Id INTEGER PRIMARY KEY, CreditDate TEXT, CreditCategory TEXT, CreditDescription TEXT, CreditAmount DOUBLE, CreditTimestamp INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DEBIT_TABLE);
        db.execSQL(CREATE_CREDIT_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_DELETED_CREDIT_TABLE);
        insertInitialCategories(db);
    }

    private void insertInitialCategories(SQLiteDatabase db) {
        db.execSQL("INSERT INTO Category VALUES (1, \"Clothing\");");
        db.execSQL("INSERT INTO Category VALUES (2, \"Charitable Giving\");");
        db.execSQL("INSERT INTO Category VALUES (3, \"Education\");");
        db.execSQL("INSERT INTO Category VALUES (4, \"Electronics\");");
        db.execSQL("INSERT INTO Category VALUES (5, \"Entertainment\");");
        db.execSQL("INSERT INTO Category VALUES (6, \"Fitness\");");
        db.execSQL("INSERT INTO Category VALUES (7, \"Food\");");
        db.execSQL("INSERT INTO Category VALUES (8, \"Gifts\");");
        db.execSQL("INSERT INTO Category VALUES (9, \"Health\");");
        db.execSQL("INSERT INTO Category VALUES (10, \"Household\");");
        db.execSQL("INSERT INTO Category VALUES (11, \"Investment\");");
        db.execSQL("INSERT INTO Category VALUES (12, \"Loan Payment\");");
        db.execSQL("INSERT INTO Category VALUES (13, \"Miscellaneous\");");
        db.execSQL("INSERT INTO Category VALUES (14, \"Rent\");");
        db.execSQL("INSERT INTO Category VALUES (15, \"Transportation\");");
        db.execSQL("INSERT INTO Category VALUES (16, \"Utility Bills\");");
        db.execSQL("INSERT INTO Category VALUES (17, \"Vacation\");");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Debit");
        db.execSQL("DROP TABLE IF EXISTS Credit");
        db.execSQL("DROP TABLE IF EXISTS Category");
        db.execSQL("DROP TABLE IF EXISTS DeletedCredit");
        onCreate(db);
    }
}
