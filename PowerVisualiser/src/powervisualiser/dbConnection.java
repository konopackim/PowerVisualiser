/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powervisualiser;

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
//    CREATE TABLE `data_history`.`data_history` (
//  `date` DATETIME NOT NULL DEFAULT 0,
//  `device_id` VARCHAR(45) NOT NULL DEFAULT 0,
//  `sys_voltage` INT NULL,
//  `ln_voltage_phase_1` INT NULL,
//  `ln_voltage_phase_2` INT NULL,
//  `ln_voltage_phase_3` INT NULL,
//  `ll_voltage_12` INT NULL,
//  `ll_voltage_23` INT NULL,
//  `ll_voltage_31` INT NULL,
//  `sys_current` INT NULL,
//  `phase_1_current` INT NULL,
//  `phase_2_current` INT NULL,
//  `phase_3_current` INT NULL,
//  PRIMARY KEY (`date`, `device_id`))
//  ENGINE = InnoDB;

    public ArrayList<String> date = new ArrayList<>();
    public ArrayList<Integer> sys_voltage = new ArrayList<>();
    public ArrayList<Integer> ln_voltage_phase_1 = new ArrayList<>();
    public ArrayList<Integer> ln_voltage_phase_2 = new ArrayList<>();
    public ArrayList<Integer> ln_voltage_phase_3 = new ArrayList<>();
    public ArrayList<Integer> ll_voltage_12 = new ArrayList<>();
    public ArrayList<Integer> ll_voltage_23 = new ArrayList<>();
    public ArrayList<Integer> ll_voltage_31 = new ArrayList<>();
    public ArrayList<Integer> sys_current = new ArrayList<>();
    public ArrayList<Integer> phase_1_current = new ArrayList<>();
    public ArrayList<Integer> phase_2_current = new ArrayList<>();
    public ArrayList<Integer> phase_3_current = new ArrayList<>();

    private Connection con;
    Statement stmt;

    public void connectToDatabase(String databaseAddress, String portNumber, String login, String password) throws SQLException, ClassNotFoundException, IOException {

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://" + databaseAddress + ":" + portNumber + "/data_history", login, password);
        
        // localhost:3306, usr1, pass

        stmt = con.createStatement();
//        ResultSet lm = stmt.executeQuery("SELECT * FROM data_history");

    }

    public void selectFromDatabase(String date1, String date2, String device_id) throws SQLException, ClassNotFoundException, IOException {

        sys_voltage.clear();
        ln_voltage_phase_1.clear();
        ln_voltage_phase_2.clear();
        ln_voltage_phase_3.clear();
        ll_voltage_12.clear();
        ll_voltage_23.clear();
        ll_voltage_31.clear();
        sys_current.clear();
        phase_1_current.clear();
        phase_2_current.clear();
        phase_3_current.clear();

        stmt = con.createStatement();
        ResultSet lm = stmt.executeQuery("SELECT * FROM data_history WHERE date BETWEEN'" + date1 + "' AND '" + date2 + "' AND device_id='" + device_id + "'");
        while (lm.next()) {
//            System.out.printf("%s %s\n", lm.getString("date"), lm.getString("device_id"));

            date.add(lm.getString("date"));
            sys_voltage.add(lm.getInt("sys_voltage"));
            ln_voltage_phase_1.add(lm.getInt("ln_voltage_phase_1"));
            ln_voltage_phase_2.add(lm.getInt("ln_voltage_phase_2"));
            ln_voltage_phase_3.add(lm.getInt("ln_voltage_phase_3"));
            ll_voltage_12.add(lm.getInt("ll_voltage_12"));
            ll_voltage_23.add(lm.getInt("ll_voltage_23"));
            ll_voltage_31.add(lm.getInt("ll_voltage_31"));
            sys_current.add(lm.getInt("sys_current"));
            phase_1_current.add(lm.getInt("phase_1_current"));
            phase_2_current.add(lm.getInt("phase_2_current"));
            phase_3_current.add(lm.getInt("phase_3_current"));
        }
    }

    public void closeConnection() throws SQLException, ClassNotFoundException, IOException {

        con.close();
    }
}

