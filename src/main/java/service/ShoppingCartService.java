package service;

import dao.ShoppingCartDAO;
import entity.Product;
import entity.User;
import util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService extends Util implements ShoppingCartDAO {

    Connection connection;

    public ShoppingCartService() {
        connection = getConnection();
    }

    @Override
    public void add(User user, Product product) throws SQLException {

        String sql = "INSERT INTO shopping_cart(user_id, product_id) values (?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, user.getId());
        preparedStatement.setInt(2, product.getId());

        int rows = preparedStatement.executeUpdate();
        if(rows > 0) logger.info("Added product " + product.getManufacturer() + " " +
                product.getName() + " into " + rows + " rows in shopping_cart table");

    }

    @Override
    public void remove(User user, Product product) throws SQLException {

        String sql = "DELETE FROM shopping_cart WHERE user_id=? AND product_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, user.getId());
        preparedStatement.setInt(2, product.getId());

        int rows = preparedStatement.executeUpdate();

        if(rows > 0) logger.info("Deleted " + rows + " rows from shopping_cart table");

    }

    @Override
    public List<Product> getUserProducts(User user) throws SQLException {

        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM shopping_cart sc JOIN products p ON sc.product_id = p.product_id WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, user.getId());

        ResultSet res = preparedStatement.executeQuery();

        while(res.next()){
            Product product = new Product();
            product.setId(res.getInt("product_id"));
            product.setName(res.getString("name"));
            product.setType(res.getString("type"));
            product.setManufacturer(res.getString("manufacturer"));
            product.setPrice(res.getDouble("price"));

            productList.add(product);
        }

        logger.info("Obtained products list for " + user.getName() + " " + user.getSurname());

        return productList;
    }

    @Override
    public void removeAllProducts(User user) throws SQLException {

        String sql = "DELETE FROM shopping_cart WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, user.getId());

        int rows = preparedStatement.executeUpdate();

        if(rows > 0) logger.info("Deleted " + rows + " rows from shopping_cart table");

    }
}
