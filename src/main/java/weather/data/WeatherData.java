package weather.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherData {
    /**
     * Метод получает информацию с сайта погоды в формате строки(JSON)
     * @return метод возвращает ответ со страницы сайта
     */
    public static String getInfo(){
        String apiKey = "a7c02aae-d41d-4948-829a-1257a7a51f59";
        String header = "X-Yandex-API-Key";
        String responseFromAPI="";
        SourceData sourceInfo= new SourceData(45.043315,41.969111,
                2, "en_US", true, false);
        String url = "https://api.weather.yandex.ru/v2/forecast?lat=" + sourceInfo.getLat() +
                "&lon=" + sourceInfo.getLon() +
                "&lang=" + sourceInfo.getLang() +
                "&limit=" + sourceInfo.getLimit() +
                "&hours=" + sourceInfo.isHours() +
                "&extra=" + sourceInfo.isExtra();

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty(header, apiKey);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

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