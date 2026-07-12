package ru.aston.homework_05.validators;

import ru.aston.homework_05.models.User;

public class EmailValidationHandler extends UserValidationHandler {
    @Override
    public void validate(User user) throws ValidationException {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email cannot be empty");
        }

        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidationException("Invalid email format");
        }

        validateNext(user);
    }
}
