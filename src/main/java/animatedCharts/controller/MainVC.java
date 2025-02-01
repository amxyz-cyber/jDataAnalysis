package animatedCharts.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.opencsv.bean.CsvToBeanBuilder;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.OsData;
import animatedCharts.view.MainView;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class MainVC {
	 private MainView view;
	 private Model data;
	 private int index = 0;
	 private String pieChartTitle = "";
	 private OsData dataSet = null;
	 
	 
	 public MainVC(Model data, int i) {
		 this.index = i;
		 this.data = data;	
		 setChartTitle();
		 initialize();
		 this.view = new MainView(this.data,this.pieChartTitle);
		 this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		 setupAnimation();
		 //this.displayLabels();
		 }
	 
	 public void btnCancel_Click() 
	 {
	 	this.data.getPrimaryStage().close();
	 }
	 
	 public void show(){
	      this.view.show(this.data.getPrimaryStage(),this.view.getScene());
	   }
	   
	 public MainView getMainView() {
		return this.view;
	 }
	 
	 public void setChartTitle() {
		 getOsData();
		 this.dataSet = this.data.getOsData().get(index);
		 this.pieChartTitle = Constants.PIE_CHART_TITLE + ": " + this.dataSet.getDate();	
		 }
	 
	 public void getOsData() {
		 try {
			 List<OsData> beans = new CsvToBeanBuilder(new FileReader(Constants.CSV_PIE_CHART))
				           .withType(OsData.class)
				           .build()
				           .parse();
			this.data.setOsData(beans);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				e.printStackTrace();
			}

		    for (OsData o : this.data.getOsData()) {
		        //System.out.println(o.getAndroidOS());
		    }
	 }

	public void initialize() {
		this.data.getPcData().add(new PieChart.Data(Constants.PLAYSTATION_OS,dataSet.getPlayOS() ));
		this.data.getPcData().add(new PieChart.Data(Constants.ANDROID_OS,dataSet.getAndroidOS()));
		this.data.getPcData().add(new PieChart.Data(Constants.WIN_OS,dataSet.getWindowsOS() ));
		this.data.getPcData().add(new PieChart.Data(Constants.I_OS,dataSet.getiOS() ));
		this.data.getPcData().add(new PieChart.Data(Constants.X_OS,dataSet.getxOS() ));
		this.data.getPcData().add(new PieChart.Data(Constants.UNK_OS,dataSet.getUnkOS() ));
		this.data.getPcData().add(new PieChart.Data(Constants.CHROME_OS,dataSet.getChromeOS() ));
		this.data.getPcData().add(new PieChart.Data(Constants.LINUX_OS,dataSet.getLinuxOS() ));
		this.data.getPcData().add(new PieChart.Data(Constants.SAMSUNG_OS,dataSet.getSamsungOS() ));
		this.data.getPcData().add(new PieChart.Data(Constants.XBOX_OS,dataSet.getXboxOS() ));
		this.data.getPcData().add(new PieChart.Data(Constants.KAI_OS,dataSet.getKaiOS() ));
		this.data.getPcData().add(new PieChart.Data(Constants.OTHER_OS,dataSet.getOther() ));
		
	}
	
    private void setupAnimation() {
    	this.data.getPcData().stream().forEach(pieData -> {
            //System.out.println(pieData.getName() + ": " + pieData.getPieValue());
            pieData.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> { 
                Bounds b1 = pieData.getNode().getBoundsInLocal();
                double newX = (b1.getWidth()) / 2 + b1.getMinX();
                double newY = (b1.getHeight()) / 2 + b1.getMinY();
                // Make sure pie wedge location is reset
                pieData.getNode().setTranslateX(0);
                pieData.getNode().setTranslateY(0);
                
                // Show the BoundsInLocal of the selected wedge with a rectangle
                this.view.getRectangle().setTranslateX(newX);
                this.view.getRectangle().setTranslateY(newY);
                this.view.getRectangle().setWidth(b1.getWidth());
                this.view.getRectangle().setHeight(b1.getHeight());

                // Create the animation
                TranslateTransition tt = new TranslateTransition(Duration.millis(1500), pieData.getNode());
                tt.setByX(newX);
                tt.setByY(newY);
                tt.setAutoReverse(true);
                tt.setCycleCount(2);
                tt.play();
            });
        });
    }
    
    private void displayLabels() {
    	this.data.getPcData().stream().forEach(pieData -> {
            pieData.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> { 
            	this.view.getPercentLabel().setTranslateX(event.getSceneX() - this.view.getPercentLabel().getLayoutX());
            	this.view.getPercentLabel().setTranslateY(event.getSceneY() - this.view.getPercentLabel().getLayoutY());
            	String s = String.valueOf(pieData.getPieValue()) + "%";
            	this.view.getPercentLabel().setText(s);
            });
        });
    }
}
