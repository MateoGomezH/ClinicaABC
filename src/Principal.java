import view.VentanaPrincipal;
import controller.Controlador;

public class Principal {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();

        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(controlador);
        ventanaPrincipal.setVisible(true);
    }
}