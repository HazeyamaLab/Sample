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

@WebServlet("/User/Create")
public class Create extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Create() {
        super();
    }
    
    //get
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //request.setAttribute("",);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/users/create.jsp");
        dispatcher.forward(request, response);
    }

    //post
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String userName = request.getParameter("name");
            String password = request.getParameter("pass");

            User user  = new User();
            user.setName(userName);
            user.setPass(password);
            UserService userService = new UserService();
            userService.create(user);
            //ログインしたユーザの情報(User user)をフォワードする
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);

            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
