package animatedCharts.controller.hansolo;

import animatedCharts.model.BikeSharingData;

import java.time.LocalDateTime;

import animatedCharts.controller.AbstractChartVC;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.view.hansolo.HSTimeView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;

public class HSTimeVC extends AbstractChartVC {
	private HSTimeView view;
	private DoubleProperty maxY;
	private int[] hits;

	public HSTimeVC(Model data, HSTimeView v) {
		super(data, v);
		this.view = v; 
		this.maxY = new  SimpleDoubleProperty(0);
		this.hits = new int[Constants.HOURS];
		this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		this.setupTest1();
	}
	
	private void setupTest1() {
		this.getBikeData(Constants.CSV_BIKE_SHARING);
		setData();
		getMaxX(this.maxY);
		setupChart();
	}

	private void setupChart() {
		this.view.setTimeAxis( 0,this.maxY.get(),Constants.CHART_BIKESHARING_X,Constants.CHART_BIKESHARING_Y);
		this.view.setupGrid(Constants.GRID_COLOR);
		this.view.setSeries(this.getSeries());
		//this.view.setSeries(Color.CRIMSON, MaterialDesignColors.RED_100.get(), getSeries());
		this.getSeries().clear();
		this.view.setupChart();
		this.view.showChart();
		
	}

	private void setData() {
		for (BikeSharingData o: this.getData().getBikeData()) {
			int hour = o.getHour();
			int numTrips = this.hits[hour];
			//if(numTrips > 0) {
				this.hits[hour] = ++numTrips;
			//}else {
			//	this.hits[hour] = 1;
			//}
	    }
		
		for (int i = 0; i < this.hits.length ; i++ ) {
			int ival = hits[i];
			LocalDateTime     x      = LocalDateTime.now().withHour(i);
			this.addPoint(x,ival);
			System.out.println("hour:"+i+" hit:"+ival);
		}
		
		this.createSeries("",Color.CRIMSON);
	}
	
	public void getMaxX(DoubleProperty myMax) {
		for (int i = 0; i < this.hits.length ; i++ ) {
				int ival = hits[i];
				if (ival > myMax.getValue()) {
					myMax.setValue(ival);
				}
			}
		double i = Math.ceil(myMax.getValue()/5.0) * 5;
		System.out.println("max X:"+i);
		myMax.setValue(i);
  }


}
