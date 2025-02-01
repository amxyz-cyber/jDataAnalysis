package animatedCharts.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

import com.opencsv.bean.CsvToBeanBuilder;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.WineData;
import animatedCharts.view.ScatterView;
import animatedCharts.view.XYZView;
import cern.extjfx.chart.HeatMapChart;
import cern.extjfx.chart.HeatMapChart.DefaultData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.css.converter.SizeConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

public class XYZVC {
	 private XYZView view;
	 private Model data;
	 private int index = 0; 
	 private static String[] cols;
	 private Number[] xValues;
	 private Number[] yValues;
	 private double[][] zValues;
	 private int numColumns = 12;
	 private int numRows = 0;
	 private int matrix = 40;
	 private double sumAlc = 0;
	 private double sumPH = 0;
	 private double sumQty = 0;
	 private double sumQtyJ = 0;
	 private int step = 0;
	 private DefaultData<Number,Number> mydata;
	 
	 
	 public XYZVC(Model data) {
		 this.data = data;
		 this.cols = new String[this.numColumns];
		 this.addHeaders();
		 this.getWineData();
		 this.numRows = this.data.getWineData().size();
		 setSample();
		 this.step =  this.numRows / this.matrix;
		 this.view = new XYZView(this.data,Constants.HEAT_MAP_TITLE1);
		 
		 this.init();
		 //initialize(this.data.getWineData());
		 this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		 this.setupAnimation(this.view.getChart(),this.numRows);
		 
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
	public void init() {
		this.xValues = new Number [this.data.getWineData().size()];
		this.yValues = new Number [this.data.getWineData().size()];
		this.zValues = new double [xValues.length][yValues.length];
		for (WineData obj: this.data.getWineData() ) {
			int i = this.data.getWineData().indexOf(obj);
			this.xValues[i] = obj.getdAlc(); 
			this.yValues[i] = obj.getdPH(); 
		}
	}
	 

	/*public void initialize() {
		this.xValues = new Number [this.matrix];
		this.yValues = new Number [this.matrix];
		this.zValues = new double [xValues.length][yValues.length];
				int lastStep = 0;
				for (WineData obj: this.data.getWineData() ) {
					int auxStep = setXyValues( lastStep,  obj);
					if (auxStep > -1) {
						lastStep = auxStep;
					}
				}
				this.index = 0;
				lastStep = 0;
				
				for (int g = 0; g < this.numRows;  g++) {
					WineData objPrime = this.data.getWineData().get(g);
					double qty = objPrime.getdQty();
					int delta = g % step;
					
					if ( g > 0 && delta == 0 && lastStep < this.matrix) {
						this.index = 1;
						int lastStepJ = 0;
						for (int j = 0; j < this.numRows; j++) {
							if (index >= this.matrix ) {
								break;
							}
							int auxStep = setZvalues( lastStepJ,lastStep, j);
							if (auxStep > -1) {
								lastStepJ = auxStep;
							}
				        }
						this.sumQtyJ = 0;
						this.sumQty =0;
						lastStep++;
					}
					this.sumQty += qty;
			    }
		mydata = new DefaultData<Number,Number>(this.xValues,this.yValues,this.zValues);
		this.view.getChart().setData(mydata);
		this.index = 0;
		}*/
	
	
	
	public int setXyValues( int lastStep, WineData obj) {
		int i = this.data.getWineData().indexOf(obj);
		int delta = i % step;
		int stepIndex = -1;
		if (index == (this.matrix -1) ) {
			//System.out.println("highest index:"+i);
			//System.out.println("last step:"+lastStep);
			this.xValues[index] = sumAlc/(this.numRows-lastStep); 
			this.yValues[index] = sumPH/(this.numRows-lastStep);
			this.zValues[index][0] = this.sumQty / (this.numRows-lastStep);
			//System.out.println("Alc:"+this.xValues[index]);
			//System.out.println("pH:"+this.yValues[index]);
			//System.out.println("qty:"+this.zValues[index][0]);
			this.sumAlc = 0;
			this.sumPH = 0;
			this.sumQty = 0;
		} else if (i > 0 && delta == 0) {
			this.xValues[index] = sumAlc/step; 
			this.yValues[index] = sumPH/step;
			this.zValues[index][0] = this.sumQty / step;
			//System.out.println("Alc:"+this.xValues[index]);
			//System.out.println("pH:"+this.yValues[index]);
			//System.out.println("qty:"+this.zValues[index][0]);
			//System.out.println("highest index - second if:"+i);
			//System.out.println("index - second if:"+index);
			sumAlc = 0;
			sumPH =0;
			this.sumQty = 0;
			lastStep=i;
			this.index++;
			stepIndex = lastStep;
		} 
			sumAlc += obj.getdAlc(); 
			sumPH += obj.getdPH();
			this.sumQty += obj.getdQty();
			return stepIndex;
	}
	
	public int setZvalues( int lastStep,int xCol, int j) {
		WineData obj = this.data.getWineData().get(j);
		int delta = j % step;
		int stepIndex = -1;
		
		
		if (index == (this.matrix -1)  ) {
			//this.zValues[xCol][this.index] = ((sumQtyJ + sumQty)/2) /(this.numRows-lastStep); 
			this.zValues[xCol][this.index] = sumQtyJ /(this.numRows-lastStep); 
			System.out.println("index - x - z function - last element:"+xCol);
			System.out.println("qty - z function - last element:"+this.zValues[xCol][index]);
			} else if (index > 0 && delta == 0 && j > 0 ) {
			//System.out.println("index - x1 - z function:"+bdRounded.intValue());
			System.out.println("index - x - z function:"+xCol);
			//this.zValues[xCol][index] = ((sumQtyJ + sumQty)/2)/step; 
			this.zValues[xCol][index] = sumQtyJ/step; 
			System.out.println("qty - z function:"+this.zValues[xCol][index]);
			this.sumQtyJ = 0;
			lastStep=j;
			this.index++;
			stepIndex = lastStep;
		} 
		    sumQtyJ += obj.getdQty(); 
			return stepIndex;
	}
	
	public void setSample() {
        Random random = new Random();
		for (int i = 0; i < this.matrix; i++) {
			int g = random.nextInt(this.numRows-1);
			WineData obj = this.data.getWineData().get(g);
			System.out.println("ph:"+obj.getdPH() + " - index:"+i);
			this.data.getWineSamples().add(obj);
		}
	}
	
	public void initialize( List<WineData> sample) {
		System.out.println("size:"+sample.size());
		this.xValues = new Number [sample.size()];
		this.yValues = new Number [sample.size()];
		this.zValues = new double [xValues.length][yValues.length];
		System.out.println("size-x:"+xValues.length);
		System.out.println("size-y:"+yValues.length);
		System.out.println("size-z:"+zValues.length);
		//for (int i = 0; i < this.numColumns ; i++ ) {
		//	XYChart.Series<Number, Number> dataSeries = new XYChart.Series<Number, Number>();
		
		//String xCol = this.cols[i];
		//	if (!xCol.equals(Constants.QUALITY)) {
		//		dataSeries.setName(xCol);
				//int i = 0;
				for (WineData obj: sample ) {
					//dataSeries.getData().add(new XYChart.Data<Number, Number>(obj.getValue(xCol),obj.getdQty()));
					int i = sample.indexOf(obj);
					this.xValues[i] = obj.getdAlc(); 
					this.yValues[i] = obj.getdPH(); 
					//System.out.println(" alc:"+this.xValues[i] + " - ph-value:"+this.yValues[i]);
					//i++;
				}
				//System.out.println("highest index:"+i);
				
				for (int g = 0; g < xValues.length; g++) {
				//for (WineData objPrime: this.data.getWineData() ) {
					WineData objPrime = sample.get(g);
					double qty = objPrime.getdQty();
					for (int j = 0; j < yValues.length; j++) {
			        	WineData obj = sample.get(j);
			        	double qtyJ = obj.getdQty();
			        	if (j == 0) {
			        		zValues[g][j] = qty;
			        	}else {
			        		//zValues[g][j] = qtyJ;
			        		zValues[g][j] = (qty+qtyJ)/2;
			        		//System.out.println(" xcol:"+qty + " - ycol:"+qtyJ+" - values:"+zValues[g][j]);
			        		//zValues[g][j] = Math.abs(qty-qtyJ)/7;
			        	}
			        }
					//System.out.println(" index g:"+g);
			    }
			
				
				//
		//	}
		
		mydata = new DefaultData<Number,Number>(this.xValues,this.yValues,this.zValues);
		//mydata = new DefaultData<Number,Number>(this.xValues,this.yValues);
		this.view.getChart().setData(mydata);
		}
	
	
	public void set() {
		if (index >= this.numRows) {
			this.index = 0;
			this.view.getChart().setData(null);
		} 
		
		WineData objPrime = this.data.getWineData().get(this.index);
		double qty = objPrime.getdQty();
		for (int j = 0; j < yValues.length; j++) {
        	WineData obj = this.data.getWineData().get(j);
        	double qtyJ = obj.getdQty();
        	if (j == 0) {
        		zValues[this.index][j] = qty;
        	}else {
        		//zValues[g][j] = qtyJ;
        		zValues[this.index][j] = (qty+qtyJ)/2;
        		//zValues[g][j] = Math.abs(qty-qtyJ)/7;
        	}
        }
		DefaultData<Number,Number> newData = new DefaultData<Number,Number>(this.xValues,this.yValues,this.zValues);
		this.view.getChart().setData(newData);
		System.out.println("index:"+index);
		this.index++;
	}	
	
	public void setupAnimation(HeatMapChart<Number,Number> chart,int len) {
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(
		new KeyFrame(Duration.millis(500), 
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
