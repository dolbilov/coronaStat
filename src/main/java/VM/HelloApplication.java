package VM;

import Model.configuration.ConfigManager;
import Model.logger.Logger;
import View.Plotter.GUICreator;
import View.Plotter.GraphPlotter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


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

            var series = GraphPlotter.CreateGraph("russia");

            BorderPane root = new BorderPane();
            root.setCenter(series);

            var comboBox = GUICreator.CreateComboBox();
            comboBox.setOnAction(event ->
                    {
                        String newCountry = comboBox.getValue();
                        root.setCenter(GraphPlotter.CreateGraph(newCountry));
                        Logger.writeInfo(newCountry + " selected");
                    });

            root.setTop(comboBox);

            Scene scene = new Scene(root, 1200, 800); stage.setScene(scene);
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