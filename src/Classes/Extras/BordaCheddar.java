package Classes.Extras;
import Classes.PizzaDecorator;
import Interfaces.IPizza;

public class BordaCheddar extends PizzaDecorator {

    public BordaCheddar(IPizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", borda recheada com cheddar";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 9.0;
    }
}
