/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author Krzysztof
 */
public class FXMLDocumentController implements Initializable {

    XYChart.Series series1, series2, series3, series4, series5, series6;
    int counter = 0;
    @FXML
    public LineChart<String, Integer> lineChart1;

    @FXML
    public LineChart<String, Integer> lineChart2;

    @FXML
    public LineChart<String, Integer> lineChart3;

    @FXML
    CheckBox checkBox1;

    @FXML
    CheckBox checkBox2;

    @FXML
    ComboBox chooseInterval;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        chooseInterval.getItems().addAll(
                "24 godziny",
                "7 dni",
                "Ostatni miesiąc",
                "Ostatnie 3 miesiące"
        );
        lineChart3.setAnimated(false);

//        try {
//            connect.connectToDatabase();
//        } catch (SQLException | ClassNotFoundException | IOException ex) {
//            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
//        }
//
//        LocalDateTime dt1 = LocalDateTime.of(2015, 1, 22, 8, 0);
//        LocalDateTime dt2 = LocalDateTime.of(2015, 2, 2, 8, 0);
//        try {
//            connect.selectFromDatabase(dt1.toString(), dt2.toString(),
//                    "SERIAL1");
//
//        } catch (SQLException | ClassNotFoundException | IOException ex) {
//            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
//        }
//
//        try {
//            connect.closeConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      Odczyt przykładowych danych -> ll_voltage_12
        lineChart2.setAnimated(false);

        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        series3 = new XYChart.Series();

        series1.setName("Michał");

        series1.getData().add(new XYChart.Data("Jan", 23));
        series1.getData().add(new XYChart.Data("Feb", 14));
        series1.getData().add(new XYChart.Data("Mar", 15));
        series1.getData().add(new XYChart.Data("Apr", 24));
        series1.getData().add(new XYChart.Data("May", 34));
        series1.getData().add(new XYChart.Data("Jun", 36));
        series1.getData().add(new XYChart.Data("Jul", 22));
        series1.getData().add(new XYChart.Data("Aug", 45));
        series1.getData().add(new XYChart.Data("Sep", 43));
        series1.getData().add(new XYChart.Data("Oct", 17));
        series1.getData().add(new XYChart.Data("Nov", 29));
        series1.getData().add(new XYChart.Data("Dec", 25));

        series2.setName("Leszek");
        series2.getData().add(new XYChart.Data("Jan", 33));
        series2.getData().add(new XYChart.Data("Feb", 34));
        series2.getData().add(new XYChart.Data("Mar", 25));
        series2.getData().add(new XYChart.Data("Apr", 44));
        series2.getData().add(new XYChart.Data("May", 39));
        series2.getData().add(new XYChart.Data("Jun", 16));
        series2.getData().add(new XYChart.Data("Jul", 55));
        series2.getData().add(new XYChart.Data("Aug", 54));
        series2.getData().add(new XYChart.Data("Sep", 48));
        series2.getData().add(new XYChart.Data("Oct", 27));
        series2.getData().add(new XYChart.Data("Nov", 37));
        series2.getData().add(new XYChart.Data("Dec", 29));

        series3.setName("Krzyś");
        series3.getData().add(new XYChart.Data("Jan", 44));
        series3.getData().add(new XYChart.Data("Feb", 35));
        series3.getData().add(new XYChart.Data("Mar", 36));
        series3.getData().add(new XYChart.Data("Apr", 33));
        series3.getData().add(new XYChart.Data("May", 31));
        series3.getData().add(new XYChart.Data("Jun", 26));
        series3.getData().add(new XYChart.Data("Jul", 22));
        series3.getData().add(new XYChart.Data("Aug", 25));
        series3.getData().add(new XYChart.Data("Sep", 43));
        series3.getData().add(new XYChart.Data("Oct", 44));
        series3.getData().add(new XYChart.Data("Nov", 45));
        series3.getData().add(new XYChart.Data("Dec", 44));

        series4 = new XYChart.Series();
        series5 = new XYChart.Series();
        series6 = new XYChart.Series();

        series4.setName("Alicja");

        series4.getData().add(new XYChart.Data("Jan", 20));
        series4.getData().add(new XYChart.Data("Feb", 24));
        series4.getData().add(new XYChart.Data("Mar", 25));
        series4.getData().add(new XYChart.Data("Apr", 14));
        series4.getData().add(new XYChart.Data("May", 24));
        series4.getData().add(new XYChart.Data("Jun", 26));
        series4.getData().add(new XYChart.Data("Jul", 32));
        series4.getData().add(new XYChart.Data("Aug", 35));
        series4.getData().add(new XYChart.Data("Sep", 23));
        series4.getData().add(new XYChart.Data("Oct", 57));
        series4.getData().add(new XYChart.Data("Nov", 19));
        series4.getData().add(new XYChart.Data("Dec", 45));

        series5.setName("Basia");
        series5.getData().add(new XYChart.Data("Jan", 11));
        series5.getData().add(new XYChart.Data("Feb", 23));
        series5.getData().add(new XYChart.Data("Mar", 44));
        series5.getData().add(new XYChart.Data("Apr", 12));
        series5.getData().add(new XYChart.Data("May", 55));
        series5.getData().add(new XYChart.Data("Jun", 12));
        series5.getData().add(new XYChart.Data("Jul", 15));
        series5.getData().add(new XYChart.Data("Aug", 52));
        series5.getData().add(new XYChart.Data("Sep", 11));
        series5.getData().add(new XYChart.Data("Oct", 55));
        series5.getData().add(new XYChart.Data("Nov", 12));
        series5.getData().add(new XYChart.Data("Dec", 42));

        series6.setName("Kasia");
        series6.getData().add(new XYChart.Data("Jan", 22));
        series6.getData().add(new XYChart.Data("Feb", 33));
        series6.getData().add(new XYChart.Data("Mar", 13));
        series6.getData().add(new XYChart.Data("Apr", 14));
        series6.getData().add(new XYChart.Data("May", 15));
        series6.getData().add(new XYChart.Data("Jun", 65));
        series6.getData().add(new XYChart.Data("Jul", 14));
        series6.getData().add(new XYChart.Data("Aug", 22));
        series6.getData().add(new XYChart.Data("Sep", 14));
        series6.getData().add(new XYChart.Data("Oct", 11));
        series6.getData().add(new XYChart.Data("Nov", 12));
        series6.getData().add(new XYChart.Data("Dec", 41));

//        ObservableList<PieChart.Data> pieChartData
//                = FXCollections.observableArrayList(
//                        new PieChart.Data("Michał B", 60),
//                        new PieChart.Data("Michał K", 25),
//                        new PieChart.Data("Leszek", 15),
//                        new PieChart.Data("Krzyś", 15)
//                );
//
//        piechart.setData(pieChartData);
        //lineChart.getData().add(series1);
    }

    @FXML
    protected void button1click(ActionEvent event) {
        if (counter < 1) {
//            lineChart1.setTitle("Wahania kursów akcji, 2015");

            lineChart1.getData().addAll(series1, series2, series3);
            counter++;
        }

    }

    @FXML
    protected void button2click(ActionEvent event) {
        if (counter > 0) {
            lineChart1.getData().clear();
            counter--;
        }

    }

    @FXML
    private void checkBox1Clicked(ActionEvent e) {
        if (checkBox1.isSelected()) {
            lineChart2.getData().addAll(series1, series2, series3);
        } else {
            lineChart2.getData().clear();
            if (checkBox2.isSelected()) {
                checkBox2Clicked(e);
            }
        }

    }

    @FXML
    private void checkBox2Clicked(ActionEvent e) {

        if (checkBox2.isSelected()) {
            lineChart2.getData().addAll(series4, series5, series6);
        } else {
            lineChart2.getData().clear();
            if (checkBox1.isSelected()) {
                checkBox1Clicked(e);
            }
        }

    }

    @FXML
    private void intervalChosen(ActionEvent e) {
        if (chooseInterval.getValue().toString().equals("24 godziny")) {

            XYChart.Series series24h = new XYChart.Series();
            //Connection to the database
            dbConnection connect = new dbConnection();
            System.out.println("24 godziny");
            LocalDateTime dt1 = LocalDateTime.now().minusHours(24);
            LocalDateTime dt2 = LocalDateTime.now();
            try {
                connect.connectToDatabase();
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
            }
            try {
                connect.selectFromDatabase(dt1.toString(), dt2.toString(),
                        "SERIAL1");

            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
            }
            try {
                connect.closeConnection();
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            series24h.setName("SERIAL1");
            for (int i = 0; i < connect.date.size(); i++) {
                if (i % 100 == 0) {
                    series24h.getData().add(new XYChart.Data(connect.date.get(i), connect.sys_voltage.get(i)));
//                    System.out.println(connect.sys_voltage.get(i));
                }
            }
            lineChart3.getData().clear();
            lineChart3.getData().addAll(series24h);
        } else if (chooseInterval.getValue().toString().equals("7 dni")) {
            XYChart.Series series7d = new XYChart.Series();
            //Connection to the database
            dbConnection connect = new dbConnection();
            System.out.println("7 dni");
            LocalDateTime dt1 = LocalDateTime.now().minusDays(7);
            LocalDateTime dt2 = LocalDateTime.now();
            try {
                connect.connectToDatabase();
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
            }
            try {
                connect.selectFromDatabase(dt1.toString(), dt2.toString(),
                        "SERIAL1");

            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
            }
            try {
                connect.closeConnection();
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            series7d.setName("SERIAL1");
            for (int i = 0; i < connect.date.size(); i++) {
                if (i % 700 == 0) {
                    series7d.getData().add(new XYChart.Data(connect.date.get(i), connect.sys_voltage.get(i)));
//                    System.out.println(connect.sys_voltage.get(i));
                }
            }
            lineChart3.getData().clear();
            lineChart3.getData().addAll(series7d);
        } else if (chooseInterval.getValue().toString().equals("Ostatni miesiąc")) {
            XYChart.Series series1m = new XYChart.Series();
            //Connection to the database
            dbConnection connect = new dbConnection();
            System.out.println("Ostatni miesiąc");
            LocalDateTime dt1 = LocalDateTime.now().minusMonths(1);
            LocalDateTime dt2 = LocalDateTime.now();
            try {
                connect.connectToDatabase();
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
            }
            try {
                connect.selectFromDatabase(dt1.toString(), dt2.toString(),
                        "SERIAL1");

            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
            }
            try {
                connect.closeConnection();
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            series1m.setName("SERIAL1");
            for (int i = 0; i < connect.date.size(); i++) {
                if (i % 3000 == 0) {
                    series1m.getData().add(new XYChart.Data(connect.date.get(i), connect.sys_voltage.get(i)));
//                    System.out.println(connect.sys_voltage.get(i));
                }
            }
            lineChart3.getData().clear();
            lineChart3.getData().addAll(series1m);
        } else if (chooseInterval.getValue().toString().equals("Ostatnie 3 miesiące")) {
            XYChart.Series series3m = new XYChart.Series();
            //Connection to the database
            dbConnection connect = new dbConnection();
            System.out.println("Ostatnie 3 miesiące");
            LocalDateTime dt1 = LocalDateTime.now().minusMonths(3);
            LocalDateTime dt2 = LocalDateTime.now();
            try {
                connect.connectToDatabase();
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
            }
            try {
                connect.selectFromDatabase(dt1.toString(), dt2.toString(),
                        "SERIAL1");

            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
            }
            try {
                connect.closeConnection();
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            series3m.setName("SERIAL1");
            for (int i = 0; i < connect.date.size(); i++) {
                if (i % 9000 == 0) {
                    series3m.getData().add(new XYChart.Data(connect.date.get(i), connect.sys_voltage.get(i)));
//                    System.out.println(connect.sys_voltage.get(i));
                }
            }
            lineChart3.getData().clear();
            lineChart3.getData().addAll(series3m);
        }
    }
    
    
}
