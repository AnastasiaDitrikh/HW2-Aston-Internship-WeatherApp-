package weather.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Модель необходимой информации о местоположении и дополнительные параметры
 * обязательные поля
 * lat=<широта>
 * lon=<долгота>
 * необязательные поля
 * [lang=<язык ответа>]
 * [limit=<срок прогноза>]
 * [hours=<наличие почасового прогноза>]
 * [extra=<подробный прогноз осадков>]
 * Примечание:
 * Допустимые значения lang:
 * «ru_RU» — русский язык для домена России.
 * «ru_UA» — русский язык для домена Украины.
 * «uk_UA» — украинский язык для домена Украины.
 * «be_BY» — белорусский язык для домена Беларуси.
 * «kk_KZ» — казахский язык для домена Казахстана.
 * «tr_TR» — турецкий язык для домена Турции.
 * «en_US» — международный английский.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SourceData {

    private double lat;
    private double lon;
    private String lang;
    private int limit;
    private boolean hours;
    private boolean extra;

    public SourceData(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public SourceData(double lat, double lon, String lang) {
        this.lat = lat;
        this.lon = lon;
        this.lang = lang;
    }
}
