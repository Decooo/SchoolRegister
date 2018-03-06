/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Grupa;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class DodajPrzedmiotFXMLController implements Initializable {

    @FXML
    private TextField textFieldNazwa;
    @FXML
    private TextField textFieldTypPrzedmiotu;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void dodajPrzedmiotAction(ActionEvent event) throws IOException, Exception {
        try {
            if (!dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldNazwa.getText())
                    || !dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldTypPrzedmiotu.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie przedmiotu");
                alert.setHeaderText("Nie podano wszystkich danych");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else {
                dziennikelektroniczny.modelDAO.PrzedmiotDAO.dodajPrzemiot(textFieldNazwa.getText(), textFieldTypPrzedmiotu.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie przedmiotu");
                alert.setHeaderText("Dodano nowy przedmiot");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
                textFieldNazwa.setText("");
                textFieldTypPrzedmiotu.setText("");
            }
        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu przedmiotu (controller)");
            e.printStackTrace();
        }
    }

}
