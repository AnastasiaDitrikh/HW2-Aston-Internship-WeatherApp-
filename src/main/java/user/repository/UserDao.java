package user.repository;


import user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    /**
     * Метод получает весь список пользователей из БД
     */
    List<User> findAll();
    /**
     * Метод сохраняет полученную информацию о пользователе в БД
     */
    void add(User user);
    /**
     * Метод обновляет  информацию о пользователе в БД
     */
    void update(User user);
    /**
     * Метод удаляет сущность пользователя  из БД по id
     */
    void delete(Long id);

    Optional<User> getById(Long id);
}