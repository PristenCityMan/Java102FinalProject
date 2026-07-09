package ru.aston.homework_05.sort.comparators;

import ru.aston.homework_05.models.User;

import java.util.Comparator;

public class PasswordComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return Integer.compare(u1.getThirdFieldName(), u2.getThirdFieldName());
    }
}
