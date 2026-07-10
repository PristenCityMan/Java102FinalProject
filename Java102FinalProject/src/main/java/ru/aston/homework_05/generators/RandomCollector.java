package ru.aston.homework_05.generators;

import ru.aston.homework_05.models.BaseClass;
import ru.aston.homework_05.models.User;
import ru.aston.homework_05.models.WorkSpace;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomCollector<T extends BaseClass> extends BaseCollectionGenerator<T> {
    protected final int size;
    protected final String type;

    public RandomCollector(int size, String type) {
        this.size = size;
        this.type = type;
    }

    @Override
    List<T> generate() {
        // TODO: Fakers
        return IntStream.range(0, size).mapToObj(i -> {
            if (type.equals(User.getClassName())) {
                String name = getRandomNameFromCollection();
                return (T) User.Builder.builder()
                        .addName(name)
                        .addEmail(name + "@" + emailDomainName)
                        .addPassword()
                        .build();
            } else if (type.equals(WorkSpace.getClassName())) {
                return (T) WorkSpace.Builder.builder()
                        .addSpace((int) (Math.random() * 10))
                        .addSeat((int) (Math.random() * 100))
                        .build();
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }

    private String getRandomNameFromCollection() {
        var names = List.of("Сергей", "Вадим", "Максим");
        return names.get((int) (Math.random() * names.size()));
    }

    private final String emailDomainName = "mail.ru";
}
