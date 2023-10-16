package weather.repository.impl;

import exceptions.ConnectionDoesNotExistException;
import jdbc.ConnectionManager;
import weather.model.WeatherModel;
import weather.repository.WeatherDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherDaoImpl implements WeatherDao {

    private static final WeatherDao weatherDao = new WeatherDaoImpl();

    private WeatherDaoImpl() {

    }

    public static WeatherDaoImpl getWeatherDao() {
        return (WeatherDaoImpl) weatherDao;
    }

    Connection connection = ConnectionManager.open();
    private static final String SQL_INSERT = "insert into weather (locality, localityprovince, datetime,temp, windspeed, winddir, pressure, humidity) values (?,?,?,?,?,?,?,?)";
    private static final String DELETE_BY_ID = "DELETE FROM weather WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM weather";

    /**
     * Сохранение данных о погоде
     * @param weatherModel - полученная информация с сайта
     */
    @Override
    public void add(WeatherModel weatherModel) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
            preparedStatement.setString(1, weatherModel.getLocality());
            preparedStatement.setString(2, weatherModel.getLocalityProvince());
            preparedStatement.setString(3, weatherModel.getDateTime());
            preparedStatement.setDouble(4, weatherModel.getTemp());
            preparedStatement.setDouble(5, weatherModel.getWindSpeed());
            preparedStatement.setString(6, weatherModel.getWindDir());
            preparedStatement.setDouble(7, weatherModel.getPressure());
            preparedStatement.setDouble(8, weatherModel.getHumidity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ConnectionDoesNotExistException("Соединение не установлено");
        }
    }

    /**
     * Удаление сущности из БД
     * @param id - параметр, по которому будет удаление данных и БД
     */
    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new ConnectionDoesNotExistException("Соединение не установлено");
        }
    }

    /**
     * Получение всей информации о запрашиваемой погоде
     * @return List
     */

    @Override
    public List<WeatherModel> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
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
}