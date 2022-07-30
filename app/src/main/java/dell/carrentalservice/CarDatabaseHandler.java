package dell.carrentalservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CarDatabaseHandler extends SQLiteOpenHelper {

    String table_name = "cars";
    String att_make = "Make";
    String att_model = "Model";
    String att_year = "Year";
    String att_number = "Number";
    String att_Price = "Price";
    String att_rented = "Rented";

    public CarDatabaseHandler (Context c) {super (c, "CarsDatabase", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreationQuery = "CREATE TABLE " + table_name + "( Make TEXT, Model TEXT, Year TEXT, Number TEXT PRIMARY KEY, Price TEXT, Rented TEXT)";
        db.execSQL(CreationQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void addCar (Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(att_make, car.getMake());
        c.put(att_model, car.getModel());
        c.put(att_year, car.getYear());
        c.put(att_number, car.getNumber());
        c.put(att_Price, car.getPrice());
        c.put(att_rented, String.valueOf(car.isRented()));
        db.insert(table_name, null, c);
        db.close();
    }

    public List <String> getCarsForAdmin () {
        List <String> l = new ArrayList <String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + table_name;
        Cursor c = db.rawQuery(query, null);
        String carAsString;
        while (c.moveToNext()) { //add rented attribute if needed
            carAsString = c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + "\nCar Number: " + c.getString(3) + "\nPrice: $" + c.getString(4) + " per hours" + "\nRented: " + c.getString(5);
            l.add(carAsString);
        }
        return l;
    }

    public List <String> getCarsForUser () {
        List <String> l = new ArrayList <String >();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + table_name + " WHERE Rented = ?";
        Cursor c = db.rawQuery(query, new String[]{Boolean.toString(false)});
        String carAsString;
        while (c.moveToNext()) {
            carAsString = c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + "\nCar Number: " + c.getString(3) + "\nPrice: $" + c.getString(4) + " per hours";
            l.add(carAsString);
        }
        return l;
    }

    public void removeCar (String carNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_name + " WHERE Number = " + carNumber);
    }

    public Car getCar (String carNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Car car = null;
        Cursor c = db.rawQuery("SELECT * FROM " + table_name + " WHERE Number = ?", new String[] {carNumber});
        if (c != null) {
            c.moveToFirst();
            car = new Car (c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
        }
        return car;
    }

    public void changeRented (Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("Make", car.getMake());
        c.put("Model", car.getModel());
        c.put("Year", car.getYear());
        c.put("Number", car.getNumber());
        c.put("Rented", "true");
        db.update(table_name, c, "Number = ?", new String[] {car.getNumber()});
    }

    public void returnCar (Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("Make", car.getMake());
        c.put("Model", car.getModel());
        c.put("Year", car.getYear());
        c.put("Number", car.getNumber());
        c.put("Rented", "false");
        db.update(table_name, c, "Number = ?", new String[] {car.getNumber()});
   }
}
