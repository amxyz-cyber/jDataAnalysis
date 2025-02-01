package animatedCharts.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import animatedCharts.model.BalancesData;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.RevenueData;
import animatedCharts.view.AreaView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;
import java.text.DateFormatSymbols;

public class AreaVC {
	 private AreaView view;
	 private Model data;
	 private int index = 0; 
	 private static DateFormatSymbols dfs = new DateFormatSymbols();
	 private static String[] months = dfs.getMonths();
	 private int iMonths = 12;
	 
	 
	 public AreaVC(Model data) {
		 this.data = data;	
		 this.getBalances();
		 this.view = new AreaView(this.data,Constants.AREA_CHART_TITLE);
		 //initialize();
		 set();
		 this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		 //randomAnimation(this.view.getChart());
		 this.setupAnimation(this.view.getChart(),this.iMonths);
		 
	 }
	 
	 public void btnCancel_Click() 
	 {
	 	this.data.getPrimaryStage().close();
	 }
	 
	 public void show(){
	      this.view.show(this.data.getPrimaryStage(),this.view.getScene());
	   }
	   
	 public AreaView getView() {
		return this.view;
	 }
	 
	
	 public void getBalances() {
		 try {
			 List<BalancesData> beans = new CsvToBeanBuilder<BalancesData>(new FileReader(Constants.CSV_AREA_CHART))
				           .withType(BalancesData.class)
				           .build()
				           .parse();
			this.data.setBalances(beans);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				e.printStackTrace();
			}
	 }

	public void initialize() {
		System.out.println( "months:"+ this.iMonths );
		for (int i = 0; i < this.iMonths ; i++ ) {
			XYChart.Series<Number, Number> dataSeries = new XYChart.Series<Number, Number>();
			String month = this.months[i];
			dataSeries.setName(month);
			int g = 1;
			for (BalancesData objBalance: this.data.getBalances() ) {
				
				dataSeries.getData().add(new XYChart.Data<Number, Number>(g,objBalance.getValue(i) ));
				g++;
			}
			this.view.getChart().getData().add(dataSeries);
		}
	}
	
	public void set() {
		if (index >= this.iMonths) {
			this.index = 0;
			this.view.getChart().getData().clear();
		} 
		XYChart.Series<Number, Number> dataSeries = new XYChart.Series<Number, Number>();
		String month = this.months[this.index];
		dataSeries.setName(month);
		int g = 1;
		for (BalancesData objBalance: this.data.getBalances() ) {
			
			dataSeries.getData().add(new XYChart.Data<Number, Number>(g,objBalance.getValue(this.index) ));
			g++;
		}
		this.view.getChart().getData().add(dataSeries);
		this.index++;
	}	
	
	public void randomAnimation(AreaChart<Number,Number> chart) {
		  // Changing random data after every 1 second.
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(
		new KeyFrame(Duration.millis(1000), 
		    new EventHandler<ActionEvent>() {
		        @Override public void handle(ActionEvent actionEvent) {
		        	 for (XYChart.Series<Number, Number> series : chart.getData()) {
		                   for (XYChart.Data<Number, Number> data : series.getData()) {
		                       Number yValue = data.getYValue();
		                       Number randomValue = yValue.doubleValue() * (0.5 + Math.random());
		                       data.setYValue(randomValue);
		                   }
		               }
		        }
		     }
		));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.setAutoReverse(true);
		tl.play();
	}
	
	public void setupAnimation(AreaChart<Number,Number> chart,int len) {
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(
		new KeyFrame(Duration.millis(2000), 
		    new EventHandler<ActionEvent>() {
		        @Override public void handle(ActionEvent actionEvent) {
		        	set();
		        }
		     }
		));
		tl.setCycleCount((len-1)*2);
		tl.setAutoReverse(true);
		tl.play();
	}
	
}
