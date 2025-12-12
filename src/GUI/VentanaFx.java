package GUI;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * VentanaFxIntegrada
 * Clase que permite abrir una ventana JavaFX desde una aplicacion Swing.
 * Se usa JFXPanel para inicializar JavaFX dentro del proyecto.
 */
public class VentanaFx {

    /** Indica si JavaFX ya fue inicializado */
    private static boolean iniciado = false;

    /**
     * Abre una ventana JavaFX integrada con Swing.
     */
    public static void abrirVentanaFx() {

        // Inicializa JavaFX solo una vez
        if (!iniciado) {
            new JFXPanel(); // inicia el toolkit JavaFX
            iniciado = true;
        }

        // Ejecuta la ventana JavaFX en su hilo
        Platform.runLater(() -> {

            Stage stage = new Stage();

            Label etiqueta = new Label("Ventana JavaFX integrada con Swing");
            Button botonCerrar = new Button("Cerrar");
            botonCerrar.setOnAction(e -> stage.close());

            VBox contenedor = new VBox(10);
            contenedor.getChildren().addAll(etiqueta, botonCerrar);

            Scene escena = new Scene(contenedor, 380, 160);

            stage.setTitle("JavaFX integrada");
            stage.setScene(escena);
            stage.show();
        });
    }
}
