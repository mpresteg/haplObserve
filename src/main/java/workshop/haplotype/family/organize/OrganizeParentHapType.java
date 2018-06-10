/**
 * Extract parent haplotype based on child haplotype information
 */
package workshop.haplotype.family.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author kazutoyo
 * @author March 2 2017
 */
public class OrganizeParentHapType extends OrganizeChildHapTypeValidated {	//OrganizeChildHapType
	// child as key for outer Map
	// parent as key for trio Internal Map
	protected Map<String, Map<String, Map<String, List<String>>>> parentHapTypeMap;
	// outer key: child
	// inner key: parent
	// value: DRB345 type
	protected Map<String, Map<String, List<String>>> parentDRB345TypeList;


	/**
	 * @param ce
	 */
	public OrganizeParentHapType(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		parentHapTypeMap = new HashMap<String, Map<String, Map<String, List<String>>>>();
		parentDRB345TypeList = new HashMap<String, Map<String, List<String>>>();
		
		for (String child : childList) {	// go through child list
			Map<String, Map<String, List<String>>> tmpMap = new HashMap<String, Map<String, List<String>>>();
			Map<String, List<String>> drb345Map = new HashMap<String, List<String>>();
			
			for (String parent : parentList) {		// go through parent list	
				Map<String, List<String>> tmpMapList = new HashMap<String, List<String>>();

				tmpMapList.putAll(addInfo(child, parent));	// see below
				if (!tmpMapList.isEmpty()) {
					tmpMap.put(parent, tmpMapList);	
				}
				
				if (tmpMap.get(parent).containsKey("HLA-DRB345")) {
					drb345Map.put(parent, tmpMap.get(parent).get("HLA-DRB345"));
				}
							
			}
			parentHapTypeMap.put(child, tmpMap);
			if (!drb345Map.isEmpty()) {
				parentDRB345TypeList.put(child, drb345Map);
			}			
		}		
	}
	
	// function addInfo
	public Map<String, List<String>> addInfo(String child, String parent) {
		Map<String, List<String>> pmapList = new HashMap<String, List<String>>();
		
		for (String gene : geneListDRB345Combined.getList()) {	// go through gene
//			System.out.println(gene);
			if (hlaTypeBySample.get(parent).containsKey(gene)) {
				String p1 = hlaTypeBySample.get(parent).get(gene).get(0);	// parent gene allele1
				String p2 = "";
				if (gene.contains("HLA-DRB345")) {	// DRB345 homozygous issue
					if (hlaTypeBySample.get(parent).get(gene).size() > 1) {
						p2 = this.hlaTypeBySample.get(parent).get(gene).get(1);
					}
				}
				else {	// not DRB345
					p2 = hlaTypeBySample.get(parent).get(gene).get(1);	
				}
				
				String ch1 = "";
				if (childHapTypeDRLinkageMap.get(child).containsKey(gene)) {
					if (parent.equals(parentList.get(0))) { // bug fixed: getChildHapType(), not getHlaTypeBySample()
						ch1 = childHapTypeDRLinkageMap.get(child).get(gene).get(0);
					}
					else {	// parent.equals(parentList.get(1))
						if (childHapTypeDRLinkageMap.get(child).get(gene).size() > 1) {
							ch1 = childHapTypeDRLinkageMap.get(child).get(gene).get(1);	// trouble here		
						}
								
					}
				}
								
				if (ch1.contains(p1)) {	// ch1 may be changed to have ambiguity, see 			
					pmapList.put(gene, hlaTypeBySample.get(parent).get(gene));
				}
				else if (ch1.contains(p2)) {	// swap the order
					List<String> list = new ArrayList<String>();
					list.add(p2);
					list.add(p1);
					pmapList.put(gene, list);
				}
				else {	// no match
					if (gene.contains("HLA-DRB345")) {	// DRB345
						List<String> list = new ArrayList<String>();
						list.add("");	// child has no DRB345
						list.add(p1);	// but exist one allele in parent
						pmapList.put(gene, list);
					}
					// originally commented out, but removed comment sign
					else {
						List<String> list = new ArrayList<String>();
						list.add(gene + "*NoMatch");	// allele1
						list.add(gene + "*NoMatch");	// allele2
						pmapList.put(gene, list);
					}
					
				}
			}
		}		
		return pmapList;
	}
	
	public Map<String, Map<String, Map<String, List<String>>>> getParentHapTypeMap() {
		return parentHapTypeMap;
	}
	
	public Map<String, Map<String, List<String>>> getParentDRB345TypeList() {
		return parentDRB345TypeList;
	}

}
