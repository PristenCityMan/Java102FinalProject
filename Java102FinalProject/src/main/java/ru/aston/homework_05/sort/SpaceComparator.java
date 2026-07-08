package ru.aston.homework_05.sort;

import java.util.Comparator;

public class SpaceComparator implements Comparator<WorkSpace> {
    @Override
    public int compare(WorkSpace w1, WorkSpace w2) {
        return Integer.compare(w1.getSecondFieldName(), w2.getSecondFieldName());
    }
}
