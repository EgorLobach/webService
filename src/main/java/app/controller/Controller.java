package app.controller;

import app.model.Author;
import app.model.Chapter;
import app.model.Directory;
import app.model.Item;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Controller {
    private static Controller instance;

    private List<Item> items;

    public static Controller getInstance(){
        if (instance == null) {
            synchronized(Controller.class) {
                Controller inst = instance;
                if (inst == null) {
                    synchronized(Controller.class) {
                        instance = new Controller();
                    }
                }
            }
        }
        return instance;
    }

    private Controller(){
        items = new ArrayList<Item>();
    }

    public void addItem(Item item) throws JAXBException {
        items = unmarshal();
        if (items.indexOf(item)==-1)
            items.add(item);
        marshal(items);
    }

    public void updateItem(Item Item) throws JAXBException {
        items = unmarshal();
        for(app.model.Item i: items)
            if(i.getItemName().equals(Item.getItemName())) {
                items.set(items.indexOf(i), Item);
                marshal(items);
            }
    }

    public void deleteItem(String name) throws JAXBException {
        items = unmarshal();
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item element = iterator.next();
            if (element.getItemName().equals(name)) {
                iterator.remove();
                marshal(items);
            }
        }
    }

    public List<Item> getItems() throws JAXBException {
        return unmarshal();
    }

    public Item getItem(String itemName) throws JAXBException {
        List<Item> items = getItems();
        for(Item item : items){
            if (itemName.equals(item.getItemName()))
                return item;
        }
        return null;
    }

    private List<Item> unmarshal() throws JAXBException {
        File file = new File("D:\\work\\java\\AIPOS\\resources\\temp2.xml");
        JAXBContext context = JAXBContext.newInstance(Directory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Directory directory = (Directory) unmarshaller.unmarshal(file);
        List<Item> itemList = directory.getItems();
        List<Item> items1 = new ArrayList<Item>();
        for (Item item:itemList){
            Item item1 = new Item();
            item1.setYearOfPublication(item.getYearOfPublication());
            item1.setItemName(item.getItemName());
            Author author = new Author();
            author.setFirstName(item.getAuthor().getFirstName());
            author.setSecondName(item.getAuthor().getSecondName());
            item1.setAuthor(author);
            for (Chapter chapter:item.getChapters()){
                Chapter chapter1 = new Chapter();
                chapter1.setChapterName(chapter.getChapterName());
                chapter1.setText(chapter.getText());
                item1.addChapter(chapter1);
            }
            items1.add(item1);
        }
        return items1;
    }
    private void marshal(List<Item> items) throws JAXBException {
        Directory directory = new Directory();
        directory.setItems(items);
        File file = new File("D:\\work\\java\\AIPOS\\resources\\temp2.xml");
        JAXBContext context = JAXBContext.newInstance(Directory.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(directory, file);
    }
}
