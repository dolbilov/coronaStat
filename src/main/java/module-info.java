module coronastat.personalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens coronastat.gui to javafx.fxml;
    exports coronastat.gui;
    exports configuration to com.google.gson;
    opens configuration to com.google.gson;
}