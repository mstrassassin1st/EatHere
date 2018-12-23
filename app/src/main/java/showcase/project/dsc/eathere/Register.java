package showcase.project.dsc.eathere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    EditText confirmpw;
    Button btnRegister;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.usernameReg);
        email = (EditText) findViewById(R.id.emailReg);
        password = (EditText) findViewById(R.id.passwordReg);
        confirmpw = (EditText) findViewById(R.id.confpasswordReg);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usn = username.getText().toString();
                String em = email.getText().toString();
                String pw = password.getText().toString();
                String confpw = confirmpw.getText().toString();

                if(usn.equals("") || em.equals("") || pw.equals("") || confpw.equals("")){
                    Toast.makeText(Register.this, "All forms must be filled!", Toast.LENGTH_SHORT).show();
                }else if(!isValidEmail(em)){
                    Toast.makeText(Register.this, "Email invalid!", Toast.LENGTH_SHORT).show();
                    email.setText("");
                    password.setText("");
                    confirmpw.setText("");
                } else if(confpw.equals(pw)) {
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    intent.putExtra("username", usn);
                    intent.putExtra("password", pw);
                    intent.putExtra("email", em);
                    startActivity(intent);
                }else{
                    Toast.makeText(Register.this, "Password and confirm passwords not match!", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    confirmpw.setText("");
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
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
