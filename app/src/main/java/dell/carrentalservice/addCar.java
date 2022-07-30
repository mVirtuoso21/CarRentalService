package dell.carrentalservice;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addCar extends AppCompatActivity {

    EditText e1, e2, e3, e4, e5;
    String s1, s2, s3, s4, s5;
    CarDatabaseHandler db = new CarDatabaseHandler(this);
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void submitCar (View view) {
        e1 = (EditText) findViewById(R.id.carMake);
        s1 = e1.getText().toString();
        e2 = (EditText) findViewById(R.id.carModel);
        s2 = e2.getText().toString();
        e3 = (EditText) findViewById(R.id.carYear);
        s3 = e3.getText().toString();
        e4 = (EditText) findViewById(R.id.carNumber);
        s4 = e4.getText().toString();
        e5 = (EditText) findViewById(R.id.carPrice);
        s5 = e5.getText().toString();

        if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") ||s5.equals(""))
            Toast.makeText(getApplicationContext(), "Please fill all the attributes", Toast.LENGTH_LONG).show();
        else if (s4.equals("0"))
            Toast.makeText(getApplicationContext(), "0 is not accepted as a car number", Toast.LENGTH_LONG).show();
        else if (s5.equals("0"))
            Toast.makeText(getApplicationContext(), "0 is not accepted as a price", Toast.LENGTH_LONG).show();
        else {
            car = new Car(s1, s2, s3, s4, s5);
            db.addCar(car);
            Toast.makeText(getApplicationContext(), "Car added successfully", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, AdminLoggedIn.class));
        }
    }
}
