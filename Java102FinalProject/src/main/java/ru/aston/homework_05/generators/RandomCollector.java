package ru.aston.homework_05.generators;

import ru.aston.homework_05.models.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomCollector extends BaseCollectionGenerator<User> {
    protected final int size;

    public RandomCollector(int size) {
        this.size = size;
    }

    @Override
    List<User> generate() throws NullPointerException {
        // TODO: Fakers
        return IntStream.range(0, size).mapToObj(i -> {
            String name = getRandomNameFromCollection();
            return User.Builder.builder()
                    .addName(name)
                    .addEmail(name + "@" + emailDomainName)
                    .addPassword()
                    .build();
        }).collect(Collectors.toList());
    }

    private String getRandomNameFromCollection() {
        var names = List.of("Сергей", "Вадим", "Максим");
        return names.get((int)(Math.random() * names.size()));
    }

    private final String emailDomainName = "mail.ru";
}
