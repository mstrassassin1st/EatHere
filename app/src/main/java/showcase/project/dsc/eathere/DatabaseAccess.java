package showcase.project.dsc.eathere;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if (db != null){
            this.db.close();
        }
    }

    public void adduser(String username, String password, String email){
        db.execSQL("INSERT INTO MsUSER(userName, userPassword, userEmail, userEatpoints, userEatcash) VALUES('" + username + "', '" + password + "', '" + email + "'," + " 0, 0)");
    }

    public int getID(String username, String password){
        Cursor cursor = db.rawQuery("SELECT userID from MsUser WHERE userName = '" + username + "' AND userPassword = '" + password + "'", null);
        int userID = 0;
        if (cursor.moveToFirst()){
            userID = cursor.getInt(cursor.getColumnIndex("userID"));
        }
        return userID;
    }

    public String getEmail(String username, String password){
        Cursor cursor = db.rawQuery("SELECT userEmail from MsUser WHERE userName = '" + username + "' AND userPassword = '" + password + "'", new String[]{});
        String email = "";
        if (cursor.moveToFirst()){
            email = cursor.getString(cursor.getColumnIndex("userEmail"));
        }
        return email;
    }

    public String getUsername(String username, String password){
        Cursor cursor = db.rawQuery("SELECT userName from MsUser WHERE userName = '" + username + "' AND userPassword = '" + password + "'", new String[]{});
        String userName = "";
        if (cursor.moveToFirst()){
            userName = cursor.getString(cursor.getColumnIndex("userName"));
        }
        return userName;
    }

    public int getEatpoints(String username, String password){
        Cursor cursor = db.rawQuery("SELECT userEatpoints from MsUser WHERE userName = '" + username + "' AND userPassword = '" + password + "'", new String[]{});
        int eatpoints = 0;
        if (cursor.moveToFirst()){
            eatpoints = cursor.getInt(cursor.getColumnIndex("userEatpoints"));
        }
        return eatpoints;
    }

    public int getEatcash(String username, String password){
        Cursor cursor = db.rawQuery("SELECT userEatcash from MsUser WHERE userName = '" + username + "' AND userPassword = '" + password + "'", new String[]{});
        int eatcash = 0;
        if (cursor.moveToFirst()){
            eatcash = cursor.getInt(cursor.getColumnIndex("userEatcash"));
        }
        return eatcash;
    }

    public boolean loginValidation(String username, String password){
        Cursor cursor = db.rawQuery("SELECT userName, userEmail, userEatpoints, userEatcash FROM MsUser WHERE userName = '" + username + "' AND userPassword = '" + password + "'", null);
        if (cursor.getCount() > 0){
            return true;
        }
        return false;
    }


    public Cursor viewRestaurant(){
        SQLiteDatabase db1 = openHelper.getReadableDatabase();
        Cursor cursor = db1.rawQuery("SELECT restaurantID, restaurantName, restaurantDescription, restaurantAddress, restaurantOpenHour, restaurantCloseHour FROM MsRestaurant", null);

        return cursor;
    }

    public void insertHeaderTransaction(int userID, int restaurantID, int transactionQuantity, int totalTransactionPrice){
        db.execSQL("INSERT INTO HeaderSalesTransaction (userID, restaurantID, transactionQuantity, totalTransactionPrice) VALUES " +
                "('" + userID + "', '" + restaurantID + "', '" + transactionQuantity + "', '" + totalTransactionPrice + ")" );
    }

    public Cursor viewMenu(int restaurantID){
        SQLiteDatabase db1 = openHelper.getReadableDatabase();
        Cursor cursor = db1.rawQuery("SELECT menuID, restaurantID, menuName, menuDescription, menuPrice, menuStock FROM MsMenu WHERE restaurantID = '" +  restaurantID + "'", null);

        return cursor;
    }

    public void insertNewEmail(int userID, String email){
        db.execSQL("UPDATE MsUser SET userEmail = '" + email + "' WHERE userID = '" +  userID + "'");
    }

    public void insertNewUsername(int userID, String username){
        db.execSQL("UPDATE MsUser SET userName = '" + username + "' WHERE userID = '" +  userID + "'");
    }

    public void insertNewPassword(int userID, String password){
        db.execSQL("UPDATE MsUser SET userPassword = '" + password + "' WHERE userID = '" +  userID + "'");
    }
}
