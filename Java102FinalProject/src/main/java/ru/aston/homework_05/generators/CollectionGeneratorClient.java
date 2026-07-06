package ru.aston.homework_05.generators;

import ru.aston.homework_05.models.IBaseClass;

import java.util.List;

public class CollectionGeneratorClient<T extends IBaseClass> {
    private final BaseCollectionGenerator<T> generator;

    public CollectionGeneratorClient(BaseCollectionGenerator<T> generator) {
        this.generator = generator;
    }

    public List<T> get() throws NullPointerException {
        if (generator != null) {
            return generator.generate();
        }

        throw new NullPointerException("Не задан генератор коллекции");
    }
}
