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

public class AllCarsFromAdmin extends AppCompatActivity {

    ListView lv;
    CarDatabaseHandler db = new CarDatabaseHandler(this);
    String rented;
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cars_from_admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        lv = findViewById(R.id.listView1);
        db = new CarDatabaseHandler(this);
        List <String> cars = db.getCarsForAdmin();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cars);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //remove a car using a dialog to confirm deletion after checking if it is not rented
                String carAsString = (String) parent.getAdapter().getItem(position);
                String[] CarNumber = carAsString.split("\n");
                String[] n = CarNumber[1].split(" ");
                car = db.getCar(n[2]);
                rented = String.valueOf(car.isRented());
                if (rented == "true"){
                    Toast.makeText(getApplicationContext(), "Car is rented, and cannot be removed", Toast.LENGTH_LONG).show();
                }
                else {
                    db.removeCar(n[2]);
                    Toast.makeText(getApplicationContext(), "Car Removed", Toast.LENGTH_LONG).show();
                }
                finish();
                startActivity(getIntent());
            }
        });
    }
}
