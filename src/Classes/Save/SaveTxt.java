package Classes.Save;

import java.io.FileWriter;
import java.util.ArrayList;

import Classes.Constants;
import Classes.Clients.Model.Clients;
import Classes.Order.Model.Order;
import Interfaces.IPizza;
import Interfaces.ISaveOrder;

public class SaveTxt implements ISaveOrder {

    private String createFilename(Order order) {
        Clients client = order.getClient();
        return Constants.Paths.TXT + order.getId() + "_" + client.getName() + Constants.Extensions.TXT;
    }

    private String getPizzasDescription(Order order) {
        String description = "";
        ArrayList<IPizza> pizzas = order.getPizza();

        for (IPizza pizza : pizzas) {
            description = description + "\n" +
                    "Descrição: " + pizza.getDescription() + " - \t" +
                    "Valor: " + pizza.getCost();
        }

        description = description + "\n" +
                "Valor total: " + order.getTotalCost();

        return description;
    }

    private String createDocument(Order order) {
        Clients client = order.getClient();

        return "Num. Pedido: " + order.getId() + "\n" +
                "Nome: " + client.getName() + "\n" +
                "Email: " + client.getEmail() + "\n" +
                "Celular: " + client.getPhoneNumber() + "\n" +
                "Endereço: " + client.getAddress() + "\n" + "\n" +
                "------------------------------------------------ \n" +
                "Pedidos: " + getPizzasDescription(order);

    }

    @Override
    public void saveOrder(Order order) {
        String filename;
        String document;

        filename = createFilename(order);
        document = createDocument(order);

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(document);
            writer.close();
        } catch (Exception e) {

        }
    }

}
