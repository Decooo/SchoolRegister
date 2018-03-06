/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.PrzedmiotyGrupy;
import dziennikelektroniczny.model.Wykladowca;
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
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class UsunPrzedmiotFXMLController implements Initializable {

    @FXML
    private ComboBox cBoxWyborPrzedmiotu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void usunPrzedmiotAction(ActionEvent event) throws Exception {
        int index = cBoxWyborPrzedmiotu.getSelectionModel().getSelectedIndex();
        Przedmiot wybranyPrzedmiot = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyborPrzedmiotu().get(index);
        //sprawdzanie czy z przedmiotu odbywają się zajęcia 
        PrzedmiotyGrupy pg = dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.wyszukajPrzedmiotGrupyPrzedmiot(Integer.parseInt(wybranyPrzedmiot.getId_przedmiotuString()));
        if (pg == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usuwanie przedmiotu");
            alert.setHeaderText("Czy napewno chcesz usunąć wybrany przedmiot?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                dziennikelektroniczny.modelDAO.PrzedmiotDAO.usunPrzedmiot(wybranyPrzedmiot.getId_przedmiotuString());
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Usuwanie przedmiotu");
                alert1.setHeaderText("Poprawnie usunięto wybrany przedmiot");
                alert1.getButtonTypes().setAll(ButtonType.OK);
                alert1.showAndWait();
                //odświeżanie ComboBoxa
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
            } else {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Usuwanie przedmiotu");
                alert1.setHeaderText("Anulowano usuwanie przedmiotu");
                alert1.getButtonTypes().setAll(ButtonType.OK);
                alert1.showAndWait();
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Informacja");
            alert1.setContentText("Aby usunąć przedmiot, usuń odbywające się zajęcia.");
            alert1.setHeaderText("Nie można usunąć przedmiotu, ponieważ jednej lub kilku grupach odbywają się zajęcia z tego przedmiotu.");
            alert1.showAndWait();
        }

    }

}
