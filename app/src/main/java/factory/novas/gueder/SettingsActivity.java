package factory.novas.gueder;

import android.content.Intent;
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

        //A huevo, esto es del sistema.
        setResult(RESULT_CANCELED);

        finish(); //Esto es un pop.

    }

    private void acceptSetting() {

        //Creamos una especie de diccionario para comunicar 2 actividades.
        Intent returnIntent = new Intent();

        //Con los EXTRAS damos datos al intent, me invento el "units". El valor del 2º parámetro es un
        //entero con el valor del radioButton que hemos seleccionado.
        returnIntent.putExtra("units", mRadioGroup.getCheckedRadioButtonId());

        setResult(RESULT_OK, returnIntent);

        finish();

    }

}
