package showcase.project.dsc.eathere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();
                String uname = username.getText().toString();
                String pw = password.getText().toString();
                if(uname.equals("") || pw.equals("")){
                    Toast.makeText(showcase.project.dsc.eathere.Login.this,"Username and passwords empty!", Toast.LENGTH_SHORT).show();
                }
                else if(databaseAccess.loginValidation(uname, pw) == false){
                    Toast.makeText(showcase.project.dsc.eathere.Login.this, "Username or password wrong!", Toast.LENGTH_SHORT).show();
                }
                else{
                    DataPassing dataPassing = DataPassing.getInstance();
                    dataPassing.setUsername(databaseAccess.getUsername(uname, pw));
                    dataPassing.setEmail(databaseAccess.getEmail(uname, pw));
                    dataPassing.setEatcash(databaseAccess.getEatcash(uname, pw));
                    dataPassing.setEatpoints(databaseAccess.getEatpoints(uname, pw));

                    Intent intent = new Intent(showcase.project.dsc.eathere.Login.this, MainActivity.class);
                    startActivity(intent);
                }
                databaseAccess.close();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showcase.project.dsc.eathere.Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public boolean shouldAllowBack(){
        return false;
    }
    public void doNothing(){
        finish();
        moveTaskToBack(true);
    }

    @Override
    public void onBackPressed() {
        if (!shouldAllowBack()) {
            doNothing();
        } else {
            super.onBackPressed();
        }
    }
}
