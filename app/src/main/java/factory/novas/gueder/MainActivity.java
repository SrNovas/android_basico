package factory.novas.gueder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override //Esto es una anotación, indica que implementamos un método que ya existe.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Super, para llamar a la implementación de lo que ya hacía el padre. Llamamos para que se ejecute el código, además añadimos más código.
        setContentView(R.layout.activity_main); //Carga su interfaz de activity_main.

        //Muestro mi primero LOG, V de verbose.
        Log.v(MainActivity.TAG, "ola k ase?");

        //Asociamos el controlador con la vista a través del identificador.
        //Tengo que hacer un CAST, ello se hace con (Button).
        Button change2AmericanBtn = (Button) findViewById(R.id.change_to_american_btn);

        //setOnClickListener-> Hacer que un botón llame a un método. Necesita algo que llamar cuando se pulse el botón.
        //Le decimos que pasa cuando se pulsa el botón.
        //Para eso le pasamos una instancia de alguien que implementa la interfaz OnclickListener
        //Clase anónima.
        change2AmericanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeToAmericanSystem(v);

            }
        });


    }

    public void changeToAmericanSystem(View view){

        Log.v(MainActivity.TAG, "Llamo al change2AmericanSystem");

    }

}