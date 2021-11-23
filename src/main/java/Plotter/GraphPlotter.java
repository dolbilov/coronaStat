package Plotter;

import dataHandler.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraphPlotter {
    public static Scene CreateGraph() {
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> numberNumberLineChart = new LineChart<Number, Number>(x,y);
        numberNumberLineChart.setTitle("Series");
        XYChart.Series series = new XYChart.Series();
        series.setName("USA");
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();

        var rawX = DataHandler.getInstance().getReducedData();
        var rawY = DataHandler.getInstance().getDiffForReducedPeriod();

        for (int i = 0; i < rawX.size(); i++)
        {
            data.add(new XYChart.Data(
                    rawX.get("usa").get(i),
                    rawY.get("usa").get(i)
            ));

        }

        series.setData(data);
        Scene scene = new Scene(numberNumberLineChart, 800, 600);
        numberNumberLineChart.getData().add(series);
        return scene;
    }
}
