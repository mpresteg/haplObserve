/**
 * This program checks whether haplotypes based on family and 
 * haplotypes based on DRB linkages matches or not
 */
package workshop.haplotype.family.organize;

import java.util.HashMap;
import java.util.Map;

import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author kazutoyo
 * @version June 21 2016
 *
 */
public class OrganizeParentHapTypeValidated extends
		OrganizeParentHapTypeDRBlinkage {
	private Map<String, Map<String, Map<String, Boolean>>> validatedParentMap;


	/**
	 * @param ce
	 */
	public OrganizeParentHapTypeValidated(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		validatedParentMap = new HashMap<String, Map<String, Map<String, Boolean>>>();
		
		for (String child : childList) {
			Map<String, Map<String, Boolean>> parentMap = new HashMap<String, Map<String, Boolean>>();
			for (String parent : parentList) {
				Map<String, Boolean> map = new HashMap<String, Boolean>();
				for (String gene : geneListDRB345Combined.getList()) {
					boolean validated = true;
					 if (parentHapTypeMap.get(child).get(parent).containsKey(gene)) {
						 if (parentHapTypeMapDRLinkageMap.get(child).get(parent).containsKey(gene)) {
							 if (!parentHapTypeMap.get(child).get(parent).get(gene)
									 .equals(parentHapTypeMapDRLinkageMap.get(child).get(parent).get(gene))) {
								 validated = false;
								 System.err.println("Child: " + child + ", Parent: " + parent);
								 System.err.println("Please review: " + gene + " for accuracy");
							 }
							 map.put(gene, validated);
						 }
					 }					 
				}
				parentMap.put(parent, map);
			}
			validatedParentMap.put(child, parentMap);
		}		
	}
	
	public Map<String, Map<String, Map<String, Boolean>>> getValidatedParentMap() {
		return validatedParentMap;
	}

}
