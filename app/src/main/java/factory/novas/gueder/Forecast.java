package factory.novas.gueder;

/**
 * Created by Adrian on 9/5/16.
 */
public class Forecast {

    //En Android a los atributos se les pone una "m" delante para indicar que son miembros de la clase.
    //Cuando el atributo es estático, en lugar de "m" sería una "s".
    private float mMaxTemp;
    private float mMinTemp;
    private float mHumidity;
    private String mDescription;
    private int mIcon;

    //Constructor, lo hago con CMD + N.
    //Preferencias -> Editor -> Code Style -> Java -> Code Generator
    //Así quitamos a los argumentos y variables locales la "m", manteníendola los atributos.
    public Forecast(float maxTemp, float minTemp, float humidity, String description, int icon) {

        mMaxTemp = maxTemp;
        mMinTemp = minTemp;
        mHumidity = humidity;
        mDescription = description;
        mIcon = icon;

    }

    public float getMaxTemp() {
        return mMaxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        mMaxTemp = maxTemp;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }

    public float getHumidity() {
        return mHumidity;
    }

    public void setHumidity(float humidity) {
        mHumidity = humidity;
    }

    public float getMinTemp() {
        return mMinTemp;
    }

    public void setMinTemp(float minTemp) {
        mMinTemp = minTemp;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

}
