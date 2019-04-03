package controller.post;
import model.Post;
import service.PostService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bbs/post/update")
public class UpdatePost extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdatePost() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String postId = request.getParameter("id");
        PostService postService = new PostService();
        Post post = new Post();
        post = postService.getPostByPostId(Integer.parseInt(postId));
        request.setAttribute("post", post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/post/update.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String postId = request.getParameter("postId");
        String bbsId = request.getParameter("bbsId");
        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        post.setPostId(Integer.parseInt(postId));
        post.setBbsId(Integer.parseInt(bbsId));
        PostService postService = new PostService();
        postService.update(post);
        response.sendRedirect("/Sample/bbs/post?id=" + bbsId);
    }
}
