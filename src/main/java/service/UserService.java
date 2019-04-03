package service;

import dao.UsersDao;
import model.User;

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.util.List;

public class UserService extends HttpServlet {
    private Connection connection = null;

    private void createConnection(UsersDao dao){
        this.connection = dao.createConnection();
    }

    private void closeConnection(UsersDao dao){
        dao.closeConnection(this.connection);
        this.connection = null;
    }

    public User login(String userName, String password) {
        UsersDao dao = new UsersDao();
        createConnection(dao);
        User user = dao.login(userName, password, connection);
        closeConnection(dao);
        return user;
    }

    public void create(User user) {
        UsersDao dao = new UsersDao();
        createConnection(dao);
        dao.create(user, connection);
        closeConnection(dao);
    }

    public void update(User user) {
        UsersDao dao = new UsersDao();
        createConnection(dao);
        dao.update(user, connection);
        closeConnection(dao);
    }

    public void delete(int id) {
        UsersDao dao = new UsersDao();
        createConnection(dao);
        dao.delete(id, connection);
        closeConnection(dao);
    }

    public List<User> show(String search) {
        UsersDao dao = new UsersDao();
        createConnection(dao);
        List<User> userList = dao.show(search, connection);
        closeConnection(dao);
        return  userList;
    }
}
