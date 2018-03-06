/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Ocena;
import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.Student;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class WyszukiwanieOcenStudentaFXMLController implements Initializable {

    @FXML
    private ComboBox cBoxWyborStudenta;
    @FXML
    private ComboBox cBoxPrzedmioty;
    @FXML
    private TableColumn<Ocena, String> tColumnId;
    @FXML
    private TableColumn<Ocena, Integer> tColumnNrAlbumu;
    @FXML
    private TableColumn<Ocena, String> tColumnOcena;
    @FXML
    private TableColumn<Ocena, String> tColumnOpis;
    @FXML
    private TableView tableViewOceny;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cBoxPrzedmioty.setConverter(new StringConverter<Przedmiot>() {
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
            cBoxPrzedmioty.setItems(dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmioty());
            cBoxPrzedmioty.getSelectionModel().select(0);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DodajOceneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DodajOceneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //liczba porzadkowa
        tColumnId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ocena, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Ocena, String> p) {
                return new ReadOnlyObjectWrapper(tableViewOceny.getItems().indexOf(p.getValue()) + 1 + "");
            }
        });
        tColumnId.setSortable(false);

        tColumnNrAlbumu.setCellValueFactory(cellData -> cellData.getValue().nr_albumuProperty().asObject());
        tColumnOcena.setCellValueFactory(cellData -> cellData.getValue().ocenaProperty());
        tColumnOpis.setCellValueFactory(cellData -> cellData.getValue().opis_ocenyProperty());
    }

    @FXML
    private void wyszukiwanieOcenyAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        int indexS = cBoxWyborStudenta.getSelectionModel().getSelectedIndex();
        Student wybranyStudent = dziennikelektroniczny.modelDAO.StudentDAO.wyborStudenta().get(indexS);
        int index = cBoxPrzedmioty.getSelectionModel().getSelectedIndex();
        Przedmiot wybranyPrzedmiot = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyborPrzedmiotu().get(index);
        ObservableList<Ocena> stuData = dziennikelektroniczny.modelDAO.OcenaDAO.wyszukajOceneNrAlbumuPrzedmiot(wybranyStudent.getNr_albumuString(), wybranyPrzedmiot.getId_przedmiotu());
        wszystkieOceny(stuData);
    }

    private void pokazOcene(Ocena oce) {
        if (oce != null) {
            wyswietlOcene(oce);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wyszukiwanie ocen studenta");
            alert.setHeaderText("Student nie ma ocen z tego przedmiotu");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void wyswietlOcene(Ocena oce) {
        ObservableList<Ocena> stuOcena = FXCollections.observableArrayList();
        stuOcena.add(oce);
        tableViewOceny.setItems(stuOcena);
    }

    @FXML
    private void wszystkieOceny(ObservableList<Ocena> stuData) {
        tableViewOceny.setItems(stuData);
        if (stuData.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wyszukiwanie ocen studenta");
            alert.setHeaderText("Student nie ma ocen z tego przedmiotu");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }
}
