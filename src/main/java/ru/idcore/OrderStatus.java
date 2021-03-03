package ru.idcore;

public enum OrderStatus {
    STATUS_IN_PROGRESS("ВЫПОЛНЯЕТСЯ"),
    STATUS_DONE("ГОТОВ"),
    STATUS_NEW("НОВЫЙ"),
    STATUS_CANCELED("ОТМЕНЕН");


    private String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return "\"" + this.title + "\"";
    }
}
