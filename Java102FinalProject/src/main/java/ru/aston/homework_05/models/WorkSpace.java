package ru.aston.homework_05.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Contract;

public class WorkSpace extends BaseClass {
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
}
