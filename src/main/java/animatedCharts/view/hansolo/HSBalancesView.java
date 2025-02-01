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

public class HSBalancesView extends AbstractChartView  {
	private XYChart<XYChartItem> lineChart;
	private XYPane lineChartPane;

	public HSBalancesView(Model data, String title) {
		super(data,title,Constants.CSS_SCRIPT_2);
		this.setSceneSize();
		this.setLayout();
		setupHeadline();
		this.createButtons();
		this.createWindow();
		this.setChartWindow(Pos.TOP_RIGHT);
		auxWindow();
		this.addToGrid();
	}
	
	
	private void auxWindow() {
		this.getMainPane().getChildren().addAll(this.getRow());
		//this.getMainPane().getChildren().add(this.getChartPane());
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
