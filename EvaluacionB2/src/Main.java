import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Login loginForm = new Login();

        JFrame frame = new JFrame("Sistema de Gestión de Productos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.pack();
        frame.setVisible(true);
    }
}
