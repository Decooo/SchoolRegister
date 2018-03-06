/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.modelDAO;

import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.PrzedmiotyGrupy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jakub
 */
public class PrzedmiotyGrupyDAO {

    public static ObservableList<PrzedmiotyGrupy> wyszukajPrzedmiotyGrupyPrzedmiot() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM przedmioty_grupy";
        try {
            ResultSet rsPG = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<PrzedmiotyGrupy> pgList = getPGList(rsPG);
            return pgList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu aktywnych przedmiotów");
            throw e;
        }
    }

    private static ObservableList<PrzedmiotyGrupy> getPGList(ResultSet rs) throws SQLException {
        ObservableList<PrzedmiotyGrupy> pgList = FXCollections.observableArrayList();

        while (rs.next()) {
            PrzedmiotyGrupy pg = new PrzedmiotyGrupy();
            pg.setId_przedmioty_grupy(rs.getInt("id_przedmioty_grupy"));
            pg.setId_grupy(rs.getInt("id_grupy"));
            pg.setId_przedmiotu(rs.getInt("id_przedmiotu"));
            pg.setId_wykladowcy(rs.getInt("id_wykladowcy"));
            pgList.add(pg);
        }
        return pgList;
    }

    public static PrzedmiotyGrupy wyszukajPrzedmiotGrupyPrzedmiot(int id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM przedmioty_grupy WHERE id_przedmiotu='" + id + "'";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            PrzedmiotyGrupy przedmiotGrupy = getPrzedmiotGrupy(rsPrze);
            return przedmiotGrupy;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu przedmiotu grupy");
            throw e;
        }
    }

    private static PrzedmiotyGrupy getPrzedmiotGrupy(ResultSet rsPrze) throws SQLException {
        PrzedmiotyGrupy pg = null;
        if (rsPrze.next()) {
            pg = new PrzedmiotyGrupy();
            pg.setId_przedmioty_grupy(rsPrze.getInt("id_przedmioty_grupy"));
            pg.setId_grupy(rsPrze.getInt("id_grupy"));
            pg.setId_przedmiotu(rsPrze.getInt("id_przedmiotu"));
            pg.setId_wykladowcy(rsPrze.getInt("id_wykladowcy"));
        }
        return pg;
    }

    public static PrzedmiotyGrupy wyszukajWykladowceGrupyPrzedmiot(int idWykladowcy) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM przedmioty_grupy WHERE id_wykladowcy='" + idWykladowcy + "'";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            PrzedmiotyGrupy przedmiotGrupy = getPrzedmiotGrupy(rsPrze);
            return przedmiotGrupy;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu wykladowcy grupy");
            throw e;
        }
    }

    public static PrzedmiotyGrupy wyszukajPrzedmiotStudenta(int idPrzedmiotu, int idgrupy) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM przedmioty_grupy WHERE id_przedmiotu='" + idPrzedmiotu + "' AND id_grupy='" + idgrupy + "'";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            PrzedmiotyGrupy przedmiotGrupy = getPrzedmiotGrupy(rsPrze);
            return przedmiotGrupy;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu czy student uczy sie przedmiotu");
            throw e;
        }
    }

    public static ObservableList<PrzedmiotyGrupy> wyszukajPrzedmiotyWGrupie(int idGrupy) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM przedmioty_grupy WHERE id_grupy='" + idGrupy + "'";
        try {
            ResultSet rsPG = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<PrzedmiotyGrupy> pgList = getPGIdPrzedmiotuList(rsPG);
            return pgList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu grupy");
            throw e;
        }
    }

    private static ObservableList<PrzedmiotyGrupy> getPGIdPrzedmiotuList(ResultSet rs) throws SQLException {
        ObservableList<PrzedmiotyGrupy> pgList = FXCollections.observableArrayList();

        while (rs.next()) {
            PrzedmiotyGrupy pg = new PrzedmiotyGrupy();
            pg.setId_przedmiotu(rs.getInt("id_przedmiotu"));
            pgList.add(pg);
        }
        return pgList;
    }

    public static void dodajPrzedmiotDoGrupy(int grupa, int przedmiot, int wykladowca) throws Exception {
        String updateStmt = "INSERT INTO przedmioty_grupy VALUES (null, '" + grupa + "','" + przedmiot + "','" + wykladowca + "')";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.err.println("Błąd operacji przy dodawaniu przedmiotu do grupy");
            throw e;
        }
    }

    public static void usunPrzedmiotGrupy(int grupa, int przedmiot) throws SQLException, Exception {
        String deleteStmt = "DELETE FROM przedmioty_grupy WHERE id_grupy='" + grupa + "' AND id_przedmiotu='" + przedmiot + "' ";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(deleteStmt);
        } catch (SQLException e) {
            System.err.println("Błąd SQL przy usuwaniu przedmiotu z grupy");
            throw e;
        }
    }
    
        public static ArrayList<PrzedmiotyGrupy> przedmiotyDanejGrupy(int nrAlbumu) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT pg.id_przedmiotu\n"
                + "FROM studenci s, przedmioty_grupy pg\n"
                + "WHERE pg.id_grupy=s.id_grupy\n"
                + "AND s.nr_albumu='" + nrAlbumu + "'";
        try {
            ResultSet rsPrze = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ArrayList<PrzedmiotyGrupy> przedmiotList = getList(rsPrze);
            return przedmiotList;
        } catch (SQLException e) {
            System.err.println("Błąd przy tworzeniu tablicy z id przedmiotów w grupie");
            throw e;
        }
    }

    private static ArrayList<PrzedmiotyGrupy> getList(ResultSet rs) throws SQLException {
        ArrayList<PrzedmiotyGrupy> listaID = new ArrayList<>();

        while (rs.next()) {
            PrzedmiotyGrupy pg = new PrzedmiotyGrupy();
            pg.setId_przedmiotu(rs.getInt("id_przedmiotu"));
            listaID.add(pg);
        }
        return listaID;
    }
    

}
