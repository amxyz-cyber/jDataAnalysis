package animatedCharts.model;

import eu.hansolo.fx.charts.Symbol;
import eu.hansolo.fx.charts.color.MaterialDesignColors;

public class Colors {
	private MaterialDesignColors lineColor;
	private MaterialDesignColors areaColor;
	private Symbol symbol;
	private MaterialDesignColors[] shades;
	
	/*public Colors (MaterialDesignColors color1, MaterialDesignColors color2) {
		this.lineColor = color1;
		this.areaColor = color2;
	}*/
	
	public Colors (MaterialDesignColors color1, MaterialDesignColors color2,Symbol sym) {
		this.lineColor = color1;
		this.areaColor = color2;
		this.symbol = sym;
		this.shades = new MaterialDesignColors[0];
	}
	
	public Colors (MaterialDesignColors color1, MaterialDesignColors color2,MaterialDesignColors[] mx,Symbol sym) {
		this.lineColor = color1;
		this.areaColor = color2;
		this.symbol = sym;
		this.shades = mx;
	}

	public MaterialDesignColors getLineColor() {
		return lineColor;
	}

	public MaterialDesignColors getAreaColor() {
		return areaColor;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public MaterialDesignColors[] getShades() {
		return shades;
	}

}
