package animatedCharts.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormatSymbols;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import animatedCharts.model.BalancesData;
import animatedCharts.model.BikeSharingData;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.OsData;
import animatedCharts.model.ProcessData;
import animatedCharts.model.RevenueData;
import animatedCharts.model.TestData;
import animatedCharts.model.WineData;
import animatedCharts.view.AbstractView;
import javafx.beans.property.DoubleProperty;

public abstract class AbstractVC {
	 private AbstractView view;
	 private Model data;
	 public static DateFormatSymbols dfs = new DateFormatSymbols();
	 public static String[] MONTHS = dfs.getMonths();
	 public static final int XCol = 0;
	 public static final int YCol = 1;
	 public static final int YCOL2 = 2;
	 public static final int YCOL3 = 3;
	 private double[][] mySample;
	 private int dimensions = 0;
	 
	 public AbstractVC(Model data,AbstractView v) {
		 this.data = data;
		 this.view = v;
	 }
	 
	 public void btnCancel_Click() 
	 {
	 	this.data.getPrimaryStage().close();
	 }
	 
	 public void show(){
	      this.view.show(this.data.getPrimaryStage(),this.view.getScene());
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
	 
	 public void getOsData(String path) {
		 try {
			 List<OsData> beans = new CsvToBeanBuilder<OsData>(new FileReader(path))
				           .withType(OsData.class)
				           .build()
				           .parse();
			this.getData().setOsData(beans);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				e.printStackTrace();
			}
	 }
	 
	 public void getBikeData(String path) {
		 try {
			 List<BikeSharingData> beans = new CsvToBeanBuilder<BikeSharingData>(new FileReader(path))
				           .withType(BikeSharingData.class)
				           .build()
				           .parse();
			this.getData().setBikeData(beans);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				e.printStackTrace();
			}
	 }
	 
	 public void getRevenueData(String path) {
		 try {
			 List<RevenueData> beans = new CsvToBeanBuilder<RevenueData>(new FileReader(path))
				           .withType(RevenueData.class)
				           .build()
				           .parse();
			this.getData().setTxData(beans);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				e.printStackTrace();
			}

		    for (RevenueData o : this.getData().getTxData()) {
		        o.setCompany();
		        o.setDate();
		    }
	 }
	 
	 public void getWineData(String path) {
		 try {
			 List<WineData> beans = new CsvToBeanBuilder<WineData>(new FileReader(path))
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
	 
	 public void getProcessesData(String path) {
		 try {
			 List<ProcessData> beans = new CsvToBeanBuilder<ProcessData>(new FileReader(path))
				           .withType(ProcessData.class)
				           .build()
				           .parse();
			this.data.setProcesses(beans);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				e.printStackTrace();
			}
	 }
	 
	 public void getTestData(String path) {
		 try {
			 List<TestData> beans = new CsvToBeanBuilder<TestData>(new FileReader(path))
				           .withType(TestData.class)
				           .build()
				           .parse();
			this.data.setServerTests(beans);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				e.printStackTrace();
			}
	 }
	 
	 public void addSampleData(int row, int column, double value) {
		 mySample[row][column] = value;
	 }
	 
	 public void getMaxX(DoubleProperty myMax) {
	  int rows = mySample.length;
	  for (int i = 0; i < rows ; i++ ) {
			for (int g = 0; g < this.dimensions ; g++ ) {
			 	 double dval = mySample[i][g];
				 if (dval > myMax.getValue()) {
				  	 myMax.setValue(dval);
				 }
			}
		 }
			double i = Math.ceil(myMax.getValue()/5.0) * 5;
			System.out.println("max X:"+i);
			myMax.setValue(i);
	 }
	   
	 public AbstractView getView() {
		return this.view;
	 }

	public Model getData() {
		return data;
	}

	public double[][] getMySample() {
		return mySample;
	}

	public void setMySample(int rows, int dimensions) {
		this.dimensions = dimensions;
		this.mySample = new double[rows][dimensions];
	}

	public int getDimensions() {
		return dimensions;
	}
	
}
