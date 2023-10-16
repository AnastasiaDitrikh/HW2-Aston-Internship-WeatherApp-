package jdbc;

import exceptions.ConnectionDoesNotExistException;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
public final class ConnectionManager {
    private static final String PASSWORD_KEY = "db.password";
    private static final String USER_NAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";

    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USER_NAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new ConnectionDoesNotExistException("Соединение не установлено");
        }
    }
}
