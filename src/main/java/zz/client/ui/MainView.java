package zz.client.ui;

import com.zzpublic.zwing.Button;
import com.zzpublic.zwing.Label;
import com.zzpublic.zwing.TextField;
import com.zzpublic.zwing.View;
import zz.common.model.TodoItem;
import zz.common.model.TodoList;
import zz.client.service.TodoListService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainView extends View {

    // mark - service

    private TodoListService todoListService = new TodoListService();

    // mark - views

    private static final int paddingNormal = 10;
    private static final int paddingSmall = 5;
    private static final int cellHeight = 30;
    private static final int panelHeight = 40;

    private static final int windowPadding = 40;

    private Label titleLabel;
    private Button addButton;
    private TextField textField;
    private View containerView;


    @Override
    protected void initSubviews() {
        super.initSubviews();

        // title
        titleLabel = new Label();
        titleLabel.setLocation(paddingNormal, paddingNormal);
        titleLabel.setSize(this.getWidth() - 2 * paddingNormal, cellHeight);
        titleLabel.setText("No Title");
//        titleLabel.setBackground(Color.green);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        //输入框
        View inputView = new View();
        inputView.setSize(this.getWidth(), panelHeight);
        inputView.setLocation(0, this.getHeight() - inputView.getHeight() - windowPadding);
        this.add(inputView);

        addButton = new Button("Add task");
        addButton.setSize(100, cellHeight);
        addButton.setLocation(inputView.getWidth() - paddingSmall - addButton.getWidth(), paddingSmall);
        inputView.add(addButton);

        textField = new TextField();
        textField.setSize(addButton.getX() - 2 * paddingSmall, cellHeight);
        textField.setLocation(paddingSmall, paddingSmall);
        inputView.add(textField);
    }

    @Override
    protected void initEvents() {
        super.initEvents();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText().trim();
                if (text.equals("")) {
                    return;
                }
                TodoItem todoItem = new TodoItem(text);
                todoListService.add(todoItem);
                textField.setText("");

                dataToView();
            }
        });

    }

    @Override
    protected void viewDidDisplay() {
        super.viewDidDisplay();
        dataToView();

    }

    private void dataToView() {
        TodoList todoList = todoListService.get();

        titleLabel.setText(todoList.getTitle());

        if (containerView != null) {
            this.remove(containerView);
        }

        // todolist显示位置
        containerView = new View();
        containerView.setLocation(0, titleLabel.getY() + titleLabel.getHeight() + paddingNormal);
        containerView.setSize(this.getWidth(), this.getHeight() - windowPadding - containerView.getY() - panelHeight);
        this.add(containerView);

        int y = 0;

        LinkedList<TodoItem> items = todoList.getItems();
//        Iterator<TodoItem> iterator = items.iterator();
//        while (iterator.hasNext()) {
//            TodoItem item = iterator.next();
//        }

        for (int i = 0; i < items.size(); i++) {
            TodoItem item = items.get(i);
//        }
//        for (TodoItem item : items) {

            //每个todoItem
            View itemContainerView = new View();
            itemContainerView.setLocation(paddingSmall, y);
            itemContainerView.setSize(containerView.getWidth() - 2 * paddingSmall, cellHeight);
            containerView.add(itemContainerView);

            Button deleteButton = new Button();
            deleteButton.setText("Delete");
            deleteButton.setSize(100, cellHeight);
            deleteButton.setLocation(containerView.getWidth() - paddingSmall - deleteButton.getWidth(), 0);
            itemContainerView.add(deleteButton);

            Button editButton = new Button();
            editButton.setText("Edit");
            editButton.setSize(100, cellHeight);
            editButton.setLocation(deleteButton.getX() - paddingSmall - editButton.getWidth(), 0);
            itemContainerView.add(editButton);

            Label label = new Label();
            label.setText(item.getText());
            label.setLocation(0, 0);
            label.setSize(editButton.getX() - 2 * paddingSmall, cellHeight);
            itemContainerView.add(label);
            y += label.getHeight() + paddingNormal;

            int index = i;
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int n = JOptionPane.showConfirmDialog(null, "Do you really want to delete it?", "Title",JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
//                        todoListService.delete(item);
                        todoListService.delete(index);
                        containerView.remove(itemContainerView);
                        dataToView();
                    }

                }

            });

            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newString = JOptionPane.showInputDialog(null, "please input new item:\n", item.getText());


                    if (newString != null && !newString.trim().equals("") && !newString.trim().equals(item.getText())) {
//                        item.setText(newString);
                        todoListService.edit(index, newString);
                        dataToView();
                    }


                }
            });


        }


    }
}
