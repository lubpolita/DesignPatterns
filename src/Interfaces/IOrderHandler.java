package Interfaces;

import Classes.Order.Model.Order;

public interface IOrderHandler {
    public void setNext(IOrderHandler handler);
    public void processOrder(Order order);
}
