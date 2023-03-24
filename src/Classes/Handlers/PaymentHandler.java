package Classes.Handlers;

import Classes.Constants;
import Classes.Order.Model.Order;
import Interfaces.IOrderHandler;

public class PaymentHandler implements IOrderHandler {

    private IOrderHandler nextHandler;

    @Override
    public void setNext(IOrderHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void processOrder(Order order) {
        if (order.getPaymentStatus() != Constants.Payment.CONFIRMED) {
            throw new RuntimeException("Pagamento não autorizado.");
        }

        // Passa para o próximo handler
        if (nextHandler != null) {
            nextHandler.processOrder(order);
        }

    }
}
