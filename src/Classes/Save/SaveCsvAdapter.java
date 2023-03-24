package Classes.Save;

import Classes.Order.Model.Order;
import Interfaces.ISaveOrder;

public class SaveCsvAdapter implements ISaveOrder {


    @Override
    public void saveOrder(Order order) {
        SaveCsv saveCsv = SaveCsv.getDefaultInstance();
        saveCsv.saveOrderCsv(order);
    }
    
}
