package work;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class WeatherDataExtractor {

    public static void main(String[] args) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String jsonString = WeatherForecast.getInfo();
        try {
            // Создание файла для записи результатов
            FileWriter fileWriter = new FileWriter("output.txt");
            JSONObject jsonObject = new JSONObject(jsonString);

            // Извлечение данных местоположения
            JSONObject geoObject = jsonObject.getJSONObject("geo_object");
            JSONObject localityObject = geoObject.getJSONObject("locality");
            JSONObject provinceObject = geoObject.getJSONObject("province");
            String locality = localityObject.getString("name");
            String localityProvince = provinceObject.getString("name");

            // Извлечение и форматирование текущей даты
            String dateObject = jsonObject.getString("now_dt");
            Instant instant = Instant.parse(dateObject);
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
            String formattedTime = localDateTime.format(formatter);


            // Извлечение данных из объекта из прогноза
            JSONArray forecastsArray = jsonObject.getJSONArray("forecasts");
            JSONObject forecastObject = forecastsArray.getJSONObject(0);
            String date = forecastObject.getString("date");

            // Извлечение данных о погоде на данный момент
            JSONObject factObject = jsonObject.getJSONObject("fact");
            int temp = factObject.getInt("temp");
            int windSpeed = factObject.getInt("wind_speed");
            int pressure = factObject.getInt("pressure_mm");
            int humidity = factObject.getInt("humidity");
            String windDir = factObject.getString("wind_dir");


            // Запись данных в файл
            fileWriter.write("Дата: " + formattedTime + "\n");
            fileWriter.write("Город: " + locality + "\n");
            fileWriter.write("Регион: " + localityProvince + "\n");
            fileWriter.write("Температура: " + temp + "\n");
            fileWriter.write("Скорость ветра: " + windSpeed + "\n");
            fileWriter.write("Направление ветра: " + windDir + "\n");
            fileWriter.write("Давление, мм: " + pressure + "\n");
            fileWriter.write("Влажность: " + humidity + "\n");
            fileWriter.write("\n");
            fileWriter.write("Прогноз: " + "\n");
            fileWriter.write("Дата: " + date + "\n");

            // Закрытие файла
            fileWriter.close();

            System.out.println("Данные успешно извлечены и записаны в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл.");
            e.printStackTrace();
        }
    }
}