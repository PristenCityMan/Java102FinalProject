package ru.aston.homework_05.generators;

import ru.aston.homework_05.models.IBaseClass;
import ru.aston.homework_05.models.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomCollector extends BaseCollectionGenerator<IBaseClass> {
    protected final int size;

    public RandomCollector(int size) {
        this.size = size;
    }

    @Override
    List<IBaseClass> generate() throws NullPointerException {
        // TODO: псевдослучайное заполнение, например via Fakers
        return IntStream.range(0, size).mapToObj(i -> {
            String name = names.get((int)(Math.random() * size));
            return User.Builder.builder()
                    .addName(name)
                    .addEmail(name + "@" + emailDomainName)
                    .addPassword()
                    .build();
        }).collect(Collectors.toList());
    }

    private static List<String> names = List.of("Сергей", "Вадим", "Максим");
    private final String emailDomainName = "mail.ru";
}
