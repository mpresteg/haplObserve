/**
 * from parent pairwise to remaining child pairwise
 * update childParentPairWise
 */
package workshop.haplotype.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import workshop.haplotype.family.organize.OrganizeChildHapTypeDRBlinkage;

/**
 * @author kazu
 * @version November 8 2017
 *
 */
public class ChooseChildPairWiseList extends ChooseParentPairWiseList {
	// child
	// pairwise list
	protected Map<String, List<List<String>>> childPairWiseList;

	/**
	 * @param locus1
	 * @param locus2
	 * @param of
	 */
	public ChooseChildPairWiseList(String locus1, String locus2,
			OrganizeChildHapTypeDRBlinkage of) {
		super(locus1, locus2, of);
		// TODO Auto-generated constructor stub
		childPairWiseList = new HashMap<String, List<List<String>>>();
		for (String child : of.getChildList()) {	// go through children
			if (!successChild.getList().contains(child)) {
				CreatePairWiseCombination childpw = new CreatePairWiseCombination(locus1,
						locus2, of.getChildHapTypeDRLinkageMap().get(child));
				
				for (Integer cnum : childpw.getPairWiseCombination().keySet()) {	
					Map<String, Map<List<String>, List<List<String>>>> parentPairWise =
							new HashMap<String, Map<List<String>, List<List<String>>>>();
					
					List<List<String>> listList = new LinkedList<List<String>>();
					listList.addAll(childpw.getPairWiseCombination().get(cnum));
					Map<List<String>, List<List<String>>> mapListList = new HashMap<List<String>, List<List<String>>>();
					
					for (String parent : parentPairWiseList.keySet()) {								
						for (List<String> plist : parentPairWiseList.get(parent)) {							
							boolean found = false;
							int index = 0;
							while (index < listList.size()) {
								if (listList.get(index).equals(plist)) {
									listList.remove(index);
									found = true;
									break;
								}
								index++;
							}
							if (found) {
								mapListList.put(plist, parentPairWiseList.get(parent));
							}							
						}						
						if (!mapListList.isEmpty()) {
							parentPairWise.put(parent, mapListList);
						}						
					}
					if (parentPairWise.keySet().size() == 2) {
						childParentPairWise.put(child, parentPairWise);
					}
				}
			}
		}
		
		for (String child : childParentPairWise.keySet()) {
//			System.out.println(child);
			List<List<String>> listList = new ArrayList<List<String>>();
			for (String parent : childParentPairWise.get(child).keySet()) {
				for (List<String> list : childParentPairWise.get(child).get(parent).keySet()) {
					listList.add(list);
//					System.out.println(list);
				}
			}
			childPairWiseList.put(child, listList);
		}		
	}
	
	public Map<String, List<List<String>>> getChildPairWiseList() {
		return childPairWiseList;
	}

}
