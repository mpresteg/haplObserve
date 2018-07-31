/**
 * 
 */
package workshop.haplotype.sirona;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kazutoyo
 * @version March 21 2016
 *
 */
public class RemoveNotation {
	private String fixed;
	private String xKept;

	/**
	 * 
	 */
	public RemoveNotation(String ambiguity) {			
		// TODO Auto-generated constructor stub
		
		String regx = "[a-z][0-9]*@*"; // regular expression for e, i and x
		fixed = ambiguity;
		xKept = ambiguity;
		boolean test = true;
		
		while (test) {
			Pattern pattern = Pattern.compile(regx);	// handle e, i and x
			Matcher matcher = pattern.matcher(fixed);
			
			if (matcher.find()) {
				fixed = fixed.replace(matcher.group(0), "");	
			}
			else {
				test = false;
			}
		}
		
		String regx1 = "[a-wyz][0-9]*@*"; // regular expression for x
		test = true;
		while (test) {
			Pattern pattern = Pattern.compile(regx1);	// handle e, i and x
			Matcher matcher = pattern.matcher(xKept);
			
			if (matcher.find()) {
				xKept = xKept.replace(matcher.group(0), "");	
			}
			else {
				test = false;
			}
		}
		
	}
	
	public String getFixed() {
		return fixed;
	}
	
	public String getXkept() {
		return xKept;
	}


}
