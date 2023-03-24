package Classes.SpecialPizzas;
import Classes.Constants;
import Interfaces.IPizza;

public class CalabresaEspecial implements IPizza {
    private String pizzaSize;

    public CalabresaEspecial(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    @Override
    public String getDescription() {
        return "Pizza de Calabresa";
    }

    @Override
    public double getCost() {
        switch (pizzaSize) {
            case Constants.Sizes.SMALL:
                return 50.00;
            case Constants.Sizes.MEDIUM:
                return 70.00;
            case Constants.Sizes.LARGE:
                return 90.00;
            default:
                return 0.00;
        }
    }
}
