/**
 * This class is to identify at least one unique child pairwise combination
 * second (non unique) child list is added
 */
package workshop.haplotype.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.family.organize.OrganizeChildHapTypeDRBlinkage;

/**
 * @author kazu
 * @version November 8 2017
 *
 */
public class ChooseUniqueChildPairWiseList extends ChoosePairWiseList {
	protected Map<String, List<List<String>>> uniqueChildPairWiseList;

	/**
	 * @param locus1
	 * @param locus2
	 * @param of
	 */
	public ChooseUniqueChildPairWiseList(String locus1, String locus2,
			OrganizeChildHapTypeDRBlinkage of) {
		super(locus1, locus2, of);
		// TODO Auto-generated constructor stub
		uniqueChildPairWiseList = new HashMap<String, List<List<String>>>();
		
		if (uniquePairWise) {	// if unique pairwise exists
			for (String child : successChild.getList()) {	// go through only successful children
//				System.out.println(child);
				List<List<String>> listList = new ArrayList<List<String>>();
				
				// childParentPairWise inherited from ChoosePairWiseList
				for (String parent : childParentPairWise.get(child).keySet()) {	// go through parent
					for (List<String> clist : childParentPairWise.get(child).get(parent).keySet()) {	// child list
						listList.add(clist);
//						System.out.println(clist);
						// generate child pairwise combination to find second pairwise combination
						CreatePairWiseCombination childpw = new CreatePairWiseCombination(locus1,
								locus2, of.getChildHapTypeDRLinkageMap().get(child));
						
						for (Integer cnum : childpw.getPairWiseCombination().keySet()) {
							if (childpw.getPairWiseCombination().get(cnum).contains(clist)) {	
								for (List<String> second : childpw.getPairWiseCombination().get(cnum)) {
									// second pair is different
									// homozygous is captured at listList.add(clist);
									if (!second.equals(clist)) {	
										listList.add(second);
									}									
								}	
							}
						}										
					}			
				}				
				uniqueChildPairWiseList.put(child, listList);		
			}
		}
	}
	
	public Map<String, List<List<String>>> getUniqueChildPairWiseList() {
		return uniqueChildPairWiseList;
	}

}
