/**
 * This program is developed to reduce the code in OrganizeParentHapTypeDRBlinkage
 */
package workshop.haplotype.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.family.organize.OrganizeParentHapType;
import workshop.haplotype.gene.DRBgenes;
import workshop.haplotype.gene.ExpectedDRB345Count;

/**
 * @author kazutoyo
 * @version March 2 2017
 *
 */
public class EnterGeneMapForParent {
	// gene as key
	// types in list
	private Map<String, List<String>> geneMap;

	/**
	 * 
	 */
	public EnterGeneMapForParent(List<String> drb1List, String child, String parent, OrganizeParentHapType op) {
		// TODO Auto-generated constructor stub
		DRBgenes drb = new DRBgenes();
		geneMap = new HashMap<String, List<String>>();
		List<String> drb345List = new ArrayList<String>();
		NonRedundantList nonRedundant = new NonRedundantList();
		int nonCount = 0;
		int alleleCount = 0;	// keep track of which allele
		int allele = 0;		// which allele
		
		for (String drb1Type : drb1List) {	// go through DRB1 type list
			alleleCount++;
			for (String drb345 : drb.getDrb0345List()) {	// go through DRB345 locus (genes)					
				// go through expected DRB1 types corresponding to DRB345
				for (String drb1 : drb.getDrb1MapList().get(drb345)) { 
					if (drb1Type.contains(drb1)) {
						if (!drb345.equals("None")) {	// DRB345
							if (op.getParentDRB345TypeList().containsKey(child)) {	// added on March 2 2017
								if (op.getParentDRB345TypeList().get(child).containsKey(parent)) {
									for (String drb345Type : op.getParentDRB345TypeList().get(child).get(parent)) {
										if (drb345Type.contains(drb345)) {	// drb345Type contains DRB345 gene
											nonRedundant.addNonRedundantList(drb345Type);
											// how about homozygous?
										}
									}
								}
							}
							
						}		
						else {	// drb345.equals("None")
							drb345List.add("");	// added here on June 8 2016
							nonCount++;
							allele = alleleCount;	// assign allele
						}
						
					}
				}					
			}				
		}
		ExpectedDRB345Count expectedCount = new ExpectedDRB345Count(drb1List);
//		System.out.println(expectedCount.getDrb345Count());
		if (nonCount != (2 - expectedCount.getDrb345Count())) {
			System.err.println("Please Review DRB345");
		}
		
		if (nonCount == 0) {	// 2 DRB345			
			// problem when DRB345 is reported only once, even though it is homozygous
			if (op.getParentDRB345TypeList().containsKey(child)) {	// added on March 2 2017
				if (op.getParentDRB345TypeList().get(child).containsKey(parent)) {
					if (op.getParentDRB345TypeList().get(child).get(parent).size() == 2) {	// non-homozygous
						geneMap.put("HLA-DRB345", op.getParentDRB345TypeList().get(child).get(parent));
					}
					else if (op.getParentDRB345TypeList().get(child).get(parent).size() == 1){	// homozygous, reported once
						List<String> list = new ArrayList<String>();
						for (int index = 0; index < 2; index ++) {
							list.addAll(op.getParentDRB345TypeList().get(child).get(parent));
						}
						geneMap.put("HLA-DRB345", list);				
					}
				}
				
			}
						
		}
		else if (nonCount == 1){
			// hemizygous
			List<String> list = new ArrayList<String>();
			if (allele == 1) {	// decide the order of DRB345
				list.addAll(drb345List);
				list.addAll(nonRedundant.getList());
			}
			else {
				list.addAll(nonRedundant.getList());
				list.addAll(drb345List);
			}
			geneMap.put("HLA-DRB345", list);
		}
		else {	// no DRB345
			geneMap.put("HLA-DRB345", drb345List);	
		}		
				
	}
	
	public Map<String, List<String>> getGeneMap() {
		return geneMap;
	}

}
