package factory.novas.gueder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        //Muestro en la interfaz mi modelo.
        mMaxTemp.setText(String.format(getString(R.string.max_temp_label), forecast.getMaxTemp()));
        mMinTemp.setText(String.format(getString(R.string.min_temp_label), forecast.getMinTemp()));
        mHumidity.setText(String.format(getString(R.string.humidity_label),forecast.getHumidity()));
        mDescription.setText(forecast.getDescription());
        mForecastImage.setImageResource(forecast.getIcon());

    }

}