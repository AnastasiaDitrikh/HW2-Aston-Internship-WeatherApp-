package user.model;

import exceptions.ValidationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidatorUser {

    /**
     * Метод проверяет корректность информации, введеной пользователем при регистрации и обновлении данных
     * @param user введеные данные пользователем
     * @throws ValidationException выбрасывается при введенных некорректных данных
     */
    public void validateUser(User user) {
        if (user.getLogin().contains(" ")) {
            throw new ValidationException("Поле логин не может быть пустым или содержать пробелы");
        } else if (!user.getEmail().contains("@")|| user.getEmail().contains(" ")){
            throw new ValidationException("Поле почты не может быть пустым или содержать пробелы, а также должно содержать символ - @");
        }
        else if (user.getLogin() == null || user.getLogin().isBlank()) {
            user.setLogin(user.getEmail());
            System.out.println("В качестве логина пользователя с id " +user.getId()+
                    " будет использоваться почта");
        }
        System.out.println("Пользователь успешно прошел валидацию");
    }
}