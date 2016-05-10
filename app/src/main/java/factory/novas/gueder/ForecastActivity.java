package factory.novas.gueder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ForecastActivity extends AppCompatActivity {

    private static final String TAG = "ForecastActivity";

    private TextView mMaxTemp;
    private TextView mMinTemp;
    private TextView mHumidity;
    private TextView mDescription;
    private ImageView mForecastImage;

    @Override //Esto es una anotación, indica que implementamos un método que ya existe.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Super, para llamar a la implementación de lo que ya hacía el padre. Llamamos para que se ejecute el código, además añadimos más código.
        setContentView(R.layout.activity_forecast); //Carga su interfaz de activity_forecast.

        //Asociamos vista con controlador.
        mMaxTemp = (TextView) findViewById(R.id.max_temp);
        mMinTemp = (TextView) findViewById(R.id.min_temp);
        mHumidity = (TextView) findViewById(R.id.humidity);
        mDescription = (TextView) findViewById(R.id.forecast_description);
        mForecastImage = (ImageView) findViewById(R.id.forecast_image);

        //Creo mi modelo.
        Forecast forecast = new Forecast(45, 14, 10, "Paece que está nublo", R.drawable.sun_cloud);

        setForecast(forecast);

    }

    //Este dice como es el menú (lo busco con CTRL + O).
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Esto estaba con el return, pero tengo que llamar primero al padre, y en el return, devolver true.
        super.onCreateOptionsMenu(menu);

        //Creo opción de menú a través de un fichero XML (menu_settings).

        //Inflater, clases que nos permiten pasar de XML a código.
        getMenuInflater().inflate(R.menu.menu_settings, menu);

        return true;
    }

    //Para manejar que pasa cuando se presiona una de las opciones de menú.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Devuelve la llamada a super.
        boolean superReturn = super.onOptionsItemSelected(item);

        //Si presiono la opcion menu_show_settings, lanzamos SettingsActivity.
        if(item.getItemId() == R.id.menu_show_settings) {

            //Intent crea un evento, con ello saco la actividad. Indica explicitamente quien lo maneja.
            //Las actividades son hijas de la clase context, por ello le paso el this, por el otro párametro le paso explicitamente la clase.
            Intent intent = new Intent(this, SettingsActivity.class);

            //Con esto lanzo el intent, con ello ya me carga el activity_settings.xml
            startActivity(intent);

            return true;

        }

        return superReturn;

    }

    public void setForecast(Forecast forecast) {

        //Muestro en la interfaz mi modelo.
        mMaxTemp.setText(String.format(getString(R.string.max_temp_label), forecast.getMaxTemp()));
        mMinTemp.setText(String.format(getString(R.string.min_temp_label), forecast.getMinTemp()));
        mHumidity.setText(String.format(getString(R.string.humidity_label),forecast.getHumidity()));
        mDescription.setText(forecast.getDescription());
        mForecastImage.setImageResource(forecast.getIcon());

    }

}