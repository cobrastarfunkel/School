package project4;
/**
 * The CellPhone class is an Object composed of a Map.
 * The Key is the model of the phone and the value is the Cost.
 * @author Ian Cobia
 * @version 1.0
 * 
 * COP2253 Project #4
 * File Name: CellPhone.java
 */
import java.util.Map;
import java.util.TreeMap;

public class CellPhone {
	private Map<Integer, Double> cPhone;
	
	/**
	 * Param Constructor
	 * @param cPhone TreeMap: Integer(Key) Model, 
	 * Double(Value) Cost
	 */
	public CellPhone(TreeMap<Integer, Double> cPhone) {
		this.cPhone = cPhone;
	}
	
	/**
	 * Match the requested Phone Model Number
	 * with a phone Model (Key).
	 * @param sentModel Integer: Requested Model(Key)
	 * @return True if Model is matched, else False
	 */
	public boolean getPhone(Integer sentModel) {
		for (Integer model: cPhone.keySet()) {
			if (model.equals(sentModel)) {
			return true;
			}
		}
		return false;
	}
	
	/**
	 * Return the Cost (Value) based on the Model (Key)
	 * @param model int: Model (Key) of the phone
	 * @return double cost:  The Cost(Value) that matches the 
	 * Model (Key)
	 */
	public double getCost(int model) {
		return cPhone.get(model);
	}

	/**
	 * Returns a formatted String of the Key Value Pairs
	 */
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		for(Integer model: cPhone.keySet()) {
			returnString.append(String.format("-------------------------------%n"
					+ "Model:    %d%nCost:     $%.2f%n"
					+ "-------------------------------%n", model, cPhone.get(model)));
		}
		return returnString.toString();
	}
}

