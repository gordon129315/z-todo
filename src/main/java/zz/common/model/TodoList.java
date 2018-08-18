package zz.common.model;

import java.util.LinkedList;

public class TodoList {

    // mark - title

    private  String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // mark - items

    private  LinkedList<TodoItem> items = new LinkedList<>();

    public LinkedList<TodoItem> getItems() {
        return items;
    }

    public void setItems(LinkedList<TodoItem> items) {
        this.items = items;
    }
}
