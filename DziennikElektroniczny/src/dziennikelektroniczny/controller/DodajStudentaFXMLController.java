/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Grupa;
import java.awt.Insets;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class DodajStudentaFXMLController implements Initializable {

    @FXML
    private TextField textFieldImie;
    @FXML
    private TextField textFieldNazwisko;
    @FXML
    private TextField textFieldPesel;
    @FXML
    private TextField textFieldIdGrupy;
    @FXML
    private ComboBox cBoxWyborGrupy;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cBoxWyborGrupy.setConverter(new StringConverter<Grupa>() {
                @Override
                public Grupa fromString(String string) {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public String toString(Grupa object) {
                    return object.getId_grupyString();
                }
            });
            cBoxWyborGrupy.setItems(dziennikelektroniczny.modelDAO.GrupaDAO.wyborGrupy());
            cBoxWyborGrupy.getSelectionModel().select(0);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DodajStudentaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void dodajStudentaAction(ActionEvent event) throws Exception {
        try {
            if (!dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldImie.getText())
                    || !dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldNazwisko.getText())
                    || !dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldPesel.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie studenta");
                alert.setHeaderText("Nie podano wszystkich danych");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else if (!dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldImie.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie studenta");
                alert.setHeaderText("Imie zawiera nieprawidlowe znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else if (!dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldNazwisko.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie studenta");
                alert.setHeaderText("Nazwisko zawiera nieprawidlowe znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else if (!dziennikelektroniczny.custom.TylkoLiczby.czySameLiczby(textFieldPesel.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie studenta");
                alert.setHeaderText("W peselu użyto nieprawidłowych znaków");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else if (!dziennikelektroniczny.custom.DlugoscTekstu.czyJest11Znakow(textFieldPesel.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie studenta");
                alert.setHeaderText("Pesel ma nieodpowiednia dlugosc");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else {
                int index = cBoxWyborGrupy.getSelectionModel().getSelectedIndex();
                Grupa wybranaGrupa = dziennikelektroniczny.modelDAO.GrupaDAO.wyborGrupy().get(index);
                dziennikelektroniczny.modelDAO.StudentDAO.DodajStudenta(textFieldImie.getText(), textFieldNazwisko.getText(), textFieldPesel.getText(), Integer.parseInt(wybranaGrupa.getId_grupyString()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie studenta");
                alert.setHeaderText("Poprawnie dodano nowego studenta");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
                textFieldImie.setText("");
                textFieldNazwisko.setText("");
                textFieldPesel.setText("");
            }
        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu studenta (controller)");
            e.printStackTrace();
        }

    }
}
