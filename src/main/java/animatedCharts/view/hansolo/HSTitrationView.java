package animatedCharts.view.hansolo;

import java.util.List;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.view.AbstractChartView;
import eu.hansolo.fx.charts.XYChart;
import eu.hansolo.fx.charts.XYPane;
import eu.hansolo.fx.charts.data.XYChartItem;
import eu.hansolo.fx.charts.series.XYSeries;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class HSTitrationView extends AbstractChartView {
	private Label row;
	private XYChart<XYChartItem> lineChart;
	private XYPane lineChartPane;
	
	
	
	public HSTitrationView(Model data) {
		super(data,Constants.CSS_SCRIPT_2);
		this.setSceneSize();
		this.setLayout();
		setupChart();
		this.createButtons();
		this.createWindow();
		this.setChartWindow(Pos.CENTER_LEFT);
		auxWindow();
		this.addToGrid();
	}


	private void setupChart() {
		 row = new Label();
		 row.getStyleClass().add(Constants.CSS_LABEL);
	}
	
	private void auxWindow() {
		//this.getChartPane().prefWidth(getSceneHeight()*0.8);
		//this.getChartPane().prefWidth(getSceneWidth()*0.9);
		this.getMainPane().getChildren().addAll(this.row);
		//this.getMainPane().prefWidth(getSceneHeight()*0.8);
		//this.getMainPane().prefWidth(getSceneWidth()*0.9);
		//this.getMainPane().getChildren().add(this.getChartPane());
	}
	
	public Label getRow() {
		return row;
	}

	public XYChart getChart() {
		return lineChart;
	}


	public void setLineChart() {
		this.lineChart = new XYChart<>(lineChartPane,this.getChartGrid(), this.getyAxis(),this.getxAxis());
		lineChart.setScaleX(1.1);
		lineChart.setScaleY(1.1);
	}
	
	public void setupSeries(List<XYSeries> l) {
		this.lineChartPane = new XYPane(l);
	}
	
	public void showChart() {
		lineChart.autosize();
		this.getMainPane().getChildren().addAll(this.lineChart,this.getLegend());
		//this.getChartPane().getChildren().addAll(this.lineChart,this.getLegend());
	}

	public XYPane getLineChartPane() {
		return lineChartPane;
	}

}
