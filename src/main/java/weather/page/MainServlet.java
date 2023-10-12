package weather.page;

import weather.data.ParserData;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/weather")
public class MainServlet extends HttpServlet {
    ParserData data = new ParserData();

    /**
     * Метод формирует response на get запрос
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }

        session.setAttribute("Дата: ", data.getFormattedTime());
        session.setAttribute("Город: ", data.getLocality());
        session.setAttribute("Регион: ", data.getLocalityProvince());
        session.setAttribute("Температура: ", data.getTemp());
        session.setAttribute("Скорость ветра: ", data.getWindSpeed());
        session.setAttribute("Направление ветра: ", data.getWindDir());
        session.setAttribute("Давление, мм: ", data.getPressure());
        session.setAttribute("Влажность: ", data.getHumidity());


        resp.setContentType("text/html,charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();


        printWriter.write("Date: " + data.getFormattedTime() + "<br>");
        printWriter.write("City: " + data.getLocality() + "<br>");
        printWriter.write("Region: " + data.getLocalityProvince() + "<br>");
        printWriter.write("Temperature: " + data.getTemp() + "<br>");
        printWriter.write("Wind Speed: " + data.getWindSpeed() + "<br>");
        printWriter.write("Wind Direction: " + data.getWindDir() + "<br>");
        printWriter.write("Pressure: " + data.getPressure() + "<br>");
        printWriter.write("Humidity: " + data.getHumidity() + "<br>");
        printWriter.write("Page was visited " + visitCounter + " times." + "<br>");
        printWriter.close();
    }
}