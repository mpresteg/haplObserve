/**
 * This program normalize phase ambiguity from two possibilities if applicable
 */
package workshop.haplotype.ambiguity;

import java.util.Set;
import java.util.TreeSet;

import workshop.haplotype.organize.NonRedundantList;

/**
 * @author kazu
 * @version October 27 2017
 *
 */
public class CompressAmbiguity {
	private String glstring;
	private boolean resolved;

	/**
	 * 
	 */
	public CompressAmbiguity(String str1, String str2) {	// start from two gl string
		// TODO Auto-generated constructor stub
		glstring = "";
		resolved = false;
		
		String [] alleleList1 = str1.split("\\+");	// separate	
		String [] alleleList2 = str2.split("\\+");	// separate	
		NonRedundantList non = new NonRedundantList();	// nonRedundant list
		
		int match = 0;
		for (String type1 : alleleList1) {	// go through allele list1			
			for (String type2 : alleleList2) {		// go through allele list2
				if (type1.equals(type2)) {				// equal, HLA-DRB4*01:03:02
					non.addNonRedundantList(type1);
					match++;
				}
				else if (type1.contains(type2)) {		// contains HLA-DRB4*01:03:01:02N/HLA-DRB4*01:03:02
					non.addNonRedundantList(type1);
					match++;
				}
				else if (type2.contains(type1)) {		// opposite of above
					non.addNonRedundantList(type2);
					match++;
				}
			}	
		}
		// both overlap
		// HLA-DRB1*03:01:01:01/HLA-DRB1*03:01:01:02+HLA-DRB1*12:01:01:01/HLA-DRB1*12:01:01:03/HLA-DRB1*12:10
		// HLA-DRB1*03:01:01:02+HLA-DRB1*12:01:01:01/HLA-DRB1*12:01:01:03
		if (match == 2) {	
			resolved = true;		// resolved
			int index = 0;
			for (String str : non.getList()) {
				if (index != 0) {
					glstring += "+";
				}
				glstring += str;
				index++;
			}
		}
		else {
			if (non.getList().size() > 0) {	// if element is added to non
				resolved = true;		// resolved
				String ambiguity = "";
				Set<String> ambSet = new TreeSet<String>();		// to make allele order in the same way
				Set<String> typeSet = new TreeSet<String>();	// to make same order
				
				for (String sharedType : non.getList()) {
//					System.out.println(sharedType);
					typeSet.add(sharedType);
					for (String type1 : alleleList1) {			
						if (!sharedType.contains(type1)) {	// fix for ordering problem when ambiguity size > 2
							if (type1.contains("/")) {		//HLA-DRB1*15:01:01:01/HLA-DRB1*15:01:01:03
								String [] tmp = type1.split("/");
								for (String str : tmp) {
									ambSet.add(str);
								}
							}
							else {
								ambSet.add(type1);
							}						
						}				
					}
					
					for (String type2 : alleleList2) {
						if (!sharedType.contains(type2)) {
							if (type2.contains("/")) {
								String [] tmp = type2.split("/");
								for (String str : tmp) {
									ambSet.add(str);
								}
							}
							else {
								ambSet.add(type2);
							}						
							
						}				
					}
					// generate ambiguity string
					int trace = 0;
					for (String str : ambSet) {
//						System.out.println(str);
						if (trace != 0) {
							ambiguity += "/";
						}
						ambiguity += str;
						trace++;
					}
					// fix the order of alleles to be consistent
					FixAmbiguityNumericalOrder fano = new FixAmbiguityNumericalOrder(ambiguity);
					ambiguity = fano.getFixedGl();				
//					System.out.println("Ambiguity: " + ambiguity);
				}
				
				typeSet.add(ambiguity);		// order alleles			
				int index = 0;
				for (String type : typeSet) {
					if (index != 0) {
						glstring += "+";
					}
					glstring += type;
					index++;
				}
			}
			else {
				glstring = str1 + "|" + str2;
			}
		}
						
	}
	
	public String getGlString() {
		return glstring;
	}
	
	public boolean getResolved() {
		return resolved;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "HLA-DPB1*02:01:02+HLA-DPB1*13:01:01";
		String str2 = "HLA-DPB1*02:01:02+HLA-DPB1*107:01";
		CompressAmbiguity ca = new CompressAmbiguity(str1, str2);
		System.out.println(ca.getGlString());
		System.out.println(ca.getResolved());
		
		str1 = "HLA-DPB1*04:01:01:01+HLA-DPB1*04:02:01:02";
		str2 = "HLA-DPB1*105:01+HLA-DPB1*126:01";
		CompressAmbiguity ca1 = new CompressAmbiguity(str1, str2);
		System.out.println(ca1.getGlString());
		System.out.println(ca1.getResolved());
				
		str1 = "HLA-DQB1*02:01:01+HLA-DQB1*05:01:01:02";
		str2 = "HLA-DQB1*02:01:01+HLA-DQB1*05:01:01:03";
		CompressAmbiguity ca2 = new CompressAmbiguity(str1, str2);
		System.out.println(ca2.getGlString());
		System.out.println(ca2.getResolved());
		
		str1 = "HLA-DRB4*01:03:01:01+HLA-DRB4*01:03:01:02N";
		str2 = "HLA-DRB4*01:03:01:01+HLA-DRB4*01:03:02";		
		CompressAmbiguity ca3 = new CompressAmbiguity(str1, str2);
		System.out.println(ca3.getGlString());
		System.out.println(ca3.getResolved());
		
		str1 = "HLA-DRB4*01:03:01:01+HLA-DRB4*01:03:01:02N/HLA-DRB4*01:03:02";
		str2 = "HLA-DRB4*01:03:01:03+HLA-DRB4*01:03:01:02N/HLA-DRB4*01:03:02";		
		CompressAmbiguity ca4 = new CompressAmbiguity(str1, str2);
		System.out.println(ca4.getGlString());
		System.out.println(ca4.getResolved());
		
		str1 = "HLA-DRB1*11:01:01:01+HLA-DRB1*15:01:01:03";
		str2 = "HLA-DRB1*11:01:01:01+HLA-DRB1*15:01:01:01";		
		CompressAmbiguity ca5 = new CompressAmbiguity(str1, str2);
		System.out.println(ca5.getGlString());
		System.out.println(ca5.getResolved());
		
		str1 = "HLA-DRB1*11:01:01:01+HLA-DRB1*15:01:01:01/HLA-DRB1*15:01:01:03";
		str2 = "HLA-DRB1*11:01:01:01+HLA-DRB1*15:01:01:02";		
		CompressAmbiguity ca6 = new CompressAmbiguity(str1, str2);
		System.out.println(ca6.getGlString());
		System.out.println(ca6.getResolved());
		
		str1 = "HLA-DRB1*03:01:01:01/HLA-DRB1*03:01:01:02+HLA-DRB1*12:01:01:01/HLA-DRB1*12:01:01:03/HLA-DRB1*12:10";
		str2 = "HLA-DRB1*03:01:01:02+HLA-DRB1*12:01:01:01/HLA-DRB1*12:01:01:03";		
		CompressAmbiguity ca7 = new CompressAmbiguity(str1, str2);
		System.out.println(ca7.getGlString());
		System.out.println(ca7.getResolved());

	}

}
