package Classes.Order.Model;

import java.util.ArrayList;

import Classes.Constants;
import Classes.Clients.Model.Clients;
import Classes.Drinks.Drinks;
import Interfaces.IPizza;
import Interfaces.IOrderObserver;

public class Order {
    private ArrayList<IPizza> pizza;
    private ArrayList<IOrderObserver> observers = new ArrayList<>();
    private String id;
    private Clients client;
    private int status;
    private double totalCost = 0.00;
    private String description = "";
    private int paymentStatus;
    private ArrayList<Drinks> drinks;

    @Override
    public String toString() {
        return "\nId: " + id +
                "\nStatus: " + status +
                "\nValor: " + getTotalCost() +
                "\nNome do cliente: " + client.getName() +
                "\nEmail do cliente: " + client.getEmail() +
                "\nDescrição: " + getDescription();
    }

    public ArrayList<Drinks> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Drinks> drinks) {
        this.drinks = drinks;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void addObserver(IOrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IOrderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (IOrderObserver observer : observers) {
            observer.updateStatus(this.status);
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        notifyObservers();
    }

    public Order(String id, Clients client) {
        this.id = id;
        this.client = client;
        this.status = Constants.Status.CONFIRMED;
    }

    public double getTotalCost() {
        totalCost = 0;
        for (IPizza p : pizza) {
            totalCost = totalCost + p.getCost();
        }
        if (drinks == null) {
            return totalCost;
        }
        for (Drinks d : drinks) {
            totalCost = totalCost + d.getCost();
        }

        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getDescription() {
        for (IPizza p : this.pizza) {
            description = description + p.getDescription() + "+ ";
        }

        if(drinks == null){
            return description;
        }

        for (Drinks d : this.drinks) {
            description = description + d.getName() + "+ ";
        }

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<IPizza> getPizza() {
        return pizza;
    }

    public void setPizza(ArrayList<IPizza> pizza) {
        this.pizza = pizza;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
