/**
 * This program is developed to reduce the code in OrganizeChildHapTypeDRBlinkage
 */
package workshop.haplotype.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.family.organize.OrganizeChildHapType;
import workshop.haplotype.gene.DRBgenes;
import workshop.haplotype.gene.ExpectedDRB345Count;

/**
 * @author Kazutoyo Osoegawa
 * @version September 7 2016
 *
 */
public class EnterGeneMapForChild {
	private Map<String, List<String>> geneMap;
	private boolean valid;

	/**
	 * 
	 */
	public EnterGeneMapForChild(List<String> drb1List, String child, OrganizeChildHapType oc) {
		// TODO Auto-generated constructor stub
		DRBgenes drb = new DRBgenes();
		geneMap = new HashMap<String, List<String>>();
		valid = true;
		List<String> drb345List = new ArrayList<String>();
		NonRedundantList nonRedundant = new NonRedundantList();
		int nonCount = 0;
		int alleleCount = 0;	// keep track of which allele
		int allele = 0;		// which allele
		for (String drb1Type : drb1List) {	// go through DRB1 type list from child
			alleleCount++;
			for (String drb345 : drb.getDrb0345List()) {	// go through DRB345 locus (genes)	or None				
				// go through expected DRB1 types corresponding to DRB345
				for (String drb1 : drb.getDrb1MapList().get(drb345)) { 
					if (drb1Type.contains(drb1)) {
						if (!drb345.equals("None")) {	// DRB345							
							if (oc.getChildDRB345TypeList().containsKey(child)) {
								for (String drb345Type : oc.getChildDRB345TypeList().get(child)) {
									if (drb345Type.contains(drb345)) {	// drb345Type contains DRB345 gene
										nonRedundant.addNonRedundantList(drb345Type);
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
		
		// This section was added to fix problem for Family47 DRB3 problem
		NonRedundantList tmp = new NonRedundantList();
		if (nonRedundant.getList().size() > 1) {	// if more than 1 types 
			for (String str : nonRedundant.getList()) {
				for (String original : oc.getHlaTypeBySample().get(child).get("HLA-DRB345")) {
					if(str.equals(original)) {		// pick an allele that is identical to the original			
						tmp.addNonRedundantList(str);
					}
				}
			}
		}		
		//
		ExpectedDRB345Count expectedCount = new ExpectedDRB345Count(drb1List);
		if (nonCount != (2 - expectedCount.getDrb345Count())) {
			System.err.println("DRB345 ERROR: Please Review DRB345");
			valid = false;
		}
		
		if (nonCount == 0) {
			// 2 DRB345
			if (nonRedundant.getList().size() == 1)  {	// homozygous
				geneMap.put("HLA-DRB345", oc.getChildHapTypeMap().get(child).get("HLA-DRB345"));
			}
			else {	// heterozygous, fixed on September 7 2016
				geneMap.put("HLA-DRB345", nonRedundant.getList());
			}
			
		}
		else if (nonCount == 1){
			// hemizygous
			List<String> list = new ArrayList<String>();
			if (allele == 1) {	// decide the order of DRB345
				list.addAll(drb345List);
				if (tmp.getList().size() == 0) {	// added here on August 26
					list.addAll(nonRedundant.getList());
				}
				else {
					list.addAll(tmp.getList());
				}
				
			}
			else {
				if (tmp.getList().size() == 0) {
					list.addAll(nonRedundant.getList());
				}
				else {
					list.addAll(tmp.getList());
				}
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
	
	public boolean getValid() {
		return valid;
	}

}
