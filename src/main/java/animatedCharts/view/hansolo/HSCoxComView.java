package animatedCharts.view.hansolo;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.view.AbstractChartView;
import eu.hansolo.fx.charts.CoxcombChart;
import eu.hansolo.fx.charts.CoxcombChartBuilder;
import eu.hansolo.fx.charts.tools.Order;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class HSCoxComView extends AbstractChartView {
	private CoxcombChart chart;
	private Label row;
	
	public HSCoxComView(Model data)  {
		super(data,"",Constants.CSS_SCRIPT_2);
		this.setSceneSize();
		this.setLayout();
		setupChart();
		this.createButtons();
		this.createWindow();
		auxWindow();
		this.addToGrid();
	}

	private void auxWindow() {
		this.getMainPane().getChildren().addAll(this.row,this.chart);
		//this.getGrid().setRowSpan(getMainPane(), 10);
		//this.getMainPane().prefHeight(getSceneHeight()*0.9);
		//this.getMainPane().prefWidth(getSceneWidth()*0.5);
	}

	private void setupChart() {
		chart = CoxcombChartBuilder.create()
                //.items(items)
                .textColor(Color.WHITE)
                //.prefSize(getSceneWidth()*0.8, getSceneHeight()*0.9)
                .autoTextColor(false)
                .useChartItemTextFill(false)
                .equalSegmentAngles(true)
                .order(Order.ASCENDING)
                .showPopup(true)
                .showItemName(true)
                .formatString("%.2f")
                .selectedItemFill(Color.MAGENTA)
                //.padding(Insets.EMPTY)
                .build();

		row = new Label();
		row.getStyleClass().add(Constants.CSS_LABEL);
		//Label row1 = new Label(this.getChartTitle());
		//Label row2 = new Label("Sub title 1");
		//Label row3 = new Label("Sub title 2");
		//chart.autosize();
		//chart.prefHeight(getSceneHeight()*0.5);
		//chart.prefWidth(getSceneWidth()*0.5);
	}

	public Label getRow() {
		return row;
	}

	public CoxcombChart getChart() {
		return chart;
	}

}
