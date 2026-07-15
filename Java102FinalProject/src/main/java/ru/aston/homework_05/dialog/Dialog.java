package ru.aston.homework_05.dialog;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aston.homework_05.generators.BaseCollectionGenerator;
import ru.aston.homework_05.generators.CollectionGeneratorClient;
import ru.aston.homework_05.generators.ConsoleCollector;
import ru.aston.homework_05.generators.FileCollector;
import ru.aston.homework_05.generators.RandomCollector;
import ru.aston.homework_05.models.User;
import ru.aston.homework_05.models.WorkSpace;
import ru.aston.homework_05.output.JsonFileSaver;
import ru.aston.homework_05.sort.ComparatorStrategy;
import ru.aston.homework_05.sort.MergeSort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.ToIntFunction;

public class Dialog {
    private static final String TYPE_FILLING_TEXT = """
            Выберите вариант заполнения
            1: Из файла
            2: Случайно
            3: Вручную""";
    private static final String LENGTH_TEXT = "Выберите длину массива:";
    private static final String EXIT_TEXT = "Для выхода из программы выберите 0, для повторения работы любое другое число.";
    private static final String STANDARD_SORT = "Стандартный";
    private static final String SORT_EVEN = "Сортировка только чётных значений";
    private static final String EMPTY_LIST = "Список пуст. Возврат к началу.";
    private static final List<Integer> VALID_LIST3 = Arrays.asList(1, 2, 3);
    private static final List<Integer> VALID_LIST2 = Arrays.asList(1, 2);
    private static final String FILES_DIRECTORY = "Java102FinalProject/src/main/resources/";

    public static void dialog() {
        System.out.println("Вас приветствует программа сортировки классов.\n " + "Выбирайте вариант из предложенных.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int classType = answerTaker("""
                    Выберите класс:
                    1: %s
                    2: %s""".formatted(User.getClassName(), WorkSpace.getClassName()), VALID_LIST2, scanner);
            if (classType == 0) {
                scanner.close();
                break;
            }

            int typeFilling = answerTaker(TYPE_FILLING_TEXT, VALID_LIST3, scanner);
            if (typeFilling == 0) {
                scanner.close();
                break;
            }

            int length = answerTaker(LENGTH_TEXT, scanner);
            if (length == 0) {
                scanner.close();
                break;
            }

            List<User> users = new ArrayList<>();
            List<WorkSpace> workSpaces = new ArrayList<>();
            if (classType == 1) {
                users = get1stClassCollection(typeFilling, length);
                if (users == null || users.isEmpty()) {
                    System.out.println(EMPTY_LIST);
                    continue;
                }
            } else {
                workSpaces = get2ndClassCollection(typeFilling, length);
                if (workSpaces == null || workSpaces.isEmpty()) {
                    System.out.println(EMPTY_LIST);
                    continue;
                }
            }

            int field = answerTaker("""
                Выберите поле для сортировки:
                1: %s
                2: %s
                3: %s""".formatted(classType == 1 ? User.getFirstFieldName() : WorkSpace.getFirstFieldName(),
                            classType == 1 ? User.getSecondFieldName() : WorkSpace.getSecondFieldName(),
                            classType == 1 ? User.getThirdFieldName() : WorkSpace.getThirdFieldName()), VALID_LIST3, scanner);
            if (field == 0) {
                scanner.close();
                break;
            }

            switch (classType) {
                case 1:
                    sortAndPrint(users, User.class, field, "Users", scanner);
                    break;
                case 2:
                    workSpaceSort(field, workSpaces, scanner);
                    break;
                default:
                    System.out.println("Ошибка: Выбран неверный модуль");
                    continue;
            }

            if (answerTaker(EXIT_TEXT, scanner) == 0) {
                scanner.close();
                break;
            }
        }
    }

    public static int answerTaker(String message, List<Integer> validAnswer, Scanner scanner) {
        System.out.println(message);
        int choice;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (validAnswer.contains(choice)) {
                    return choice;
                }
            } else if (scanner.hasNext()) {
                String s = scanner.next();
                if (s.equalsIgnoreCase("q")) {
                    return 0;
                }
            }
            System.out.println("Некорректные данные. Повторите ввод.");
            scanner.nextLine();
        }
    }

    public static int answerTaker(String message, Scanner scanner) {
        System.out.println(message);
        int choice;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                return choice;
            } else if (scanner.hasNext()) {
                String s = scanner.next();
                if (s.equalsIgnoreCase("q")) {
                    return 0;
                }
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
                    "%s%ss.json".formatted(FILES_DIRECTORY, classSimpleName.toLowerCase()), User.class, size);
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
                    "%s%ss.json".formatted(FILES_DIRECTORY, className.toLowerCase()), WorkSpace.class, size);
            case 2 -> generator = new RandomCollector<>(size,className);
            case 3 -> generator = new ConsoleCollector<>(size, className);
        }

        CollectionGeneratorClient<WorkSpace> client = new CollectionGeneratorClient<>(generator);
        return client.get();
    }

    private static <T> void sortAndPrint(List<T> list, Class<T> clas, int choice, String className, Scanner scanner) {
        if (list == null || list.isEmpty()) {
            System.out.println("Ошибка: Список " + className + " пуст");
            return;
        }
        try {
            Comparator<T> comparator = ComparatorStrategy.classFieldsSort(clas, choice);
            MergeSort.sort(list, comparator);
            System.out.println("Сортировка завершена:");
            list.forEach(System.out::println);
            try {
                offerSave(list, "sorted_" + className.toLowerCase() + ".json", clas, scanner);
            } catch (IOException e) {
                System.out.println("Ошибка: При записи в файл возникла ошибка: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static <T> void sortAndPrintByEvenIndices(List<T> list, Class<T> clas, int choice, ToIntFunction<T> extractor, Scanner scanner) {
        if (list == null || list.isEmpty()) {
            System.out.println("Список пуст, сортировка невозможна.");
            return;
        }
        try {
            Comparator<T> comparator = ComparatorStrategy.classFieldsSort(clas, choice);
            MergeSort.sortEvenByIndices(list, extractor, comparator);
            System.out.println("Сортировка только четных чисел завершена:");
            list.forEach(System.out::println);
            try {
                offerSave(list, "sorted_workspaces.json", clas, scanner);
            } catch (IOException e) {
                System.out.println("Ошибка: При записи в файл возникла ошибка: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void workSpaceSort(int field, List<WorkSpace> workSpaces, Scanner scanner) {
        if (workSpaces == null || workSpaces.isEmpty()) {
            System.out.println("Ошибка: Массив пустой. Заполните массив");
            return;
        }
        if (field == 1) {
            sortAndPrint(workSpaces, WorkSpace.class, field, "WorkSpaces", scanner);
            return;
        }
        int sortChoice = answerTaker("""
                Выберите режим сортировки для числового поля:
                1: %s
                2: %s""".formatted(STANDARD_SORT, SORT_EVEN), scanner);

        if (sortChoice == 1) {
            sortAndPrint(workSpaces, WorkSpace.class, field, "Рабочие места", scanner);
        } else {
            ToIntFunction<WorkSpace> extractor;
            if (field == 2) {
                extractor = WorkSpace::getSpace;
            } else if (field == 3) {
                extractor = WorkSpace::getSeat;
            } else {
                System.out.println("Ошибка: Выбрано некорректное поле для сортировки. Выбрано " + field);
                return;
            }
            sortAndPrintByEvenIndices(workSpaces, WorkSpace.class, field, extractor, scanner);
        }
    }

    public static <T> void offerSave(List<T> data, String defaultFileName, Class<T> elementType, Scanner scanner)
            throws IOException {
        int choice = answerTaker("""
                Сохранить отсортированный массив в файл?
                1. Да
                2. Нет""", scanner);
        if (choice == 1) {
            String fullPath = FILES_DIRECTORY + defaultFileName;
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFileSaver saver = new JsonFileSaver(objectMapper);
            saver.saveToFile(fullPath, data, elementType);
        }
    }
}