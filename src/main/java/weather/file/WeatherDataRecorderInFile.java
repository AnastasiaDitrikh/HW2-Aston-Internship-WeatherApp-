package weather.file;

import weather.data.ParserData;

import java.io.FileWriter;
import java.io.IOException;

public class WeatherDataRecorderInFile {
    /**
     * Создание файла и запись результатов в файл
     */
    public static void main(String[] args) {
        ParserData data = new ParserData();
        try {
            FileWriter fileWriter = new FileWriter("output.txt");

            fileWriter.write("Дата: " + data.getFormattedTime() + "\n");
            fileWriter.write("Город: " + data.getLocality() + "\n");
            fileWriter.write("Регион: " + data.getLocalityProvince() + "\n");
            fileWriter.write("Температура: " + data.getTemp() + "\n");
            fileWriter.write("Скорость ветра: " + data.getWindSpeed() + "\n");
            fileWriter.write("Направление ветра: " + data.getWindDir() + "\n");
            fileWriter.write("Давление, мм: " + data.getPressure() + "\n");
            fileWriter.write("Влажность: " + data.getHumidity() + "\n");
            fileWriter.close();

            System.out.println("Данные успешно извлечены и записаны в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл.");
            e.printStackTrace();
        }
    }
}