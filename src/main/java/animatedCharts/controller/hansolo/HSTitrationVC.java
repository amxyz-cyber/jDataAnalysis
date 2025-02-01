package animatedCharts.controller.hansolo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

import animatedCharts.controller.AbstractChartVC;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.view.hansolo.HSTitrationView;
import blue.macroLab.mycmd.phcalc.calc.MyTitrationData;
import blue.macroLab.mycmd.phcalc.model.Data;
import eu.hansolo.fx.charts.data.XYChartItem;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class HSTitrationVC  extends AbstractChartVC {
	 private HSTitrationView view;
	 private String chartTitle = "";
	 private String xLabel = "";
	 private String yLabel = "";
	 private StringBuilder t1 ;
	 private double[][] sample1; 
	 private DoubleProperty ep1;
	 private DoubleProperty maxX;
	 private DoubleProperty maxY;
	 private MyTitrationData titration_ex;
	 private Map<MyTitrationData,Data> m;

	public HSTitrationVC(Model data, HSTitrationView v) {
		super(data, v);
		this.view = v; 
		this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		set_properties();
	}
	
	public void setupTest1() {
		 this.m = new LinkedHashMap();
		 this.t1 = new StringBuilder();
		 this.ep1 = new  SimpleDoubleProperty();
		 this.maxX = new  SimpleDoubleProperty(0);
		 this.maxY = new  SimpleDoubleProperty(0);
		 setData(this.titration_ex);
		 getMaxX(maxX,XCol);
		 getMaxX(maxY,YCol);
		 this.setChartTitle();
		 iter();
		 //randomAnimation(this.view.getChart());
		 System.out.println("size:"+m.size());
		 setupChart();
	 }
	
	 public void set_properties() {
		 File f0 = new File(Constants.PHCALC_PROPERTIES);
		 if (f0.exists()) {
			 this.setupTest1();
		 }else {
			 select_file();
		 }
	 }
	   
	 public void select_file() {
		 File f0 = new File(Constants.PHCALC_PROPERTIES);
		 File fex = new File(Constants.PHCALC_PROPERTIES_EX);
		 File f1 = new File(Constants.PHCALC_PROPERTIES_1);
		 File f2 =new File(Constants.PHCALC_PROPERTIES_2);
		 File f3 = new File(Constants.PHCALC_PROPERTIES_3);
		 File[] files = {fex,f1,f2,f3};
		 boolean hasFound = false;
		 
		 for (int i = 0; i < files.length ;i++) {
			 File f = files[i];
			 if (f.exists()) {
				 try {
					Files.copy( f.toPath(), f0.toPath() );
					hasFound = true;
					break;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		 }
		 
		 if (hasFound) {
			 this.setupTest1();
		 } else {
			 System.out.println("No 'phcalc.properties' file found!");
		 }
		 
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
	
	public void getMaxX(DoubleProperty myMax,int col) {
		 for (Map.Entry<MyTitrationData,Data> pair: m.entrySet()) {
			 MyTitrationData titration = pair.getKey();
			 double [][] sample = titration.toData();
			 if (sample != null) {
					for (int i = 0; i < sample.length; i++) {
						double x = sample[i][col];
						if (x > myMax.getValue()) {
							myMax.setValue(x);
						}
					}
			 }
		 }
		 double i = Math.ceil(myMax.getValue()/5.0) * 5;
		 System.out.println("max X:"+i);
		 myMax.setValue(i);
	 }
	
	
	
	 public void setChartTitle() {
		 this.view.getRow().setText(chartTitle);
	 }
	 
	 public void iter() {
		 for (Map.Entry<MyTitrationData,Data> pair: m.entrySet()) {
			 MyTitrationData titration = pair.getKey();
			 Data dat = pair.getValue();
			 String t = dat.getCalcSettings().getSampleName();
			 double [][] sample = titration.toData();
			 addData(t,sample);
			 additionalData(Constants.LABEL_DEV1,sample,YCOL2,Constants.COLOR_MAGENTA,Constants.COLOR_VIOLETTE);
			 additionalData(Constants.LABEL_DEV2,sample,YCOL3,Constants.COLOR_GREEN,Constants.COLOR_YELLOW);
		 }
	 }
	 
	 public void addData(String curveTitle,double[][] sample) {
		 	if (sample != null) {
				for (int i = 0; i < sample.length; i++) {
					double x = sample[i][XCol];
					double y = sample[i][YCol];
					XYChartItem p = new XYChartItem();
					p.setX(x);
					p.setY(y);
					//p.setTooltipText(curveTitle);
					this.getData().getSample().add(p);
			}
				createSeries(curveTitle,Constants.COLOR_BLUE,Constants.COLOR_BLUE_2);
				this.getData().getSample().clear();
			}
	}
	 
	 public void additionalData(String curveTitle,double[][] sample,int ycol,String scolor,String scolor2) {
		 	if (sample != null) {
				for (int i = 0; i < sample.length; i++) {
					double x = sample[i][XCol];
					double y = sample[i][ycol];
					XYChartItem p = new XYChartItem();
					p.setX(x);
					p.setY(y);
					//p.setTooltipText(curveTitle);
					this.getData().getSample().add(p);
			}
				createSeries(curveTitle,scolor,scolor2);
				this.getData().getSample().clear();
			}
	}
	 
	public void setupChart() {
		this.view.setAxis(0, this.maxX.get() , 0, 14,xLabel,yLabel);
		this.view.setupGrid(Constants.GRID_COLOR);
		this.view.setupSeries(this.getSeries());
		this.getSeries().clear();
		this.view.setLineChart();
		this.view.showChart();
	}

}
