package animatedCharts.controller.hansolo;

import java.util.List;
import java.util.Random;

import animatedCharts.controller.AbstractChartVC;
import animatedCharts.model.Colors;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.WineData;
import animatedCharts.view.hansolo.HSWineView;
import eu.hansolo.fx.charts.Symbol;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;

public class HSWineVC  extends AbstractChartVC  {
	private HSWineView view;
	private DoubleProperty maxX;
	private DoubleProperty maxY;
	private static String[] cols;
	private int numColumns = 12;
	private int numRows = 0;
	private double[] xValues;
	private double[] yValues;
	private int matrix = 50;
	private int step = 0;

	public HSWineVC(Model data, HSWineView v, boolean isSetup1) {
		super(data, v);
		this.view = v; 
		this.maxX = new  SimpleDoubleProperty(0);
		this.maxY = new  SimpleDoubleProperty(0);
		this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		if (isSetup1) {
			setupTest1();
		} else {
			setupTest2();
		}
	}

private void setupTest1() {
		this.cols = new String[this.numColumns];
		this.getWineData(Constants.CSV_SCATTER_CHART);
		setMySample(this.getData().getWineData().size(),numColumns);
		addHeaders();
		setData();
		getMaxX(this.maxY);
		setMySample(this.getData().getWineData().size(),1);
		setXvalues();
		getMaxX(this.maxX);
		setupChart();
	}

private void setupTest2() {
	this.cols = new String[this.numColumns];
	this.getWineData(Constants.CSV_SCATTER_CHART);
	addHeaders();
	this.numRows = this.getData().getWineData().size();
	this.step =  this.numRows / this.matrix;
	setSample();
	setData(this.getData().getWineSamples());
	setupChart2();
}
	
	private void addHeaders() {
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
	
	private void setData() {
		int iSize = this.getData().getColors().size();
		int index = 0;
		int g = 0;
		for (int i = 0; i < this.numColumns ; i++ ) {
			String xCol = this.cols[i];
			if (!xCol.equals(Constants.QUALITY)) {
				for (WineData obj: this.getData().getWineData() ) {
					addPoint(obj.getdQty(),obj.getValue(xCol) );
					addSampleData(g, i, obj.getValue(xCol));
					g++;
				}
				if (index >=  iSize  ) {
					index = 0;
				}
				Colors cx = this.getData().getColors().get(index);
				Color lColor = cx.getLineColor().get();
				Color symbolColor = cx.getAreaColor().get();
				Symbol symbol = cx.getSymbol();
				createSeries(lColor,lColor,symbol,xCol.toUpperCase());
				g = 0;
				index++;
		}
	  }
	}
	
	private void setData( List<WineData> sample) {
		System.out.println("size:"+sample.size());
		this.xValues = new double [sample.size()];
		this.yValues = new double [sample.size()];
		System.out.println("size-x:"+xValues.length);
		System.out.println("size-y:"+yValues.length);
		for (WineData obj: sample ) {
			int i = sample.indexOf(obj);
			this.xValues[i] = obj.getdAlc(); 
			this.yValues[i] = obj.getdPH(); 
		}
		
		double pHrelevance = 0.7;
		double alcRelevance = 0.3;
		
		for (int g = 0; g < xValues.length; g++) {
			WineData objPrime = sample.get(g);
			double qty = objPrime.getdQty();
			for (int j = 0; j < yValues.length; j++) {
	        	WineData obj = sample.get(j);
	        	double qtyJ = obj.getdQty();
	        	if (j == 0) {
	        		addPoint(g, j,qty);
			        	}else {
			        		//zValues[g][j] = qtyJ;
			        		//double dAlc = alcRelevance * qty;
			        		//double dPH = pHrelevance * qtyJ;
			        		//addPoint(g, j,dAlc+dPH);
			        		double dAlc = alcRelevance * xValues[g];
			        		double dPH = pHrelevance * yValues[j];
			        		double qtyGJ = (dAlc + dPH)/100*qtyJ;
			        		if (qtyGJ < 1) {
			        			System.out.println("("+g+","+j+")"+": "+qtyGJ*20);
			        			addPoint(g, j,qtyGJ*20);
			        		} else {
			        			System.out.println("("+g+","+j+")"+": "+qtyGJ);
			        			addPoint(g, j,qtyGJ);
			        		}
			        		//addPoint(g, j,(qty+qtyJ)/2);
			        		//System.out.println(" xcol:"+qty + " - ycol:"+qtyJ+" - values:"+zValues[g][j]);
			        		//zValues[g][j] = Math.abs(qty-qtyJ)/7;
			        	}
			        }
		}
		this.createSeries(xValues.length, yValues.length);
	}
	
	private void setXvalues() {
				int i = 0;
				int g = 0;
				for (WineData obj: this.getData().getWineData() ) {
					addSampleData(i, g, obj.getdQty());
					i++;
				}
		}
	
	public void setSample() {
        Random random = new Random();
		for (int i = 0; i < this.matrix; i++) {
			int g = random.nextInt(this.numRows-1);
			WineData obj = this.getData().getWineData().get(g);
			System.out.println("ph:"+obj.getdPH() + " - index:"+i);
			this.getData().getWineSamples().add(obj);
		}
	}
	
	public void setupChart() {
		this.view.setAxis(0, this.maxX.get() , 0, this.maxY.get(),Constants.QUALITY,Constants.SCATTER_CHART_Y2);
		this.view.setupGrid(Constants.GRID_COLOR);
		this.view.setSeries(this.getSeries());
		this.getSeries().clear();
		this.view.setupChart();
		this.view.showChart(0);
	}
	
	public void setupChart2() {
		//this.view.setAxis(0, this.maxX.get() , 0, this.maxY.get(),Constants.QUALITY,Constants.SCATTER_CHART_Y2);
		//this.view.setupGrid(Constants.GRID_COLOR);
		//this.view.setSeries(this.getSeries());
		//this.getSeries().clear();
		this.view.setupChart();
		this.view.showChart(11);
	}
}
