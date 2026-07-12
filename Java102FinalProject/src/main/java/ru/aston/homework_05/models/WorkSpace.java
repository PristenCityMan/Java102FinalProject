package ru.aston.homework_05.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Contract;

public class WorkSpace extends BaseClassImpl {
    private final String name;
    private final int space;
    private final int seat;

    @Contract(pure = true)
    @JsonCreator
    public WorkSpace(@JsonProperty("name") String name, @JsonProperty("space") int space, @JsonProperty("seat") int seat) {
        this.name = name;
        this.space = space;
        this.seat = seat;
    }

    public WorkSpace(Builder builder) {
        this.name = builder.name;
        this.space = builder.space;
        this.seat = builder.seat;
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

        public WorkSpace build() {
            return new WorkSpace(this);
        }

        public static WorkSpace.Builder builder() {
            return new WorkSpace.Builder();
        }
    }
}
