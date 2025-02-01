package animatedCharts.controller.hansolo;

import animatedCharts.controller.AbstractChartVC;
import animatedCharts.model.BalancesData;
import animatedCharts.model.Colors;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.view.hansolo.HSBalancesView;
import eu.hansolo.fx.charts.Symbol;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;

public class HSBalancesVC extends AbstractChartVC {
	private HSBalancesView view;
	private DoubleProperty maxX;
	private DoubleProperty maxY;

	public HSBalancesVC(Model data, HSBalancesView v) {
		super(data, v);
		this.view = v; 
		this.maxX = new  SimpleDoubleProperty(0);
		this.maxY = new  SimpleDoubleProperty(0);
		this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		setupTest1();
	}

	private void setupTest1() {
		this.getBalances();
		setData();
		getMaxX(this.maxY);
		setupChart();
	}

	private void setData() {
		System.out.println( "months:"+ MONTHS.length );
		Color lColor = null;
		Color symbolColor = null;
		int g = 1;
		int iSize = this.getData().getColors().size();
		for (int i = 0; i < MONTHS.length-1 ; i++ ) {
			String month = MONTHS[i];
			g = 1;
			for (BalancesData objBalance: this.getData().getBalances() ) {
				addPoint(g,objBalance.getValue(i) );
				g++;
			}
			if (i <  iSize  ) {
				Colors cx = this.getData().getColors().get(i);
				lColor = cx.getLineColor().get();
				symbolColor = cx.getAreaColor().get();
			} else {
				int c = i - iSize;
				Colors cx = this.getData().getColors().get(c);
				lColor = cx.getLineColor().get();
				symbolColor = cx.getAreaColor().get();
			}
			createSeries(month, lColor,symbolColor,Symbol.CIRCLE);
		}
	}
	
	public void getMaxX(DoubleProperty myMax) {
		int g = 1;
		for (int i = 0; i < MONTHS.length ; i++ ) {
			String month = MONTHS[i];
			g = 1;
			for (BalancesData objBalance: this.getData().getBalances() ) {
				double dval = objBalance.getValue(i);
				if (dval > myMax.getValue()) {
					myMax.setValue(dval);
				}
				g++;
			}
		}
		double i = Math.ceil(myMax.getValue()/5.0) * 5;
		System.out.println("max X:"+i);
		myMax.setValue(i);
	}
	
	public void setupChart() {
		this.view.setAxis(1, Constants.DAYS , 0, this.maxY.get(),Constants.AREA_CHART_X.toUpperCase(),Constants.AREA_CHART_Y);
		this.view.setupGrid(Constants.GRID_COLOR);
		this.view.setupSeries(this.getSeries());
		this.getSeries().clear();
		this.view.setLineChart();
		this.view.showChart();
	}

}
