package Classes.Handlers;

import java.util.ArrayList;

import Classes.Drinks.Drinks;
import Classes.Order.Model.Order;
import Interfaces.IOrderHandler;

public class DrinksHandler implements IOrderHandler {
    private IOrderHandler nextHandler;

    @Override
    public void setNext(IOrderHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void processOrder(Order order) {
        ArrayList<Drinks> drinks = order.getDrinks();

        for (Drinks d : drinks) {
            if (d.getQuantity() <= 0) {
                throw new RuntimeException("O item " + d.getName() + " não está disponível. Tente realizar outro pedido");
            }
        }

        // Passa para o próximo handler
        if (nextHandler != null) {
            nextHandler.processOrder(order);
        }

    }

}
