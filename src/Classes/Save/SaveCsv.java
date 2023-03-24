package Classes.Save;

import java.io.FileWriter;
import java.util.ArrayList;

import Classes.Constants;
import Classes.Clients.Model.Clients;
import Classes.Order.Model.Order;
import Interfaces.IPizza;

public class SaveCsv {
    private static SaveCsv instance;

    private SaveCsv() {
    }

    public static SaveCsv getDefaultInstance() {
        if (instance == null) {
            instance = new SaveCsv();
        }
        return instance;
    }

    private String createFilename(Order order) {
        Clients client = order.getClient();
        return Constants.Paths.CSV + order.getId() + "_" + client.getName() + Constants.Extensions.CSV;
    }

    private String createHeader() {
        return "Pedido;Valor\n";
    }

    private String createLine(IPizza pizza) {
        return pizza.getDescription() + ";" +
                pizza.getCost() + "\n";
    }

    private String createDocument(Order order) {
        String document = "";
        ArrayList<IPizza> pizzas = order.getPizza();

        document = createHeader();

        for (IPizza pizza : pizzas) {
            document = document + createLine(pizza);
        }

        document = document + "Valor total;" + order.getTotalCost();
        return document;
    }

    public void saveOrderCsv(Order order) {

        String filename = createFilename(order);

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(createDocument(order));
            writer.close();
        } catch (Exception e) {
        }

    }

}
