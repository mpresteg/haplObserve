/**
 * This class was built to generate ambiguity string
 */
package workshop.haplotype.ambiguity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kazu
 * @version October 16 2017
 *
 */
public class AmbiguityList extends AmbiguityListAbstract{

	/**
	 * 
	 */
	public AmbiguityList() {
		super();
		// TODO Auto-generated constructor stub
		ambiguityList.addAll(convertToAmbiguityList(alleleList));
	}
	
	@Override
	public List<String> convertToAmbiguityList(List<List<String>> ambiguityList) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		for (List<String> tmplist : ambiguityList) {
			String ambiguityType = "";
			int count = 0;
			for (String type : tmplist) {
				if (count > 0) {
					ambiguityType += "/";
				}
				ambiguityType += type;				
				count++;
			}
			list.add(ambiguityType);
		}
		return list;
	}

	@Override
	public void convertToAmbiguityString(String type) {
		converted = "";
		for (String str : ambiguityList) {
			if (str.contains(type)) {
//			if (type.contains(str)) {	
				converted = str;
				break;
			}
		}	
		if (converted.length() == 0) {
			converted = type;
		}
		if (converted.contains("HLA-DRB3*01:01:02:01")) {
			converted = "HLA-DRB3*01:01:02:01";
		}
		
		// fixed here
		// type: "HLA-DQA1*01:02:01:01/HLA-DQA1*01:02:01:03"
		boolean fieldmatch = false;
		if (converted.length() > 0) {
			for (List<String> tmpList : alleleList) {
				for (String str : tmpList) {
					String [] list = type.split("/");	// if type.contains("/)					
					for (String allele : list) {
						if (allele.equals(str)) {
							fieldmatch = true;
						}
					}					
				}
			}
			if (!fieldmatch) {
				converted = type;
			}
		}		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AmbiguityList amList = new AmbiguityList();
	for (String str : amList.getAmbiguityList()) {
			System.out.println(str);
		}
		
		System.out.println();
		amList.convertToAmbiguityString("HLA-DRB1*15:01:01:01");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DRB3*02:02:01:02v1");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-A*01:01:01:01");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DPB1*13:01:01");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DPB1*02:01:19");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DRB1*03:01:01:01");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DQA1*01:02:01:01/HLA-DQA1*01:02:01:03");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DPB1*04:01:01:01/HLA-DPB1*04:01:01:02");
		System.out.println(amList.getConverted());

	}

}
