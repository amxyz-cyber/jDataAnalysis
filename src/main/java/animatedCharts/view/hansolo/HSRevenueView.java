package animatedCharts.view.hansolo;

import java.util.List;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.view.AbstractChartView;
import eu.hansolo.fx.charts.NestedBarChart;
import eu.hansolo.fx.charts.data.ChartItem;
import eu.hansolo.fx.charts.series.ChartItemSeries;
import javafx.geometry.Pos;

public class HSRevenueView extends AbstractChartView {
	private NestedBarChart chart;
	
	public HSRevenueView(Model data) {
		super(data,Constants.CSS_SCRIPT_2);
		this.setSceneSize();
		this.setLayout();
		setupHeadline();
		this.createButtons();
		this.createWindow();
		this.setChartWindow(Pos.TOP_LEFT);
		auxWindow();
		this.addToGrid();
	}
	
	public NestedBarChart getChart() {
		return chart;
	}
	
	private void auxWindow() {
		this.getMainPane().getChildren().addAll(this.getRow());
		this.getMainPane().getChildren().add(this.getChartPane());
	}

	public void setupChart(List<ChartItemSeries<ChartItem>> items) {
		this.chart =  new NestedBarChart();
		this.chart.addSeries(items);
		chart.setScaleX(1.1);
		chart.setScaleY(1.1);
	}
	
	public void showChart() {
		chart.autosize();
		//this.getMainPane().getChildren().addAll(this.chart,this.getLegend());
		this.getChartPane().getChildren().addAll(this.chart,this.getLegend());
	}


}
