package Plotter;

import Service.Utils;
import dataHandler.FileParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;


public class GUICreator {
    private GUICreator() {}

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

        ComboBox<String> countriesComboBox = new ComboBox<String>(tempList);

        // set default elements
        countriesComboBox.setValue("Russia");


        return countriesComboBox;

    }
}
