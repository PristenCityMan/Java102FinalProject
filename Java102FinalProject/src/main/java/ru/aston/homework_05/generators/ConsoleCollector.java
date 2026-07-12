package ru.aston.homework_05.generators;

import ru.aston.homework_05.models.BaseClass;
import ru.aston.homework_05.models.User;
import ru.aston.homework_05.models.WorkSpace;
import ru.aston.homework_05.validators.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleCollector<T extends BaseClass> extends BaseCollectionGenerator<T> {
    protected final String type;

    public ConsoleCollector(int size, String type) {
        this.size = size;
        this.type = type;
    }

    @Override
    List<T> generate() {
        Scanner in = new Scanner(System.in);
        List<T> collection = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            if (type.equals(User.getClassName())) {
                System.out.printf("%s: ", User.getFirstFieldName());
                String name = in.nextLine();
                System.out.printf("%s: ", User.getSecondFieldName());
                String email = in.nextLine();
                try {
                    collection.add((T) User.Builder.builder()
                            .addName(name.trim())
                            .addEmail(email)
                            .addPassword()
                            .build());
                } catch (ValidationException validationException) {
                    System.out.println("Некорректные данные. Повторите ввод, пожалуйста.");
                }
            } else if (type.equals(WorkSpace.getClassName())) {
                System.out.printf("%s: ", WorkSpace.getFirstFieldName());
                String name = in.nextLine();
                System.out.printf("%s: ", WorkSpace.getSecondFieldName());
                var workspaceBuilder = WorkSpace.Builder.builder().addName(name);
                try {
                    int space = Integer.parseInt(in.nextLine());
                    workspaceBuilder.addSpace(space);
                    System.out.printf("%s: ", WorkSpace.getThirdFieldName());
                    int seat = Integer.parseInt(in.nextLine());
                    workspaceBuilder.addSeat(seat);
                } catch (NumberFormatException e) {
                    System.out.println("Некорректные данные. Повторите ввод, пожалуйста.");
                } finally {
                    var workSpace = workspaceBuilder.build();
                    if (workSpace != null) {
                        collection.add((T) workSpace);
                    }
                }
            }
        }
        in.close();
        return collection;
    }
}
