package ru.aston.homework_05.validators;

import ru.aston.homework_05.models.User;

public class EmailValidationHandler extends UserValidationHandler {
    @Override
    public void validate(User user) throws ValidationException {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new ValidationException("Электронная почта не может быть пустой");
        }

        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidationException("Неверный формат электронной почты");
        }

        validateNext(user);
    }
}
