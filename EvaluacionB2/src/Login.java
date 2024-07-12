import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private String url = "jdbc:mysql://localhost:3306/productos_cuidado_personal";
    private String user = "root";
    private String password = "123";
    private JPanel panel1;
    private JTextField bienvenidosALaAppProductosTextField;
    private JTextField ingreseSuUsuarioTextField;
    private JTextField ingreseSuContraseñaTextField;
    private JButton inicarSesionButton;

    public Login() {
        super("Login");

        panel = new JPanel(new GridLayout(3, 2));
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar Sesión");

        panel.add(new JLabel("Usuario:"));
        panel.add(usernameField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(passwordField);
        panel.add(new JLabel("")); // Espacio en blanco para alinear botón
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (validarCredenciales(username, password)) {
                    // Aquí iría el código para abrir la ventana de Búsqueda de Productos
                    abrirVentanaBusquedaProductos();
                } else {
                    JOptionPane.showMessageDialog(Login.this,
                            "Usuario o contraseña incorrectos",
                            "Error de inicio de sesión",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    private boolean validarCredenciales(String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, user, this.password)) {
            String query = "SELECT * FROM usuario WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Si hay algún resultado, las credenciales son válidas

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(Login.this,
                    "Error al validar las credenciales: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void abrirVentanaBusquedaProductos() {
        // Abrir la ventana de Búsqueda de Productos
        ProductosApp busquedaProductos = new ProductosApp(url, user, password);
        busquedaProductos.setVisible(true);

        // Cerrar la ventana de Login después de iniciar sesión correctamente
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
    }
}
