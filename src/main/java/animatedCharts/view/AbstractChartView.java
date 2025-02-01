package animatedCharts.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.Server;
import eu.hansolo.fx.charts.Axis;
import eu.hansolo.fx.charts.BubbleGridChart;
import eu.hansolo.fx.charts.BubbleGridChartBuilder;
import eu.hansolo.fx.charts.ChartType;
import eu.hansolo.fx.charts.Grid;
import eu.hansolo.fx.charts.GridBuilder;
import eu.hansolo.fx.charts.Legend;
import eu.hansolo.fx.charts.LegendItem;
import eu.hansolo.fx.charts.MatrixPane;
import eu.hansolo.fx.charts.ParallelCoordinatesChart;
import eu.hansolo.fx.charts.ParallelCoordinatesChartBuilder;
import eu.hansolo.fx.charts.Position;
import eu.hansolo.fx.charts.Symbol;
import eu.hansolo.fx.charts.XYPane;
import eu.hansolo.fx.charts.data.BubbleGridChartItem;
import eu.hansolo.fx.charts.data.MatrixChartItem;
import eu.hansolo.fx.charts.data.TYChartItem;
import eu.hansolo.fx.charts.data.XYChartItem;
import eu.hansolo.fx.charts.series.MatrixItemSeries;
import eu.hansolo.fx.charts.series.XYSeries;
import eu.hansolo.fx.charts.series.XYSeriesBuilder;
import eu.hansolo.fx.charts.tools.Order;
import eu.hansolo.fx.charts.tools.Topic;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class AbstractChartView extends AbstractView {
	private AnchorPane pane;
	private String chartTitle = "";
	private static final Double AXIS_WIDTH = 25d;
	private              Axis   xAxis;
    private              Axis   yAxis;
    private 			 Axis   yAxisRight;
    private              Grid   chartGrid;
    private Legend legend;
    //private HBox chartPane;
    private StackPane chartPane;
    private Label row;
    private List<XYPane> xyPanes;
    private MatrixPane<MatrixChartItem> matrixHeatMap;
    private BubbleGridChart bubbleGridChart;
    private ParallelCoordinatesChart parallelCoordinatesChart;

	public AbstractChartView(Model data, String s,String path) {
		super(data,path);
		this.chartTitle = s;
		this.legend = new Legend();
		xyPanes = new ArrayList<>();
	}
	
	public AbstractChartView(Model data, String path) {
		super(data,path);
		this.legend = new Legend();
	}
	
	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}
	
	public void setupHeadline() {
		 row = new Label();
		 row.getStyleClass().add(Constants.CSS_LABEL);
		 row.setText(getChartTitle());
	}
	
	public void setChartWindow(Pos pos) {
		legend.setOrientation(Orientation.VERTICAL);
		//this.chartPane = this.setHBox(pos);
		this.chartPane = new StackPane();
		this.chartPane.setAlignment(pos);
	}
	
	public Axis createLeftYAxis(final double MIN, final double MAX, String ylabel, final boolean AUTO_SCALE) {
	        Axis axis = new Axis(Orientation.VERTICAL, Position.LEFT);
	        axis.setMinValue(MIN);
	        axis.setMaxValue(MAX);
	        axis.setPrefWidth(AXIS_WIDTH);
	        axis.setAutoScale(AUTO_SCALE);
	        axis.setTitle(ylabel);

	        AnchorPane.setTopAnchor(axis, 0d);
	        AnchorPane.setBottomAnchor(axis, 25d);
	        AnchorPane.setLeftAnchor(axis, 0d);

	        return axis;
	    }
	
	public Axis createRightYAxis(final double MIN, final double MAX, final boolean AUTO_SCALE) {
	        Axis axis = new Axis(Orientation.VERTICAL, Position.RIGHT);
	        axis.setMinValue(MIN);
	        axis.setMaxValue(MAX);
	        axis.setPrefWidth(AXIS_WIDTH);
	        axis.setAutoScale(AUTO_SCALE);

	        AnchorPane.setRightAnchor(axis, 0d);
	        AnchorPane.setTopAnchor(axis, 0d);
	        AnchorPane.setBottomAnchor(axis, 25d);

	        return axis;
	    }

	public Axis createBottomXAxis(final double MIN, final double MAX, String xlabel, final boolean AUTO_SCALE) {
	        Axis axis = new Axis(Orientation.HORIZONTAL, Position.BOTTOM);
	        axis.setMinValue(MIN);
	        axis.setMaxValue(MAX);
	        axis.setPrefHeight(AXIS_WIDTH);
	        axis.setAutoScale(AUTO_SCALE);
	        axis.setTitle(xlabel);

	        AnchorPane.setBottomAnchor(axis, 0d);
	        AnchorPane.setLeftAnchor(axis, 25d);
	        AnchorPane.setRightAnchor(axis, 25d);

	        return axis;
	    }
	public Axis createTopXAxis(final double MIN, final double MAX, final boolean AUTO_SCALE) {
	        Axis axis = new Axis(Orientation.HORIZONTAL, Position.TOP);
	        axis.setMinValue(MIN);
	        axis.setMaxValue(MAX);
	        axis.setPrefHeight(AXIS_WIDTH);
	        axis.setAutoScale(AUTO_SCALE);
	        AnchorPane.setTopAnchor(axis, 25d);
	        AnchorPane.setLeftAnchor(axis, 25d);
	        AnchorPane.setRightAnchor(axis, 25d);
	        return axis;
	    }
	
	private Axis createBottomTimeAxis(final LocalDateTime START, final LocalDateTime END,String xlabel, final String PATTERN, final boolean AUTO_SCALE) {
        Axis axis = new Axis(START, END, Orientation.HORIZONTAL, Position.BOTTOM);
        axis.setDateTimeFormatPattern(PATTERN);
        axis.setPrefHeight(AXIS_WIDTH);
        axis.setTitle(xlabel);

        AnchorPane.setBottomAnchor(axis, 0d);
        AnchorPane.setLeftAnchor(axis, 25d);
        AnchorPane.setRightAnchor(axis, 25d);

        return axis;
    }
	
	public void setAxis(double xmin, double xmax, double ymin, double ymax,String xTitle, String yTitle) {
        xAxis = createBottomXAxis(xmin, xmax, xTitle, true);
        xAxis.setMinorTickMarksVisible(false);
        //xAxis.setMajorTickMarkColor(Color.RED);

        yAxis = createLeftYAxis(ymin, ymax, yTitle, true);
        yAxis.setMinorTickMarksVisible(false);
        //yAxis.setMediumTickMarkColor(Color.MAGENTA);
    }
	
	public void setTimeAxis( double ymin, double ymax,String xTitle, String yTitle) {
		LocalDateTime     start      = LocalDateTime.now().withHour(0);
        LocalDateTime     end        = start.plusHours(Constants.HOURS);
		xAxis = createBottomTimeAxis(start, end,xTitle, "HH:mm", true);
        yAxis   = createLeftYAxis(ymin, ymax,yTitle,  true);
    }
	
	public void setupGrid(String sColor) {
		//chartGrid  = new Grid(xAxis, yAxis);
		chartGrid = GridBuilder.create(xAxis, yAxis)
	            .gridLinePaint(Color.web(sColor))
	            .minorHGridLinesVisible(false)
	            .mediumHGridLinesVisible(false)
	            .minorVGridLinesVisible(false)
	            .mediumVGridLinesVisible(false)
	            .gridLineDashes(4, 4)
	            .scaleX(1.2)
	            .scaleY(1.2)
	            .build();
	}
	


	public void setupChartGrid() {
		pane = new AnchorPane(xAxis, yAxis, chartGrid);
	
		AnchorPane.setTopAnchor(yAxis, 0d);
		AnchorPane.setBottomAnchor(yAxis, 25d);
		AnchorPane.setLeftAnchor(yAxis, 0d);
	
		AnchorPane.setLeftAnchor(xAxis, 25d);
		AnchorPane.setRightAnchor(xAxis, 0d);
		AnchorPane.setBottomAnchor(xAxis, 0d);
	
		AnchorPane.setTopAnchor(chartGrid, 0d);
		AnchorPane.setRightAnchor(chartGrid, 0d);
		AnchorPane.setBottomAnchor(chartGrid, 25d);
		AnchorPane.setLeftAnchor(chartGrid, 25d);
	}
	
	public XYSeries<?> lineStyle(String sColor,String sColor2, String label,List<XYChartItem> lyst) {
		XYSeries<?> xySeries = XYSeriesBuilder.create()
	             .items(lyst)
	             .chartType(ChartType.SMOOTH_LINE)
	             .fill(Color.web(sColor))
	             .stroke(Color.web(sColor))
	             .symbolFill(Color.web(sColor2))
	             .symbolStroke(Color.web("#393839"))
	             .symbolSize(7)
	             .strokeWidth(2)
	             .symbolsVisible(true)
	             .symbol(Symbol.CROSS)
	             .name(label)
	             .build();
		return xySeries;
	}
	
	public XYSeries<?> scatterStyle(Color lineColor,Color symColor, String label,Symbol sym, List<XYChartItem> lyst) {
		XYSeries<?> xySeries = XYSeriesBuilder.create()
	             .items(lyst)
	             .chartType(ChartType.SCATTER)
	             .fill(lineColor)
	             .stroke(lineColor)
	             //.symbolFill(symColor)
	             .symbolFill(Color.TRANSPARENT)
	             //.symbolStroke(MaterialDesignColors.GREY_700.get())
	             .symbolStroke(symColor)
	             .symbolSize(10)
	             .strokeWidth(1)
	             .symbolsVisible(true)
	             .symbol(sym)
	             .name(label)
	             .build();
		return xySeries;
	}
	
	public XYSeries<?> lineStyle(Color col, String label,List<TYChartItem> lyst) {
		XYSeries<?> xySeries = XYSeriesBuilder.create()
	             .items(lyst)
	             .chartType(ChartType.SMOOTH_LINE)
	             .fill(col)
	             .stroke(col)
	             .strokeWidth(2)
	             .symbolsVisible(false)
	             .name(label)
	             .build();
		return xySeries;
	}
	
	public XYSeries<?> areaStyle(Color cArea,Color cSymbol, String label,List<XYChartItem> lyst,Symbol sym) {
		XYSeries<?> xySeries = XYSeriesBuilder.create()
	             .items(lyst)
	             .chartType(ChartType.SMOOTH_AREA)
	             .fill(Color.TRANSPARENT)
	             .stroke(cArea)
	             .symbolFill(cSymbol)
	             .symbolStroke(Color.web("#393839"))
	             .symbolSize(7)
	             .strokeWidth(2)
	             .symbolsVisible(true)
	             .symbol(sym)
	             .name(label)
	             .build();
		return xySeries;
	}
	
	public void bubbleChartStyle(List<BubbleGridChartItem> chartItems) {
		bubbleGridChart = BubbleGridChartBuilder.create()
                .chartBackground(Color.WHITESMOKE)
                .textColor(Color.BLACK)
                .gridColor(Color.rgb(0, 0, 0, 0.1))
                .showGrid(true)
                .showValues(true)
                .shortenNumbers(false)
                .showPercentage(false)
                .items(chartItems)
                .sortCategoryX(Topic.NAME, Order.DESCENDING)
                .sortCategoryY(Topic.INDEX, Order.ASCENDING)
                .useXCategoryFill()
                .autoBubbleTextColor(true)
                .useGradientFill(true)
                .gradient(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                                             new Stop(0.00, Color.web("#2C67D5")),
                                             new Stop(0.25, Color.web("#00BF6C")),
                                             new Stop(0.50, Color.web("#FFD338")),
                                             new Stop(0.75, Color.web("#FF8235")),
                                             new Stop(1.00, Color.web("#F23C5A"))))
                .build();
	}
	
	public void parallelCoordinatesStyle(List<Server> list) {
		parallelCoordinatesChart = ParallelCoordinatesChartBuilder.create()
                .items(list)
                .tickMarksVisible(true)
                //.smoothConnections(true)
                .axisColor(Color.web("#352e2f"))
                .selectionRectColor(Color.FORESTGREEN)
                .build();
	}
	
	
	
	public void matrixStyle(MatrixItemSeries<MatrixChartItem> series, int rows, int cols) {
		matrixHeatMap = new MatrixPane<>(series);
		int index = 0;
		matrixHeatMap.setMatrixGradient(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                                                            new Stop(0, this.getData().getColors().get(0).getLineColor().get()),
                                                            new Stop(1,  this.getData().getColors().get(1).getLineColor().get()),
                                                            new Stop(2, this.getData().getColors().get(2).getLineColor().get()),
                                                            new Stop(3, this.getData().getColors().get(3).getLineColor().get()),
                                                            new Stop(4,  this.getData().getColors().get(4).getLineColor().get()),
                                                            new Stop(5,  this.getData().getColors().get(5).getLineColor().get()),
                                                            new Stop(6,  this.getData().getColors().get(6).getLineColor().get()),
                                                            new Stop(7, this.getData().getColors().get(7).getLineColor().get()),
                                                            new Stop(8,  this.getData().getColors().get(8).getLineColor().get()),
                                                            new Stop(9,  this.getData().getColors().get(9).getLineColor().get()),
                                                            new Stop(10,  this.getData().getColors().get(10).getLineColor().get())));
		//matrixHeatMap.setColorMapping(ColorMapping.BLACK_BLUE_PURPLE_ORANGE_YELLOW);
		matrixHeatMap.getMatrix().setUseSpacer(false);
		matrixHeatMap.getMatrix().setSquarePixels(false);
		matrixHeatMap.getMatrix().setColsAndRows(rows,cols);
		//matrixHeatMap.
		//matrixHeatMap.setPrefSize(900, 400);
	}
	
	public XYPane addShades(Color colStroke, Color devColor, List<XYSeries> l) {
		XYPane xyPane = new XYPane(l);
		/*xyPane.setAverageStroke(colStroke);
        xyPane.setAverageStrokeWidth(3);
        xyPane.setStdDeviationFill(colStroke);
        xyPane.setStdDeviationStroke(devColor);
        xyPane.setEnvelopeVisible(true);
        //xyPane.setEnvelopeFill(Color.TRANSPARENT);
        xyPane.setEnvelopeFill(colStroke);
        xyPane.setEnvelopeStroke(colStroke);*/
		xyPane.setAverageStroke(Color.rgb(42, 186, 56));
        xyPane.setAverageStrokeWidth(3);
        xyPane.setStdDeviationFill(Color.rgb(42, 186, 56, 0.2));
        xyPane.setStdDeviationStroke(Color.rgb(120, 120, 120));
        xyPane.setEnvelopeVisible(true);
        xyPane.setEnvelopeFill(Color.rgb(42, 186, 56));
        xyPane.setEnvelopeStroke(Color.rgb(42, 186, 56));

        return xyPane;
	}
	
	public void addLegend(String label, Symbol symb, String sColor) {
		LegendItem item = new LegendItem(symb, label, Color.web(sColor), Color.BLACK);
		this.legend.addLegendItem(item);
	}
	
	public void addLegend(String label, Symbol symb, Color color) {
		LegendItem item = new LegendItem(symb, label, color, Color.BLACK);
		this.legend.addLegendItem(item);
	}
	
	public void addXyPane(XYPane xyPane1) {
		xyPanes.add(xyPane1);
	}
	
	public VBox createColorBar(int rows) {
		VBox vb = setVBox();
		int g = 0;
		for (int i = 0; i < rows; i++) {
			Rectangle rec = new Rectangle(40,40);
		    Text l = new Text(String.valueOf(i));
			if (g < this.getData().getColors().size() ) {
				Color c = this.getData().getColors().get(g).getLineColor().get();
				rec.setFill(c);
				rec.setStroke(c);
			} else {
				g = 0;
				Color c = this.getData().getColors().get(g).getLineColor().get();
				rec.setFill(c);
				rec.setStroke(c);
			}
			StackPane stack = new StackPane();
			stack.getChildren().addAll(rec, l);
			vb.getChildren().reversed().add(stack);
			g++;
		}
		return vb;
	}

	public Axis getxAxis() {
		return xAxis;
	}

	public Axis getyAxis() {
		return yAxis;
	}

	public Axis getyAxisRight() {
		return yAxisRight;
	}

	public Grid getChartGrid() {
		return chartGrid;
	}

	public StackPane getChartPane() {
		return chartPane;
	}

	public Legend getLegend() {
		return legend;
	}
	
	public Label getRow() {
		return row;
	}

	public List<XYPane> getXyPanes() {
		return xyPanes;
	}

	public MatrixPane<MatrixChartItem> getMatrixHeatMap() {
		return matrixHeatMap;
	}

	public BubbleGridChart getBubbleGridChart() {
		return bubbleGridChart;
	}

	public ParallelCoordinatesChart getParallelCoordinatesChart() {
		return parallelCoordinatesChart;
	}

	public AnchorPane getPane() {
		return pane;
	}

	public void setPane(AnchorPane pane) {
		this.pane = pane;
	}

	/*public AnchorPane getAxisPane() {
		return pane;
	}*/


}
