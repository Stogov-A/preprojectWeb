package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = UserService.getInstance();
        User user = service.getUserByID(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", user);
        String pass = req.getParameter("Pass") == null ? "" : req.getParameter("Pass");
        if (!pass.isEmpty()) {
            if (!user.getPass().equals(pass)) {
                req.setAttribute("message", "Wrong password");
                getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
                return;
            }
            service.deleteUserByID(user.getId());
            req.setAttribute("Users", service.getAllUsers());
            resp.setStatus(HttpServletResponse.SC_OK);
            getServletContext().getRequestDispatcher("/listUsers.jsp").forward(req, resp);
            return;
        }
        getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
    }
}
