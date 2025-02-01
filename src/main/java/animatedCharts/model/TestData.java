package animatedCharts.model;

import com.opencsv.bean.CsvBindByName;

public class TestData {
	private EnumSERVER server = null;
	private double mRAM = 0;
	private double pCpuLoad = 0;
	
	@CsvBindByName(column = Constants.LABEL_SERVER)
    private String sServer;
	
	@CsvBindByName(column = Constants.LABEL_THREADS)
    private int threads;
	
	@CsvBindByName(column = Constants.LABEL_RAM)
    private double dRAM;
	
	@CsvBindByName(column = Constants.LABEL_TX)
    private double throughput;
	
	@CsvBindByName(column = Constants.LABEL_CPU_USAGE)
    private double dCpuLoad;
	
	public void setServer() {
		if (sServer != null || !sServer.isEmpty()) {
			for (EnumSERVER s : EnumSERVER.values()) {
				if (sServer.toLowerCase().contains(s.getName().toLowerCase())) {
					this.server = s;
				}
			}
		}
	}

	public double getmRAM() {
		return mRAM;
	}

	public void setmRAM() {
		this.mRAM = dRAM / Constants.BYTE_CONVERSION;
	}

	public double getpCpuLoad() {
		return pCpuLoad;
	}

	public void setpCpuLoad() {
		this.pCpuLoad = dCpuLoad*Constants.PERCENTAGE_CONVERSION;
	}

	public EnumSERVER getServer() {
		return server;
	}

	public int getThreads() {
		return threads;
	}

	public double getThroughput() {
		return throughput;
	}
	

}

