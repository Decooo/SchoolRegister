/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.util;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jakub
 */
public class DBConnection {

    private static final String Driver = "jdbc:sqlite:database\\studenci.db";

    private static Connection conn = null;

    private static Statement stmt = null;

    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("1.Zarejestrowano sterownik do bazy danych SQLITE");
            conn = DriverManager.getConnection(Driver);
            System.out.println("2.Nawiazano polaczenie z baza danych");
        } catch (SQLException e) {
            System.err.println("Bład połączenia z bazą danych");
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        ResultSet resultset = null;
        CachedRowSetImpl crs = null;

        try {
            dbConnect();
            System.out.println("Select Statement: " + queryStmt + "\n");

            stmt = conn.createStatement();
            resultset = stmt.executeQuery(queryStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultset);
        } catch (SQLException e) {
            System.err.println("Problem z executeQuery");
            throw e;
        } finally {
            if (resultset != null) {
                resultset.close();
                resultset = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            dbDisconnect();
        }
        return crs;
    }

    public static void dbExecuteUpdate(String sqlStmt) throws Exception {

        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.err.println("Problem z executeUpdate");
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }

}
