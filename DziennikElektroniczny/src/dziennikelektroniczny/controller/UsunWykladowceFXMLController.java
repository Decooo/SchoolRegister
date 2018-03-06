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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class UsunWykladowceFXMLController implements Initializable {

    @FXML
    private ComboBox cBoxWyborWykladowcy;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void usunWykladowceAction(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        int index = cBoxWyborWykladowcy.getSelectionModel().getSelectedIndex();
        Wykladowca wybranyWykladowca = dziennikelektroniczny.modelDAO.WykladowcaDAO.wyborWykladowcy().get(index);

        //sprawdzanie czy wykladowca prowadzi zajęcia 
        PrzedmiotyGrupy pw = dziennikelektroniczny.modelDAO.PrzedmiotyGrupyDAO.wyszukajWykladowceGrupyPrzedmiot(Integer.parseInt(wybranyWykladowca.getId_wykladowcyString()));
        if (pw == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usuwanie wykladowcy");
            alert.setHeaderText("Czy napewno chcesz usunąć wybranego wykadowcę?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                dziennikelektroniczny.modelDAO.WykladowcaDAO.usunWykladowceID(wybranyWykladowca.getId_wykladowcyString());
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Usuwanie wykładowcy");
                alert1.setHeaderText("Poprawnie usunięto wykładowcę");
                alert1.getButtonTypes().setAll(ButtonType.OK);
                alert1.showAndWait();

                //odświeżanie ComboBoxa
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
            } else {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Usuwanie wykładowcy");
                alert1.setHeaderText("Anulowano usuwanie wykładowcy");
                alert1.getButtonTypes().setAll(ButtonType.OK);
                alert1.showAndWait();
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Informacja");
            alert1.setContentText("Aby usunąć wykladowce, nie może on prowadzić żadnych zajęć.");
            alert1.setHeaderText("Nie można usunąć wykąłdowcy, ponieważ obecnie prowadzi jedny lub więcej zajęć.");
            alert1.showAndWait();
        }
    }
}
