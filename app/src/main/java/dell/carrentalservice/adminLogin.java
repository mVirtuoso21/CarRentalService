package dell.carrentalservice;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class adminLogin extends AppCompatActivity {

    EditText e1, e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void login (View view) {
        e1 = findViewById(R.id.adminNameField);
        e2 = findViewById(R.id.adminPassword);
        if (e1.getText().toString().equals("") || e2.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(),"Please fill all the attributes",Toast.LENGTH_LONG).show();
        else if (!(e1.getText().toString().equals("admin") && e2.getText().toString().equals("admin")))
                Toast.makeText(getApplicationContext(),"Wrong attributes",Toast.LENGTH_LONG).show();
        else {
            Intent i = new Intent(this, AdminLoggedIn.class);
            startActivity(i);
        }
    }
}
