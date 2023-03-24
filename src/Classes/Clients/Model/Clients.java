package Classes.Clients.Model;

import Classes.Constants;
import Interfaces.IOrderObserver;

public class Clients implements IOrderObserver {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public Clients(String name, String email, String address, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "\nNome: " + name +
                "\nEmail: " + email +
                "\nEndereço: " + address +
                "\nCelular: " + phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void updateStatus(int status) {
        String msg = "";

        switch (status) {
            case Constants.Status.OUT_FOR_DELIVERY:
                msg = name + ", seu pedido saiu para a entrega!";
                break;

            case Constants.Status.FINISHED:
                msg = name + ", seu pedido foi entregue!";
                break;

            case Constants.Status.CONFIRMED:
                msg = name + ", seu pedido foi confirmado!";
            default:
                break;
        }
        System.out.println(msg);
    }

}
