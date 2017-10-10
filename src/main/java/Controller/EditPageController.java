/**
 * Anuwat Angkuldee 5810401066
 */

package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class EditPageController {

    @FXML
    private Label appointmentIDLabel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField name;
    @FXML
    private TextArea description;
    @FXML
    private ComboBox<String> hour;
    @FXML
    private ComboBox<String> minute;
    @FXML
    private ComboBox<String> repeatComboBox;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Stage stage;

    private int editID;
    private Appointment appointment;

    private Date date = new Date();
    private DateFormat dateFormat = new SimpleDateFormat("u dd/MM/yyyy HH:mm", Locale.US);

    @FXML
    public void initialize() {
        for (int i=0; i<=23; i++) {
            String hour = "";
            if (i < 10)
                hour = "0";
            this.hour.getItems().add(hour + i);
        }
        for (int i=0; i<=59; i++) {
            String min = "";
            if (i < 10)
                min = "0";
            this.minute.getItems().add(min + i);
        }

        this.repeatComboBox.getItems().add("NONE");
        this.repeatComboBox.getItems().add("DAILY");
        this.repeatComboBox.getItems().add("WEEKLY");
        this.repeatComboBox.getItems().add("MONTHLY");
    }

    @FXML
    public void confirmEdit() {
        LocalDate localDate = this.datePicker.getValue();
        String name = this.name.getText();
        String description = this.description.getText();
        String hour = this.hour.getValue();
        String minute = this.minute.getValue();
        String repeat = this.repeatComboBox.getValue();
        String date = localDate.getDayOfWeek().getValue() + " " +
                localDate.getDayOfMonth()+"/"+localDate.getMonthValue()+"/"+localDate.getYear()+" "+
                hour+":"+minute;

        if (localDate != null && !"".equals(name) && hour != null && minute != null && repeat != null) {
            try {
                /**
                 * edit appointment
                 */
                this.appointment.setName(name);
                this.appointment.setDescription(description);
                this.date = dateFormat.parse(date);
                LocalDateTime localDateTime = LocalDateTime.ofInstant(this.date.toInstant(), ZoneId.systemDefault());
                this.appointment.setDate(localDateTime);

                /**
                 * CANT CHANGE DYNAMIC TYPE; NEED DESIGN PATTERN
                 */

                this.stage.close();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void cancelEdit() {
        this.stage.close();
    }

    public void setEditAppointment(Appointment appointment) {
        this.appointment = appointment;

        this.editID = appointment.getId();

        this.appointmentIDLabel.setText(this.appointmentIDLabel.getText()+this.editID);
        this.datePicker.setValue(this.appointment.getDate().toLocalDate());
        this.name.setText(this.appointment.getName());
        this.description.setText(this.appointment.getDescription());
        this.hour.setValue(this.appointment.getDate().getHour()+"");
        this.minute.setValue(this.appointment.getDate().getMinute()+"");
        String repeat = "NONE";
        if (appointment instanceof DailyAppointment)
            repeat = "DAILY";
        else if (appointment instanceof WeeklyAppointment)
            repeat = "WEEKLY";
        else if (appointment instanceof MonthlyAppointment)
            repeat = "MONTHLY";
        this.repeatComboBox.setValue(repeat);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}