import java.util.Comparator;

//implements key from treeMap "RomanNumeral"
//Comparator object informs TreeMap on how Roman Numerals are compared
public class RomanNumeralComparator implements Comparator<RomanNumeral> {

	@Override
	public int compare(RomanNumeral rn1, RomanNumeral rn2) { //compares int values 
		return rn1.compareTo(rn2); //returns the result of comparison 
	}
}
