package dell.carrentalservice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class userLogin extends AppCompatActivity {

    UserDatabaseHandler db = new UserDatabaseHandler(this);
    EditText e1, e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void login (View view) {
        boolean userExists;
        String password;
        Intent i = new Intent(this, UserLoggedIn.class);
        e1 = (EditText) findViewById(R.id.username);
        e2 = (EditText) findViewById(R.id.userPassowrd);
        if (e1.getText().toString().equals("") || e2.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Please fill all the attributes", Toast.LENGTH_LONG).show();
        else {
            User user = new User (e1.getText().toString(), e2.getText().toString());
            if (!db.userExists(user))
                Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_LONG).show();
            else {
                password = db.getPassword(user);
                if (!e2.getText().toString().equals(password))
                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_LONG).show();
                else {
                    i.putExtra("username", user.getUsername());
                    i.putExtra("password", user.getPassword());
                    startActivity(i);
                }
            }
        }
    }
}
