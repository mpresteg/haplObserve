/**
 * This class prioritize phase ambiguity
 * 
 */
package workshop.haplotype.ambiguity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kazutoyo Osoegawa
 * @version September 27 2017
 *
 */
public class PrioritizePhaseAmbiguity {
	private String orderedAmbiguity;

	/**
	 * 
	 */
	public PrioritizePhaseAmbiguity(String phaseAmbiguity) {
		// TODO Auto-generated constructor stub
		orderedAmbiguity = "";
		
		String [] list = phaseAmbiguity.split("\\|");	// back slash was required
		Arrays.sort(list);
		
		List<String> orderedList = new ArrayList<String>();
		for (String str : list) {		
			List<String> typeList = new ArrayList<String>();
			
			String [] alleleList = str.split("\\+");			
			int threeDigit = 0;
			Arrays.sort(alleleList);
			for (String allele : alleleList) {
				
				typeList.add(allele);
				String regex = "HLA-\\w+\\*(\\d+):\\d+.*";
				Pattern pat = Pattern.compile(regex);
				Matcher matcher = pat.matcher(allele);
				if (matcher.find()) {
//					System.out.println(matcher.group(1));
					if (matcher.group(1).length() == 3) { // three digit
						threeDigit++;
					}
				}				
			}
			
			// three digit 0 or 2 => leave as it is
			if (threeDigit == 1) {	// 1 three digit => check order
				String regex = "HLA-\\w+\\*(\\d+):\\d+.*";
				Pattern pat = Pattern.compile(regex);
				Matcher matcher0 = pat.matcher(typeList.get(0));
				Matcher matcher1 = pat.matcher(typeList.get(1));
				if (matcher0.find() && matcher1.find()) {
					if ((matcher0.group(1).length() == 3) && (matcher1.group(1).length() == 2)) {
						Arrays.sort(alleleList, Collections.reverseOrder());
					}	
				}					
			}	
			// added to fix if the ambiguity is not correctly ordered,
			// HLA-DPB1*107:01/HLA-DPB1*13:01:01
			FixAmbiguityNumericalOrder fano0 = new FixAmbiguityNumericalOrder(alleleList[0]);	// fix the order
			FixAmbiguityNumericalOrder fano1 = new FixAmbiguityNumericalOrder(alleleList[1]);	// fix the order
			orderedList.add(fano0.getFixedGl() + "+" + fano1.getFixedGl());
		}
		
		int count = 0;
		for (String str : orderedList) {
			if (count != 0) {
				orderedAmbiguity += "|";
			}
			orderedAmbiguity += str;
			count++;
		}
	}	
		
	
	public String getOrderedAmbiguity() {
		return orderedAmbiguity;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = 
//		"HLA-DPB1*04:01:01:01+HLA-DPB1*04:02:01:02|HLA-DPB1*105:01+HLA-DPB1*126:01";
//		"HLA-DPB1*105:01+HLA-DPB1*126:01|HLA-DPB1*04:01:01:01+HLA-DPB1*04:02:01:02";
//		"HLA-DPB1*126:01+HLA-DPB1*105:01|HLA-DPB1*04:02:01:02+HLA-DPB1*04:01:01:01";
//		"HLA-DPB1*05:01:01+HLA-DPB1*107:01/HLA-DPB1*13:01:01|HLA-DPB1*135:01+HLA-DPB1*519:01";
		"HLA-DPB1*03:01:01+HLA-DPB1*04:01:01:01|HLA-DPB1*03:01:01+HLA-DPB1*04:01:01:02|HLA-DPB1*124:01+HLA-DPB1*350:01";
		PrioritizePhaseAmbiguity ppa = new PrioritizePhaseAmbiguity(str);
		System.out.println(ppa.getOrderedAmbiguity());

	}

}
