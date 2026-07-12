package ru.aston.homework_05.dialog;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import ru.aston.homework_05.generators.BaseCollectionGenerator;
import ru.aston.homework_05.generators.CollectionGeneratorClient;
import ru.aston.homework_05.generators.ConsoleCollector;
import ru.aston.homework_05.generators.FileCollector;
import ru.aston.homework_05.generators.RandomCollector;
import ru.aston.homework_05.models.User;
import ru.aston.homework_05.models.WorkSpace;
import ru.aston.homework_05.sort.ComparatorFactory;
import ru.aston.homework_05.sort.MergeSort;
import ru.aston.homework_05.sort.comparators.*;

public class Dialog {
    private static final String TYPE_FILLING_TEXT = """
            Выберите вариант заполнения
            1: Из файла
            2: Случайно
            3: Вручную""";
    private static final String LENGTH_TEXT = "Выберите длину массива:";
    private static final String EXIT_TEXT = "Для выхода из программы выберете 0, для повторения работы любое другое число.";
    private static final List<Integer> VALID_LIST3 = Arrays.asList(1, 2, 3);
    private static final List<Integer> VALID_LIST2 = Arrays.asList(1, 2);

    public static void dialog() {
        System.out.println("Вас приветствует программа сортировки классов.\n " + "Выбирайте вариант из предложенных.");
        while (true) {
            int classType = answerTaker("""
                    Выберите класс:
                    1: %s
                    2: %s""".formatted(User.getClassName(), WorkSpace.getClassName()), VALID_LIST2);
            int typeFilling = answerTaker(TYPE_FILLING_TEXT,VALID_LIST3);
            int length = answerTaker(LENGTH_TEXT);

            List<User> users = new ArrayList<>();
            List<WorkSpace> workSpaces = new ArrayList<>();
            if (classType == 1) {
                users = get1stClassCollection(typeFilling, length);
            } else {
                workSpaces = get2ndClassCollection(typeFilling, length);
            }

            int field = answerTaker("""
                    Выберите поле для сортировки:
                    1: %s
                    2: %s
                    3: %s""".formatted(classType == 1 ? User.getFirstFieldName() : WorkSpace.getFirstFieldName(),
                    classType == 1 ? User.getSecondFieldName() : WorkSpace.getSecondFieldName(),
                    classType == 1 ? User.getThirdFieldName() : WorkSpace.getThirdFieldName()),VALID_LIST3);

            if (classType == 1) {
                sortAndPrint(users, User.class, field, "Пользователи");
            } else {
                sortAndPrint(workSpaces, WorkSpace.class, field, "Рабочие места");
            }

            if (answerTaker(EXIT_TEXT) == 0) {
                break;
            }
        }
    }

    public static int answerTaker(String message, List<Integer> validAnswer) {
        System.out.println(message);
        int choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (validAnswer.contains(choice)) {
                    return choice;
                }

            }
            System.out.println("Некорректные данные. Повторите ввод.");
            scanner.nextLine();
        }
    }

    public static int answerTaker(String message) {
        System.out.println(message);
        int choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                return choice;
            }
            System.out.println("Некорректные данные. Повторите ввод.");
            scanner.nextLine();
        }
    }

    private static List<User> get1stClassCollection(int fillType, int size) {
        String classSimpleName = User.class.getSimpleName();
        BaseCollectionGenerator<User> generator = null;
        switch (fillType) {
            case 1 -> generator = new FileCollector<>(
                    "%s%ss.json".formatted(FILES_DIRECTORY, classSimpleName.toLowerCase()), User.class);
            case 2 -> generator = new RandomCollector<>(size, classSimpleName);
            case 3 -> generator = new ConsoleCollector<>(size, classSimpleName);
        }

        CollectionGeneratorClient<User> client = new CollectionGeneratorClient<>(generator);
        return client.get();
    }

    private static List<WorkSpace> get2ndClassCollection(int fillType, int size) {
        String className = WorkSpace.class.getSimpleName();
        BaseCollectionGenerator<WorkSpace> generator = null;
        switch (fillType) {
            case 1 -> generator = new FileCollector<>(
                    "%s%ss.json".formatted(FILES_DIRECTORY, className.toLowerCase()), WorkSpace.class);
            case 2 -> generator = new RandomCollector<>(size,className);
            case 3 -> generator = new ConsoleCollector<>(size, className);
        }

        CollectionGeneratorClient<WorkSpace> client = new CollectionGeneratorClient<>(generator);
        return client.get();
    }

    private static <T> void sortAndPrint(List<T> list, Class<T> clas, int choice, String className) {
        if (list == null || list.isEmpty()) {
            System.out.println("Список " + className + " пуст");
            return;
        }
        try {
            Comparator<T> comparator = ComparatorFactory.classFieldsSort(clas, choice);
            MergeSort.sort(list, comparator);
            System.out.println("Сортировка завершена:");
            list.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static final String FILES_DIRECTORY = "src/main/resources/";
}