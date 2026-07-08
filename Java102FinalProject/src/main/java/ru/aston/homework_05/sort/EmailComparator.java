package ru.aston.homework_05.sort;

import java.util.Comparator;

public class EmailComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return u1.getSecondFieldName().complairTo(u2.getSecondFieldName());
    }
}
