import entity.Product;
import entity.User;
import service.OrderService;
import service.ProductService;
import service.ShoppingCartService;
import service.UserService;
import util.Util;

import java.sql.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

//        OrderService orderService = new OrderService();
//        ProductService productService = new ProductService();
//        UserService userService = new UserService();
//        ShoppingCartService shoppingCartService = new ShoppingCartService();
//
//        User user = new User("Test" , "test", 100000);
//
//        userService.add(user);
//
//        user = null;
//
//        user = userService.getById(166);
//
//        shoppingCartService.add(user, productService.getById(10));
//
//        orderService.createOrder(user);

        String sql = "INSERT INTO users (name, surname, age) VALUES (?, ?, ?)";

        Util util = new Util();
        Connection connection = util.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println(preparedStatement.toString());

        preparedStatement.setString(1, "lololo");
        preparedStatement.setString(2, "sdlkfjdslf");
        preparedStatement.setInt(3,100);

        System.out.println(preparedStatement.toString());

    }

}
