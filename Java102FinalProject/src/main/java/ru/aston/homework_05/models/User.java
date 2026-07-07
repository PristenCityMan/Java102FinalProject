package ru.aston.homework_05.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Contract;

import java.util.Base64;

public class User extends BaseClass {
    private final String name;
    private final String email;
    private String password;

    @Contract(pure = true)
    @JsonCreator
    protected User(@JsonProperty("name") String name, @JsonProperty("email") String email) {
        this.name = name;
        this.email = email;
    }

    private User(Builder builder) {
        name = builder.name;
        email = builder.email;
        password = builder.password;
    }

    public String getName() {
        return name;
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

        public Builder addName(String name) {
            this.name = name;
            return this;
        }

        public Builder addPassword() {
            this.password = Base64.getEncoder().encodeToString(name.getBytes());
            return this;
        }

        public Builder addEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }

        public static Builder builder() {
            return new Builder();
        }
    }
}
