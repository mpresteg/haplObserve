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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ambiguity1 = "HLA-DPB1*04:02:01:02/HLA-DPB1*105:01/HLA-DPB1*105:01i1";
		RemoveNotation rn1 = new RemoveNotation(ambiguity1);
		System.out.println(rn1.getFixed());
		System.out.println(rn1.getXkept());
		
		String ambiguity2 = "HLA-DPB1*13:01:01e1/HLA-DPB1*107:01";
		RemoveNotation rn2 = new RemoveNotation(ambiguity2);
		System.out.println(rn2.getFixed());
		System.out.println(rn2.getXkept());
		
		String fake = "HLA-DPB1*13:01:01/HLA-DPB1*13:01:01e1/HLA-DPB1*133:01/HLA-DPB1*04:02:01:02/HLA-DPB1*105:01/HLA-DPB1*105:01i1";
		RemoveNotation rn3 = new RemoveNotation(fake);
		System.out.println(rn3.getFixed());
		System.out.println(rn3.getXkept());
		
		String test = "HLA-B*14:02:01:01+HLA-B*35:02:01e1";
		RemoveNotation rn4 = new RemoveNotation(test);
		System.out.println(rn4.getFixed());
		System.out.println(rn4.getXkept());
		
		String test5 = "HLA-B*14:02:01:01+HLA-B*35:02:01x1";
		RemoveNotation rn5 = new RemoveNotation(test5);
		System.out.println(rn5.getFixed());
		System.out.println(rn5.getXkept());
		
		String test6 = "HLA-DPB1*04:01:01:01+HLA-DPB1*04:02:01:02|HLA-DPB1*105:01+HLA-DPB1*126:01|HLA-DPB1*105:01i1+HLA-DPB1*126:01";
		RemoveNotation rn6 = new RemoveNotation(test6);
		System.out.println(rn6.getFixed());
		System.out.println(rn6.getXkept());

	}

}
