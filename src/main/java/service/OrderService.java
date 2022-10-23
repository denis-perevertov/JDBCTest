package service;

import dao.OrderDAO;
import entity.Order;
import entity.Product;
import entity.User;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService extends Util implements OrderDAO {

    Connection connection;
    public UserService userService;
    public ProductService productService;
    public ShoppingCartService shoppingCartService;

    public OrderService() {
        connection = getConnection();
        userService = new UserService();
        productService = new ProductService();
        shoppingCartService = new ShoppingCartService();
    }


    @Override
    public void createOrder(User user) throws SQLException {

        logger.info("Creating an order for " + user.getName() + " " + user.getSurname());

        StringBuilder sb = new StringBuilder();
        String products = "--";
        double total_price = 0.0;

        List<Product> userProductList = shoppingCartService.getUserProducts(user);

        for(Product p : userProductList) {
            sb.append(p.getName()).append(", ");
            total_price += p.getPrice();
        }

        if(userProductList.size() > 0) {
            products = sb.deleteCharAt(sb.length()-2).toString().trim();
        } else {
            logger.error("Shopping cart for user is empty, can't create order");
            return;
        };

        logger.info("Finished creating order, now inserting into DB");

        String sql = "INSERT INTO orders(user_id, description, total_price) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, products);
        preparedStatement.setDouble(3, total_price);

        int rows = preparedStatement.executeUpdate();

        if(rows > 0) logger.info(rows + " rows have been updated in Orders table");

        shoppingCartService.removeAllProducts(user);

        logger.info("Cleared products from shopping cart for user");

    }


    @Override
    public List<Order> getUserOrders(User user) throws SQLException {

        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM orders WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, user.getId());

        ResultSet res = preparedStatement.executeQuery();

        while(res.next()) {
            Order order = new Order();
            order.setId(res.getInt("order_id"));
            order.setUser(res.getInt("user_id"));
            order.setDescription(res.getString("description"));
            order.setTotalPrice(res.getDouble("total_price"));

            orderList.add(order);
        }

        return orderList;
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {

        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM orders";

        Statement statement = connection.createStatement();

        ResultSet res = statement.executeQuery(sql);

        while(res.next()) {
            Order order = new Order();
            order.setId(res.getInt("order_id"));
            order.setUser(res.getInt("user_id"));
            order.setDescription(res.getString("description"));
            order.setTotalPrice(res.getDouble("total_price"));

            orderList.add(order);
        }

        return orderList;
    }
}
