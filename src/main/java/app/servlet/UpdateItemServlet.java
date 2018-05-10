package app.servlet;

import app.controller.Controller;
import app.controller.DataBaseController;
import app.model.Author;
import app.model.Chapter;
import app.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseController controller = DataBaseController.getInstance();
        String itemName = req.getParameter("itemName");
        req.setAttribute("item", controller.getItem(itemName));
        req.getRequestDispatcher("view/updateItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseController controller = DataBaseController.getInstance();
        String itemName = req.getParameter("itemName");
        long yearOfPublication = Long.parseLong(req.getParameter("yearOfPublication"));
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        List<Chapter> chapters = new ArrayList<>();
        String[] chapterNames = req.getParameterValues("chapterName");
        String[] texts = req.getParameterValues("text");
        for(int i = 0; i<chapterNames.length; i++){
            chapters.add(new Chapter(chapterNames[i], texts[i]));
        }
        Item item = new Item(itemName, yearOfPublication, new Author(firstName, secondName), chapters);
        controller.updateItem(item);
        resp.sendRedirect(req.getContextPath() + "/getItems");
    }
}
