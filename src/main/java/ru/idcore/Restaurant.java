package ru.idcore;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant extends Thread {
    //private final Table table;
    private Kitchen kitchen;
    private ReentrantLock locker;
    private int timeForExecute;
    private Deque<Guest> guests;
    private int countWaiters;


    public Restaurant(ReentrantLock locker, int totalOrders, int executedOrders, int timeForWaiting, int timeForExecute, int countWaiters) {
        this.locker = locker;
        kitchen = new Kitchen(locker, this, totalOrders, executedOrders, timeForWaiting);
        this.timeForExecute = timeForExecute;
        guests = new ArrayDeque<>();
        this.countWaiters = countWaiters;

    }

    public Deque<Guest> getGuests() {
        return guests;
    }

    public void setGuests(Deque<Guest> guests) {
        this.guests = guests;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public ReentrantLock getLocker() {
        return locker;
    }

    public void setLocker(ReentrantLock locker) {
        this.locker = locker;
    }

    public void issueOrder() {
        kitchen.issueOrder();
    }


    public void receiveOrder(Order order) {
        kitchen.receiveOrder(order);
    }

    @Override
    public void run() {
        System.out.println("Ресторан открыт");

        ThreadGroup waitersGroup = new ThreadGroup("Waiters");


        for (int i = 0; i < countWaiters; i++) {
            Waiter waiter = new Waiter(this, i);
            waiter.start();
        }

        while (true) {
            try {
                Thread.sleep(timeForExecute);
                kitchen.executeOrder();
                System.out.println("Количество выполненных заказов: " + kitchen.getCountOrders());
                if (kitchen.getCountOrders() == kitchen.getTotalOrders()) {
                    waitersGroup.interrupt();
                    waitersGroup.destroy();
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Кухня выполнила все заказы. Ресторан закрывается...");
    }

    public void makeOrder(Guest guest) {
        kitchen.makeOrder(guest);
    }

    public boolean checkGuestOrderStatus(Guest guest, OrderStatus orderStatus) {
        return kitchen.checkGuestOrderStatus(guest, orderStatus);
    }

    public void waitGuestOrder() {
        kitchen.waitGuestOrder();
    }
}
