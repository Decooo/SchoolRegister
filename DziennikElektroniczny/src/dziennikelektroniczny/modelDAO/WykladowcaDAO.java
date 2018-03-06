package dziennikelektroniczny.modelDAO;

import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.Student;
import dziennikelektroniczny.model.Wykladowca;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jakub
 */
public class WykladowcaDAO {

    public static void dodajWykladowce(String imie, String nazwisko, String email, String typ_specjalizacji) throws Exception {
        String updateStmt = "INSERT INTO wykladowcy VALUES (null, '" + imie + "','" + nazwisko + "','" + email + "','" + typ_specjalizacji + "')";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.err.println("Błąd SQL przyy dodawaniu wykladowcy");
            throw e;
        }
    }

    public static void usunWykladowceID(String idWykladowcy) throws SQLException, Exception {
        String deleteStmt = "DELETE FROM wykladowcy WHERE id_wykladowcy='" + idWykladowcy + "'";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(deleteStmt);
        } catch (SQLException e) {
            System.err.println("Błąd sql przy usuwaniu wykladowcy");
            throw e;
        }
    }

    public static Wykladowca wyszukajWykladowceID(String idWykladowcy) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM wykladowcy WHERE id_wykladowcy = '" + idWykladowcy + "'";

        try {
            ResultSet rsWyk = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            Wykladowca wykladowca = getWykladowcaId(rsWyk);
            return wykladowca;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu wykladowcy po id");
            throw e;
        }
    }

    private static Wykladowca getWykladowcaId(ResultSet rsWyk) throws SQLException {
        Wykladowca w = null;
        if (rsWyk.next()) {
            w = new Wykladowca();
            w.setId_wykladowcy(rsWyk.getInt("id_wykladowcy"));
            w.setImie(rsWyk.getString("imie"));
            w.setNazwisko(rsWyk.getString("nazwisko"));
            w.setEmail(rsWyk.getString("email"));
            w.setTyp_specjalizacji(rsWyk.getString("typ_specjalizacji"));
        }
        return w;
    }

    public static ObservableList<Wykladowca> WyszukajWszystkichWykladowcow() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM wykladowcy";
        try {
            ResultSet rsWykladowca = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Wykladowca> wykladowcaList = getWykladowcaList(rsWykladowca);
            return wykladowcaList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy pokazwaniu wszystkich wykladowcow");
            throw e;
        }
    }

    private static ObservableList<Wykladowca> getWykladowcaList(ResultSet rs) throws SQLException {
        ObservableList<Wykladowca> wykladowcaList = FXCollections.observableArrayList();

        while (rs.next()) {
            Wykladowca w = new Wykladowca();
            w.setId_wykladowcy(rs.getInt("id_wykladowcy"));
            w.setImie(rs.getString("imie"));
            w.setNazwisko(rs.getString("nazwisko"));
            w.setEmail(rs.getString("email"));
            w.setTyp_specjalizacji(rs.getString("typ_specjalizacji"));
            wykladowcaList.add(w);
        }
        return wykladowcaList;
    }

    public static ObservableList<Wykladowca> WyszukajWykladowceImieNazwisko(String imie, String nazwisko) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM wykladowcy WHERE imie ='" + imie + "' AND nazwisko='" + nazwisko + "'";
        try {
            ResultSet rsWykladowca = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Wykladowca> wykladowcaList = getWykladowcaList(rsWykladowca);
            return wykladowcaList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu wykladowców po imieniu i nazwisku");
            throw e;
        }
    }

    public static ObservableList<Wykladowca> WyszukajWykladowceNazwisko(String nazwisko) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM wykladowcy WHERE nazwisko ='" + nazwisko + "'";
        try {
            ResultSet rsWykladowca = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Wykladowca> wykladowcaList = getWykladowcaList(rsWykladowca);
            return wykladowcaList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu wykladowcy po nazwisku");
            throw e;
        }
    }

    public static ObservableList<Wykladowca> WyszukajWykladowceImie(String imie) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM wykladowcy WHERE imie ='" + imie + "'";
        try {
            ResultSet rsWykladowca = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Wykladowca> wykladowcaList = getWykladowcaList(rsWykladowca);
            return wykladowcaList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu wykladowców po imieniu");
            throw e;
        }
    }

    public static ObservableList<Wykladowca> wyszukajWykladowcow() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM wykladowcy";
        try {
            ResultSet rsWykladowca = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Wykladowca> wykladowcaList = getWykladowcaList(rsWykladowca);
            return wykladowcaList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu wszystkich wykladowcow");
            throw e;
        }
    }

    public static ObservableList<Wykladowca> wyborWykladowcy() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT id_wykladowcy FROM wykladowcy";
        try {
            ResultSet rsWykladowca = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Wykladowca> wykladowcaList = getWykladowcaListID(rsWykladowca);
            return wykladowcaList;
        } catch (SQLException e) {
            System.err.println("Błąd przy uzupełnianiu wyboru przedmiotu");
            throw e;
        }
    }

    private static ObservableList<Wykladowca> getWykladowcaListID(ResultSet rs) throws SQLException {
        ObservableList<Wykladowca> wykladowcaListID = FXCollections.observableArrayList();

        while (rs.next()) {
            Wykladowca w = new Wykladowca();
            w.setId_wykladowcy(rs.getInt("id_wykladowcy"));
            wykladowcaListID.add(w);
        }
        return wykladowcaListID;
    }

}
