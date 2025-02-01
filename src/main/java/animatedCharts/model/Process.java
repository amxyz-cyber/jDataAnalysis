package animatedCharts.model;

public enum Process {
	ISOCHORIC("isochoric"),
	ISOBARIC("isobaric"),
	ISOTHERMAL("isothermal"),
	ADIABATIC("adiabatic");

    private final String name;

    Process(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
