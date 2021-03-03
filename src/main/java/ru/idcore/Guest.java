package ru.idcore;

public class Guest extends Thread {
    private Restaurant restaurant;

    public Guest(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            //Thread.sleep(restaurant.getKitchen().getTimeForWaiting());
            restaurant.makeOrder(this);
            while (!restaurant.checkGuestOrderStatus(this, OrderStatus.STATUS_DONE)) {
                System.out.printf("%s ожидает выполнение заказа...\n", this.getName());
                Thread.sleep(restaurant.getKitchen().getTimeForWaiting());
            }
            System.out.printf("%s получил заказ...\n", this.getName());
            Thread.sleep(restaurant.getKitchen().getTimeForWaiting());
            System.out.printf("%s ушел из ресторана...\n", this.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
