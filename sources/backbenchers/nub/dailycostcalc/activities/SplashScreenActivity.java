package backbencers.nub.dailycostcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import backbencers.nub.dailycostcalc.C0374R;

public class SplashScreenActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0374R.layout.activity_splash_screen);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
