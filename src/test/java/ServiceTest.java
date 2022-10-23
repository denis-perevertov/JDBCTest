import entity.Product;
import org.apache.logging.log4j.core.config.Order;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import util.Util;

import static org.junit.Assert.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceTest {

    static Connection connection;
    static Util util;

    @BeforeClass
    public static void openConnection() {
        util = new Util();
        connection = util.getConnection();
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    public void SQLExceptionTest() {
        assertThrows(SQLException.class, () -> {
           String sql = "INSERT dsfjskd 29 2 oj ";
           PreparedStatement preparedStatement = connection.prepareStatement(sql);

           preparedStatement.setString(1, "LOL");
           preparedStatement.setInt(3, 123);
        });
    }

    @Test
    public void addUserTest() throws SQLException {

        String sql = "INSERT INTO users (name, surname, age) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, "TestName");
        preparedStatement.setString(2, "TestSurname");
        preparedStatement.setInt(3, 100);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " INSERT INTO users (name, surname, age) VALUES (\'TestName\', \'TestSurname\', 100)");

    }

    @Test
    public void getUserTest() throws SQLException {

        String sql = "SELECT * FROM users WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,1);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " SELECT * FROM users WHERE user_id=1");

    }

    @Test
    public void updateUserTest() throws SQLException {

        String sql = "UPDATE users SET name=?, surname=?, age=? WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,"TestName");
        preparedStatement.setString(2,"TestSurname");
        preparedStatement.setInt(3,100);
        preparedStatement.setInt(4,1);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " UPDATE users SET name=\'TestName\', surname=\'TestSurname\', age=100 WHERE user_id=1");

    }

    @Test
    public void removeUserTest() throws SQLException {

        String sql = "DELETE FROM users WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, 1);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " DELETE FROM users WHERE user_id=1");

    }

    @Test
    public void addProductTest() throws SQLException {

        String sql = "INSERT INTO products (name, type, manufacturer, price) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, "TestName");
        preparedStatement.setString(2, "TestType");
        preparedStatement.setString(3, "TestManufacturer");
        preparedStatement.setDouble(4, 100.0);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " INSERT INTO products (name, type, manufacturer, price) VALUES" +
                        " (\'TestName\', \'TestType\', \'TestManufacturer\', 100.0)");

    }

    @Test
    public void getProductTest() throws SQLException {

        String sql = "SELECT * FROM products WHERE product_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,1);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " SELECT * FROM products WHERE product_id=1");

    }

    @Test
    public void updateProductTest() throws SQLException {

        String sql = "UPDATE products SET name=?, type=?, manufacturer=?, price=? WHERE product_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,"TestName");
        preparedStatement.setString(2,"TestType");
        preparedStatement.setString(3,"TestManufacturer");
        preparedStatement.setDouble(4,100.0);
        preparedStatement.setInt(5, 1);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " UPDATE products SET name=\'TestName\', type=\'TestType\'," +
                        " manufacturer=\'TestManufacturer\', price=100.0 WHERE product_id=1");

    }

    @Test
    public void removeProductTest() throws SQLException {

        String sql = "DELETE FROM products WHERE product_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, 1);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " DELETE FROM products WHERE product_id=1");

    }

    @Test
    public void addProductIntoShoppingCartTest() throws SQLException{

        String sql = "INSERT INTO shopping_cart(user_id, product_id) values (?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 10);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " INSERT INTO shopping_cart(user_id, product_id) values (1, 10)");

    }

    @Test
    public void removeProductFromShoppingCartTest() throws SQLException {

        String sql = "DELETE FROM shopping_cart WHERE user_id=? AND product_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 100);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " DELETE FROM shopping_cart WHERE user_id=1 AND product_id=100");
    }

    @Test
    public void getUserProductsTest() throws SQLException {

        String sql = "SELECT * FROM shopping_cart sc JOIN products p ON sc.product_id = p.product_id WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, 100);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " SELECT * FROM shopping_cart sc JOIN products p ON sc.product_id = p.product_id WHERE user_id=100");

    }

    @Test
    public void removeAllProductsTest() throws SQLException {

        String sql = "DELETE FROM shopping_cart WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, 1);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " DELETE FROM shopping_cart WHERE user_id=1");
    }


    @Test
    public void createOrderTest() throws SQLException {

        StringBuilder sb = new StringBuilder();
        String products = "--";
        double total_price = 0.0;

        List<Product> userProductList = new ArrayList<>();
        userProductList.add(new Product("TestName1", "TestType1", "TestManufacturer1", 100));
        userProductList.add(new Product("TestName2", "TestType2", "TestManufacturer2", 100));
        userProductList.add(new Product("TestName3", "TestType3", "TestManufacturer3", 100));

        for(Product p : userProductList) {
            sb.append(p.getName()).append(", ");
            total_price += p.getPrice();
        }

        if(userProductList.size() > 0) {
            products = sb.deleteCharAt(sb.length()-2).toString().trim();
        }

        assertEquals(products, "TestName1, TestName2, TestName3");
        assertEquals(total_price, 300.0, 1.0);

        String sql = "INSERT INTO orders(user_id, description, total_price) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, products);
        preparedStatement.setDouble(3, total_price);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " INSERT INTO orders(user_id, description, total_price)" +
                        " VALUES (1, \'TestName1, TestName2, TestName3\', 300.0)");

    }

    @Test
    public void getUserOrdersTest() throws SQLException {

        String sql = "SELECT * FROM orders WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, 50);

        assertEquals(preparedStatement.toString(),
                "com.mysql.cj.jdbc.ClientPreparedStatement:" +
                        " SELECT * FROM orders WHERE user_id=50");
    }

}
