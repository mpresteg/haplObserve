/**
 * Combine Child and Parent haplotypes in a single class
 */
package workshop.haplotype.family.organize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author kazutoyo
 * @version April 29 2016
 *
 */
public class OrganizeFamilyTrioHapType extends OrganizeParentHapTypeValidated {
	// child as a key
	protected Map<String, Map<String, Map<String, List<String>>>> familyTrioHapType;

	/**
	 * @param ce
	 */
	public OrganizeFamilyTrioHapType(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		familyTrioHapType = new HashMap<String, Map<String, Map<String, List<String>>>>();
		for (String child : this.getChildList()) {			
			Map<String, Map<String, List<String>>> tmpMap = new HashMap<String, Map<String, List<String>>>();
			tmpMap.put(child, childHapTypeDRLinkageMap.get(child));	// modified here on June 14 2016
			tmpMap.putAll(parentHapTypeMapDRLinkageMap.get(child));		
			familyTrioHapType.put(child, tmpMap);		
		}
	}
	
	public Map<String, Map<String, Map<String, List<String>>>> getFamilyTrioHapType() {
		return familyTrioHapType;
	}

}
