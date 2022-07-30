package dell.carrentalservice;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLoggedIn extends AppCompatActivity {

    Intent intent;
    UserDatabaseHandler udb = new UserDatabaseHandler(this);
    CarDatabaseHandler cdb = new CarDatabaseHandler(this);
    String rentedCarNumber;
    String carAsString;
    User user;
    TextView t;
    String none = "NONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logged_in);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        user = new User(intent.getStringExtra("username"), intent.getStringExtra("password"));
        rentedCarNumber = udb.getRentedCar(user);
        t = (TextView) findViewById(R.id.rentedCar);
        if (rentedCarNumber == null || rentedCarNumber.equals("0"))
            t.setText(none);
        else {
            carAsString = cdb.getCar(rentedCarNumber).toString();
            t.setText(carAsString);
        }
    }

    public void rentCar (View view) {
        Intent i = getIntent();
        if (t.getText().toString().equals("NONE")) {
            Intent intent = new Intent(this, AllCarsFromUser.class);
            intent.putExtra("username", i.getStringExtra("username"));
            intent.putExtra("password", i.getStringExtra("password"));
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), "You have already rented a car", Toast.LENGTH_LONG).show();
    }

    public void returnCar (View view) {
        Intent i = getIntent();
        User u = new User(i.getStringExtra("username"), i.getStringExtra("password"));
        if (t.getText().toString().equals("NONE")) {
            Toast.makeText(getApplicationContext(), "You haven't rented a car yet", Toast.LENGTH_LONG).show();
        }
        else {
            udb.removeCarForUser(u, rentedCarNumber);
            cdb.returnCar(cdb.getCar(rentedCarNumber));
            recreate();
        }
    }

    public void signOut (View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
