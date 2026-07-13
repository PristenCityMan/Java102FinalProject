package ru.aston.homework_05.validators;

import ru.aston.homework_05.models.User;

public class UsernameValidationHandler extends UserValidationHandler {
    @Override
    public void validate(User user) throws ValidationException {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new ValidationException("User name cannot be empty");
        }

        if (!user.getName().matches("^[A-Za-zА-Яа-я]+(?:[ '-][A-Za-zА-Яа-я]+)*$")) {
            throw new ValidationException("Invalid user format");
        }

        validateNext(user);
    }
}
