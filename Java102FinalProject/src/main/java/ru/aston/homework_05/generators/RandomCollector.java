package ru.aston.homework_05.generators;

import net.datafaker.Faker;
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
        var faker = new Faker();
        return IntStream.range(0, size).mapToObj(i -> {
            if (type.equals(User.getClassName())) {
                String name = faker.name().fullName();
                return (T) User.Builder.builder()
                        .addName(name)
                        .addEmail(faker.internet().emailAddress())
                        .addPassword()
                        .build();
            } else if (type.equals(WorkSpace.getClassName())) {
                return (T) WorkSpace.Builder.builder()
                        .addName(faker.address().cityName())
                        .addSpace((int) (Math.random() * 10) + 1)
                        .addSeat((int) (Math.random() * 100) + 1)
                        .build();
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }
}
