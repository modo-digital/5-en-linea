/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class InformacionController implements Initializable {

    @FXML
    private Label informacion;
    
    @FXML
    private Button espanol;
    
    @FXML
    private Button ingles;
    
    @FXML 
    private Label materia;
            
    @FXML 
    private Label nombreMateria;
    
    @FXML 
    private Label alumnas;
    
    @FXML
    private Label alumna1;
    
    @FXML
    private Label alumna2;
    
    @FXML
    private Label version;
    
    @FXML
    private Button Bregresar;
    
    ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;
    }   
    
    public void configurarIdioma(){
        informacion.setText(resources.getString("labelInformacion"));
        espanol.setText(resources.getString("espanol"));
        ingles.setText(resources.getString("ingles"));
        materia.setText(resources.getString("materia"));
        nombreMateria.setText(resources.getString("nombreMateria"));
        alumnas.setText(resources.getString("alumnas"));
        alumna1.setText(resources.getString("alumna1"));
        alumna2.setText(resources.getString("alumna2"));
        version.setText(resources.getString("version"));
        Bregresar.setText(resources.getString("regresar"));
    }
    
    @FXML
    public void cambiarIdiomaUS(){
        resources = ResourceBundle.getBundle("resources.idioma_en_US");
        configurarIdioma();
    }
    
    @FXML
    public void cambiarIdiomaMX(){
        resources = ResourceBundle.getBundle("resources.idioma_es_MX");
        configurarIdioma();
        
    }
    
}