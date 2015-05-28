/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generate_test_data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author krzysztofzabinski
 */
public class dbConnection {

    public ArrayList<String> pierwsza = new ArrayList<String>();
    public ArrayList<String> druga = new ArrayList<String>();
    private Connection con;
    Statement stmt;
    
    public void connectToDatabase() throws SQLException, ClassNotFoundException, IOException {

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_history", "root", "");

//        for (int i = 0; i < load.result.length; i++) {
//            Statement stmt = con.createStatement();
//            String update = "insert into odczytyTestowe values('2015-07-01 09:12:00', 'SERIAL2', 231, 233, 222, 223, 1, 12, 13, 14, 50)";
//            int lm = stmt.executeUpdate(update);
//        }
//        while (lm.next()) {
//
//            pierwsza.add(lm.getString("pierwsza"));
//            druga.add(lm.getString("druga"));
//(date, serial, systemVoltage, L-NVoltagePhase1, L-NVoltagePhase2, L-NVoltagePhase3, systemCurrent, phase1current, phase2current, phase3current, frequency)
//        }
    }
    
    public void insertDatabase(String date, String serialId, int[] values) throws SQLException, ClassNotFoundException, IOException {
        
        String query = "insert into data_history values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString (1, date);
        stmt.setString (2,serialId);
        stmt.setInt(3, values[0]);
        stmt.setInt(4, values[1]);
        stmt.setInt(5, values[2]);
        stmt.setInt(6, values[3]);
        stmt.setInt(7, values[4]);
        stmt.setInt(8, values[5]);
        stmt.setInt(9, values[6]);
        stmt.setInt(10, values[7]);
        stmt.setInt(11, values[8]);
        stmt.setInt(12, values[9]);
        stmt.setInt(13, values[10]);
        stmt.execute();
    }
    
    public void selectFromDatabase() throws SQLException, ClassNotFoundException, IOException {
        
        stmt = con.createStatement();
        ResultSet lm = stmt.executeQuery("SELECT * FROM data_history");
        while (lm.next()) {
            System.out.printf("%s %s\n", lm.getString("date"), lm.getString("device_id"));
        }
    }
}
