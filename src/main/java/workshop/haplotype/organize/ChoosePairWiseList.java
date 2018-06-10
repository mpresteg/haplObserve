/**
 * Identify at least one unique child pairwise combination,
 * and corresponding parent pairwise combination
 */
package workshop.haplotype.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import workshop.haplotype.family.organize.OrganizeChildHapTypeDRBlinkage;

/**
 * @author kazu
 * @version November 8 2017
 *
 */
public class ChoosePairWiseList {
	protected boolean pairWiseTest;
	// child
	// parent
	// child list
	// parent list list
	protected Map<String, Map<String, Map<List<String>, List<List<String>>>>> childParentPairWise;
	protected boolean uniquePairWise;		// test if unique pairwise found or not
	protected NonRedundantList successChild;	// list of child that had unique pairwise

	/**
	 * 
	 */
	public ChoosePairWiseList(String locus1, String locus2, OrganizeChildHapTypeDRBlinkage of) {
		// TODO Auto-generated constructor stub
		pairWiseTest = false;		// default true		
		childParentPairWise = new HashMap<String, Map<String, Map<List<String>, List<List<String>>>>>();
		uniquePairWise = false;
		successChild = new NonRedundantList();
				
		for (String child : of.getChildList()) {	// go through children
			if (!of.getHlaTypeBySample().get(child).containsKey(locus1)) {	// locus1 not typed
				break;
			}
			else if (!of.getHlaTypeBySample().get(child).containsKey(locus2)) {	// locus2 not typed
				break;
			}
			else {	// obtain pairwise combination for a child
				// obtain from childHapTypeDRLinkageMap()
				CreatePairWiseCombination childpw = 	
					new CreatePairWiseCombination(locus1, locus2, of.getChildHapTypeDRLinkageMap().get(child));
				
				Map<String, Map<List<String>, List<List<String>>>> parentPairWise =
									new HashMap<String, Map<List<String>, List<List<String>>>>();
								
				for (String parent : of.getParentList()) {	// go through parents
					CreatePairWiseCombination parentpw = 
							new CreatePairWiseCombination(locus1, locus2, of.getHlaTypeBySample().get(parent));
					
					List<List<String>> sortedList = new ArrayList<List<String>>();
					Set<List<String>> set = new HashSet<List<String>>();									
					Map<List<String>, List<List<String>>> mapListList = new HashMap<List<String>, List<List<String>>>();
					
					for (Integer cnum : childpw.getPairWiseCombination().keySet()) {
						for (List<String> list : childpw.getPairWiseCombination().get(cnum)) {
							for (Integer pnum : parentpw.getPairWiseCombination().keySet()) {
								if (parentpw.getPairWiseCombination().get(pnum).contains(list)) {
									if (!set.contains(list)) {
										sortedList.add(list);	// unique child pairwise list
										set.add(list);
										mapListList.put(list, parentpw.getPairWiseCombination().get(pnum));
									}	
								}
							}							
						}
					}
					if (sortedList.size() == 1) {		// unique => only one child pairwise possibility
						uniquePairWise = true;		// at least one
						successChild.addNonRedundantList(child);
						for (List<String> list : sortedList) {
							for (Integer pnum : parentpw.getPairWiseCombination().keySet()) {
								if (parentpw.getPairWiseCombination().get(pnum).contains(list)) {
									parentPairWise.put(parent, mapListList);								
								}
							}
						}
					}					
				}
				if (!parentPairWise.isEmpty()) {
					childParentPairWise.put(child, parentPairWise);
				}			
			}			
		}
		if (uniquePairWise) {
			pairWiseTest = true;
		}
	}
	
	public boolean getPairWiseTest() {
		return pairWiseTest;
	}
	
	public Map<String, Map<String, Map<List<String>, List<List<String>>>>> getChildParentPairWise() {
		return childParentPairWise;
	}
	
	public NonRedundantList getSuccessChild() {
		return successChild;
	}

}
