package coronastat.gui;

import Plotter.GUICreator;
import Plotter.GraphPlotter;
import configuration.ConfigManager;
import dataHandler.DataHandler;
import dataHandler.FileParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logger.Logger;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage.setTitle("CoronaStat");

        try
        {
            ConfigManager.Initialize();
            Logger.writeInfo("Program started");

            var series = GraphPlotter.CreateGraph("russia");

            BorderPane root = new BorderPane();
            root.setCenter(series);

            var comboBox = GUICreator.CreateComboBox();
            comboBox.setOnAction(event -> root.setCenter(GraphPlotter.CreateGraph(comboBox.getValue())));
            root.setTop(comboBox);


            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception exp) {
            Logger.writeFatal(exp.getMessage());
        }
    }

    public static void main(String[] args)
    {
        launch();
    }
}