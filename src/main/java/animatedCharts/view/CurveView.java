package animatedCharts.view;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class CurveView extends AbstractChartView {
	private LineChart<Number,Number>  chart;
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	private String chartTitle;
	private String xLabel;
	private String yLabel;
	private Label label;
	private Label title;
	private Label notify;
	private double xMAX;

	
	public CurveView(Model data, String s,String lX, String lY) {
		super(data,s,Constants.CSS_SCRIPT);
		this.xLabel = lX;
		this.yLabel = lY;
		this.setSceneSize();
		this.setLayout();
		setupLineChart();
		createButtons();
		createWindow();
		this.addToGrid();
	}
	
	public void setupTest1(Model data, double d) {
		this.xMAX =  d;
		this.setSceneSize();
		this.setLayout();
		setupLineChart();
		createButtons();
		createWindow();
		auxWindow();
		this.addToGrid();
	}
	

	public CurveView(Model data, double d ) {
		super(data,"",Constants.CSS_SCRIPT);
		setupTest1(data, d);
	}
	
	
	public void setupTitles() {
		this.label = new Label(Constants.SUB_TITLE);
		this.title = new Label(Constants.APP_TITLE);
		this.notify = new Label(Constants.DEMO);
		this.title.prefWidth(this.getSceneWidth()/2);
		this.label.prefWidth(this.getSceneWidth()/4);
		this.notify.prefWidth(this.getSceneWidth()/5);
		this.label.setTextFill(Color.DARKOLIVEGREEN);
		this.label.setStyle("-fx-font: 14 Frutiger;");
		this.title.setTextFill(Color.DARKOLIVEGREEN);
		this.title.setStyle("-fx-font: 18 Frutiger;");
		this.notify.setTextFill(Color.DARKRED);
		this.notify.setStyle("-fx-font: 14 Frutiger;");
	}
	
	private void setupLineChart() {
		xAxis = new NumberAxis(0,this.xMAX,0.25);
	    //xAxis.setLabel(this.xLabel.toUpperCase());
		
	    yAxis = new NumberAxis(0,14,1);
	    //yAxis.setLabel(this.yLabel.toUpperCase());
		this.chart = new LineChart<Number, Number>(xAxis, yAxis);
		this.chart.setTitle(chartTitle);
		this.chart.setLegendSide(Side.RIGHT);
		this.chart.setHorizontalGridLinesVisible(false);
		this.chart.setVerticalGridLinesVisible(false);
		
		/*yAxis.setTickMarkVisible(true);
		xAxis.setTickMarkVisible(true);
		yAxis.setTickLabelFill(Color.DARKOLIVEGREEN);
		yAxis.setTickLabelFont( new Font("Frutiger", 18));
		xAxis.setTickLabelFill(Color.DARKOLIVEGREEN);
		xAxis.setTickLabelFont( new Font("Frutiger", 18));*/
		xAxis.setTickLabelRotation(30);
		//this.percentLabel = new Label();
		//this.percentLabel.setTextFill(Color.DARKOLIVEGREEN);
		//this.percentLabel.setStyle("-fx-font: 20 arial;");
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(2);
		shadow.setColor(Color.GREY);
		this.chart.setEffect(shadow);
		//this.chart.autosize();
		this.chart.setCreateSymbols(false);
		
	}
	
	// test 1
	private void auxWindow(){
		 this.getMainPane().getChildren().addAll(this.chart);
	}
	
	public LineChart getChart() {
		return chart;
	}

	public NumberAxis getXAxis() {
		return this.xAxis;
	}

	public NumberAxis getYAxis() {
		return this.yAxis;
	}

	
	public Label getNotify() {
		return notify;
	}
	
	

}
