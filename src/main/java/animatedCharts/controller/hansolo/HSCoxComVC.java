package animatedCharts.controller.hansolo;

import java.util.List;
import java.util.Optional;

import animatedCharts.controller.AbstractChartVC;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.OsData;
import animatedCharts.view.hansolo.HSCoxComView;
import eu.hansolo.fx.charts.data.ChartItem;
import eu.hansolo.fx.charts.data.ChartItemBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;

public class HSCoxComVC extends AbstractChartVC {
	private HSCoxComView hsView; 
	private OsData dataSet = null;
	private int index = 6;
	private String pieChartTitle = "";
	

	public HSCoxComVC(Model data, HSCoxComView v) {
		super(data, v);
		this.hsView = v;
		this.hsView.getCancelBtn().setOnAction(e -> btnCancel_Click());
		setChartTitle();
		initialize();
		setupChart();
	}
	
	 private void setupChart() {
		 EventHandler<MouseEvent> onPressedHandler = e -> {
	            Optional<ChartItem> opt = this.hsView.getChart().getSelectedItem(e);
	            if (opt.isEmpty()) { return; }
	            ChartItem selectedItem = opt.get();
	            //System.out.println(selectedItem);
	            if (selectedItem.isSelected()) {
	                selectedItem.setSelected(false);
	            } else {
	            	this.hsView.getChart().getItems().forEach(item -> item.setSelected(false));
	                selectedItem.setSelected(true);
	            }
	        };

	        EventHandler<MouseEvent> onMoveHandler = e -> {
	            Optional<ChartItem> opt = this.hsView.getChart().getSelectedItem(e);
	            if (opt.isEmpty()) { return; }
	            System.out.println(opt.get());
	        };
		 this.hsView.getChart().addItems(this.getData().getOsItems());
		 this.hsView.getChart().onMousePressed(onPressedHandler);
		 this.hsView.getChart().onMouseMoved(onMoveHandler);
		 //this.hsView.getChart().autosize();
	}
	 

	public void setChartTitle() {
		 getOsData(Constants.CSV_PIE_CHART_CURRENT);
		 this.dataSet = this.getData().getOsData().get(index);
		 this.pieChartTitle = Constants.PIE_CHART_TITLE + ": " + this.dataSet.getDate();
		 this.hsView.getRow().setText(pieChartTitle);
	 }
	 
	 public void initialize() {
			List<ChartItem> items = List.of(
		            ChartItemBuilder.create().name(Constants.PLAYSTATION_OS).value(dataSet.getPlayOS()).fill(Color.web("#96AA3B")).build(),
		            ChartItemBuilder.create().name(Constants.ANDROID_OS).value(dataSet.getAndroidOS()).fill(Color.web("#29A783")).build(),
		            ChartItemBuilder.create().name(Constants.WIN_OS).value(dataSet.getWindowsOS()).fill(Color.web("#098AA9")).build(),
		            ChartItemBuilder.create().name(Constants.I_OS).value(dataSet.getiOS()).fill(Color.web("#62386F")).build(),
		            ChartItemBuilder.create().name(Constants.X_OS).value(dataSet.getxOS()).fill(Color.web("#89447B")).build(),
		            ChartItemBuilder.create().name(Constants.UNK_OS).value(dataSet.getUnkOS()).fill(Color.web("#EF5780")).build(),
		            
		            ChartItemBuilder.create().name(Constants.CHROME_OS).value(dataSet.getChromeOS()).fill(Color.web("#96AA3B")).build(),
		            ChartItemBuilder.create().name(Constants.LINUX_OS).value(dataSet.getLinuxOS()).fill(Color.web("#29A783")).build(),
		            ChartItemBuilder.create().name(Constants.SAMSUNG_OS).value(dataSet.getSamsungOS()).fill(Color.web("#098AA9")).build(),
		            ChartItemBuilder.create().name(Constants.XBOX_OS).value(dataSet.getXboxOS()).fill(Color.web("#62386F")).build(),
		            ChartItemBuilder.create().name(Constants.KAI_OS).value(dataSet.getKaiOS()).fill(Color.web("#89447B")).build(),
		            ChartItemBuilder.create().name(Constants.OTHER_OS).value(dataSet.getOther()).fill(Color.web("#EF5780")).build()
		            
		            /*ChartItemBuilder.create().name(Constants.CHROME_OS).value(27).fill(Color.web("#96AA3B")).build(),
		            ChartItemBuilder.create().name(Constants.LINUX_OS).value(24).fill(Color.web("#29A783")).build(),
		            ChartItemBuilder.create().name(Constants.SAMSUNG_OS).value(16).fill(Color.web("#098AA9")).build(),
		            ChartItemBuilder.create().name(Constants.XBOX_OS).value(15).fill(Color.web("#62386F")).build(),
		            ChartItemBuilder.create().name(Constants.KAI_OS).value(13).fill(Color.web("#89447B")).build(),
		            ChartItemBuilder.create().name(Constants.OTHER_OS).value(5).fill(Color.web("#EF5780")).build()*/
		            );
			this.getData().setOsItems(items);
		}

}
