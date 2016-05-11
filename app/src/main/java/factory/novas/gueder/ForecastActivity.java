package factory.novas.gueder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ForecastActivity extends AppCompatActivity {

    private final static int REQUEST_UNITS = 1;

    //La utilizo para recuperar mis datos de mi sharepreferences como para guardarlos.
    private static String PREFERENCE_UNITS = "units";

    private static final String TAG = "ForecastActivity";

    private TextView mMaxTemp;
    private TextView mMinTemp;
    private TextView mHumidity;
    private TextView mDescription;
    private ImageView mForecastImage;
    private boolean showCelsius;
    private Forecast mForecast;

    @Override //Esto es una anotación, indica que implementamos un método que ya existe.
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState); //Super, para llamar a la implementación de lo que ya hacía el padre. Llamamos para que se ejecute el código, además añadimos más código.

        setContentView(R.layout.activity_forecast); //Carga su interfaz de activity_forecast.

        //Cargamos los valores de la última vez (guardamos en SharedPreferences)
        showCelsius = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(PREFERENCE_UNITS, true); //True para los celsius.

        //Asociamos vista con controlador.
        mMaxTemp = (TextView) findViewById(R.id.max_temp);
        mMinTemp = (TextView) findViewById(R.id.min_temp);
        mHumidity = (TextView) findViewById(R.id.humidity);
        mDescription = (TextView) findViewById(R.id.forecast_description);
        mForecastImage = (ImageView) findViewById(R.id.forecast_image);

        //Creo mi modelo.
        mForecast = new Forecast(45, 14, 10, "Paece que está nublo", R.drawable.sun_cloud);

        //Este es para cuando creo la interfaz.
        setForecast(mForecast);

    }

    //Video 1 del día 3.
    //Este dice como es el menú (lo busco con CTRL + O).
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Esto estaba con el return, pero tengo que llamar primero al padre, y en el return, devolver true.
        super.onCreateOptionsMenu(menu);

        //Creo opción de menú a través de un fichero XML (menu_forecast).

        //Inflater, clases que nos permiten pasar de XML a código.
        getMenuInflater().inflate(R.menu.menu_forecast, menu);

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

            //También nos sirve para mandar datos de la 1º a la 2º pantalla. Pasamos los datos a la pantalla de ajustes.
            //Vamos a persistir el radiobutton.
            intent.putExtra(SettingsActivity.EXTRA_CURRENT_UNITS, showCelsius);

            //Con esto lanzo el intent, con ello ya me carga el activity_settings.xml
            //startActivity(intent);

            //Si quiero que una segunda actividad me devuelva datos (que ha hecho el usuario), necesito hacer un StartActivityForResult.
            //La segunda actividad (la pantalla de ajustes) nos selecciona las unidades de la temperatura. Debo crearle un campo estático
            //para exponer.
            //Le pedimos a Android que lance el intent explícito para mostrar la pantalla.
            startActivityForResult(intent, REQUEST_UNITS);

            return true;

        }

        return superReturn;

    }

    //Con este método va a ser llamado cuando regrese de una pantalla.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Con esto sé de donde vuelvo (en este caso, de la pantalla de ajustes).
        if(requestCode == REQUEST_UNITS){

            //Estoy manejando el resultado de la pantalla ajustes.
            if(resultCode == RESULT_OK){

                //Si el usuario ha elegido algo.
                //En el data vienen los datos.
                int optionSelected = data.getIntExtra("units", -1);

                if(optionSelected == R.id.celsius_rb){

                    //Se han seleccionado ºC.
                    Log.v(TAG, "Se han seleccionado ºC.");
                    showCelsius = true;

                }
                else if(optionSelected == R.id.farenheit_rb){

                    //Se han seleccionado ºF.
                    Log.v(TAG, "Se han seleccionado ºF.");
                    showCelsius = false;

                }

                /*

                //
                //Manera 1 de guardar la configuración que seleccionamos.
                //

                //Persistimos preferencias (guardar preferencias).
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

                //Entramos en modo edición de preferencias.
                SharedPreferences.Editor editor = prefs.edit();

                //Guardamos los datos.
                editor.putBoolean(PREFERENCE_UNITS, showCelsius);

                //Si no lo hago, no guarda nada.
                editor.commit();
                */

                //La otra mas común es la siguiente. Persistimos los datos de la interfaz, no los radio buttons.
                PreferenceManager.getDefaultSharedPreferences(this)
                        .edit()
                        .putBoolean(PREFERENCE_UNITS, showCelsius)
                        .commit();

                //Si lo que he pedido es el cambio de unidad, y el usuario ha dicho OK, despues de ver que uidad es,
                //refrescamos la pantalla.
                //Actualizamos la interfaz con las nuevas unidades.
                setForecast(mForecast);

                //Avisamos al usuario de que los ajustes han cambiado.
                //Esto es algo prehistórico.
                //Toast.makeText(this, "Preferencias actualizadas", Toast.LENGTH_SHORT).show();

                //1º parámetro, vista (la que contiene toda mi pantalla, es un comodín) sobre la que queremos que actúe.
                //Le digo que es el R de android, no el mío.
                Snackbar.make(findViewById(android.R.id.content), "Preferencias actualizadas", Snackbar.LENGTH_LONG).show();

            }

        }

    }

    public void setForecast(Forecast forecast) {

        float maxTemp = forecast.getMaxTemp();
        float minTemp = forecast.getMinTemp();

        if(!showCelsius){

            maxTemp = toFarenheit(maxTemp);
            minTemp = toFarenheit(minTemp);

        }

        //Muestro en la interfaz mi modelo.
        mMaxTemp.setText(String.format(getString(R.string.max_temp_label), maxTemp));
        mMinTemp.setText(String.format(getString(R.string.min_temp_label), minTemp));
        mHumidity.setText(String.format(getString(R.string.humidity_label),forecast.getHumidity()));
        mDescription.setText(forecast.getDescription());
        mForecastImage.setImageResource(forecast.getIcon());

    }

    protected static float toFarenheit(float celsius){

        return (celsius * 1.8f) + 32;

    }

}