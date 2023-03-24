package Classes.ClassicPizzas;
import Classes.Constants;
import Interfaces.IPizza;

public class Portuguesa implements IPizza {
    private String pizzaSize;

    @Override
    public String getDescription() {
        return "Pizza Portuguesa";
    }

    public Portuguesa(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    @Override
    public double getCost() {
        switch (pizzaSize) {
            case Constants.Sizes.SMALL:
                return 35.00;
            case Constants.Sizes.MEDIUM:
                return 55.00;
            case Constants.Sizes.LARGE:
                return 85.00;
            default:
                return 0.00;
        }
    }

}
