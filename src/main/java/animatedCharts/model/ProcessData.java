package animatedCharts.model;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

public class ProcessData {
	private Process process;
	private Double dU;
	private Double dS;
	private List <MyProcess> xValues;
	
	@CsvBindByName(column = Constants.LABEL_STEP)
    private Integer step;
	
	@CsvBindByName(column = Constants.LABEL_Q)
    private Double q;
	
	@CsvBindByName(column = Constants.LABEL_WORK)
    private Double w;
	
	@CsvBindByName(column = Constants.LABEL_SYSTEM_ENTROPY)
    private Double s_sys;
	
	@CsvBindByName(column = Constants.LABEL_SURROUNDINGS_ENTROPY)
    private Double s_sur;
	
	@CsvBindByName(column = Constants.LABEL_PROCESS)
    private String sProcess;
	
	public void setProcess() {
		if (sProcess != null || !sProcess.isEmpty()) {
			for (Process p : Process.values()) {
				if (p.getName().toLowerCase().contains(sProcess.toLowerCase())) {
					this.process = p;
				}
			}
		}
	}
	
	public Process getProcess() {
		return process;
	}

	public Double getdU() {
		return dU;
	}

	public void setdU(int i) {
		this.dU = this.q + this.w;
		MyProcess dx = new MyProcess(Constants.LABEL_U,dU,i);
		this.xValues.add(dx);
	}

	public Double getdS() {
		return dS;
	}

	public void setdS(int i) {
		this.dS = this.s_sur + this.s_sys;
		MyProcess dx = new MyProcess(Constants.LABEL_ENTROPY,dS,i);
		this.xValues.add(dx);
	}

	public Integer getStep() {
		return step;
	}

	public Double getQ() {
		return q;
	}

	public Double getW() {
		return w;
	}

	public Double getS_sys() {
		return s_sys;
	}

	public Double getS_sur() {
		return s_sur;
	}

	public void setQ(int i) {
		MyProcess dx = new MyProcess(Constants.LABEL_Q,q,i);
		this.xValues.add(dx);
	}

	public void setW(int i) {
		MyProcess dx = new MyProcess(Constants.LABEL_WORK,w,i);
		this.xValues.add(dx);
	}

	public void setS_sys(int i) {
		MyProcess dx = new MyProcess(Constants.LABEL_SYSTEM_ENTROPY_S,s_sys,i);
		this.xValues.add(dx);
	}

	public void setS_sur(int i) {
		MyProcess dx = new MyProcess(Constants.LABEL_SURROUNDINGS_ENTROPY_S,s_sur,i);
		this.xValues.add(dx);
	}

	public List<MyProcess> getxValues() {
		return xValues;
	}

	public void setxValues() {
		this.xValues = new ArrayList<>();
	}

}
