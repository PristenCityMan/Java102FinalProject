package ru.aston.homework_05.models;

public class WorkPlace extends BaseClass {
    @Override
    public String getClassName() {
        return "WorkPlace";
    }

    @Override
    public String getFirstFieldName() {
        return "Название";
    }

    @Override
    public String getSecondFieldName() {
        return "Номер кабинета";
    }

    @Override
    public String getThirdFieldName() {
        return "Номер места";
    }
}
