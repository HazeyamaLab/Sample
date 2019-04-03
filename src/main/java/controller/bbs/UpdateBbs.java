package controller.bbs;

import model.Bbs;
import service.BbsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bbs/update")
public class UpdateBbs extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateBbs() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String bbsId = request.getParameter("id");
        BbsService bbsService = new BbsService();
        Bbs bbs = new Bbs();
        bbs = bbsService.getBbs(Integer.parseInt(bbsId));
        request.setAttribute("bbs", bbs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF//bbs/update.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String bbsTitle = request.getParameter("title");
        String description = request.getParameter("description");
        String bbsId = request.getParameter("bbsId");
        Bbs bbs = new Bbs();
        bbs.setBbsId(Integer.parseInt(bbsId));
        bbs.setTitle(bbsTitle);
        bbs.setDescription(description);
        BbsService bbsService = new BbsService();
        bbsService.updateBbs(bbs);
        response.sendRedirect("/Sample/bbs");
    }

}
