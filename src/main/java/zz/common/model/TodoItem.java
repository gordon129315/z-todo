package zz.common.model;

public class TodoItem {
    private long id;
    private long userId;

    private String text;

    public TodoItem() {
    }

    public TodoItem(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                '}';
    }
}
