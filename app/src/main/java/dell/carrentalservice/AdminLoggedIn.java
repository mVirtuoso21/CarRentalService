package dell.carrentalservice;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AdminLoggedIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_logged_in);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void addCar (View view) {
        startActivity (new Intent(this, addCar.class));
    }

    public void removeCar (View view) {
        startActivity (new Intent(this, AllCarsFromAdmin.class));
    }

    public void signOut (View view) { startActivity (new Intent(this, MainActivity.class)); }

    public void viewUsers(View view) { startActivity (new Intent(this, viewAllUsers.class)); }
}
