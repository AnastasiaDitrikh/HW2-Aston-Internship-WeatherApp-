package weather.repository;

import weather.model.WeatherModel;

import java.util.List;
import java.util.Optional;

public interface AnalysisWeatherDao {
    /**
     * Получение данных о погоде по названию города
     *
     * @param city - название города
     */
    List<WeatherModel> getWeatherInfoByCityName(String city);

    /**
     * Получение информации о самом холодном городе
     */
    Optional<WeatherModel> getTheColdestWeatherInfo();

    /**
     * Получение информации о самом теплом городе
     */
    Optional<WeatherModel> getTheWarmestWeatherInfo();
}
