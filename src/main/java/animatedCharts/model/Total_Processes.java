package animatedCharts.model;


public class Total_Processes {
	private double total_work = 0;
	private double total_q = 0;
	private double total_U = 0;
	private double total_S_sur = 0;
	private double total_S_sys = 0;
	private double total_S = 0;
	private double total_steps = 0;
	
	public Total_Processes() {
		
	}

	public double getTotal_work() {
		return total_work;
	}

	public void addTotal_work(double work) {
		this.total_work += work;
	}

	public double getTotal_q() {
		return total_q;
	}

	public void addTotal_q(double q) {
		this.total_q += q;
	}

	public double getTotal_S_sur() {
		return total_S_sur;
	}

	public void addTotal_S_sur(double S_sur) {
		this.total_S_sur += S_sur;
	}

	public double getTotal_S_sys() {
		return total_S_sys;
	}

	public void addTotal_S_sys(double S_sys) {
		this.total_S_sys += S_sys;
	}

	public double getTotal_S() {
		return total_S;
	}

	public void setTotal_S() {
		this.total_S = this.total_S_sur+this.total_S_sys;
	}

	public double getTotal_steps() {
		return total_steps;
	}

	public void addTotal_steps(double steps) {
		this.total_steps += steps;
	}

	public double getTotal_U() {
		return total_U;
	}

	public void setTotal_U() {
		this.total_U = this.total_q + this.total_work;
	}
	
	
}
