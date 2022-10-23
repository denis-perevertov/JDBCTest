package dao;

import entity.Product;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingCartDAO {

    void add(User user, Product product) throws SQLException;
    void remove(User user, Product product) throws SQLException;

    List<Product> getUserProducts(User user) throws SQLException;

    void removeAllProducts(User user) throws SQLException;

}
