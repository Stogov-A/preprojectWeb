package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", req.getParameter("message"));
        getServletContext().getRequestDispatcher("/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        if (name.isEmpty() || mail.isEmpty() || pass.isEmpty()) {
            req.setAttribute("message", "Wrong argument");
            getServletContext().getRequestDispatcher("/addUser.jsp").forward(req, resp);
            return;
        }
        User user = new User(req.getParameter("id").isEmpty() ? 0 : Long.parseLong(req.getParameter("id"))
                , name, mail, pass);
        UserService service = new UserService();
        if (service.addUser(user)) {
            resp.setStatus(HttpServletResponse.SC_OK);
            req.setAttribute("Users", service.getAllUsers());
            getServletContext().getRequestDispatcher("/listUsers.jsp").forward(req, resp);
        } else {
            //  resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            req.setAttribute("message", "User exists");
            getServletContext().getRequestDispatcher("/addUser.jsp").forward(req, resp);
        }
    }
}

