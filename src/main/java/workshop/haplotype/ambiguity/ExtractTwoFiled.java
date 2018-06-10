/**
 * This program truncates allele name to two-field allele name
 */
package workshop.haplotype.ambiguity;

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kazutoyo Osoegawa
 * @version December 31 2015
 *
 */
public class ExtractTwoFiled {
	private Set<String> extractedTwoFieldType;
	
	public ExtractTwoFiled(String type) {
		extractedTwoFieldType = new TreeSet<String>();
		
		String [] typeList = type.split("/");
		for (String str : typeList) {
			
			if (str.contains("N")) {	// null allele
				String regex1 = "(HLA-\\w+\\*\\d+:\\d+).*(N)";
				Pattern pat1 = Pattern.compile(regex1);
				Matcher matcher1 = pat1.matcher(str);
				if(matcher1.find()){
					extractedTwoFieldType.add(matcher1.group(1) + matcher1.group(2));	
				}
			}
			else {
				String regex = "HLA-\\w+\\*\\d+:\\d+";
				Pattern pat = Pattern.compile(regex);
				Matcher matcher = pat.matcher(str);
				if(matcher.find()){
					extractedTwoFieldType.add(matcher.group());
				}
			}

		}
		
	}
	
	public Set<String> getExtractedTwoFieldType() {
		return extractedTwoFieldType;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExtractTwoFiled extracted = new ExtractTwoFiled("HLA-DRB1*04:07:01/HLA-DRB1*04:92");
		for (String str : extracted.getExtractedTwoFieldType()) {
			System.out.println(str);
			
		}
		System.out.println();
		
		ExtractTwoFiled extracted1 = new ExtractTwoFiled("HLA-DQB1*03:03:02:01/HLA-DQB1*03:03:02:02/HLA-DQB1*03:03:02:03");
		for (String str : extracted1.getExtractedTwoFieldType()) {
			System.out.println(str);			
		}
		System.out.println();
		
		ExtractTwoFiled extracted2 = new ExtractTwoFiled("HLA-DRB4*01:03:01:01/HLA-DRB4*01:03:01:02N/HLA-DRB4*01:03:01:03");
		for (String str : extracted2.getExtractedTwoFieldType()) {
			System.out.println(str);			
		}
		System.out.println();
		ExtractTwoFiled extracted3 = new ExtractTwoFiled("HLA-DRB4*01:03:01:01/HLA-DRB4*01:03:01:03");
		for (String str : extracted3.getExtractedTwoFieldType()) {
			System.out.println(str);			
		}
		System.out.println();
		ExtractTwoFiled extracted4 = new ExtractTwoFiled("HLA-DRB4*01:03:01:02N");
		for (String str : extracted4.getExtractedTwoFieldType()) {
			System.out.println(str);			
		}

	}

}
