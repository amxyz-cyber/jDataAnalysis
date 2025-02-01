package animatedCharts.model;

import com.opencsv.bean.CsvBindByName;

public class BikeSharingData {
	 
	@CsvBindByName(column = Constants.LABEL_BEGIN_HOUR)
	    private int hour;

		public int getHour() {
			return hour;
		}
}
