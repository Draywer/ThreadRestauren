package ru.idcore;

public class Order {
    private Guest guest;
    private OrderStatus status;

    public Order(Guest guest) {
        this.guest = guest;
        this.status = OrderStatus.STATUS_NEW;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
