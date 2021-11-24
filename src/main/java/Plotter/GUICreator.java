package Plotter;

import Service.Utils;
import dataHandler.FileParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import logger.Logger;

/**
 * Class to create ComboBox for choosing optional country
 * @author Kirill Dolbilov
 * @version 1.0
 */
public class GUICreator {
    /** Private constructor to avoid creating objects*/
    private GUICreator() {}

    /**
     * Method for creating ComboBox for choosing country
     * @return ComboBox with countries
     */
    public static ComboBox<String> CreateComboBox()
    {
        ObservableList<String> tempList = FXCollections.observableArrayList(FileParser.optionalCountries);

        // Capitalize names of countries
        for (int i = 0; i < tempList.size(); i++)
        {
            String oldString =  tempList.get(i);
            String newString = Utils.Capitalize(oldString);
            tempList.set(i,newString);
        }

        ComboBox<String> countriesComboBox = new ComboBox<>(tempList);

        // set default elements
        countriesComboBox.setValue("Russia");

        Logger.writeDebug("ComboBox was created");


        return countriesComboBox;

    }
}
