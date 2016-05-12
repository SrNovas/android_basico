package factory.novas.gueder;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//Un fragment es como una actividad, corto el código y me lo traigo aquí.
//No crea la interfaz en el OnCreate, si no en el OnCreateView.
public class ForecastFragment extends Fragment { //Elijo el Fragment adnroid.app para versiones modernas.

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    //Un fragment crea la interfaz en onCreateView.
    //Necesita devolver la vista raíz.
    //getContext() solo funciona en versiones >= 23, si n hay que usar getActivity()
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        //Me creo una vista. El container es de la que va a colgar.
        View root = inflater.inflate(R.layout.fragment_forecast, container, false);

        //Aquí meto lo que había en el onCreate.
        //Cargamos los valores de la última vez (guardamos en SharedPreferences)
        //Fragment tiene acceso a su actividad con getContext().
        showCelsius = PreferenceManager.getDefaultSharedPreferences(getActivity())
                .getBoolean(PREFERENCE_UNITS, true); //True para los celsius.

        //Asociamos vista con controlador.
        //No encuentra el findViewById por que busca desde la vista raíz,en los fragment tenemos que decirle
        //a partir de donde, por eso le ponemos root.
        mMaxTemp = (TextView) root.findViewById(R.id.max_temp);
        mMinTemp = (TextView) root.findViewById(R.id.min_temp);
        mHumidity = (TextView) root.findViewById(R.id.humidity);
        mDescription = (TextView) root.findViewById(R.id.forecast_description);
        mForecastImage = (ImageView) root.findViewById(R.id.forecast_image);

        //Creo mi modelo.
        mForecast = new Forecast(45, 14, 10, "Paece que está nublo", R.drawable.sun_cloud);

        //Este es para cuando creo la interfaz.
        setForecast(mForecast);

        return root;

    }

    //Este dice como es el menú (lo busco con CTRL + O).
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_forecast, menu);
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
            Intent intent = new Intent(getActivity(), SettingsActivity.class);

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



    //En el fragment es publico, no protegido..
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Con esto sé de donde vuelvo (en este caso, de la pantalla de ajustes).
        if(requestCode == REQUEST_UNITS){

            //Guardamos el valor previo de showCelsius por si el usuario quiere deshacer.
            //Si uso la variable dentro de una clase anónimca, automáticamente le pone el final.
            final boolean oldShowCelsius = showCelsius;

            //Estoy manejando el resultado de la pantalla ajustes.
            if(resultCode == Activity.RESULT_OK){

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
                PreferenceManager.getDefaultSharedPreferences(getActivity())
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
                //findViewById no es un método que forme parte del fragment, así que tengo que guardarme la referencia de la vista
                //lo sustituyo por getView()
                Snackbar.make(getView(), R.string.updated_preferences, Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                showCelsius = oldShowCelsius;

                                PreferenceManager
                                        .getDefaultSharedPreferences(getActivity()) //Un this (a pelo) es de la clase anónima OnClickListener(), por eso peta. Para ello tengo que poner el ForecastActivity.this, para referirme al this del ForecasActivity.
                                        .edit()
                                        .putBoolean(PREFERENCE_UNITS, showCelsius)
                                        .commit();

                                //Actualizamos la interfaz con las nuevas unidades.
                                setForecast(mForecast);

                            }
                        })
                        .show();

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
