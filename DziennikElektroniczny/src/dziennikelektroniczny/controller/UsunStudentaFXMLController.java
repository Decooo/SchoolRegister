/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Student;
import dziennikelektroniczny.model.Wykladowca;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class UsunStudentaFXMLController implements Initializable {

    @FXML
    private ComboBox cBoxWyborStudenta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            cBoxWyborStudenta.setConverter(new StringConverter<Student>() {
                @Override
                public Student fromString(String string) {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public String toString(Student object) {
                    return object.getNr_albumu() + ", " + object.getNazwisko() + ", " + object.getImie();
                }
            });
            cBoxWyborStudenta.setItems(dziennikelektroniczny.modelDAO.StudentDAO.WyszukajWszystkichStudentow());
            cBoxWyborStudenta.getSelectionModel().select(0);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DodajOceneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void usunStudentaAction(ActionEvent event) throws Exception {
        int index = cBoxWyborStudenta.getSelectionModel().getSelectedIndex();
        Student wybranyStudent = dziennikelektroniczny.modelDAO.StudentDAO.wyborStudenta().get(index);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie studenta");
        alert.setHeaderText("Czy napewno chcesz usunąć wybranego studenta ?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            dziennikelektroniczny.modelDAO.StudentDAO.usunStudentaNrAlbumu(wybranyStudent.getNr_albumuString());
            dziennikelektroniczny.modelDAO.OcenaDAO.usunOceneNrAlbumu(wybranyStudent.getNr_albumuString());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Usuwanie studenta");
            alert1.setHeaderText("Usunieto studenta i wszystkie jego oceny");
            alert1.getButtonTypes().setAll(ButtonType.OK);
            alert1.showAndWait();
            //odświeżenie ComboBoxa
            cBoxWyborStudenta.setConverter(new StringConverter<Student>() {
                @Override
                public Student fromString(String string) {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public String toString(Student object) {
                    return object.getNr_albumu() + ", " + object.getNazwisko() + ", " + object.getImie();
                }
            });
            cBoxWyborStudenta.setItems(dziennikelektroniczny.modelDAO.StudentDAO.WyszukajWszystkichStudentow());
            cBoxWyborStudenta.getSelectionModel().select(0);

        } else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Usuwanie studenta");
            alert1.setHeaderText("Anulowano usuwanie studenta");
            alert1.getButtonTypes().setAll(ButtonType.OK);
            alert1.showAndWait();
        }
    }
}
