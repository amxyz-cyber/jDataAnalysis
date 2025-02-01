package animatedCharts.controller.hansolo;

import java.util.Random;

import animatedCharts.controller.AbstractChartVC;
import animatedCharts.model.Colors;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.Server;
import animatedCharts.model.TestData;
import animatedCharts.view.hansolo.HSServerTestView;
import javafx.scene.paint.Color;

public class HSServerTestVC  extends AbstractChartVC{
	private HSServerTestView view;

	public HSServerTestVC(Model data, HSServerTestView v) {
		super(data, v);
		this.view = v;
		this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		setupTest1();
	}

	private void setupTest1() {
		this.getTestData(Constants.CSV_SERVER_TESTS);
		setData();
		setupChart();
		
	}
	
	private void setData() {
		int iSize = this.getData().getColors().size();
		int i = 0;
		Random random = new Random();
		for (TestData obj: this.getData().getServerTests()  ) { 
			obj.setServer();
			obj.setmRAM();
			obj.setpCpuLoad();
			i = random.nextInt(iSize-1);
				Colors cx = this.getData().getColors().get(i);
				Color lineColor = cx.getLineColor().get();
				Server server = new Server(obj, lineColor);
				this.getData().getServers().add(server);
			}
		}
	
	public void setupChart() {
		this.view.setupChart(this.getData().getServers());
		this.view.showChart();
	}


}
