/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Grupa;
import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.PrzedmiotyGrupy;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class UsuwaniePrzedmiotuGrupyFXMLController implements Initializable {

    @FXML
    private ComboBox cBoxWyborGrupy;

    @FXML
    private ComboBox cBoxWyborPrzedmiotu;

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
            cBoxWyborPrzedmiotu.setItems(dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmioty());
            cBoxWyborPrzedmiotu.getSelectionModel().select(0);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DodajOceneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void usunPrzedmiotAction(ActionEvent event) throws ClassNotFoundException, SQLException, Exception {
        int index = cBoxWyborGrupy.getSelectionModel().getSelectedIndex();
        Grupa wybranaGrupa = dziennikelektroniczny.modelDAO.GrupaDAO.wyborGrupy().get(index);
        index = cBoxWyborPrzedmiotu.getSelectionModel().getSelectedIndex();
        Przedmiot wybranyPrzedmiot = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyborPrzedmiotu().get(index);

        PrzedmiotyGrupy pg = dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.wyszukajPrzedmiotStudenta(wybranyPrzedmiot.getId_przedmiotu(), wybranaGrupa.getId_grupy());
        if (pg != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usuwanie przedmiotu z grupy");
            alert.setHeaderText("Czy napewno chcesz usunąć wybrany przedmiot z grupy: " + wybranaGrupa.getId_grupyString() + "?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.usunPrzedmiotGrupy(wybranaGrupa.getId_grupy(), wybranyPrzedmiot.getId_przedmiotu());
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Usuwanie przedmiotu z grupy");
                alert1.setHeaderText("Usunięto przedmiot z grupy");
                alert1.getButtonTypes().setAll(ButtonType.OK);
                alert1.showAndWait();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Usuwanie przedmiotu z grupy");
                alert1.setHeaderText("Anulowano usuwanie przedmiotu");
                alert1.getButtonTypes().setAll(ButtonType.OK);
                alert1.showAndWait();
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Usuwanie przedmiotu z grupy");
            alert1.setHeaderText("Wybrana grupa nie ma przydzielonego tego przedmiotu");
            alert1.getButtonTypes().setAll(ButtonType.OK);
            alert1.showAndWait();
        }

    }

}
