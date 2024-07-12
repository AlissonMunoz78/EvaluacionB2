import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ProductosApp extends JFrame {
    private JLabel resultadoLabel;
    private JButton buscarButton;
    private JTextField codigoField;
    private String url = "jdbc:mysql://localhost:3306/productos_cuidado_personal";
    private String user = "root";
    private String password = "123";

    public ProductosApp() {
        super("Búsqueda de Productos");

        resultadoLabel = new JLabel("Resultado:");
        buscarButton = new JButton("Buscar");
        codigoField = new JTextField(20);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("Ingrese el código del producto:"));
        panel.add(codigoField);
        panel.add(buscarButton);
        panel.add(resultadoLabel);

        getContentPane().add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    public void mostrarInterfaz() {
        SwingUtilities.invokeLater(() -> setVisible(true));
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

                resultadoLabel.setText(mensaje);
            } else {
                resultadoLabel.setText("No se encontró ningún producto con el código: " + codigo);
            }

        } catch (SQLException e) {
            resultadoLabel.setText("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductosApp app = new ProductosApp();
            app.mostrarInterfaz();
        });
    }
}
