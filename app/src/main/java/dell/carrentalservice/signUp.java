package dell.carrentalservice;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signUp extends AppCompatActivity {

    EditText e1, e2, e3;
    UserDatabaseHandler db = new UserDatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void addUser (View view) {
        e1 = (EditText) findViewById(R.id.newUsername);
        e2 = (EditText) findViewById(R.id.newPassword);
        e3 = (EditText) findViewById(R.id.retype);
        if (e1.getText().toString().equals("") || e2.getText().toString().equals("") || e3.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Please fill all the attributes", Toast.LENGTH_LONG).show();
        else if (db.userExists(new User(e1.getText().toString(), e2.getText().toString())))
            Toast.makeText(getApplicationContext(), "This username is taken", Toast.LENGTH_LONG).show();
        else if (e2.getText().toString().equals("error"))
            Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_LONG).show();
        else {
            if (e2.getText().toString().equals(e3.getText().toString())) {
                db.addUser(new User (e2.getText().toString(), e3.getText().toString()));
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
            else
                Toast.makeText(getApplicationContext(), "Passwords don't match. Please re-type them correctly", Toast.LENGTH_LONG).show();
        }
    }
}
