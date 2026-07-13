package ru.aston.homework_05.generators;

import java.util.List;

public abstract class BaseCollectionGenerator<T> {
    protected int size;
    abstract List<T> generate();
}
