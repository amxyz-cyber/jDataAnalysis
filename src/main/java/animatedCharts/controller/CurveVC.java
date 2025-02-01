package animatedCharts.controller;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.view.CurveView;
import blue.macroLab.mycmd.phcalc.calc.MyCalc;
import blue.macroLab.mycmd.phcalc.calc.MyTitration;
import blue.macroLab.mycmd.phcalc.calc.MyTitrationData;
import blue.macroLab.mycmd.phcalc.model.Data;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

public class CurveVC {
	 private CurveView view;
	 private Model data;
	 private String chartTitle = "";
	 private String xLabel = "";
	 private String yLabel = "";
	 private StringBuilder t1 ;
	 private StringBuilder t2 ;
	 private StringBuilder t3 ;
	 private IntegerProperty counter; 
	 public static final int XCol = 0;
	 public static final int YCol = 1;
	 private double[][] sample1; 
	 private double[][] sample2; 
	 private double[][] sample3; 
	 private DoubleProperty ep1;
	 private DoubleProperty ep2 ;
	 private DoubleProperty ep3 ;
	 private DoubleProperty maxX;
	 private MyTitrationData titration1;
	 private MyTitrationData titration2;
	 private MyTitrationData titration3;
	 private Map<MyTitrationData,Data> m;
	 
	 
	 public CurveVC(Model data) {
		 		 this.setupTest1(data);
	}
	 
	 public void setupTest1(Model d) {
		 this.data = d;
		 this.m = new LinkedHashMap();
		 this.t1 = new StringBuilder();
		 this.t2 = new StringBuilder();
		 this.t3 = new StringBuilder();
		 this.ep1 = new  SimpleDoubleProperty();
		 this.ep2 = new  SimpleDoubleProperty();
		 this.ep3 = new  SimpleDoubleProperty();
		 this.maxX = new  SimpleDoubleProperty(0);
		 this.counter = new  SimpleIntegerProperty(0);
		 initialize();
		 getX();
		 this.view = new CurveView(this.data,this.maxX.get());
		 this.setChartTitle();
		 iter();
		 this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		 ///randomAnimation(this.view.getChart());
		 System.out.println("size:"+m.size());
		 this.setupAnimation(this.view.getChart(),m.size());
	 }
	
	public void btnCancel_Click() 
	 {
	 	this.data.getPrimaryStage().close();
	 }
	 
	 public void show(){
	      this.view.show(this.data.getPrimaryStage(),this.view.getScene());
	   }
	 
	 public void setChartTitle() {
		 this.view.getChart().setTitle(chartTitle);
		 this.view.getXAxis().setLabel(xLabel);
		 this.view.getYAxis().setLabel(yLabel);
	 }
	 
	   
	 
	 public void setData(MyTitrationData myTitration) {
		 Data pHData = new Data("pH Calculator");
		 //curveTitle.append(pHData.getCalcSettings().getSampleName());
		 pHData.getMydata().setDataset();
		 //System.out.println("size of dataset:"+pHData.getMydata().getDataset().size());
		 myTitration = new MyTitrationData(pHData);
		 myTitration.calculateDerivatives();
		 //double[][] sample  =  myTitration.toData();
		 //pHData.setDat(sample);
		 
		 if (this.chartTitle.isBlank()) {
			 String s = pHData.getChartSettings().getChartTitle();
			 String[] arr = s.split("-");
			 this.chartTitle = arr[0];
		 }
		 
		 if (this.xLabel.isBlank()) {
			 	this.xLabel = pHData.getChartSettings().getXLabel();
		 	}
		 
		 if (this.yLabel.isBlank()) {
			 	this.yLabel = pHData.getChartSettings().getYLabel();
		 	}
		 
		
	    	//yValue.setValue(calc.getEquivalencePoint());
	     this.m.put(myTitration, pHData);	
		 //return sample;
	 }
	 
	 public void initialize() {
		 File f0 = new File(Constants.PHCALC_PROPERTIES);
		 File f1 = new File(Constants.PHCALC_PROPERTIES_1);
		 File f2 =new File(Constants.PHCALC_PROPERTIES_2);
		 File f3 = new File(Constants.PHCALC_PROPERTIES_3);
		 
		 for (int i = 0; i < 3;i++) {
			 	if (i == 0) {
			 		f1.renameTo(f0);
			 		setData(this.titration1);
			 		f0.renameTo(f1);
			 		
			 	} else if (i == 1) {
			 		f2.renameTo(f0);
			 		setData(this.titration2);
			 		f0.renameTo(f2);
			 	} else {
			 		f3.renameTo(f0);
			 		setData(this.titration3);
			 		f0.renameTo(f3);
			 	}
		 }
		 
	 }
		 
	 public void iter() {
		 for (Map.Entry<MyTitrationData,Data> pair: m.entrySet()) {
			 MyTitrationData titration = pair.getKey();
			 Data dat = pair.getValue();
			 String t = dat.getCalcSettings().getSampleName();
			 double [][] sample = titration.toData();
			 addData(t,sample);
		 }
	 }
	 
	 public void getX() {
		 for (Map.Entry<MyTitrationData,Data> pair: m.entrySet()) {
			 MyTitrationData titration = pair.getKey();
			 double [][] sample = titration.toData();
			 if (sample != null) {
					for (int i = 0; i < sample.length; i++) {
						double x = sample[i][XCol];
						if (x > this.maxX.getValue()) {
							this.maxX.setValue(x);
						}
					}
			 }
		 }
		 double i = Math.ceil(this.maxX.getValue()/5.0) * 5;
		 System.out.println("max X:"+i);
		 this.maxX.setValue(i);
	 }
	 
	 
	 
	 public MyTitration setPoints(MyTitration current,int len, MyTitrationData key ) {
		 int i = key.getMyDataPoints().indexOf(current);
		 System.out.println("index current point:"+i);
		 MyTitration next = null;
		 int g = i+1;
		 if (g < len) {
			 //previous = new MyTitration(current.getPoint(),current.getFstDrv(),current.getSndDrv());
			 next = key.getMyDataPoints().get(g);
			 if (next != null) {
				 System.out.println("next pont is not null:"+g);
			 }
			 //next = new MyTitration(row_next.getPoint(),row_next.getFstDrv(),row_next.getSndDrv());
		 }
		 return next;
	 }
	 
	 
	public void addData(String curveTitle,double[][] sample) {
			XYChart.Series<Number, Number> dataSeries = new XYChart.Series<Number, Number>();
	        //dataSeries1.setName("2014");
			dataSeries.setName(curveTitle);
			if (sample != null) {
				for (int i = 0; i < sample.length; i++) {
					double x = sample[i][XCol];
					double y = sample[i][YCol];
					dataSeries.getData().add(new XYChart.Data(x,y)); 
			}
				this.view.getChart().getData().add(dataSeries);
			}
				
	}
	
	public void addData(String curveTitle) {
		XYChart.Series<Number, Number> dataSeries = new XYChart.Series<Number, Number>();
		dataSeries.setName(curveTitle);
			this.view.getChart().getData().add(dataSeries);
	}
	
	public void inc() {
		this.counter.setValue(this.getCounter()+1);
	}
	
	public int getCounter() {
		return this.counter.get();
	}
	
	public void setCounter(int value) {
		this.counter.setValue(value);
	}
	

	public void set() {
		
		int len = m.size();
		if (this.getCounter() > len) {
			this.setCounter(0);
		} 
		Set<MyTitrationData> keySet = m.keySet();
		MyTitrationData[] keyArray = keySet.toArray(new MyTitrationData[keySet.size()]);
		MyTitrationData key = keyArray[this.getCounter()];
		Data dat = m.get(key);
		MyCalc calc = new MyCalc(key,dat);
	    calc.setLines();
	    calc.calcEP();
	    calc.setConcentrationAfterDilution();
	    calc.setConcentrationBeforeDilution();
	    calc.convertConcentration();
	    calc.getMass();
		XYChart.Series<Number, Number> dataSeries = new XYChart.Series<Number, Number>();
		String labelEP = "Equivalence Point("+dat.getCalcSettings().getSampleName()+")";
		String labelPH = "pH("+dat.getCalcSettings().getSampleName()+")";
		dataSeries.setName(labelPH);
		double x = 0;
		double x2 = Math.ceil(this.maxX.getValue());
		dataSeries.getData().add(new XYChart.Data(x,calc.getPH() ));
		dataSeries.getData().add(new XYChart.Data(x2,calc.getPH()));
		this.view.getChart().getData().add(dataSeries);
		addVerticalLine(calc.getEquivalencePoint(),labelEP);
		System.out.println("counter:"+this.getCounter());
		this.inc();
		System.out.println("counter-inc:"+this.getCounter());
	}	
	
	public void addVerticalLine(double x,String title) {
		XYChart.Series<Number, Number> dataSeries = new XYChart.Series<Number, Number>();
		dataSeries.setName(title);
		for (int i = 0; i < 15; i++) {
			dataSeries.getData().add(new XYChart.Data(x,i));
		}
		this.view.getChart().getData().add(dataSeries);
	}
	
	public void randomAnimation(LineChart<Number,Number> chart,int cycles,double xVal, double yVal) {
		  // Changing random data after every 1 second.
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(
		new KeyFrame(Duration.millis(1000), 
		    new EventHandler<ActionEvent>() {
		        @Override public void handle(ActionEvent actionEvent) {
		            XYChart.Series<Number, Number> series = chart.getData().get(0);
		            series.getData().add(new XYChart.Data(xVal,yVal));
		            }
		        }
		));
		tl.setCycleCount(cycles*2);
		tl.setAutoReverse(true);
		tl.play();
	}
	
	public void setupAnimation(LineChart<Number,Number> chart,int len) {
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(
		new KeyFrame(Duration.millis(2000), 
		    new EventHandler<ActionEvent>() {
		        @Override public void handle(ActionEvent actionEvent) {
		        	set();
		        }
		     }
		));
		tl.setCycleCount(len*2);
		//tl.setCycleCount((len-1)*2);
		tl.setAutoReverse(true);
		tl.play();
	}
	
}
