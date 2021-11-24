package View.Plotter;

import Model.Service.Utils;
import Model.dataHandler.DataHandler;
import Model.dataHandler.FileParser;
import Model.logger.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that create graph representation of COVID-19 cases stats
 * @author Kirill Dolbilov
 * @version 1.0
 */
public class GraphPlotter {
    /** Log base that uses to reduce graph size */
    private static final int logBase = 10;

    /**
     * Create LineChart(graph) with stats
     * @param countryName Name of country from optional countries list
     *                    that will be added to graph
     * @return LineChart(graph) with required countries + countryName
     */
    public static LineChart<Number, Number> CreateGraph(String countryName) {
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> numberNumberLineChart = new LineChart<>(x, y);
        numberNumberLineChart.setTitle("COVID-19 statistics");
        numberNumberLineChart.setCreateSymbols(false);

        var rawX = DataHandler.getInstance().getReducedData();
        var rawY = DataHandler.getInstance().getDiffForReducedPeriod();

        // create array of countries that should be drawn
        String[] countriesToDraw = FileParser.requiredCountries.clone();
        ArrayList<String> tempList = new ArrayList<>(List.of(countriesToDraw));
        tempList.add(countryName);
        countriesToDraw = tempList.toArray(new String[0]);


        for (var country : countriesToDraw)
        {
            country = country.toLowerCase();

            if (rawX.get(country).size() != rawY.get(country).size())
            {
                Logger.writeWarn("Dimensions of X and Y are not match");
            }

            XYChart.Series series = new XYChart.Series();
            series.setName(Utils.Capitalize(country));
            ObservableList<XYChart.Data> data = FXCollections.observableArrayList();

            for (int i = 0; i < rawX.get(country).size(); i++)
            {
                var tempX = Math.log(rawX.get(country).get(i)) / Math.log(logBase);
                var tempY = Math.log(rawY.get(country).get(i)) / Math.log(logBase);

                data.add(new XYChart.Data(tempX,tempY));

            }

            series.setData(data);
            numberNumberLineChart.getData().add(series);
        }

        return numberNumberLineChart;
    }
}
