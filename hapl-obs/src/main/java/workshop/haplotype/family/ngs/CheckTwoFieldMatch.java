/**
 * This program checks two-filed allele name identity
 */
package workshop.haplotype.family.ngs;

import workshop.haplotype.ambiguity.ExtractTwoField;

/**
 * @author Kazutoyo Osoegawa
 * @version April 27 2016
 *
 */
public class CheckTwoFieldMatch {
	private boolean matched;

	/**
	 * 
	 */
	public CheckTwoFieldMatch(String str1, String str2) {
		// TODO Auto-generated constructor stub
		matched = false;
		ExtractTwoField extracted1 = new ExtractTwoField(str1);
		ExtractTwoField extracted2 = new ExtractTwoField(str2);
		int matchedCount = 0;
		for (String type1 : extracted1.getExtractedTwoFieldType().getList()) {
			for (String type2 : extracted2.getExtractedTwoFieldType().getList()) {
				if (type1.equals(type2)) {
					matchedCount++;
				}
			}			
		}
		if (matchedCount > 0) {
			matched = true;
		}
	}
	
	public boolean getResult() {
		return matched;
	}
}
