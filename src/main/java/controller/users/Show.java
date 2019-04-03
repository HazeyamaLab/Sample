package controller.users;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/User/Show")
public class Show extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Show() {
        super();
    }

    //get
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<User> userList = new ArrayList<>();
        try {
            String search = request.getParameter("search");
            UserService userService = new UserService();
            userList = userService.show(search);
        } catch (Exception e) {
            // TODO: handle exception
        }
        request.setAttribute("userList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/users/show.jsp");
        dispatcher.forward(request, response);
    }
    //post
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

    }
}
