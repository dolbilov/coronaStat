package coronastat.gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import configuration.Config;
import configuration.ConfigManager;
import dataHandler.DataHandler;
import dataHandler.FileParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logger.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)  {

        try
        {
            ConfigManager.Initialize();
            Logger.writeInfo("Program started");
            var Y = DataHandler.getInstance().getDiffForReducedPeriod();
            var X = DataHandler.getInstance().getReducedData();

            for (var country : FileParser.fileNames)
            {
                var s1 = X.get(country).size();
                var s2 = Y.get(country).size();
                System.out.println(s1);
                System.out.println(s2);
            }
        }
        catch (Exception e)
        {
            Logger.writeError(e.getMessage());
        }

        launch();
    }
}