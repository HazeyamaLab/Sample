package service;

import dao.BbsDao;
import model.Bbs;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class BbsService {

    private Connection connection = null;

    private void createConnection(BbsDao dao) {
        this.connection = dao.createConnection();
    }

    private void closeConnection(BbsDao dao) {
        dao.closeConnection(this.connection);
        this.connection = null;
    }

    LocalDateTime dateTime = LocalDateTime.now();

    public void createBbs(Bbs bbs) {
        bbs.setCreatedAt(dateTime);
        bbs.setUpdatedAt(dateTime);
        BbsDao dao = new BbsDao();
        createConnection(dao);
        dao.create(bbs, connection);
        closeConnection(dao);
    }

    public List<Bbs> getBbs() {
        BbsDao dao = new BbsDao();
        createConnection(dao);
        List<Bbs> bbs = dao.findAll(connection);
        closeConnection(dao);
        return bbs;
    }

    public Bbs getBbs(int bbsId) {
        BbsDao dao = new BbsDao();
        createConnection(dao);
        Bbs bbs = new Bbs();
        bbs = dao.findOne(bbsId, connection);
        closeConnection(dao);
        return bbs;
    }

    public void updateBbs(Bbs bbs) {
        bbs.setUpdatedAt(dateTime);
        BbsDao dao = new BbsDao();
        createConnection(dao);
        dao.update(bbs, connection);
        closeConnection(dao);
    }

    public void deleteBbs(int bbsId) {
        BbsDao dao = new BbsDao();
        createConnection(dao);
        dao.delete(bbsId, connection);
        closeConnection(dao);
    }
}
