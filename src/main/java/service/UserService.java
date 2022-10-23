package service;

import dao.UserDAO;
import entity.User;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService extends Util implements UserDAO {

    private Connection connection;

    public UserService() {
        connection = getConnection();
    }

    @Override
    public void add(User user) throws SQLException {

        String sql = "INSERT INTO users(name, surname, age) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setInt(3, user.getAge());

        int rows = preparedStatement.executeUpdate();
        if(rows > 0) logger.info("Added user " + user.getName() + " " + user.getSurname() + " into users table");

        preparedStatement.close();

        String sql2 = "INSERT INTO user_details(name, surname, age, gender, birth_date, " +
                "phone_number, country, city, nationality) values (?,?,?,?,?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(sql2);

        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setInt(3, user.getAge());
        preparedStatement.setString(4, user.getGender());
        preparedStatement.setDate(5, user.getBirth_date());
        preparedStatement.setString(6, user.getPhone_number());
        preparedStatement.setString(7, user.getCountry());
        preparedStatement.setString(8, user.getCity());
        preparedStatement.setString(9, user.getNationality());

        rows = preparedStatement.executeUpdate();
        if(rows > 0) logger.info("Added user " + user.getName() + " " + user.getSurname() + " into user_details table");

        preparedStatement.close();

    }

    @Override
    public List<User> getAllUsers() throws SQLException {

        List<User> userList = new ArrayList<>();

        String sql = "SELECT * FROM users";

        Statement statement = connection.createStatement();

        ResultSet res = statement.executeQuery(sql);

        while(res.next()) {
            User user = new User();
            user.setId(res.getInt("user_id"));
            user.setName(res.getString("name"));
            user.setSurname(res.getString("surname"));
            user.setAge(res.getInt("age"));

            userList.add(user);
        }

        logger.info("Obtained list of all users from users table");

        return userList;
    }

    @Override
    public User getById(int id) throws SQLException {

        User user = new User();

        String sql = "SELECT * FROM users WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,id);

        ResultSet res = preparedStatement.executeQuery();

        while(res.next()) {
            user.setId(res.getInt(1));
            user.setName(res.getString(2));
            user.setSurname(res.getString(3));
            user.setAge(res.getInt(4));
        }

        logger.info("Obtained user " + user.getName() + " " + user.getSurname() + " from users table");

        return user;
    }

    @Override
    public void update(User user) throws SQLException {

        String sql = "UPDATE users SET name=?, surname=?, age=? WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getSurname());
        preparedStatement.setInt(3,user.getAge());
        preparedStatement.setInt(4,user.getId());

        int rows = preparedStatement.executeUpdate();
        if(rows > 0) logger.info("Updated user");

    }

    @Override
    public void remove(User user) throws SQLException {

        String sql = "DELETE FROM users WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, user.getId());

        int rows = preparedStatement.executeUpdate();
        if(rows > 0) logger.info("Deleted " + rows + " rows from users table");

    }
}
