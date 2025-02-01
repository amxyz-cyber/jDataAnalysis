package animatedCharts.controller.hansolo;

import animatedCharts.controller.AbstractChartVC;
import animatedCharts.model.Constants;
import animatedCharts.model.Model;
import animatedCharts.model.MyProcess;
import animatedCharts.model.ProcessData;
import animatedCharts.model.Total_Processes;
import animatedCharts.view.hansolo.HSProcessesView;
import eu.hansolo.fx.charts.data.ChartItem;

public class HSProcessesVC extends AbstractChartVC {
	private HSProcessesView view;
	private Total_Processes total;
	private String[] cols;
	private int numColumns = 6;
	

	public HSProcessesVC(Model data, HSProcessesView v) {
		super(data, v);
		this.view = v;
		this.view.getCancelBtn().setOnAction(e -> btnCancel_Click());
		setupTest1();
	}

	private void setupTest1() {
		this.total = new Total_Processes();
		this.cols = new String[this.numColumns];
		addHeaders();
		this.getProcessesData(Constants.CSV_CARNOT_PROCESS);
		setData();
		auxSeries();
		setupChart();
	}
	
	private void addHeaders() {
		 int i = 0;
		 this.cols[i] = Constants.LABEL_Q;
		 i++;
		 this.cols[i] = Constants.LABEL_WORK;
		 i++;
		 this.cols[i] = Constants.LABEL_U;
		 i++;
		 this.cols[i] = Constants.LABEL_SYSTEM_ENTROPY_S;
		 i++;
		 this.cols[i] = Constants.LABEL_SURROUNDINGS_ENTROPY_S;
		 i++;
		 this.cols[i] = Constants.LABEL_ENTROPY;
	 }

	private void setData() {
		int index = 0;
		int iU = getIndexOfHeader(Constants.LABEL_U);
		int iW = getIndexOfHeader(Constants.LABEL_WORK);
		int iQ = getIndexOfHeader(Constants.LABEL_Q);
		int iS = getIndexOfHeader(Constants.LABEL_ENTROPY);
		int iSys = getIndexOfHeader(Constants.LABEL_SYSTEM_ENTROPY_S);
		int iSur = getIndexOfHeader(Constants.LABEL_SURROUNDINGS_ENTROPY_S);
		for (ProcessData obj: this.getData().getProcesses()) {
			obj.setxValues();
			obj.setProcess();
			obj.setQ(iQ);
			obj.setW(iW);
			obj.setdU(iU);
			obj.setS_sur(iSur);
			obj.setS_sys(iSys);
			obj.setdS(iS);
			this.total.addTotal_q(obj.getQ());
			this.total.addTotal_work(obj.getW());
			this.total.addTotal_S_sur(obj.getS_sur());
			this.total.addTotal_S_sys(obj.getS_sys());
			this.total.addTotal_steps(1);
			String label = (obj.getStep()) + ") "+obj.getProcess().getName();
			this.addChartItem(label, obj.getStep());
		}
		this.total.setTotal_U();
		this.total.setTotal_S();
	}
	
	private int getIndexOfHeader(String s) {
		int g = -1;
		for (int i = 0; i < this.cols.length; i++) {
			if(cols[i].toLowerCase().equals(s.toLowerCase())) {
				g = i;
				break;
			}
		}
		return g;
	}
	
	private void auxSeries() {
		for (int i = 0; i < this.cols.length; i++) {
			//System.out.println("Category:"+cols[i] + " - index:"+i);
			ChartItem xCategory =  createXCategory(cols[i], i);
			for (ProcessData obj: this.getData().getProcesses()) {
				for (MyProcess proc: obj.getxValues()) {
					//System.out.println("Value:"+proc.getValue() + " - process:"+obj.getStep());
					if (i == proc.getIndex()) {
						this.addSeries(xCategory,getlChartItems().get(obj.getStep()-1),proc.getValue());
						//System.out.println("Value:"+proc.getValue() + " - process:"+obj.getStep());
						break;
					}
					
				}
			}
			
			}
		}
	
	public void setupChart() {
		this.view.setupChart(this.getBubbleItems());
		this.view.showChart();
	}
}

