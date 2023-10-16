package weather.repository.impl;

import exceptions.ConnectionDoesNotExistException;
import jdbc.ConnectionManager;
import weather.model.WeatherModel;
import weather.repository.AnalysisWeatherDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnalysisWeatherDaoImpl implements AnalysisWeatherDao {
    private static final AnalysisWeatherDao analysisWeatherDao = new AnalysisWeatherDaoImpl();

    private AnalysisWeatherDaoImpl() {

    }

    public static AnalysisWeatherDaoImpl getAnalysisWeatherDao() {
        return (AnalysisWeatherDaoImpl) analysisWeatherDao;
    }

    Connection connection = ConnectionManager.open();
    private static final String SQL_SELECT_WEATHER_INFO_BY_CITY_NAME = "select * from weather where locality ilike ?";
    private static final String SQL_SELECT_INFO_ABOUT_CITY_WHERE_THE_WARMEST_WEATHER = "select locality, max(temp) as temp from weather\n" +
            "group by locality\n" +
            "order by max(temp) desc\n" +
            "limit 1";
    private static final String SQL_SELECT_INFO_ABOUT_CITY_WHERE_THE_COLDEST_WEATHER = "select locality, min(temp) as temp from weather\n" +
            "group by locality\n" +
            "order by min(temp) \n" +
            "limit 1";

    public List<WeatherModel> getWeatherInfoByCityName(String city) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_WEATHER_INFO_BY_CITY_NAME)) {
            preparedStatement.setString(1, city);
            var resultSet = preparedStatement.executeQuery();
            List<WeatherModel> weatherModels = new ArrayList<>();
            while (resultSet.next()) {
                weatherModels.add(getWeather(resultSet));
            }
            return weatherModels;
        } catch (SQLException throwables) {
            throw new ConnectionDoesNotExistException("Соединение не установлено");
        }
    }

    @Override
    public Optional<WeatherModel> getTheColdestWeatherInfo() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_INFO_ABOUT_CITY_WHERE_THE_COLDEST_WEATHER)) {
            var resultSet = preparedStatement.executeQuery();
            WeatherModel weatherModel = null;
            if (resultSet.next()) {
                weatherModel = getCityAndTempModel(resultSet);
            }
            return Optional.ofNullable(weatherModel);
        } catch (SQLException throwables) {
            throw new ConnectionDoesNotExistException("Соединение не установлено");
        }
    }

    @Override
    public Optional<WeatherModel> getTheWarmestWeatherInfo() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_INFO_ABOUT_CITY_WHERE_THE_WARMEST_WEATHER)) {
            var resultSet = preparedStatement.executeQuery();
            WeatherModel weatherModel = null;
            if (resultSet.next()) {
                weatherModel = getCityAndTempModel(resultSet);
            }
            return Optional.ofNullable(weatherModel);
        } catch (SQLException throwables) {
            throw new ConnectionDoesNotExistException("Соединение не установлено");
        }
    }

    private WeatherModel getWeather(ResultSet resultSet) throws SQLException {
        return new WeatherModel(resultSet.getLong("id"),
                resultSet.getString("locality"),
                resultSet.getString("localityprovince"),
                resultSet.getString("datetime"),
                resultSet.getDouble("temp"),
                resultSet.getDouble("windspeed"),
                resultSet.getString("winddir"),
                resultSet.getDouble("pressure"),
                resultSet.getDouble("humidity"));
    }

    private WeatherModel getCityAndTempModel(ResultSet resultSet) throws SQLException {
        return new WeatherModel(
                resultSet.getString("locality"),
                resultSet.getDouble("temp"));
    }
}
