package animatedCharts.view;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainView extends AbstractChartView {
	private PieChart chart;
	private Rectangle rectangle;
	private Label percentLabel;

	
	public MainView(Model data, String s) {
		super(data,s,Constants.CSS_SCRIPT);
		this.rectangle = new Rectangle();
		this.setSceneSize();
		this.setLayout();
		setupPieChart();
		createButtons();
		createWindow();
		this.auxWindow();
		this.addToGrid();
	}
	
	private void setupPieChart() {
		this.chart = new PieChart();
		this.chart.setData(this.getData().getPcData());
		this.chart.setTitle(this.getChartTitle());
		this.chart.setLegendSide(Side.RIGHT);
		this.percentLabel = new Label();
		this.percentLabel.setTextFill(Color.DARKOLIVEGREEN);
		this.percentLabel.setStyle("-fx-font: 20 arial;");
		
	}
	
	private void auxWindow(){
		 // insert pie chart + label into a stackpane
		 this.getMainPane().getChildren().addAll(this.chart,this.percentLabel);
	}
	
	public PieChart getChart() {
		return chart;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public Label getPercentLabel() {
		return percentLabel;
	}

}
