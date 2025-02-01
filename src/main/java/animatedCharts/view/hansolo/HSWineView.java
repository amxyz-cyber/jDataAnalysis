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
import javafx.scene.layout.VBox;

public class HSWineView  extends AbstractChartView  {
	private XYChart<XYChartItem> chart;
	private XYPane chartPane;
	private boolean isSetup1;

	public HSWineView(Model data, String title,boolean b) {
		super(data,title,Constants.CSS_SCRIPT_2);
		this.setSceneSize();
		this.isSetup1 = b;
		this.setLayout();
		setupHeadline();
		this.createButtons();
		this.createWindow();
		this.setChartWindow(Pos.TOP_LEFT);
		auxWindow();
		this.addToGrid();
	}
	
	private void auxWindow() {
		this.getMainPane().getChildren().addAll(this.getRow());
		this.getMainPane().getChildren().add(this.getChartPane());
	}
	
	public XYChart getChart() {
		return chart;
	}

	public void setupChart() {
		if(isSetup1) {
			this.chart = new XYChart<>(chartPane,this.getChartGrid(), this.getyAxis(),this.getxAxis());
			chart.setScaleX(1.1);
			chart.setScaleY(1.1);
		} else {
			this.getMatrixHeatMap().setPrefSize(this.getSceneWidth()*0.5, this.getSceneWidth()*0.5);
		}
	}
	
	
	public void setSeries(List<XYSeries> l) {
		this.chartPane = new XYPane(l);
	}
	
	public void showChart(int rows) {
		if(isSetup1) {
			chart.autosize();
			this.getMainPane().getChildren().addAll(this.chart,this.getLegend());
		} else {
			//getMatrixHeatMap().autosize();
			VBox colorbar = createColorBar(rows);
			//this.getMainPane().getChildren().addAll(this.getMatrixHeatMap());
			this.getChartPane().getChildren().addAll(this.getMatrixHeatMap(),colorbar);
			//this.getMatrixHeatMap().getMatrix().drawMatrix();
			
		}
		//this.getChartPane().getChildren().addAll(this.chart,this.getLegend());
	}
	

	public XYPane getLineChartPane() {
		return chartPane;
	}


}
