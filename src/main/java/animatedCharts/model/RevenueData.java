package animatedCharts.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.opencsv.bean.CsvBindByName;

public class RevenueData {
	private Revenue rev = null;
	private Date dDate = null;
	private Calendar cal;
	
	@CsvBindByName(column = Constants.BAR_CHART_Y)
    private int year;

    @CsvBindByName(column = Constants.BAR_CHART_X_PREFIX)
    private int yearlyRevenue;
    
    @CsvBindByName(column = Constants.LABEL_QRT)
    private String sQrt;
    
    @CsvBindByName(column = Constants.LABEL_CPY)
    private String sCpy;

	public int getYear() {
		return year;
	}

	public int getYearlyRevenue() {
		return yearlyRevenue;
	}
	
	public void setCompany() {
		if (sCpy != null || !sCpy.isEmpty()) {
			for (Revenue r : Revenue.values()) {
				if (r.getName().toLowerCase().contains(sCpy.toLowerCase())) {
					this.rev = r;
				}
			}
		}
	}
	
	public Revenue getCompany() {
		return this.rev;
	}
	
	public void setDate() {
		try {
			if (sQrt != null || !sQrt.isEmpty()) {
				this.dDate =new SimpleDateFormat("yyyy-MM-dd").parse(sQrt);
				cal = Calendar.getInstance();
				cal.setTime(dDate);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public Date getDate() {
		return this.dDate;
	}
	
	public Calendar getCal() {
		return this.cal;
	}

}
