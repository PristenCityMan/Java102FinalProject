package ru.aston.homework_05.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Contract;
import ru.aston.homework_05.validators.UserValidationHandler;
import ru.aston.homework_05.validators.NameValidationHandler;
import ru.aston.homework_05.validators.ValidationException;

public class WorkSpace extends BaseClassImpl {
    private final String name;
    private final int space;
    private final int seat;
    private UserValidationHandler<WorkSpace> validationHandler;

    @Contract(pure = true)
    @JsonCreator
    public WorkSpace(@JsonProperty("name") String name, @JsonProperty("space") int space, @JsonProperty("seat") int seat) {
        this.name = name;
        this.space = space;
        this.seat = seat;
        setValidator();
        try {
            validationHandler.validate(this);
        } catch (ValidationException e) {
            System.out.printf("Ошибка валидации: %s, рабочее место %s%n", e.getMessage(), this);
        }
    }

    public WorkSpace(Builder builder, boolean shouldValidate) {
        this.name = builder.name;
        this.space = builder.space;
        this.seat = builder.seat;
        setValidator();
        if (shouldValidate) {
            try {
                validationHandler.validate(this);
            } catch (ValidationException e) {
                System.out.printf("Ошибка валидации: %s, рабочее место %s%n", e.getMessage(), this);
            }
        }
    }

    private void setValidator() {
        validationHandler = new NameValidationHandler<>();
    }

    public String getName() {
        return name;
    }

    public int getSeat() {
        return seat;
    }

    public int getSpace() {
        return space;
    }

    public static String getClassName() {
        return "WorkSpace";
    }

    public static String getFirstFieldName() {
        return "Название";
    }

    public static String getSecondFieldName() {
        return "Номер помещения";
    }

    public static String getThirdFieldName() {
        return "Номер места";
    }

    @Override
    public String toString() {
        return name + ", space=" + space + ", seat=" + seat;
    }

    public static class Builder {
        private String name;
        private int space;
        private int seat;
        private boolean shouldValidate = true;

        public WorkSpace.Builder addName(String name) {
            this.name = name;
            return this;
        }

        public WorkSpace.Builder addSeat(int seat) {
            this.seat = seat;
            return this;
        }

        public WorkSpace.Builder addSpace(int space) {
            this.space = space;
            return this;
        }

        public WorkSpace.Builder disableValidation() {
            shouldValidate = false;
            return this;
        }

        public WorkSpace build() {
            return new WorkSpace(this, shouldValidate);
        }

        public static WorkSpace.Builder builder() {
            return new WorkSpace.Builder();
        }
    }
}
