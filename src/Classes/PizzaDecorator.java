package Classes;
import Interfaces.IPizza;

public abstract class PizzaDecorator implements IPizza {
    protected IPizza pizza;
    
    public PizzaDecorator(IPizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }

}