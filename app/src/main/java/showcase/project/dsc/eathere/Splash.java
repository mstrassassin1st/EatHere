package showcase.project.dsc.eathere;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //temp
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 1.5s = 1500ms
                Intent intent = new Intent(showcase.project.dsc.eathere.Splash.this, Login.class);
                startActivity(intent);
            }
        }, 1500);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
