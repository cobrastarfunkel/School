package project4;
/**
 * The TalkPackage class is an Object composed of a Map.
 * The Key is the minutes for the plan and the value is the Cost.
 * @author Ian Cobia
 * @version 1.0
 * 
 * COP2253 Project #4
 * File Name: TalkPackage.java
 */
import java.util.Map;
import java.util.TreeMap;

public class TalkPackage {
	// Map tPackage: Integer(Key)=Minutes, Double(value)=Cost
	private Map<Integer, Double> tPackage;
	
	/**
	 * Param Constructor
	 * @param tPackage TreeMap: Integer(Key) Minutes, 
	 * Double(Value) Cost
	 */
	public TalkPackage(TreeMap<Integer, Double> tPackage) {
		this.tPackage = tPackage;
	}

	/**
	 * Returns True if the minutes match a key from a Package
	 * @param sentMinutes Integer: Requested Minutes
	 * @return True if Minutes matches a Key, else False.  This is returned
	 * to the Provider classes getPackage method which returns the package so
	 * it can be added to the Customers Profile.
	 */
	public boolean getMinutes(Integer sentMinutes) {
		for (Integer minutes: tPackage.keySet()) {
			if (minutes.equals(sentMinutes)) {
			return true;
			}
		}
		return false;
	}
	
	/**
	 * Match the Key(Minutes) to a Value(Cost) and return it.
	 * @param minutes int: Key for the Cost
	 * @return Value that matches with the Key(minutes)
	 */
	public double getCost(int minutes) {
		return tPackage.get(minutes);
	}

	/**
	 * Return a Formatted String of the Key Value Pairs
	 */
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		for(Integer minutes: tPackage.keySet()) {
			if(minutes == 0) {
				returnString.append(String.format("-------------------------------%n"
						+ "Minutes:  unlimited%nCost:     $%.2f%n"
						+ "-------------------------------%n", tPackage.get(minutes)));
			} else {
			returnString.append(String.format("-------------------------------%n"
					+ "Minutes:  %d%nCost:     $%.2f%n"
					+ "-------------------------------%n", minutes, tPackage.get(minutes)));
			}
		}
		return returnString.toString();
	}
}
