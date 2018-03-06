/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.modelDAO;

import dziennikelektroniczny.model.Grupa;
import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.PrzedmiotyGrupy;
import dziennikelektroniczny.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrzedmiotDAO {

    public static void dodajPrzemiot(String nazwa, String typ_przedmiotu) throws Exception {
        String updateStmt = "INSERT INTO przedmioty VALUES (null,'" + nazwa + "','" + typ_przedmiotu + "')";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.err.println("Błąd sql przy dodawaniu przedmiotu");
            throw e;
        }
    }

    public static void usunPrzedmiot(String id) throws SQLException, Exception {
        String deleteStmt = "DELETE FROM przedmioty WHERE id_przedmiotu='" + id + "'";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(deleteStmt);
        } catch (SQLException e) {
            System.err.println("Błąd SQL przy usuwaniu przedmiotu");
            throw e;
        }
    }

    public static Przedmiot wyszukajPrzedmiotId(String id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM przedmioty WHERE id_przedmiotu='" + id + "'";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            Przedmiot przedmiot = getPrzedmiot(rsPrze);
            return przedmiot;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu przedmiotu");
            throw e;
        }
    }

    public static Przedmiot wyszukajPrzedmiotZgodnyTypSpecjalizaji(int idWykladowcy, int idPrzedmiotu) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT p.* FROM przedmioty p, wykladowcy w WHERE w.typ_specjalizacji = p.typ_przedmiotu AND p.id_przedmiotu='" + idPrzedmiotu + "'  AND w.id_wykladowcy='" + idWykladowcy + "'";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            Przedmiot przedmiot = getPrzedmiot(rsPrze);
            return przedmiot;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu czy dany wykladowca ma zgodny typ z przedmiotem");
            throw e;
        }
    }

    public static Przedmiot wyszukajPrzedmiotNazwaPrzedmiot(String id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM przedmioty WHERE nazwa='" + id + "'";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            Przedmiot przedmiot = getPrzedmiot(rsPrze);
            return przedmiot;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu przedmiotu");
            throw e;
        }
    }

    private static Przedmiot getPrzedmiot(ResultSet rsPrze) throws SQLException {
        Przedmiot p = null;
        if (rsPrze.next()) {
            p = new Przedmiot();
            p.setId_przedmiotu(rsPrze.getInt("id_przedmiotu"));
            p.setNazwa(rsPrze.getString("nazwa"));
            p.setTyp_przedmiotu(rsPrze.getString("typ_przedmiotu"));
        }
        return p;
    }

    public static ObservableList<Przedmiot> wyszukajPrzedmioty() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM przedmioty";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Przedmiot> przedmiotList = getPrzedmiotList(rsPrze);
            return przedmiotList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu wszystkich przedmiotów");
            throw e;
        }
    }


    public static ObservableList<Przedmiot> wyszukajPrzedmiotIdGrupy(String id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM przedmioty WHERE id_przedmiotu='" + id + "'";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Przedmiot> przedmiotList = getPrzedmiotList(rsPrze);
            return przedmiotList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu przedmiotow grupy");
            throw e;
        }
    }

    private static ObservableList<Przedmiot> getPrzedmiotList(ResultSet rs) throws SQLException {
        ObservableList<Przedmiot> przedmiotList = FXCollections.observableArrayList();

        while (rs.next()) {
            Przedmiot p = new Przedmiot();
            p.setId_przedmiotu(rs.getInt("id_przedmiotu"));
            p.setNazwa(rs.getString("nazwa"));
            p.setTyp_przedmiotu(rs.getString("typ_przedmiotu"));
            przedmiotList.add(p);
        }
        return przedmiotList;
    }

    public static ObservableList<Przedmiot> wyszukajPrzedmiotNazwa(String nazwa) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM przedmioty WHERE nazwa='" + nazwa + "'";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Przedmiot> przedmiotList = getPrzedmiotList(rsPrze);
            return przedmiotList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu wszystkich przedmiotów");
            throw e;
        }
    }

    public static ObservableList<Przedmiot> wyszukajPrzedmiotTypPrzedmiotu(String typ) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM przedmioty WHERE typ_przedmiotu='" + typ + "'";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Przedmiot> przedmiotList = getPrzedmiotList(rsPrze);
            return przedmiotList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu wszystkich przedmiotów");
            throw e;
        }
    }

    public static ObservableList<Przedmiot> wyborPrzedmiotu() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT id_przedmiotu FROM przedmioty";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Przedmiot> przedmiotList = getPrzedmiotListID(rsPrze);
            return przedmiotList;
        } catch (SQLException e) {
            System.err.println("Błąd przy uzupełnianiu wyboru przedmiotu");
            throw e;
        }
    }

    private static ObservableList<Przedmiot> getPrzedmiotListID(ResultSet rs) throws SQLException {
        ObservableList<Przedmiot> przedmiotListID = FXCollections.observableArrayList();

        while (rs.next()) {
            Przedmiot p = new Przedmiot();
            p.setId_przedmiotu(rs.getInt("Id_przedmiotu"));
            przedmiotListID.add(p);
        }
        return przedmiotListID;
    }

}
