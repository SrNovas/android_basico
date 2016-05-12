package factory.novas.gueder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class ForecastActivity extends AppCompatActivity {

    //Como se queda vacío, le decimos que cargue el layout de ActivityForecast.
    //El menú no se ve por que los fragments si tienen menús, tienen que decírselo a la actividad que los contiene.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
    }
}