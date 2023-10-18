package user.repository;

import exceptions.ConnectionDoesNotExistException;
import exceptions.NotFoundException;
import jdbc.ConnectionManager;
import user.model.User;
import user.model.ValidatorUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final UserDao userDao = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getUserDao() {
        return (UserDaoImpl) userDao;
    }


    Connection connection = ConnectionManager.open();

    //изменить запросы
    private static final String SQL_INSERT = "insert into user (login, email, password) values (?,?,?)";
    private static final String DELETE_BY_ID = "DELETE FROM user WHERE id=?";
    //проверить на существование пользователя
    private static final String UPDATE_USER = "UPDATE user SET login = ?, email = ?, password = ? WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM user";
    private static final String GET_BY_ID = FIND_ALL + " WHERE id = ?";

    /**
     * Сохранение данных о пользователе
     *
     * @param user - введеная информация о пользователе
     */
    @Override
    public void add(User user) {
        ValidatorUser.validateUser(user);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ConnectionDoesNotExistException("Соединение не установлено");
        }
    }

    /**
     * Функция для админа. Получение данных о всех пользователях
     */
    @Override
    public List<User> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(getUser(resultSet));
            }
            return users;
        } catch (SQLException throwables) {
            throw new ConnectionDoesNotExistException("Соединение не установлено");
        }
    }

    /**
     * Удаление сущности пользователя из БД
     *
     * @param id - параметр, по которому будет удаление данных и БД
     */
    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new ConnectionDoesNotExistException("Соединение не установлено");
        }
    }


    /**
     * Обновление сущности пользователя в БД
     *
     * @param user - новые данные о пользователе
     * @throws NotFoundException, если обновляемого пользователя нет в базе
     */
    @Override
    public void update(User user) {
        Optional<User> updatedUser = getById(user.getId());
        if (updatedUser.isEmpty()) {
            throw new NotFoundException("Данного пользователя нет в базе данных");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new ConnectionDoesNotExistException("Соединение не установлено");
        }
    }

    /**
     * Получение сущности пользователя из БД по id
     *
     * @param id - индентификатор пользователя
     */
    @Override
    public Optional<User> getById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = getUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException throwables) {
            throw new ConnectionDoesNotExistException("Соединение не установлено");
        }
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong("id"),
                resultSet.getString("login"),
                resultSet.getString("email"),
                resultSet.getString("password"));
    }
}