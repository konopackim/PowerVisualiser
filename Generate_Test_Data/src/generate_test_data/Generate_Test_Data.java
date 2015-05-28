/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generate_test_data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author Dell
 */
public class Generate_Test_Data {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        int m;
//        LocalDateTime dt_temp;
        dbConnection connect = new dbConnection();
        int[] values;
        int val = 230;
        Random generator = new Random();
        try {
            connect.connectToDatabase();
            connect.selectFromDatabase();
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }
        LocalDateTime dt = LocalDateTime.of(2015, 1, 1, 8, 0);
//            connect.insertDatabase(null, null, );
        for (int m = 0; dt.plusMinutes(m).isBefore(LocalDateTime.of(2015, 3, 31, 0, 0)); m = m + 1) {
//            dt_temp = dt.plusMinutes(m);
//                System.out.println(dt_temp);
//                val = 230 + generator.nextInt()*10-5;
            values = new int[11];
            
            values[0] = 250 + generator.nextInt() * 10 - 5;
            values[1] = 100 + generator.nextInt() * 10 - 5;
            values[2] = 200 + generator.nextInt() * 10 - 5;
            values[3] = 300 + generator.nextInt() * 10 - 5;
            values[4] = 120 + generator.nextInt() * 10 - 5;
            values[5] = 230 + generator.nextInt() * 10 - 5;
            values[6] = 310 + generator.nextInt() * 10 - 5;
            values[7] = 5 + generator.nextInt() * 10 - 5;
            values[8] = 10 + generator.nextInt() * 10 - 5;
            values[9] = 20 + generator.nextInt() * 10 - 5;
            values[10] = 30 + generator.nextInt() * 10 - 5;


            
            try {
                connect.insertDatabase(dt.plusMinutes(m).toString(),
                        "SERIAL1",
                        values);

            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
            }
            if (m % 1000 == 0) {
                System.out.println(dt.plusMinutes(m));
            }
            values = null;
        }

//        dt+4;
    }

}
