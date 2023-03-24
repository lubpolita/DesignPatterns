import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import Classes.Constants;
import Classes.PizzaDecorator;
import Classes.PizzaFactory;
import Classes.Clients.Model.Clients;
import Classes.Drinks.Drinks;
import Classes.Handlers.DrinksHandler;
import Classes.Handlers.PaymentHandler;
import Classes.Extras.BordaCatupiry;
import Classes.Extras.BordaCheddar;
import Classes.Order.Model.Order;
import Classes.Save.SaveCsvAdapter;
import Classes.Save.SaveTxt;
import Interfaces.IPizza;
import Interfaces.ISaveOrder;

public class App {
    private static Scanner input = new Scanner(System.in);
    private static Clients client = new Clients(null, null, null, null);
    private static ArrayList<Clients> clients_list = new ArrayList<>();
    private static ArrayList<IPizza> pizzas_list = new ArrayList<>();
    private static ArrayList<Order> orders_list = new ArrayList<>();
    private static ArrayList<Drinks> drinks_list = new ArrayList<>();

    public static boolean isCardNumberValid(String cardNumber) {
        // Remove espaços em branco e hífens do número do cartão
        cardNumber = cardNumber.replaceAll("\\s", "").replaceAll("-", "");
    
        // Verifica se o número do cartão tem 16 dígitos
        if (cardNumber.length() != 16) {
            return false;
        }
    
        // Inverte o número do cartão
        StringBuilder sb = new StringBuilder(cardNumber);
        String reversedCardNumber = sb.reverse().toString();
    
        // Calcula a soma ponderada dos dígitos
        int sum = 0;
        for (int i = 0; i < reversedCardNumber.length(); i++) {
            int digit = Character.getNumericValue(reversedCardNumber.charAt(i));
    
            if (i % 2 == 1) { // Dobra o valor de cada dígito alternado
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
    
            sum += digit;
        }
    
        // Verifica se a soma dos dígitos é um múltiplo de 10
        return (sum % 10 == 0);
    }

    private static void makeOrder() {
        PizzaFactory pizzaFactory = new PizzaFactory();

        System.out.print("Digite seu email: ");
        String email = input.next();
        Clients client = verifyLogin(email);

        while (client == null) {
            System.out.println("Usuário não encontrado, tente novamente.");
            System.out.print("Digite seu email: ");
            try {
                email = input.next();
            } catch (Exception e) {
                client = null;
            }
            client = verifyLogin(email);
        }

        System.out.println("\n== FAZER PEDIDO ==");
        System.out.println("Escolha o sabor da pizza: ");
        System.out.println(Constants.Flavors.MARGUERITA + ". " + "Marguerita");
        System.out.println(Constants.Flavors.QUATRO_QUEIJOS + ". " + "Quatro Queijos");
        System.out.println(Constants.Flavors.FRANGO_CATUPIRY + ". " + "Frango com Catupiry");
        System.out.println(Constants.Flavors.PEPPERONI + ". " + "Pepperoni");
        System.out.println(Constants.Flavors.PORTUGUESA + ". " + "Portuguesa");
        System.out.println(Constants.Flavors.CALABRESA_ESPECIAL + ". " + "Calabresa Especial");
        int flavor= 0;

        while (true) {
            try {
               flavor = input.nextInt();
            } catch (Exception e) {
                System.out.println("Sabor inválido! Tente novamente");
            }
            if (flavor == Constants.Flavors.MARGUERITA ||
                    flavor == Constants.Flavors.QUATRO_QUEIJOS ||
                    flavor == Constants.Flavors.FRANGO_CATUPIRY ||
                    flavor == Constants.Flavors.PEPPERONI ||
                    flavor == Constants.Flavors.PORTUGUESA ||
                    flavor == Constants.Flavors.CALABRESA_ESPECIAL) {
                System.out.println("Você escolheu um sabor válido!");
                break;
            } 
            System.out.println("Sabor inválido! Tente novamente");
        }

        System.out.println("Digite o tamanho da pizza: ");
        System.out.println("P - pequena \nM - média\nG - grande");
        String size = input.next().toUpperCase();

        if (size.equalsIgnoreCase(Constants.Sizes.SMALL) ||
                size.equalsIgnoreCase(Constants.Sizes.MEDIUM) ||
                size.equalsIgnoreCase(Constants.Sizes.LARGE)) {
            System.out.println("Tamanho válido!");
        } else {
            System.out.println("Tamanho inválido!");
        }

        IPizza pizza = pizzaFactory.createPizza(flavor, size);

        System.out.println("Escolha o sabor da borda: ");
        System.out.println("1. Borda catupiry\n2. Borda cheddar\n3. Sem borda");
        int extra = input.nextInt();
        
        while(extra < 1 && extra > 3){
            System.out.println("Valor inválido. Tente novamente.");
            extra = input.nextInt();
        }

        pizza = addComplements(pizza, extra);

        String drink_name = "";
        System.out.println("Escolher uma bebida: ");
        System.out.println("1. Coca-Cola\n2. Guaraná\n3. Sem bebida");
        int drink_choice = input.nextInt();

        while (true) {
            if (drink_choice == 1) {
                drink_name = "coca";
                break;
            } else if (drink_choice == 2) {
                drink_name = "guarana";
                break;
            } else if (drink_choice == 3) {
                drink_name = "nenhum";
                break;
            } else {
                System.out.println("Opção inválida tente novamente!");
                drink_choice = input.nextInt();
            }
        }

        ArrayList<Drinks> drinks_order = new ArrayList<>();
        ArrayList<IPizza> pizzas_order = new ArrayList<>();

        Drinks drink = findDrink(drink_name);
        if (drink != null) {
            drinks_order.add(drink);
            drink.subtractOne();
            System.out.println("drink: " + drink.getQuantity());
        }

        pizzas_order.add(pizza);

        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();

        Order order = new Order(id, client);
        order.setDrinks(drinks_order);
        order.setPizza(pizzas_order);
        order.addObserver(client);

        System.out.print("Digite o número do seu cartão de crédito para o pagamento: ");
        String card = input.next();
        if(isCardNumberValid(card)){
            order.setPaymentStatus(Constants.Payment.CONFIRMED);
        }else{
            order.setPaymentStatus(Constants.Payment.PENDING);
        }

        callHandlers(order);
        orders_list.add(order);
        System.out.println("Pedido realizado com sucesso!");
    }

    private static Order findOrder(String email){
        for(Order order : orders_list){
            Clients client = order.getClient();
            if(email.equalsIgnoreCase(client.getEmail())){
                return order;
            }
        }
        return null;
    }

    private static IPizza addComplements(IPizza pizza, int choice){
        if(choice == 1){
            return pizza = new BordaCatupiry(pizza);
        }
        else if(choice == 2){
            return pizza = new BordaCheddar(pizza);
        }
        return pizza;
    }

    private static void changeStatus(){
        System.out.println("Digite o email associado ao pedido. ");
        String email = input.next();

        Order order = findOrder(email);
        if(order == null){
            System.out.println("Nenhum pedido associado a esse email.");
            return;
        }
        System.out.println("Qual o novo status do pedido? ");
        System.out.println("1. Confirmado\n2. Saiu para entrega\n3.Pedido entregue");
        int status = input.nextInt();
        while(status < 0 || status > 3){
            System.out.println("Status inválido, tente novamente");
            status = input.nextInt();
        }
        order.setStatus(status);
    }

    private static void callHandlers(Order order) {
        // iniciando handlers de verificação
        DrinksHandler drinksHandler = new DrinksHandler();
        PaymentHandler paymentHandler = new PaymentHandler();

        paymentHandler.setNext(drinksHandler);
        paymentHandler.processOrder(order);

    }

    private static Drinks findDrink(String name) {
        for (Drinks drink : drinks_list) {
            if (drink.getName().equalsIgnoreCase(name)) {
                return drink;
            }
        }
        return null;
    }

    private static void register() {

        String text;

        System.out.println("\n== CADASTRO ==");
        System.out.print("Digite seu nome: ");
        text = input.next();
        client.setName(text);

        System.out.print("Digite seu email: ");
        text = input.next();
        client.setEmail(text);

        System.out.print("Digite seu endereço: ");
        text = input.next();
        client.setAddress(text);

        System.out.print("Digite seu número de celular: ");
        text = input.next();
        client.setPhoneNumber(text);

        clients_list.add(client);

        System.out.println("Cadastro concluído com sucesso!");
    }

    public static Clients verifyLogin(String email) {
        for (Clients client : clients_list) {
            if (client.getEmail().equalsIgnoreCase(email)) {
                return client;
            }
        }
        return null;
    }

    public static void saveFileCsv(){
        System.out.println("Digite o email associado ao pedido. ");
        String email = input.next();

        Order order = findOrder(email);
        if(order == null){
            System.out.println("Nenhum pedido associado a esse email.");
            return;
        }

        ISaveOrder saveCsv = new SaveCsvAdapter();
        saveCsv.saveOrder(order);
    }

    public static void saveFileTxt(){
        System.out.println("Digite o email associado ao pedido. ");
        String email = input.next();

        Order order = findOrder(email);
        if(order == null){
            System.out.println("Nenhum pedido associado a esse email.");
            return;
        }

        ISaveOrder saveTxt = new SaveTxt();
        saveTxt.saveOrder(order);
    }

    public static void menu() {
        int choice;
        do {
            System.out.println("\n== MENU DE PEDIDOS DE PIZZA ==");
            System.out.println("1. Cadastrar");
            System.out.println("2. Fazer um pedido");
            System.out.println("3. Alterar status do pedido");
            System.out.println("4. Fazer download do pedido em formato .txt");
            System.out.println("5. Fazer download do pedido em formato .csv");
            System.out.println( "6. Listar pedidos");
            System.out.println("7. Listar clientes");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    makeOrder();
                    break;
                case 3:
                    changeStatus();
                    break;
                case 4: 
                    saveFileTxt();
                    break;
                case 5:
                    saveFileCsv();
                    break;
                case 6:
                    listOrders();
                    break;
                case 7:
                    listClients();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (choice != 8);
    }

    private static void listClients() {
        int i = 1;
        for(Clients client: clients_list){
            System.out.println("\n---------------------");
            System.out.println("\nCliente " + i);
            System.out.println(client.toString());
            i = i + 1;
        }
    }

    private static void listOrders() {
        int i = 1;
        for(Order order: orders_list){
            System.out.println("\n---------------------");
            System.out.println("\nPedido " + i);
            System.out.println(order.toString());
            i = i + 1;
        }
    }

    public static void main(String[] args) {

        // Inicializando algumas variáveis 

        PizzaFactory pizzaFactory = new PizzaFactory();

        IPizza margueritaPizza = pizzaFactory.createPizza(Constants.Flavors.MARGUERITA, Constants.Sizes.LARGE);
        IPizza quatroQueijosPizza = pizzaFactory.createPizza(Constants.Flavors.QUATRO_QUEIJOS, Constants.Sizes.MEDIUM);

        System.out.println("Preço quatro queijos: " + quatroQueijosPizza.getCost());
        PizzaDecorator pizzaDecorator = new BordaCatupiry(new BordaCheddar(quatroQueijosPizza));
        System.out.println("Preço quatro queijos com decorator: " + pizzaDecorator.getCost());

        pizzas_list.add(margueritaPizza);
        pizzas_list.add(pizzaDecorator);

        Drinks drink = new Drinks("coca", 10, 10.00);
        drinks_list.add(drink);

        Drinks drink2 = new Drinks("guarana", 0, 10.00);
        drinks_list.add(drink2);


        Clients client = new Clients("Juliana Silva", "email", "Rua Teste 2, n 100", "3198569993");
        clients_list.add(client);
        Order order = new Order("test id", client);
        order.setPizza(pizzas_list);
        order.addObserver(client);
        orders_list.add(order);
       
        // System.out.println(order.getDescription());
        menu();

    }
}
