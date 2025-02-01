package animatedCharts.model;

import com.opencsv.bean.CsvBindByName;

public class WineData {
	
	@CsvBindByName(column = Constants.FIXED_ACID )
    private double dFixedAcidity;
	
	@CsvBindByName(column = Constants.VOL_ACID )
    private double dVolatileAcidity;
	
	@CsvBindByName(column = Constants.CITRIC_ACID )
    private double dCitricAcid;
	
	@CsvBindByName(column = Constants.RESID_SUGAR )
    private double dSugar;
	
	@CsvBindByName(column = Constants.CHLORIDES )
    private double dCl;
	
	@CsvBindByName(column = Constants.FREE_SO2 )
    private double dSO2;

	@CsvBindByName(column = Constants.TOTAL_SO2 )
    private double dTotalSO2;
	
	@CsvBindByName(column = Constants.DENSITY )
    private double dDensity;
	
	@CsvBindByName(column = Constants.PH )
    private double dPH;
	
	@CsvBindByName(column = Constants.SO4 )
    private double dSO4;
	
	@CsvBindByName(column = Constants.ALCOHOL )
    private double dAlc;
	
	@CsvBindByName(column = Constants.QUALITY )
    private double dQty;

	public double getdFixedAcidity() {
		return dFixedAcidity;
	}

	public double getdVolatileAcidity() {
		return dVolatileAcidity;
	}

	public double getdCitricAcid() {
		return dCitricAcid;
	}

	public double getdSugar() {
		return dSugar;
	}

	public double getdCl() {
		return dCl;
	}

	public double getdSO2() {
		return dSO2;
	}

	public double getdTotalSO2() {
		return dTotalSO2;
	}

	public double getdDensity() {
		return dDensity;
	}

	public double getdPH() {
		return dPH;
	}

	public double getdSO4() {
		return dSO4;
	}

	public double getdAlc() {
		return dAlc;
	}

	public double getdQty() {
		return dQty;
	}
	
	
	public double getValue(String attribute) {
		double value = 0;
		
		switch(attribute) {
			case Constants.FIXED_ACID:
				value = this.dFixedAcidity;
				break;
			case Constants.VOL_ACID:
				value = this.dVolatileAcidity;
				break;
			case Constants.CITRIC_ACID:
				value = this.dCitricAcid;
				break;
			case Constants.RESID_SUGAR:
				value = this.dSugar;
				break;
			case Constants.FREE_SO2:
				value = this.dSO2;
				break;
			case Constants.TOTAL_SO2:
				value = this.dTotalSO2;
				break;
			case Constants.DENSITY :
				value = this.dDensity;
				break;
			case Constants.PH:
				value = this.dPH;
				break;
			case Constants.SO4:
				value = this.dSO4;
				break;
			case Constants.CHLORIDES :
				value = this.dCl;
				break;
			case Constants.ALCOHOL:
				value = this.dAlc;
				break;
			default:
				value = this.dQty;
				break;
		}
		
		return value;
		
	}
	
}
