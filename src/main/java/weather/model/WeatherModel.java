package weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Модель информации о погоде с сайта
 */
public class WeatherModel {
    private long id;
    private String locality;
    private String localityProvince;
    private String dateTime;
    private double temp;
    private double windSpeed;
    private String windDir;
    private double pressure;
    private double humidity;

    public WeatherModel(String locality, String localityProvince, String dateTime, double temp, double windSpeed, String windDir, double pressure, double humidity) {
        this.locality = locality;
        this.localityProvince = localityProvince;
        this.dateTime = dateTime;
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public WeatherModel(String locality, double temp) {
        this.locality = locality;
        this.temp = temp;
    }
}