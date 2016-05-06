package factory.novas.gueder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override //Esto es una anotación, indica que implementamos un método que ya existe.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Super, para llamar a la implementación de lo que ya hacía el padre. Llamamos para que se ejecute el código, además añadimos más código.
        setContentView(R.layout.activity_main); //Carga su interfaz de activity_main.

        //Muestro mi primero LOG, V de verbose.
        Log.v(MainActivity.TAG, "ola k ase?");

    }
}
