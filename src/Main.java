import javax.swing.SwingUtilities;
import View.TelaPrincipal;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new TelaPrincipal().setVisible(true);

        });

    }

}