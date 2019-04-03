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

@WebServlet("/User/Update")
public class Update extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Update() {
        super();
    }

    //get
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("name");
        String password = request.getParameter("pass");

        User user = new User();
        user.setId(id);
        user.setName(userName);
        user.setPass(password);
        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/users/update.jsp");
        dispatcher.forward(request, response);
    }

    //post
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String userName = request.getParameter("name");
            String password = request.getParameter("pass");

            User user  = new User();
            user.setId(id);
            user.setName(userName);
            user.setPass(password);
            UserService userService = new UserService();
            userService.update(user);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/users/home.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
