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
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class WyszukajGrupeFXMLController implements Initializable {

    @FXML
    private ComboBox cBoxWyborGrupy;
    @FXML
    private TableView tableViewGrupa;
    @FXML
    private TableColumn<Student, Integer> tColumnNrAlbumu;
    @FXML
    private TableColumn<Student, String> tColumnNazwisko;
    @FXML
    private TableColumn<Student, String> tColumnImie;
    @FXML
    private TableColumn<Student, String> tColumnPesel;
    @FXML
    private TableColumn<Student, String> tColumnLP;

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
        tColumnNrAlbumu.setCellValueFactory(cellData -> cellData.getValue().nr_albumuProperty().asObject());
        tColumnImie.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        tColumnNazwisko.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        tColumnPesel.setCellValueFactory(cellData -> cellData.getValue().peselProperty());
        
        //liczba porzadkowa
        tColumnLP.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> p) {
                return new ReadOnlyObjectWrapper(tableViewGrupa.getItems().indexOf(p.getValue())+1 + "");
            }
        });
        tColumnLP.setSortable(false);

    }

    @FXML
    private void znajdzGrupeAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        int index = cBoxWyborGrupy.getSelectionModel().getSelectedIndex();
        Grupa wybranaGrupa = dziennikelektroniczny.modelDAO.GrupaDAO.wyborGrupy().get(index);
        ObservableList<Student> stuData = dziennikelektroniczny.modelDAO.StudentDAO.WyszukajStudentaGrupa(Integer.parseInt(wybranaGrupa.getId_grupyString()));
        wyswietlGrupe(stuData);
    }

    @FXML
    private void wyswietlGrupe(ObservableList<Student> stuData) {
        tableViewGrupa.setItems(stuData);
    }
}
