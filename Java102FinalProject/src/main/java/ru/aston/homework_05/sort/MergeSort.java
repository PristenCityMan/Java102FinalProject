package ru.aston.homework_05.sort;

import java.util.Comparator;
import java.util.List;

public class MergeSort {

    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        if (list == null || list.size() < 2) return;
        mergeSort(list, 0, list.size() - 1, comparator);
    }

    private static <T> void mergeSort(List<T> list, int left, int right, Comparator<? super T> comparator) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid, comparator);
            mergeSort(list, mid + 1, right, comparator);
            merge(list, left, mid, right, comparator);
        }
    }

    private static <T> void merge(List<T> list, int left, int mid, int right, Comparator<? super T> comparator) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        T[] L = (T[]) new Object[n1];
        T[] R = (T[]) new Object[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = list.get(left + i);
        }
        for (int j = 0; j < n2; j++) {
            R[j] = list.get(mid + 1 + j);
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (comparator.compare(L[i], R[j]) <= 0) {
                list.set(k++, L[i++]);
            } else {
                list.set(k++, R[j++]);
            }
        }
        while (i < n1) {
            list.set(k++, L[i++]);
        }
        while (j < n2) {
            list.set(k++, R[j++]);
        }
    }
}
