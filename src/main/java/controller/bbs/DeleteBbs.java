package controller.bbs;

import service.BbsService;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bbs/delete")
public class DeleteBbs extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteBbs() {

        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String bbsId = request.getParameter("id");
        PostService postService = new PostService();
        postService.deletePostByBbsId(Integer.parseInt(bbsId));
        BbsService bbsService = new BbsService();
        bbsService.deleteBbs(Integer.parseInt(bbsId));
        response.sendRedirect("/Sample/bbs");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
