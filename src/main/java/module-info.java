module personalProject {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens VM;
    opens View.Plotter;
    opens Model.configuration;
    opens Model.dataHandler;
    opens Model.logger;
    opens Model.Service;
}