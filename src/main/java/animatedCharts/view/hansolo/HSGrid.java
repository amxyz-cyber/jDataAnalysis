package animatedCharts.view.hansolo;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.view.AbstractChartView;


public class HSGrid   extends AbstractChartView {
    
    public HSGrid(Model data, String s) {
    	super(data,s,Constants.CSS_SCRIPT_2);
		this.setSceneSize();
		this.setLayout();
		this.setAxis(0, 50,0,20,"X","Y");
		this.setupGrid(Constants.COLOR_VIOLETTE);
		this.setupChartGrid();
		createButtons();
		createWindow();
		auxWindow();
		this.addToGrid();
	}
    
    
	private void auxWindow(){
		 this.getMainPane().getChildren().addAll(this.getPane() );
	}

	
    
}
