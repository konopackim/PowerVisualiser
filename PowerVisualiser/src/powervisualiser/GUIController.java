/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powervisualiser;

import static java.awt.Color.RED;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.swing.event.DocumentEvent;

/**
 * FXML Controller class
 *
 * @author Krzysztof
 */
public class GUIController implements Initializable {

    private int accuracy;

    @FXML
    private GridPane gridPane1, gridPane2, gridPane3, gridPane4, gridPane5;

    @FXML
    private Label connectionStatus;

    @FXML
    private Slider slider;

    @FXML
    private CheckBox CBvoltagePhase1, CBvoltagePhase2, CBvoltagePhase3;

    @FXML
    private CheckBox CBvoltagePhase12, CBvoltagePhase23, CBvoltagePhase31;

    @FXML
    private CheckBox CBcurrentPhase1, CBcurrentPhase2, CBcurrentPhase3;

    @FXML
    private TextField databaseAddress, serialNumber, portNumber, sliderValue, dbLogin;

    @FXML
    private PasswordField dbPassword;

    @FXML
    private LineChart lineChart1, lineChart2, lineChart3, lineChart4, lineChart5;

    private DatePicker datePicker1, datePicker2, datePicker3, datePicker4, datePicker5;
    public ArrayList<String> settings = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String fileName = "settings.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);

            try (
                    BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while ((line = bufferedReader.readLine()) != null) {
                    settings.add(line);
                }

                databaseAddress.setText(settings.get(0));
                portNumber.setText(settings.get(1));
                dbLogin.setText(settings.get(2));
                dbPassword.setText(settings.get(3));
                serialNumber.setText(settings.get(4));
                slider.setValue(Double.valueOf(settings.get(5)));
               
            }
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Nie można otworzyć pliku '"
                    + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Błąd w czasie zapisu do pliku '"
                    + fileName + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
        }

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

        sliderValue.setDisable(true);
        sliderValue.setText(String.valueOf((int) slider.getValue()));

        accuracy = (int) slider.getValue();

        slider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                sliderValue.setText(String.valueOf((int) slider.getValue()));
                accuracy = (int) slider.getValue();
            }

        });

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
            connect.connectToDatabase(databaseAddress.getText(), portNumber.getText(), dbLogin.getText(), dbPassword.getText());
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
        long tmp = (long) connect.sys_voltage.size() / accuracy;
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
            connect.connectToDatabase(databaseAddress.getText(), portNumber.getText(), dbLogin.getText(), dbPassword.getText());
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

        lineChart2.getData().clear();

        series21.setName("Napięcie fazy 1 [V]");
        series22.setName("Napięcie fazy 2 [V]");
        series23.setName("Napięcie fazy 3 [V]");

        if (CBvoltagePhase1.isSelected()) {
            long tmp = (long) connect.ln_voltage_phase_1.size() / accuracy;
            for (int i = 0; i < connect.ln_voltage_phase_1.size(); i += tmp) {
                series21.getData().add(new XYChart.Data(connect.date.get(i), connect.ln_voltage_phase_1.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
            }
            lineChart2.getData().clear();
            lineChart2.getData().addAll(series21, series22, series23);
        }

        if (CBvoltagePhase2.isSelected()) {
            long tmp = (long) connect.ln_voltage_phase_2.size() / accuracy;
            for (int i = 0; i < connect.ln_voltage_phase_2.size(); i += tmp) {
                series22.getData().add(new XYChart.Data(connect.date.get(i), connect.ln_voltage_phase_2.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
            }
            lineChart2.getData().clear();
            lineChart2.getData().addAll(series21, series22, series23);
        }

        if (CBvoltagePhase3.isSelected()) {
            long tmp = (long) connect.ln_voltage_phase_3.size() / accuracy;
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

        XYChart.Series series31 = new XYChart.Series();
        XYChart.Series series32 = new XYChart.Series();
        XYChart.Series series33 = new XYChart.Series();

        dbConnection connect = new dbConnection();

        LocalDateTime startDate = LocalDateTime.of(datePicker3.getValue(), LocalTime.now());
        LocalDateTime endDate = LocalDateTime.now();

        try {
            connect.connectToDatabase(databaseAddress.getText(), portNumber.getText(), dbLogin.getText(), dbPassword.getText());
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

        lineChart3.getData().clear();

        series31.setName("Napięcie faz 1-2 [V]");
        series32.setName("Napięcie faz 2-3 [V]");
        series33.setName("Napięcie faz 3-1 [V]");

        if (CBvoltagePhase12.isSelected()) {
            long tmp = (long) connect.ll_voltage_12.size() / accuracy;
            for (int i = 0; i < connect.ll_voltage_12.size(); i += tmp) {
                series31.getData().add(new XYChart.Data(connect.date.get(i), connect.ll_voltage_12.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
            }
            lineChart3.getData().clear();
            lineChart3.getData().addAll(series31, series32, series33);
        }

        if (CBvoltagePhase23.isSelected()) {
            long tmp = (long) connect.ll_voltage_23.size() / accuracy;
            for (int i = 0; i < connect.ll_voltage_23.size(); i += tmp) {
                series32.getData().add(new XYChart.Data(connect.date.get(i), connect.ll_voltage_23.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
            }
            lineChart3.getData().clear();
            lineChart3.getData().addAll(series31, series32, series33);
        }

        if (CBvoltagePhase31.isSelected()) {
            long tmp = (long) connect.ll_voltage_31.size() / accuracy;
            for (int i = 0; i < connect.ll_voltage_31.size(); i += tmp) {
                series33.getData().add(new XYChart.Data(connect.date.get(i), connect.ll_voltage_31.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
            }
            lineChart3.getData().clear();
            lineChart3.getData().addAll(series31, series32, series33);
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
            connect.connectToDatabase(databaseAddress.getText(), portNumber.getText(), dbLogin.getText(), dbPassword.getText());
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
        long tmp = (long) connect.sys_current.size() / accuracy;
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

        XYChart.Series series51 = new XYChart.Series();
        XYChart.Series series52 = new XYChart.Series();
        XYChart.Series series53 = new XYChart.Series();

        dbConnection connect = new dbConnection();

        LocalDateTime startDate = LocalDateTime.of(datePicker5.getValue(), LocalTime.now());
        LocalDateTime endDate = LocalDateTime.now();

        try {
            connect.connectToDatabase(databaseAddress.getText(), portNumber.getText(), dbLogin.getText(), dbPassword.getText());
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

        lineChart5.getData().clear();

        series51.setName("Natężenie fazy 1 [mA]");
        series52.setName("Natężenie fazy 2 [mA]");
        series53.setName("Natężenie fazy 3 [mA]");

        if (CBcurrentPhase1.isSelected()) {
            long tmp = (long) connect.phase_1_current.size() / accuracy;
            for (int i = 0; i < connect.phase_1_current.size(); i += tmp) {
                series51.getData().add(new XYChart.Data(connect.date.get(i), connect.phase_1_current.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
            }
            lineChart5.getData().clear();
            lineChart5.getData().addAll(series51, series52, series53);
        }

        if (CBcurrentPhase2.isSelected()) {
            long tmp = (long) connect.phase_2_current.size() / accuracy;
            for (int i = 0; i < connect.phase_2_current.size(); i += tmp) {
                series52.getData().add(new XYChart.Data(connect.date.get(i), connect.phase_2_current.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
            }
            lineChart5.getData().clear();
            lineChart5.getData().addAll(series51, series52, series53);
        }

        if (CBcurrentPhase3.isSelected()) {
            long tmp = (long) connect.phase_3_current.size() / accuracy;
            for (int i = 0; i < connect.phase_3_current.size(); i += tmp) {
                series53.getData().add(new XYChart.Data(connect.date.get(i), connect.phase_3_current.get(i) / 10000000));
//                    System.out.println(connect.sys_voltage.get(i));
            }
            lineChart5.getData().clear();
            lineChart5.getData().addAll(series51, series52, series53);
        }

    }

    @FXML
    private void connectionTested(javafx.event.ActionEvent e) {
        boolean status;
        status = true;
        dbConnection connect = new dbConnection();

        try {
            connect.connectToDatabase(databaseAddress.getText(), portNumber.getText(), dbLogin.getText(), dbPassword.getText());
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger("mylogger").log(Level.SEVERE, null, ex);
            status = false;
        }
        if (!status) {
            connectionStatus.setTextFill(Color.RED);
            connectionStatus.setText("Błąd połączenia!");
        } else {
            connectionStatus.setTextFill(Color.BLACK);
            connectionStatus.setText("Połączenie ok!");
        }
    }

    @FXML
    private void settingsSaved(javafx.event.ActionEvent e) {
        String fileName = "settings.txt";
        File file = new File(fileName);
        try {

            if (file.delete()) {
                System.out.println(file.getName() + " usunięty!");
            } else {
                System.out.println("Usuwanie pliku zakończone błędem!");
            }

        } catch (Exception e2) {
        }
        file = new File(fileName);
        try {
            FileWriter fileWriter = new FileWriter(fileName);

            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(databaseAddress.getText() + "\n");
                bufferedWriter.write(portNumber.getText() + "\n");
                bufferedWriter.write(dbLogin.getText() + "\n");
                bufferedWriter.write(dbPassword.getText() + "\n");
                bufferedWriter.write(serialNumber.getText() + "\n");
                bufferedWriter.write(String.valueOf(slider.getValue()) + "\n");

                connectionStatus.setTextFill(Color.BLACK);
                
                
                connectionStatus.setText("Ustawienia zapisane!");
            }

        } catch (IOException ex) {
            System.out.println(
                    "Błąd w zapisie do pliku '"
                    + fileName + "'");
        }
    }

}
