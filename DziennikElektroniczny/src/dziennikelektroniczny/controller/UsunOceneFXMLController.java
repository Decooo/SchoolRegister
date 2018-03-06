/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Grupa;
import dziennikelektroniczny.model.Ocena;
import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.PrzedmiotyGrupy;
import dziennikelektroniczny.model.Student;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
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
import javafx.util.StringConverter;

public class UsunOceneFXMLController implements Initializable {

    @FXML
    private ComboBox cBoxWyborStudenta;
    @FXML
    private ComboBox cBoxWyborPrzedmiotu;
    @FXML
    private ComboBox cBoxWyborOceny;
    @FXML
    private TextArea tAreaWyniki;

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

        try {
            cBoxWyborOceny.setConverter(new StringConverter<Ocena>() {
                @Override
                public Ocena fromString(String string) {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public String toString(Ocena object) {
                    return object.getOcena() + ", " + object.getOpis_oceny();
                }
            });
            int index = cBoxWyborStudenta.getSelectionModel().getSelectedIndex();
            Student wybranyStudent = dziennikelektroniczny.modelDAO.StudentDAO.wyborStudenta().get(index);
            index = cBoxWyborPrzedmiotu.getSelectionModel().getSelectedIndex();
            ArrayList<PrzedmiotyGrupy> list = dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.przedmiotyDanejGrupy(wybranyStudent.getNr_albumu());
            cBoxWyborOceny.setItems(dziennikelektroniczny.modelDAO.OcenaDAO.wyszukajOceneNrAlbumuPrzedmiot(wybranyStudent.getNr_albumuString(), list.get(index).getId_przedmiotu()));
            cBoxWyborOceny.getSelectionModel().select(0);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DodajOceneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void zmianaPrzedmiotuAction(ActionEvent event) {
        try {
            cBoxWyborOceny.setConverter(new StringConverter<Ocena>() {
                @Override
                public Ocena fromString(String string) {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public String toString(Ocena object) {
                    return object.getOcena() + ", " + object.getOpis_oceny();
                }
            });
            int index = cBoxWyborStudenta.getSelectionModel().getSelectedIndex();
            Student wybranyStudent = dziennikelektroniczny.modelDAO.StudentDAO.wyborStudenta().get(index);
            index = cBoxWyborPrzedmiotu.getSelectionModel().getSelectedIndex();
            ArrayList<PrzedmiotyGrupy> list = dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.przedmiotyDanejGrupy(wybranyStudent.getNr_albumu());
            cBoxWyborOceny.setItems(dziennikelektroniczny.modelDAO.OcenaDAO.wyszukajOceneNrAlbumuPrzedmiot(wybranyStudent.getNr_albumuString(), list.get(index).getId_przedmiotu()));
            cBoxWyborOceny.getSelectionModel().select(0);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DodajOceneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void usunOceneAction(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        //odczytanie id wybranej oceny
        int indexO = cBoxWyborOceny.getSelectionModel().getSelectedIndex();
        int indexP = cBoxWyborPrzedmiotu.getSelectionModel().getSelectedIndex();
        int indexS = cBoxWyborStudenta.getSelectionModel().getSelectedIndex();
        Student wybranyStudent = dziennikelektroniczny.modelDAO.StudentDAO.wyborStudentaID().get(indexS);
        ArrayList<PrzedmiotyGrupy> list = dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.przedmiotyDanejGrupy(wybranyStudent.getNr_albumu());

        ObservableList<Ocena> o = dziennikelektroniczny.modelDAO.OcenaDAO.wyborOceny(wybranyStudent.getNr_albumuString(), list.get(indexP).getId_przedmiotu());
        System.out.println("indeks przedmiotu: " + list.get(indexP).getId_przedmiotu());
        System.out.println("indeks oceny: " + dziennikelektroniczny.modelDAO.OcenaDAO.wyborOceny(wybranyStudent.getNr_albumuString(), list.get(indexP).getId_przedmiotu()).get(indexO));
        Ocena wybranaOcena = dziennikelektroniczny.modelDAO.OcenaDAO.wyborOceny(wybranyStudent.getNr_albumuString(), list.get(indexP).getId_przedmiotu()).get(indexO);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie oceny");
        alert.setHeaderText("Czy napewno chcesz usunąć wybraną ocene?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            dziennikelektroniczny.modelDAO.OcenaDAO.usunOceneId(String.valueOf(wybranaOcena.getId_oceny()));
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Usuwanie oceny");
            alert1.setHeaderText("Usunięto wybraną ocene");
            alert1.getButtonTypes().setAll(ButtonType.OK);
            alert1.showAndWait();
            zmianaPrzedmiotuAction(event);
        } else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Usuwanie oceny");
            alert1.setHeaderText("Anulowano usuwanie oceny");
            alert1.getButtonTypes().setAll(ButtonType.OK);
            alert1.showAndWait();
        }
    }
}
