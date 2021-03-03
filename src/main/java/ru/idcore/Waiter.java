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
        while (true) {
            try {
                Thread.sleep(restaurant.getKitchen().getTimeForWaiting());
                //restaurant.waitGuestOrder();
                if (restaurant.getKitchen().getCountOrders() == restaurant.getKitchen().getTotalOrders() &
                        !restaurant.getKitchen().checkOrdersStatus(OrderStatus.STATUS_NEW)) {
                    break;
                } else {
                    restaurant.waitGuestOrder();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.getName() + " закончил работу...");
    }
}
