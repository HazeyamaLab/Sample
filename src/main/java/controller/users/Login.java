package controller.users;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    //get
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //request.setAttribute("",);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }

    //post
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String userName = request.getParameter("name");
            String password = request.getParameter("pass");

            UserService userService = new UserService();
            User user = userService.login(userName, password);

            if (user.getName() == null && user.getPass() == null) {
                response.sendRedirect("/Sample/Login");
            }
            String login = "login";
            request.setAttribute("login",login);
            //sessionにログイン情報
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            //ログインしたユーザの情報(User user)をフォワードする
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/users/home.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
