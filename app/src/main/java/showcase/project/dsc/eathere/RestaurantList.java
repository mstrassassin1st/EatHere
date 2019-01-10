package showcase.project.dsc.eathere;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import showcase.project.dsc.eathere.adapter.RestaurantAdapter;
import showcase.project.dsc.eathere.model.Restaurant;

public class RestaurantList extends AppCompatActivity {
    RecyclerView recyclerView;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        setTitle("Restaurant List");

        restaurantList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_restaurant_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RestaurantAdapter(RestaurantList.this, restaurantList);
        recyclerView.setAdapter(adapter);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        Cursor cursor = databaseAccess.viewRestaurant();

        while (cursor.moveToNext()){
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantID(cursor.getInt(0));
            restaurant.setRestaurantName(cursor.getString(1));
            restaurant.setRestaurantDescription(cursor.getString(2));
            restaurant.setRestaurantAddress(cursor.getString(3));
            restaurant.setOpenHour(cursor.getInt(4));
            restaurant.setCloseHour(cursor.getInt(5));

            restaurantList.add(restaurant);
        }
        databaseAccess.close();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RestaurantList.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
