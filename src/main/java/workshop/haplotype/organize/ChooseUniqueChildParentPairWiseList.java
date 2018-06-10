/**
 * identify second pairwise combination inherited from parent2
 * the first pairwise combination is found in uniqueChildPairWiseList
 * update childParentPairWise
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
public class ChooseUniqueChildParentPairWiseList extends
		ChooseUniqueChildPairWiseList {

	/**
	 * @param locus1
	 * @param locus2
	 * @param of
	 */
	public ChooseUniqueChildParentPairWiseList(String locus1, String locus2,
			OrganizeChildHapTypeDRBlinkage of) {
		super(locus1, locus2, of);
		// TODO Auto-generated constructor stub
		if (uniquePairWise) {
			for (String child : uniqueChildPairWiseList.keySet()) {	// go through children
				if (childParentPairWise.get(child).keySet().size() == 1) {	// only one parent success
					String parent1 = "";	// first parent id => found unique pairwise
					String parent2 = "";	// second parent id
					for (String parent : of.getParentList()) {	// go through parent list
						if (!childParentPairWise.get(child).keySet().contains(parent)) {	// different parent
							parent2 = parent;
						}					
						else {	// if exists
							parent1 = parent;
						}
					}
					// child list derived from parent2 list => not unique
					List<String> p2list = new ArrayList<String>();	
					for (List<String> list : uniqueChildPairWiseList.get(child)) {	// go through child list
						for (String parent : childParentPairWise.get(child).keySet()) {
							// go through child list
							for (List<String> clist : childParentPairWise.get(child).get(parent).keySet()) {
								if (!clist.equals(list)) {	// identify second combination from parent2
									p2list.addAll(list);
								}
							}
						}
					}
					
					CreatePairWiseCombination parentpw = 
							new CreatePairWiseCombination(locus1, locus2, of.getHlaTypeBySample().get(parent2));
					
					Map<List<String>, List<List<String>>> mapListList = new HashMap<List<String>, List<List<String>>>();
					for (Integer pnum : parentpw.getPairWiseCombination().keySet()) {
						if (parentpw.getPairWiseCombination().get(pnum).contains(p2list)) {
							mapListList.put(p2list, parentpw.getPairWiseCombination().get(pnum));
						}
					}
					Map<String, Map<List<String>, List<List<String>>>> parentPairWise =
							new HashMap<String, Map<List<String>, List<List<String>>>>();
					
					parentPairWise.put(parent1, childParentPairWise.get(child).get(parent1));	// parent1
					parentPairWise.put(parent2, mapListList);	// parent2
					// update childParentPairWise
					childParentPairWise.put(child, parentPairWise);
					
				}
				else {
					// both parent was success => do nothing
				}				
			}
		}
	}

}
