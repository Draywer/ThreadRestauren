package ru.idcore;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Kitchen {
    private List<Order> orderList;
    private Deque<Order> receivedOrders;
    private Deque<Order> preparedOrders;
    private ReentrantLock locker;
    private Condition queNotEmpty;
    private Condition queNotExecute;
    private Restaurant restaurant;
    private Condition newOrder;
    private int countOrders;
    private int totalOrders;
    private int executedOrders;
    private int timeForWaiting;

    public Kitchen(ReentrantLock locker, Restaurant restaurant, int totalOrders, int executedOrders, int timeForWaiting) {
        this.locker = locker;
        this.restaurant = restaurant;
        queNotEmpty = locker.newCondition();
        queNotExecute = locker.newCondition();
        newOrder = locker.newCondition();
        receivedOrders = new ArrayDeque<>();
        preparedOrders = new ArrayDeque<>();
        countOrders = 0;
        this.totalOrders = totalOrders;
        this.executedOrders = executedOrders;
        this.timeForWaiting = timeForWaiting;
        orderList = new ArrayList<>();
    }

    public int getTimeForWaiting() {
        return timeForWaiting;
    }

    public void setTimeForWaiting(int timeForWaiting) {
        this.timeForWaiting = timeForWaiting;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public int getExecutedOrders() {
        return executedOrders;
    }

    public void setExecutedOrders(int executedOrders) {
        this.executedOrders = executedOrders;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getCountOrders() {
        return countOrders;
    }

    public void setCountOrders(int countOrders) {
        this.countOrders = countOrders;
    }

    public Deque<Order> getPreparedOrders() {
        return preparedOrders;
    }

    public void setPreparedOrders(Deque<Order> preparedOrders) {
        this.preparedOrders = preparedOrders;
    }

    public Deque<Order> getReceivedOrders() {
        return receivedOrders;
    }

    public void setReceivedOrders(Deque<Order> receivedOrders) {
        this.receivedOrders = receivedOrders;
    }

    public ReentrantLock getLocker() {
        return locker;
    }

    public void setLocker(ReentrantLock locker) {
        this.locker = locker;
    }

    public Condition getQueNotEmpty() {
        return queNotEmpty;
    }

    public void setQueNotEmpty(Condition queNotEmpty) {
        this.queNotEmpty = queNotEmpty;
    }

    public Condition getQueNotExecute() {
        return queNotExecute;
    }

    public void setQueNotExecute(Condition queNotExecute) {
        this.queNotExecute = queNotExecute;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    public void waitGuestOrder() {
        locker.lock();
        try {
            while (!checkOrdersStatus(OrderStatus.STATUS_NEW)) {
                System.out.println("Нет новых заказов...");
                newOrder.await();
            }
            Thread.sleep(timeForWaiting);
            Order order = getOrder(OrderStatus.STATUS_NEW);
            System.out.printf("%s принимает заказ у %s\n", Thread.currentThread().getName(), order.getGuest().getName());
            order.setStatus(OrderStatus.STATUS_IN_PROGRESS);
            newOrder.signalAll();
            receiveOrder(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }

    }


    public void receiveOrder(Order order) {
        locker.lock();
        try {
            System.out.printf("%s передает заказ на Кухню...\n", Thread.currentThread().getName());
            while (receivedOrders.size() == executedOrders) {
                System.out.printf("Ответ Кухни для %s: Заказ поставлен в очередь...\n", Thread.currentThread().getName());
                queNotEmpty.await();
            }
            Thread.sleep(timeForWaiting);
            if (receivedOrders.offerLast(order)) {
                System.out.printf("Кухня приняла заказ от %s\n", Thread.currentThread().getName());
                queNotExecute.signalAll();
            }
            issueOrder();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }


    public void issueOrder() {
        locker.lock();
        try {
            System.out.printf("%s ожидает исполнение заказа от Кухни...\n", Thread.currentThread().getName());
            while (preparedOrders.size() == 0) {
                System.out.printf("Ответ Кухни для %s: Заказ еще не готов. Ждите!...\n", Thread.currentThread().getName());
                queNotExecute.await();
            }
            Thread.sleep(timeForWaiting);
            Order order = preparedOrders.pollFirst();
            assert order != null;
            System.out.printf("%s получил заказ от Кухни для %s\n", Thread.currentThread().getName(), order.getGuest().getName());
            queNotEmpty.signalAll();
            newOrder.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }


    public void executeOrder() {
        locker.lock();
        try {
            //Thread.sleep(2000);
            if (receivedOrders.size() != 0) {
                Order order = receivedOrders.pollFirst();
                order.setStatus(OrderStatus.STATUS_DONE);
                preparedOrders.offerLast(order);
                System.out.println("Кухня выполнила заказ");
                countOrders++;
                queNotExecute.signalAll();
                newOrder.signalAll();
            } else {
                System.out.println("Кухня ожидает заказы..." + receivedOrders.size());
            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
        } finally {
            locker.unlock();
        }

    }

    public boolean checkGuestOrderStatus(Guest guest, OrderStatus orderStatus) {
        locker.lock();
        try {
            boolean result = false;
            for (Order order : orderList
            ) {
                if (order.getGuest().getId() == guest.getId() & order.getStatus().equals(orderStatus)) {
                    result = true;
                    break;
                }
            }
            return result;

        } finally {
            locker.unlock();
        }
    }

    public boolean checkOrdersStatus(OrderStatus orderStatus) {
        locker.lock();
        try {
            boolean result = false;
            for (Order order : orderList
            ) {
                if (order.getStatus().equals(orderStatus)) {
                    result = true;
                    break;
                }
            }
            return result;
        } finally {
            locker.unlock();
        }

    }

    public Order getOrder(OrderStatus orderStatus) {
        Order order = null;
        for (Order order1 : orderList
        ) {
            if (order1.getStatus().equals(orderStatus)) {
                order = order1;
            }
        }
        return order;
    }

    public void makeOrder(Guest guest) {
        locker.lock();
        try {
            Thread.sleep(timeForWaiting);
            System.out.printf("%s хочет сделать заказ...\n", guest.getName());
            ;
            orderList.add(new Order(guest));
            newOrder.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }

    }

}
