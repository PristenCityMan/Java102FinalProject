package ru.aston.homework_05.sort;

import ru.aston.homework_05.models.User;
import ru.aston.homework_05.models.WorkSpace;
import ru.aston.homework_05.sort.comparators.*;

import java.util.Comparator;

public class ComparatorFactory {

    public static <T> Comparator<T> classFieldsSort(Class<T> clas, int fieldNumber) {
        if (clas == User.class) {
            userFieldsSort(clas, fieldNumber);
        } else if (clas == WorkSpace.class) {
            workSpaceFieldsSort(clas, fieldNumber);
        }
        throw new IllegalArgumentException("Неизвестный класс: " + clas.getName());
    }

    public static <T> Comparator<T> userFieldsSort(Class<T> clas, int fieldNumber) {
        return switch (fieldNumber) {
            case 1 -> (Comparator<T>) new UserNameComparator();
            case 2 -> (Comparator<T>) new EmailComparator();
            case 3 -> (Comparator<T>) new PasswordComparator();
            default -> throw new IllegalArgumentException("Неверный номер поля: " + fieldNumber);
        };
    }

    public static <T> Comparator<T> workSpaceFieldsSort(Class<T> clas, int fieldNumber) {
        return switch (fieldNumber) {
            case 1 -> (Comparator<T>) new WorkSpaceNameComparator();
            case 2 -> (Comparator<T>) new SpaceComparator();
            case 3 -> (Comparator<T>) new SeatComparator();
            default -> throw new IllegalArgumentException("Неверный номер поля: " + fieldNumber);
        };
    }
}
