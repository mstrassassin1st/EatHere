package showcase.project.dsc.eathere;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import showcase.project.dsc.eathere.adapter.MenuAdapter;
import showcase.project.dsc.eathere.model.Cart;
import showcase.project.dsc.eathere.model.Menu;


public class MenuList extends AppCompatActivity {
    RecyclerView recyclerView;
    MenuAdapter adapter;
    ArrayList<Menu> menuList;
    FloatingActionButton btnFab;
    ArrayList<Cart> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        setTitle("Menu");

        btnFab = findViewById(R.id.fab);

        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartList = adapter.getData();

                if (cartList.size() == 0){
                    Toast.makeText(MenuList.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MenuList.this, OrderList.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dataCart", cartList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });

        menuList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_menu_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MenuAdapter(MenuList.this, menuList);
        recyclerView.setAdapter(adapter);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        Intent intent = getIntent();
        int id = intent.getIntExtra("restaurantID", 0);

        Cursor cursor = databaseAccess.viewMenu(id);

        while (cursor.moveToNext()){
            Menu menu = new Menu();
            menu.setMenuID(cursor.getInt(0));
            menu.setMenuName(cursor.getString(2));
            menu.setMenuDescription(cursor.getString(3));
            menu.setMenuPrice(cursor.getInt(4));
            menu.setMenuStock(cursor.getInt(5));
            menuList.add(menu);
        }
        cursor.close();
        databaseAccess.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cartList.clear();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MenuList.this, RestaurantList.class);
        startActivity(intent);
        finish();
    }
}