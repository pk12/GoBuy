//package com.example.nightc.gobuy;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.example.nightc.gobuy.GoBuySDK.BillsAPI;
//import com.example.nightc.gobuy.GoBuySDK.Goal;
//import com.example.nightc.gobuy.GoBuySDK.GoalDates;
//import com.example.nightc.gobuy.GoBuySDK.IncomesAPI;
//import com.example.nightc.gobuy.GoBuySDK.Item;
//import com.example.nightc.gobuy.GoBuySDK.UserClasses.Credentials;
//import com.example.nightc.gobuy.GoBuySDK.UserClasses.Expense;
//import com.example.nightc.gobuy.GoBuySDK.UserClasses.Income;
//import com.example.nightc.gobuy.GoBuySDK.UserClasses.Job;
//import com.example.nightc.gobuy.GoBuySDK.UserClasses.ShoppingInterests;
//import com.example.nightc.gobuy.GoBuySDK.UserClasses.SpontaeousExpense;
//import com.example.nightc.gobuy.GoBuySDK.UserClasses.SpontaneousIncome;
//import com.example.nightc.gobuy.GoBuySDK.UserClasses.StableExpense;
//import com.example.nightc.gobuy.GoBuySDK.UserClasses.StableIncome;
//import com.example.nightc.gobuy.GoBuySDK.UserClasses.User;
//import com.example.nightc.gobuy.GoBuySDK.UserClasses.UserData;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import org.joda.time.LocalDate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///* At the AddIncomes/Expenses method we have to note that when we create an Expense/Income we need a variable to keep the
//latest ID so that the new one will be the last +1
// */
//public class DBHandler extends SQLiteOpenHelper {
//
//    // Database Version
//    private static final int DATABASE_VERSION = 1;
//    // Database Name
//    private static final String DATABASE_NAME = "GobuyDB";
//    // Tables
//    private static final String TABLE_USER = "User ";
//    private static final String KEY_USERID = "UserID ";
//
//    private static final String TABLE_CREDENTIALS = "Credentials";
//    private static final String KEY_USERNAME = "Username ";
//    private static final String KEY_PASSWORD = "Password ";
//
//    private static final String TABLE_USERDATA = "UserData ";
//    private static final String KEY_AREA = "Area ";
//    private static final String KEY_AGE = "Age ";
//    private static final String KEY_GENDER = "Gender";
//    private static final String KEY_SHOPPINGINTERESTS = "Shopping_Interests ";
//    private static final String KEY_STABLEINCOMES = "Stable_Incomes "; //FOR ARRAYLIST JSON FILE
//    private static final String KEY_STABLEEXPENSES = "Stable_Expenses ";
//    private static final String KEY_SPONTANEOUSINCOMES = "Sponatneous_Incomes ";
//    private static final String KEY_SPONTANEOUSEXPENSES = "Spontaneous_Expenses ";
//
//
//    private static final String TABLE_GOAL = "Goal ";
//    private static final String KEY_MONEYSAVED = "MoneySaved ";
//    private static final String KEY_MONEYTOSAVEPERDAY = "MoneyToSavePerDay ";
//    private static final String KEY_GOALID = "GoalID ";
//
//    private static final String TABLE_ITEM = "Item ";
//    private static final String KEY_NAME = "Name ";
//    private static final String KEY_PRICE = "Price ";
//    private static final String KEY_CATEGORY = "Category ";
//
//    private static final String TABLE_JOB = "Job ";
//    private static final String KEY_TYPE = "Type ";
//    private static final String KEY_SALARY = "Salary ";
//    private static final String KEY_JOBID = "JobID ";
//    private static final String KEY_PAYPERIOD = "Pay_Period ";
//
//    private static final String TABLE_EXTRAINCOME = "Income ";
//    private static final String KEY_TYPE_EXTRAINCOME = "Type ";
//    private static final String KEY_AMOUNT_EXTRAINCOME = "Amount ";
//    private static final String KEY_EXTRID = "ExtrID ";
//
//    private static final String TABLE_EXPENSE = "Expense ";
//    private static final String KEY_TYPE_EXPENSE = "Type ";
//    private static final String KEY_AMOUNT_EXPENSE = "Amount ";
//    private static final String KEY_EXPENSEID = "ExpenseID ";
//
//    private static final String TABLE_DATES = "Dates ";
//    private static final String KEY_DATEWANTED = "DateWanted ";
//    private static final String KEY_EXPECTEDDATE = "ExpectedDate ";
//    private static final String KEY_DATECREATED = "DateCreated ";
//
//
//    public DBHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        //You had a syntax error,creting the table does not need the database name in front
//        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "(" +
//                KEY_USERID + "INTEGER PRIMARY KEY NOT NULL" + ");";
//        db.execSQL(CREATE_USER_TABLE);
//
//        String CREATE_CREDENTIALS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CREDENTIALS + "(" +
//                KEY_USERNAME + "VARCHAR(45) NOT NULL" + "," +
//                KEY_PASSWORD + "VARCHAR(45) NOT NULL" + "," +
//                KEY_USERID + "INTEGER PRIMARY KEY NOT NULL" + "," +
//                "CONSTRAINT Username_UNIQUE " + "UNIQUE(" + KEY_USERNAME + ")" + "," +
//                " CONSTRAINT fk_Credentials_User1 " +
//                "FOREIGN KEY(" + KEY_USERID + ")" +
//                "REFERENCES " + TABLE_USER + "(" + KEY_USERID + ")" +
//                ");";
//        db.execSQL(CREATE_CREDENTIALS_TABLE);
//
//        //ti exeis kanei apo edw kai katw
//        String CREATE_CREDENTIALS_INDEX = "CREATE INDEX IF NOT EXISTS " + "fk_Credentials_User1_idx " + "ON " + TABLE_CREDENTIALS + "(" + KEY_USERID + "); ";
//        db.execSQL(CREATE_CREDENTIALS_INDEX);
//        String CREATE_USERDATA_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USERDATA + "(" +
//                KEY_AREA + "VARCHAR(45) NOT NULL" + "," +
//                KEY_AGE + "INTEGER NOT NULL" + "," +
//                KEY_GENDER + "INTEGER NOT NULL" + "," +
//                KEY_USERID + "INTEGER PRIMARY KEY NOT NULL" + "," +
//                KEY_SHOPPINGINTERESTS + "TEXT" + "," +
//                KEY_STABLEINCOMES + "TEXT" + "," +
//                KEY_STABLEEXPENSES + "TEXT" + "," +
//                KEY_SPONTANEOUSINCOMES + "TEXT" + "," +
//                KEY_SPONTANEOUSEXPENSES + "TEXT" + "," +
//                "CONSTRAINT fk_UserData_User1 " +
//                "FOREIGN KEY(" + KEY_USERID + ")" +
//                "REFERENCES " + TABLE_USER + "(" + KEY_USERID + ")" +
//                ");";
//        db.execSQL(CREATE_USERDATA_TABLE);
//        String CREATE_USERDATA_INDEX = "CREATE INDEX IF NOT EXISTS " + "fk_UserData_User1_idx " + "ON " + TABLE_USERDATA + "(" + KEY_USERID + "); ";
//        db.execSQL(CREATE_USERDATA_INDEX);
//        String CREATE_GOAL_TABLE = "CREATE TABLE " + TABLE_GOAL + "(" +
//                KEY_MONEYSAVED + "DOUBLE NOT NULL" + "," +
//                KEY_MONEYTOSAVEPERDAY + "DOUBLE NOT NULL" + "," +
//                KEY_GOALID + "INTEGER NOT NULL" + "," +
//                KEY_USERID + "INTEGER  NOT NULL" + "," +
//                "PRIMARY KEY(" + KEY_GOALID + "," + KEY_USERID + ")," +
//                "CONSTRAINT fk_Goal_User1 " +
//                "FOREIGN KEY(" + KEY_USERID + ")" +
//                "REFERENCES " + TABLE_USER + "(" + KEY_USERID + ")" +
//                ");";
//        db.execSQL(CREATE_GOAL_TABLE);
//        String CREATE_GOAL_INDEX = "CREATE INDEX IF NOT EXISTS " + "fk_Goal_User1_idx " + "ON " + TABLE_GOAL + "(" + KEY_USERID + "); ";
//        db.execSQL(CREATE_GOAL_INDEX);
//        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEM + "(" +
//                KEY_NAME + " VARCHAR(45) NOT NULL" + "," +
//                KEY_PRICE + "DOUBLE NOT NULL" + "," +
//                KEY_CATEGORY + " VARCHAR(45) NOT NULL" + "," +
//                KEY_GOALID + "INTEGER NOT NULL" + "," +
//                KEY_USERID + "INTEGER  NOT NULL" + "," +
//                "PRIMARY KEY(" + KEY_GOALID + "," + KEY_USERID + ")," +
//                "CONSTRAINT fk_Item_Goal1 " +
//                "FOREIGN KEY(" + KEY_GOALID + "," + KEY_USERID + ")" +
//                "REFERENCES " + TABLE_GOAL + "(" + KEY_GOALID + "," + KEY_USERID + ")" +
//                ");";
//        db.execSQL(CREATE_ITEM_TABLE);
//        String CREATE_ITEM_INDEX = "CREATE INDEX IF NOT EXISTS " + "fk_Goal_User1_idx " + "ON " + TABLE_ITEM + "(" + KEY_GOALID + "," + KEY_USERID + "); ";
//        db.execSQL(CREATE_ITEM_INDEX);
//        String CREATE_JOB_TABLE = "CREATE TABLE " + TABLE_JOB + "(" +
//                KEY_TYPE + "VARCHAR(45) NOT NULL" + "," +
//                KEY_SALARY + "DOUBLE NOT NULL" + "," +
//                KEY_JOBID + "INTEGER NOT NULL" + "," +
//                KEY_USERID + "INTEGER UNIQUE NOT NULL" + "," +
//                KEY_PAYPERIOD + "VARCHAR(45) NOT NULL" + "," +
//                "PRIMARY KEY(" + KEY_JOBID + "," + KEY_USERID + ")," +
//                "CONSTRAINT fk_Job_UserData1 " +
//                "FOREIGN KEY(" + KEY_USERID + ")" +
//                "REFERENCES " + TABLE_USERDATA + "(" + KEY_USERID + ")" +
//                ");";
//        db.execSQL(CREATE_JOB_TABLE);
//        String CREATE_JOB_INDEX = "CREATE INDEX IF NOT EXISTS " + "fk_Job_User1_idx " + "ON " + TABLE_JOB + "(" + KEY_USERID + "); ";
//        db.execSQL(CREATE_JOB_INDEX);
//        String CREATE_EXTRAINCOME_TABLE = "CREATE TABLE " + TABLE_EXTRAINCOME + "(" +
//                KEY_TYPE_EXTRAINCOME + "VARCHAR(45) NOT NULL" + "," +
//                KEY_AMOUNT_EXTRAINCOME + "DOUBLE NOT NULL" + "," +
//                KEY_EXTRID + "INTEGER NOT NULL" + "," +
//                KEY_USERID + "INTEGER UNIQUE NOT NULL" + "," +
//                "PRIMARY KEY(" + KEY_EXTRID +"," + KEY_USERID + ")," +
//                "CONSTRAINT fk_ExtraIncome_UserData1 " +
//                "FOREIGN KEY(" + KEY_USERID + ")" +
//                "REFERENCES " + TABLE_USERDATA + "(" + KEY_USERID + ")" +
//                ");";
//        db.execSQL(CREATE_EXTRAINCOME_TABLE);
//        String CREATE_EXTRAINCOME_INDEX = "CREATE INDEX IF NOT EXISTS " + "fk_ExtraIncome_User1_idx " + "ON " + TABLE_EXTRAINCOME + "(" + KEY_USERID + "); ";
//        db.execSQL(CREATE_EXTRAINCOME_INDEX);
//        String CREATE_EXPENSE_TABLE = "CREATE TABLE " + TABLE_EXPENSE + "(" +
//                KEY_TYPE_EXPENSE + "VARCHAR(45) NOT NULL" + "," +
//                KEY_AMOUNT_EXPENSE + "DOUBLE NOT NULL" + "," +
//                KEY_EXPENSEID + "INTEGER NOT NULL" + "," +
//                KEY_USERID + "INTEGER UNIQUE NOT NULL" + "," +
//                "PRIMARY KEY(" + KEY_EXPENSEID + "," + KEY_USERID +")," +
//                "CONSTRAINT fk_Expense_UserData1 " +
//                "FOREIGN KEY(" + KEY_USERID + ")" +
//                "REFERENCES " + TABLE_USERDATA + "(" + KEY_USERID + ")" +
//                ");";
//        db.execSQL(CREATE_EXPENSE_TABLE);
//        String CREATE_EXPENSE_INDEX = "CREATE INDEX IF NOT EXISTS " + "fk_Expense_User1_idx " + "ON " + TABLE_EXPENSE + "(" + KEY_USERID + "); ";
//        db.execSQL(CREATE_EXPENSE_INDEX);
//        String CREATE_DATES_TABLE = "CREATE TABLE " + TABLE_DATES + "(" +
//                KEY_DATEWANTED + "DATE NOT NULL" + "," +
//                KEY_EXPECTEDDATE + "DATE NOT NULL" + "," +
//                KEY_DATECREATED + "DATE NOT NULL" + "," +
//                KEY_GOALID + "INTEGER NOT NULL" + "," +
//                KEY_USERID + "INTEGER UNIQUE NOT NULL" + "," +
//                "PRIMARY KEY(" + KEY_GOALID + "," + KEY_USERID + ")," +
//                "CONSTRAINT fk_Dates_Goal1 " +
//                "FOREIGN KEY(" + KEY_GOALID + "," + KEY_USERID + ")" +
//                "REFERENCES " + TABLE_GOAL + "(" + KEY_GOALID + "," + KEY_USERID + ")" +
//                ");";
//        db.execSQL(CREATE_DATES_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//// Drop older tables if existed
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDENTIALS);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERDATA);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOAL);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOB);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXTRAINCOME);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATES);
//// Creating tables again
//        onCreate(db);
//    }
//
//    //Auta ta objects prepei na svistoun apo edo kai dimiourgithoun stin main gia ton kathe pinaka kai meta na klironomithoun edo
//    User user;
//    Credentials credentials;
//    UserData userData;
//    Goal goal;
//    Item item;
//    Job job;
//    Income income;
//    Expense expense;
//    GoalDates dates;
//
//    //ADD TABLES theloun allages sta input ton klaseon
//    public void addUser() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_USERID, user.getUserID()); // get the user id from object and set it into the db
//// Inserting Row
//        db.insert(TABLE_USER, null, values);
//        db.close(); // Closing database connection
//
//
//    }
//
//    public void addCredenstials() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_USERNAME, credentials.getUsername()); // get the username from object and set it into the db
//        values.put(KEY_PASSWORD, credentials.getPassword()); // get the password from object and set it into the db
//        values.put(KEY_USERID, user.getUserID());
//
//        db.insert(TABLE_CREDENTIALS, null, values);
//        db.close(); // Closing database connection
//    }
//
//    public void addUserData() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_AREA, userData.getArea()); // get the area from object and set it into the db
//        values.put(KEY_AGE, userData.getAge()); // get the age from object and set it into the db
//        values.put(KEY_GENDER, userData.getGender()); // get the gender from object and set it into the db
//        values.put(KEY_USERID, user.getUserID());
//
//        db.insert(TABLE_USERDATA, null, values);
//        db.close(); // Closing database connection
//    }
//
//    public void addGoal(Goal goal) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_MONEYSAVED, goal.getMoneySaved()); // get the area from object and set it into the db
//        values.put(KEY_MONEYTOSAVEPERDAY, goal.getMoneyToSavePerDay()); // get the age from object and set it into the db
//        values.put(KEY_GOALID, goal.getGoalID()); // get the gender from object and set it into the db
//        values.put(KEY_USERID, user.getUserID());
//
//        db.insert(TABLE_GOAL, null, values);
//        db.close(); // Closing database connection
//    }
//
//    public void addItem() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, item.getName());
//        values.put(KEY_PRICE, item.getPrice());
//        values.put(KEY_CATEGORY, item.getCategory());
//        values.put(KEY_GOALID, goal.getGoalID());
//        values.put(KEY_USERID, user.getUserID());
//
//        db.insert(TABLE_ITEM, null, values);
//        db.close(); // Closing database connection
//    }
//
//    public void addJob() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_TYPE, job.getName());
//        values.put(KEY_SALARY, job.getAmount());
//        values.put(KEY_JOBID, job.getIncomeID());
//        values.put(KEY_USERID, user.getUserID());
//
//        db.insert(TABLE_JOB, null, values);
//        db.close(); // Closing database connection
//    }
//
//    public void addExtraIncome() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_TYPE_EXTRAINCOME, income.getName());
//        values.put(KEY_AMOUNT_EXTRAINCOME, income.getAmount());
//        values.put(KEY_EXTRID, income.getIncomeID());
//        values.put(KEY_USERID, user.getUserID());
//
//        db.insert(TABLE_EXTRAINCOME, null, values);
//        db.close(); // Closing database connection
//    }
//
//    public void addExpenses() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_TYPE_EXPENSE, expense.getName());
//        values.put(KEY_AMOUNT_EXPENSE, expense.getAmount());
//        values.put(KEY_EXPENSEID, expense.getExpenseID());
//        values.put(KEY_USERID, user.getUserID());
//
//        db.insert(TABLE_EXPENSE, null, values);
//        db.close(); // Closing database connection
//    }
//
//    public void addDates() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_DATEWANTED, dates.getDateWanted().toString());
//        values.put(KEY_EXPECTEDDATE, dates.getExpectedDate().toString());
//        values.put(KEY_DATECREATED, dates.getDateCreated().toString());
//        values.put(KEY_GOALID, goal.getGoalID());
//        values.put(KEY_USERID, user.getUserID());
//
//        db.insert(TABLE_DATES, null, values);
//        db.close(); // Closing database connection
//    }
//    // Read tables
//    public User getUser(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_USERID
//                         }, KEY_USERID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        //Must create the credentials and goal and userData first and the Create the User Class
//        //We just want the UserID from here to get it and send it to the Database for Login and SignUp purposes
//
//        int UserID = Integer.parseInt(cursor.getString(0));
//        User user = new User(getCredentials(UserID),getUserData(UserID),getGoal(UserID),Integer.parseInt(cursor.getString(0)));
//
//        return user;
//    }
//
//    public com.example.nightc.gobuy.GoBuySDK.UserClasses.Credentials getCredentials(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_CREDENTIALS, new String[] { KEY_USERNAME ,KEY_PASSWORD,KEY_USERID
//                }, KEY_USERID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        com.example.nightc.gobuy.GoBuySDK.UserClasses.Credentials credentials = new com.example.nightc.gobuy.GoBuySDK.UserClasses.Credentials(cursor.getString(0),cursor.getString(1),Integer.parseInt(cursor.getString(2)));
//
//        return credentials;
//    }
//
//    public UserData getUserData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_USERDATA, new String[] { KEY_AREA ,KEY_AGE,KEY_GENDER,KEY_USERID,KEY_STABLEINCOMES,
//                KEY_STABLEEXPENSES,KEY_SPONTANEOUSINCOMES,KEY_SPONTANEOUSEXPENSES
//                }, KEY_USERID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        //we have to add the arrayLists in the database too in a JSON file
//        //Load Json and get the ArrayList
//
//        //Example Loading the ArrayList from the DB
//        ArrayList<ShoppingInterests> ShoppingInterests = new Gson().fromJson(cursor.getString(4),new TypeToken<List<ShoppingInterests>>(){}.getName());
//        ArrayList<StableIncome> stableIncome = new Gson().fromJson(cursor.getString(5),new TypeToken<List<StableIncome>>(){}.getName());
//        ArrayList<StableExpense> stableExpense = new Gson().fromJson(cursor.getString(6),new TypeToken<List<StableExpense>>(){}.getName());
//        ArrayList<SpontaneousIncome> a2 = new Gson().fromJson(cursor.getString(7),new TypeToken<List<SpontaneousIncome>>(){}.getName());
//        ArrayList<SpontaeousExpense> a3 = new Gson().fromJson(cursor.getString(8),new TypeToken<List<SpontaeousExpense>>(){}.getName());
//
//        UserData userData = new UserData(cursor.getString(0),Short.parseShort(cursor.getString(1)),cursor.getString(2),ShoppingInterests,getJob(Integer.parseInt(cursor.getString(3))),
//                stableIncome,stableExpense,Integer.parseInt(cursor.getString(3)));
//
//        return userData;
//    }
//
//    public Goal getGoal(int id) {
//        int UserID = 0;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_GOAL, new String[] { KEY_MONEYSAVED ,KEY_MONEYTOSAVEPERDAY,KEY_GOALID,KEY_USERID
//                }, KEY_USERID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        //if there are no goals in the user database then returns the goal as null
//        if (cursor.getCount() != 0)
//            UserID = Integer.parseInt(cursor.getString(3));
//        else return goal;
//
//        Goal goal = new Goal(getItem(UserID),new IncomesAPI(getUserData(UserID).CalculateIncomeAmount(),0) //we create new Incomes and Expense API each time because they may have changed since the last time they were saved
//                ,new BillsAPI(getUserData(UserID).CalculateBillAmount()),getDates(UserID).getDateWanted(),Double.parseDouble(cursor.getString(0)),Integer.parseInt(cursor.getString(2)),
//                    Integer.parseInt(cursor.getString(3)));
//
//        return goal;
//    }
//
//    public Item getItem(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_ITEM, new String[] { KEY_NAME ,KEY_PRICE,KEY_CATEGORY,KEY_GOALID,KEY_USERID
//                }, KEY_USERID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        Item item = new Item(Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)),cursor.getString(0),cursor.getString(2),Double.parseDouble(cursor.getString(1)));
//
//        return item;
//    }
//
//    public Job getJob(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_JOB, new String[] { KEY_TYPE ,KEY_SALARY,KEY_JOBID,KEY_USERID,KEY_PAYPERIOD
//                }, KEY_USERID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        Job job = new Job(cursor.getString(0),Double.parseDouble(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),cursor.getString(4));
//
//        return job;
//    }
//
//    public Income getIncome(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_EXTRAINCOME, new String[] { KEY_TYPE_EXTRAINCOME ,KEY_AMOUNT_EXTRAINCOME,KEY_EXTRID,KEY_USERID
//                }, KEY_USERID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        Income extraIncome = new Income(cursor.getString(0),Double.parseDouble(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)));
//
//        return extraIncome;
//    }
//
//    public Expense getExpenses(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_EXPENSE, new String[] { KEY_TYPE_EXPENSE ,KEY_AMOUNT_EXPENSE,KEY_EXPENSEID,KEY_USERID
//                }, KEY_USERID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        Expense expenses = new Expense(cursor.getString(0),Double.parseDouble(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)));
//
//        return expenses;
//    }
//
//    public GoalDates getDates(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_DATES, new String[] { KEY_DATEWANTED ,KEY_EXPECTEDDATE,KEY_DATECREATED,KEY_GOALID,KEY_USERID
//                }, KEY_USERID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        GoalDates dates = new GoalDates(Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)),new LocalDate(cursor.getString(0)),new LocalDate(cursor.getString(2)));
//        dates.setExpectedDate(new LocalDate(cursor.getString(1)));
//
//        return dates;
//    }
//
//    // Deleting tables theloun allagi sta input kai theloun allages genika alla einai mia kali vasi
//    public void deleteUser() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_USER, KEY_USERID + " = ?",
//                new String[] { String.valueOf(user.getUserID()) });
//        db.close();
//    }
//
//    public void deleteCredentials() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CREDENTIALS, KEY_USERID + " = ?",
//                new String[] { String.valueOf(user.getUserID()) });
//        db.close();
//    }
//
//    public void deleteGoal() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_GOAL, KEY_GOALID + " = ?",
//                new String[] { String.valueOf(goal.getGoalID()) });
//        db.close();
//    }
//
//    public void deleteItem() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_ITEM, KEY_GOALID + " = ?",
//                new String[] { String.valueOf(item.getGoalID()) });
//        db.close();
//    }
//
//    public void deleteJob() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_JOB, KEY_JOBID + " = ?",
//                new String[] { String.valueOf(job.getIncomeID())});
//        db.close();
//    }
//
//    public void deleteExtraincome() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_EXTRAINCOME, KEY_EXTRID + " = ?",
//                new String[] { String.valueOf(income.getIncomeID()) });
//        db.close();
//    }
//
//    public void deleteExpense() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_EXPENSE, KEY_EXPENSEID + " = ?",
//                new String[] { String.valueOf(expense.getExpenseID()) });
//        db.close();
//    }
//
//    public void deleteDate() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_DATES, KEY_USERID + " = ?",
//                new String[] { String.valueOf(dates.getUserID()) });
//        db.close();
//    }
//
//
//}