package animatedCharts.model;

import com.opencsv.bean.CsvBindByName;

public class BalancesData {
	
	@CsvBindByName(column = "January" )
    private double month01;
	
	@CsvBindByName(column = "February" )
    private double month02;
	
	@CsvBindByName(column = "March" )
    private double month03;
	
	@CsvBindByName(column = "April" )
    private double month04;
	
	@CsvBindByName(column = "May" )
    private double month05;
	
	@CsvBindByName(column = "June" )
    private double month06;

	@CsvBindByName(column = "July" )
    private double month07;
	
	@CsvBindByName(column = "August" )
    private double month08;
	
	@CsvBindByName(column = "September" )
    private double month09;
	
	@CsvBindByName(column = "October" )
    private double month10;
	
	@CsvBindByName(column = "November" )
    private double month11;
	
	@CsvBindByName(column = "December" )
    private double month12;

	public double getMonth01() {
		return month01;
	}

	public double getMonth02() {
		return month02;
	}

	public double getMonth03() {
		return month03;
	}

	public double getMonth04() {
		return month04;
	}

	public double getMonth05() {
		return month05;
	}

	public double getMonth06() {
		return month06;
	}

	public double getMonth07() {
		return month07;
	}

	public double getMonth08() {
		return month08;
	}

	public double getMonth09() {
		return month09;
	}

	public double getMonth10() {
		return month10;
	}

	public double getMonth11() {
		return month11;
	}

	public double getMonth12() {
		return month12;
	}
	
	public double getValue(int iMonth) {
		double value = 0;
		
		switch(iMonth) {
			case 0:
				value = this.month01;
				break;
			case 1:
				value = this.month02;
				break;
			case 2:
				value = this.month03;
				break;
			case 3:
				value = this.month04;
				break;
			case 4:
				value = this.month05;
				break;
			case 5:
				value = this.month05;
				break;
			case 6:
				value = this.month07;
				break;
			case 7:
				value = this.month08;
				break;
			case 8:
				value = this.month09;
				break;
			case 9:
				value = this.month10;
				break;
			case 10:
				value = this.month11;
				break;
			default:
				value = this.month12;
				break;
			
		}
		
		return value;
	}
	
}
