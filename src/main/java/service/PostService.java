package service;

import dao.PostDao;
import model.Post;

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class PostService extends HttpServlet {

    private Connection connection = null;

    private void createConnection(PostDao dao) {
        this.connection = dao.createConnection();
    }

    private void closeConnection(PostDao dao) {
        dao.closeConnection(this.connection);
        this.connection = null;
    }

    LocalDateTime dateTime = LocalDateTime.now();

    public List<Post> getPostByBbsId(int bbsId) {
        PostDao dao = new PostDao();
        createConnection(dao);
        List<Post> posts = dao.findByBbsId(bbsId, connection);
        closeConnection(dao);
        return posts;
    }

    public void addPost(Post post) {
        post.setCreatedAt(dateTime);
        PostDao dao = new PostDao();
        createConnection(dao);
        dao.create(post, connection);
        closeConnection(dao);
    }

    public void deletePostByBbsId(int bbsId) {
        PostDao dao = new PostDao();
        createConnection(dao);
        dao.deleteByBbsId(bbsId, connection);
        closeConnection(dao);
    }

    public void deletePostByPostId(int postId) {
        PostDao dao = new PostDao();
        createConnection(dao);
        dao.delete(postId, connection);
        closeConnection(dao);
    }

    public Post getPostByPostId(int postId) {
        PostDao dao = new PostDao();
        createConnection(dao);
        Post post = new Post();
        post = dao.findOne(postId, connection);
        closeConnection(dao);
        return post;
    }

    public void update(Post post) {
        PostDao dao = new PostDao();
        createConnection(dao);
        dao.update(post, connection);
        closeConnection(dao);
    }
}
