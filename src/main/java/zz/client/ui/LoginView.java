package zz.client.ui;

import com.zzpublic.zwing.Button;
import com.zzpublic.zwing.Label;
import com.zzpublic.zwing.TextField;
import com.zzpublic.zwing.View;
import zz.client.service.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends View {

    private UserService userService = new UserService();

    private TextField usernameTextField;
    private TextField passwordTextField;
    private Button registerButton;
    private Button loginButton;
    private Label messageLabel;


    @Override
    protected void initSubviews() {
        super.initSubviews();

        View panel = new View();
        panel.setSize(200, 400);
        panel.setLocation((this.getWidth() - panel.getWidth()) / 2, 100); //居中代码
        this.add(panel);

        usernameTextField = new TextField();
        usernameTextField.setLocation(0, 0);
        usernameTextField.setSize(panel.getWidth(), 40);
        panel.add(usernameTextField);

        passwordTextField = new TextField();
        passwordTextField.setLocation(0, 50);
        passwordTextField.setSize(panel.getWidth(), 40);
        panel.add(passwordTextField);

        registerButton = new Button("Register");
        registerButton.setSize(95, 40);
        registerButton.setLocation(0, 100);
        panel.add(registerButton);

        loginButton = new Button("Login");
        loginButton.setSize(95, 40);
        loginButton.setLocation(105, 100);
        panel.add(loginButton);

        messageLabel = new Label();
        messageLabel.setSize(panel.getWidth(), 30);
        messageLabel.setLocation(0, 150);
        panel.add(messageLabel);
    }

    @Override
    protected void initEvents() {
        super.initEvents();

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean status = userService.register(username, password);
                        messageLabel.setText(status ? "success" : "failure");
                    }
                }).start();

            }
        });
    }
}