package factory.novas.gueder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class ForecastActivity extends AppCompatActivity {

    private static final String TAG = "ForecastActivity";

    //Implicitamente hace un forecastImage = NULL.
    private ImageView forecastImage;

    @Override //Esto es una anotación, indica que implementamos un método que ya existe.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Super, para llamar a la implementación de lo que ya hacía el padre. Llamamos para que se ejecute el código, además añadimos más código.
        setContentView(R.layout.activity_forecast); //Carga su interfaz de activity_forecast.

        //Muestro mi primero LOG, V de verbose.
        Log.v(ForecastActivity.TAG, "ola k ase?");

        //Accedemos al ImageView a través de su id. El final hace que no pueda ser modificado.
        forecastImage = (ImageView) findViewById(R.id.forecast_image);

        //Asociamos el controlador con la vista a través del identificador.
        //Tengo que hacer un CAST, ello se hace con (ToggleButton).
        final ToggleButton forecastSystemButton = (ToggleButton) findViewById(R.id.forecast_system_btn);

        //setOnClickListener-> Hacer que un botón llame a un método. Necesita algo que llamar cuando se pulse el botón.
        //Le decimos que pasa cuando se pulsa el botón.
        //Para eso le pasamos una instancia de alguien que implementa la interfaz OnclickListener
        //Clase anónima.
        // Para llamar al OnClick de esta instancia uso el this, tengo que hacer un implements
        //de OnClickListener
        forecastSystemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(forecastSystemButton.isChecked()) {

                    changeToEuropeanSystem(v);

                }
                else {

                    changeToAmericanSystem(v);

                }
            }
        });

    }

    public void changeToAmericanSystem(View view){

        Log.v(ForecastActivity.TAG, "Llamo al change2AmericanSystem");

        //Esto hace un cambio de imagen a la pasada por parámetro.
        forecastImage.setImageResource(R.drawable.offline_weather);

    }

    public void changeToEuropeanSystem(View view){

        Log.v(ForecastActivity.TAG, "Llamo al change2EuropeanSystem");

        //Esto hace un cambio de imagen a la pasada por parámetro.
        forecastImage.setImageResource(R.drawable.offline_weather2);

    }

}