package ru.aston.homework_05.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Contract;

import ru.aston.homework_05.validators.EmailValidationHandler;
import ru.aston.homework_05.validators.UserValidationHandler;
import ru.aston.homework_05.validators.NameValidationHandler;
import ru.aston.homework_05.validators.ValidationException;

import java.util.Base64;
import java.util.Optional;

public class User extends BaseClassImpl {
    private final String name;
    private final String email;
    private String password;
    private UserValidationHandler<User> validationHandler;

    @Contract(pure = true)
    @JsonCreator
    public User(@JsonProperty("name") String name, @JsonProperty("email") String email) {
        this.name = name;
        this.email = email;
        setValidators();
        try {
            validationHandler.validate(this);
        } catch (ValidationException e) {
            System.out.printf("Ошибка валидации: %s, пользователь %s%n", e.getMessage(), this);
        }
    }

    private void setValidators() {
        validationHandler = new NameValidationHandler<>();
        validationHandler.setNext(new EmailValidationHandler());
    }

    private User(Builder builder, boolean shouldValidate) {
        name = builder.name;
        email = builder.email;
        password = builder.password;
        if (shouldValidate) {
            setValidators();
            try {
                validationHandler.validate(this);
            } catch (ValidationException e) {
                System.out.printf("Ошибка валидации: %s, пользователь %s%n", e.getMessage(), this);
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static String getClassName() {
        return "User";
    }

    public static String getFirstFieldName() {
        return "Имя пользователя";
    }

    public static String getSecondFieldName() {
        return "Электронная почта";
    }

    public static String getThirdFieldName() {
        return "Пароль";
    }

    @Override
    public String toString() {
        return getName() + " with an email: " + email + " has a password=" + password;
    }

    public static class Builder {
        private String name;
        private String email;
        private String password;
        private boolean shouldValidate = true;

        public Builder addName(String name) {
            this.name = name;
            return this;
        }

        public Builder addPassword(Optional<String> password) {
            this.password = password.orElseGet(() -> Base64.getEncoder().encodeToString(name.getBytes()));
            return this;
        }

        public Builder addEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder disableValidation() {
            shouldValidate = false;
            return this;
        }

        public User build() {
            return new User(this, shouldValidate);
        }

        public static Builder builder() {
            return new Builder();
        }
    }
}
