/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Przedmiot;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class WyszukajPrzedmiotFXMLController implements Initializable {

    @FXML
    private TextField textFieldNazwa;
    @FXML
    private TextField textFieldTypPrzedmiotu;
    @FXML
    private TableView<Przedmiot> tableViewPrzedmioty;
    @FXML
    private TableColumn<Przedmiot, String> tColumnId;
    @FXML
    private TableColumn<Przedmiot, String> tColumnNazwa;
    @FXML
    private TableColumn<Przedmiot, String> tColumnTypPrzedmiotu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //liczba porzadkowa
        tColumnId.setCellValueFactory(new Callback<CellDataFeatures<Przedmiot, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Przedmiot, String> p) {
                return new ReadOnlyObjectWrapper(tableViewPrzedmioty.getItems().indexOf(p.getValue())+1 + "");
            }
        });
        tColumnId.setSortable(false);
        
        tColumnNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        tColumnTypPrzedmiotu.setCellValueFactory(cellData -> cellData.getValue().typ_przedmiotuProperty());
    }

    @FXML
    private void pokazWszystkiePrzedmiotyAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Przedmiot> przeData = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmioty();
            wszystkiePrzedmioty(przeData);
        } catch (SQLException e) {
            System.err.println("Błąd przy pokazywaniu wszystkich przedmiotów w tabeli");
            throw e;
        }
    }

    @FXML
    private void pokazPrzedmiotAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldNazwa.getText())) {
            ObservableList<Przedmiot> przeData = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmiotNazwa(textFieldNazwa.getText());
            wszystkiePrzedmioty(przeData);
        } else if (dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldTypPrzedmiotu.getText())) {
            ObservableList<Przedmiot> przeData = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmiotTypPrzedmiotu(textFieldTypPrzedmiotu.getText());
            wszystkiePrzedmioty(przeData);

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wyszukiwanie przedmiotu");
            alert.setHeaderText("Nie podano żadnych danych");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void wszystkiePrzedmioty(ObservableList<Przedmiot> przeData) {
        tableViewPrzedmioty.setItems(przeData);
        if (przeData.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wyszukiwanie przedmiotu");
            alert.setHeaderText("Nie znaleziono przedmiotu");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();

        }
    }

}
