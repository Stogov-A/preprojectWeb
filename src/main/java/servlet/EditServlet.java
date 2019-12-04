package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = UserService.getInstance();
        User user = service.getUserByID(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", user);
        if (req.getParameter("message") != null) {
            if (!req.getParameter("Pass").equals(user.getPass())) {
                req.setAttribute("message", "Wrong Password. Try again");
            } else {
                String name = req.getParameter("Name");
                String mail = req.getParameter("Mail");
                String pass = req.getParameter("New password").isEmpty() ?
                        user.getPass() : req.getParameter("New password");
                if (name.isEmpty() || mail.isEmpty()) {
                    req.setAttribute("message", "Wrong argument");
                } else {
                    service.editUser(user.getId(), name, mail, pass);
                    resp.setStatus(HttpServletResponse.SC_OK);
                    req.setAttribute("Users", service.getAllUsers());
                    getServletContext().getRequestDispatcher("/listUsers.jsp").forward(req, resp);
                }
            }
        }
        getServletContext().getRequestDispatcher("/editUser.jsp").forward(req, resp);
    }
}
