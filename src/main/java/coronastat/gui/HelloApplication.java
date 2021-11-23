package coronastat.gui;

import Plotter.GraphPlotter;
import configuration.ConfigManager;
import dataHandler.DataHandler;
import dataHandler.FileParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
            Scene scene = GraphPlotter.CreateGraph();
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception exp) {
            System.out.println(exp.getMessage());
        }



        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);

    }

    public static void main(String[] args)
    {
        launch();
    }
}