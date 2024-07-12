import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ProductosApp extends JFrame {
    private JTextField codigoField;
    private JButton buscarButton;
    private JTextArea resultadoArea;

    private String url;
    private String user;
    private String password;
    private JTextField bienvenidosALaAppProductosTextField;

    public ProductosApp(String url, String user, String password) {
        super("Búsqueda de Productos");

        this.url = url;
        this.user = user;
        this.password = password;

        codigoField = new JTextField(20);
        buscarButton = new JButton("Buscar");
        resultadoArea = new JTextArea(10, 30);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Ingrese el código del producto:"));
        panel.add(codigoField);
        panel.add(buscarButton);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    private void buscarProducto() {
        String codigo = codigoField.getText().trim();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM producto WHERE codigo_producto = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nombreProducto = resultSet.getString("nombre");
                String descripcionProducto = resultSet.getString("descripcion");
                double precioProducto = resultSet.getDouble("precio");
                int cantidadProducto = resultSet.getInt("cantidad");
                String categoriaProducto = resultSet.getString("categoria");

                String mensaje = "Nombre del producto: " + nombreProducto + "\n";
                mensaje += "Descripción: " + descripcionProducto + "\n";
                mensaje += "Precio: $" + precioProducto + "\n";
                mensaje += "Cantidad disponible: " + cantidadProducto + "\n";
                mensaje += "Categoría: " + categoriaProducto + "\n\n";

                resultadoArea.setText(mensaje);
            } else {
                resultadoArea.setText("No se encontró ningún producto con el código: " + codigo);
            }

        } catch (SQLException e) {
            resultadoArea.setText("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Este main no se utiliza directamente en la aplicación final,
            // se muestra solo con fines de prueba y desarrollo
            ProductosApp busquedaProductos = new ProductosApp("jdbc:mysql://localhost:3306/productos_cuidado_personal", "root", "123");
            busquedaProductos.setVisible(true);
        });
    }
}
