package ru.aston.homework_05.validators;

public abstract class UserValidationHandler<T> {
    protected UserValidationHandler<T> next;

    public void setNext(UserValidationHandler<T> next) {
        this.next = next;
    }

    public abstract void validate(T user) throws ValidationException;

    protected void validateNext(T user) throws ValidationException {
        if (next != null) {
            next.validate(user);
        }
    }
}
