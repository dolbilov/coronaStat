package coronastat.gui;

import Plotter.GUICreator;
import Plotter.GraphPlotter;
import configuration.ConfigManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logger.Logger;


/**
 * Main application class
 * @author Kirill Dolbilov
 * @version 1.0
 */
public class HelloApplication extends Application {
    /** Method that initialize config and create GUI*/
    @Override
    public void start(Stage stage) {
        stage.setTitle("CoronaStat");

        try
        {
            ConfigManager.Initialize();
            Logger.writeInfo("Program started");

            var series = GraphPlotter.CreateGraph("brazil");

            BorderPane root = new BorderPane();
            root.setCenter(series);

            var comboBox = GUICreator.CreateComboBox();
            comboBox.setOnAction(event -> root.setCenter(GraphPlotter.CreateGraph(comboBox.getValue())));
            root.setTop(comboBox);

            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.show();
            Logger.writeInfo("Program has finished work");
        }
        catch (Exception exp) {
            Logger.writeFatal(exp.getMessage());
        }
    }

    /** Main method*/
    public static void main(String[] args)
    {
        launch();
    }
}