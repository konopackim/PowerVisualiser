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
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
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
    private CheckBox CBvoltagePhase1, CBvoltagePhase2, CBvoltagePhase3;

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

        lineChart1.setAnimated(false);
        lineChart2.setAnimated(false);
        lineChart3.setAnimated(false);
        lineChart4.setAnimated(false);
        lineChart5.setAnimated(false);

        datePicker1.setEditable(false);
        datePicker2.setEditable(false);
        datePicker3.setEditable(false);
        datePicker4.setEditable(false);
        datePicker5.setEditable(false);
//        String dbAddress = databaseAddress.getText();
//        String serialNo = serialNumber.getText();
//        String portNo = portNumber.getText();
    }

    @FXML
    private void button1Clicked(javafx.event.ActionEvent e) {
//        System.out.println(datePicker1.getValue());

        XYChart.Series series1 = new XYChart.Series();

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
        long tmp = (long) connect.sys_voltage.size() / 50;
        series1.setName("Napięcie systemu [V]");
        for (int i = 0; i < connect.sys_voltage.size(); i += tmp) {
            series1.getData().add(new XYChart.Data(connect.date.get(i), connect.sys_voltage.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
        }
        lineChart1.getData().clear();
        lineChart1.getData().addAll(series1);

    }

    @FXML
    private void button2Clicked(javafx.event.ActionEvent e) {
//        System.out.println(datePicker2.getValue());

        XYChart.Series series21 = new XYChart.Series();
        XYChart.Series series22 = new XYChart.Series();
        XYChart.Series series23 = new XYChart.Series();

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
        
        series21.setName("Napięcie fazy 1 [V]");
        series22.setName("Napięcie fazy 2 [V]");
        series23.setName("Napięcie fazy 3 [V]");

        if (CBvoltagePhase1.isSelected()) {
            long tmp = (long) connect.ln_voltage_phase_1.size() / 50;
            for (int i = 0; i < connect.ln_voltage_phase_1.size(); i += tmp) {
                series21.getData().add(new XYChart.Data(connect.date.get(i), connect.ln_voltage_phase_1.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
            }
            lineChart2.getData().clear();
            lineChart2.getData().addAll(series21, series22, series23);
        }
        
        if (CBvoltagePhase2.isSelected()) {
            long tmp = (long) connect.ln_voltage_phase_2.size() / 50;
            for (int i = 0; i < connect.ln_voltage_phase_2.size(); i += tmp) {
                series22.getData().add(new XYChart.Data(connect.date.get(i), connect.ln_voltage_phase_2.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
            }
            lineChart2.getData().clear();
            lineChart2.getData().addAll(series21, series22, series23);
        }
        
        if (CBvoltagePhase3.isSelected()) {
            long tmp = (long) connect.ln_voltage_phase_3.size() / 50;
            for (int i = 0; i < connect.ln_voltage_phase_3.size(); i += tmp) {
                series23.getData().add(new XYChart.Data(connect.date.get(i), connect.ln_voltage_phase_3.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
            }
            lineChart2.getData().clear();
            lineChart2.getData().addAll(series21, series22, series23);
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

        XYChart.Series series4 = new XYChart.Series();
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
        long tmp = (long) connect.sys_current.size() / 50;
        series4.setName("Natężenie systemu [mA]");
        for (int i = 0; i < connect.sys_current.size(); i += tmp) {
            series4.getData().add(new XYChart.Data(connect.date.get(i), connect.sys_current.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
        }

        lineChart4.getData().clear();
        lineChart4.getData().addAll(series4);
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
