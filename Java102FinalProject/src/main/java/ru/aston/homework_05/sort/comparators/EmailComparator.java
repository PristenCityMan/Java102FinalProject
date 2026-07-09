package ru.aston.homework_05.sort.comparators;

import ru.aston.homework_05.models.User;

import java.util.Comparator;

public class EmailComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return u1.getSecondFieldName().compareTo(u2.getSecondFieldName());
    }
}
