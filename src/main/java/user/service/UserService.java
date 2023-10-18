package user.service;

import user.model.User;
import user.repository.UserDao;
import user.repository.UserDaoImpl;

import java.util.List;

public class UserService {

    private static final UserService userService = new UserService();

    private UserService() {

    }

    public static UserService getUserService() {
        return userService;
    }

    UserDao userDao = UserDaoImpl.getUserDao();
    /**
     * Метод выводит весь список пользователей
     */
    public List<User> findAll() {
        return userDao.findAll();
    }
    /**
     * Метод сохраняет полученную информацию о пользователе в БД
     */
    public void add(User user) {
        userDao.add(user);
    }
    /**
     * Метод обновляет  информацию о пользователе в БД
     */
    public void update(User user) {
        userDao.update(user);
    }
    /**
     * Метод удаляет сущность пользователя  из БД по id
     */
    public void delete(Long id) {
        userDao.delete(id);
    }
}