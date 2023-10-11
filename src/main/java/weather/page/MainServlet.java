package weather.page;

import org.json.JSONObject;
import weather.WeatherInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@WebServlet("/weather")
public class MainServlet extends HttpServlet {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String jsonString = WeatherInfo.getInfo();
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


    // Извлечение данных о погоде на данный момент
    JSONObject factObject = jsonObject.getJSONObject("fact");
    int temp = factObject.getInt("temp");
    int windSpeed = factObject.getInt("wind_speed");
    int pressure = factObject.getInt("pressure_mm");
    int humidity = factObject.getInt("humidity");
    String windDir = factObject.getString("wind_dir");


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }


        session.setAttribute("Дата: ", formattedTime);
        session.setAttribute("Город: ", locality);
        session.setAttribute("Регион: ", localityProvince);
        session.setAttribute("Температура: ", temp);
        session.setAttribute("Скорость ветра: ", windSpeed);
        session.setAttribute("Направление ветра: ", windDir);
        session.setAttribute("Давление, мм: ", pressure);
        session.setAttribute("Влажность: ", humidity);


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();


        printWriter.write("Date: " + formattedTime + "<br>");
        printWriter.write("City: " + locality + "<br>");
        printWriter.write("Region: " + localityProvince + "<br>");
        printWriter.write("Temperature: " + temp + "<br>");
        printWriter.write("Wind Speed: " + windSpeed + "<br>");
        printWriter.write("Wind Direction: " + windDir + "<br>");
        printWriter.write("Pressure: " + pressure + "<br>");
        printWriter.write("Humidity: " + humidity + "<br>");
        printWriter.write("Page was visited " + visitCounter + " times." + "<br>");
        printWriter.close();
    }
}