/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

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
public class DodajWykladowceFXMLController implements Initializable {

    @FXML
    private TextField textFieldImie;
    @FXML
    private TextField textFieldNazwisko;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldSpecjalizacja;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void dodajWykladowceAction(ActionEvent event) throws Exception {
        try {
            if (!dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldImie.getText())
                    || !dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldNazwisko.getText())
                    || !dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldEmail.getText())
                    || !dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldSpecjalizacja.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie wykładowcy");
                alert.setHeaderText("Nie podano wszystkich danych");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else if (!dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldImie.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie wykładowcy");
                alert.setHeaderText("Imię zawiera nieprawidłowe znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else if (!dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldNazwisko.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie wykładowcy");
                alert.setHeaderText("Nazwisko zawiera nieprawidłowe znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else if (!dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldSpecjalizacja.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie wykładowcy");
                alert.setHeaderText("Specjalizacja zawiera nieprawidłowe znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else if (!dziennikelektroniczny.custom.PoprawnyEmail.czyEmailJestPoprawny(textFieldEmail.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie wykładowcy");
                alert.setHeaderText("Podany email jest niepoprawny");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else {
                dziennikelektroniczny.modelDAO.WykladowcaDAO.dodajWykladowce(textFieldImie.getText(), textFieldNazwisko.getText(), textFieldEmail.getText(), textFieldSpecjalizacja.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie wykładowcy");
                alert.setHeaderText("Poprawnie dodano nowego wykładowcę");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
                textFieldImie.setText("");
                textFieldNazwisko.setText("");
                textFieldEmail.setText("");
                textFieldSpecjalizacja.setText("");
            }
        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu wykladowcy (controller)");
            e.printStackTrace();
        }
    }
}
