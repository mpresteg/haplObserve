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
public class OrganizeChildHapTypeValidated extends
					OrganizeChildHapTypePairWise {
	// outer key: child
	// inner key: gene
	private Map<String, Map<String, Boolean>> validatedMap;

	/**
	 * @param ce
	 */
	public OrganizeChildHapTypeValidated(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		validatedMap = new HashMap<String, Map<String, Boolean>>();
		
		for (String child : childList) {			
			Map<String, Boolean> map = new HashMap<String, Boolean>();
			for (String gene : geneListDRB345Combined.getList()) {
				boolean validated = true;
				if (childHapTypeMap.get(child).containsKey(gene)) {
					if (childHapTypeDRLinkageMap.get(child).containsKey(gene)) {
						if (!childHapTypeMap.get(child).get(gene).
								equals(childHapTypeDRLinkageMap.get(child).get(gene))) {
							validated = false;
							System.err.println(child);
							System.err.println("Please review: " + gene + " for accuracy");
						}
						map.put(gene, validated);	
					}					
				}
			}
			validatedMap.put(child, map);
		}
		
	}
	
	public Map<String, Map<String, Boolean>> getValidatedMap() {
		return validatedMap;
	}

}
