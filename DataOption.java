package project4;
/**
 * The DataOption class is an Object composed of a Map.
 * The Key is the plan size and the value is the Cost.
 * @author Ian Cobia
 * @version 1.0
 * 
 * COP2253 Project #4
 * File Name: DataOption.java
 */
import java.util.Map;
import java.util.TreeMap;

public class DataOption {
	private Map<String, Double> dOption;
	
	/**
	 * Param Constructor
	 * @param dOption TreeMap: String(Key) plan size, 
	 * Double(Value) Cost
	 */
	public DataOption(TreeMap<String, Double> dOption) {
		this.dOption = dOption;
	}
	
	/**
	 * Match the requested plan size
	 * with a DataOption plan size (Key).
	 * @param sentData String: Requested plan size(Key)
	 * @return True if plan is matched, else False
	 */
	public boolean getDataOption(String sentData) {
		for (String dataPlan: dOption.keySet()) {
			if (dataPlan.equalsIgnoreCase(sentData)) {
			return true;
			}
		}
		return false;
	}
	
	/**
	 * Return the Cost (Value) based on the plan size (Key)
	 * @param sentPlan String: Model (Key) of the phone
	 * @return double cost:  The Cost(Value) that matches the 
	 * plan size (Key)
	 */
	public double getCost(String sentPlan) {
		for (String dataPlan: dOption.keySet()) {
			if (dataPlan.equalsIgnoreCase(sentPlan)) {
			return dOption.get(dataPlan);
			}
		}
		return 0.0;
	}

	/**
	 * Returns a formatted String of the Key Value Pairs
	 */
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		for(String data: dOption.keySet()) {
			returnString.append(String.format("-------------------------------%n"
					+ "Data:     %s%nCost:     $%.2f%n"
					+ "-------------------------------%n", data, dOption.get(data)));
		}
		return returnString.toString();
	}
}

