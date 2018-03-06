/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class MenuFXMLController implements Initializable {

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void dodajStudentaAction(ActionEvent event) throws IOException {
        zmianaSceny("DodajStudentaFXML");
    }
    
    @FXML
    private void przydzielPrzedmiotAction(ActionEvent event) throws IOException {
        zmianaSceny("PrzydzielPrzedmiotFXML");
    }
    
    @FXML
    private void usuwaniePrzedmiotuAction(ActionEvent event) throws IOException {
        zmianaSceny("UsuwaniePrzedmiotuGrupyFXML");
    }
    
    @FXML
    private void wyszukajPrzedmiotyGrupyAction(ActionEvent event) throws IOException {
        zmianaSceny("WyszukajPrzedmiotyGrupyFXML");
    }

    @FXML
    private void znajdzGrupeAction(ActionEvent event) throws IOException {
        zmianaSceny("WyszukajGrupeFXML");
    }

    @FXML
    private void wyszukajOcenyAction(ActionEvent event) throws IOException {
        zmianaSceny("WyszukiwanieOcenStudentaFXML");
    }

    @FXML
    private void dodajOceneAction(ActionEvent event) throws IOException {
        zmianaSceny("DodajOceneFXML");
    }

    @FXML
    private void usunOceneAction(ActionEvent event) throws IOException {
        zmianaSceny("UsunOceneFXML");
    }

    @FXML
    private void dodajPrzedmiotAction(ActionEvent event) throws IOException {
        zmianaSceny("DodajPrzedmiotFXML");
    }

    @FXML
    private void usunPrzedmiotAction(ActionEvent event) throws IOException {
        zmianaSceny("UsunPrzedmiotFXML");
    }

    @FXML
    private void wyszukajPrzedmiotAction(ActionEvent event) throws IOException {
        zmianaSceny("WyszukajPrzedmiotFXML");
    }

    @FXML
    private void dodajWykladowceAction(ActionEvent event) throws IOException {
        zmianaSceny("DodajWykladowceFXML");
    }

    @FXML
    private void usunWykladowceAction(ActionEvent event) throws IOException {
        zmianaSceny("UsunWykladowceFXML");
    }


    @FXML
    private void wyszukajStudentaAction(ActionEvent event) {
        zmianaSceny("WyszukajStudentaFXML");
    }

    @FXML
    private void usunStudentaAction(ActionEvent event) {
        zmianaSceny("UsunStudentaFXML");
    }

    @FXML
    private void zaaktualizujDaneStudentaAction(ActionEvent event) {
        zmianaSceny("ZaaktualizujDaneStudentaFXML");
    }

    @FXML
    private void wyszukajWykladowceAction(ActionEvent event) {
        zmianaSceny("WyszukajWykladowceFXML");
    }

    private void zmianaSceny(String nazwa) {
        try {
            dziennikelektroniczny.DziennikElektroniczny.initMenu();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(dziennikelektroniczny.DziennikElektroniczny.class.getResource("view/" + nazwa + ".fxml"));
            AnchorPane fxmlView = (AnchorPane) loader.load();
            dziennikelektroniczny.DziennikElektroniczny.getMenuLayout().setCenter(fxmlView);
        } catch (IOException e) {
            System.err.println("Błąd przy ustawianiu sceny: " + nazwa + " !");
            e.printStackTrace();
        }
    }

}
