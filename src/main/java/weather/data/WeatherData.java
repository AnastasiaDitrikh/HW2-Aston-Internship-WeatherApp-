package weather.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherData {
    SourceData sourceInfo = new SourceData(37.98, 23.73,
            "en_US");
    String apiKey = "a7c02aae-d41d-4948-829a-1257a7a51f59";
    String header = "X-Yandex-API-Key";
    /**
     *Информацию с сайта погоды можно получить по широте и долготе местоположения города/местности
     * с указанием дополнительных параметров, напримерб по часам, количество дней
     * В закомментированном коде подготовлена ссылка для этого
     */
//    SourceData sourceInfo= new SourceData(45.043315,41.969111,
//            "en_US", 2,true, false);


//    String url = "https://api.weather.yandex.ru/v2/forecast?"+"lat=" + sourceInfo.getLat() +
//            "&lon=" + sourceInfo.getLon() +
//            "&lang=" + sourceInfo.getLang() +
//            "&limit=" + sourceInfo.getLimit() +
//            "&hours=" + sourceInfo.isHours() +
//            "&extra=" + sourceInfo.isExtra();
    /**
     * Информацию с сайта погоды можно получить по широте и долготе местоположения города/местности
     */
    String url = "https://api.weather.yandex.ru/v2/forecast?" + "lat=" + sourceInfo.getLat() +
            "&lon=" + sourceInfo.getLon() +
            "&lang=" + sourceInfo.getLang();

    /**
     * Метод получает информацию с сайта погоды в формате строки(JSON)
     *
     * @return метод возвращает ответ со страницы сайта
     */
    public String getInfo() {
        String responseFromAPI = "";
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