package ru.aston.homework_05.generators;

import ru.aston.homework_05.models.IBaseClass;
import ru.aston.homework_05.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleCollector extends RandomCollector {
    public ConsoleCollector(int size) {
        super(size);
    }

    @Override
    List<IBaseClass> generate() throws NullPointerException {
        Scanner in = new Scanner(System.in);
        List<IBaseClass> collection = new ArrayList<>();
        for(int i = 0; i < size; ++i) {
            System.out.print("Введите имя пользователя, пожалуйста: ");
            String name = in.nextLine();
            System.out.print("Введите электронную почту пользователя, пожалуйста: ");
            String email = in.nextLine();
            collection.add(User.Builder.builder()
                    .addName(name.trim())
                    .addEmail(email)
                    .addPassword()
                    .build());
        }
        in.close();
        return collection;
    }
}
