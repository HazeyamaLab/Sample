package dao;

import model.Bbs;
import utility.DriverAccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BbsDao extends DriverAccessor {

    public static final String create = "insert into bbs(title,description,created_at,updated_at)values(?,?,?,?)";
    public static final String findAll = "select * from bbs";
    public static final String findOne = "select * from bbs where id = ?";
    public static final String update =  "update bbs set title = ? ,description = ? , updated_at = ? where id = ? ";
    public static final String delete = "delete from bbs where id = ?";
    public void create(Bbs bbs, Connection connection) {
        try {

            PreparedStatement statement = connection.prepareStatement(create);

            statement.setString(1, bbs.getTitle());
            statement.setString(2, bbs.getDescription());
            statement.setObject(3, bbs.getCreatedAt());
            statement.setObject(4, bbs.getUpdatedAt());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bbs> findAll(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(findAll);
            ResultSet resultSet = statement.executeQuery();
            List<Bbs> bbsList = new ArrayList<Bbs>();
            while(resultSet.next()) {
                Bbs bbs = new Bbs();
                bbs.setBbsId(resultSet.getInt("id"));
                bbs.setTitle(resultSet.getString("title"));
                bbs.setDescription(resultSet.getString("description"));
                bbs.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                bbs.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
                bbsList.add(bbs);
            }
            statement.close();
            resultSet.close();

            return bbsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bbs findOne(int bbsId,Connection connection) {
        try {

            PreparedStatement statement = connection.prepareStatement(findOne);
            statement.setInt(1, bbsId);

            ResultSet resultSet = statement.executeQuery();
            resultSet.first();
            Bbs bbs = new Bbs();
            bbs.setBbsId(bbsId);
            bbs.setTitle(resultSet.getString("title"));
            bbs.setDescription(resultSet.getString("description"));
            bbs.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
            bbs.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
            statement.close();
            resultSet.close();
            return bbs;
        } catch (SQLException e) {
            return null;
        }
    }

    public void update(Bbs bbs, Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, bbs.getTitle());
            statement.setString(2, bbs.getDescription());
            statement.setObject(3, bbs.getUpdatedAt());
            statement.setInt(4, bbs.getBbsId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int bbsId,Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setInt(1, bbsId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
