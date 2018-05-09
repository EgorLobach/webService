package app.servlet;

import app.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class DeleteItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = Controller.getInstance();
        try {
            controller.deleteItem(req.getParameter("itemName"));
            resp.sendRedirect(req.getContextPath() + "/getItems");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
