package dao;

import model.User;
import utility.DriverAccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersDao extends DriverAccessor {

    // CRUDのSQL文
    public static final String getById = "select * from user where user_id = ?"; // IDを引数にユーザを取得するSQL文
    public static final String getByName = "select * from users where name = ?"; // ユーザ名を引数にユーザを取得するSQL文
    public static final String create = "insert into users(name,pass)values(?,?)"; // ユーザ作成のSQL文
    public static final String update = "update users set name = ?,pass = ? where user_id = ?"; // ユーザ情報更新のSQL文
    public static final String delete = "delete from users where user_id = ?"; // IDを引数にユーザを削除するSQL文

    // ログインの判定のSQL文
    public static final String login = "select * from users where name = ? and pass = ? "; // ログイン時のSQL文

    // 正規表現(数字だけかどうかの判断に使う)
    Pattern pattern = Pattern.compile("^[0-9]*$");

    // Login
    public User login(String userName, String password, Connection connection) {
        try {

            PreparedStatement statement = connection.prepareStatement(login);
            statement.setString(1, userName);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            resultSet.first();
            User user = new User();
            if (resultSet.first()) {
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setPass(resultSet.getString("pass"));
            } else {
                // DB内に存在しない時はこっちに移動する(resultSet.firstがないから).
            }
            statement.close();
            resultSet.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ユーザの作成
    public void create(User user, Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(create);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPass());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ユーザの更新
    public void update(User user, Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPass());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ユーザの削除
    public void delete(int id, Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ユーザの参照
    public List<User> show(String search, Connection connection) {
        List<User> userList = new ArrayList<>();
        try {

            Matcher matcher = pattern.matcher(search);
            if (matcher.find()) {// 数字の時
                int id = Integer.parseInt(search);
                PreparedStatement statement = connection.prepareStatement(getById);
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("user_id"));
                    user.setName(resultSet.getString("name"));
                    user.setPass(resultSet.getString("pass"));
                    userList.add(user);
                }
                statement.close();
                resultSet.close();
            } else {// 文字の時
                PreparedStatement statement = connection.prepareStatement(getByName);
                statement.setString(1, search);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("user_id"));
                    user.setName(resultSet.getString("name"));
                    user.setPass(resultSet.getString("pass"));
                    userList.add(user);
                }
                statement.close();
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
