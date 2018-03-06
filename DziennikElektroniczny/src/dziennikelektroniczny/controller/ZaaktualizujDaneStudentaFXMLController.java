/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Grupa;
import dziennikelektroniczny.model.Student;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class ZaaktualizujDaneStudentaFXMLController implements Initializable {

    @FXML
    private TextField textFieldNazwisko;
    @FXML
    private ComboBox comboBoxGrupa;
    @FXML
    private ComboBox cBoxWyborStudenta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            comboBoxGrupa.setConverter(new StringConverter<Grupa>() {
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
            comboBoxGrupa.setItems(dziennikelektroniczny.modelDAO.GrupaDAO.wyborGrupy());
            comboBoxGrupa.getSelectionModel().select(0);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ZaaktualizujDaneStudentaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ZaaktualizujDaneStudentaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
            Logger.getLogger(ZaaktualizujDaneStudentaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void zaaktualizujNazwiskoAction(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        if (dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldNazwisko.getText())) {
            if (dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldNazwisko.getText())) {
                int index = cBoxWyborStudenta.getSelectionModel().getSelectedIndex();
                Student wybranyStudent = dziennikelektroniczny.modelDAO.StudentDAO.wyborStudenta().get(index);
                dziennikelektroniczny.modelDAO.StudentDAO.zaaktualizujNazwiskoStudenta(wybranyStudent.getNr_albumuString(), textFieldNazwisko.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aktualizowanie danych studenta");
                alert.setHeaderText("Zaaktualizowano dane studenta");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
                //odświeżanie comboBoxa
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aktualizowanie danych studenta");
                alert.setHeaderText("Przy podawaniu nowego nazwiska użyto nieprawidłowych znaków");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aktualizowanie danych studenta");
            alert.setHeaderText("Nie podano nowego nazwiska studenta");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void przeniesDoInnejGrupyAction(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        int indexS = cBoxWyborStudenta.getSelectionModel().getSelectedIndex();
        Student wybranyStudent = dziennikelektroniczny.modelDAO.StudentDAO.wyborStudenta().get(indexS);
        int index = comboBoxGrupa.getSelectionModel().getSelectedIndex();
        Grupa wybranaGrupa = dziennikelektroniczny.modelDAO.GrupaDAO.wyborGrupy().get(index);
        Student stud = dziennikelektroniczny.modelDAO.StudentDAO.wyszukajStudentaGrupa(wybranyStudent.getNr_albumuString(), Integer.parseInt(wybranaGrupa.getId_grupyString()));
        if (stud == null) {
            dziennikelektroniczny.modelDAO.StudentDAO.zaaktualizujGrupeStudenta(wybranyStudent.getNr_albumuString(), Integer.parseInt(wybranaGrupa.getId_grupyString()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aktualizowanie danych studenta");
            alert.setHeaderText("Zaaktualizowano dane studenta");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aktualizowanie danych studenta");
            alert.setHeaderText("Wybrany student jest już w tej grupie");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }

    }

}
