package ru.idcore;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int totalOrders = 1;//количество исполненных заказов перед закрытием
        int executedOrders = 2;//максимальное количество одновременно исполняемых заказов
        int countWaiters = 2;
        int timeForWaiting = 2_000;
        int timeForExecute = 4_000;

        ReentrantLock lock = new ReentrantLock();
        Restaurant restaurant = new Restaurant(lock, totalOrders, executedOrders, timeForWaiting, timeForExecute, countWaiters);
        restaurant.start();

        for (int i = 0; i < totalOrders; i++) {
            Guest guest = new Guest(restaurant);
            guest.setName("Гость-" + i);
            guest.start();
        }


    }
}
