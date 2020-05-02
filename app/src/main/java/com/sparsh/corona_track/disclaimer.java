package com.sparsh.corona_track;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
public class disclaimer extends AppCompatActivity {
    Window window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT>=21){
            window=this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorstatusbar));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disclaimer);
    }
}