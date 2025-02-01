package animatedCharts.view.hansolo;

import java.util.List;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.Server;
import animatedCharts.view.AbstractChartView;

public class HSServerTestView  extends AbstractChartView  {

	public HSServerTestView(Model data, String s) {
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
	
	public void setupChart(List<Server> list) {
			//this.getMatrixHeatMap().setPrefSize(this.getSceneWidth()*0.5, this.getSceneWidth()*0.5);
		this.parallelCoordinatesStyle(list);
		this.getParallelCoordinatesChart().setScaleY(1.05);
		this.getParallelCoordinatesChart().setScaleX(1.05);
	}
	
	public void showChart() {
			this.getParallelCoordinatesChart().autosize();
			this.getMainPane().getChildren().add(this.getParallelCoordinatesChart());
	}
}
