package animatedCharts.view;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import javafx.geometry.Side;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;

public class AreaView  extends AbstractChartView  {
	private AreaChart<Number,Number>  chart;

	
	public AreaView(Model data, String s) {
		super(data,s,Constants.CSS_SCRIPT);
		this.setSceneSize();
		this.setLayout();
		setupAreaChart();
		createButtons();
		createWindow();
		auxWindow();
		this.addToGrid();
	}
	private void setupAreaChart() {
		NumberAxis xAxis = new NumberAxis(1,31,1);
	    xAxis.setLabel(Constants.AREA_CHART_X.toUpperCase());

	    NumberAxis yAxis = new NumberAxis();
	    yAxis.setLabel(Constants.AREA_CHART_Y);
		this.chart = new AreaChart<Number, Number>(xAxis, yAxis);
		//this.chart.setData(this.data.getPcData());
		this.chart.setTitle(this.getChartTitle());
		this.chart.setLegendSide(Side.RIGHT);
		this.chart.setCreateSymbols(false);
		//this.chart.autosize();
		this.chart.setPrefHeight(this.getSceneHeight()/1.5);
		//this.chart.setOpacity(0.5);
		//yAxis.setTickMarkVisible(true);
		//xAxis.setTickMarkVisible(true);
		//this.percentLabel = new Label();
		//this.percentLabel.setTextFill(Color.DARKOLIVEGREEN);
		//this.percentLabel.setStyle("-fx-font: 20 arial;");
		
	}
	
	private void auxWindow() {
		this.getMainPane().getChildren().addAll(this.chart);
		 //this.mainPane.getChildren().addAll(this.chart,this.percentLabel);
	}
	

	public AreaChart getChart() {
		return chart;
	}


}
