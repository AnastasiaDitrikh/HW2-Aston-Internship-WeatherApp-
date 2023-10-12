package weather.data;
/**
 * Модель необходимой информации о местоположении и дополнительные параметры
 * обязательные поля
 *lat=<широта>
 *lon=<долгота>
 *необязательные поля
 *[lang=<язык ответа>]
 *[limit=<срок прогноза>]
 *[hours=<наличие почасового прогноза>]
 *[extra=<подробный прогноз осадков>]
 * Примечание:
 * Допустимые значения lang:
 *     «ru_RU» — русский язык для домена России.
 *     «ru_UA» — русский язык для домена Украины.
 *     «uk_UA» — украинский язык для домена Украины.
 *     «be_BY» — белорусский язык для домена Беларуси.
 *     «kk_KZ» — казахский язык для домена Казахстана.
 *     «tr_TR» — турецкий язык для домена Турции.
 *     «en_US» — международный английский.
 */
public class SourceData {

    private double lat;
    private double lon;
    private int limit;
    private String lang;
    private boolean hours;
    private boolean extra;


    public SourceData(double lat, double lon, int limit, String lang, boolean hours, boolean extra) {
        this.lat = lat;
        this.lon = lon;
        this.limit = limit;
        this.lang = lang;
        this.hours = hours;
        this.extra = extra;
    }

    public SourceData(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int getLimit() {
        return limit;
    }

    public String getLang() {
        return lang;
    }

    public boolean isHours() {
        return hours;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setHours(boolean hours) {
        this.hours = hours;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }
}
