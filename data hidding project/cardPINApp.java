import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Scanner;

public class cardPINApp {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;
    private JLabel usernameLabel;
    private static final File CREDENTIALS_FILE = new File("/Users/jaifha_wongkunta/Documents/wuttikan/data hidding project/user.txt");

    public cardPINApp() {
        frame = new JFrame("WiFi Login");
        frame.setSize(450, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 120, 250, 42);
        frame.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(50, 150, 350, 42);
        frame.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 190, 250, 42);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 220, 350, 42);
        frame.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 300, 150, 42);
        frame.add(loginButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(50, 350, 350, 42);
        frame.add(statusLabel);

        loginButton.addActionListener(this::checkLogin);

        frame.setVisible(true);
    }

    private void checkLogin(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        System.out.println("Login button clicked."); // Debugging statement

        if (validateCredentials(username, password)) {
            statusLabel.setText("You're logged in!");
        } else {
            statusLabel.setText("Wrong username or password.");
        }
    }

    private boolean validateCredentials(String username, String password) {
        if (!CREDENTIALS_FILE.exists()) {
            System.out.println("File not found: " + CREDENTIALS_FILE.getAbsolutePath());  // Debugging statement
            return false;
        }

        try (Scanner scanner = new Scanner(CREDENTIALS_FILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Reading line: " + line);  // Debugging line
                String[] credentials = line.split(":");

                if (credentials.length == 2) {
                    System.out.println("Checking username: " + credentials[0] + " with input: " + username);  // Debugging statement
                    System.out.println("Checking password: " + credentials[1] + " with input: " + password);  // Debugging statement
                }

                if (credentials.length == 2 && credentials[0].trim().equals(username.trim()) && credentials[1].trim().equals(password.trim())) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(cardPINApp::new);
    }
}