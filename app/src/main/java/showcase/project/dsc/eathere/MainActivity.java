package showcase.project.dsc.eathere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ChangeEmail.ChangeEmailDialog, ChangeUsername.ChangeUsernameDialog, ChangePassword.ChangePasswordDialog {
    private DataPassing dataPassing = DataPassing.getInstance();
    private String username;
    private DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
    private TextView tvName, tvEatpoints, tvEatcash, tvCurrentEmail, tvCurrentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Home");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button;
        CardView cvEmail, cvUsername, cvPassword;

        tvCurrentEmail = findViewById(R.id.currentEmail);
        tvName = findViewById(R.id.tv_name);
        tvEatpoints = findViewById(R.id.tv_eatpoints);
        tvEatcash = findViewById(R.id.tv_eatcash);
        tvCurrentUsername = findViewById(R.id.currentUsername);

        cvEmail = findViewById(R.id.cv_change_email);
        cvUsername = findViewById(R.id.cv_change_username);
        cvPassword = findViewById(R.id.cv_change_password);

        cvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeEmailDialog();
            }
        });

        cvUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUsernameDialog();
            }
        });

        cvPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePasswordDialog();
            }
        });

        username = dataPassing.getUsername();
        String email = dataPassing.getEmail();


        int eatpoints = dataPassing.getEatpoints();
        int eatcash = dataPassing.getEatcash();

        tvName.setText(username);
        tvCurrentEmail.setText(email);
        tvCurrentUsername.setText(username);


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

    private void changeEmailDialog(){
        ChangeEmail changeEmail =  new ChangeEmail();
        changeEmail.show(getSupportFragmentManager(), "changeEmail");
    }

    private void changeUsernameDialog(){
        ChangeUsername changeUsername = new ChangeUsername();
        changeUsername.show(getSupportFragmentManager(), "changeUsername");
    }

    private void changePasswordDialog(){
        ChangePassword changePassword = new ChangePassword();
        changePassword.show(getSupportFragmentManager(), "changePassword");
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
    public void applyNewEmail(String newEmail) {
        tvCurrentEmail.setText(newEmail);
        dataPassing.setEmail(newEmail);
        databaseAccess.open();
        databaseAccess.insertNewEmail(dataPassing.getUserID(), newEmail);
        Toast.makeText(this, "Email has been updated", Toast.LENGTH_SHORT).show();
        databaseAccess.close();
    }

    @Override
    public void applyNewUsername(String newUsername) {
        tvCurrentUsername.setText(newUsername);
        dataPassing.setUsername(newUsername);
        databaseAccess.open();
        databaseAccess.insertNewUsername(dataPassing.getUserID(), newUsername);
        Toast.makeText(this, "Username has been updated", Toast.LENGTH_SHORT).show();
        databaseAccess.close();
    }

    @Override
    public void applyNewPassword(String newPassword) {
        databaseAccess.open();
        databaseAccess.insertNewPassword(dataPassing.getUserID(), newPassword);
        Toast.makeText(this, "Password has been updated", Toast.LENGTH_SHORT).show();
        databaseAccess.close();
    }
}
