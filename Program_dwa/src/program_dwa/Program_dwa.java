/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_dwa;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Krzysztof
 */
public class Program_dwa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("Cześć Michał :D\ntestujemy połączenie z przykładową bazą mySQL\n");
        dbConnection connect = new dbConnection();
        connect.connectToAndQueryDatabase();
        for(int i=0; i < connect.pierwsza.size(); i++){
            System.out.println(connect.pierwsza.get(i));
        }
        for(int i=0; i < connect.druga.size(); i++){
            System.out.println(connect.druga.get(i));
        }
    }
    
}
