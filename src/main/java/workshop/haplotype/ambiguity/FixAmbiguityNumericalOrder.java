/**
 * This class corrects the order of alleles in ambiguity string
 * HLA-DQB1*05:01:01:02/HLA-DQB1*05:01:01:01/HLA-DQB1*05:01:01:03
 * HLA-DQB1*05:01:01:01/HLA-DQB1*05:01:01:02/HLA-DQB1*05:01:01:03
 */
package workshop.haplotype.ambiguity;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kazutoyo Osoegawa
 * @version May 31 2018
 *
 */
public class FixAmbiguityNumericalOrder {
	private String fixedGl;

	/**
	 * 
	 */
	public FixAmbiguityNumericalOrder(String str) {
		// TODO Auto-generated constructor stub
		fixedGl = "";
		
		if (str.contains("/")) {	// allele ambiguity
			String [] alleleList = str.split("\\/");	// split alleles
			
			int threeDigit = 0;	// first filed digit
			Arrays.sort(alleleList);	// sort alleleList
			for (String allele : alleleList) {	// go through list
				String regex = "HLA-\\w+\\*(\\d+):\\d+.*";
				Pattern pat = Pattern.compile(regex);
				Matcher matcher = pat.matcher(allele);
				if (matcher.find()) {
//					System.out.println(matcher.group(1));	// matcher.group(1) => first field
					if (matcher.group(1).length() == 3) { // three digit, eg. 105
						threeDigit++;
					}
				}				
			}
			
			// three digit 0 or 2 => leave as it is
			if (threeDigit == 1) {	// 1 three digit => check order
				String regex = "HLA-\\w+\\*(\\d+):\\d+.*";
				Pattern pat = Pattern.compile(regex);
				Matcher matcher0 = pat.matcher(alleleList[0]);
				Matcher matcher1 = pat.matcher(alleleList[1]);
				if (matcher0.find() && matcher1.find()) {
					if ((matcher0.group(1).length() == 3) && (matcher1.group(1).length() == 2)) {
						Arrays.sort(alleleList, Collections.reverseOrder());	// 105, 04 => 04, 105
					}	
				}					
			}
			int index = 0;
			for (String allele : alleleList) {
				if (index != 0) {
					fixedGl += "/";
				}
				fixedGl += allele;
				index++;
			}	
		}
		
		else {
			fixedGl = str;
		}				
	}
	
	public String getFixedGl() {
		return fixedGl;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "HLA-DQB1*05:01:01:02/HLA-DQB1*05:01:01:01/HLA-DQB1*05:01:01:03";
		FixAmbiguityNumericalOrder fano = new FixAmbiguityNumericalOrder(str);
		System.out.println(fano.getFixedGl());

	}

}
