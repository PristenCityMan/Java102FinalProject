package ru.aston.homework_05.sort;

import java.util.Comparator;

public class SeatComaparator implements Comparator<WorkSpace> {
    @Override
    public int compare(WorkSpace w1, WorkSpace w2) {
        return Integer.compare(w1.getThirdFieldName(), w2.getThirdFieldName());
    }
}
