package Classes;
import Classes.ClassicPizzas.FrangoCatupiry;
import Classes.ClassicPizzas.Marguerita;
import Classes.ClassicPizzas.Pepperoni;
import Classes.ClassicPizzas.Portuguesa;
import Classes.SpecialPizzas.CalabresaEspecial;
import Classes.SpecialPizzas.QuatroQueijos;
import Interfaces.IPizza;

public class PizzaFactory {
    public IPizza createPizza(int flavor, String pizzaSize) {

        switch (flavor) {
            case Constants.Flavors.MARGUERITA :
            return new Marguerita(pizzaSize);
            
            case Constants.Flavors.FRANGO_CATUPIRY:
            return new FrangoCatupiry(pizzaSize);

            case Constants.Flavors.QUATRO_QUEIJOS:
            return new QuatroQueijos(pizzaSize);

            case Constants.Flavors.PEPPERONI:
            return new Pepperoni(pizzaSize);

            case Constants.Flavors.PORTUGUESA:
            return new Portuguesa(pizzaSize);

            case Constants.Flavors.CALABRESA_ESPECIAL:
            return new CalabresaEspecial(pizzaSize);

            default:
            return null;
        } 

    }
}
