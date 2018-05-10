package app.servlet;

import app.controller.Controller;
import app.controller.DataBaseController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class DeleteItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseController controller = DataBaseController.getInstance();
        controller.deleteItem(req.getParameter("itemName"));
        resp.sendRedirect(req.getContextPath() + "/getItems");
    }
}
