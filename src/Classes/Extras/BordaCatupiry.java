package Classes.Extras;
import Classes.PizzaDecorator;
import Interfaces.IPizza;

public class BordaCatupiry extends PizzaDecorator {

    public BordaCatupiry(IPizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", borda recheada com catupiry";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 8.0;
    }
}
