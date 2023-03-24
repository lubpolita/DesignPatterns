package Classes.ClassicPizzas;
import Classes.Constants;
import Interfaces.IPizza;

public class Pepperoni implements IPizza {
    private String pizzaSize;

    @Override
    public String getDescription() {
        return "Pizza de Pepperoni";
    }

    public Pepperoni(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    @Override
    public double getCost() {
        switch (pizzaSize) {
            case Constants.Sizes.SMALL:
                return 40.00;
            case Constants.Sizes.MEDIUM:
                return 60.00;
            case Constants.Sizes.LARGE:
                return 85.00;
            default:
                return 0.00;
        }
    }

}
