package service;

import dao.ProductDAO;
import entity.Product;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService extends Util implements ProductDAO {

    private Connection connection;

    public ProductService() {
        connection = getConnection();
    }

    @Override
    public void add(Product product) throws SQLException {

        String sql = "INSERT INTO products (name, type, manufacturer, price) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getType());
        preparedStatement.setString(3, product.getManufacturer());
        preparedStatement.setDouble(4, product.getPrice());

        int rows = preparedStatement.executeUpdate();
        if(rows > 0) logger.info("Added product " + product.getManufacturer() + " " + product.getName() + " into products table");

    }

    @Override
    public List<Product> getAllProducts() throws SQLException {

        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM products";

        Statement statement = connection.createStatement();

        ResultSet res = statement.executeQuery(sql);

        while(res.next()) {
            Product product = new Product();
            product.setName(res.getString("name"));
            product.setType(res.getString("type"));
            product.setManufacturer(res.getString("manufacturer"));
            product.setPrice(res.getDouble("price"));

            productList.add(product);
        }

        logger.info("Obtained list of all products");

        return productList;
    }

    @Override
    public Product getById(int id) throws SQLException {

        Product product = new Product();

        String sql = "SELECT * FROM products WHERE product_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,id);

        ResultSet res = preparedStatement.executeQuery();

        while(res.next()) {
            product.setId(res.getInt("product_id"));
            product.setName(res.getString("name"));
            product.setType(res.getString("type"));
            product.setManufacturer(res.getString("manufacturer"));
            product.setPrice(res.getDouble("price"));
        }

        logger.info("Obtained product " + product.getManufacturer() + " " + product.getName() + " from products table by ID " + id);
        return product;
    }

    @Override
    public void update(Product product) throws SQLException {

        String sql = "UPDATE products SET name=?, type=?, manufacturer=?, price=? WHERE product_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,product.getName());
        preparedStatement.setString(2,product.getType());
        preparedStatement.setString(3,product.getManufacturer());
        preparedStatement.setDouble(4,product.getPrice());
        preparedStatement.setInt(5, product.getId());

        int rows = preparedStatement.executeUpdate();
        if(rows > 0) logger.info("Updated product");
    }

    @Override
    public void remove(Product product) throws SQLException {

        String sql = "DELETE FROM products WHERE product_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, product.getId());

        int rows = preparedStatement.executeUpdate();
        if(rows > 0) System.out.println("Deleted " + rows + " from products table");

    }
    
}
