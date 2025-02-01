package animatedCharts.controller.hansolo;

import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.List;

import animatedCharts.controller.AbstractChartVC;
import animatedCharts.model.Colors;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.Revenue;
import animatedCharts.model.RevenueData;
import animatedCharts.view.hansolo.HSRevenueView;
import eu.hansolo.fx.charts.Symbol;
import eu.hansolo.fx.charts.color.MaterialDesignColors;

public class HSRevenueVC extends AbstractChartVC {
	private HSRevenueView view;
	private String chartTitle = "";

	public HSRevenueVC(Model data, HSRevenueView v) {
		super(data, v);
		this.view = v; 
		this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		setupTest1();
	}
	
	public void setupTest1() {
		this.getRevenueData(Constants.CSV_REVENUES);
		this.setChartTitle();
		setData();
		this.view.setupChart(this.getChartItems());
		this.view.showChart();
	 }
	
	 public void setChartTitle() {
		 if (this.getData().getTxData().isEmpty()) {
			 this.chartTitle = Constants.BAR_CHART_TITLE_REVENUES;
		 } else {
			 int len = this.getData().getTxData().size();
			 RevenueData first = this.getData().getTxData().get(0);
			 this.chartTitle = Constants.BAR_CHART_TITLE_REVENUES + ": " + (first.getCal().get(Calendar.YEAR)) ;
		 }
		 
		this.view.getRow().setText(chartTitle);
	 }
	 
	 public void setData() {
		 int i = 0;
		 int g = 0;
		 int iSize = this.getData().getColors().size();
		 
		 for (Revenue r : Revenue.values()) {
			 if (i >= iSize) {
				 i = 0;
			 }
			 List<RevenueData> rX = this.getData().getTxData().stream()
					    .filter(p -> p.getCompany() == r).collect(Collectors.toList());
			 Colors cx = this.getData().getColors().get(i);
			 for (RevenueData rD: rX) {
				 if (cx.getShades().length > 0) {
					 MaterialDesignColors col = cx.getShades()[g];
					 String barLabel = Constants.REVENUES_PREFIX+" "+(g+1);
					 this.addChartItem(barLabel,rD.getYearlyRevenue(),col);
				 }
				 ++g;
			 }
			 if (getlChartItems().size() > 0) {
				 MaterialDesignColors mainColor = cx.getLineColor();
				 addSeries(r.getName(), mainColor,Symbol.CIRCLE );
			 }
			 ++i;
			 g = 0;
			}
	 }

}
