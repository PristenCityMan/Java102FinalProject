package ru.aston.homework_05.dialog;

import ru.aston.homework_05.generators.BaseCollectionGenerator;
import ru.aston.homework_05.generators.CollectionGeneratorClient;
import ru.aston.homework_05.generators.ConsoleCollector;
import ru.aston.homework_05.generators.FileCollector;
import ru.aston.homework_05.generators.RandomCollector;
import ru.aston.homework_05.models.IBaseClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dialog {
    public static void dialog() {
        boolean isAlwaysRunning = true;
        int classType, typeFilling, length, sortField;
        final String TYPE_FILLING_TEXT = """
                Выберите вариант заполнения
                1: Из файла
                2: Случайно
                3: Вручную""";
        final String LENGTH_TEXT = "Выберите длину массива:";
        final String EXIT = "Для выхода из программы выберете 0, для повторения работы любое другое число.";

        System.out.println("Вас приветствует программа сортировки классов.\n " + "Выбирайте вариант из предложенных.");
        while (isAlwaysRunning) {
            classType = answerTaker("""
                    Выберите класс:
                    1: Class1.getName()
                    2: Class2.getName()""");

            typeFilling = answerTaker(TYPE_FILLING_TEXT);

            length = answerTaker(LENGTH_TEXT);

            List<IBaseClass> items = new ArrayList<>(length);
            switch (classType) {
                case 1, 2:
                    items = getClass1Array(typeFilling, length);
                    break;
                default:
                    break;
            }

            sortField = answerTaker("""
                    Выберите поле для сортировки:
                    1: Class1.getFirstFieldName()
                    2: Class1.getSecondFieldName()
                    3: Class1.getThirdFieldName()""");

            // TODO: как сделать сортировку. Что передаём, что возвращаем?

            if (answerTaker(EXIT) == 0) {
                break;
            }
        }
    }

    public static int answerTaker(String message) {
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

    public static List<IBaseClass> getClass1Array(int fillType, int size) {
        BaseCollectionGenerator<IBaseClass> generator = null;
        switch (fillType) {
            case 1 -> generator = new FileCollector<>(FILE_NAME);
            case 2 -> generator = new RandomCollector(size);
            case 3 -> generator = new ConsoleCollector(size);
        }

        CollectionGeneratorClient<IBaseClass> client = new CollectionGeneratorClient<>(generator);
        return client.get();
    }

    private static final String FILE_NAME = "Java102FinalProject/src/main/resources/users.json";
}