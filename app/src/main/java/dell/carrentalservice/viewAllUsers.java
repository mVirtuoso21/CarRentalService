package dell.carrentalservice;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class viewAllUsers extends AppCompatActivity {

    ListView lv;
    UserDatabaseHandler db = new UserDatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar11);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        lv = findViewById(R.id.allUsers);
        db = new UserDatabaseHandler(this);
        List users = db.viewAllUsers();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);
        lv.setAdapter(adapter);
    }
}
