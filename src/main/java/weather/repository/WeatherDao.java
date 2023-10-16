package weather.repository;


import weather.model.WeatherModel;

import java.util.List;

public interface WeatherDao {

    List<WeatherModel> findAll();

    void add(WeatherModel weatherModel);

    void delete(Long id);
}