package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherInfo {
    /**
     * Метод получает информацию с сайта погоды в формате строки(JSON)
     * @return метод возвращает ответ со страницы сайта
     */
    public static String getInfo(){

        String apiKey = "a7c02aae-d41d-4948-829a-1257a7a51f59";
        double lat=45.043315;
        double lon=41.969111;
        int limit = 2;
        String lang = "ru_RU";
        boolean hours = true;
        boolean extra = false;
        String responseFromAPI="";


        String url = "https://api.weather.yandex.ru/v2/forecast?lat=" + lat +
                "&lon=" + lon +
                "&lang=" + lang +
                "&limit=" + limit +
                "&hours=" + hours +
                "&extra=" + extra;

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Yandex-API-Key", apiKey);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuffer response = new StringBuffer();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return response.toString();
            } else {
                System.out.println("Error: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseFromAPI;
    }
}