// package Classes;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;

// import Classes.Clients.Model.Clients;
// import Classes.Order.Model.Order;

// public class PizzariaDAO {
//   private Connection connection;

//   public void conectar() throws SQLException {
//     connection = DriverManager.getConnection("jdbc:sqlite:pizzaria.db");
//   }

//   public void insertClient(Clients client) throws SQLException {
//     String sql = "INSERT INTO clients (email, name, address, phone_number) VALUES (?, ?, ?, ?)";
//     PreparedStatement stmt = connection.prepareStatement(sql);
//     stmt.setString(1, client.getEmail());
//     stmt.setString(2, client.getName());
//     stmt.setString(3, client.getAddress());
//     stmt.setString(4, client.getPhoneNumber());
//     stmt.executeUpdate();
//   }
  
//   public void insertOrder(Order order) throws SQLException {
//     String sql = "INSERT INTO orders (id, client_email, pizzas_description, total_cost ) VALUES (?, ?, ?, ?)";
//     PreparedStatement stmt = connection.prepareStatement(sql);
//     stmt.setInt(1, order.getId());
//     stmt.setString(2, order.getClient().getEmail());
//     stmt.setString(3, order.getDescription());
//     stmt.setDouble(4, order.getTotalCost());
//     stmt.executeUpdate();
//   }

//   public ArrayList<Order> getOrdersByClient(Clients client) {
//     ArrayList<Order> orders = new ArrayList<Order>();

//     String sql = "SELECT * FROM orders WHERE client_email = ?";
//     try {
//       PreparedStatement preparedStatement = connection.prepareStatement(sql);
//       preparedStatement.setString(1, client.getEmail());
//       ResultSet resultSet = preparedStatement.executeQuery();

//         while (resultSet.next()) {
//             int id = resultSet.getInt("id");
//             String pizzasDescription = resultSet.getString("pizzas_description");
//             double totalCost = resultSet.getDouble("total_cost");

//             Order order = new Order(id, client);
//             order.setDescription(pizzasDescription);
//             order.setTotalCost(totalCost);

//             orders.add(order);
//         }
//     } catch (SQLException e) {
//         System.out.println(e.getMessage());
//     }

//     return orders;
// }

// }