package animatedCharts.model;

import java.util.HashMap;
import java.util.Map;

import eu.hansolo.fx.charts.data.ChartItem;
import eu.hansolo.fx.charts.data.DataObject;
import eu.hansolo.fx.charts.tools.Helper;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Server implements DataObject {
	 private final String NAME;
	 private       String                 compiler;
	 private	   TestData				  sample;
	 private       ChartItem              ram;
     private       ChartItem              cpuUsage;
     private       ChartItem              threads;
     private       ChartItem              throughput;
     private       Paint                  fill;
     private       Color                  stroke;
     private       Map<String, ChartItem> properties;


     public Server(TestData sample, Color strokeColor) {
    	 this.sample = sample;
    	 compiler  = sample.getServer().getCompiler();
         this.NAME    = Model.createHeadline(this.sample.getServer().getName(), compiler);
         ram     = new ChartItem(Constants.LABEL_RAM.toUpperCase());
         cpuUsage        = new ChartItem(Constants.HEADER_CPU_USAGE.toUpperCase());
         threads = new ChartItem(Constants.LABEL_THREADS.toUpperCase());
         throughput       = new ChartItem(Constants.LABEL_TX.toUpperCase());

         properties = new HashMap<>();
         properties.put(ram.getName(), ram);
         properties.put(cpuUsage.getName(), cpuUsage);
         properties.put(threads.getName(), threads);
         properties.put(throughput.getName(), throughput);

         fill   = Color.TRANSPARENT;
         stroke = strokeColor;
         setValues();
     }
    
    public void setValues() {
    	this.setRam(sample.getmRAM(), Constants.LABEL_MB);
    	this.setCpuUsage(sample.getpCpuLoad(), Constants.LABEL_PERCENTAGE);
    	this.setThreads(sample.getThreads());
    	this.setThroughput(sample.getThroughput(),Constants.LABEL_MBS);
    }

	public String getName() {
		return NAME;
	}

	public Paint getFill() {
		return fill;
	}

	public void setFill(Paint FILL) {
		this.fill = FILL;
	}

	public Color getStroke() {
		return this.stroke;
	}

	public void setStroke(Color STROKE) {
		this.stroke = STROKE;
	}

	public Map<String, ChartItem> getProperties() {
		return properties;
	}

	public double getRam() {
		return ram.getValue();
	}

	public void setRam(double value,String sUnit) {
		ram.setValue((int) Helper.clamp(0, Double.MAX_VALUE, value));
		ram.setUnit(sUnit);
	}

	public double getCpuUsage() {
		return cpuUsage.getValue();
	}

	public void setCpuUsage(double dCpuUsage,String sUnit) {
		cpuUsage.setValue(Helper.clamp(0, Double.MAX_VALUE, dCpuUsage));
		cpuUsage.setUnit(sUnit);
	}

	public double getThreads() {
		return threads.getValue();
	}

	public void setThreads(double threads) {
		this.threads.setValue((int) Helper.clamp(0, Double.MAX_VALUE, threads));
	}

	public double getThroughput() {
		return throughput.getValue();
	}

	public void setThroughput(double dThroughput,String sUnit) {
		throughput.setValue(Helper.clamp(0, Double.MAX_VALUE, dThroughput));
		throughput.setUnit(sUnit);
	}

	public String getCompiler() {
		return compiler;
	}


}
