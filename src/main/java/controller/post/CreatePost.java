package controller.post;

import model.Bbs;
import model.Post;
import model.User;
import service.BbsService;
import service.PostService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/bbs/post")
public class CreatePost extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public CreatePost() {
        super();
    }

    //BBS内の投稿を表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String bbsId = request.getParameter("id");
        PostService postService = new PostService();
        List<Post> posts = postService.getPostByBbsId(Integer.parseInt(bbsId));
        BbsService bbsService = new BbsService();
        Bbs bbs = bbsService.getBbs(Integer.parseInt(bbsId));
        request.setAttribute("posts", posts);
        request.setAttribute("bbs", bbs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/post/home.jsp");
        dispatcher.forward(request, response);
    }
    //BBSに投稿
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String bbsId = request.getParameter("bbsId");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Post post = new Post();
        post.setContributorId(String.valueOf(user.getId()));
        post.setTitle(title);
        post.setBody(body);
        post.setBbsId(Integer.parseInt(bbsId));
        PostService postService = new PostService();
        postService.addPost(post);
        response.sendRedirect("/Sample/bbs/post?id="+bbsId);
    }

}