/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.modelDAO;

import dziennikelektroniczny.model.Grupa;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jakub
 */
public class GrupaDAO {

    public static ObservableList<Grupa> wyborGrupy() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT id_grupy FROM grupy";
        try {
            ResultSet rsGrupa = dziennikelektroniczny.util.DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Grupa> grupList = getgrupaList(rsGrupa);
            return grupList;
        } catch (SQLException e) {
            System.err.println("Błąd przy uzupełnianiu wyboru grupy");
            throw e;
        }
    }

    private static ObservableList<Grupa> getgrupaList(ResultSet rs) throws SQLException {
        ObservableList<Grupa> grupaList = FXCollections.observableArrayList();
        
        while(rs.next()){
            Grupa g = new Grupa();
            g.setId_grupy(rs.getInt("Id_grupy"));
            grupaList.add(g);
        }
        return grupaList;
    }

}
