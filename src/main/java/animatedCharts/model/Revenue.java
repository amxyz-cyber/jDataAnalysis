/*
 * MonitorState.java
 */

package animatedCharts.model;

public enum Revenue {

	DUPONT("DuPont De Nemours","USD"),
	BASF("BASF SE","USD"),
	ENI("Eni SpA","USD");

	private final String name;
	private final String currency;

    Revenue(String name,String c) {
        this.name = name;
        this.currency = c;
    }
    
   public String getName() {
        return this.name;
    }
   
   public String getCurrency() {
       return this.currency;
   }

}

