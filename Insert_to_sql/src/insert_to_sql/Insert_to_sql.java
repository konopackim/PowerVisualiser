/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insert_to_sql;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.util.Random;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
/**
 *
 * @author Dell
 */
public class Insert_to_sql {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // TODO code application logic here
        
//        int m;
        LocalDateTime dt_temp;
        dbConnection connect = new dbConnection();
        String[] values;
//        values = new int[11];
        int val = 230;
        Random generator = new Random();
        BufferedReader br = null;
        String line ="";
	String cvsSplitBy = ",";
        try {
            connect.connectToDatabase();
            connect.selectFromDatabase();
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }
            String csvFile = "dupa";
            try {
                br = new BufferedReader(new FileReader(csvFile));
            
                while ((line = br.readLine()) != null) {
                    // use comma as separator
                    String[] data = line.split(cvsSplitBy);

                    values = Arrays.copyOfRange(data, 2, 13);

                    try {   
                      connect.insertDatabase(data[0],
                            data[1],
                            values
                      );
                    } catch (SQLException | ClassNotFoundException | IOException ex) {
                          Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
                    }
                }
                
            } catch (FileNotFoundException ex) {
                System.out.println("DUPA DUPA NIE MA PLIKU!!!!");
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
//            LocalDateTime dt = LocalDateTime.of(2015, 1, 1, 8, 0);
//            connect.insertDatabase(null, null, );
//            for(int m=0; dt.plusMinutes(m).isBefore(LocalDateTime.of(2015, 3, 31, 0, 0)); m=m+1){
//                dt_temp = dt.plusMinutes(m);
                
//                if (m%1000 == 0) {
//                    System.out.println(dt_temp);
//                }
        }
}
