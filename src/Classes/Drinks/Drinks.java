package Classes.Drinks;

public class Drinks {
    private String name;
    private int quantity = 0;
    private double cost = 0.0;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Drinks(String name, int quantity, double cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void subtractOne(){
        if(quantity <= 0){
            return;
        }
        quantity = quantity - 1;
    }
}
