package animatedCharts.model;
/*import static eu.hansolo.fx.charts.color.MaterialDesignColors.AMBER_100;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.AMBER_300;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.AMBER_500;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.AMBER_700;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.CYAN_100;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.CYAN_300;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.CYAN_500;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.CYAN_700;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.PINK_100;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.PINK_300;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.PINK_500;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.PINK_700;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.PURPLE_100;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.PURPLE_300;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.PURPLE_500;
import static eu.hansolo.fx.charts.color.MaterialDesignColors.PURPLE_700;*/
import static eu.hansolo.fx.charts.color.MaterialDesignColors.*;

import java.util.ArrayList;
import java.util.List;

import eu.hansolo.fx.charts.Symbol;
import eu.hansolo.fx.charts.color.MaterialDesignColors;
import eu.hansolo.fx.charts.data.ChartItem;
import eu.hansolo.fx.charts.data.XYChartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.stage.*;

public class Model {
	private Stage primaryStage = null; 
	private String appTitle = "";
	private int screenHeight = 0;
	private int screenWidth = 0;
	private List<OsData> osData;
	private List<RevenueData> txnData;
	private List<BalancesData> balances;
	private List<WineData> wineData;
	private List<WineData> wineSamples;
	private List<BikeSharingData> bikeData;
	private List<ProcessData> processes;
	private List<TestData> serverTests;
	private ObservableList<PieChart.Data> pcData;
	private List<ChartItem> osItems;
	private List<XYChartItem> titrationSample;
	private List<Colors> colors;
	private List <Server>servers;
	public static MaterialDesignColors[] GREENS = {GREEN_700,GREEN_500,GREEN_300,GREEN_100};
	public static MaterialDesignColors[] PURPLES = {PURPLE_700,PURPLE_500,PURPLE_300,PURPLE_100};
	public static MaterialDesignColors[] LIMES = {LIME_700,LIME_500,LIME_300,LIME_100};
	public static MaterialDesignColors[] AMBERS = {AMBER_700,AMBER_500,AMBER_300,AMBER_100};

	
	public Model(Stage primaryStage,String title) {
		 this.primaryStage = primaryStage;
	     this.appTitle = title;
	     this.setScreenSize();	
		 this.pcData = FXCollections.observableArrayList();
		 this.osData =  new ArrayList<OsData>();
		 this.txnData =  new ArrayList<RevenueData>();
		 this.balances =  new ArrayList<BalancesData>();
		 this.wineData =  new ArrayList<WineData>();
		 this.wineSamples =  new ArrayList<WineData>();
		 this.osItems =  new ArrayList<ChartItem>();
		 this.titrationSample =  new ArrayList<XYChartItem>();
		 this.colors = new ArrayList<Colors>();
		 this.bikeData = new ArrayList<BikeSharingData>();
		 this.processes = new ArrayList<ProcessData>();
		 this.servers = new ArrayList<Server>();
		 setColors();
	}
	
	public Stage getPrimaryStage() {
	      return primaryStage;
	}
	
	public void setScreenSize(){
		   this.screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	       this.screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
	}

	public int getScreenHeight() {
		   return this.screenHeight;
	}
	   
	public int getScreenWidth() {
		   return this.screenWidth;
	}
	
	public String getAppTitle() {
		return this.appTitle;
	}
	
	public void setColors() {
		this.colors.add(new Colors(MaterialDesignColors.GREEN_900,MaterialDesignColors.GREEN_100,GREENS,Symbol.SQUARE));
		this.colors.add(new Colors(MaterialDesignColors.AMBER_900,MaterialDesignColors.AMBER_100, AMBERS, Symbol.CIRCLE));
		this.colors.add(new Colors(MaterialDesignColors.DEEP_PURPLE_900,MaterialDesignColors.DEEP_PURPLE_100,PURPLES,Symbol.CROSS));
		this.colors.add(new Colors(MaterialDesignColors.LIME_900,MaterialDesignColors.LIME_100,LIMES,Symbol.TRIANGLE));
		this.colors.add(new Colors(MaterialDesignColors.YELLOW_500,MaterialDesignColors.YELLOW_100,Symbol.CIRCLE));
		this.colors.add(new Colors(MaterialDesignColors.DEEP_ORANGE_500,MaterialDesignColors.DEEP_ORANGE_100,Symbol.CROSS));
		this.colors.add(new Colors(MaterialDesignColors.INDIGO_900,MaterialDesignColors.INDIGO_100,Symbol.SQUARE));
		this.colors.add(new Colors(MaterialDesignColors.RED_900,MaterialDesignColors.RED_100,Symbol.STAR));
		this.colors.add(new Colors(MaterialDesignColors.TEAL_A700,MaterialDesignColors.TEAL_100,Symbol.SQUARE));
		this.colors.add(new Colors(MaterialDesignColors.BLUE_GREY_600,MaterialDesignColors.BLUE_GREY_100,Symbol.STAR));
		//this.colors.add(new Colors(MaterialDesignColors.GREY_900,MaterialDesignColors.GREY_100,Symbol.TRIANGLE));
		this.colors.add(new Colors(MaterialDesignColors.BLUE_900,MaterialDesignColors.BLUE_100,Symbol.CIRCLE));
	}
	
	public static String createHeadline(String prefix, String suffix) {
		StringBuilder sb = new StringBuilder(prefix);
		sb.append(" ");
		sb.append(Constants.LP);
		sb.append(suffix);
		sb.append(Constants.RP);
		return sb.toString();
	}
	
	public List<RevenueData> getTxData() {
		return txnData;
	}

	public void setTxData(List<RevenueData> data) {
		this.txnData = data;
	}
	
	public List<BalancesData> getBalances() {
		return balances;
	}

	public void setBalances(List<BalancesData> balances) {
		this.balances = balances;
	}

	public List<WineData> getWineData() {
		return wineData;
	}

	public void setWineData(List<WineData> wineData) {
		this.wineData = wineData;
	}

	public List<WineData> getWineSamples() {
		return wineSamples;
	}

	public void setWineSamples(List<WineData> wineSamples) {
		this.wineSamples = wineSamples;
	}

	public List<OsData> getOsData() {
		return osData;
	}

	public void setOsData(List<OsData> osData) {
		this.osData = osData;
	}

	public List<BikeSharingData> getBikeData() {
		return bikeData;
	}

	public void setBikeData(List<BikeSharingData> bikeData) {
		this.bikeData = bikeData;
	}

	public ObservableList<PieChart.Data> getPcData() {
		return pcData;
	}

	public List<ChartItem> getOsItems() {
		return osItems;
	}

	public void setOsItems(List<ChartItem> osItems) {
		this.osItems = osItems;
	}

	public List<XYChartItem> getSample() {
		return titrationSample;
	}

	public void setSample(List<XYChartItem> titrationSample) {
		this.titrationSample = titrationSample;
	}

	public List<Colors> getColors() {
		return colors;
	}

	public List<ProcessData> getProcesses() {
		return processes;
	}

	public void setProcesses(List<ProcessData> processes) {
		this.processes = processes;
	}

	public List<TestData> getServerTests() {
		return serverTests;
	}

	public void setServerTests(List<TestData> serverTests) {
		this.serverTests = serverTests;
	}

	public List<Server> getServers() {
		return servers;
	}

}
