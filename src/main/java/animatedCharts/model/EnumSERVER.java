package animatedCharts.model;

public enum EnumSERVER {
	HASKELL("Haskell","GHC-9.6.3"),
	JAVA("Java","Openjdk-20.0.2"),
	PYTHON("Python","Python-3.12.1");

    private final String name;
    private final String compiler;

    EnumSERVER(String name,String c) {
        this.name = name;
        this.compiler = c;
    }
    
    public String getName() {
        return this.name;
    }

	public String getCompiler() {
		return compiler;
	}

}
