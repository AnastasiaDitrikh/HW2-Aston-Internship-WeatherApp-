package weather.service;

import weather.model.WeatherModel;
import weather.repository.WeatherDao;
import weather.repository.impl.WeatherDaoImpl;

import java.util.List;

/**
 * Погодный сервис, реализован singleton
 */
public class WeatherService {


    private static final WeatherService weatherService = new WeatherService();

    private WeatherService() {

    }

    public static WeatherService getWeatherService() {
        return weatherService;
    }

    WeatherDao weatherDao = WeatherDaoImpl.getWeatherDao();

    /**
     * Метод сохраняет полученную информацию о погоде в БД
     */
    public void save(WeatherModel weatherModel) {
        weatherDao.add(weatherModel);
    }

    /**
     * Метод удаляет информацию о погоде из БД по id
     *
     * @param id - уникальный идентификатор
     */
    public void delete(Long id) {
        weatherDao.delete(id);
    }

    /**
     * Метод получает всю информацию о запросах погоды из БД
     *
     * @return лист моделей WeatherModel
     */
    public List<WeatherModel> findAll() {
        return weatherDao.findAll();
    }
}