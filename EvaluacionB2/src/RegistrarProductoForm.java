import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrarProductoForm extends JFrame {
    private JTextField codigoField, nombreField, descripcionField, precioField, cantidadField, categoriaField;
    private JButton registrarButton, buscarButton;

    public RegistrarProductoForm() {
        super("Registrar Producto");

        codigoField = new JTextField(10);
        nombreField = new JTextField(20);
        descripcionField = new JTextField(20);
        precioField = new JTextField(10);
        cantidadField = new JTextField(10);
        categoriaField = new JTextField(20);
        registrarButton = new JButton("Registrar");
        buscarButton = new JButton("Buscar por Código");

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica de conexión y guardado en la base de datos
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para búsqueda por código
            }
        });

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Código:"));
        panel.add(codigoField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Descripción:"));
        panel.add(descripcionField);
        panel.add(new JLabel("Precio:"));
        panel.add(precioField);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadField);
        panel.add(new JLabel("Categoría:"));
        panel.add(categoriaField);
        panel.add(registrarButton);
        panel.add(buscarButton);

        getContentPane().add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public void mostrarInterfaz() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

    public void establecerVisible(boolean visible) {
        setVisible(visible);
    }
}
