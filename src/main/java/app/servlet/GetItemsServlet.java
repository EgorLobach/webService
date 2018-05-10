package app.servlet;

import app.controller.Controller;
import app.controller.DataBaseController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class GetItemsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseController controller = DataBaseController.getInstance();
        req.setAttribute("items", controller.getItems());
        req.getRequestDispatcher("view/getItems.jsp").forward(req, resp);
    }
}
