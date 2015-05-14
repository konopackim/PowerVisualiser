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
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author Krzysztof
 */
public class FXMLDocumentController implements Initializable {

    int counter = 0;
    @FXML
//    public PieChart piechart;
    public LineChart<String, Integer> lineChart1;

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
            lineChart1.setTitle("Wahania kursów akcji, 2015");

            XYChart.Series series1 = new XYChart.Series();
            XYChart.Series series2 = new XYChart.Series();
            XYChart.Series series3 = new XYChart.Series();

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
            lineChart1.getData().addAll(series1, series2, series3);
            counter++;
        }

    }

    @FXML
    protected void button2click(ActionEvent event) {
        lineChart1.getData().clear();
        counter--;
    }
}
