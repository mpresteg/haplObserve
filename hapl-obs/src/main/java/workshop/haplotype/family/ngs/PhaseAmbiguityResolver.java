/**
 * Resolve phase ambiguity
 */
package workshop.haplotype.family.ngs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author kazu
 * @version November 13 2017
 *
 */
public class PhaseAmbiguityResolver {
	// child
	// cList
	// parent
	// pList
	private Map<String, Map<List<String>, Map<String, List<String>>>> childParentList;

	/**
	 * 
	 */
	public PhaseAmbiguityResolver(List<String> childList, List<String> parentList, 
			Map<String, Map<String, List<List<String>>>> map, String gene) {
		// TODO Auto-generated constructor stub
		childParentList = new HashMap<String, Map<List<String>, Map<String, List<String>>>>();
		
		for (String child: childList) {	// go through children
			
			Map<List<String>, Map<String, List<String>>> cListMap = 
					new HashMap<List<String>, Map<String, List<String>>>(); 
			for (List<String> cList : map.get(child).get(gene)) {
				Set<String> cSet = new TreeSet<String>();				
				for (String cAllele : cList) {
					cSet.add(cAllele);	// populate child allele in order
//					System.out.println(cAllele);
				}
				
				boolean test = false;				
				for (List<String> p0List : map.get(parentList.get(0)).get(gene)) {	// first parent
					for (String p0Allele : p0List) {						
						
						for (List<String> p1List : map.get(parentList.get(1)).get(gene)) {		// second parent
							for (String p1Allele : p1List) {
								Set<String> pSet = new TreeSet<String>();	
								pSet.add(p0Allele);	// generate p0 & p1 allele combination in order
								pSet.add(p1Allele);
								
								if ((cSet.equals(pSet)) && (!test)) {		// child and parent set is identical
									Map<String, List<String>> parentMap = new HashMap<String, List<String>>();
									parentMap.put(parentList.get(0), p0List);	// first parent
									parentMap.put(parentList.get(1), p1List);	// second parent

									cListMap.put(cList, parentMap);	// child
									test = true;	// see above
//									System.out.println(cList);
									break;
								}
							}						
						}						
					}									
				}		
				if (test) {	// if found break out
					break;
				}
			}
			if (!cListMap.isEmpty()) {
				childParentList.put(child, cListMap);	
			}								
		}	
	}
	
	public Map<String, Map<List<String>, Map<String, List<String>>>> getChildParentList() {
		return childParentList;
	}

}
