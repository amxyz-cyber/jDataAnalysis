package animatedCharts.view;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import javafx.geometry.Side;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class BarView extends AbstractChartView {
	private BarChart<Number,String>  chart;

	
	public BarView(Model data, String s) {
		super(data,s,Constants.CSS_SCRIPT);
		this.setSceneSize();
		this.setLayout();
		setupBarChart();
		createButtons();
		createWindow();
		auxWindow();
		this.addToGrid();
	}
	
	private void setupBarChart() {
		Axis<String> xAxis = new CategoryAxis();
	    xAxis.setLabel(Constants.BAR_CHART_Y.toUpperCase());

	    NumberAxis yAxis = new NumberAxis();
	    yAxis.setLabel(Constants.BAR_CHART_X_SUFFIX);
		this.chart = new BarChart<Number,String>(yAxis, xAxis);
		//this.chart.setData(this.data.getPcData());
		this.chart.setTitle(this.getChartTitle());
		this.chart.setLegendSide(Side.RIGHT);
		yAxis.setTickMarkVisible(false);
		xAxis.setTickMarkVisible(false);
		chart.setBarGap(3);
		chart.setCategoryGap(20);
		chart.autosize();
	}
	
	private void auxWindow() {
		this.getMainPane().getChildren().addAll(this.chart);
	}

	public BarChart getChart() {
		return chart;
	}


}
