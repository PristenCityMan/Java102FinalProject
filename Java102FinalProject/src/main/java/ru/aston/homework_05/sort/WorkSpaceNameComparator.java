package ru.aston.homework_05.sort;

import java.util.Comparator;

public class WorkSpaceNameComparator implements Comparator<WorkSpace> {
    @Override
    public int compare(WorkSpace w1, WorkSpace w2) {
        return w1.getFirstFieldName().compareTo(w2.getFirstFieldName());
    }
}
