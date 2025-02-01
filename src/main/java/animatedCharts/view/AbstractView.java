package animatedCharts.view;

import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class AbstractView {
	private VBox mainPane;
	private double sceneWidth;
	private double sceneHeight;
	private Button btnClose;
	private HBox paneButtons;
	private Scene scene;
	private GridPane grid;	
	private Model data;
	private String cssStyleSheet;
	
	
	public AbstractView(Model data, String pathCSS) {
		this.data = data;
		this.cssStyleSheet = pathCSS;
	}
	
	public void setSceneSize() {
		this.sceneWidth = this.data.getScreenWidth()*Constants.SIXTY_PERCENT;
		this.sceneHeight = this.data.getScreenHeight()*Constants.THREE_QUARTER;
	}
	
	public void setLayout() {
		this.setGrid();
	    this.setScene();
	    this.setCSS(this.getScene());
	}
	
	public void setGrid() {
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(5);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(85);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(10);
		this.grid = new GridPane();
		this.grid.getColumnConstraints().addAll(col1, col2, col3);
		this.grid.setPadding(new Insets(10));
		this.grid.setHgap(10);
	    this.grid.setVgap(10);
	}
	
	public void setScene() {
		this.scene = new Scene(this.grid,  this.sceneWidth, this.sceneHeight, Color.TRANSPARENT);
	}

	public Scene getScene() {
		return this.scene;
	}
	
	public void setCSS(Scene s) {
		s.getStylesheets().clear();	
		s.getStylesheets().add(getClass().getResource(cssStyleSheet).toExternalForm());
	}
	
	public void createButtons() {
		this.btnClose = new Button("close");
		this.paneButtons = setHBox(Pos.CENTER_LEFT);
		this.paneButtons.setSpacing(5);
		this.paneButtons.getChildren().addAll(this.btnClose);
	}
	
	public static HBox setHBox(Pos p) {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(10));
	    alignHbox(hbox,p);
        return hbox;
	}

	public static VBox setVBox() {
	   //Region spacer = new Region();
	   //VBox.setVgrow(spacer, Priority.ALWAYS);
	   VBox vbox = new VBox(10);
	   vbox.setAlignment(Pos.CENTER);
       vbox.setPadding(new Insets(10));
       return vbox;
	}
	
	public static void alignHbox(HBox box,Pos p) {
		  box.setAlignment(p); 
	} 
	
	public void show(Stage stage, Scene s) {
	    stage.setTitle(this.data.getAppTitle());
	    stage.setScene(s);
	    stage.setResizable(true);
	    stage.initStyle(StageStyle.TRANSPARENT);
	    stage.show();
	 }
	
	public void createWindow(){
		 this.mainPane = setVBox();
		 // insert pie chart + label into a stackpane
		 //this.mainPane.getChildren().addAll(this.chart,this.percentLabel);
		 //this.mainPane.getChildren().addAll(this.chart);
	}
	
	public int auxAddToGrid(int row,int column, Node n) {
		this.grid.add(n,column,row);
		GridPane.setHalignment(n,HPos.CENTER);
		GridPane.setValignment(n,VPos.CENTER);
		GridPane.setHgrow(n,Priority.ALWAYS);
		GridPane.setVgrow(n,Priority.ALWAYS);
		++row;
		return row;
	}
	
	public void addToGrid() {
		int index = 0;
		index = auxAddToGrid(index,1, this.mainPane);
		auxAddToGrid(index,2, this.paneButtons);
	}
	

	public Button getCancelBtn() {
      return this.btnClose;
	}

	public VBox getMainPane() {
		return mainPane;
	}

	public double getSceneWidth() {
		return sceneWidth;
	}

	public double getSceneHeight() {
		return sceneHeight;
	}

	public Button getBtnClose() {
		return btnClose;
	}

	public HBox getPaneButtons() {
		return paneButtons;
	}

	public GridPane getGrid() {
		return grid;
	}

	public Model getData() {
		return data;
	}

}
