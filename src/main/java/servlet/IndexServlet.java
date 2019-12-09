package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = UserService.getInstance();
        if (!service.isBaseContainsAdmin()) {
            service.addUser(new User(0L, "admin", "admin", "admin", "admin"));
        }
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        service = UserService.getInstance();
        User user = service.getUserByNameAndPass(name, pass);
        if (user == null) {
            req.setAttribute("message", "Wrong argument");
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("User", user);
            resp.sendRedirect(req.getContextPath() + "/admin");
        }
    }
}
