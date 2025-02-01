package animatedCharts.controller.hansolo;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.view.hansolo.HSGrid;

public class HSGridVC {
	 private HSGrid view;
	 private Model data;
	 
	 
	 public HSGridVC(Model data) {
		 this.data = data;	
		 this.view = new HSGrid(this.data,Constants.GRID_DEMO);
		 this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
	 }
	 
	 public void btnCancel_Click() 
	 {
	 	this.data.getPrimaryStage().close();
	 }
	 
	 public void show(){
	      this.view.show(this.data.getPrimaryStage(),this.view.getScene());
	   }
	   
	 public HSGrid getView() {
		return this.view;
	 }

}
