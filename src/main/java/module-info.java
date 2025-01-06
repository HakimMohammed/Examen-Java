module exam.javaexam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens exam.javaexam to javafx.fxml;

    //exports views.dialogs.patient;

}