package showcase.project.dsc.eathere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import showcase.project.dsc.eathere.adapter.OrderAdapter;
import showcase.project.dsc.eathere.model.Cart;

public class OrderList extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderAdapter adapter;
    ArrayList<Cart> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        setTitle("Confirm Order");

        Bundle bundle = getIntent().getExtras();
        cartList = (ArrayList<Cart>) bundle.getSerializable("dataCart");

        recyclerView = findViewById(R.id.rv_order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new OrderAdapter(OrderList.this, cartList);
        recyclerView.setAdapter(adapter);

        int total = adapter.getTotalPrice();
        int tax = total / 10;
        int fee = 100;
        final int grandTotal = total + tax + fee;

        TextView tvTotal, tvTax, tvFee, tvGrandTotal;
        Button btnOrder, btnCancel;

        tvTotal = findViewById(R.id.tv_order_total);
        tvTax = findViewById(R.id.tv_order_tax);
        tvFee = findViewById(R.id.tv_order_fee);
        tvGrandTotal = findViewById(R.id.tv_order_grand_total);

        btnOrder = findViewById(R.id.btn_confirm);
        btnCancel = findViewById(R.id.btn_cancel);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderList.this, Payment.class);
                intent.putExtra("paymentPrice", grandTotal);
                startActivity(intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartList.clear();
                Toast.makeText(OrderList.this, "Order canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        tvTotal.setText("Total: IDR" + String.valueOf(total));
        tvTax.setText("Tax(10%): IDR" + String.valueOf(tax));
        tvFee.setText("Admin Fee: IDR" + String.valueOf(fee));
        tvGrandTotal.setText("Grand Total : IRD" + String.valueOf(grandTotal));
    }
}
