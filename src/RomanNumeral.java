import java.util.ArrayList;

public class RomanNumeral {
	private String romanNumeral;
	private int arabicValue;
	//two private instance variables 
	
	public RomanNumeral(String rn) { //one argument constructor:takes a String as a parameter for the Roman Numeral
		romanNumeral= rn;
		/**
		 * for loop goes through Roman Numeral characters identifying all
		 * different key data stored inside RomanNumeral 
		 */
		for(int i=0; i<romanNumeral.length(); i++) {
			if(romanNumeral.charAt(i)== 'M' ||
					romanNumeral.charAt(i)== 'D' ||
					romanNumeral.charAt(i)== 'C'||
					romanNumeral.charAt(i)== 'L'||
					romanNumeral.charAt(i)== 'X'||
					romanNumeral.charAt(i)=='V'||
					romanNumeral.charAt(i)=='I');
			else 
				//if it does not match with one of the Roman characters listed above a
				//string exception will be thrown 
				 System.out.println(new 
						 IllegalRomanNumeralException("Invalid Character"));
			}
		arabicValue= romantoArabic(rn); 
		 
			
		//converts the Roman numeral to Arabic and sets the Arabic integer value 
	}
	/**
	 * 
	 * @param roman : specific Roman Characters 
	 * @returns conversation of what each Roman Numeral represents in Arabic value 
	 */
	public int value(char roman) { 
		if (roman =='M') return 1000;
		else if(roman=='D') return 500;
		else if(roman == 'C') return 100;
		else if(roman == 'L') return 50;
		else if(roman == 'X') return 10;
		else if(roman == 'V') return 5;
		else if(roman == 'I') return 1;
		else return 0;
	}	
	public int romantoArabic(String n){
			//for length of Roman numeral
			int len = n.length();
			//set the result to the last character in the word 
			int result= value(n.charAt(len-1));
			//loop from the second to last character in the word to the first 
			for(int i= len-2; i>=0; i--) {
				//if the value before it is greater than the value after it 
				if(value(n.charAt(i)) >= value(n.charAt(i+1)))
					//then we add that value to the result 
				result += value(n.charAt(i));
				//otherwise subtract the value from the result 
				else 
					result -= value(n.charAt(i));
			}
			return result; 
	}
	
	/**
	 *  get set method for access to arabicValue and romanNumeral
	 */
	public String getRomanNumeral() {
		return romanNumeral;
	}
	public int getArabicValue() {
		return arabicValue;
	}
	public void setRomanNumeral(String a) {
		romanNumeral=a;
	}
	public void setArabicValue(int b) {
		arabicValue= b;

	}
	/**
	 * equals method is comparing two Roman numerals by their Arabic values
	 */
	public boolean equals(RomanNumeral other) { 
		return this.arabicValue== other.getArabicValue(); //getArabicValue used because its private 	
	}
	
	@Override
	public String toString() {
		return this.romanNumeral+" : "+this.arabicValue;
		//Not used in code: used for debugging
	}
	public String toStringGUI() {
		return this.romanNumeral+"\n";
		// Used for printing the left pane on the GUI 
	}
	/**
	 * CompareTo method Takes in a Roman Numeral obj and decides 1 of 3 cases
	 * <ul>
	 * <li> They are equal return 0
	 * <li> Left is smaller then the right side return <0
	 * <li> Left is greater then the right side return >0
	 * </ul>
	 * 
	 * @return the difference in Arabic value 
	 * */
	public int compareTo(RomanNumeral other) { //romanNumeral other is what is being brought to compare 
		return this.arabicValue-other.getArabicValue(); //used to find the difference to sort with proper placement 
	}
}

