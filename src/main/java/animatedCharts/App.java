package animatedCharts;
import animatedCharts.controller.AreaVC;
import animatedCharts.controller.BarVC;
import animatedCharts.controller.CurveVC;
import animatedCharts.controller.MainVC;
import animatedCharts.controller.ScatterVC;
import animatedCharts.controller.XYZVC;
import animatedCharts.controller.hansolo.HSBalancesVC;
import animatedCharts.controller.hansolo.HSCoxComVC;
import animatedCharts.controller.hansolo.HSGridVC;
import animatedCharts.controller.hansolo.HSProcessesVC;
import animatedCharts.controller.hansolo.HSRevenueVC;
import animatedCharts.controller.hansolo.HSServerTestVC;
import animatedCharts.controller.hansolo.HSTimeVC;
import animatedCharts.controller.hansolo.HSTitrationVC;
import animatedCharts.controller.hansolo.HSWineVC;
import animatedCharts.model.Model;
import animatedCharts.model.OsData;
import animatedCharts.view.hansolo.HSBalancesView;
import animatedCharts.view.hansolo.HSCoxComView;
import animatedCharts.view.hansolo.HSProcessesView;
import animatedCharts.view.hansolo.HSRevenueView;
import animatedCharts.view.hansolo.HSServerTestView;
import animatedCharts.view.hansolo.HSTimeView;
import animatedCharts.view.hansolo.HSTitrationView;
import animatedCharts.view.hansolo.HSWineView;
import blue.macroLab.mycmd.phcalc.model.Data;
import javafx.application.Application;
import javafx.stage.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import animatedCharts.model.Constants;

/**
 * Hello world!
 *
 */

public class App extends Application {

	public static void main (String[] args) 
	{
			launch(args);
	}
	
	public void start(Stage primaryStage) {
	   Model data = new Model(primaryStage,"My animated charts!");
	   boolean isSetup1 = false;
	   testPiechart(data);
	   //testBarchart(data);
	   //testAreachart(data);
	   //testScatterchart(data);
	   //testHeatmap(data);
	   //testLinechart(data);
	   //
	   // third party libraries
	   // Han Solo Library
	   //testGrid(data);
	   //testCoxCom(data);
	   //testTitration(data);
	   //testBalances(data);
	   
	   // 1000
	   //testBikeSharing(data); 
	   // 100
	   //testWineSet(data, Constants.SCATTER_CHART_TITLE,!isSetup1); 
	   
	   //testProcessesSet(data,Constants.BUBBLE_GRID_CHART_TITLE1); 
	   //testServerMeasurements(data,Constants.PARALLEL_COORDINATES_CHART_TITLE1);
	   //testRevenue(data); 
	   
	   //testWineSet(data, Constants.HEAT_MAP_TITLE1,isSetup1);
    }
	
	private void testServerMeasurements(Model data, String title) {
		HSServerTestView view = new HSServerTestView(data,title);
		HSServerTestVC vc = new HSServerTestVC(data, view);
		vc.show();
	}

	private void testProcessesSet(Model data, String title) {
		HSProcessesView view = new HSProcessesView(data, title);
		HSProcessesVC vc = new HSProcessesVC(data,view);
		vc.show();
	}

	private void testWineSet(Model data,String title, boolean isSetup1) {
		HSWineView view = new HSWineView(data,title,isSetup1);
		//HSWineView view = new HSWineView(data, Constants.SCATTER_CHART_TITLE,isSetup1);
		HSWineVC vc = new HSWineVC(data,view,isSetup1);
		vc.show();
	}

	private void testBikeSharing(Model data) {
		HSTimeView view = new HSTimeView(data, Constants.CHART_BIKESHARING_TITLE);
		HSTimeVC vc = new HSTimeVC(data,view);
		vc.show();
	}

	private void testBalances(Model data) {
		HSBalancesView view = new HSBalancesView(data, Constants.AREA_CHART_TITLE);
		HSBalancesVC vc = new HSBalancesVC(data,view);
		vc.show();
	}

	private void testGrid(Model data) {
		HSGridVC grid = new HSGridVC(data);
		grid.show();
	}
	
	private void testCoxCom(Model data) {
		HSCoxComView view = new HSCoxComView(data);
		HSCoxComVC vc = new HSCoxComVC(data,view);
		vc.show();
	}
	
	private void testTitration(Model data) {
		HSTitrationView view = new HSTitrationView(data);
		HSTitrationVC vc = new HSTitrationVC(data,view);
		vc.show();
	}
	
	private void testRevenue(Model data) {
		HSRevenueView view = new HSRevenueView(data);
		HSRevenueVC vc = new HSRevenueVC(data,view);
		vc.show();
	}

	public static void testPiechart(Model data) {
		MainVC mainVC = new MainVC(data,12);
		mainVC.show();   
	}
	
	public static void testBarchart(Model data) {
		BarVC mainVC = new BarVC(data);
		mainVC.show();   
	}
	
	public static void testAreachart(Model data) {
		AreaVC mainVC = new AreaVC(data);
		mainVC.show();   
	}
	
	public static void testScatterchart(Model data) {
		ScatterVC mainVC = new ScatterVC(data);
		mainVC.show();   
	}
	
	public static void testHeatmap(Model data) {
		XYZVC mainVC = new XYZVC(data);
		mainVC.show();   
	}
	
	public static void testLinechart(Model data) {
		CurveVC vc = new CurveVC(data);
		vc.show();
	}
    
    
}