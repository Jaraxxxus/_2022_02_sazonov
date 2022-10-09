module com.example.task1 {
    requires javafx.controls;
    requires javafx.fxml;


    //opens cft.shift.task1 to javafx.fxml;
    exports com.example.task1;
    opens com.example.task1 to javafx.fxml;
}