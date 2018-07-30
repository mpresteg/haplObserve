/**
 * This program truncates allele name to two-field allele name
 * fixed typo in the original name ExtractTwoFiled => ExtractTwoField
 */
package workshop.haplotype.ambiguity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import workshop.haplotype.organize.NonRedundantList;

/**
 * @author Kazutoyo Osoegawa
 * @version June 27 2018
 *
 */
public class ExtractTwoField {
	private NonRedundantList extractedTwoFieldType;

	/**
	 * 
	 */
	public ExtractTwoField(String type) {
		// TODO Auto-generated constructor stub
		extractedTwoFieldType = new NonRedundantList();
		
		FixAmbiguityNumericalOrder fano = new FixAmbiguityNumericalOrder(type);
		
		String [] typeList = fano.getFixedGl().split("/");
		for (String str : typeList) {			
			if (str.contains("N")) {	// null allele
				String regex1 = "(HLA-\\w+\\*\\d+:\\d+).*(N)";
				Pattern pat1 = Pattern.compile(regex1);
				Matcher matcher1 = pat1.matcher(str);
				if(matcher1.find()){
					extractedTwoFieldType.addNonRedundantList(matcher1.group(1) + matcher1.group(2));
				}
			}
			else {
				String regex = "HLA-\\w+\\*\\d+:\\d+";
				Pattern pat = Pattern.compile(regex);
				Matcher matcher = pat.matcher(str);
				if(matcher.find()){
					extractedTwoFieldType.addNonRedundantList(matcher.group());
				}
			}

		}
	}
	
	public NonRedundantList getExtractedTwoFieldType() {
		return extractedTwoFieldType;
	}

}
