package animatedCharts.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.WineData;
import animatedCharts.view.ScatterView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

public class ScatterVC {
	 private ScatterView view;
	 private Model data;
	 private int index = 0; 
	 private static String[] cols;
	 private int numColumns = 12;
	 
	 
	 public ScatterVC(Model data) {
		 this.data = data;
		 this.cols = new String[this.numColumns];
		 this.addHeaders();
		 this.getWineData();
		 this.view = new ScatterView(this.data,Constants.SCATTER_CHART_TITLE);
		 //initialize();
		 set();
		 this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		 //randomAnimation(this.view.getChart());
		 this.setupAnimation(this.view.getChart(),this.numColumns);
		 
	 }
	 
	 public void addHeaders() {
		 int i = 0;
		 this.cols[i] = Constants.FIXED_ACID;
		 i++;
		 this.cols[i] = Constants.VOL_ACID;
		 i++;
		 this.cols[i] = Constants.CITRIC_ACID;
		 i++;
		 this.cols[i] = Constants.RESID_SUGAR;
		 i++;
		 this.cols[i] = Constants.FREE_SO2;
		 i++;
		 this.cols[i] = Constants.TOTAL_SO2;
		 i++;
		 this.cols[i] = Constants.DENSITY;
		 i++;
		 this.cols[i] = Constants.PH;
		 i++;
		 this.cols[i] = Constants.SO4;
		 i++;
		 this.cols[i] = Constants.ALCOHOL;
		 i++;
		 this.cols[i] = Constants.QUALITY;
		 i++;
		 this.cols[i] = Constants.CHLORIDES;
	 }
	 
	 public void btnCancel_Click() 
	 {
	 	this.data.getPrimaryStage().close();
	 }
	 
	 public void show(){
	      this.view.show(this.data.getPrimaryStage(),this.view.getScene());
	   }
	   
	 public void getWineData() {
		 try {
			 List<WineData> beans = new CsvToBeanBuilder<WineData>(new FileReader(Constants.CSV_SCATTER_CHART))
				           .withType(WineData.class)
				           .build()
				           .parse();
			this.data.setWineData(beans);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				e.printStackTrace();
			}
	 }

	public void initialize() {
		for (int i = 0; i < this.numColumns ; i++ ) {
			XYChart.Series<Number, Number> dataSeries = new XYChart.Series<Number, Number>();
			String xCol = this.cols[i];
			if (!xCol.equals(Constants.QUALITY)) {
				dataSeries.setName(xCol);
				for (WineData obj: this.data.getWineData() ) {
					//dataSeries.getData().add(new XYChart.Data<Number, Number>(obj.getValue(xCol),obj.getdQty()));
					dataSeries.getData().add(new XYChart.Data<Number, Number>(obj.getdQty(),obj.getValue(xCol)));
				}
				this.view.getChart().getData().add(dataSeries);
			}
			
		}
	}
	
	public void set() {
		if (index >= this.numColumns) {
			this.index = 0;
			this.view.getChart().getData().clear();
		} 
		XYChart.Series<Number, Number> dataSeries = new XYChart.Series<Number, Number>();
		String xCol = this.cols[index];
		if (!xCol.equals(Constants.QUALITY)) {
			dataSeries.setName(xCol);
			for (WineData obj: this.data.getWineData() ) {
				dataSeries.getData().add(new XYChart.Data<Number, Number>(obj.getdQty(),obj.getValue(xCol)));
			}
			this.view.getChart().getData().add(dataSeries);
		}
		this.index++;
	}	
	
	/*public void randomAnimation(AreaChart<Number,Number> chart) {
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
	}*/
	
	public void setupAnimation(ScatterChart<Number,Number> chart,int len) {
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
