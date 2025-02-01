package animatedCharts.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.RevenueData;
import animatedCharts.view.BarView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

public class BarVC {
	 private BarView view;
	 private Model data;
	 private String barChartTitle = "";
	 private int index = 0; 
	 //private TxnData dataSet = null;
	 
	 
	 public BarVC(Model data) {
		 this.data = data;	
		 getTxnData();
		 setChartTitle();
		 this.view = new BarView(this.data,this.barChartTitle);
		 initialize();
		 set();
		 //this.index++;
		 this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		 //randomAnimation(this.view.getChart());
		 this.setupAnimation(this.view.getChart(),this.data.getTxData().size());
		 
	 }
	 
	 public void btnCancel_Click() 
	 {
	 	this.data.getPrimaryStage().close();
	 }
	 
	 public void show(){
	      this.view.show(this.data.getPrimaryStage(),this.view.getScene());
	   }
	   
	 public BarView getView() {
		return this.view;
	 }
	 
	 public void setChartTitle() {
		 if (this.data.getTxData().isEmpty()) {
			 this.barChartTitle = Constants.BAR_CHART_TITLE;
		 } else {
			 int len = this.data.getTxData().size();
			 RevenueData first = this.data.getTxData().get(0);
			 RevenueData last = this.data.getTxData().get(len-1);
			 this.barChartTitle = Constants.BAR_CHART_TITLE + ": " + first.getYear() + " - " + last.getYear() ;
		 }
	 }
	 
	 public void getTxnData() {
		 try {
			 List<RevenueData> beans = new CsvToBeanBuilder<RevenueData>(new FileReader(Constants.CSV_BAR_CHART))
				           .withType(RevenueData.class)
				           .build()
				           .parse();
			this.data.setTxData(beans);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				e.printStackTrace();
			}
	 }

	public void initialize() {
		for (RevenueData tx: this.data.getTxData()) {
			XYChart.Series<Number,String> dataSeries = new XYChart.Series<Number,String>();
	        //dataSeries1.setName("2014");
			int i = this.data.getTxData().indexOf(tx) + 1; 
			dataSeries.setName(String.valueOf(i ));
			dataSeries.getData().add(new XYChart.Data< Number,String>(tx.getYearlyRevenue(),String.valueOf(tx.getYear())));
			this.view.getChart().getData().add(dataSeries);
		}
	}
	
	public void set() {
		int len = this.data.getTxData().size();
		if (index >= len) {
			this.index = 0;
		} 
		RevenueData tx = this.data.getTxData().get(this.index);
		XYChart.Series<Number, String> dataSeries = new XYChart.Series<Number, String>();
		
		dataSeries.setName(String.valueOf(index+1 ));
		dataSeries.getData().add(new XYChart.Data<Number, String>(tx.getYearlyRevenue(), String.valueOf(tx.getYear())));
		this.view.getChart().getData().clear();
		this.view.getChart().getData().add(dataSeries);
		this.index++;
	}	
	
	public void randomAnimation(BarChart<Number,String> chart) {
		  // Changing random data after every 1 second.
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(
		new KeyFrame(Duration.millis(1000), 
		    new EventHandler<ActionEvent>() {
		        @Override public void handle(ActionEvent actionEvent) {
		            for (XYChart.Series<Number, String> series : chart.getData() ) {
		                for (XYChart.Data<Number, String> data : series.getData()) {
		                    data.setXValue(Math.random() * 1000);
		                }
		            }
		        }
		     }
		));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.setAutoReverse(true);
		tl.play();
	}
	
	/*public void randomAnimation(BarChart<String,Number> chart) {
		  // Changing random data after every 1 second.
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(
		new KeyFrame(Duration.millis(1000), 
		    new EventHandler<ActionEvent>() {
		        @Override public void handle(ActionEvent actionEvent) {
		            for (XYChart.Series<String, Number > series : chart.getData() ) {
		                for (XYChart.Data<String,Number> data : series.getData()) {
		                    data.setYValue(Math.random() * 1000);
		                }
		            }
		        }
		     }
		));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.setAutoReverse(true);
		tl.play();
	}*/
	
	public void setupAnimation(BarChart<Number,String> chart,int len) {
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
