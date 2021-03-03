package ru.idcore;

public class Waiter extends Thread {
    private Restaurant restaurant;

    public Waiter(Restaurant restaurant, int num) {
        this.restaurant = restaurant;
        this.setName("Официант-" + num);
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " в зале...");
        try {
            do {
                Thread.sleep(restaurant.getKitchen().getTimeForWaiting());
                restaurant.waitGuestOrder();
            } while (restaurant.getKitchen().checkOrdersStatus(OrderStatus.STATUS_NEW));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + " закончил работу...");
    }
}
