package animatedCharts.model;

public abstract class AbstractItem {
	private String name;
	private double value;
	
	public AbstractItem(String n, double val) {
		this.name = n;
		this.value = val;
	}

	public String getName() {
		return name;
	}

	public double getValue() {
		return value;
	}
	

}
