package ru.aston.homework_05.dialog;


import java.util.Scanner;

public class Dialog {
    public static void dialog() {
        boolean isAlwaysRunnig = true;
        int classType, typeFilling, length, sortField;
        final String TYPEFILINGTEXT= """
                Выберите вариант заполнения
                1: Из файла
                2: Случайно
                3: Вручную""";
        final String LENGTHTEXT="Выберите длинну массива:";
        final String EXIT="Для выхода из программы выберете 0, для повторения работы любое другое число.";

        System.out.println("Вас приветствует программа сортировки классов.\n " +
                "Выбирайте вариант из предложенных.");
        while (isAlwaysRunnig) {
            classType = answerTaker("""
                    Выберите класс:
                    1: Class1.getName()
                    2: Class2.getName()""");

            typeFilling = answerTaker(TYPEFILINGTEXT);

            length = answerTaker(LENGTHTEXT);

            //Получаем ArrayList по заданным параметрам

   //          switch (classType)
   //          case 1: ArrayList<Class1> list = getClass1Array(typeFilling, length); break;
   //          case 2: ArrayList<Class2> list = getClass2Array(typeFilling, length); break;


            sortField = answerTaker("""
                    Выберете поле для сортировки:
                    1: Class1.getFirstFieldName()
                    2: Class1.getSecondFieldName()
                    3: Class1.getThirdFieldName()""");

       //       как сделать сортировку. Что передаём, что возвращаем?

            if (answerTaker(EXIT)==0){break;}

        }

    }


    public static int answerTaker(String message) {
        System.out.println(message);
        int choice;
        while (true) {
        Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                choice= scanner.nextInt();
                return choice;
            }
            else {
                System.out.println("Некорректные данные. Повторите ввод.");
                continue;
            }
        }
    }
}