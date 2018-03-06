/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.modelDAO;

import dziennikelektroniczny.model.Ocena;
import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jakub
 */
public class OcenaDAO {

    public static void dodajOcene(int nr_albumu, int id_przedmiotu, String ocena, String opis_oceny) throws Exception {

        String updateStmt = "INSERT INTO oceny VALUES (null, '" + nr_albumu + "','" + id_przedmiotu + "','" + ocena + "','" + opis_oceny + "')";

        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.err.println("Błąd operacji przy dodawaniu oceny");
            throw e;
        }
    }

    public static void usunOceneId(String id) throws SQLException, Exception {
        String deleteStmt = "DELETE FROM oceny WHERE id_oceny='" + id + "'";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(deleteStmt);
        } catch (SQLException e) {
            System.err.println("Błąd SQL przy usuwaniu oceny");
            throw e;
        }
    }

    public static void usunOceneNrAlbumu(String nrAlbumu) throws SQLException, Exception {
        String deleteStmt = "DELETE FROM oceny WHERE nr_albumu='" + nrAlbumu + "'";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(deleteStmt);
        } catch (SQLException e) {
            System.err.println("Błąd SQL przy usuwaniu ocen studenta");
            throw e;
        }
    }

    public static Ocena wyszukajOceneId(String id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM oceny WHERE id_oceny='" + id + "'";
        try {
            ResultSet rsOcena = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            Ocena ocena = getOcenaId(rsOcena);
            return ocena;
        } catch (SQLException e) {
            System.err.println("Błąd przy wyszukiwaniu oceny po id");
            throw e;
        }
    }

    public static ObservableList<Ocena> wyszukajOceneNrAlbumu(String id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM oceny WHERE nr_albumu='" + id + "'";
        try {
            ResultSet rsOcena = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Ocena> ocenaList = getOcenaList(rsOcena);
            return ocenaList;
        } catch (SQLException e) {
            System.err.println("Błąd przy wyszukiwaniu oceny po nr_albumu");
            throw e;
        }
    }

    public static ObservableList<Ocena> wyszukajOceneNrAlbumuPrzedmiot(String id, int nazwa) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM oceny WHERE nr_albumu='" + id + "' AND id_przedmiotu='" + nazwa + "'";
        try {
            ResultSet rsOcena = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Ocena> ocenaList = getOcenaList(rsOcena);
            return ocenaList;
        } catch (SQLException e) {
            System.err.println("Błąd przy wyszukiwaniu oceny po nr albumu i nazwie przedmiotu");
            throw e;
        }
    }

    public static ObservableList<Ocena> wyborOceny(String id, int nazwa) throws ClassNotFoundException, SQLException {
         String selectStmt = "SELECT * FROM oceny WHERE nr_albumu='" + id + "' AND id_przedmiotu='" + nazwa + "'";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Ocena> ocenaList = getOcenaListID(rsPrze);
            return ocenaList;
        } catch (SQLException e) {
            System.err.println("Błąd przy uzupełnianiu wyboru oceny");
            throw e;
        }
    }

    private static ObservableList<Ocena> getOcenaListID(ResultSet rs) throws SQLException {
        ObservableList<Ocena> ocenaListID = FXCollections.observableArrayList();

        while (rs.next()) {
            Ocena o = new Ocena();
            o.setId_oceny(rs.getInt("Id_oceny"));
            ocenaListID.add(o);
        }
        return ocenaListID;
    }

    private static Ocena getOcenaId(ResultSet rsOcena) throws SQLException {
        Ocena o = null;
        if (rsOcena.next()) {
            o = new Ocena();
            o.setId_oceny(rsOcena.getInt("id_oceny"));
            o.setNr_albumu(rsOcena.getInt("nr_albumu"));
            o.setId_przedmiotu(rsOcena.getInt("id_przedmiotu"));
            o.setOcena(rsOcena.getString("ocena"));
            o.setOpis_oceny(rsOcena.getString("opis_oceny"));
        }
        return o;
    }

    private static ObservableList<Ocena> getOcenaList(ResultSet rs) throws SQLException {
        ObservableList<Ocena> ocenaList = FXCollections.observableArrayList();

        while (rs.next()) {
            Ocena o = new Ocena();
            o.setId_oceny(rs.getInt("id_oceny"));
            o.setNr_albumu(rs.getInt("nr_albumu"));
            o.setId_przedmiotu(rs.getInt("id_przedmiotu"));
            o.setOcena(rs.getString("ocena"));
            o.setOpis_oceny(rs.getString("opis_oceny"));
            ocenaList.add(o);
        }
        return ocenaList;
    }

}
