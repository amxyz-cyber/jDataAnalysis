package animatedCharts.model;

public class MyProcess extends AbstractItem {
	private int index;
	
	public MyProcess(String n, double val, int i) {
		super(n, val);
		this.index = i;
	}
	
	public int getIndex() {
		return index;
	}

}
