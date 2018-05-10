package app.controller;

import app.model.Author;
import app.model.Chapter;
import app.model.Item;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseController {
    private static DataBaseController instance;

    private static final String URL = "jdbc:mysql://localhost:3306/pascalbook";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static DataBaseController getInstance(){
        if (instance == null) {
            synchronized(DataBaseController.class) {
                DataBaseController inst = instance;
                if (inst == null) {
                    synchronized(DataBaseController.class) {
                        instance = new DataBaseController();
                    }
                }
            }
        }
        return instance;
    }

    DataBaseController(){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItem(Item item){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO items (itemName, yearOfPublication, firstName, secondName)" +
                    " values ('"+item.getItemName()+"', "+ item.getYearOfPublication() +", '"
                    + item.getAuthor().getFirstName() +"', '"+ item.getAuthor().getSecondName()+"');");
            for(Chapter chapter:item.getChapters()){
                statement.execute("insert into chapters (chapterName, text, itemName)" +
                        " values ('"+ chapter.getChapterName() +"', '"+ chapter.getText()+"', '"+item.getItemName()+"');");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteItem(String name){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM items WHERE itemName = '" + name + "';");
            statement.executeUpdate("DELETE FROM chapters WHERE itemName = '" + name + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Item getItem(String name){
        Item item = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM items WHERE itemName ='" + name + "';");
            while (resultSet.next()) {
                String itemName = resultSet.getString("itemName");
                Integer yearOfPublication = resultSet.getInt("yearOfPublication");
                String firstName = resultSet.getString("firstName");
                String secondName = resultSet.getString("secondName");
                List<Chapter> chapters = getChapters(name);
                item = new Item(itemName, yearOfPublication, new Author(firstName, secondName), chapters);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
    public List<Item> getItems(){
        List<Item> items = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
            while (resultSet.next()) {
                String itemName = resultSet.getString("itemName");
                Integer yearOfPublication = resultSet.getInt("yearOfPublication");
                String firstName = resultSet.getString("firstName");
                String secondName = resultSet.getString("secondName");
                List<Chapter> chapters = getChapters(itemName);
                items.add(new Item(itemName, yearOfPublication, new Author(firstName, secondName), chapters));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    private List<Chapter> getChapters(String itemName){
        List<Chapter> chapters = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM chapters WHERE itemName ='" + itemName + "';");
            while (result.next()) {
                String chapterName = result.getString("chapterName");
                String text = result.getString("text");
                chapters.add(new Chapter(chapterName, text));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapters;
    }
    private String add(String fields, String field, String fieldName) {
        if (!field.equals(""))
            fields = fields + fieldName + "='" + field + "', ";
        return fields;
    }

    public void updateItem(Item item) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            String fields = "";
            fields = add(fields, item.getAuthor().getFirstName(), "firstName");
            fields = add(fields, item.getAuthor().getSecondName(), "secondName");
            fields += "yearOfPublication = " + item.getYearOfPublication() +" ";
            statement.executeUpdate("UPDATE items SET " +
                    fields + "WHERE itemName = '" + item.getItemName() + "';");
            statement.executeUpdate("DELETE FROM chapters WHERE itemName = '" + item.getItemName() + "';");
            for(Chapter chapter:item.getChapters()){
                statement.execute("insert into chapters (chapterName, text, itemName)" +
                        " values ('"+ chapter.getChapterName() +"', '"+ chapter.getText()+"', '"+item.getItemName()+"');");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
