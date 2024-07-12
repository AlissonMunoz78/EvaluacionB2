import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductosApp app = new ProductosApp();
            app.mostrarInterfaz();
        });
    }
}
