/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

/**
 *
 * @author Krzysztof
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    public PieChart piechart;
    
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        dbConnection connect = new dbConnection();
        try {
            connect.connectToAndQueryDatabase();
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String pierwsza : connect.pierwsza) {
            System.out.println(pierwsza);
        }
        for (String druga : connect.druga) {
            System.out.println(druga);
        }
        ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
            new PieChart.Data("Michał B", 60),
            new PieChart.Data("Michał K", 25),
            new PieChart.Data("Leszek", 15),
            new PieChart.Data("Krzyś", 15)
            );

            piechart.setData(pieChartData);
    }    
    
}
