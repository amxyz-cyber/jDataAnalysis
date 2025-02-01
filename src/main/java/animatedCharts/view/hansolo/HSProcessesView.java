package animatedCharts.view.hansolo;

import java.util.List;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.view.AbstractChartView;
import eu.hansolo.fx.charts.data.BubbleGridChartItem;

public class HSProcessesView extends AbstractChartView {

	public HSProcessesView(Model data, String s) {
		super(data,s,Constants.CSS_SCRIPT_2);
		this.setSceneSize();
		this.setLayout();
		setupHeadline();
		this.createButtons();
		this.createWindow();
		auxWindow();
		this.addToGrid();
	}
	
	private void auxWindow() {
		this.getMainPane().getChildren().add(this.getRow());
	}
	
	public void setupChart(List<BubbleGridChartItem> chartItems) {
			//this.getMatrixHeatMap().setPrefSize(this.getSceneWidth()*0.5, this.getSceneWidth()*0.5);
		this.bubbleChartStyle(chartItems);
		this.getBubbleGridChart().setScaleY(1.2);
		this.getBubbleGridChart().setScaleX(1.2);
	}
	
	public void showChart() {
			this.getBubbleGridChart().autosize();
			this.getMainPane().getChildren().add(this.getBubbleGridChart());
	}

}
