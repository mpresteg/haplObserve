/**
 * obtain parent final pairwise list
 */
package workshop.haplotype.organize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import workshop.haplotype.family.organize.OrganizeChildHapTypeDRBlinkage;

/**
 * @author kazu
 * @version November 8 2017
 *
 */
public class ChooseParentPairWiseList extends ChooseUniqueChildParentPairWiseList {
	// parent
	// pairwise list
	protected Map<String, List<List<String>>> parentPairWiseList;

	/**
	 * @param locus1
	 * @param locus2
	 * @param of
	 */
	public ChooseParentPairWiseList(String locus1, String locus2,
			OrganizeChildHapTypeDRBlinkage of) {
		super(locus1, locus2, of);
		// TODO Auto-generated constructor stub
		parentPairWiseList = new HashMap<String, List<List<String>>>();
		
		if (uniquePairWise) {
			for (String child : successChild.getList()) {
				for (String parent : childParentPairWise.get(child).keySet()) {	// go through parent
					for (List<String> list : childParentPairWise.get(child).get(parent).keySet()) {
						parentPairWiseList.put(parent, childParentPairWise.get(child).get(parent).get(list));						
					}					
				}
				
			}			
		}
	}
	
	public Map<String, List<List<String>>> getParentPairWiseList() {
		return parentPairWiseList;
	}

}
