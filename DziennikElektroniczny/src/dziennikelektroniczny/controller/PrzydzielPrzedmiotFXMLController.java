/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.controller;

import dziennikelektroniczny.model.Grupa;
import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.PrzedmiotyGrupy;
import dziennikelektroniczny.model.Wykladowca;
import java.net.URL;
import java.sql.SQLException;
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
public class PrzydzielPrzedmiotFXMLController implements Initializable {

    @FXML
    private ComboBox cBoxWyborGrupy;

    @FXML
    private ComboBox cBoxWyborPrzedmiotu;

    @FXML
    private ComboBox cBoxWyborWykladowcy;

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

        try {
            cBoxWyborWykladowcy.setConverter(new StringConverter<Wykladowca>() {
                @Override
                public Wykladowca fromString(String string) {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public String toString(Wykladowca object) {
                    return object.getNazwisko() + ", " + object.getImie();
                }
            });
            cBoxWyborWykladowcy.setItems(dziennikelektroniczny.modelDAO.WykladowcaDAO.wyszukajWykladowcow());
            cBoxWyborWykladowcy.getSelectionModel().select(0);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DodajOceneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void przydzielPrzedmiotAction(ActionEvent event) throws ClassNotFoundException, Exception {
        try {
            int index = cBoxWyborGrupy.getSelectionModel().getSelectedIndex();
            Grupa wybranaGrupa = dziennikelektroniczny.modelDAO.GrupaDAO.wyborGrupy().get(index);
            index = cBoxWyborPrzedmiotu.getSelectionModel().getSelectedIndex();
            Przedmiot wybranyPrzedmiot = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyborPrzedmiotu().get(index);
            index = cBoxWyborWykladowcy.getSelectionModel().getSelectedIndex();
            Wykladowca wybranyWykladowca = dziennikelektroniczny.modelDAO.WykladowcaDAO.wyborWykladowcy().get(index);

            PrzedmiotyGrupy pg = dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.wyszukajPrzedmiotStudenta(Integer.parseInt(wybranyPrzedmiot.getId_przedmiotuString()), Integer.parseInt(wybranaGrupa.getId_grupyString()));
            if (pg == null) {
                Przedmiot prze = dziennikelektroniczny.modelDAO.PrzedmiotDAO.wyszukajPrzedmiotZgodnyTypSpecjalizaji(Integer.parseInt(wybranyWykladowca.getId_wykladowcyString()), Integer.parseInt(wybranyPrzedmiot.getId_przedmiotuString()));
                if (prze != null) {
                    dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.dodajPrzedmiotDoGrupy(Integer.parseInt(wybranaGrupa.getId_grupyString()), Integer.parseInt(wybranyPrzedmiot.getId_przedmiotuString()), Integer.parseInt(wybranyWykladowca.getId_wykladowcyString()));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Przydzielanie przedmiotu do grupy");
                    alert.setHeaderText("Przydzielono przedmiot do grupy");
                    alert.getButtonTypes().setAll(ButtonType.OK);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Przydzielanie przedmiotu do grupy");
                    alert.setHeaderText("Ten wykładowca nie może prowadzić tego przedmiotu , ponieważ ma inną specjalizacje");
                    alert.getButtonTypes().setAll(ButtonType.OK);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Przydzielanie przedmiotu do grupy");
                alert.setHeaderText("Ta grupa ma już przydzielony ten przedmiot");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu studenta (controller)");
            e.printStackTrace();
        }
    }
}
