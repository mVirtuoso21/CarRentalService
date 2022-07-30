package dell.carrentalservice;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class AllCarsFromUser extends AppCompatActivity {

    ListView lv;
    CarDatabaseHandler cdb = new CarDatabaseHandler(this);
    UserDatabaseHandler udb = new UserDatabaseHandler(this);
    Intent intent;
    User user;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cars_from_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar9);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        lv = findViewById(R.id.listView2);
        List <String> cars = cdb.getCarsForUser();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cars);
        lv.setAdapter(adapter);

        i = new Intent(this, UserLoggedIn.class);
        i.putExtra("username", intent.getStringExtra("username"));
        i.putExtra("password", intent.getStringExtra("password"));
        user = new User (intent.getStringExtra("username"), intent.getStringExtra("password"));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String carAsString = (String) parent.getAdapter().getItem(position);
                String [] CarNumber = carAsString.split("\n");
                String [] n = CarNumber[1].split(" ");
                Car car = cdb.getCar(n[2]);
                udb.addCarToUser(user, n[2]);
                cdb.changeRented(car);
                Toast.makeText(getApplicationContext(), "Car Rented", Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
    }
}
