package weather.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ParserData {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String jsonString = WeatherData.getInfo();
    JSONObject jsonObject = new JSONObject(jsonString);

    /**
     * Извлечение данных местоположения
     */
    JSONObject geoObject = jsonObject.getJSONObject("geo_object");
    JSONObject localityObject = geoObject.getJSONObject("locality");
    JSONObject provinceObject = geoObject.getJSONObject("province");
    String locality = localityObject.getString("name");
    String localityProvince = provinceObject.getString("name");

    /**
     * Извлечение и форматирование текущей даты
     */
    String dateObject = jsonObject.getString("now_dt");
    Instant instant = Instant.parse(dateObject);
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    String formattedTime = localDateTime.format(formatter);


    /**
     * Извлечение данных из объекта из прогноза
     */
    JSONArray forecastsArray = jsonObject.getJSONArray("forecasts");
    JSONObject forecastObject = forecastsArray.getJSONObject(0);
    String date = forecastObject.getString("date");

    /**
     * Извлечение данных о погоде на данный момент
     */
    JSONObject factObject = jsonObject.getJSONObject("fact");
    int temp = factObject.getInt("temp");
    int windSpeed = factObject.getInt("wind_speed");
    int pressure = factObject.getInt("pressure_mm");
    int humidity = factObject.getInt("humidity");
    String windDir = factObject.getString("wind_dir");

    public String getLocality() {
        return locality;
    }

    public String getLocalityProvince() {
        return localityProvince;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public String getDate() {
        return date;
    }

    public int getTemp() {
        return temp;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getWindDir() {
        return windDir;
    }
}
