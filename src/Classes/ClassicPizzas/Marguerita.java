package Classes.ClassicPizzas;
import Classes.Constants;
import Interfaces.IPizza;

public class Marguerita implements IPizza {

    private String pizzaSize;

    public Marguerita(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    @Override
    public String getDescription() {
        return "Pizza de Marguerita";
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
