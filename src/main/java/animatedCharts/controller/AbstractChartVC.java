package animatedCharts.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import animatedCharts.model.AbstractItem;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.OsData;
import animatedCharts.model.RevenueData;
import animatedCharts.view.AbstractChartView;
import eu.hansolo.fx.charts.ChartType;
import eu.hansolo.fx.charts.Symbol;
import eu.hansolo.fx.charts.color.MaterialDesignColors;
import eu.hansolo.fx.charts.data.BubbleGridChartItem;
import eu.hansolo.fx.charts.data.BubbleGridChartItemBuilder;
import eu.hansolo.fx.charts.data.ChartItem;
import eu.hansolo.fx.charts.data.ChartItemBuilder;
import eu.hansolo.fx.charts.data.MatrixChartItem;
import eu.hansolo.fx.charts.data.TYChartItem;
import eu.hansolo.fx.charts.data.XYChartItem;
import eu.hansolo.fx.charts.series.ChartItemSeries;
import eu.hansolo.fx.charts.series.MatrixItemSeries;
import eu.hansolo.fx.charts.series.XYSeries;
import eu.hansolo.fx.charts.tools.Order;
import javafx.scene.paint.Color;

import static eu.hansolo.fx.charts.color.MaterialDesignColors.* ;

public abstract class AbstractChartVC extends AbstractVC{
	private AbstractChartView view;
	private List<XYSeries> series;
	private List <XYChartItem> lXYItems;
	private List <ChartItem> lChartItems;
	private List<ChartItemSeries<ChartItem>> chartItems;
	private List<TYChartItem> tyItems;
	private List<MatrixChartItem> matrixData;
	private List<BubbleGridChartItem> bubbleItems;
	private MatrixItemSeries<MatrixChartItem>matrixSeries;

	public AbstractChartVC(Model data, AbstractChartView v) {
		super(data, v);
		this.view = v;
		series = new ArrayList<>(); 
		this.chartItems = new ArrayList<>();
		this.lChartItems = new ArrayList<>();
		//this.XChartItems = new ArrayList<>();
		this.lXYItems = new ArrayList<>();
		this.tyItems = new ArrayList<>();
		this.matrixData = new ArrayList<>();
		this.bubbleItems = new ArrayList<>();
	}
	
	public AbstractChartView getView() {
		return this.view;
	 }
	 
	 public void addSeries(XYSeries xy) {
		 this.series.add(xy);
	 }
	 
	 public void addChartItem(String sLabel, double value,MaterialDesignColors col) {
		  ChartItem p1Q1 = new ChartItem(sLabel, value, col.get());
		  this.lChartItems.add(p1Q1);
	 }
	 
	 // Y-category
	 public void addChartItem(String sLabel, int index) {
		  ChartItem cItem = ChartItemBuilder.create().name(sLabel).index(index).fill(this.getData().getColors().get(index).getLineColor().get()).build();
		  this.lChartItems.add(cItem);
	 }
	 
	 public void addChartItem(ChartItem cItem) {
		  this.lChartItems.add(cItem);
	 }
	
	 public void addPoint(double x, double y) {
		 XYChartItem p = new XYChartItem();
		 p.setX(x);
		 p.setY(y);
		 this.lXYItems.add(p);
	 }
	 
	 public void addPoint(LocalDateTime x,double y) {
		 TYChartItem t = new TYChartItem(x,y);
		 tyItems.add(t);
	 }
	 
	 public void addPoint(int x, int y, double z) {
		MatrixChartItem mdo = new MatrixChartItem(x, y, z);
		this.matrixData.add(mdo);
	 }
	 
	 public void addSeries(String label, MaterialDesignColors col,Symbol sym ) {
		 ChartItemSeries<ChartItem> chartSeries = new ChartItemSeries<>(ChartType.NESTED_BAR, label, col.get(), Color.TRANSPARENT);
		 chartSeries.sort(Order.ASCENDING);
		 chartSeries.setItems(lChartItems);
		 this.lChartItems.clear();
		 this.chartItems.add(chartSeries);
		 this.view.addLegend(label, sym, col.get());
	 }
	 
	 public void addSeries(ChartItem xItem,ChartItem yItem,double value) {
		 BubbleGridChartItem items  = BubbleGridChartItemBuilder.create().categoryXItem(xItem).categoryYItem(yItem).value(value).build();
		 this.bubbleItems.add(items);
	 }

	public List<XYSeries> getSeries() {
		return series;
	}
	
	public void createSeries(String label,String sColor,String colSymbol) {
		XYSeries xySeries = this.view.lineStyle(sColor,colSymbol,label,this.getData().getSample());
		this.addSeries(xySeries);
		this.view.addLegend(label, Symbol.TRIANGLE, sColor);
	}
	
	public void createSeries(String label,Color lineColor,Color symColor,Symbol sym) {
		XYSeries xySeries = this.view.areaStyle(lineColor,symColor,label,lXYItems,sym);
		this.addSeries(xySeries);
		this.view.addLegend(label, sym, lineColor);
		this.lXYItems.clear();
	}
	
	 // X-Category
	 public ChartItem createXCategory(String xLabel, int index) {
		 ChartItem xCategory  = ChartItemBuilder.create().name(xLabel).index(index).fill(this.getData().getColors().get(index).getLineColor().get()).build();
		 return xCategory;
	 }
	
	
	public void createSeries(Color fillColor,Color symColor,Symbol sym,String label) {
		XYSeries xySeries = this.view.scatterStyle(fillColor,symColor, label,sym, lXYItems);
		this.addSeries(xySeries);
		this.view.addLegend(label, sym, fillColor);
		this.lXYItems.clear();
	}
	
	public void createSeries(String label,Color lineColor) {
		XYSeries xySeries = this.view.lineStyle(lineColor,label,tyItems);
		this.addSeries(xySeries);
		this.tyItems.clear();
	}
	
	public void createSeries(int rows, int cols) {
		matrixSeries  = new MatrixItemSeries<>(matrixData, ChartType.MATRIX_HEATMAP);
		this.view.matrixStyle(matrixSeries, rows, cols);
		this.matrixData.clear();
	}

	public List<ChartItemSeries<ChartItem>> getChartItems() {
		return chartItems;
	}

	public List<ChartItem> getlChartItems() {
		return lChartItems;
	}

	public List<BubbleGridChartItem> getBubbleItems() {
		return bubbleItems;
	}

}
