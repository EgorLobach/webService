package app.servlet;

import app.controller.Controller;
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

public class AddItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = Controller.getInstance();
        String itemName = req.getParameter("itemName");
        long yearOfPublication = Long.parseLong(req.getParameter("yearOfPublication"));
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String chapterName = req.getParameter("chapterName");
        String text = req.getParameter("text");
        List<Chapter> chapters = new ArrayList<>();
        chapters.add(new Chapter(chapterName, text));
        Item item = new Item(itemName, yearOfPublication, new Author(firstName, secondName), chapters);
        try {
            controller.addItem(item);
            resp.sendRedirect(req.getContextPath() + "/getItems");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

