package zz.client.ui;


import com.zzpublic.zwing.Button;
import com.zzpublic.zwing.Label;
import com.zzpublic.zwing.TextField;
import com.zzpublic.zwing.View;
import zz.client.service.UserService;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView_1 extends View {
    // mark - service

    private UserService userService = new UserService();

    // mark - views

    private static final int paddingNormal = 10;
    private static final int paddingSmall = 5;
    private static final int paddingCentre = 245;

    private static final int cellHeight = 30;
    private static final int panelHeight = 40;

    private static final int windowPadding = 40;

    private TextField usernameField;
    private JPasswordField pwdField;
    private Button registerButton;
    private Button resetButton;
    private Label statusLabel;


    @Override
    protected void initSubviews() {
        super.initSubviews();

        // title
        Label titleLabel = new Label();
        titleLabel.setLocation(paddingNormal, paddingNormal);
        titleLabel.setSize(this.getWidth() - 2 * paddingNormal, cellHeight);
        titleLabel.setText("Register");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        // username inputView
        View usernameContainer = new View();
        usernameContainer.setLocation(paddingCentre, titleLabel.getY() + titleLabel.getHeight() + paddingNormal);
        usernameContainer.setSize(this.getWidth() - 2 * paddingCentre, panelHeight);
        this.add(usernameContainer);

        Label usernameLabel = new Label();
        usernameLabel.setSize(100, cellHeight);
        usernameLabel.setLocation(paddingSmall, paddingSmall);
        usernameLabel.setText("User Name: ");
        usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        usernameContainer.add(usernameLabel);

        usernameField = new TextField();
        usernameField.setSize(200, cellHeight);
        usernameField.setLocation(usernameLabel.getX() + usernameLabel.getWidth()+ paddingSmall, paddingSmall);
        usernameContainer.add(usernameField);

        // pwd inputView
        View pwdContainer = new View();
        pwdContainer.setLocation(paddingCentre, usernameContainer.getY() + usernameContainer.getHeight() + paddingNormal);
        pwdContainer.setSize(this.getWidth() - 2 * paddingCentre, panelHeight);
        this.add(pwdContainer);

        Label pwdLabel = new Label();
        pwdLabel.setSize(100, cellHeight);
        pwdLabel.setLocation(paddingSmall, paddingSmall);
        pwdLabel.setText("Password: ");
        pwdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pwdContainer.add(pwdLabel);

        pwdField = new JPasswordField();
        pwdField.setSize(200, cellHeight);
        pwdField.setLocation(pwdLabel.getX() + pwdLabel.getWidth()+ paddingSmall, paddingSmall);
        pwdContainer.add(pwdField);

        // Button Container
        View btnContainer = new View();
        btnContainer.setLocation(paddingCentre, pwdContainer.getY() + pwdContainer.getHeight() + paddingNormal);
        btnContainer.setSize(this.getWidth() - 2 * paddingCentre, panelHeight);
        this.add(btnContainer);

        registerButton = new Button();
        registerButton.setText("Register");
        registerButton.setLocation(paddingSmall, paddingSmall);
        registerButton.setSize(100, cellHeight);
        btnContainer.add(registerButton);

        resetButton = new Button();
        resetButton.setText("Reset");
        resetButton.setSize(100, cellHeight);
        resetButton.setLocation(btnContainer.getWidth() - resetButton.getWidth(), paddingSmall);
        btnContainer.add(resetButton);

        statusLabel = new Label();
        statusLabel.setSize(this.getWidth() - 2 * paddingNormal, cellHeight);
        statusLabel.setLocation(paddingNormal, btnContainer.getY() + btnContainer.getHeight() + paddingNormal);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(statusLabel);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String pwd = String.valueOf(pwdField.getPassword()).trim();

                if (username.equals("") || pwd.equals("")) {
                    statusLabel.setText("username or password cannot be empty !");
                    statusLabel.setForeground(Color.red);
                    return;
                }

//                User user = new User();
//                user.setUsername(username);
//                user.setPassword(MD5.md5(pwd));

                boolean success = userService.register(username, pwd);
                if (success) {
                    statusLabel.setText("Register Successful !");
                    statusLabel.setForeground(Color.green);
                }
                else {
                    statusLabel.setText("Register failed !");
                    statusLabel.setForeground(Color.red);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "Reset will clear all your inputs.\nDo you really want to reset?", "Reset",JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    usernameField.setText("");
                    pwdField.setText("");
                    statusLabel.setText("");
                }

            }
        });
    }

/*
    @Override
    protected void viewDidDisplay() {
        super.viewDidDisplay();
        dataToView();

    }
*/

}
