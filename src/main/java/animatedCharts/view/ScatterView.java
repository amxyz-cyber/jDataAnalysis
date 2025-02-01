package animatedCharts.view;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class ScatterView  extends AbstractChartView {
	private ScatterChart<Number,Number>  chart;
	
	public ScatterView(Model data, String s) {
		super(data,s,Constants.CSS_SCRIPT);
		this.setSceneSize();
		this.setLayout();
		setupScatterChart();
		createButtons();
		createWindow();
		this.auxWindow();
		this.addToGrid();
	}
	
	private void setupScatterChart() {
		NumberAxis yAxis = new NumberAxis();
	    yAxis.setLabel(Constants.SCATTER_CHART_Y2.toUpperCase() );

	    NumberAxis xAxis = new NumberAxis();
	    xAxis.setLabel(Constants.QUALITY.toUpperCase() );
		this.chart = new ScatterChart<Number, Number>(xAxis, yAxis);
		this.chart.setTitle(this.getChartTitle());
		this.chart.setLegendSide(Side.RIGHT);
		this.chart.setHorizontalGridLinesVisible(false);
		this.chart.setVerticalGridLinesVisible(false);
		//this.chart.setOpacity(0.5);
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(2);
		shadow.setColor(Color.GREY);
		this.chart.setEffect(shadow);
	}
	
	private void auxWindow(){
		 this.getMainPane().getChildren().addAll(this.chart);
	}
	
	public ScatterChart<Number,Number> getChart() {
		return chart;
	}

}
