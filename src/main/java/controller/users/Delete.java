package controller.users;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/User/Delete")
public class Delete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Delete() {
        super();
    }

    //get
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            UserService userService = new UserService();
            userService.delete(id);
        }catch (Exception e){
            // TODO: handle exception
        }
        response.sendRedirect("/Sample/Login");
    }

    //post
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

    }
}
