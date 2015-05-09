/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author krzysztofzabinski
 */
public class dbConnection {

    public ArrayList<String> pierwsza = new ArrayList<String>();
    public ArrayList<String> druga = new ArrayList<String>();

    public void connectToAndQueryDatabase() throws SQLException, ClassNotFoundException, IOException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/leszkiimichaly",
                "leszkiimichaly", "leszkiimichaly");

//        for (int i = 0; i < load.result.length; i++) {
//            Statement stmt = con.createStatement();
//            String update = "insert into odczytyTestowe values('2015-07-01 09:12:00', 'SERIAL2', 231, 233, 222, 223, 1, 12, 13, 14, 50)";
//            int lm = stmt.executeUpdate(update);
//        }
        Statement stmt = con.createStatement();
        ResultSet lm = stmt.executeQuery("SELECT date FROM odczytyTestowe");
        while (lm.next()) {
            System.out.println(lm.getString("date"));
        }
//        while (lm.next()) {
//
//            pierwsza.add(lm.getString("pierwsza"));
//            druga.add(lm.getString("druga"));
//(date, serial, systemVoltage, L-NVoltagePhase1, L-NVoltagePhase2, L-NVoltagePhase3, systemCurrent, phase1current, phase2current, phase3current, frequency)
//        }
    }
}
