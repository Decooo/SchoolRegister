/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.modelDAO;

import dziennikelektroniczny.model.Grupa;
import dziennikelektroniczny.model.Przedmiot;
import dziennikelektroniczny.model.Student;
import dziennikelektroniczny.model.Wykladowca;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDAO {

    public static void DodajStudenta(String imie, String nazwisko, String pesel, int id_grupy) throws Exception {

        String updateStmt = "INSERT INTO studenci VALUES (null, '" + imie + "','" + nazwisko + "','" + pesel + "','" + id_grupy + "')";

        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.err.println("Błąd operacji przy dodawaniu studenta");
            throw e;
        }
    }

    public static ObservableList<Student> WyszukajWszystkichStudentow() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM studenci";
        try {
            ResultSet rsStudent = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Student> studentList = getStudentList(rsStudent);
            return studentList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy pokazwaniu wszystkich studentow");
            throw e;
        }
    }

    private static ObservableList<Student> getStudentList(ResultSet rs) throws SQLException {
        ObservableList<Student> studentList = FXCollections.observableArrayList();

        while (rs.next()) {
            Student s = new Student();
            s.setNr_albumu(rs.getInt("nr_albumu"));
            s.setImie(rs.getString("imie"));
            s.setNazwisko(rs.getString("nazwisko"));
            s.setPesel(rs.getString("pesel"));
            s.setId_grupy(rs.getInt("id_grupy"));
            studentList.add(s);
        }
        return studentList;
    }

    public static Student wyszukajStudentaNrAlbumu(String nrAlbumu) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM studenci WHERE nr_albumu='" + nrAlbumu + "'";
        try {
            ResultSet rsStu = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            Student student = getStudentNrAlbumu(rsStu);
            return student;
        } catch (SQLException e) {
            System.err.println("Błąd przy wyszukiwaniu studentów po nr albumu");
            throw e;
        }
    }

    private static Student getStudentNrAlbumu(ResultSet rsStu) throws SQLException {
        Student s = null;
        if (rsStu.next()) {
            s = new Student();
            s.setNr_albumu(rsStu.getInt("nr_albumu"));
            s.setImie(rsStu.getString("imie"));
            s.setNazwisko(rsStu.getString("nazwisko"));
            s.setPesel(rsStu.getString("pesel"));
            s.setId_grupy(rsStu.getInt("id_grupy"));
        }
        return s;
    }

    public static Student wyszukajStudentaPesel(String pesel) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM studenci WHERE pesel='" + pesel + "'";

        try {
            ResultSet rsStu = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            Student student = getStudentNrAlbumu(rsStu);
            return student;
        } catch (SQLException e) {
            System.err.println("Błąd przy wyszukiwaniu studentów po peselu");
            throw e;
        }
    }

    public static Student wyszukajStudentaGrupa(String nrAlbumu, int idGrupy) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM studenci WHERE nr_albumu='" + nrAlbumu + "' AND id_grupy = '" + idGrupy + "'";

        try {
            ResultSet rsStu = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            Student student = getStudentNrAlbumu(rsStu);
            return student;
        } catch (SQLException e) {
            System.err.println("Błąd przy wyszukiwaniu grupy studenta");
            throw e;
        }
    }

    public static ObservableList<Student> WyszukajStudentaImie(String imie) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM studenci WHERE imie ='" + imie + "'";
        try {
            ResultSet rsStudent = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Student> studentList = getStudentList(rsStudent);
            return studentList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu studentow po imieniu");
            throw e;
        }
    }

    public static ObservableList<Student> WyszukajStudentaGrupa(int grupa) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM studenci WHERE id_grupy ='" + grupa + "'";
        try {
            ResultSet rsStudent = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Student> studentList = getStudentList(rsStudent);
            return studentList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu studentow w grupie");
            throw e;
        }
    }

    public static ObservableList<Student> WyszukajStudentaNazwisko(String nazwisko) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM studenci WHERE nazwisko ='" + nazwisko + "'";
        try {
            ResultSet rsStudent = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Student> studentList = getStudentList(rsStudent);
            return studentList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu studentow po nazwisku");
            throw e;
        }
    }

    public static ObservableList<Student> WyszukajStudentaImieNazwisko(String imie, String nazwisko) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM studenci WHERE imie ='" + imie + "' AND nazwisko='" + nazwisko + "'";
        try {
            ResultSet rsStudent = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Student> studentList = getStudentList(rsStudent);
            return studentList;
        } catch (SQLException e) {
            System.err.println("Błąd sql przy wyszukiwaniu studentow po imieniu i nazwisku");
            throw e;
        }
    }

    public static void usunStudentaNrAlbumu(String nrAlbumu) throws SQLException, Exception {
        String deleteStmt = "DELETE FROM studenci WHERE nr_albumu='" + nrAlbumu + "'";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(deleteStmt);
        } catch (SQLException e) {
            System.err.println("Błąd SQL przy usuwaniu studenta po nr albumu");
            throw e;
        }
    }

    public static void usunStudentaPesel(String pesel) throws SQLException, Exception {
        String deleteStmt = "DELETE FROM studenci WHERE pesel='" + pesel + "'";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(deleteStmt);
        } catch (SQLException e) {
            System.err.println("Błąd przy usuwaniu studenta po peselu");
            throw e;
        }
    }

    public static void zaaktualizujNazwiskoStudenta(String nrAlbumu, String nazwisko) throws SQLException, Exception {
        String updateStmt = "UPDATE studenci SET nazwisko = '" + nazwisko + "' WHERE nr_albumu= '" + nrAlbumu + "'";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.err.println("Błąd sql przy aktualizacji nazwiska studenta");
            throw e;
        }
    }

    public static void zaaktualizujGrupeStudenta(String nrAlbumu, int idGrupy) throws SQLException, Exception {
        String updateStmt = "UPDATE studenci SET id_grupy = '" + idGrupy + "' WHERE nr_albumu= '" + nrAlbumu + "'";
        try {
            dziennikelektroniczny.util.DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.err.println("Błąd sql przy aktualizacji grupy studenta");
            throw e;
        }
    }

    public static ObservableList<Student> wyborStudenta() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT nr_albumu FROM studenci";
        try {
            ResultSet rsStu = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Student> studentList = getStudentListID(rsStu);
            return studentList;
        } catch (SQLException e) {
            System.err.println("Błąd przy uzupełnianiu wyboru studentów");
            throw e;
        }
    }

    private static ObservableList<Student> getStudentListID(ResultSet rs) throws SQLException {
        ObservableList<Student> studentListID = FXCollections.observableArrayList();

        while (rs.next()) {
            Student s = new Student();
            s.setNr_albumu(rs.getInt("nr_albumu"));
            studentListID.add(s);
        }
        return studentListID;
    }

    public static ObservableList<Student> wyborStudentaID() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT * FROM studenci";
        try {
            ResultSet rsStu = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Student> studentList = getStudentListId(rsStu);
            return studentList;
        } catch (SQLException e) {
            System.err.println("Błąd przy uzupełnianiu wyboru studentów");
            throw e;
        }
    }

    private static ObservableList<Student> getStudentListId(ResultSet rs) throws SQLException {
        ObservableList<Student> studentListID = FXCollections.observableArrayList();

        while (rs.next()) {
            Student s = new Student();
            s.setNr_albumu(rs.getInt("nr_albumu"));
            s.setImie(rs.getString("imie"));
            s.setNazwisko(rs.getString("nazwisko"));
            s.setPesel(rs.getString("pesel"));
            s.setId_grupy(rs.getInt("id_grupy"));
            studentListID.add(s);
        }
        return studentListID;
    }

}
