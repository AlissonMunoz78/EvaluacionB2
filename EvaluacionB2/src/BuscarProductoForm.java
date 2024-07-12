import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BuscarProductoForm extends JFrame {
    private JTextField codigoField;
    private JButton buscarButton, volverButton;

    public BuscarProductoForm() {
        super("Buscar Producto");

        codigoField = new JTextField(10);
        buscarButton = new JButton("Buscar");
        volverButton = new JButton("Volver a Registrar Productos");

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para búsqueda por código en la base de datos
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar esta ventana y volver a la ventana de registrar productos
            }
        });

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Código:"));
        panel.add(codigoField);
        panel.add(buscarButton);
        panel.add(volverButton);

        getContentPane().add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    public void mostrarInterfaz() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

    public void establecerVisible(boolean visible) {
        setVisible(visible);
    }
}
