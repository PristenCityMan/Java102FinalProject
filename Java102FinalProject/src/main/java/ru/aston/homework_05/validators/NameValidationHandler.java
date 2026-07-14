package ru.aston.homework_05.validators;

import ru.aston.homework_05.models.BaseClass;

public class NameValidationHandler<T extends BaseClass> extends UserValidationHandler<T> {
    @Override
    public void validate(T user) throws ValidationException {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new ValidationException("Имя не может быть пустым");
        }

        if (!user.getName().matches("^[A-Za-zА-Яа-я+.]+(?:[ '-][A-Za-zА-Яа-я.]+)*$")) {
            throw new ValidationException("Неверный формат имени");
        }

        validateNext(user);
    }
}
