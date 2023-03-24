package Classes.ClassicPizzas;
import Classes.Constants;
import Interfaces.IPizza;

public class FrangoCatupiry implements IPizza {
    private String pizzaSize;

    @Override
    public String getDescription() {
        return "Pizza de Frango com Catupiry";
    }

    public FrangoCatupiry(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    @Override
    public double getCost() {
        switch (pizzaSize) {
            case Constants.Sizes.SMALL:
                return 30.00;
            case Constants.Sizes.MEDIUM:
                return 50.00;
            case Constants.Sizes.LARGE:
                return 80.00;
            default:
                return 0.00;
        }
    }
}
