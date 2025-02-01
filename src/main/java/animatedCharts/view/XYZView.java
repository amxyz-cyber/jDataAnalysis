package animatedCharts.view;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import cern.extjfx.chart.HeatMapChart;
import cern.extjfx.chart.HeatMapChart.ColorGradient;
import cern.extjfx.chart.NumericAxis;
import javafx.geometry.Side;

public class XYZView  extends AbstractChartView {
	private HeatMapChart<Number,Number>  chart;
	private String chartTitle;
	
	public XYZView(Model data, String s) {
		super(data,s,Constants.CSS_SCRIPT);
		this.setSceneSize();
		this.setLayout();
		setupHeatmap();
		createButtons();
		createWindow();
		this.auxWindow();
		this.addToGrid();
	}
		
	private void setupHeatmap() {
		//NumericAxis yAxis = new NumericAxis(3,3.5,0.1);
		NumericAxis yAxis = new NumericAxis(0,4,0.2);
	    yAxis.setLabel(Constants.PH.toUpperCase());
	    //yAxis.setAutoRanging(true);
	    //yAxis.setAutoRangeRounding(false);
	    
	    NumericAxis xAxis = new NumericAxis(9.4,11,0.2);
	    //NumericAxis xAxis = new NumericAxis();
	    xAxis.setLabel(Constants.ALCOHOL.toUpperCase() );
	    //xAxis.setAutoRangeRounding(true);
	    //xAxis.setAutoRanging(true);
	    
		this.chart = new HeatMapChart<Number, Number>(xAxis, yAxis);
		//this.chart.setData(this.data.getPcData());
		this.chart.setTitle(chartTitle);
		this.chart.setLegendSide(Side.RIGHT);
		//this.chart.setLegendVisible(true);
		this.chart.setHorizontalGridLinesVisible(true);
		this.chart.setVerticalGridLinesVisible(true);
		this.chart.setColorGradient(ColorGradient.RAINBOW);
		this.chart.setSmooth(true);
		
		//this.chart.getZAxis().setAutoRanging(true);
		
		//this.chart.setOpacity(0.75);
		
		//DropShadow shadow = new DropShadow();
		//shadow.setOffsetX(2);
		//shadow.setColor(Color.GREY);
		//this.chart.setEffect(shadow);
		//this.percentLabel = new Label();
		//this.percentLabel.setTextFill(Color.DARKOLIVEGREEN);
		//this.percentLabel.setStyle("-fx-font: 20 arial;");
		
	}
	
	private void auxWindow(){
		 this.getMainPane().getChildren().addAll(this.chart);
	}
	
	public HeatMapChart<Number,Number> getChart() {
		return chart;
	}

}
