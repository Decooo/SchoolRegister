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
import java.util.Iterator;
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
public class WyszukajPrzedmiotyGrupyFXMLController implements Initializable {

    @FXML
    private ComboBox cBoxWyborGrupy;
    @FXML
    private TableView tableViewPrzedmioty;
    @FXML
    private TableColumn<Przedmiot, String> tColumnId;
    @FXML
    private TableColumn<Przedmiot, String> tColumnNazwa;
    @FXML
    private TableColumn<Przedmiot, String> tColumnTyp;

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
        tColumnId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Przedmiot, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Przedmiot, String> p) {
                return new ReadOnlyObjectWrapper(tableViewPrzedmioty.getItems().indexOf(p.getValue()) + 1 + "");
            }
        });
        tColumnId.setSortable(false);

        tColumnNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        tColumnTyp.setCellValueFactory(cellData -> cellData.getValue().typ_przedmiotuProperty());
    }

    @FXML
    private void znajdzPrzedmiotyGrupyAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        int index = cBoxWyborGrupy.getSelectionModel().getSelectedIndex();
        Grupa wybranaGrupa = dziennikelektroniczny.modelDAO.GrupaDAO.wyborGrupy().get(index);
        ObservableList<PrzedmiotyGrupy> idPrzedmiotow = dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.wyszukajPrzedmiotyWGrupie(Integer.parseInt(wybranaGrupa.getId_grupyString()));
        String przedmioty = "";
        for (Iterator<PrzedmiotyGrupy> iterator = idPrzedmiotow.iterator(); iterator.hasNext();) {
            PrzedmiotyGrupy next = iterator.next();
            ObservableList<Przedmiot> przeData = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmiotIdGrupy(next.getId_przedmiotuString());
            wyswietlPrzedmioty(przeData);
            //przedmioty += next.getId_przedmiotuString() + ", ";
            przedmioty += next.getId_przedmiotuString() + "' OR id_przedmiotu='";

        }

        String zapytanie = przedmioty.substring(0, przedmioty.length() - 20);
        ObservableList<Przedmiot> przeData = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmiotIdGrupy(zapytanie);
        wyswietlPrzedmioty(przeData);

    }

    @FXML
    private void wyswietlPrzedmioty(ObservableList<Przedmiot> stuData) {
        tableViewPrzedmioty.setItems(stuData);

    }

}
