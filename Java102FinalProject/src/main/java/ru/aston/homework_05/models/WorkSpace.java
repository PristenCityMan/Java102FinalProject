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

    @Override
    public String getClassName() {
        return "WorkSpace";
    }

    @Override
    public String getFirstFieldName() {
        return "Название";
    }

    @Override
    public String getSecondFieldName() {
        return "Номер помещения";
    }

    @Override
    public String getThirdFieldName() {
        return "Номер места";
    }
}
