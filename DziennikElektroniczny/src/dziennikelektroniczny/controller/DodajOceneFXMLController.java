/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Grupa;
import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.PrzedmiotyGrupy;
import dziennikelektroniczny.model.Student;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
public class DodajOceneFXMLController implements Initializable {

    @FXML
    private ComboBox cBoxWyborStudenta;
    @FXML
    private TextField textFieldOpisOceny;
    @FXML
    private ComboBox cBoxWyborOceny;
    @FXML
    private ComboBox cBoxWyborPrzedmiotu;

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

        try {
            cBoxWyborPrzedmiotu.setConverter(new StringConverter<Przedmiot>() {
                @Override
                public Przedmiot fromString(String string) {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public String toString(Przedmiot object) {
                    return object.getNazwa();
                }
            });
            int indexS = cBoxWyborStudenta.getSelectionModel().getSelectedIndex();
            Student wybranyStudent = dziennikelektroniczny.modelDAO.StudentDAO.wyborStudentaID().get(indexS);
            ArrayList<PrzedmiotyGrupy> lista = dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.przedmiotyDanejGrupy(wybranyStudent.getNr_albumu());
            String przedmioty = "";
            for (Iterator<PrzedmiotyGrupy> iterator = lista.iterator(); iterator.hasNext();) {
                PrzedmiotyGrupy next = iterator.next();
                ObservableList<Przedmiot> przeData = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmiotIdGrupy(next.getId_przedmiotuString());
                przedmioty += next.getId_przedmiotuString() + "' OR id_przedmiotu='";
            }
            String zapytanie = przedmioty.substring(0, przedmioty.length() - 20);
            cBoxWyborPrzedmiotu.setItems(dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmiotIdGrupy(zapytanie));
            cBoxWyborPrzedmiotu.getSelectionModel().select(0);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DodajOceneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //dodanie ocen do combobox
        cBoxWyborOceny.getItems().addAll(
                "2",
                "3",
                "+3",
                "4",
                "+4",
                "5"
        );
        cBoxWyborOceny.getSelectionModel().select(0);

    }

    @FXML
    private void dodajOceneAction(ActionEvent event) throws ClassNotFoundException, Exception {
        try {
            if (!dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldOpisOceny.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie oceny");
                alert.setHeaderText("Nie podano wszystkich danych");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            } else {
                int indexS = cBoxWyborStudenta.getSelectionModel().getSelectedIndex();
                Student wybranyStudent = dziennikelektroniczny.modelDAO.StudentDAO.wyborStudenta().get(indexS);
                int index = cBoxWyborPrzedmiotu.getSelectionModel().getSelectedIndex();
                //Przedmiot wybranyPrzedmiot = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyborPrzedmiotu().get(index);
                ArrayList<PrzedmiotyGrupy> list = dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.przedmiotyDanejGrupy(wybranyStudent.getNr_albumu());
                String ocena = cBoxWyborOceny.getSelectionModel().getSelectedItem().toString();
                dziennikelektroniczny.modelDAO.OcenaDAO.dodajOcene(Integer.parseInt(wybranyStudent.getNr_albumuString()), list.get(index).getId_przedmiotu(), ocena, textFieldOpisOceny.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie oceny");
                alert.setHeaderText("Poprawnie dodano ocene");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
                textFieldOpisOceny.setText("");

            }

        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu studenta (controller)");
            e.printStackTrace();
        }
    }

    @FXML
    private void zmianaStudentaAction(ActionEvent event) {
        try {
            cBoxWyborPrzedmiotu.setConverter(new StringConverter<Przedmiot>() {
                @Override
                public Przedmiot fromString(String string) {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public String toString(Przedmiot object) {
                    return object.getNazwa();
                }
            });
            int indexS = cBoxWyborStudenta.getSelectionModel().getSelectedIndex();
            Student wybranyStudent = dziennikelektroniczny.modelDAO.StudentDAO.wyborStudentaID().get(indexS);
            ArrayList<PrzedmiotyGrupy> lista = dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.przedmiotyDanejGrupy(wybranyStudent.getNr_albumu());
            String przedmioty = "";
            for (Iterator<PrzedmiotyGrupy> iterator = lista.iterator(); iterator.hasNext();) {
                PrzedmiotyGrupy next = iterator.next();
                ObservableList<Przedmiot> przeData = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmiotIdGrupy(next.getId_przedmiotuString());
                przedmioty += next.getId_przedmiotuString() + "' OR id_przedmiotu='";
            }
            String zapytanie = przedmioty.substring(0, przedmioty.length() - 20);
            cBoxWyborPrzedmiotu.setItems(dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmiotIdGrupy(zapytanie));
            cBoxWyborPrzedmiotu.getSelectionModel().select(0);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DodajOceneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
