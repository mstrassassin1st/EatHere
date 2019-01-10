package showcase.project.dsc.eathere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ChangeEmail.EmailDialogListener {
    private DataPassing dataPassing = DataPassing.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Home");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvName, tvEatpoints, tvEatcash;
        Button button;
        CardView cardView;

        tvName = findViewById(R.id.tv_name);
        tvEatpoints = findViewById(R.id.tv_eatpoints);
        tvEatcash = findViewById(R.id.tv_eatcash);
        cardView = findViewById(R.id.cv_change_email);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        String username = dataPassing.getUsername();
        int eatpoints = dataPassing.getEatpoints();
        int eatcash = dataPassing.getEatcash();
        tvName.setText(username);
        tvEatpoints.setText("Eatpoints: " + String.valueOf(eatpoints));
        tvEatcash.setText("Eatcash: " + String.valueOf(eatcash));

        button = findViewById(R.id.btn_lets_go);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showcase.project.dsc.eathere.MainActivity.this, RestaurantList.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void openDialog(){
        ChangeEmail changeEmail = new ChangeEmail();
        changeEmail.show(getSupportFragmentManager(), "email");
    }


    public boolean shouldAllowBack(){
        return false;
    }
    public void doNothing(){

    }

    @Override
    public void onBackPressed() {
        if (!shouldAllowBack()) {
            doNothing();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void applyText(String email) {
        String newEmail = email;
        dataPassing.setEmail(email);
    }
}
