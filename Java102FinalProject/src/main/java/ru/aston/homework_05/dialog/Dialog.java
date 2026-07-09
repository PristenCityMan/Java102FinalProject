package ru.aston.homework_05.dialog;

import jdk.jshell.spi.ExecutionControl;
import ru.aston.homework_05.generators.BaseCollectionGenerator;
import ru.aston.homework_05.generators.CollectionGeneratorClient;
import ru.aston.homework_05.generators.ConsoleCollector;
import ru.aston.homework_05.generators.FileCollector;
import ru.aston.homework_05.generators.RandomCollector;
import ru.aston.homework_05.models.User;
import ru.aston.homework_05.models.WorkSpace;

import java.util.List;
import java.util.Scanner;

public class Dialog {
    private static final String TYPE_FILLING_TEXT = """
            Выберите вариант заполнения
            1: Из файла
            2: Случайно
            3: Вручную""";
    private static final String LENGTH_TEXT = "Выберите длину массива:";
    private static final String EXIT_TEXT = "Для выхода из программы выберете 0, для повторения работы любое другое число.";

    public static void dialog() {
        System.out.println("Вас приветствует программа сортировки классов.\n " + "Выбирайте вариант из предложенных.");
        while (true) {
            int classType = answerTaker("""
                    Выберите класс:
                    1: %s
                    2: %s""".formatted(User.getClassName(), WorkSpace.getClassName()));

            int typeFilling = answerTaker(TYPE_FILLING_TEXT);

            int length = answerTaker(LENGTH_TEXT);

            List<User> users = get1stClassCollection(typeFilling, length);
            try {
                List<WorkSpace> workSpaces = get2ndClassCollection(typeFilling, length);
            } catch (ExecutionControl.NotImplementedException nie) {

            }

            answerTaker("""
                    Выберите поле для сортировки:
                    1: %s
                    2: %s
                    3: %s""".formatted(classType == 1 ? User.getFirstFieldName() : WorkSpace.getFirstFieldName(),
                    classType == 1 ? User.getSecondFieldName() : WorkSpace.getSecondFieldName(),
                    classType == 1 ? User.getThirdFieldName() : WorkSpace.getThirdFieldName()));


            // TODO: как сделать сортировку. Что передаём, что возвращаем?
            if (answerTaker(EXIT_TEXT) == 0) {
                break;
            }
        }
    }

    private static int answerTaker(String message) {
        System.out.println(message);
        int choice;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                return choice;
            } else {
                System.out.println("Некорректные данные. Повторите ввод.");
            }
        }
    }

    private static List<User> get1stClassCollection(int fillType, int size) {
        BaseCollectionGenerator<User> generator = null;
        switch (fillType) {
            case 1 -> generator = new FileCollector<>("%s%ss.json".formatted(FILES_DIRECTORY, User.getClassName().toLowerCase()));
            case 2 -> generator = new RandomCollector(size);
            case 3 -> generator = new ConsoleCollector(size);
        }

        CollectionGeneratorClient<User> client = new CollectionGeneratorClient<>(generator);
        return client.get();
    }

    private static List<WorkSpace> get2ndClassCollection(int fillType, int size)
            throws ExecutionControl.NotImplementedException {
        BaseCollectionGenerator<WorkSpace> generator = null;
        switch (fillType) {
            case 1 -> generator = new FileCollector<>("%s%ss.json".formatted(FILES_DIRECTORY, WorkSpace.getClassName().toLowerCase()));
            case 2 ->
                    throw new ExecutionControl.NotImplementedException("Метод случайной генерации рабочих мест не реализован");
            case 3 ->
                    throw new ExecutionControl.NotImplementedException("Метод генерации рабочих мест из консоли не реализован");
        }

        CollectionGeneratorClient<WorkSpace> client = new CollectionGeneratorClient<>(generator);
        return client.get();
    }

    private static final String FILES_DIRECTORY = "src/main/resources/";
}