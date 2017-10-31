package GUI;

import cincoenlinea.CincoEnLinea;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private Label menuPrincipal;

    @FXML
    private JFXButton espanol;

    @FXML
    private JFXButton ingles;

    @FXML
    private Button iniciarPartida;

    @FXML
    private Button hamburgerReglas;

    @FXML
    private Button reglas;

    @FXML
    private JFXDrawer draweMenu;

    @FXML
    private Button raking;

    @FXML
    private Button informacion;

    ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");
    //private MenuPrincipalController controller = new MenuPrincipalController();
    private Stage stage = new Stage();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;

        hamburgerReglas.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Reglas.fxml"), resources);
                draweMenu.setSidePane(pane);
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            draweMenu.open();
        });

        informacion.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Informacion.fxml"), resources);
                draweMenu.setSidePane(pane);
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            draweMenu.open();

        });

        raking.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Ranking.fxml"), resources);
                draweMenu.setSidePane(pane);
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            draweMenu.open();

        });

        iniciarPartida.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("SeleccionarJugador.fxml"), resources);
                 Scene scenePartida = new Scene(pane);
                stage.setScene(scenePartida);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    public void configurarIdioma() {
        menuPrincipal.setText(resources.getString("menuPrincipal"));
        iniciarPartida.setText(resources.getString("iniciarPartida"));
        hamburgerReglas.setText(resources.getString("reglas"));
        raking.setText(resources.getString("ranking"));
        informacion.setText(resources.getString("informacion"));
    }

    @FXML
    public void cambiarIdiomaUS() {
        resources = ResourceBundle.getBundle("resources.idioma_en_US");
        configurarIdioma();
    }

    @FXML
    public void cambiarIdiomaMX() {
        resources = ResourceBundle.getBundle("resources.idioma_es_MX");
        configurarIdioma();
    }

}
