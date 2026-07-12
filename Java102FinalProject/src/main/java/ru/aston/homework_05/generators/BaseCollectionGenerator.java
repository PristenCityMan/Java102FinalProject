package ru.aston.homework_05.generators;

import java.util.List;

public abstract class BaseCollectionGenerator<T> {
    abstract List<T> generate();
}
