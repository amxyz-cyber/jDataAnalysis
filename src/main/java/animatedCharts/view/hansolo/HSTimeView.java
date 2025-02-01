package animatedCharts.view.hansolo;

import java.util.List;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.view.AbstractChartView;
import eu.hansolo.fx.charts.XYChart;
import eu.hansolo.fx.charts.XYPane;
import eu.hansolo.fx.charts.data.TYChartItem;
import eu.hansolo.fx.charts.series.XYSeries;
import javafx.scene.paint.Color;

public class HSTimeView extends AbstractChartView {
	private XYChart<TYChartItem> chart;
	private XYPane chartPane;

	public HSTimeView(Model data, String title) {
		super(data, title,Constants.CSS_SCRIPT_2);
		this.setSceneSize();
		this.setLayout();
		setupHeadline();
		this.createButtons();
		this.createWindow();
		auxWindow();
		this.addToGrid();
	}
	
	
	private void auxWindow() {
		this.getMainPane().getChildren().addAll(this.getRow());
		//this.getMainPane().getChildren().add(this.getChartPane());
	}
	
	public XYChart getChart() {
		return chart;
	}

	public void setupChart() {
		this.chart = new XYChart<>(chartPane,this.getChartGrid(), this.getyAxis(),this.getxAxis());
		chart.setScaleX(1.1);
		chart.setScaleY(1.1);
	}
	
	public void setSeries(List<XYSeries> l) {
		this.chartPane = new XYPane(l);
	}
	
	public void setSeries(Color colStroke, Color devColor,List<XYSeries> l) {
		this.chartPane = this.addShades(colStroke, devColor, l);
	}
	
	public void showChart() {
		chart.autosize();
		this.getMainPane().getChildren().addAll(this.chart);
		//this.getChartPane().getChildren().addAll(this.lineChart,this.getLegend());
	}

	public XYPane getchartPane() {
		return chartPane;
	}

}
