/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powervisualiser;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javax.swing.event.DocumentEvent;

/**
 * FXML Controller class
 *
 * @author Krzysztof
 */
public class GUIController implements Initializable {

    @FXML
    private GridPane gridPane1, gridPane2, gridPane3, gridPane4, gridPane5;

    @FXML
    private TextField databaseAddress, serialNumber, portNumber;
    
    @FXML
    private LineChart lineChart1, lineChart2, lineChart3, lineChart4, lineChart5;

    private DatePicker datePicker1, datePicker2, datePicker3, datePicker4, datePicker5;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        datePicker1 = new DatePicker();
        datePicker1.setPromptText("Początkowy dzień");
        gridPane1.add(datePicker1, 0, 0);

        datePicker2 = new DatePicker();
        datePicker2.setPromptText("Początkowy dzień");
        gridPane2.add(datePicker2, 0, 0);

        datePicker3 = new DatePicker();
        datePicker3.setPromptText("Początkowy dzień");
        gridPane3.add(datePicker3, 0, 0);

        datePicker4 = new DatePicker();
        datePicker4.setPromptText("Początkowy dzień");
        gridPane4.add(datePicker4, 0, 0);

        datePicker5 = new DatePicker();
        datePicker5.setPromptText("Początkowy dzień");
        gridPane5.add(datePicker5, 0, 0);

//        String dbAddress = databaseAddress.getText();
//        String serialNo = serialNumber.getText();
//        String portNo = portNumber.getText();
    }

    @FXML
    private void button1Clicked(javafx.event.ActionEvent e) {
//        System.out.println(datePicker1.getValue());

        dbConnection connect = new dbConnection();

        LocalDateTime startDate = LocalDateTime.of(datePicker1.getValue(), LocalTime.now());
        LocalDateTime endDate = LocalDateTime.now();

        try {
            connect.connectToDatabase(databaseAddress.getText(), portNumber.getText());
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }

        try {
            connect.selectFromDatabase(startDate.toString(), endDate.toString(),
                    serialNumber.getText());

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }
        try {
            connect.closeConnection();
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void button2Clicked(javafx.event.ActionEvent e) {
//        System.out.println(datePicker2.getValue());
        
        dbConnection connect = new dbConnection();
        
        LocalDateTime startDate = LocalDateTime.of(datePicker2.getValue(), LocalTime.now());
        LocalDateTime endDate = LocalDateTime.now();

        try {
            connect.connectToDatabase(databaseAddress.getText(), portNumber.getText());
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }

        try {
            connect.selectFromDatabase(startDate.toString(), endDate.toString(),
                    serialNumber.getText());

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }
        try {
            connect.closeConnection();
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void button3Clicked(javafx.event.ActionEvent e) {
//        System.out.println(datePicker3.getValue());
        
        dbConnection connect = new dbConnection();
        
        LocalDateTime startDate = LocalDateTime.of(datePicker3.getValue(), LocalTime.now());
        LocalDateTime endDate = LocalDateTime.now();

        try {
            connect.connectToDatabase(databaseAddress.getText(), portNumber.getText());
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }

        try {
            connect.selectFromDatabase(startDate.toString(), endDate.toString(),
                    serialNumber.getText());

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }
        try {
            connect.closeConnection();
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void button4Clicked(javafx.event.ActionEvent e) {
//        System.out.println(datePicker4.getValue());
        
        dbConnection connect = new dbConnection();
        
        LocalDateTime startDate = LocalDateTime.of(datePicker4.getValue(), LocalTime.now());
        LocalDateTime endDate = LocalDateTime.now();

        try {
            connect.connectToDatabase(databaseAddress.getText(), portNumber.getText());
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }

        try {
            connect.selectFromDatabase(startDate.toString(), endDate.toString(),
                    serialNumber.getText());

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }
        try {
            connect.closeConnection();
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void button5Clicked(javafx.event.ActionEvent e) {
//        System.out.println(datePicker5.getValue());
        
        dbConnection connect = new dbConnection();
        
        LocalDateTime startDate = LocalDateTime.of(datePicker5.getValue(), LocalTime.now());
        LocalDateTime endDate = LocalDateTime.now();

        try {
            connect.connectToDatabase(databaseAddress.getText(), portNumber.getText());
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }

        try {
            connect.selectFromDatabase(startDate.toString(), endDate.toString(),
                    serialNumber.getText());

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
        }
        try {
            connect.closeConnection();
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
