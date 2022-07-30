package dell.carrentalservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseHandler extends SQLiteOpenHelper {

    private String table_name = "Users";
    private String att_username = "Username";
    private String att_password = "Password";
    private String att_rentedCar = "RentedCar";

    public UserDatabaseHandler (Context c) { super(c, "usersDatabase", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creationQuery = "CREATE TABLE " + table_name + "( Username TEXT PRIMARY KEY, Password TEXT, RentedCar TEXT)";
        db.execSQL(creationQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void addUser (User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(att_username, user.getUsername());
        c.put(att_password, user.getPassword());
        db.insert(table_name, null, c);
        db.close();
    }

    public String getPassword (User user) {
        String password = "error";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Users WHERE Username=?", new String[]{user.getUsername()});
        if (c != null) {
            c.moveToFirst();
            password = c.getString(1);
            return password;
        }
        else
            return password;
    }

    public boolean userExists (User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Users WHERE Username=?", new String[]{user.getUsername()});
        if (c != null) {
            try {
                if (c.moveToFirst())
                    return true;
            }
            catch (CursorIndexOutOfBoundsException e) {return false;}
        }
        return false;
    }

    public String getRentedCar (User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM Users WHERE Username = ?";
        Cursor c = db.rawQuery(query, new String[] {user.getUsername()});
        if (c != null) {
            c.moveToFirst();
            return c.getString(2);
        }
        return null;
    }

    public void addCarToUser (User user, String carNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("Username", user.getPassword());
        c.put("Password", user.getPassword());
        c.put("RentedCar", carNumber);
        db.update(table_name, c, "username = ?", new String[] {user.getUsername()});
    }

    public void removeCarForUser (User user, String carNumber) {
        if (!carNumber.equals("0")) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues c = new ContentValues();
            c.put("Username", user.getUsername());
            c.put("Password", user.getPassword());
            c.put("RentedCar", 0);
            db.update(table_name, c, "username = ?", new String[]{user.getUsername()});
        }
    }

    public List viewAllUsers () {
        List <String>  l = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + table_name;
        Cursor c = db.rawQuery(query, null);
        String userAsString;
        while (c.moveToNext()) {
            userAsString = c.getString(0);
            l.add(userAsString);
        }
        return l;
    }
}
