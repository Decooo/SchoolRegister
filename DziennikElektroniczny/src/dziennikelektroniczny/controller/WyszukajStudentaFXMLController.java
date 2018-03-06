/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.Student;
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
public class WyszukajStudentaFXMLController {

    @FXML
    private TextField textFieldNazwisko;
    @FXML
    private TextField textFieldImie;
    @FXML
    private TextField textFieldNrAlbumu;
    @FXML
    private TextField textFieldPesel;
    @FXML
    private TableView<Student> tableViewStudenci;
    @FXML
    private TableColumn<Student, String> tableColumnLP;
    @FXML
    private TableColumn<Student, Integer> tableColumnNrAlbumu;
    @FXML
    private TableColumn<Student, String> tableColumnImie;
    @FXML
    private TableColumn<Student, String> tableColumnNazwisko;
    @FXML
    private TableColumn<Student, String> tableColumnPesel;
    @FXML
    private TableColumn<Student, Integer> tableColumnIdGrupy;

    @FXML
    public void initialize() {
        //liczba porzadkowa
        tableColumnLP.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> p) {
                return new ReadOnlyObjectWrapper(tableViewStudenci.getItems().indexOf(p.getValue())+1 + "");
            }
        });
        tableColumnLP.setSortable(false);
        
        tableColumnNrAlbumu.setCellValueFactory(cellData -> cellData.getValue().nr_albumuProperty().asObject());
        tableColumnImie.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        tableColumnNazwisko.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        tableColumnPesel.setCellValueFactory(cellData -> cellData.getValue().peselProperty());
        tableColumnIdGrupy.setCellValueFactory(cellData -> cellData.getValue().id_grupyProperty().asObject());

    }

    @FXML
    private void PokazWszystkichStudentowAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Student> stuData = dziennikelektroniczny.modelDAO.StudentDAO.WyszukajWszystkichStudentow();
            wszyscyStudenci(stuData);
        } catch (SQLException e) {
            System.err.println("Błąd przy pokazywaniu wszystkich studentów w tabeli");
            throw e;
        }
    }

    @FXML
    private void ZnajdzStudentaAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldNrAlbumu.getText())) {
            if (dziennikelektroniczny.custom.TylkoLiczby.czySameLiczby(textFieldNrAlbumu.getText())) {
                Student stu = dziennikelektroniczny.modelDAO.StudentDAO.wyszukajStudentaNrAlbumu(textFieldNrAlbumu.getText());
                pokazStudenta(stu);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wyszukiwanie studenta");
                alert.setHeaderText("Nr albumu zawiera złe znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }
        } else if (dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldPesel.getText())) {
            if (dziennikelektroniczny.custom.DlugoscTekstu.czyJest11Znakow(textFieldPesel.getText())
                    && dziennikelektroniczny.custom.TylkoLiczby.czySameLiczby(textFieldPesel.getText())) {
                Student stu = dziennikelektroniczny.modelDAO.StudentDAO.wyszukajStudentaPesel(textFieldPesel.getText());
                pokazStudenta(stu);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wyszukiwanie studenta");
                alert.setHeaderText("Podany pesel ma zła dlugosc lub zawiera zle znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }
        } else if (dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldImie.getText())
                && dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldNazwisko.getText())) {
            if (dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldImie.getText())
                    && dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldNazwisko.getText())) {
                ObservableList<Student> stuData = dziennikelektroniczny.modelDAO.StudentDAO.WyszukajStudentaImieNazwisko(textFieldImie.getText(), textFieldNazwisko.getText());
                wszyscyStudenci(stuData);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wyszukiwanie studenta");
                alert.setHeaderText("Imie lub nazwisko zawiera złe znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }

        } else if (dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldNazwisko.getText())) {
            if (dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldNazwisko.getText())) {
                ObservableList<Student> stuData = dziennikelektroniczny.modelDAO.StudentDAO.WyszukajStudentaNazwisko(textFieldNazwisko.getText());
                wszyscyStudenci(stuData);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wyszukiwanie studenta");
                alert.setHeaderText("Nazwisko zawiera złe znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }
        } else if (dziennikelektroniczny.custom.DlugoscTekstu.czyNieJestPusty(textFieldImie.getText())) {
            if (dziennikelektroniczny.custom.TylkoLitery.czySameLitery(textFieldImie.getText())) {
                ObservableList<Student> stuData = dziennikelektroniczny.modelDAO.StudentDAO.WyszukajStudentaImie(textFieldImie.getText());
                wszyscyStudenci(stuData);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wyszukiwanie studenta");
                alert.setHeaderText("Imie zawiera złe znaki");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wyszukiwanie studenta");
            alert.setHeaderText("Nie podano żadnych danych");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void wszyscyStudenci(ObservableList<Student> stuData) {
        tableViewStudenci.setItems(stuData);
        if (stuData.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wyszukiwanie studenta");
            alert.setHeaderText("Nie znaleziono studenta");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void pokazStudenta(Student stu) {
        if (stu != null) {
            wyswietlStudenta(stu);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wyszukiwanie studenta");
            alert.setHeaderText("Nie ma takiego studenta");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void wyswietlStudenta(Student stu) {

        ObservableList<Student> stuData = FXCollections.observableArrayList();
        stuData.add(stu);
        tableViewStudenci.setItems(stuData);
    }

}
