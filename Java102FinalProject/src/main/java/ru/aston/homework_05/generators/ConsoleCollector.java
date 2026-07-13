package ru.aston.homework_05.generators;

import ru.aston.homework_05.models.BaseClass;
import ru.aston.homework_05.models.User;
import ru.aston.homework_05.models.WorkSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleCollector<T extends BaseClass> extends RandomCollector<T> {
    public ConsoleCollector(int size, String type) {
        super(size, type);
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
                System.out.printf("%s: ", User.getThirdFieldName());
                String password = in.nextLine();
                collection.add((T) User.Builder.builder()
                        .addName(name.trim())
                        .addEmail(email)
                        .addPassword(Optional.of(password))
                        .build());
            } else if (type.equals(WorkSpace.getClassName())) {
                System.out.printf("%s: ", WorkSpace.getFirstFieldName());
                String name = in.nextLine();
                var workspaceBuilder = WorkSpace.Builder.builder().addName(name);
                do {
                    try {
                        System.out.printf("%s: ", WorkSpace.getSecondFieldName());
                        workspaceBuilder.addSpace(Integer.parseInt(in.nextLine()));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректные данные. Повторите ввод, пожалуйста.");
                    }
                } while (true);

                do {
                    try {
                        System.out.printf("%s: ", WorkSpace.getThirdFieldName());
                        workspaceBuilder.addSeat(Integer.parseInt(in.nextLine()));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректные данные. Повторите ввод, пожалуйста.");
                    }
                } while (true);

                var workSpace = workspaceBuilder.build();
                if (workSpace != null) {
                    collection.add((T) workSpace);
                }
            }
        }
        return collection;
    }
}
