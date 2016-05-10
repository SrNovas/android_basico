package factory.novas.gueder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    
        //Accedo a las vistas.
        mRadioGroup = (RadioGroup) findViewById(R.id.units_rg);
        
        findViewById(R.id.accept_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                acceptSetting();
                
            }
        });
        
        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                cancelSettings();
                
            }
        });
        
    }

    private void cancelSettings() {



    }

    private void acceptSetting() {



    }

}
