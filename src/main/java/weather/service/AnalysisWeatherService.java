package weather.service;

import weather.model.WeatherModel;
import weather.repository.AnalysisWeatherDao;
import weather.repository.impl.AnalysisWeatherDaoImpl;

import java.util.List;
import java.util.Optional;

public class AnalysisWeatherService {
    private static final AnalysisWeatherService analysisWeatherService = new AnalysisWeatherService();

    private AnalysisWeatherService() {
    }

    public static AnalysisWeatherService getAnalysisWeatherService() {
        return analysisWeatherService;
    }

    AnalysisWeatherDao analysisWeatherDao = AnalysisWeatherDaoImpl.getAnalysisWeatherDao();

    public List<WeatherModel> getWeatherInfoByCityName(String city) {
        return analysisWeatherDao.getWeatherInfoByCityName(city);
    }

    /**
     * Получение информации о самом холодном городе
     */
    public Optional<WeatherModel> getTheColdestWeatherInfo() {
        return analysisWeatherDao.getTheColdestWeatherInfo();
    }

    /**
     * Получение информации о самом жарком городе
     */
    public Optional<WeatherModel> getTheWarmestWeatherInfo() {
        return analysisWeatherDao.getTheWarmestWeatherInfo();
    }
}