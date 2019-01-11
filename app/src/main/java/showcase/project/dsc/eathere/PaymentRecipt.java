package showcase.project.dsc.eathere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PaymentRecipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_recipt);
        setTitle("Payment Recipt");

        Random rand = new Random();
        int reservationID = rand.nextInt(9999) + 1;

        TextView tvReservationID;
        tvReservationID = findViewById(R.id.tv_reservation_id);
        tvReservationID.setText(String.format("%04d", reservationID));

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(PaymentRecipt.this);

        Button btnBackHome;
        btnBackHome = findViewById(R.id.btn_back_home);

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentRecipt.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(PaymentRecipt.this, "Thank you for ordering using EatHere :)", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}
