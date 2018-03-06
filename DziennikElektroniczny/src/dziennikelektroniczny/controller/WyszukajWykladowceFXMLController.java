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
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class WyszukajWykladowceFXMLController implements Initializable {

    @FXML
    private TextField textFieldNazwisko;
    @FXML
    private TextField textFieldImie;
    @FXML
    private TableView<Wykladowca> tableViewWykladowcy;
    @FXML
    private TableColumn<Wykladowca, String> tColumnId;
    @FXML
    private TableColumn<Wykladowca, String> tColumnImie;
    @FXML
    private TableColumn<Wykladowca, String> tColumnNazwisko;
    @FXML
    private TableColumn<Wykladowca, String> tColumnEmail;
    @FXML
    private TableColumn<Wykladowca, String> tColumnSpecjalizacja;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //liczba porzadkowa
        tColumnId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Wykladowca, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Wykladowca, String> p) {
                return new ReadOnlyObjectWrapper(tableViewWykladowcy.getItems().indexOf(p.getValue()) + 1 + "");
            }
        });
        tColumnId.setSortable(false);

        tColumnImie.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        tColumnNazwisko.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        tColumnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        tColumnSpecjalizacja.setCellValueFactory(cellData -> cellData.getValue().typ_specjalizacjiProperty());
    }

    @FXML
    private void znajdzWykladowceAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldImie.getText())
                && dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldNazwisko.getText())) {
            if (dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldImie.getText())
                    && dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldNazwisko.getText())) {
                ObservableList<Wykladowca> wykData = dziennikelektroniczny.modelDAO.WykladowcaDAO.WyszukajWykladowceImieNazwisko(textFieldImie.getText(), textFieldNazwisko.getText());
                wszyscyWykladowcy(wykData);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wyszukiwanie wykładowcy");
                alert.setHeaderText("Imie lub nazwisko zawiera złe znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }
        } else if (dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldNazwisko.getText())) {
            if (dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldNazwisko.getText())) {
                ObservableList<Wykladowca> wykData = dziennikelektroniczny.modelDAO.WykladowcaDAO.WyszukajWykladowceNazwisko(textFieldNazwisko.getText());
                wszyscyWykladowcy(wykData);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wyszukiwanie wykładowcy");
                alert.setHeaderText("Nazwisko zawiera zle znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }
        } else if (dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldImie.getText())) {
            if (dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldImie.getText())) {
                ObservableList<Wykladowca> wykData = dziennikelektroniczny.modelDAO.WykladowcaDAO.WyszukajWykladowceImie(textFieldImie.getText());
                wszyscyWykladowcy(wykData);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wyszukiwanie wykładowcy");
                alert.setHeaderText("Imie zawiera złe znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wyszukiwanie wykładowcy");
            alert.setHeaderText("Nie podano żadnych danych");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void pokazWszystkichWykladowcowAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Wykladowca> wykData = dziennikelektroniczny.modelDAO.WykladowcaDAO.WyszukajWszystkichWykladowcow();
            wszyscyWykladowcy(wykData);
        } catch (SQLException e) {
            System.err.println("Błąd przy pokazywaniu wszystkich wykladowcow w tabeli");
            throw e;
        }
    }

    @FXML
    private void wszyscyWykladowcy(ObservableList<Wykladowca> wykData) {
        tableViewWykladowcy.setItems(wykData);
        if (wykData.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wyszukiwanie wykładowcy");
            alert.setHeaderText("Nie znaleziono wykładowcy");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

}
