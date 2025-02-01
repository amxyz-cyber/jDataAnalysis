package animatedCharts.model;
import com.opencsv.bean.CsvBindByName;

public class OsData {
	@CsvBindByName(column = Constants.ANDROID_OS)
    private Double androidOS;

    @CsvBindByName(column = Constants.WIN_OS)
    private Double windowsOS;

    @CsvBindByName(column = Constants.I_OS)
    private Double iOS;
    
    @CsvBindByName(column = Constants.X_OS)
    private Double xOS;

    @CsvBindByName(column = Constants.UNK_OS)
    private Double unkOS;

    @CsvBindByName(column = Constants.CHROME_OS)
    private Double chromeOS;
    
    @CsvBindByName(column = Constants.LINUX_OS)
    private Double linuxOS;

    @CsvBindByName(column = Constants.SAMSUNG_OS)
    private Double samsungOS;

    @CsvBindByName(column = Constants.XBOX_OS)
    private Double xboxOS;
    
    @CsvBindByName(column = Constants.KAI_OS)
    private Double kaiOS;

    @CsvBindByName(column = Constants.PLAYSTATION_OS)
    private Double playOS;

    @CsvBindByName(column = Constants.OTHER_OS)
    private Double other;

    @CsvBindByName
    private String date;

	public Double getAndroidOS() {
		return androidOS;
	}

	public Double getWindowsOS() {
		return windowsOS;
	}

	public Double getiOS() {
		return iOS;
	}

	public Double getxOS() {
		return xOS;
	}

	public Double getUnkOS() {
		return unkOS;
	}

	public Double getChromeOS() {
		return chromeOS;
	}

	public Double getLinuxOS() {
		return linuxOS;
	}

	public Double getSamsungOS() {
		return samsungOS;
	}

	public Double getXboxOS() {
		return xboxOS;
	}

	public Double getKaiOS() {
		return kaiOS;
	}

	public Double getPlayOS() {
		return playOS;
	}

	public Double getOther() {
		return other;
	}

	public String getDate() {
		return date;
	}

	
}
